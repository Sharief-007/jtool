package app.micronaut.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.nio.file.Path;

@Command(name = "json2xml", description = "convert json to xml", mixinStandardHelpOptions = true)
public class Json2Xml implements Runnable{
    @CommandLine.Option(names = {"--xml"}, description = "path to xml file", required = true)
    private Path xml;
    @CommandLine.Option(names = {"--json"}, description = "path to json file", required = true)
    private Path json;
    private final XmlMapper xmlMapper = new XmlMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void run() {
        try {
            JsonNode node = objectMapper.readValue(json.toFile(), JsonNode.class);
            xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(xml.toFile(),node);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
