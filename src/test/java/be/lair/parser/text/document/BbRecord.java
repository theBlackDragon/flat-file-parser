package be.lair.parser.text.document;

import be.lair.parser.text.Record;
import be.lair.parser.text.RecordParser;

import java.util.Queue;

public class BbRecord extends RecordParser {

    @Override
    public String[] getChildIdentifiers() {
        return new String[]{ "CC" };
    }

    public BbRecord(Record parent, Queue<String> fileContents, String line) {
        super(parent, fileContents, line);
    }

    @Override
    public Record parse(String identifier, String line, Queue<String> fileContents) {
        switch(identifier) {
            case "CC":
                return new CcRecord(line);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "BbRecord";
    }
}
