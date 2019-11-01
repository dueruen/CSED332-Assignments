package edu.postech.csed332.homework2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A Boolean expression whose top-level operator is ! (not).
 */
public class Negation implements Exp {
    private final Exp subexp;

    /**
     * Builds a negated expression of a given Boolean expression.
     *
     * @param exp a Boolean expression
     */
    public Negation(Exp exp) {
        subexp = exp;
    }

    /**
     * Returns the immediate sub-expression of this expression.
     *
     * @return a sub-expression
     */
    public Exp getSubexp() {
        return subexp;
    }

    @Override
    public Set<Integer> vars() {
        return new HashSet<>(subexp.vars());
    }

    @Override
    public Boolean evaluate(Map<Integer, Boolean> assignment) {
        Exp e = subexp.simplify();
        return !e.evaluate(assignment);
    }

    @Override
    public Exp simplify() {
        Exp e = DeMorgan();
        if (e != null) {
            return e.simplify();
        }
        e = DoubleNegation();
        if (e != null) {
            return e.simplify();
        }
        return this;
    }

    private Exp DeMorgan() {
        if (subexp instanceof Conjunction) {
            return new Disjunction(
                    new Negation(((Conjunction) subexp).getSubexps().get(0)),
                    new Negation(((Conjunction) subexp).getSubexps().get(1)));
        }
        if (subexp instanceof  Disjunction) {
            return new Conjunction(
                    new Negation(((Disjunction) subexp).getSubexps().get(0)),
                    new Negation(((Disjunction) subexp).getSubexps().get(1)));
        }
        return null;
    }

    private Exp DoubleNegation() {
        if (subexp instanceof Negation) {
            return new Conjunction(
                    ((Negation) subexp).getSubexp(),
                    new Constant(true));
        }
        return null;
    }

    @Override
    public String toString() {
        return "(! " + subexp + ")";
    }
}
