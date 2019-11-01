package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExpTest {

    @Test
    void testParserOK() {
        Exp exp = ExpParser.parse("p1 || p2 && ! p3 || true");
        assertEquals(exp.toString(), "((p1 || (p2 && (! p3))) || true)");
    }

    @Test
    void testParserError() {
        assertThrows(IllegalStateException.class, () -> {
            Exp exp = ExpParser.parse("p1 || p2 && ! p0 || true");
        });
    }

    @Test
    void testFromExercise() {
        Exp exp = ExpParser.parse("(p1 && true) && (p2 && ! (! p1 && ! p2))");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyFromExerciseOne() {
        Exp exp = ExpParser.parse("p1 && (p2 && ! p1)");
        assertEquals(exp.simplify().toString(), "false");
    }

    @Test
    void testSimplifyFromExerciseTwo() {
        Exp exp = ExpParser.parse("(p1 && true) && (p2 && ! (! p1 && ! p2))");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testEvaluateFromExercise() {
        Exp exp = ExpParser.parse("(p1 || p2) && (p2 || ! p3)");
        assertEquals(exp.evaluate(Map.of(1, true, 2, false, 3, false)), true);
    }

    /*
     * TODO: add  test methods to achieve at least 80% branch coverage of the classes:
     *  Conjunction, Constant, Disjunction, Negation, Variable.
     * Each test method should have appropriate JUnit assertions to test a single behavior.
     * You should not add arbitrary code to test methods to just increase coverage.
     */
}
