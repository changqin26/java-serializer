//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2021.07.19 um 12:09:30 PM CEST 
//


package org.opcfoundation.ua._2008._02.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DataSetOrderingType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="DataSetOrderingType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Undefined_0"/&gt;
 *     &lt;enumeration value="AscendingWriterId_1"/&gt;
 *     &lt;enumeration value="AscendingWriterIdSingle_2"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DataSetOrderingType")
@XmlEnum
public enum DataSetOrderingType {

    @XmlEnumValue("Undefined_0")
    UNDEFINED_0("Undefined_0"),
    @XmlEnumValue("AscendingWriterId_1")
    ASCENDING_WRITER_ID_1("AscendingWriterId_1"),
    @XmlEnumValue("AscendingWriterIdSingle_2")
    ASCENDING_WRITER_ID_SINGLE_2("AscendingWriterIdSingle_2");
    private final String value;

    DataSetOrderingType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataSetOrderingType fromValue(String v) {
        for (DataSetOrderingType c: DataSetOrderingType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
