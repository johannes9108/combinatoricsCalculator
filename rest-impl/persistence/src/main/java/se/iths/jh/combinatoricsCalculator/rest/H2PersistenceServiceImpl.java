package se.iths.jh.combinatoricsCalculator.rest;

import se.iths.jh.combinatoricsCalculator.rest.entities.Record;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class H2PersistenceServiceImpl implements PersistenceService {

    @Transactional
    @Override
    public Record persist(long n, long k, boolean repetition, long result) {
        Record record = new Record(LocalDateTime.now(),n,k,repetition,result);
        record.persist();
        return record;
    }

    @Transactional
    @Override
    public Record update(long id, long n, long k, boolean repetition, long result) {
        Record record = getBy(id);
        return updateRecord(n, k, repetition, result,record);
    }

    private Record updateRecord(long n, long k, boolean repetition,long result, Record record) {
        record.setElements(n);
        record.setChoices(k);
        record.setRepetition(repetition);
        record.setDateTime(LocalDateTime.now());
        record.setResult(result);
        record.persist();
        return record;
    }

    @Transactional
    @Override
    public Record delete(long id) {
        Record record = getBy(id);
        record.delete();
        return record;
    }

    @Override
    public Record getBy(long id) {
        Optional<Record> res = Record.findByIdOptional(id);
        if(res.isEmpty()){
            throw new WebApplicationException("Not found",404);
        }
        return res.get();

    }

    @Override
    public List<Record> getAll(HashMap<String, String> searchParams) {
        return Record.listAll();
    }
}
