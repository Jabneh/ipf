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

package org.openehealth.ipf.commons.ihe.fhir;

/**
 * @since 3.1
 */
public interface Constants {

    // PIXm stuff
    String PIXM_OPERATION_NAME = "$ihe-pix";
    String SOURCE_IDENTIFIER_NAME = "sourceIdentifier";
    String TARGET_SYSTEM_NAME = "targetSystem";

    // PDQm stuff
    String SP_RESOURCE_IDENTIFIER = "_id";
    String SP_MULTIPLE_BIRTH_ORDER_NUMBER = "multipleBirthInteger";
    String SP_MOTHERS_MAIDEN_NAME_GIVEN = "mothersMaidenName.given";
    String SP_MOTHERS_MAIDEN_NAME_FAMILY = "mothersMaidenName.family";

    // Request parameter information
    String FHIR_REQUEST_PARAMETERS = "FhirRequestParameters";

    // Parameter information from the HttpServletRequest
    String HTTP_URI = "FhirHttpUri";
    String HTTP_URL = "FhirHttpUrl";
    String HTTP_METHOD = "FhirHttpMethod";
    String HTTP_QUERY = "FhirHttpQuery";
    String HTTP_CHARACTER_ENCODING = "FhirHttpCharacterEncoding";
    String HTTP_CONTENT_TYPE = "FhirHttpContentType";
    String HTTP_PROTOCOL_VERSION = "FhirHttpProtocolVersion";
    String HTTP_CLIENT_IP_ADDRESS = "FhirHttpClientIpAddress";



}
