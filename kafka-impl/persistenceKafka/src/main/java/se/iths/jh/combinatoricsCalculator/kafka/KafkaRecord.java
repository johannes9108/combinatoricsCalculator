package se.iths.jh.combinatoricsCalculator.kafka;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class KafkaRecord extends PanacheEntity {

    protected LocalDateTime dateTime;
    protected long elements;
    protected long choices;
    protected boolean repetition;
    protected long result;
    private Type type;



    public KafkaRecord(LocalDateTime dateTime, long elements, long choices, boolean repetition, long result, Type type) {
        this.dateTime = dateTime;
        this.elements = elements;
        this.choices = choices;
        this.repetition = repetition;
        this.result = result;
        this.type = type;
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

    public enum Type{
        PERMUTATION,COMBINATION
    }

    public KafkaRecord() {
    }



    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format((getType().equals(Type.PERMUTATION)?"Permutations":"Combinations") + " when picking %s from %s " + (getRepetition() ? "with" : "without") + ": %s", choices, elements, result);
    }
}
