package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;

/**
 * A visitor to compute the string expression of a given expression
 */
public class ToStringVisitor implements ExpVisitor<String> {

    @Override
    public String visit(DivideExp exp) {
        return exp.getLeft().toString() + " / " + exp.getRight().toString();

    }

    @Override
    public String visit(ExponentiationExp exp) {
        return exp.getLeft().toString() + " ^ " + exp.getRight().toString();
    }

    @Override
    public String visit(MinusExp exp) {
        return exp.getLeft().toString() + " - " + exp.getRight().toString();
    }

    @Override
    public String visit(MultiplyExp exp) {
        return exp.getLeft().toString() + " * " + exp.getRight().toString();
    }

    @Override
    public String visit(NumberExp exp) {
        return Double.toString(exp.getValue());
    }

    @Override
    public String visit(PlusExp exp) {
        return exp.getLeft().toString() + " + " + exp.getRight().toString();
    }

    @Override
    public String visit(VariableExp exp) {
        return "x" + exp.getName();
    }
}
