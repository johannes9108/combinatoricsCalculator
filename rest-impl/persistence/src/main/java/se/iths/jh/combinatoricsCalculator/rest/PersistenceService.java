package se.iths.jh.combinatoricsCalculator.rest;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import se.iths.jh.combinatoricsCalculator.rest.entities.Record;
public interface PersistenceService {

    Record persist(long n, long k, Boolean repetition, BigInteger result);
    Record update(long id, long n, long k, Boolean repetition,BigInteger result);
    Record delete(long id);
    Record getBy(long id);
    List<Record> getAll(HashMap<String, String> searchParams);

}
