package edu.postech.csed332.homework4;


import com.fathzer.soft.javaluator.DoubleEvaluator;
import edu.postech.csed332.homework4.expression.Exp;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ExpTest {

    @Test
    void testToString() {
        Exp exp = Exp.parseExp("1.0 + 2.0 * x1");
        String expStr = exp.toString();
        assertEquals(expStr, Exp.parseExp(expStr).toString());
    }

    @Test
    void testToStringTwo() {
        Exp exp = Exp.parseExp("1.0 + 2.0 * x1 + 3.0");
        String expStr = exp.toString();
        assertEquals(expStr, Exp.parseExp(expStr).toString());
    }

    @Test
    void testEvaluation() {
        Exp exp = Exp.parseExp("x1 ^ x2 + 2.0 * x1");
        assertEquals(87.0, exp.eval(getValuation(Arrays.asList(3.0, 4.0))));
    }

    @Test
    void testToStringAndEvaluation() {
        Exp exp = Exp.parseExp("x1 ^ x2 + 2.0 * x1");
        assertEquals(87.0, Exp.parseExp(exp.toString()).eval(getValuation(Arrays.asList(3.0, 4.0))));
    }

    @Test
    void testEquivalence() {
        Exp e1 = Exp.parseExp("1.0 + 2.0 * x1 + x1");
        Exp e2 = Exp.parseExp("1.0 + 2.0 * x1 + x1");
        assertTrue(e1.equiv(e2));
    }

    @Test
    void testEquivalenceWrong() {
        Exp e1 = Exp.parseExp("1.0 + 2.0 * x1 + x1");
        Exp e2 = Exp.parseExp("1.0 + 2.0 * x2 + x3");
        assertFalse(e1.equiv(e2));
    }

    @Test
    void testEquivalenceTwo() {
        String sub = "7.9393008960108205 - -37.86902435056192";
        Map<Integer, Double> valuation = new HashMap<>() {{
           put(1, 0.2367652173447572);
           put(2, 0.8369246461485806);
           put(3, 0.15346799669336086);
        }};
        Exp e1 = Exp.parseExp(sub);
        assertEquals((new DoubleEvaluator()).evaluate(sub), e1.eval(valuation));
    }

    @Test
    void testEquivalenceThee() {
        String sub = "-16.648568770977093 * x1 ^ 5.382100061412337";
        String subs = "-16.648568770977093 * 0.27495001404273467 ^ 5.382100061412337";
        Map<Integer, Double> valuation = new HashMap<>() {{
            put(1, 0.27495001404273467);
            put(2, 0.5220177796735561);
            put(3, 0.15346799669336086);
        }};
        Exp e1 = Exp.parseExp(sub);
        double d = e1.eval(valuation);
        assertEquals((new DoubleEvaluator()).evaluate(subs), d);
    }

    @Test
    void testEquivalenceFour() {
        String sub = "x3 + -99.59955015400843 * x4 + 46.20105924453452 - 13.463870786438264 * 80.40755027700243 - -78.13330275686923 + 58.261934221871144 / x6 - x2 - 41.81449372714505 + x4 - -4.350953136070501 / x1 / x4 / -61.40141776435038";
        String subs = "0.16876853614851983 + -99.59955015400843 * 0.46415514900453003 + 46.20105924453452 - 13.463870786438264 * 80.40755027700243 - -78.13330275686923 + 58.261934221871144 / 0.6409667207149217 - 0.7365170741653407 - 41.81449372714505 + 0.46415514900453003 - -4.350953136070501 / 0.2917681197629258 / 0.46415514900453003 / -61.40141776435038";
        Map<Integer, Double> valuation = new HashMap<>() {{
            put(1, 0.2917681197629258);
            put(2, 0.7365170741653407);
            put(3, 0.16876853614851983);
            put(4, 0.46415514900453003);
            put(6, 0.6409667207149217);
        }};
        Exp e1 = Exp.parseExp(sub);
        double d = e1.eval(valuation);
        assertEquals((new DoubleEvaluator()).evaluate(subs), d);
    }

    @Test
    void testEquivalenceFive() {
        String sub = "x1 / x1 ^ x1 ^ x1 ^ 2.0696531980254256";
        String subs = "0.19419371747215664 / 0.19419371747215664 ^ 0.19419371747215664 ^ 0.19419371747215664 ^ 2.0696531980254256";
        Map<Integer, Double> valuation = new HashMap<>() {{
            put(1, 0.19419371747215664);
        }};
        Exp e1 = Exp.parseExp(sub);
        double d = e1.eval(valuation);
        assertEquals((new DoubleEvaluator()).evaluate(subs), d);
    }

    @Test
    void testEquivalenceSix() {
        String sub = "8.940097298991148 / -53.528039902452186 + x1 * 56.7105781302287";
        String subs = "8.940097298991148 / -53.528039902452186 + 0.6231136820728246 * 56.7105781302287";
        Map<Integer, Double> valuation = new HashMap<>() {{
            put(1, 0.6231136820728246);
        }};
        Exp e1 = Exp.parseExp(sub);
        double d = e1.eval(valuation);
        double ex = (new DoubleEvaluator()).evaluate(subs);
        //assertEquals(ex, d);
        assertEquals(e1.eval(valuation), (new DoubleEvaluator()).evaluate(subs));
    }

    @Test
    void testEquivalenceSeven() {
        String sub = "57.420674430747226 - 28.60949105842252 ^ 5.317803277872679";
        String subs = "57.420674430747226 - 28.60949105842252 ^ 5.317803277872679";
        Map<Integer, Double> valuation = new HashMap<>() {{
            put(1, 0.6231136820728246);
        }};
        Exp e1 = Exp.parseExp(sub);
        double d = e1.eval(valuation);
        double ex = (new DoubleEvaluator()).evaluate(subs);
        //assertEquals(ex, d);
        String substExp = applyValuation(valuation, e1.toString());
        double exx = (new DoubleEvaluator()).evaluate(substExp);

        assertEquals(e1.eval(valuation), (new DoubleEvaluator()).evaluate(substExp));
    }

    @Test
    void testEquivalenceEight() {
        String sub = "-20.533073860879767 / x1 ^ 9.111821698900767";
        Map<Integer, Double> valuation = new HashMap<>() {{
            put(1, 0.3980982823633338);
        }};
        Exp e1 = Exp.parseExp(sub);
        double d = e1.eval(valuation);
        String substExp = applyValuation(valuation, e1.toString());
        double exx = (new DoubleEvaluator()).evaluate(substExp);

        assertEquals(e1.eval(valuation), (new DoubleEvaluator()).evaluate(substExp));
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

    @Test
    void testPrettyPrintDecoration() {
        Exp exp = Exp.parseExp("1234567890123456");
        Exp pExp = new PrettyPrintExpDecorator(exp);
        assertEquals(exp.toString(), "1.234567890123456E15");
        assertEquals(pExp.toString(), "1234567890123456");
    }

    @Test
    void testDefaultValueDecoration() {
        Exp exp = Exp.parseExp("2.0 * x1 + x2");
        Exp dExp = new DefaultValueExpDecorator(exp, 2.0);
        assertEquals(8.0, dExp.eval(getValuation(Collections.singletonList(3.0))));
    }

    @Test
    void testRenamingEquivDecoration() {
        Exp e1 = Exp.parseExp("(x1 + x2) * x3 + 1.0 * x1");
        Exp e2 = Exp.parseExp("(x3 + x1) * x2 + 1.0 * x3");
        Exp de1 = new RenamingEquivDecorator(e1);
        assertTrue(de1.equiv(e2));
    }

    @Test
    void testPrettyPrintAndDefaultValueDecoration() {
        Exp exp = Exp.parseExp("2.0 * x1 + x2");
        Exp dpExp = new DefaultValueExpDecorator(new PrettyPrintExpDecorator(exp), 2.0);
        Exp pdExp = new PrettyPrintExpDecorator(new DefaultValueExpDecorator(exp, 2.0));
        assertEquals(8.0, dpExp.eval(getValuation(Collections.singletonList(3.0))));
        assertEquals(8.0, pdExp.eval(getValuation(Collections.singletonList(3.0))));
    }


    @Test
    void testDefaultValueAndRenamingEquivDecoration() {
        Exp e1 = Exp.parseExp("2.0 * x1 + x2");
        Exp e2 = Exp.parseExp("2.0 * x2 + x1");
        Exp drExp = new DefaultValueExpDecorator(new RenamingEquivDecorator(e1), 2.0);
        Exp rdExp = new RenamingEquivDecorator(new DefaultValueExpDecorator(e1, 2.0));
        assertTrue(drExp.equiv(e2));
        assertTrue(rdExp.equiv(e2));
    }

    /**
     * Create a valuation map from the given list
     *
     * @param values a list
     * @return a valuation map
     */
    @NotNull
    static Map<Integer, Double> getValuation(@NotNull List<Double> values) {
        return Collections.unmodifiableMap(
                IntStream.range(0, values.size()).mapToObj(i -> new AbstractMap.SimpleEntry<>(i + 1, values.get(i)))
                        .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }
}
