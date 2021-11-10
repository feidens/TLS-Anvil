/**
 * TLS-Testsuite - A testsuite for the TLS protocol
 *
 * Copyright 2020 Ruhr University Bochum and
 * TÜV Informationstechnik GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlstest.suite.tests.client.tls12;

import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.protocol.message.ClientHelloMessage;
import de.rub.nds.tlstest.framework.annotations.ClientTest;
import de.rub.nds.tlstest.framework.annotations.TestDescription;
import de.rub.nds.tlstest.framework.annotations.categories.ComplianceCategory;
import de.rub.nds.tlstest.framework.annotations.categories.HandshakeCategory;
import de.rub.nds.tlstest.framework.annotations.categories.InteroperabilityCategory;
import de.rub.nds.tlstest.framework.annotations.categories.SecurityCategory;
import de.rub.nds.tlstest.framework.constants.SeverityLevel;
import de.rub.nds.tlstest.framework.testClasses.Tls12Test;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

@ClientTest
public class SupportedCiphersuites extends Tls12Test {

    @Test
    @TestDescription("Evaluate if the client accepts more cipher suites than advertised")
    @Tag("ciphersuites")
    @SecurityCategory(SeverityLevel.HIGH)
    @HandshakeCategory(SeverityLevel.MEDIUM)
    @ComplianceCategory(SeverityLevel.HIGH)
    public void supportsMoreCiphersuitesThanAdvertised() {
        ClientHelloMessage clientHello = context.getReceivedClientHelloMessage();

        List<CipherSuite> advertised = CipherSuite.getCipherSuites(clientHello.getCipherSuites().getValue());
        List<CipherSuite> supported = new ArrayList<>(context.getSiteReport().getCipherSuites());
        supported.addAll(context.getSiteReport().getSupportedTls13CipherSuites());

        advertised.forEach(supported::remove);

        assertEquals("Client supports more cipher suites than advertised. " +
                        supported.parallelStream().map(Enum::name).collect(Collectors.joining(",")),
                0,
                supported.size());
    }


    @Test
    @TestDescription("Client exploration detected less supported ciphersuites than " +
            "advertised by the client in the ClientHello message.")
    @Tag("ciphersuites")
    @InteroperabilityCategory(SeverityLevel.CRITICAL)
    @HandshakeCategory(SeverityLevel.MEDIUM)
    @ComplianceCategory(SeverityLevel.CRITICAL)
    public void supportsLessCiphersuitesThanAdvertised() {
        ClientHelloMessage clientHello = context.getReceivedClientHelloMessage();

        List<CipherSuite> advertised = CipherSuite.getCipherSuites(clientHello.getCipherSuites().getValue());
        advertised.remove(CipherSuite.TLS_FALLBACK_SCSV);
        advertised.remove(CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV);
        
        List<CipherSuite> supported = new ArrayList<>(context.getSiteReport().getCipherSuites());
        supported.addAll(context.getSiteReport().getSupportedTls13CipherSuites());

        supported.forEach(advertised::remove);
        advertised = advertised.stream().filter(cipherSuite -> CipherSuite.getImplemented().contains(cipherSuite) && !cipherSuite.isGOST()).collect(Collectors.toList());

        assertEquals("Client supports less ciphersuites than advertised. " +
                        advertised.parallelStream().map(Enum::name).collect(Collectors.joining(",")),
                0,
                advertised.size());
    }
}
