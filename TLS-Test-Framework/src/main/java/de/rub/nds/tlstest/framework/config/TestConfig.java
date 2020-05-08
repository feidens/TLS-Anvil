package de.rub.nds.tlstest.framework.config;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.config.TLSDelegateConfig;
import de.rub.nds.tlsattacker.core.config.delegate.GeneralDelegate;
import de.rub.nds.tlsscanner.report.SiteReport;
import de.rub.nds.tlstest.framework.config.delegates.TestClientDelegate;
import de.rub.nds.tlstest.framework.config.delegates.TestServerDelegate;
import de.rub.nds.tlstest.framework.constants.TestEndpointType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.IPAddress;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class TestConfig extends TLSDelegateConfig {
    private static final Logger LOGGER = LogManager.getLogger();
    private TestClientDelegate testClientDelegate = null;
    private TestServerDelegate testServerDelegate = null;

    private JCommander argParser = null;

    private TestEndpointType testEndpointMode = null;
    private SiteReport siteReport = null;
    private boolean parsedArgs = false;

    Config cachedConfig = null;


    @Parameter(names = "-tags", description = "Run only tests containing on of the specified tags", variableArity = true)
    private List<String> tags = new ArrayList<>();

    @Parameter(names = "-testPackage", description = "Run only tests included in the specified package")
    private String testPackage = null;

    @Parameter(names = "-ignoreCache", description = "Discovering supported TLS-Features takes time, thus they are cached. Using this flag, the cache is ignored.")
    private boolean ignoreCache = false;

    @Parameter(names = "-outputFile", description = "Filepath where the test results should be store, defaults to `pwd/results.xml`")
    private String outputFile = Paths.get(System.getProperty("user.dir"), "testResults.xml").toString();


    public TestConfig() {
        super(new GeneralDelegate());
        this.testServerDelegate = new TestServerDelegate();
        this.testClientDelegate = new TestClientDelegate();
    }


    /**
     * This function parses the COMMAND environment variable which can be used
     * as alternative to the default arguments passed to the program.
     * This is needed to be able to run TLS-Tests directly from the IDE via GUI.
     *
     * @return arguments parsed from the COMMAND environment variable
     */
    @Nonnull
    private String[] argsFromEnvironment() {
        String env = System.getenv("COMMAND");
        if (env == null) {
            throw new ParameterException("No args could be found");
        }

        return env.split("\\s");
    }


    public void parse(@Nullable String[] args) {
        if (argParser == null) {
            argParser = JCommander.newBuilder()
                    .addObject(this)
                    .addCommand("client", testClientDelegate)
                    .addCommand("server", testServerDelegate)
                    .build();
        }

        if (args == null) {
            args = argsFromEnvironment();
        }

        this.argParser.parse(args);
        if (argParser.getParsedCommand() == null) {
            throw new ParameterException("You have to use the client or server command");
        }

        this.setTestEndpointMode(argParser.getParsedCommand());

        if (getGeneralDelegate().isHelp()) {
            argParser.usage();
        }

        try {
            Path outputFile = Paths.get(this.outputFile);
            if (this.outputFile.endsWith("/") || this.outputFile.endsWith("\\")) {
                outputFile = Paths.get(this.outputFile, "testResults.xml");
            }

            outputFile = outputFile.toAbsolutePath();

            if (Files.isDirectory(outputFile)) {
                outputFile = Paths.get(outputFile.toString(), "testResults.xml");
            }
            this.outputFile = outputFile.toString();
        } catch (Exception e) {
            throw new ParameterException(e);
        }



        parsedArgs = true;
    }


    @Override
    synchronized public Config createConfig() {
        if (cachedConfig != null) {
            return cachedConfig.createCopy();
        }

        switch (this.testEndpointMode) {
            case CLIENT:
                addDelegate(this.testClientDelegate);
                break;
            case SERVER:
                addDelegate(this.testServerDelegate);
                break;
            default:
                throw new RuntimeException("Invalid testEndpointMode");
        }

        Config config = super.createConfig();


        if ((!IPAddress.isValid(config.getDefaultClientConnection().getHostname()) || this.getTestServerDelegate().getSniHostname() != null)
                && this.testEndpointMode == TestEndpointType.SERVER) {
            config.setAddServerNameIndicationExtension(true);
        } else {
            config.setAddServerNameIndicationExtension(false);
        }

        cachedConfig = config;
        return config;
    }


    public TestEndpointType getTestEndpointMode() {
        return testEndpointMode;
    }


    private void setTestEndpointMode(@Nonnull String testEndpointMode) {
        if (testEndpointMode.toLowerCase().equals(TestEndpointType.CLIENT.toString())) {
            this.testEndpointMode = TestEndpointType.CLIENT;
        } else if (testEndpointMode.toLowerCase().equals(TestEndpointType.SERVER.toString())) {
            this.testEndpointMode = TestEndpointType.SERVER;
        } else {
            throw new RuntimeException("Invalid testEndpointMode");
        }
    }

    public String getTestPackage() {
        return testPackage;
    }

    public List<String> getTags() {
        return tags;
    }

    public TestServerDelegate getTestServerDelegate() {
        return testServerDelegate;
    }

    public TestClientDelegate getTestClientDelegate() {
        return testClientDelegate;
    }

    public void setArgParser(JCommander argParser) {
        if (parsedArgs) {
            LOGGER.warn("Args are already parsed, setting the argParse requires calling parse() again.");
        }
        this.argParser = argParser;
    }

    public JCommander getArgParser() {
        return argParser;
    }

    public SiteReport getSiteReport() {
        return siteReport;
    }

    public void setSiteReport(SiteReport siteReport) {
        this.siteReport = siteReport;
    }

    public boolean isIgnoreCache() {
        return ignoreCache;
    }

    public void setIgnoreCache(boolean ignoreCache) {
        this.ignoreCache = ignoreCache;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
