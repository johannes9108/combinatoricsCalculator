package se.iths.jh.combinatoricsCalculator.kafka;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;

@ApplicationScoped
public class CalculatorService {




    @PostConstruct
    void init(){
        LOGGER.warn("CalculatorService PS");
    }

    private Logger LOGGER = Logger.getLogger(CalculatorService.class);

    public long calcuatePermutations(long elements,
                                     long choices,
                                     boolean repetition) {
        validate(elements, choices);
        long n = elements;
        long k = choices;
        return repetition ? permutationsWithRepetition(n, k) : permutationsWithoutRepetition(n, k);
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
        return repetition ? combinationsWithRepetition(n, k) : combinationsWithoutRepetition(n, k);
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
}
