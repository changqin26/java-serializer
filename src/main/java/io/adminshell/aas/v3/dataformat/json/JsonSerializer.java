package io.adminshell.aas.v3.dataformat.json;

import io.adminshell.aas.v3.dataformat.json.enums.EnumSerializer;
import io.adminshell.aas.v3.dataformat.json.enums.UpperCamelCaseEnumNaming;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fraunhofer.iais.eis.AccessControl;
import de.fraunhofer.iais.eis.AccessControlMixin;
import de.fraunhofer.iais.eis.AccessControlPolicyPoints;
import de.fraunhofer.iais.eis.AccessControlPolicyPointsMixin;
import de.fraunhofer.iais.eis.AccessPermissionRule;
import de.fraunhofer.iais.eis.AccessPermissionRuleMixin;
import de.fraunhofer.iais.eis.AdministrativeInformation;
import de.fraunhofer.iais.eis.AdministrativeInformationMixin;
import de.fraunhofer.iais.eis.AnnotatedRelationshipElement;
import de.fraunhofer.iais.eis.AnnotatedRelationshipElementMixin;
import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.AssetAdministrationShellMixin;
import de.fraunhofer.iais.eis.AssetInformation;
import de.fraunhofer.iais.eis.AssetInformationMixin;
import de.fraunhofer.iais.eis.AssetKind;
import de.fraunhofer.iais.eis.AssetMixin;
import de.fraunhofer.iais.eis.BasicEvent;
import de.fraunhofer.iais.eis.BasicEventMixin;
import de.fraunhofer.iais.eis.Blob;
import de.fraunhofer.iais.eis.BlobCertificate;
import de.fraunhofer.iais.eis.BlobCertificateMixin;
import de.fraunhofer.iais.eis.BlobMixin;
import de.fraunhofer.iais.eis.Capability;
import de.fraunhofer.iais.eis.CapabilityMixin;
import de.fraunhofer.iais.eis.Category;
import de.fraunhofer.iais.eis.Certificate;
import de.fraunhofer.iais.eis.CertificateMixin;
import de.fraunhofer.iais.eis.ConceptDescription;
import de.fraunhofer.iais.eis.ConceptDescriptionMixin;
import de.fraunhofer.iais.eis.Constraint;
import de.fraunhofer.iais.eis.ConstraintMixin;
import de.fraunhofer.iais.eis.DataElement;
import de.fraunhofer.iais.eis.DataElementMixin;
import de.fraunhofer.iais.eis.DataSpecification;
import de.fraunhofer.iais.eis.DataSpecificationContent;
import de.fraunhofer.iais.eis.DataSpecificationContentMixin;
import de.fraunhofer.iais.eis.DataSpecificationIEC61360;
import de.fraunhofer.iais.eis.DataSpecificationIEC61360Mixin;
import de.fraunhofer.iais.eis.DataTypeIEC61360;
import de.fraunhofer.iais.eis.Entity;
import de.fraunhofer.iais.eis.EntityMixin;
import de.fraunhofer.iais.eis.EntityType;
import de.fraunhofer.iais.eis.Event;
import de.fraunhofer.iais.eis.EventElement;
import de.fraunhofer.iais.eis.EventElementMixin;
import de.fraunhofer.iais.eis.EventMessage;
import de.fraunhofer.iais.eis.EventMessageMixin;
import de.fraunhofer.iais.eis.EventMixin;
import de.fraunhofer.iais.eis.Extension;
import de.fraunhofer.iais.eis.ExtensionMixin;
import de.fraunhofer.iais.eis.File;
import de.fraunhofer.iais.eis.FileMixin;
import de.fraunhofer.iais.eis.Formula;
import de.fraunhofer.iais.eis.FormulaMixin;
import de.fraunhofer.iais.eis.HasDataSpecification;
import de.fraunhofer.iais.eis.HasDataSpecificationMixin;
import de.fraunhofer.iais.eis.HasExtensions;
import de.fraunhofer.iais.eis.HasExtensionsMixin;
import de.fraunhofer.iais.eis.HasKind;
import de.fraunhofer.iais.eis.HasKindMixin;
import de.fraunhofer.iais.eis.HasSemantics;
import de.fraunhofer.iais.eis.HasSemanticsMixin;
import de.fraunhofer.iais.eis.Identifiable;
import de.fraunhofer.iais.eis.IdentifiableElements;
import de.fraunhofer.iais.eis.IdentifiableMixin;
import de.fraunhofer.iais.eis.Identifier;
import de.fraunhofer.iais.eis.IdentifierKeyValuePair;
import de.fraunhofer.iais.eis.IdentifierKeyValuePairMixin;
import de.fraunhofer.iais.eis.IdentifierMixin;
import de.fraunhofer.iais.eis.IdentifierType;
import de.fraunhofer.iais.eis.Key;
import de.fraunhofer.iais.eis.KeyElements;
import de.fraunhofer.iais.eis.KeyMixin;
import de.fraunhofer.iais.eis.KeyType;
import de.fraunhofer.iais.eis.LevelType;
import de.fraunhofer.iais.eis.LocalKeyType;
import de.fraunhofer.iais.eis.ModelingKind;
import de.fraunhofer.iais.eis.MultiLanguageProperty;
import de.fraunhofer.iais.eis.MultiLanguagePropertyMixin;
import de.fraunhofer.iais.eis.ObjectAttributes;
import de.fraunhofer.iais.eis.ObjectAttributesMixin;
import de.fraunhofer.iais.eis.Operation;
import de.fraunhofer.iais.eis.OperationMixin;
import de.fraunhofer.iais.eis.OperationVariable;
import de.fraunhofer.iais.eis.OperationVariableMixin;
import de.fraunhofer.iais.eis.Permission;
import de.fraunhofer.iais.eis.PermissionKind;
import de.fraunhofer.iais.eis.PermissionMixin;
import de.fraunhofer.iais.eis.PermissionsPerObject;
import de.fraunhofer.iais.eis.PermissionsPerObjectMixin;
import de.fraunhofer.iais.eis.PolicyAdministrationPoint;
import de.fraunhofer.iais.eis.PolicyAdministrationPointMixin;
import de.fraunhofer.iais.eis.PolicyDecisionPoint;
import de.fraunhofer.iais.eis.PolicyDecisionPointMixin;
import de.fraunhofer.iais.eis.PolicyEnforcementPoints;
import de.fraunhofer.iais.eis.PolicyEnforcementPointsMixin;
import de.fraunhofer.iais.eis.PolicyInformationPoints;
import de.fraunhofer.iais.eis.PolicyInformationPointsMixin;
import de.fraunhofer.iais.eis.Property;
import de.fraunhofer.iais.eis.PropertyMixin;
import de.fraunhofer.iais.eis.Qualifiable;
import de.fraunhofer.iais.eis.QualifiableMixin;
import de.fraunhofer.iais.eis.Qualifier;
import de.fraunhofer.iais.eis.QualifierMixin;
import de.fraunhofer.iais.eis.RC01;
import de.fraunhofer.iais.eis.RC01Mixin;
import de.fraunhofer.iais.eis.Range;
import de.fraunhofer.iais.eis.RangeMixin;
import de.fraunhofer.iais.eis.Referable;
import de.fraunhofer.iais.eis.ReferableElements;
import de.fraunhofer.iais.eis.ReferableMixin;
import de.fraunhofer.iais.eis.Reference;
import de.fraunhofer.iais.eis.ReferenceElement;
import de.fraunhofer.iais.eis.ReferenceElementMixin;
import de.fraunhofer.iais.eis.ReferenceMixin;
import de.fraunhofer.iais.eis.RelationshipElement;
import de.fraunhofer.iais.eis.RelationshipElementMixin;
import de.fraunhofer.iais.eis.Security;
import de.fraunhofer.iais.eis.SecurityMixin;
import de.fraunhofer.iais.eis.SubjectAttributes;
import de.fraunhofer.iais.eis.SubjectAttributesMixin;
import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.SubmodelElement;
import de.fraunhofer.iais.eis.SubmodelElementCollection;
import de.fraunhofer.iais.eis.SubmodelElementCollectionMixin;
import de.fraunhofer.iais.eis.SubmodelElementMixin;
import de.fraunhofer.iais.eis.SubmodelMixin;
import de.fraunhofer.iais.eis.View;
import de.fraunhofer.iais.eis.ViewMixin;

