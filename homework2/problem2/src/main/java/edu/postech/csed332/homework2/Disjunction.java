package edu.postech.csed332.homework2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Boolean expression whose top-level operator is || (or).
 */
public class Disjunction implements Exp {
    private final List<Exp> subexps;

    /**
     * Builds a disjunction of a given list of Boolean expressions
     *
     * @param exps
     */
    public Disjunction(Exp... exps) {
        subexps = Arrays.asList(exps);
    }

    /**
     * Returns the immediate sub-expressions of this expression.
     *
     * @return a list of sub-expressions
     */
    public List<Exp> getSubexps() {
        return subexps;
    }

    @Override
    public Set<Integer> vars() {
        Set<Integer> vars = new HashSet<>();
        for (Exp e : subexps) {
            Set<Integer> res = e.vars();
            if (res != null) {
                vars.addAll(res);
            }
        }
        return vars;
    }

    @Override
    public Boolean evaluate(Map<Integer, Boolean> assignment) {
        Exp exp = this.simplify();
        if (!(exp instanceof Disjunction)) {
            return exp.evaluate(assignment);
        }
        Disjunction mainExp = (Disjunction) exp;
        Exp firstExp = mainExp.subexps.get(0);
        Exp secondExp = mainExp.subexps.get(1);
        return (firstExp.evaluate(assignment) || secondExp.evaluate(assignment));
    }

    @Override
    public Exp simplify() {
        Exp e = identityOr();
        if (e != null) {
            return e.simplify();
        }
        e = dominationOr();
        if (e != null) {
            return e.simplify();
        }
        e = absorptionLawOr();
        if (e != null) {
            return e.simplify();
        }
        e = distributiveLawOr();
        if (e != null) {
            return e.simplify();
        }

        checkSubs();
        return this;
    }

    private void checkSubs() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            if (!(e instanceof Variable) && !(e instanceof Constant)) {
                subexps.set(i, e.simplify());
                subexps.set(i, subexps.get(i).simplify());
            }
        }
    }

    private Exp identityOr() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            if (e instanceof Constant && !((Constant)e).getValue()) {
                return subexps.get((i + 1) % 2);
            }
        }
        if (subexps.get(0).toString().equals(subexps.get(1).toString())) {
            return subexps.get(0);
        }
        return null;
    }

    private Exp dominationOr() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            if (e instanceof Constant && ((Constant)e).getValue()) {
                return new Constant(true);
            }
        }
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = new Negation(subexps.get(i));
            if (e.toString().equals(subexps.get((i + 1) % 2).toString())) {
                return new Constant(true);
            }
        }
        return null;
    }

    private Exp absorptionLawOr() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            Exp ee = subexps.get((i + 1) % 2);
            if (ee instanceof Conjunction) {
                if (e.toString().equals(((Conjunction)ee).getSubexps().get(0).toString())
                        || e.toString().equals(((Conjunction)ee).getSubexps().get(1).toString())) {
                    return e;
                }
            }
        }
        return null;
    }

    private Exp distributiveLawOr() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            Exp ee = subexps.get((i + 1) % 2);
            if (((e instanceof Variable) || (e instanceof Constant))) {
                if (!(ee instanceof Variable) && !(ee instanceof Constant)) {
                    if (ee instanceof Conjunction) {
                        return new Conjunction(
                            new Disjunction(
                                    e, ((Conjunction) ee).getSubexps().get(0)),
                                new Disjunction(
                                    e, ((Conjunction) ee).getSubexps().get(1))
                            );
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "(" + subexps.stream().map(i -> i.toString()).collect(Collectors.joining(" || ")) + ")";
    }
}