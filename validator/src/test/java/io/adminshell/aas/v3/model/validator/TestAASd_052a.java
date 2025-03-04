/*******************************************************************************
 *
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
 *
 ******************************************************************************/


package io.adminshell.aas.v3.model.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultReference;

/**
 * Tests the following constraint:
 * <p>
 * <i> If the semanticId of a Property references a ConceptDescription then the
 * ConceptDescription/category shall be one of following values: VALUE,
 * PROPERTY. </i>
 * </p>
 * 
 * @author schnicke
 *
 */
public class TestAASd_052a {
	@Test
	public void correctCategories() throws ValidationException {
		String conceptDescriptionPropertyId = "conceptDescriptionProperty";
		String conceptDescriptionValueId = "conceptDescriptionValue";

		ConceptDescription correctPropertyCD = ConstraintTestHelper
				.createConceptDescription("idShort1", conceptDescriptionPropertyId, "PROPERTY");

		ConceptDescription correctValueCD = ConstraintTestHelper.createConceptDescription("idShort2",
				conceptDescriptionValueId, "VALUE");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createProperty("submodelElementIdShort1", conceptDescriptionValueId),
						createProperty("submodelElementIdShort2", conceptDescriptionPropertyId)));

		AssetAdministrationShellEnvironment correctEnv = ConstraintTestHelper.createEnvironment(sm, Arrays.asList(correctValueCD, correctPropertyCD,
						ConstraintTestHelper.getIrrelevantConceptDescription()));

		ShaclValidator.getInstance().validate(correctEnv);
	}

	@Test
	public void wrongCategory() {
		String conceptDescriptionWrongId = "conceptDescriptionWrong";

		ConceptDescription wrongCD = ConstraintTestHelper.createConceptDescription("idShort1", conceptDescriptionWrongId,
				"COLLECTION");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createProperty("submodelElementIdShort", conceptDescriptionWrongId)));
		
		AssetAdministrationShellEnvironment wrongEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(wrongCD, ConstraintTestHelper.getIrrelevantConceptDescription()));

		try {
			ShaclValidator.getInstance().validate(wrongEnv);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"If the semanticId of a Property references a ConceptDescription then the ConceptDescription/category shall be one of following values: VALUE, PROPERTY."));
		}
	}

	private Property createProperty(String idShort, String conceptDescriptionId) {
		return new DefaultProperty.Builder()
				.idShort(idShort)
				.valueType("string")
				.value("TestValue")
				.semanticId(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value(conceptDescriptionId)
								.type(KeyElements.CONCEPT_DESCRIPTION)
								.build())
						.build())
				.build();
	}


}
