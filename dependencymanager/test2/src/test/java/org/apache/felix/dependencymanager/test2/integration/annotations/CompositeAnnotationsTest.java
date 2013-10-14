/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.apache.felix.dependencymanager.test2.integration.annotations;

import org.apache.felix.dependencymanager.test2.components.Ensure;
import org.apache.felix.dependencymanager.test2.components.CompositeAnnotations.C1;
import org.apache.felix.dependencymanager.test2.components.CompositeAnnotations.Dependency1;
import org.apache.felix.dependencymanager.test2.components.CompositeAnnotations.Dependency2;
import org.apache.felix.dependencymanager.test2.integration.common.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;
import org.osgi.framework.ServiceRegistration;

/**
 * Use case: Verify Composite annotated services.
 */
@RunWith(PaxExam.class)
public class CompositeAnnotationsTest extends TestBase {
    @Test
    public void testComposite() {
        Ensure e = new Ensure();
        ServiceRegistration sr1 = register(e, C1.ENSURE);
        ServiceRegistration sr2 = register(e, Dependency1.ENSURE);
        ServiceRegistration sr3 = register(e, Dependency2.ENSURE);
        e.waitForStep(4, 10000);
        stopTestComponentsBundle();
        e.waitForStep(12, 10000);
        sr3.unregister();
        sr2.unregister();
        sr1.unregister();
    }
}
