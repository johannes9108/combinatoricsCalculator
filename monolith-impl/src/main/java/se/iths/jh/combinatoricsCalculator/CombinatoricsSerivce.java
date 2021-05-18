package se.iths.jh.combinatoricsCalculator;


import se.iths.jh.combinatoricsCalculator.entities.Record;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CombinatoricsSerivce {

    @Inject
    PersistenceService persistenceService;

    public Integer calcuatePermutations(Optional<Integer> elements,
                                        Optional<Integer> choices,
                                        Boolean repetition) {
        validate(elements, choices);
        int n = elements.get();
        int k = choices.get();
        int res =  factorial(n) / (factorial(n - k));
        persistenceService.persist(n, k, repetition);
        return res;
    }

    private void validate(Optional<Integer> elements,
                          Optional<Integer> choices) {
        if ((elements.isEmpty() || choices.isEmpty()) || choices.get() > elements.get() || choices.get()< 0) {
            throw new WebApplicationException("Request does not meet requirments: 'elements'>='choices', only 0 & positive integers");
        }
    }

    public Integer calcuateCombinations(Optional<Integer> elements,
                                        Optional<Integer> choices,
                                        Boolean repetition) {
        validate(elements, choices);
        int n = elements.get();
        int k = choices.get();
        int res =  factorial(n) / (factorial(n - k))/ factorial(choices.get());
        return res;
    }

    private Integer factorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }


    public List<Record> getAll() {
        return persistenceService.getAll(null);
    }
}
