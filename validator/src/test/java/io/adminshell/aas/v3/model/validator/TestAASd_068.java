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

import io.adminshell.aas.v3.model.*;
import io.adminshell.aas.v3.model.impl.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> If the semanticId of a Range submodel element references a ConceptDescription then
 * DataSpecificationIEC61360/dataType shall be a numerical one, i.e. REAL_* or RATIONAL_*. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_068 {
	@Test
	public void wrongConceptDescriptionDatatype() throws ValidationException {

		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.dataType(DataTypeIEC61360.INTEGER_COUNT) // This should be DataTypeIEC61360.REAL_* or RATIONAL_*
				.preferredName(new LangString("Wrong Data Specification", "en"))
				.definition(new LangString("A definition of a Date in English.", "en"))
				.build();

		EmbeddedDataSpecification embeddedDataSpecification = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification( ConstraintTestHelper.createDummyReference() )
				.build();

		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("Concept-Description", "http://example.org/MultilanguageCD", "constant");
		cd.setCategory("PROPERTY");
		cd.setEmbeddedDataSpecifications(new ArrayList<>(){{ add(embeddedDataSpecification) ; }} );

		Reference reference = ConstraintTestHelper.createDummyReference();
		Key key = reference.getKeys().get(0);
		key.setIdType(KeyType.IRI);
		key.setType(KeyElements.CONCEPT_DESCRIPTION);
		key.setValue(cd.getIdentification().getIdentifier());

		Range range = new DefaultRange.Builder()
				.idShort("idShort")
				.semanticId(reference)
				.valueType("temperature")
				.max("50")
				.min("-30")
				.build();

		Submodel submodel = new DefaultSubmodel.Builder()
				.idShort("submodel_idShort")
				.identification(new DefaultIdentifier.Builder().identifier("http://example.org/TestSubmodel").idType(IdentifierType.IRI).build())
				.submodelElements(new ArrayList<>() {{add(range);}})
				.build();

		AssetAdministrationShellEnvironment wrongAssetAdministrationShellEnvironment = ConstraintTestHelper
				.createEnvironment(
						submodel,
						new ArrayList<>() {{add(cd);}}
				);

		try {
			ShaclValidator.getInstance().validate(wrongAssetAdministrationShellEnvironment);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith("If the semanticId of a Range submodel element references a " +
							"ConceptDescription then DataSpecificationIEC61360/dataType shall be a numerical one, i.e. " +
							"Real* or Rational*."));
		}


	}

	@Test
	public void correctRealConceptDescriptionDatatype() throws ValidationException {


		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.dataType(DataTypeIEC61360.REAL_COUNT) // This should be DataTypeIEC61360.STRING_TRANSLATABLE
				.preferredName(new LangString("Data Specification", "en"))
				.definition(new LangString("A definition of a REAL_COUNT in English.", "en"))
				.build();

		EmbeddedDataSpecification embeddedDataSpecification = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification( ConstraintTestHelper.createDummyReference() )
				.build();

		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("Concept-Description", "http://example.org/MultilanguageCD", "constant");
		cd.setCategory("PROPERTY");
		cd.setEmbeddedDataSpecifications(new ArrayList<>(){{ add(embeddedDataSpecification) ; }} );

		Reference reference = ConstraintTestHelper.createDummyReference();
		Key key = reference.getKeys().get(0);
		key.setIdType(KeyType.IRI);
		key.setType(KeyElements.CONCEPT_DESCRIPTION);
		key.setValue(cd.getIdentification().getIdentifier());

		Range range = new DefaultRange.Builder()
				.idShort("idShort")
				.semanticId(reference)
				.valueType("temperature")
				.max("50")
				.min("-30")
				.build();

		Submodel submodel = new DefaultSubmodel.Builder()
				.idShort("submodel_idShort")
				.identification(new DefaultIdentifier.Builder().identifier("http://example.org/TestSubmodel").idType(IdentifierType.IRI).build())
				.submodelElements(new ArrayList<>() {{add(range);}})
				.build();

		AssetAdministrationShellEnvironment assetAdministrationShellEnvironment = ConstraintTestHelper
				.createEnvironment(
						submodel,
						new ArrayList<>() {{add(cd);}}
				);


		ShaclValidator.getInstance().validate(assetAdministrationShellEnvironment);

	}


	@Test
	public void correctRationalConceptDescriptionDatatype() throws ValidationException {


		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.dataType(DataTypeIEC61360.RATIONAL_MEASURE) // This should be DataTypeIEC61360.STRING_TRANSLATABLE
				.preferredName(new LangString("Data Specification", "en"))
				.definition(new LangString("A definition of a RATIONAL_MEASURE in English.", "en"))
				.build();

		EmbeddedDataSpecification embeddedDataSpecification = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification( ConstraintTestHelper.createDummyReference() )
				.build();

		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("Concept-Description", "http://example.org/MultilanguageCD", "constant");
		cd.setCategory("PROPERTY");
		cd.setEmbeddedDataSpecifications(new ArrayList<>(){{ add(embeddedDataSpecification) ; }} );

		Reference reference = ConstraintTestHelper.createDummyReference();
		Key key = reference.getKeys().get(0);
		key.setIdType(KeyType.IRI);
		key.setType(KeyElements.CONCEPT_DESCRIPTION);
		key.setValue(cd.getIdentification().getIdentifier());

		Range range = new DefaultRange.Builder()
				.idShort("idShort")
				.semanticId(reference)
				.valueType("temperature")
				.max("50")
				.min("-30")
				.build();

		Submodel submodel = new DefaultSubmodel.Builder()
				.idShort("submodel_idShort")
				.identification(new DefaultIdentifier.Builder().identifier("http://example.org/TestSubmodel").idType(IdentifierType.IRI).build())
				.submodelElements(new ArrayList<>() {{add(range);}})
				.build();

		AssetAdministrationShellEnvironment assetAdministrationShellEnvironment = ConstraintTestHelper
				.createEnvironment(
						submodel,
						new ArrayList<>() {{add(cd);}}
				);


		ShaclValidator.getInstance().validate(assetAdministrationShellEnvironment);

	}
}
