package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegationTest {
    @Test
    void testCreateNegation() {
        Exp exp = ExpParser.parse("! (p1 && p2)");
        assertEquals(exp.toString(), "(! (p1 && p2))");
    }

    @Test
    void testGetVars() {
        Exp exp = ExpParser.parse("! (p1 && p2) && p2 && p3");
        assertEquals(exp.vars(), new HashSet<>(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testGetSubexp() {
        Exp exp = ExpParser.parse("! (p1 && p2 && p3)");
        assertEquals(((Negation)exp).getSubexp().toString(), "((p1 && p2) && p3)");
    }

    @Test
    void testSimplifyDeMorganOne() {
        Exp exp = ExpParser.parse("! ((p1 && p2) && (p2 && p3))");
        assertEquals(exp.simplify().toString(), "(((! p1) || (! p2)) || ((! p2) || (! p3)))");
    }

    @Test
    void testSimplifyDeMorganTwo() {
        Exp exp = ExpParser.parse("! ((p1 && p2) || (p2 && p3))");
        assertEquals(exp.simplify().toString(), "(((! p1) || (! p2)) && ((! p2) || (! p3)))");
    }

    @Test
    void testSimplifyDoubleNegationOne() {
        Exp exp = ExpParser.parse("! (! (p1 && p2))");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testEvaluateNegation() {
        Negation exp = new Negation(new Conjunction(new Variable(1), new Variable(2)));
        assertEquals(exp.evaluate(Map.of(1, true, 2, true)), false);
    }
}
