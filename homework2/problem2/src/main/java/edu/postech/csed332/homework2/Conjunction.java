package edu.postech.csed332.homework2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Boolean expression whose top-level operator is && (and).
 */
public class Conjunction implements Exp {
    private final List<Exp> subexps;

    /**
     * Builds a conjunction of a given list of Boolean expressions
     *
     * @param exps
     */
    public Conjunction(Exp... exps) {
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
        if (!(exp instanceof Conjunction)) {
            return exp.evaluate(assignment);
        }
        Conjunction mainExp = (Conjunction) exp;
        Exp firstExp = mainExp.subexps.get(0);
        Exp secondExp = mainExp.subexps.get(1);
        return (firstExp.evaluate(assignment) && secondExp.evaluate(assignment));
    }

    @Override
    public Exp simplify() {
        Exp e = identityLawAnd();
        if (e != null) {
            return e.simplify();
        }
        e = dominationLawAdd();
        if (e != null) {
            return e.simplify();
        }
        e = absorptionLawAdd();
        if (e != null) {
            return e.simplify();
        }
        e = distributiveLawAnd();
        if (e != null) {
            return e.simplify();
        }
        checkSubs();
        return this;
    }

    private void checkSubs() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            if (!(e instanceof Variable) && !(e instanceof  Constant)) {
                subexps.set(i, e.simplify());
                subexps.set(i, subexps.get(i).simplify());
            }
        }
    }

    private Exp identityLawAnd() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            if (e instanceof Constant && ((Constant)e).getValue()) {
                return subexps.get((i + 1) % 2);
            }
        }
        if (subexps.get(0).toString().equals(subexps.get(1).toString())) {
            return subexps.get(0);
        }
        return null;
    }

    private Exp dominationLawAdd() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            if (e instanceof Constant && !((Constant)e).getValue()) {
                return new Constant(false);
            }
        }
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = new Negation(subexps.get(i));
            if (e.toString().equals(subexps.get((i + 1) % 2).toString())) {
                return new Constant(false);
            }
        }
        for (Exp e : subexps) {
            if (e instanceof Variable || e instanceof Constant) {
                for (int i = 0; i < subexps.size(); i++) {
                    Exp inner = subexps.get(i);
                    if (inner instanceof Conjunction) {
                        Conjunction c = (Conjunction)inner;
                        for (int cc = 0; cc < c.getSubexps().size(); cc++) {
                            Exp ee = c.getSubexps().get(cc);
                            if (new Negation(e).toString().equals(ee.toString())) {
                                return new Constant(false);
                            }
                            if (ee instanceof Conjunction) {
                                ee.simplify();
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private Exp absorptionLawAdd() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            Exp ee = subexps.get((i + 1) % 2);
            if (ee instanceof Disjunction) {
                if (e.toString().equals(((Disjunction)ee).getSubexps().get(0).toString())
                        || e.toString().equals(((Disjunction)ee).getSubexps().get(1).toString())) {
                    return e;
                }
            }
        }
        return null;
    }

    private Exp distributiveLawAnd() {
        for (int i = 0; i < subexps.size(); i++) {
            Exp e = subexps.get(i);
            Exp ee = subexps.get((i + 1) % 2);
            if (((e instanceof Variable) || (e instanceof Constant))) {
                if (!(ee instanceof Variable) && !(ee instanceof Constant)) {
                    if (ee instanceof Disjunction) {
                        return new Disjunction(
                                new Conjunction(
                                        e, ((Disjunction) ee).getSubexps().get(0)),
                                new Conjunction(
                                        e, ((Disjunction) ee).getSubexps().get(1))
                        );
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "(" + subexps.stream().map(i -> i.toString()).collect(Collectors.joining(" && ")) + ")";
    }
}