/*
 * Copyright 2023 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ibm.watson.modelmesh.payload;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositePayloadProcessorTest {

    @Test
    void testPayloadProcessing() {
        List<PayloadProcessor> delegates = new ArrayList<>();
        delegates.add(new DummyPayloadProcessor());
        delegates.add(new DummyPayloadProcessor());

        CompositePayloadProcessor payloadProcessor = new CompositePayloadProcessor(delegates);
        for (int i = 0; i < 10; i++) {
            payloadProcessor.process(new Payload("123", "456", null, null, null, null));
        }
        for (PayloadProcessor p : delegates) {
            DummyPayloadProcessor dummyPayloadProcessor = (DummyPayloadProcessor) p;
            assertEquals(10, dummyPayloadProcessor.getProcessCount().get());
        }
    }
}