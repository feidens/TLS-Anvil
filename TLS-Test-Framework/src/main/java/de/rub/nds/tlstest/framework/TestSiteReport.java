package de.rub.nds.tlstest.framework;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.protocol.message.ClientHelloMessage;
import de.rub.nds.tlsscanner.report.SiteReport;

import java.util.HashSet;
import java.util.Set;

public class TestSiteReport extends SiteReport {

    @JsonIgnore
    private ClientHelloMessage receivedClientHello;

    public TestSiteReport(String host) {
        super(host);
    }

    private TestSiteReport() {
        super();
    }

    public static TestSiteReport fromSiteReoport(SiteReport siteReport) {
        try {
            TestSiteReport report = new TestSiteReport();

            report.setCipherSuites(siteReport.getCipherSuites());
            report.setSupportedSignatureAndHashAlgorithms(siteReport.getSupportedSignatureAndHashAlgorithms());
            report.setVersions(siteReport.getVersions());
            report.setSupportedNamedGroups(siteReport.getSupportedNamedGroups());
            report.setVersionSuitePairs(siteReport.getVersionSuitePairs());
            report.setSupportedCompressionMethods(siteReport.getSupportedCompressionMethods());
            report.setSupportedTls13Groups(siteReport.getSupportedTls13Groups());

            return report;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ClientHelloMessage getReceivedClientHello() {
        return receivedClientHello;
    }

    public void setReceivedClientHello(ClientHelloMessage receivedClientHelloMessage) {
        this.receivedClientHello = receivedClientHelloMessage;
    }

    @Override
    public synchronized Set<CipherSuite> getCipherSuites() {
        if (super.getCipherSuites() == null) return new HashSet<>();
        Set<CipherSuite> set = new HashSet<>(super.getCipherSuites());
        set.removeIf(CipherSuite::isTLS13);
        return set;
    }

    public synchronized Set<CipherSuite> getSupportedTls13CipherSuites() {
        if (super.getCipherSuites() == null) return new HashSet<>();
        Set<CipherSuite> set = new HashSet<>(super.getCipherSuites());
        set.removeIf(i -> !i.isTLS13());
        return set;
    }
}
