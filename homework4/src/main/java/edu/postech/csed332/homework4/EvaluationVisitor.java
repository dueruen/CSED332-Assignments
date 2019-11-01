package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Map;

/**
 * A visitor to evaluate a value of an expression, given a valuation for each variable
 */
public class EvaluationVisitor implements ExpVisitor<Double> {
    private final Map<Integer, Double> valuation;

    public EvaluationVisitor(Map<Integer, Double> valuation) {
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
        return valuation.get(exp.getName());
    }

    @NotNull
    static private String valueToString(Double value) {
        return "(" + BigDecimal.valueOf(value).toString() + ")";
    }

    static private String applyValuation(@NotNull Map<Integer, Double> valuation, String substExp) {
        for (Map.Entry<Integer, Double> e : valuation.entrySet())
            substExp = substExp.replaceAll("x" + e.getKey(), valueToString(e.getValue()));
        return substExp;
    }
}
