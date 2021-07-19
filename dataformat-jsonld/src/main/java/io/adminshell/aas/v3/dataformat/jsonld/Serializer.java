package io.adminshell.aas.v3.dataformat.jsonld;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.jsonld.custom.ReflectiveMixInResolver;
import io.adminshell.aas.v3.dataformat.jsonld.preprocessing.JsonPreprocessor;
import io.adminshell.aas.v3.dataformat.jsonld.preprocessing.TypeNamePreprocessor;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFLanguages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Serializer implements io.adminshell.aas.v3.dataformat.Serializer, Deserializer {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final List<JsonPreprocessor> preprocessors; //TODO: It seems like this list is never used...
    private final Logger logger = LoggerFactory.getLogger(Serializer.class);

    public static String implementingClassesNamePrefix = "Default";
    public static String implementingClassesNameSuffix = "";

    private static boolean charsetWarningPrinted = false;

    public Serializer() {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.setMixInResolver(new ReflectiveMixInResolver());

        preprocessors = new ArrayList<>();
        this.addPreprocessor(new TypeNamePreprocessor());

        if(!Charset.defaultCharset().equals(StandardCharsets.UTF_8) && !charsetWarningPrinted)
        {
            charsetWarningPrinted = true;
            logger.warn("Standard Charset is set to " + Charset.defaultCharset() + " - expecting " + StandardCharsets.UTF_8 + ". Some characters might not be displayed correctly.\nThis warning is only printed once");
        }

        //Default namespaces for AAS
        addKnownNamespace("xsd", "http://www.w3.org/2001/XMLSchema#");
        addKnownNamespace("owl", "http://www.w3.org/2002/07/owl#");
        addKnownNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        addKnownNamespace("aas", "https://admin-shell.io/aas/3/0/RC01/");
        addKnownNamespace("iec61360", "https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/");
        addKnownNamespace("phys_unit", "https://admin-shell.io/DataSpecificationTemplates/DataSpecificationPhysicalUnit/3/0/RC01/");

    }

    /**
     * Serializes an object to JSON-LD representation. In order to support JSON-LD, the input instance must be
     * annotated using IDS Infomodel annotations
     *
     * @param instance the instance to be serialized
     * @return RDF serialization of the provided object graph
     */
    public String serialize(Object instance) throws IOException {
        return serialize(instance, RDFLanguages.JSONLD);
    }

    //Synchronized is required for thread safety. Without it, context elements might be missing in case of multiple simultaneous calls to this function
    public synchronized String serialize(Object instance, Lang format) throws IOException {
        if (format != RDFLanguages.JSONLD && format != RDFLanguages.TURTLE && format != RDFLanguages.RDFXML) {
            throw new IOException("RDFFormat " + format + " is currently not supported by the serializer.");
        }
        mapper.registerModule(new JsonLDModule());
        String jsonLD = (instance instanceof Collection)
                ? serializeCollection((Collection<?>) instance)
                : mapper.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
        if (format == RDFLanguages.JSONLD) return jsonLD;
        else return convertJsonLdToOtherRdfFormat(jsonLD, format);
    }

    private String serializeCollection(Collection<?> collection) throws IOException {
        String lineSep = System.lineSeparator();
        StringBuilder jsonLDBuilder = new StringBuilder();

        if (collection.isEmpty()) {
            jsonLDBuilder.append("[]");
        } else {
            jsonLDBuilder.append("[");
            jsonLDBuilder.append(lineSep);
            for (Object item : collection) {
                jsonLDBuilder.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
                jsonLDBuilder.append(",");
                jsonLDBuilder.append(lineSep);
            }
            int lastComma = jsonLDBuilder.lastIndexOf(",");
            jsonLDBuilder.replace(lastComma, lastComma + 1, "");
            jsonLDBuilder.append("]");
        }
        jsonLDBuilder.append(lineSep);

        return jsonLDBuilder.toString();
    }

    public String convertJsonLdToOtherRdfFormat(String jsonLd, Lang format) {
        Model model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, new ByteArrayInputStream(jsonLd.getBytes()), RDFLanguages.JSONLD);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        RDFDataMgr.write(os, model, format);
        return os.toString();
    }

    public String serializePlainJson(Object instance) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
    }

    /**
     * Inverse method of "serialize"
     *
     * @param serialization JSON(-LD) string
     * @param valueType     class of top level type
     * @param <T>           deserialized type
     * @return an object representing the provided JSON(-LD) structure
     */
    public <T> T deserialize(String serialization, Class<T> valueType) throws IOException {
        return new Parser().parseMessage(serialization, valueType);
    }

    /**
     * Inverse method of "serialize"
     *
     * @param rdfModel Input RDF Model to be turned into an Instance of the IDS Java classes
     * @param valueType     class of top level type
     * @param <T>           deserialized type
     * @return an object representing the provided JSON(-LD) structure
     */
    public <T> T deserialize(Model rdfModel, Class<T> valueType) throws IOException {
        return new Parser().parseMessage(rdfModel, valueType);
    }

    /**
     * Allows to add further known namespaces to the message parser. Allows parsing to Java objects with JsonSubTypes annotations with other prefixes than "ids:".
     * @param prefix Prefix to be added
     * @param namespaceUrl URL of the prefix
     */
    public static void addKnownNamespace(String prefix, String namespaceUrl)
    {
        Parser.knownNamespaces.put(prefix, namespaceUrl);
        JsonLDSerializer.contextItems.put(prefix, namespaceUrl);
    }

    /**
     * Method to add a preprocessor for deserialization.
     * <p>
     * Important note: The preprocessors are executed in the same order they were added.
     *
     * @param preprocessor the preprocessor to add
     */
    public void addPreprocessor(JsonPreprocessor preprocessor) {
        preprocessors.add(preprocessor);
    }

    /**
     * Method to add a preprocessor for deserialization.
     * <p>
     * Important note: The preprocessors are executed in the same order they were added.
     *
     * @param preprocessor the preprocessor to add
     * @param validate     set whether the preprocessors output should be checked by RDF4j
     */
    public void addPreprocessor(JsonPreprocessor preprocessor, boolean validate) {
        preprocessor.enableRDFValidation(validate);
        addPreprocessor(preprocessor);
    }

    /**
     * remove a preprocessor if no longer needed
     *
     * @param preprocessor the preprocessor to remove
     */
    public void removePreprocessor(JsonPreprocessor preprocessor) {
        preprocessors.remove(preprocessor);
    }

    @Override
    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
        try {
            return serialize(aasEnvironment);
        }
        catch (IOException e)
        {
            throw new SerializationException("Failed to serialize environment.", e);
        }
    }

    @Override
    public AssetAdministrationShellEnvironment read(String value) throws DeserializationException {
        try {
            return new Parser().parseMessage(value, AssetAdministrationShellEnvironment.class);
        }
        catch (IOException e)
        {
            throw new DeserializationException("Could not deserialize to environment.", e);
        }
    }

    @Override
    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        throw new NotImplementedException("Custom implementation support not yet implemented"); //TODO
    }
}
