/*
 * Copyright 2015 the original author or authors.
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

package org.openehealth.ipf.commons.ihe.fhir

import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException
import org.hl7.fhir.instance.model.OperationOutcome
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat
import org.openehealth.ipf.commons.core.URN
import org.openehealth.ipf.commons.ihe.fhir.translation.UriMapper
import org.openehealth.ipf.modules.hl7.dsl.Repeatable

/**
 * @since 3.1
 */
class Utils {

    private static final DateTimeFormatter TIME_FORMAT = ISODateTimeFormat.basicDateTimeNoMillis()

    /**
     * Returns the next repetition of the given HL7 v2 field/segment/etc.
     */
    static def nextRepetition(Repeatable closure) {
        return closure(closure().size())
    }

    /**
     * Returns current timestamp in the format prescribed by HL7.
     */
    static String hl7Timestamp() {
        return TIME_FORMAT.print(new DateTime())[0..7, 9..14]
    }

    static boolean populateIdentifier(def cx, UriMapper uriMapper, String uri, String identifier = null) {
        cx[1] = identifier ?: ''
        cx[4][1] = uriMapper.uriToNamespace(uri)
        cx[4][2] = uriMapper.uriToOid(uri)
        cx[4][3] = 'ISO'
        return cx[4][1].value && cx[4][2].value
    }

    static BaseServerResponseException unknownPatientId() {
        OperationOutcome oo = new OperationOutcome()
        oo.addIssue()
                .setSeverity(OperationOutcome.IssueSeverity.ERROR)
                .setCode(OperationOutcome.IssueType.VALUE)
        return new InvalidRequestException('Unknown Patient ID', oo)
    }

    static BaseServerResponseException unknownPatientDomain() {
        OperationOutcome oo = new OperationOutcome()
        oo.addIssue()
                .setSeverity(OperationOutcome.IssueSeverity.ERROR)
                .setCode(OperationOutcome.IssueType.NOTSUPPORTED) // not-supported
        return new InvalidRequestException('Unknown Patient Domain', oo)
    }

    static BaseServerResponseException unknownTargetDomain() {
        OperationOutcome oo = new OperationOutcome()
        oo.addIssue()
                .setSeverity(OperationOutcome.IssueSeverity.ERROR)
                .setCode(OperationOutcome.IssueType.NOTFOUND) // unknown-key-identifier
        return new InvalidRequestException('Unknown Target Domain', oo)
    }

    static BaseServerResponseException unexpectedProblem() {
        OperationOutcome oo = new OperationOutcome()
        oo.addIssue()
                .setSeverity(OperationOutcome.IssueSeverity.ERROR)
                .setCode(OperationOutcome.IssueType.EXCEPTION)
        return new InternalErrorException('Unexpected response from server', oo)
    }

}
