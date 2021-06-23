package se.iths.jh.combinatoricsCalculator.rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Record extends PanacheEntity {

    private LocalDateTime dateTime;
    private long elements;
    private long choices;
    private boolean repetition;
    private long result;


    public Record(LocalDateTime dateTime, long elements, long choices, boolean repetition, long result) {
        this.dateTime = dateTime;
        this.elements = elements;
        this.choices = choices;
        this.repetition = repetition;
        this.result = result;
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

    public boolean getRepetition() {
        return repetition;
    }

    public void setRepetition(boolean repetition) {
        this.repetition = repetition;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "Record{" +
                "dateTime=" + dateTime +
                ", elements=" + elements +
                ", choices=" + choices +
                ", repetition=" + repetition +
                ", result=" + result +
                '}';
    }
}
