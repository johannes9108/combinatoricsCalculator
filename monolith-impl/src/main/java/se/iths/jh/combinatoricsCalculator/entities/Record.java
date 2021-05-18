package se.iths.jh.combinatoricsCalculator.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Record extends PanacheEntity {

    private LocalDateTime dateTime;
    private Integer elements;
    private Integer choices;
    private Boolean repition;

    public Record(LocalDateTime dateTime, Integer elements, Integer choices, Boolean repition) {
        this.dateTime = dateTime;
        this.elements = elements;
        this.choices = choices;
        this.repition = repition;
    }

    public Record() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getElements() {
        return elements;
    }

    public void setElements(Integer elements) {
        this.elements = elements;
    }

    public Integer getChoices() {
        return choices;
    }

    public void setChoices(Integer choices) {
        this.choices = choices;
    }

    public Boolean getRepition() {
        return repition;
    }

    public void setRepition(Boolean repition) {
        this.repition = repition;
    }

    @Override
    public String toString() {
        return "Record{" +
                "dateTime=" + dateTime +
                ", elements=" + elements +
                ", choices=" + choices +
                ", repition=" + repition +
                '}';
    }
}
