package be.lair.parser.text.document;

import be.lair.parser.text.Record;
import be.lair.parser.text.RecordParser;

import java.util.Queue;

public class AaRecord extends RecordParser {

    public AaRecord(Record parent, Queue<String> fileContents, String line) {
        super(parent, fileContents, line);
    }

    @Override
    public String[] getChildIdentifiers() {
        return new String[]{ "BB" };
    }

    @Override
    public Record parse(String identifier, String line, Queue<String> fileContents) {
        switch (identifier) {
            case "BB":
                return new BbRecord(this, fileContents, line);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "AaRecord";
    }
}
