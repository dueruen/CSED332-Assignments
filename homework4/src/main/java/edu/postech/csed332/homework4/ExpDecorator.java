package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.Exp;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * A base decorator class
 */
public class ExpDecorator extends Exp {
    private final Exp expression;

    ExpDecorator(Exp e) {
        expression = e;
    }

    @Override
    public <T> T accept(ExpVisitor<T> visitor) {
        return expression.accept(visitor);
    }

    @NotNull
    @Override
    public Double eval(@NotNull Map<Integer, Double> valuation) {
        return expression.eval(valuation);
    }

    @Override
    public boolean equiv(@NotNull Exp other) {
        return expression.equiv(other);
    }

    @Override
    public String toString() {
        return expression.toString();
    }
    // TODO implement all the methods of ExpDecorator

}
