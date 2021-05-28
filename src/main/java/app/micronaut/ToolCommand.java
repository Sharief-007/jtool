package app.micronaut;

import app.micronaut.commands.Download;
import app.micronaut.commands.Json2Xml;
import app.micronaut.commands.Transform;
import app.micronaut.commands.Xml2Json;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "tool",
        description = "Simple application to do basic operations",
        mixinStandardHelpOptions = true,
        subcommands = { Transform.class, Download.class, Xml2Json.class, Json2Xml.class }
)
public class ToolCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) {
        PicocliRunner.run(ToolCommand.class, "json2xml", "--xml", "pom2.xml", "--json", "pom.json");
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
