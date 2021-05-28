package app.micronaut.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Path;

@Command(name = "xml2json", description = "convert xml to json", mixinStandardHelpOptions = true )
public class Xml2Json implements Runnable{
    @Option(names = {"--xml"}, description = "path to xml file", required = true)
    private Path xml;
    @Option(names = {"--json"}, description = "path to json file", required = true)
    private Path json;
    private final XmlMapper xmlMapper = new XmlMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void run() {
        try {
            var node = xmlMapper.readTree(xml.toFile());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(json.toFile(),node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
