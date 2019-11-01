package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariableTest {
    @Test
    void testGetVars() {
        Variable var = new Variable(2);
        assertEquals(var.vars(), new HashSet<>(Arrays.asList(2)));
    }

    @Test
    void testSimplify() {
        Variable var = new Variable(2);
        assertEquals(var.simplify().toString(), "p2");
    }

    @Test
    void testEvaluate() {
        Variable var = new Variable(2);
        assertEquals(var.evaluate(Map.of(2, true)), true);
    }

    @Test
    void testGetIdentifier() {
        Variable var = new Variable(2);
        assertEquals(var.getIdentifier(), 2);
    }
}
