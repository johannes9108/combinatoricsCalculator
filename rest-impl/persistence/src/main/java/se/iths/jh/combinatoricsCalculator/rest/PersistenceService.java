package se.iths.jh.combinatoricsCalculator.rest;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import se.iths.jh.combinatoricsCalculator.rest.entities.Record;
public interface PersistenceService {

    Record persist(Optional<Long> id, long n, long k, boolean repetition, long result);
    Record delete(long id);
    Record getBy(long id);
    List<Record> getAll(HashMap<String, String> searchParams);

}