import io.adminshell.aas.v3.dataformat.json.enums.IdentifierTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.KeyTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.ScreamingSnakeCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.dataformat.json.serialization.DataSpecificationSerializer;
import io.adminshell.aas.v3.dataformat.json.serialization.mixins.AssetAdministrationShellEnvironmentMixin;

public class JsonSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonSerializer() {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        SimpleModule module = new SimpleModule();
        // enums with custom naming strategy
        module.addSerializer(IdentifierType.class, new EnumSerializer<>(new IdentifierTypeMapping()));
        module.addSerializer(KeyType.class, new EnumSerializer<>(new KeyTypeMapping()));
        // enums with screaming snake case naming strategy (e.g. HELLO_WORLD)
        module.addSerializer(DataTypeIEC61360.class, new EnumSerializer<>(new ScreamingSnakeCaseEnumNaming(DataTypeIEC61360.class)));
        module.addSerializer(Category.class, new EnumSerializer<>(new ScreamingSnakeCaseEnumNaming(Category.class))); // not used in JSON
        // enums with upper camel case (default) naming strategy (e.g. HelloWorld)
        module.addSerializer(AssetKind.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(AssetKind.class)));
        module.addSerializer(EntityType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(EntityType.class)));
        module.addSerializer(IdentifiableElements.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(IdentifiableElements.class))); // not used in JSON
        module.addSerializer(KeyElements.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(KeyElements.class))); // not used in JSON
        module.addSerializer(LevelType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(LevelType.class)));
        module.addSerializer(LocalKeyType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(LocalKeyType.class))); // not used in JSON
        module.addSerializer(ModelingKind.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(ModelingKind.class)));
        module.addSerializer(PermissionKind.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(PermissionKind.class))); // not used in JSON
        module.addSerializer(ReferableElements.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(ReferableElements.class))); // not used in JSON

        module.addSerializer(DataSpecification.class, new DataSpecificationSerializer());
        mapper.registerModule(module);

        mapper.addMixIn(AccessControl.class, AccessControlMixin.class);
        mapper.addMixIn(AccessControlPolicyPoints.class, AccessControlPolicyPointsMixin.class);
        mapper.addMixIn(AccessPermissionRule.class, AccessPermissionRuleMixin.class);
        mapper.addMixIn(AdministrativeInformation.class, AdministrativeInformationMixin.class);
        mapper.addMixIn(AnnotatedRelationshipElement.class, AnnotatedRelationshipElementMixin.class);
        mapper.addMixIn(AssetAdministrationShell.class, AssetAdministrationShellMixin.class);
        // does AASEnv need to have properties without value always present?
        // if yes, this requires mixin and potentially changing Default classes to init collection properties with empty collection/list
        //mapper.addMixIn(AssetAdministrationShellEnvironment.class, AssetAdministrationShellEnvironmentMixin.class);
        mapper.addMixIn(AssetInformation.class, AssetInformationMixin.class);
        mapper.addMixIn(Asset.class, AssetMixin.class);
        mapper.addMixIn(BasicEvent.class, BasicEventMixin.class);
        mapper.addMixIn(BlobCertificate.class, BlobCertificateMixin.class);
        mapper.addMixIn(Blob.class, BlobMixin.class);
        mapper.addMixIn(Capability.class, CapabilityMixin.class);
        mapper.addMixIn(Certificate.class, CertificateMixin.class);
        mapper.addMixIn(ConceptDescription.class, ConceptDescriptionMixin.class);
        mapper.addMixIn(Constraint.class, ConstraintMixin.class);
        mapper.addMixIn(DataElement.class, DataElementMixin.class);
        mapper.addMixIn(DataSpecificationContent.class, DataSpecificationContentMixin.class);
        mapper.addMixIn(DataSpecificationIEC61360.class, DataSpecificationIEC61360Mixin.class);
        mapper.addMixIn(Entity.class, EntityMixin.class);
        mapper.addMixIn(EventElement.class, EventElementMixin.class);
        mapper.addMixIn(EventMessage.class, EventMessageMixin.class);
        mapper.addMixIn(Event.class, EventMixin.class);
        mapper.addMixIn(Extension.class, ExtensionMixin.class);
        mapper.addMixIn(File.class, FileMixin.class);
        mapper.addMixIn(Formula.class, FormulaMixin.class);
        mapper.addMixIn(HasDataSpecification.class, HasDataSpecificationMixin.class);
        mapper.addMixIn(HasExtensions.class, HasExtensionsMixin.class);
        mapper.addMixIn(HasKind.class, HasKindMixin.class);
        mapper.addMixIn(HasSemantics.class, HasSemanticsMixin.class);
        mapper.addMixIn(Identifiable.class, IdentifiableMixin.class);
        mapper.addMixIn(IdentifierKeyValuePair.class, IdentifierKeyValuePairMixin.class);
        mapper.addMixIn(Identifier.class, IdentifierMixin.class);
        mapper.addMixIn(Key.class, KeyMixin.class);
        mapper.addMixIn(MultiLanguageProperty.class, MultiLanguagePropertyMixin.class);
        mapper.addMixIn(ObjectAttributes.class, ObjectAttributesMixin.class);
        mapper.addMixIn(Operation.class, OperationMixin.class);
        mapper.addMixIn(OperationVariable.class, OperationVariableMixin.class);
        mapper.addMixIn(Permission.class, PermissionMixin.class);
        mapper.addMixIn(PermissionsPerObject.class, PermissionsPerObjectMixin.class);
        mapper.addMixIn(PolicyAdministrationPoint.class, PolicyAdministrationPointMixin.class);
        mapper.addMixIn(PolicyDecisionPoint.class, PolicyDecisionPointMixin.class);
        mapper.addMixIn(PolicyEnforcementPoints.class, PolicyEnforcementPointsMixin.class);
        mapper.addMixIn(PolicyInformationPoints.class, PolicyInformationPointsMixin.class);
        mapper.addMixIn(Property.class, PropertyMixin.class);
        mapper.addMixIn(Qualifiable.class, QualifiableMixin.class);
        mapper.addMixIn(Qualifier.class, QualifierMixin.class);
        mapper.addMixIn(Range.class, RangeMixin.class);
        mapper.addMixIn(RC01.class, RC01Mixin.class);
        mapper.addMixIn(Referable.class, ReferableMixin.class);
        mapper.addMixIn(ReferenceElement.class, ReferenceElementMixin.class);
        mapper.addMixIn(Reference.class, ReferenceMixin.class);
        mapper.addMixIn(RelationshipElement.class, RelationshipElementMixin.class);
        mapper.addMixIn(Security.class, SecurityMixin.class);
        mapper.addMixIn(SubjectAttributes.class, SubjectAttributesMixin.class);
        mapper.addMixIn(SubmodelElementCollection.class, SubmodelElementCollectionMixin.class);
        mapper.addMixIn(SubmodelElement.class, SubmodelElementMixin.class);
//        mapper.addMixIn(Submodel.class, SubmodelMixin.class);
        mapper.addMixIn(Submodel.class, SubmodelMixin.class);
        mapper.addMixIn(View.class, ViewMixin.class);
    }

    public String serialize(AssetAdministrationShellEnvironment aasEnvironment) throws JsonProcessingException {
        JsonNode node = mapper.valueToTree(aasEnvironment);
        ModelTypeProcessor.postprocess(node);
        return mapper.writeValueAsString(node);
    }
}
