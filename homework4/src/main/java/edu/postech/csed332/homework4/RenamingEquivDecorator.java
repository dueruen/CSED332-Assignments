package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * This expression is equivalent to another expression that is syntactically identical up to renaming.
 * For example, "(x1 + x2) * x3 + 1.0 * x1" is equivalent to "(x3 + x1) * x2 + 1.0 * x3", but not
 * equivalent to "(x3 + x1) * x2 + 1.0 * x1".
 */
public class RenamingEquivDecorator extends ExpDecorator {

    private Exp other;

    public RenamingEquivDecorator(Exp e) {
        super(e);
    }

    @Override
    public boolean equiv(@NotNull Exp other) {
        this.other = other;
        return accept(new PrivateRenamingVisitor());
    }

    private class PrivateRenamingVisitor implements ExpVisitor<Boolean> {

        private boolean check(String s1, String s2) {
            String[] s1Parts = s1.split("\\s+");
            String[] s2Parts = s2.split("\\s+");
            Map<Integer, Integer> pointsAt = new HashMap<>();

            for (int i = 0; i < s1Parts.length - 1; i++) {
                String sOne = s1Parts[i];
                String sTwo = s2Parts[i];
                if (sOne.matches("x" + "\\d+")) {
                    int integerOne = Integer.parseInt(sOne.replaceAll("[^\\d+]", ""));
                    int integerTwo = Integer.parseInt(sTwo.replaceAll("[^\\d+]", ""));
                    if (!pointsAt.containsKey(integerOne)) {
                        pointsAt.put(integerOne, integerTwo);
                        continue;
                    }
                    if (pointsAt.get(integerOne) != integerTwo) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public Boolean visit(DivideExp exp) {
            return check(exp.toString(), other.toString());
        }

        @Override
        public Boolean visit(ExponentiationExp exp) {
            return check(exp.toString(), other.toString());
        }

        @Override
        public Boolean visit(MinusExp exp) {
            return check(exp.toString(), other.toString());
        }

        @Override
        public Boolean visit(MultiplyExp exp) {
            return check(exp.toString(), other.toString());
        }

        @Override
        public Boolean visit(NumberExp exp) {
            return false;
        }

        @Override
        public Boolean visit(PlusExp exp) {
            return check(exp.toString(), other.toString());
        }

        @Override
        public Boolean visit(VariableExp exp) {
            return check(exp.toString(), other.toString());
        }
    }
}
