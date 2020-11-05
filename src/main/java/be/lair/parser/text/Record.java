package be.lair.parser.text;

import be.lair.parser.text.listener.RecordsAddedListener;
import be.lair.parser.text.listener.RecordAddedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * A Section is a collection of other sections and/or Segments without a SegmentIdentifier.
 * <p>
 * Since there is no SegmentIdentifier Sections are usually not
 * physically represented in the output themselves, except in such
 * a exceptional cases where there is no SegmentIdentifier (eg. the
 * HeaderSegment)
 */
public abstract class Record {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final List<Record> records = new ArrayList<>();

    private String recordIdentifier;

    // listeners
    private final List<RecordAddedListener> recordAddedListeners = new ArrayList<>();
    private final List<RecordsAddedListener> recordsAddedListeners = new ArrayList<>();

    public void addSection(Record record) {
        logger.trace("addSegment called: {}", record);
        recordAddedListeners.forEach(record::addSectionAddedListener);

        this.records.add(record);
        fireSectionAdded(record);
    }

    public void addSections(List<? extends Record> sectionList) {
        for(RecordsAddedListener listener : recordsAddedListeners) {
            for(Record record : records) {
                record.addSectionsAddedListener(listener);
            }
        }

        sectionList.forEach(this::addSection);
        fireSectionsAdded(sectionList);
    }

    protected int getRecordCount() {
        return records.size();
    }

    public String getRecordIdentifier() {
        return recordIdentifier;
    }

    protected void setRecordIdentifier(String identifier) {
        this.recordIdentifier = identifier;
    }

    public abstract String generateSelf();

    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(generateSelf());
        for (Record record : records) {
            sb.append(record.generate());
        }
        return sb.toString();
    }

    public void addSectionAddedListener(RecordAddedListener listener) {
        logger.debug("Adding listener to {}", this);
        recordAddedListeners.add(listener);
    }

    public void removeSectionAddedListener(RecordAddedListener listener) {
        recordAddedListeners.remove(listener);
    }

    protected List<RecordAddedListener> getSectionAddedListeners() {
        return recordAddedListeners;
    }

    public void addSectionsAddedListener(RecordsAddedListener listener) {
        recordsAddedListeners.add(listener);
    }

    public void removeSectionsAddedListener(RecordsAddedListener listener) {
        recordsAddedListeners.remove(listener);
    }

    protected List<RecordsAddedListener> getSectionsAddedListeners() {
        return recordsAddedListeners;
    }

    private void fireSectionAdded(Record record) {
        recordAddedListeners.forEach(recordAddedListener -> recordAddedListener.sectionAdded(this, record));
    }

    private void fireSectionsAdded(List<? extends Record> sections) {
        recordsAddedListeners.forEach(recordsAddedListener -> recordsAddedListener.sectionsAdded(this, sections));
    }
}
