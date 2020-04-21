package de.rub.nds.tlstest.framework.annotations.endpoint;

import de.rub.nds.tlstest.framework.TestContext;
import de.rub.nds.tlstest.framework.annotations.ClientTest;
import de.rub.nds.tlstest.framework.annotations.ServerTest;
import de.rub.nds.tlstest.framework.annotations.TlsTest;
import de.rub.nds.tlstest.framework.utils.ConditionTest;
import de.rub.nds.tlstest.framework.junitExtensions.EndpointCondition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;


public class ClientAnnotationMethod {

    @RegisterExtension
    static ConditionTest ext = new ConditionTest(EndpointCondition.class);

    @BeforeAll
    static void setup() {
        TestContext testContext = new TestContext();
        testContext.getConfig().parse(new String[]{ "client", "-port", "443" });
    }

    @ClientTest
    public void execute_supported() { }

    @TlsTest
    public void execute_generic_supportsBothEndpointModes() { }

    @ServerTest
    public void not_execute_unsupportedForConfig() {}

}
