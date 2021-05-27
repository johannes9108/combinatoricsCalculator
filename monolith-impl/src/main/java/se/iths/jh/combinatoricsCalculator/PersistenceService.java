package se.iths.jh.combinatoricsCalculator;

import se.iths.jh.combinatoricsCalculator.entities.Record;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public interface PersistenceService {

    Record persist(long n, long k, boolean repetition, long result);
    Record update(long id, long n, long k, boolean repetition, long result);
    Record delete(long id);
    Record getBy(long id);
    List<Record> getAll(HashMap<String, String> searchParams);

}
