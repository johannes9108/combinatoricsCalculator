package se.iths.jh;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.iths.jh.combinatoricsCalculator.CombinatoricsResource;
import se.iths.jh.combinatoricsCalculator.CombinatoricsSerivce;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.net.URL;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(CombinatoricsResource.class)
@TestTransaction
public class CombinatoricsTests {


    @Inject
    CombinatoricsSerivce combinatoricsSerivce;
    Integer elements;
    Integer choices;
    Boolean repetition;

    @BeforeAll
    static void init(){

    }


    @Test
    public void testPermutationsEndpoint() {
        given()
                .when().get( "/permutations?elements=5&choices=3")
                .then()
                .statusCode(200)
                .body(is("There are 60 ways of picking 3 items from a set of 5 elements"));
    }

    @Test
    public void testCombinationsEndpoint() {
        given()
                .when().get("/combinations?elements=5&choices=3")
                .then()
                .statusCode(200)
                .body(is("There are unique 10 ways of picking 3 items from a set of 5 elements"));
    }

    @Test
    public void testPermutations(){
        Assertions.assertEquals(30240,combinatoricsSerivce.calcuatePermutations(Optional.of(10L), Optional.of(5L),repetition));
    }
    @Test
    public void testPermutationsInvalidInput(){

        Assertions.assertThrows(WebApplicationException.class, () -> {
            combinatoricsSerivce.calcuatePermutations(Optional.ofNullable(null), Optional.of(5L),repetition);
        });
        Assertions.assertThrows(WebApplicationException.class, () -> {
            combinatoricsSerivce.calcuatePermutations(null, Optional.of(5L),repetition);
        });
        Assertions.assertThrows(WebApplicationException.class, () -> {
            combinatoricsSerivce.calcuatePermutations(Optional.of(5L), Optional.of(10L),repetition);
        });
    }

    @Test
    public void testCombinatoricsCorrect(){

    }

}
