/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openehealth.ipf.platform.camel.ihe.fhir.iti78;

import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.ResourceType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehealth.ipf.commons.ihe.core.atna.MockedSender;
import org.openehealth.ipf.commons.ihe.fhir.iti78.PdqPatient;
import org.openhealthtools.ihe.atna.auditor.models.rfc3881.ActiveParticipantType;
import org.openhealthtools.ihe.atna.auditor.models.rfc3881.AuditMessage;
import org.openhealthtools.ihe.atna.auditor.models.rfc3881.CodedValueType;
import org.openhealthtools.ihe.atna.auditor.models.rfc3881.ParticipantObjectIdentificationType;

import javax.servlet.ServletException;

import static org.junit.Assert.*;

/**
 *
 */
public class TestIti78Success extends AbstractTestIti78 {

    private static final String CONTEXT_DESCRIPTOR = "iti-78.xml";

    @BeforeClass
    public static void setUpClass() throws ServletException {
        startServer(CONTEXT_DESCRIPTOR);
    }

    @Test
    public void testGetConformance() {
        Conformance conf = client.fetchConformance().ofType(Conformance.class).execute();

        assertEquals(1, conf.getRest().size());
        Conformance.ConformanceRestComponent component = conf.getRest().iterator().next();
        Conformance.ConformanceRestResourceComponent resource = component.getResource().get(1);
        assertEquals("Patient", resource.getType());

        // printAsXML(conf);
    }

    @Test
    public void testSendManualPdqm() {

        Bundle result = sendManually(familyParameters());

        // printAsXML(result);

        assertEquals(Bundle.BundleType.SEARCHSET, result.getType());
        assertEquals(ResourceType.Bundle, result.getResourceType());
        assertTrue(result.hasEntry());


        PdqPatient p = (PdqPatient)result.getEntry().get(0).getResource();
        assertEquals("Test", p.getName().get(0).getFamily().get(0).getValue());
        assertEquals("http://localhost:8999/Patient/4711", p.getId());


        // Check ATNA Audit
        MockedSender sender = getAuditSender();
        assertEquals(1, sender.getMessages().size());
        AuditMessage event = sender.getMessages().get(0).getAuditMessage();

        assertEquals("E", event.getEventIdentification().getEventActionCode());
        CodedValueType eventId = event.getEventIdentification().getEventID();
        assertEquals("110112", eventId.getCode());
        assertEquals("DCM", eventId.getCodeSystemName());
        assertEquals("Query", eventId.getOriginalText());

        CodedValueType eventTypeCode = event.getEventIdentification().getEventTypeCode().get(0);
        assertEquals("ITI-78", eventTypeCode.getCode());
        assertEquals("IHE Transactions", eventTypeCode.getCodeSystemName());
        assertEquals("Mobile Patient Demographics Query", eventTypeCode.getOriginalText());

        ActiveParticipantType destination = event.getActiveParticipant().get(1);
        assertFalse(destination.isUserIsRequestor());
        assertEquals("http://localhost:" + DEMO_APP_PORT + "/Patient", destination.getUserID());
        assertEquals("localhost", destination.getNetworkAccessPointID());

        ParticipantObjectIdentificationType poit = event.getParticipantObjectIdentification().get(0);
        assertEquals(2, poit.getParticipantObjectTypeCode().shortValue());
        assertEquals(24, poit.getParticipantObjectTypeCodeRole().shortValue());
        CodedValueType poitTypeCode = poit.getParticipantObjectIDTypeCode();
        assertEquals("ITI-78", poitTypeCode.getCode());
        assertEquals("IHE Transactions", poitTypeCode.getCodeSystemName());
        assertEquals("Mobile Patient Demographics Query", poitTypeCode.getOriginalText());
        assertEquals("MobilePatientDemographicsQuery", poit.getParticipantObjectID());
        // assertEquals("targetSystem=urn%3Aoid%3A1.2.3.4.6&sourceIdentifier=urn%3Aoid%3A1.2.3.4%7C0815", new String(poit.getParticipantObjectQuery()));
    }

    @Test
    public void testGetResource() {
        PdqPatient p = client.read()
                .resource(PdqPatient.class)
                .withId("4711")
                .execute();
        assertEquals("Test", p.getName().get(0).getFamily().get(0).getValue());
        assertEquals(String.format("http://localhost:%d/Patient/4711", DEMO_APP_PORT), p.getId());
    }


    @Test
    public void testSendEndpointPdqmCriterion() {
        Bundle result = getProducerTemplate().requestBody("direct:input", familyParameters(), Bundle.class);
        // printAsXML(result);

        // Check ATNA Audit
        MockedSender sender = getAuditSender();
        assertEquals(2, sender.getMessages().size());
        // FIXME client-side audit message needs ip addresses, target URL and queryString
    }

    @Test
    public void testSendEndpointPdqmString() {
        Bundle result = getProducerTemplate().requestBody("direct:input", "Patient?family=Test", Bundle.class);
        // printAsXML(result);

        // Check ATNA Audit
        MockedSender sender = getAuditSender();
        assertEquals(2, sender.getMessages().size());
        // FIXME client-side audit message needs ip addresses, target URL and queryString
    }




}