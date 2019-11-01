package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjunctionTest {
    @Test
    void testGetVars() {
        Exp exp = ExpParser.parse("p1 && p2 && ! p3 && true && ! p2");
        assertEquals(exp.vars(), new HashSet<>(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testSimplifyIdentityLawAndOne() {
        Exp exp = ExpParser.parse("p1 && p2 && true");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyIdentityLawAndTwo() {
        Exp exp = ExpParser.parse("p1 && p1 && p2");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyIdentityLawAndTwoFormula() {
        Exp exp = ExpParser.parse("(p1 && p2) && (p1 && p2)");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyIdentityLawAndCombined() {
        Exp exp = ExpParser.parse("p1 && ((p2 && true) && p2)");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyDominationLawAddOne() {
        Exp exp = ExpParser.parse("(p1 && p2) && false");
        assertEquals(exp.simplify().toString(), "false");
    }

    @Test
    void testSimplifyDominationLawAddTwo() {
        Exp exp = ExpParser.parse("(p1 && p2) && (! (p1 && p2))");
        assertEquals(exp.simplify().toString(), "false");
    }

    @Test
    void testSimplifyAbsorptionLawAddOne() {
        Exp exp = ExpParser.parse("(p1 && p2) && ((p1 && p2) || true)");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyAbsorptionLawAddTwo() {
        Exp exp = ExpParser.parse("(p1 && p2) && (false || (p1 && p2))");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyDistributiveLawAndOne() {
        Exp exp = ExpParser.parse("p1 && (p2 || p3)");
        assertEquals(exp.simplify().toString(), "((p1 && p2) || (p1 && p3))");
    }

    @Test
    void testEvaluateAdd() {
        Conjunction exp = new Conjunction(new Conjunction(new Variable(1), new Variable(2)), new Constant(true));
        assertEquals(exp.evaluate(Map.of(1, true, 2, true)), true);
    }
}
