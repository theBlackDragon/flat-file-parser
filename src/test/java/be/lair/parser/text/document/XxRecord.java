package be.lair.parser.text.document;

import be.lair.parser.text.Record;

public class XxRecord extends Record {

    private final String content;

    public XxRecord(String line) {
        content = line.substring(2);
    }

    @Override
    public String getRecordIdentifier() {
        return "XX";
    }

    @Override
    public String generateSelf() {
        return getRecordIdentifier() + content;
    }
}
