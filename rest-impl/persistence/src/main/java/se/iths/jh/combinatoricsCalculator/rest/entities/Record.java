package se.iths.jh.combinatoricsCalculator.rest.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Record extends PanacheEntity {

    private LocalDateTime dateTime;
    private long elements;
    private long choices;
    private Boolean repetition;

    public Record(LocalDateTime dateTime, long elements, long choices, Boolean repetition) {
        this.dateTime = dateTime;
        this.elements = elements;
        this.choices = choices;
        this.repetition = repetition;
    }

    public Record() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getElements() {
        return elements;
    }

    public void setElements(long elements) {
        this.elements = elements;
    }

    public long getChoices() {
        return choices;
    }

    public void setChoices(long choices) {
        this.choices = choices;
    }

    public Boolean getRepetition() {
        return repetition;
    }

    public void setRepetition(Boolean repetition) {
        this.repetition = repetition;
    }

    @Override
    public String toString() {
        return "Record{" +
                "dateTime=" + dateTime +
                ", elements=" + elements +
                ", choices=" + choices +
                ", repetition=" + repetition +
                '}';
    }
}
