package se.iths.jh.combinatoricsCalculator;

import se.iths.jh.combinatoricsCalculator.entities.Record;

import java.util.HashMap;
import java.util.List;

public interface PersistenceService {

    Record persist(Integer n, Integer k, Boolean repetition);
    Record update(Integer id, Integer n, Integer k, Boolean repetition);
    Record delete(Integer id);
    Record getBy(Integer id);
    List<Record> getAll(HashMap<String, String> searchParams);

}
