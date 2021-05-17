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



}
