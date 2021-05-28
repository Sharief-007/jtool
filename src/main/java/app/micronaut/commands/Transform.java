package app.micronaut.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.nio.file.Path;

@Command(name = "transform-xml",
        description = "perform xml transform and display output",
        mixinStandardHelpOptions = true )
public class Transform implements Runnable{

    @Option(names = {"--xml"}, description = "path to the source xml file", required = true)
    private Path xml;
    @Option(names = {"--xsl"}, description = "Path to the xsl file", required = true)
    private Path xsl;
    @Option(names = {"--pretty"}, description = "Path to the xsl file", defaultValue = "false")
    private boolean pretty;

    private final TransformerFactory factory = TransformerFactory.newDefaultInstance();

    @Override
    public void run() {
        var mapping = new StreamSource(xsl.toFile());
        var source = new StreamSource(xml.toFile());
        var writer = new StreamResult(System.out);
        try {
            var transformer = factory.newTransformer(mapping);
            if (pretty){
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            }
            transformer.transform(source,writer);
        } catch (TransformerException e) {
            System.err.println(e.getMessage());
        }
    }
}
