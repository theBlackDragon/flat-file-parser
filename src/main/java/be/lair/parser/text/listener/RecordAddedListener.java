package be.lair.parser.text.listener;

import be.lair.parser.text.Record;

public interface RecordAddedListener {
    /**
     * Event fired whenever a section is added to the PLD
     *
     * @param record the section that was added
     */
    void sectionAdded(Record parent, Record record);
}
