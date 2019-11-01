package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

/**
 * A visitor to check whether a given expression is syntactically equivalent to another expression.
 */
public class EquivalenceVisitor implements ExpVisitor<Boolean> {

    Exp other;

    public EquivalenceVisitor(@NotNull Exp other) {
        this.other = other;
    }

    @Override
    public Boolean visit(DivideExp exp) {
        return exp.toString().equals(other.toString());
    }

    @Override
    public Boolean visit(ExponentiationExp exp) {
        return exp.toString().equals(other.toString());
    }

    @Override
    public Boolean visit(MinusExp exp) {
        return exp.toString().equals(other.toString());
    }

    @Override
    public Boolean visit(MultiplyExp exp) {
        return exp.toString().equals(other.toString());
    }

    @Override
    public Boolean visit(NumberExp exp) {
        return exp.toString().equals(other.toString());
    }

    @Override
    public Boolean visit(PlusExp exp) {
        return exp.toString().equals(other.toString());
    }

    @Override
    public Boolean visit(VariableExp exp) {
        return exp.toString().equals(other.toString());
    }

    // TODO write and implement the visitor methods for EquivalenceVisitor, satisfying
    //  the specification of Exp.equiv. You may need to add more member variables.
}
