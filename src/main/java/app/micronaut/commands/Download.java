package app.micronaut.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

@Command(name = "download", description = "downloads a file to local system", mixinStandardHelpOptions = true)
public class Download implements Runnable{

    @Option(names = "--uri", required = true, description = "file url")
    private URL url;

    @Option(names = "--file", description = "name of the output file", required = true)
    private Path file;
    @Override
    public void run() {
        try (var fileWriter = new FileOutputStream(file.toFile())) {
            System.out.println(System.currentTimeMillis());
            var connection = url.openConnection();
            connection.connect();

            int total = connection.getContentLength();
            int byteCount,current = 0;
            var buffer = new byte[1024];
            var stream = new BufferedInputStream(connection.getInputStream());
            System.out.println(System.currentTimeMillis());
            while (( byteCount = stream.read(buffer)) != -1 ) {
                current += byteCount;
                fileWriter.write(buffer);
                //System.out.println(current*100/total);
            }
            System.out.println(System.currentTimeMillis());
            fileWriter.flush();
            fileWriter.close();
            stream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
