/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.xml.deserialization;

import static io.adminshell.aas.v3.dataformat.xml.DataSpecificationManager.PROP_DATA_SPECIFICATION;
import static io.adminshell.aas.v3.dataformat.xml.DataSpecificationManager.PROP_DATA_SPECIFICATION_CONTENT;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.adminshell.aas.v3.dataformat.xml.DataSpecificationManager;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.DefaultEmbeddedDataSpecification;

public class DataSpecificationDeserializer extends JsonDeserializer<EmbeddedDataSpecification> {

	@Override
	public EmbeddedDataSpecification deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		Object temp = parser.getCodec().readTree(parser);
		ObjectNode node = (ObjectNode) temp;
		if (node == null) {
			return null;
		}
		JsonNode nodeDataSpecification = node.get(PROP_DATA_SPECIFICATION);
		if (nodeDataSpecification == null) {
			throw new JsonMappingException(parser,
					String.format("data specification must contain node '%s'", PROP_DATA_SPECIFICATION));
		}
		JsonParser parserReference = parser.getCodec().getFactory().getCodec().treeAsTokens(nodeDataSpecification);
		parserReference.nextToken();
		Reference reference = parserReference.readValueAs(Reference.class);
		Class<? extends DataSpecificationContent> targetClass = DataSpecificationManager.getImplementation(reference);
		JsonNode nodeContent = node.get(PROP_DATA_SPECIFICATION_CONTENT);
		if (nodeContent == null) {
			context.reportInputMismatch(targetClass, "property {} must not be empty", PROP_DATA_SPECIFICATION_CONTENT);
		}
		JsonParser parserContent = parser.getCodec().getFactory().getCodec().treeAsTokens(nodeContent);
		parserContent.nextToken();
		DataSpecificationContent content = parserContent.readValueAs(targetClass);
		return new DefaultEmbeddedDataSpecification.Builder().dataSpecificationContent(content).build();
	}
}
