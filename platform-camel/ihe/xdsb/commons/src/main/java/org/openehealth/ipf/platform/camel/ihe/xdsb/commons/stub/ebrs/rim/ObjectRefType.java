//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.9-03/31/2009 04:14 PM(snajper)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.04.30 at 06:20:20 PM CEST 
//


package org.openehealth.ipf.platform.camel.ihe.xdsb.commons.stub.ebrs.rim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Use to reference an Object by its id.
 *         Specifies the id attribute of the object as its id attribute.
 *         id attribute in ObjectAttributes is exactly the same syntax and semantics as
 *         id attribute in RegistryObject.
 *       
 * 
 * <p>Java class for ObjectRefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectRefType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}IdentifiableType">
 *       &lt;attribute name="createReplica" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectRefType")
public class ObjectRefType
    extends IdentifiableType
{

    @XmlAttribute
    protected Boolean createReplica;

    /**
     * Gets the value of the createReplica property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCreateReplica() {
        if (createReplica == null) {
            return false;
        } else {
            return createReplica;
        }
    }

    /**
     * Sets the value of the createReplica property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreateReplica(Boolean value) {
        this.createReplica = value;
    }

}
