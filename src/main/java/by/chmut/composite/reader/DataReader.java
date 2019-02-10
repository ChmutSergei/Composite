package by.chmut.composite.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataReader {

    private static final Logger logger = LogManager.getLogger();

    public String read(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Path realPath = Paths.get(this.getClass().getResource(path).toURI());
            if (realPath == null) {
                logger.fatal("File not found");
                throw new RuntimeException("Fatal Error \"File not found\"");
            }
            Files.lines(realPath, StandardCharsets.UTF_8).map(StringBuilder::new).forEach(stringBuilder::append);
        } catch (IOException|URISyntaxException e) {
            logger.fatal("File not found");
            throw new RuntimeException("Fatal Error \"File not found\"", e);
        }
        return stringBuilder.toString();
    }

}
