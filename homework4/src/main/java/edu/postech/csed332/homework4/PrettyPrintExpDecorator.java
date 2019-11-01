package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * The string representation is given without exponents of double values. For example,
 * 12345678, not 1.2345678E7. (Hint: use java.math.BigDecimal)
 */
public class PrettyPrintExpDecorator extends ExpDecorator {

    public PrettyPrintExpDecorator(Exp e) {
        super(e);
    }

    @NotNull
    @Override
    public String toString() {
        return accept(new PrivateToStringVisitor());
    }

    private class PrivateToStringVisitor extends ToStringVisitor {
        @Override
        public String visit(NumberExp exp) {
            BigDecimal bd = new BigDecimal(exp.getValue());
            return bd.toString();
        }
    }
}