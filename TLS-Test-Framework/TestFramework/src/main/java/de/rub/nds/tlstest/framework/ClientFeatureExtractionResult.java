package de.rub.nds.tlstest.framework;

import de.rub.nds.tlsattacker.core.constants.ExtensionType;
import de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm;
import de.rub.nds.tlsscanner.clientscanner.report.ClientReport;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ClientFeatureExtractionResult extends FeatureExtractionResult {

    private int requiredCertificateDSSPublicKeySize;
    private int requiredCertificateRSAPublicKeySize;
    private List<SignatureAndHashAlgorithm> advertisedSignatureAndHashAlgorithms =
            new LinkedList<>();
    private List<ExtensionType> advertisedExtensions = new LinkedList<>();

    public ClientFeatureExtractionResult(String host) {
        super(host);
    }

    public static ClientFeatureExtractionResult fromClientScanReport(ClientReport report) {
        ClientFeatureExtractionResult extractionResult =
                new ClientFeatureExtractionResult("client");
        extractionResult.setSharedFieldsFromReport(report);
        extractionResult.setRequiredCertificateDSSPublicKeySize(
                report.getMinimumServerCertificateKeySizeDSS());
        extractionResult.setRequiredCertificateRSAPublicKeySize(
                report.getMinimumServerCertificateKeySizeRSA());
        extractionResult
                .getAdvertisedSignatureAndHashAlgorithms()
                .addAll(report.getClientAdvertisedSignatureAndHashAlgorithms());
        extractionResult.getAdvertisedExtensions().addAll(report.getClientAdvertisedExtensions());
        return extractionResult;
    }

    public int getRequiredCertificateDSSPublicKeySize() {
        return requiredCertificateDSSPublicKeySize;
    }

    public void setRequiredCertificateDSSPublicKeySize(int requiredCertificateDSSPublicKeySize) {
        this.requiredCertificateDSSPublicKeySize = requiredCertificateDSSPublicKeySize;
    }

    public int getRequiredCertificateRSAPublicKeySize() {
        return requiredCertificateRSAPublicKeySize;
    }

    public void setRequiredCertificateRSAPublicKeySize(int requiredCertificateRSAPublicKeySize) {
        this.requiredCertificateRSAPublicKeySize = requiredCertificateRSAPublicKeySize;
    }

    public List<SignatureAndHashAlgorithm> getAdvertisedSignatureAndHashAlgorithms() {
        return advertisedSignatureAndHashAlgorithms;
    }

    public void setAdvertisedSignatureAndHashAlgorithms(
            List<SignatureAndHashAlgorithm> advertisedSignatureAndHashAlgorithms) {
        this.advertisedSignatureAndHashAlgorithms = advertisedSignatureAndHashAlgorithms;
    }

    @Override
    public Set<SignatureAndHashAlgorithm> getSignatureAndHashAlgorithmsForDerivation() {
        return new HashSet<>(getAdvertisedSignatureAndHashAlgorithms());
    }

    public List<ExtensionType> getAdvertisedExtensions() {
        return advertisedExtensions;
    }

    public void setAdvertisedExtensions(List<ExtensionType> advertisedExtensions) {
        this.advertisedExtensions = advertisedExtensions;
    }
}
