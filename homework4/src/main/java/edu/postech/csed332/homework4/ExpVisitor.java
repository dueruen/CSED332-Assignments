package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;

/**
 * A visitor interface for expressions
 *
 * @param <T> type of return values
 */
public interface ExpVisitor<T> {
    T visit(DivideExp exp);
    T visit(ExponentiationExp exp);
    T visit(MinusExp exp);
    T visit(MultiplyExp exp);
    T visit(NumberExp exp);
    T visit(PlusExp exp);
    T visit(VariableExp exp);
}
