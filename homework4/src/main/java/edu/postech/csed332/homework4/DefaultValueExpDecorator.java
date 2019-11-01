package edu.postech.csed332.homework4;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Map;

/**
 * When evaluating the expression, use the default value for variables not present in the valuation.
 */
public class DefaultValueExpDecorator extends ExpDecorator {
    @NotNull
    private final Double defaultValue;

    public DefaultValueExpDecorator(@NotNull Exp e, @NotNull Double defaultValue) {
        super(e);
        this.defaultValue = defaultValue;
    }

    @Override
    @NotNull
    public Double eval(@NotNull Map<Integer, Double> valuation) {
        return accept(new PrivateEvaluationVisitor(valuation));
    }

    private class PrivateEvaluationVisitor implements ExpVisitor<Double> {
        private final Map<Integer, Double> valuation;

        public PrivateEvaluationVisitor(Map<Integer, Double> valuation) {
            this.valuation = valuation;
        }

        @Override
        public Double visit(DivideExp exp) {
            String substExp = applyValuation(valuation, exp.toString());
            return new DoubleEvaluator().evaluate(substExp);
        }

        @Override
        public Double visit(ExponentiationExp exp) {
            String substExp = applyValuation(valuation, exp.toString());
            return new DoubleEvaluator().evaluate(substExp);
        }

        @Override
        public Double visit(MinusExp exp) {
            String substExp = applyValuation(valuation, exp.toString());
            return new DoubleEvaluator().evaluate(substExp);
        }

        @Override
        public Double visit(MultiplyExp exp) {
            String substExp = applyValuation(valuation, exp.toString());
            return new DoubleEvaluator().evaluate(substExp);
        }

        @Override
        public Double visit(NumberExp exp) {
            return exp.getValue();
        }

        @Override
        public Double visit(PlusExp exp) {
            String substExp = applyValuation(valuation, exp.toString());
            return new DoubleEvaluator().evaluate(substExp);
        }

        @Override
        public Double visit(VariableExp exp) {
            if (valuation.containsKey(exp.getName())) {
                return valuation.get(exp.getName());
            }
            return defaultValue;
        }

        @NotNull
        private String valueToString(Double value) {
            return "(" + BigDecimal.valueOf(value).toString() + ")";
        }

        private String applyValuation(@NotNull Map<Integer, Double> valuation, String substExp) {
            for (Map.Entry<Integer, Double> e : valuation.entrySet())
                substExp = substExp.replaceAll("x" + e.getKey(), valueToString(e.getValue()));
            substExp = substExp.replaceAll("x" + "\\d+", valueToString(defaultValue));
            return substExp;
        }
    }
}
