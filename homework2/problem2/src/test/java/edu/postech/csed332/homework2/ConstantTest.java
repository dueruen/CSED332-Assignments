package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantTest {
    @Test
    void testGetVars() {
        Constant var = new Constant(true);
        assertEquals(var.vars(), new HashSet<>());
    }

    @Test
    void testSimplify() {
        Constant var = new Constant(true);
        assertEquals(var.simplify().toString(), "true");
    }

    @Test
    void testEvaluate() {
        Constant var = new Constant(true);
        assertEquals(var.evaluate(new HashMap<>()), true);
    }

    @Test
    void testGetIdentifier() {
        Constant var = new Constant(true);
        assertEquals(var.getValue(), true);
    }
}
