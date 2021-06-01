package se.iths.jh.combinatoricsCalculator.rest;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CalculatorService {

    @Inject
    @RestClient
    PersistenceService persistenceService;



    @PostConstruct
    void init(){
        LOGGER.warn("CalculatorService PS");
        LOGGER.warn(persistenceService);
    }

    private Logger LOGGER = Logger.getLogger(CalculatorService.class);

    public long calcuatePermutations(long elements,
                                     long choices,
                                     boolean repetition) {
        validate(elements, choices);
        long n = elements;
        long k = choices;
        long result = repetition ? permutationsWithRepetition(n, k) : permutationsWithoutRepetition(n, k);
        LOGGER.info("Calculator Service");
        try {
            Record record = persistenceService.persist(n,k,repetition,result);
                    LOGGER.warn("" + record );
        } catch (Exception e) {
            LOGGER.info(e.getLocalizedMessage());
        }
        return result;
    }

    private long permutationsWithRepetition(long n, long k) {
        return (long) Math.pow(n, k);
    }

    private long permutationsWithoutRepetition(long n, long k) {
        BigInteger division = n > 10 ? factorialItr(n).divide(factorialItr(n - k))
                : factorial(n).divide((factorial(n - k)));
        long result = division.longValue();
        return result;
    }

    private void validate(long elements,
                          long choices) {
                if(choices > elements || choices < 0) {
            throw new WebApplicationException("Request does not meet requirements: 'elements'>='choices', only 0 & positive integers");
        }
    }

    public long calcuateCombinations(long elements,
                                     long choices,
                                     boolean repetition) {
        validate(elements, choices);
        long n = elements;
        long k = choices;
        long result = repetition ? combinationsWithRepetition(n, k) : combinationsWithoutRepetition(n, k);
//        persistenceService.persist(n, k, repetition, result);
        return result;
    }

    private long combinationsWithRepetition(long n, long k) {
        BigInteger division = n > 10 ? factorialItr(n + k - 1).divide(factorialItr(n - 1).multiply(factorialItr(k))) :
                factorial(n + k - 1).divide(factorial(n - 1).multiply(factorial(k)));
        return division.longValue();
    }

    private long combinationsWithoutRepetition(long n, long k) {
        BigInteger division = n > 10 ? factorialItr(n).divide(factorialItr(n - k).multiply(factorialItr(k)))
                : factorial(n).divide(factorial(n - k).multiply(factorial(k)));
        long result = division.longValue();
        return result;
    }

    private BigInteger factorial(long number) {
        if (number == 0) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(number).multiply(factorial(number - 1));
        }
    }

    private BigInteger factorialItr(long number) {
        long res = (long) number;
        BigInteger bigInteger = BigInteger.valueOf(2);
        for (long i = 2; i <= number; i++) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
        }
        return bigInteger;
    }


    public List<Record> getAll(HashMap<String, String> searchParams) {
        return null;
    }
}
