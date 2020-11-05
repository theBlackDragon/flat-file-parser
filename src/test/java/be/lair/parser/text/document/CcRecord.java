package be.lair.parser.text.document;

import be.lair.parser.text.Record;

public class CcRecord extends Record {

    private final String content;

    public CcRecord(String line) {
        content = line.substring(2);
    }

    @Override
    public String getRecordIdentifier() {
        return "CC";
    }

    @Override
    public String generateSelf() {
        return getRecordIdentifier() + content;
    }
}
