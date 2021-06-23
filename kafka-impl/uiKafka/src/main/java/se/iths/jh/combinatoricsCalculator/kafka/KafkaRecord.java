package se.iths.jh.combinatoricsCalculator.kafka;

import java.time.LocalDateTime;

public class KafkaRecord extends Record{
    private Type type;

    public enum Type{
        PERMUTATION,COMBINATION
    }

    public KafkaRecord(LocalDateTime dateTime, long elements, long choices, boolean repetition, long result, Type type) {
        super(dateTime, elements, choices, repetition, result);
        this.type = type;
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
