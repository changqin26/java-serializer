package io.adminshell.aas.v3.model.validator;

import io.adminshell.aas.v3.model.*;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;
import io.adminshell.aas.v3.model.impl.DefaultSubmodelElementCollection;
import org.junit.Test;

import java.util.*;

/**
 * Tests the following constraint:
 * <p>
 * <i> If allowDuplicates==false then it is not allowed that the collection contains several elements with the same semantics (i.e. the same semanticId). </i>
 * </p>
 *
 * @author bader, chang
 *
 */

public class TestAASd_026 {

    @Test
    public void testAllowDuplicates() {

        // TODO @chang: please write the test.

    }


    private SubmodelElementCollection createSubmodelElementCollection(Collection<SubmodelElement> values) {
        return  new DefaultSubmodelElementCollection.Builder()
                .idShort("idShort")
                .value((SubmodelElement) values)
                .semanticId(new DefaultReference.Builder()
                        .key(new DefaultKey.Builder()
                                .idType(KeyType.CUSTOM)
                                .value("the value of the semantic id")
                                .type(KeyElements.CONCEPT_DESCRIPTION)
                                .build())
                        .build())
                .build();
    }
}
