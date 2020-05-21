/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.metadata;

import ca.uhn.hl7v2.model.v25.datatype.HD;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Hl7v2Based.Holder;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import java.util.Objects;

import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.UNIVERSAL_ID_TYPE_OID;

/**
 * Represents an authority that assigns IDs.
 * <p>
 * This class is based on the HL7 HD data type.
 * <p>
 * Note that most assigning authorities used in XSD only allow the definition of the
 * universal ID. The ID type must be {@code ISO} and the namespace ID has to be empty.
 * The constructor {@link #AssigningAuthority(String)} can be used to create such
 * authorities.
 * <p>
 * All members of this class are allowed to be <code>null</code>. When transforming
 * to HL7 this indicates that the values are empty. Trailing empty values are
 * removed from the HL7 string.
 * @author Jens Riemschneider
 * @author Dmytro Rud
 */
@XmlAccessorType()
@XmlType(name = "AssigningAuthority", propOrder = {"universalId", "universalIdType"})
public class AssigningAuthority extends Hl7v2Based<Holder<HD>> {
    private static final long serialVersionUID = 5350057820250191032L;

    public AssigningAuthority() {
        super(new Holder<>(new HD(MESSAGE)));
    }


    public AssigningAuthority(Holder<HD> hdHolder) {
        super(hdHolder);
    }


    public AssigningAuthority(HD hd) {
        super(new Holder<>(hd));
    }

    /**
     * Constructs an assigning authority.
     * @param universalId
     *          the universal ID (HD.2).
     * @param universalIdType
     *          the type of the universal ID (HD.3).
     */
    public AssigningAuthority(String universalId, String universalIdType) {
        this();
        setUniversalId(universalId);
        setUniversalIdType(universalIdType);
    }

    /**
     * Constructs an assigning authority that complies with the rules of the XDS profile.
     * @param universalId
     *          the universal ID (HD.2).
     */
    public AssigningAuthority(String universalId) {
        this();
        setUniversalId(universalId);
        setUniversalIdType(UNIVERSAL_ID_TYPE_OID);
    }

    /**
     * @return the universal ID (HD.2).
     */
    @XmlAttribute
    public String getUniversalId() {
        return getHapiObject().getInternal().getHd2_UniversalID().getValue();
    }

    /**
     * @param universalId
     *          the universal ID (HD.2).
     */
    public void setUniversalId(String universalId) {
        setValue(getHapiObject().getInternal().getHd2_UniversalID(), universalId);
    }

    /**
     * @return the universal type ID (HD.3).
     */
    @XmlAttribute
    public String getUniversalIdType() {
        return getHapiObject().getInternal().getHd3_UniversalIDType().getValue();
    }

    /**
     * @param universalIdType
     *          the universal type ID (HD.3).
     */
    public void setUniversalIdType(String universalIdType) {
        setValue(getHapiObject().getInternal().getHd3_UniversalIDType(), universalIdType);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (AssigningAuthority) o;
        return Objects.equals(getUniversalId(), that.getUniversalId()) &&
                Objects.equals(getUniversalIdType(), that.getUniversalIdType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniversalId(), getUniversalIdType());
    }

    @Override
    public String toString() {
        return "AssigningAuthority(" +
                "universalId=" + getUniversalId() +
                ", universalIdType=" + getUniversalIdType() +
                ')';
    }
}
