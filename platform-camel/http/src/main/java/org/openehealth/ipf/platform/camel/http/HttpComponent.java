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
package org.openehealth.ipf.platform.camel.http;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.Producer;

/**
 * IPF version of the HTTP component
 * <p>
 * This component is used to enhance the existing HTTP component with support
 * for streaming download capabilities 
 * @author Jens Riemschneider
 */
public class HttpComponent extends org.apache.camel.component.http.HttpComponent {
    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map parameters) throws Exception {
        final org.apache.camel.component.http.HttpEndpoint originalEndpoint = 
            (org.apache.camel.component.http.HttpEndpoint) super.createEndpoint(uri, remaining, parameters);
        
        return new HttpEndpoint(originalEndpoint) {
            @Override
            public Producer createProducer() throws Exception {
                return new HttpProducer(originalEndpoint);
            }
        };
    }
}
