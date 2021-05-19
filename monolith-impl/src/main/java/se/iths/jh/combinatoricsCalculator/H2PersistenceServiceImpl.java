package se.iths.jh.combinatoricsCalculator;

import se.iths.jh.combinatoricsCalculator.entities.Record;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class H2PersistenceServiceImpl implements PersistenceService{

    @Transactional
    @Override
    public Record persist(Integer n, Integer k, Boolean repetition) {
        Record record = new Record(LocalDateTime.now(),n,k,repetition);
        record.persist();
        return record;
    }

    @Transactional
    @Override
    public Record update(Integer id, Integer n, Integer k, Boolean repetition) {
        Record record = getBy(id);
        return updateRecord(n, k, repetition, record);
    }

    private Record updateRecord(Integer n, Integer k, Boolean repetition, Record record) {
        record.setElements(n);
        record.setChoices(k);
        record.setRepetition(repetition);
        record.setDateTime(LocalDateTime.now());
        record.persist();
        return record;
    }

    @Transactional
    @Override
    public Record delete(Integer id) {
        Record record = getBy(id);
        record.delete();
        return record;
    }

    @Override
    public Record getBy(Integer id) {
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
