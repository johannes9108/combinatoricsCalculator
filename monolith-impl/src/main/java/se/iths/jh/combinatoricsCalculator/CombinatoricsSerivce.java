package se.iths.jh.combinatoricsCalculator;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.Optional;

@ApplicationScoped
public class CombinatoricsSerivce {


    public Integer calcuatePermutations(Optional<Integer> elements,
                                        Optional<Integer> choices,
                                        Boolean repetition) {
        validate(elements, choices);
        int n = elements.get();
        int k = choices.get();
        return factorial(n) / (factorial(n - k));
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
        return calcuatePermutations(elements, choices, repetition) / factorial(choices.get());
    }

    private Integer factorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }


}
