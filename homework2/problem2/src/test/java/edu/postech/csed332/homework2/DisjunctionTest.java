package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisjunctionTest {
    @Test
    void testGetVars() {
        Exp exp = ExpParser.parse("p1 || p2 || ! p3 || true || ! p2");
        assertEquals(exp.vars(), new HashSet<>(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testSimplifyIdentityLawOrOne() {
        Exp exp = ExpParser.parse("(p1 || p2) || false");
        assertEquals(exp.simplify().toString(), "(p1 || p2)");
    }

    @Test
    void testSimplifyIdentityLawOrTwo() {
        Exp exp = ExpParser.parse("p1 || p1 || p2");
        assertEquals(exp.simplify().toString(), "(p1 || p2)");
    }

    @Test
    void testSimplifyIdentityLawOrTwoFormula() {
        Exp exp = ExpParser.parse("(p1 && p2) || (p1 && p2)");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyIdentityLawOrCombined() {
        Exp exp = ExpParser.parse("p1 || ((p2 || false) || p2)");
        String s = exp.simplify().toString();
        assertEquals(s, "(p1 || p2)");
    }

    @Test
    void testSimplifyDominationLawOrOne() {
        Exp exp = ExpParser.parse("(p1 && p2) || true");
        assertEquals(exp.simplify().toString(), "true");
    }

    @Test
    void testSimplifyDominationLawOrTwo() {
        Exp exp = ExpParser.parse("(p1 && p2) || (! (p1 && p2))");
        assertEquals(exp.simplify().toString(), "true");
    }

    @Test
    void testSimplifyAbsorptionLawOrOne() {
        Exp exp = ExpParser.parse("(p1 && p2) || ((p1 && p2) && true)");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyAbsorptionLawOrTwo() {
        Exp exp = ExpParser.parse("(p1 && p2) || (false && (p1 && p2))");
        assertEquals(exp.simplify().toString(), "(p1 && p2)");
    }

    @Test
    void testSimplifyDistributiveLawOrOne() {
        Exp exp = ExpParser.parse("p1 || (p2 && p3)");
        assertEquals(exp.simplify().toString(), "((p1 || p2) && (p1 || p3))");
    }

    @Test
    void testEvaluateOr() {
        Disjunction exp = new Disjunction(new Conjunction(new Variable(1), new Variable(2)), new Constant(true));
        assertEquals(exp.evaluate(Map.of(1, false, 2, true)), true);
    }
}
