package be.lair.parser.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public abstract class RecordParser extends Record {

    public abstract String[] getChildIdentifiers();

    protected final String rawContent;

    public RecordParser(Record parent, Queue<String> fileContents, String line) {
        parent.getSectionAddedListeners().forEach(this::addSectionAddedListener);

        setRecordIdentifier(line.substring(0,2));
        rawContent = line.substring(2);

        String currentSegmentIdentifier = null;
        List<Record> recordList = new ArrayList<>();
        while (fileContents.peek() != null
                && Arrays.asList(getChildIdentifiers()).contains(fileContents.peek().substring(0, 2))) {
            String record = fileContents.remove();
            String identifier = record.substring(0, 2);

            Record newSegment = parse(identifier, record, fileContents);

            if (currentSegmentIdentifier == null) {
                currentSegmentIdentifier = identifier;
                recordList.add(newSegment);
            } else if (identifier.equals(currentSegmentIdentifier)) {
                recordList.add(newSegment);
            } else {
                currentSegmentIdentifier = identifier;
                if (recordList.size() == 1) {
                    addSection(recordList.get(0));
                } else {
                    addSections(recordList);
                }
                recordList.clear();
            }
        }
        if (recordList.size() == 1) {
            addSection(recordList.get(0));
        } else {
            addSections(recordList);
        }
    }

    public String generateSelf() {
        return getRecordIdentifier() + rawContent;
    }

    public abstract Record parse(String identifier, String line, Queue<String> fileContents);
}
