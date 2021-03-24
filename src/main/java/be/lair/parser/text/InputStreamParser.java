package be.lair.parser.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class InputStreamParser extends Parser<InputStream> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final InputStream inputStream;

    protected InputStreamParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Record parse(ParseFunction parseFunction) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line.stripLeading()); // leading spaces (and newlines) only added in test file for clarity
            }
            Queue<String> fileContents = Arrays.stream(builder.toString().split(getRecordIdentifier()))
                .collect(Collectors.toCollection(LinkedList::new));

            String record;
            while((record = fileContents.poll()) != null) {
                if(!record.equals(""))
                    parseFunction.parse(this, fileContents, record);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return this;
    }

    public String generateSelf() {
        return "";
    }
}
