/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.sme.SubmodelElementMappers;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASIdentifier;
import io.adminshell.aas.v3.model.Qualifiable;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElement;

public class SubmodelMapper extends IdentifiableMapper<Submodel> implements HasKindMapper, HasSemanticsMapper, HasDataSpecificationMapper, QualifiableMapper {

	public SubmodelMapper(Submodel src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4AASIdentifier.AASSubmodelType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		
		mapKind(source, target, ctx);
		mapSemantics(source, target, ctx);
		
		List<SubmodelElement> submodelElements = source.getSubmodelElements();
		for (SubmodelElement submodelElement : submodelElements) {
			I4AASMapper<? extends SubmodelElement, UAObject> mapper = SubmodelElementMappers.getMapper(submodelElement, ctx);
			UAObject uaSubmodel = mapper.map();
			attachAsComponent(target, uaSubmodel);
		}
		mapDataSpecification(source, target, ctx);
		mapQualifiable(source, target, ctx);
	}

}
