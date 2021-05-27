package se.iths.jh.combinatoricsCalculator.rest;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CombinatoricsSerivce {


    public BigInteger calcuatePermutations(Optional<Long> elements,
                                        Optional<Long> choices,
                                        Boolean repetition) {
        validate(elements, choices);
        long n = elements.get();
        long k = choices.get();
        BigInteger res = factorialItr(n).divide(factorialItr(n-k));
//                n>10 ?  : factorial(n) / (factorial(n - k));
//        persistenceService.persist(n, k, repetition);
        return res;
    }

    private void validate(Optional<Long> elements,
                          Optional<Long> choices) {
        if ((elements == null || choices == null) || (elements.isEmpty() || choices.isEmpty())
                || choices.get() > elements.get() || choices.get() < 0) {
            throw new WebApplicationException("Request does not meet requirments: 'elements'>='choices', only 0 & positive integers");
        }
    }

    public Long calcuateCombinations(Optional<Long> elements,
                                        Optional<Long> choices,
                                        Boolean repetition) {
        validate(elements, choices);
        long n = elements.get();
        long k = choices.get();
        long res = 10L;
//                n>10 ?  factorialItr(n) / (factorialItr(n - k))/ factorialItr(choices.get()): factorial(n) / (factorial(n - k))/ factorial(choices.get());
        return res;
    }

    private Long factorial(long number) {
        if (number == 0) {
            return 1L;
        } else {
            return number * factorial(number - 1);
        }
    }
    private BigInteger factorialItr(long number) {
        long res = (long) number;
        BigInteger bigInteger = BigInteger.valueOf(2);
        for(long i = 2; i<number;i++){
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
        }
        return bigInteger;
    }


//    @GET
//    @Path("all")
//    public List<Record> getAll() {
//        return persistenceService.getAll(null);
//    }
}
