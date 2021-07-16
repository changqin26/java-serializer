package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua.i4aas.types.AASAssetKindDataType;
import org.opcfoundation.ua.i4aas.types.AASCategoryDataType;
import org.opcfoundation.ua.i4aas.types.AASKeyElementsDataType;
import org.opcfoundation.ua.i4aas.types.AASKeyTypeDataType;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;

public class I4AASEnumMapper extends I4AASMapper<Enum<?>, UAVariable> {
	
	public final static Map<Class<? extends Enum>, Class<? extends Enum>> enum2enumMap = new HashMap<>();
	
	static {
		enum2enumMap.put(io.adminshell.aas.v3.model.KeyElements.class, org.opcfoundation.ua.i4aas.types.AASKeyElementsDataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.KeyType.class, org.opcfoundation.ua.i4aas.types.AASKeyTypeDataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.AssetKind.class, org.opcfoundation.ua.i4aas.types.AASAssetKindDataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.Category.class, org.opcfoundation.ua.i4aas.types.AASCategoryDataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.DataTypeIEC61360.class, org.opcfoundation.ua.i4aas.types.DataTypeIEC61360DataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.EntityType.class, org.opcfoundation.ua.i4aas.types.EntityTypeDataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.IdentifiableElements.class, org.opcfoundation.ua.i4aas.types.AASKeyElementsDataType.class);//best match so far
		enum2enumMap.put(io.adminshell.aas.v3.model.IdentifierType.class, org.opcfoundation.ua.i4aas.types.AASIdentifierTypeDataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.LevelType.class, org.opcfoundation.ua.i4aas.types.AASLevelTypeDataType.class);
		enum2enumMap.put(io.adminshell.aas.v3.model.LocalKeyType.class, org.opcfoundation.ua.i4aas.types.AASKeyTypeDataType.class);//best match so far
		enum2enumMap.put(io.adminshell.aas.v3.model.ModelingKind.class, org.opcfoundation.ua.i4aas.types.AASModelingKindDataType.class);
		//enum2enumMap.put(io.adminshell.aas.v3.model.PermissionKind.class, null);//no match, since it is from security part
		enum2enumMap.put(io.adminshell.aas.v3.model.ReferableElements.class, org.opcfoundation.ua.i4aas.types.AASKeyElementsDataType.class);//best match so far
	}

	public I4AASEnumMapper(Enum<?> src, MappingContext ctx) {
		super(src, ctx);
		
	}

	@Override
	protected UAVariable createTargetObject() {
		String name = src.getClass().getSimpleName();
		
		Enum match = findBestMatch(src);
		
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idTypeVarBuilder = UAVariable.builder().withDisplayName(I4AASUtils.createLocalizedText(name))
				.withDataType(match.getClass().getSimpleName()).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(browseNameOf(name)).withAccessLevel(3L);
		addTypeReference(idTypeVarBuilder.build(), UaId.PropertyType);
		
		JAXBElement<Integer> targetIdTypeVar2 = new ObjectFactory().createInt32(match.ordinal());
		UAVariable targetIdTypeVar = idTypeVarBuilder.withValue().withAny(targetIdTypeVar2).end().build();

		return targetIdTypeVar;
	}

	public static Enum findBestMatch(Enum<?> src) {
		
		if (!enum2enumMap.containsKey(src.getClass())) {
			throw new IllegalArgumentException("Class " + src.getClass() + " is not supported by I4AASEnumMapper");
		}
		Class<? extends Enum> aasEnum = enum2enumMap.get(src.getClass());

		String srcName = src.name().toLowerCase();
		Enum[] enumConstants = aasEnum.getEnumConstants();
		for (Enum targetEnumCandidate : enumConstants) {
			String targetEnumCandidateName = targetEnumCandidate.name().split("_")[0].toLowerCase();
			if (targetEnumCandidateName.equals(srcName)) {
				return targetEnumCandidate;
			}
		}
		
		throw new IllegalArgumentException("Could not match " +src.name()+ " with any of " + Arrays.toString(aasEnum.getEnumConstants()));
	}

	@Override
	protected void mapAndAttachChildren() {
		// nothing to do
	}
	
	public static void main(String[] args) {
		//quick test
		System.out.println(Arrays.toString(org.opcfoundation.ua.i4aas.types.AASKeyElementsDataType.class.getEnumConstants()));
	}

}
