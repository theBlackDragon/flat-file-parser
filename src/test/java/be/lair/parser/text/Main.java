package be.lair.parser.text;

import be.lair.parser.text.document.AaRecord;
import be.lair.parser.text.document.XxRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

// TODO turn into real test
public class Main {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Main() {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testfile.txt")) {
            ParserFactory factory = new ParserFactory();
            factory.setRecordIdentifier("\\*");
            Parser<InputStream> parser = factory.instance(inputStream);
            parser.addSectionAddedListener((parent, record) -> {
                if(record != null) {
                    String identifier = record.getRecordIdentifier();
                    switch(identifier) {
                        case "BB":
                            logger.info("Record Event fired by: {}", identifier);
                            record.addSectionAddedListener((parent1, section1) -> logger.warn("Section added to B"));
                            break;
                        default:
                            logger.info("Default Record Event fired by: {} ", identifier);
                    }
                } else {
                    throw new RuntimeException("Something went very wrong");
                }
            });
            Record parsed = parser.parse((parent, fileContents, record) -> {
                switch(record.substring(0,2)) {
                    case "AA":
                        parent.addSection(new AaRecord(parent, fileContents, record));
                        break;
                    case "XX":
                        parent.addSection(new XxRecord(record));
                        break;
                    default:
                        // nothing to see here, moving along
                }
            });
            logger.info("Generated: \n" + parsed.generate());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
