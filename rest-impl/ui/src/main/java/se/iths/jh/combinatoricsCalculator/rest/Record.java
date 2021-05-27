package se.iths.jh.combinatoricsCalculator.rest;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Record {

    private LocalDateTime dateTime;
    private long elements;
    private long choices;
    private Boolean repetition;
    private BigInteger result;

    public Record(LocalDateTime dateTime, long elements, long choices, Boolean repetition,BigInteger result) {
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

    public Boolean getRepetition() {
        return repetition;
    }

    public void setRepetition(Boolean repetition) {
        this.repetition = repetition;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
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
