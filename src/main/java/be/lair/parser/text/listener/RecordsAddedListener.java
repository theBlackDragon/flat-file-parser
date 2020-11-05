package be.lair.parser.text.listener;

import be.lair.parser.text.Record;

import java.util.List;

public interface RecordsAddedListener {
    /**
     * Event fired whenever a List of sections is added to the PLD
     *
     * @param sections the sections that were added
     */
    void sectionsAdded(Record parent, List<? extends Record> sections);
}
