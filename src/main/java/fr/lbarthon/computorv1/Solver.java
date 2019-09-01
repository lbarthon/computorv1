package fr.lbarthon.computorv1;

import javax.naming.SizeLimitExceededException;
import java.util.List;

public class Solver {
    private Solver() {}

    public static void solve(Equation equation) throws SizeLimitExceededException {
        if (!equation.isReduced()) {
            equation.reduce();
        }

        if (equation.getDegree() > 2) {
            throw new SizeLimitExceededException();
        }

        List<Equation.Entry> entries = equation.getLeftPart().getEntries();

        double a = entries.stream().filter(e -> e.getPower() == 2)
                .map(Equation.Entry::getNbr).findFirst()
                .orElse(0D);
        double b = entries.stream().filter(e -> e.getPower() == 1)
                .map(Equation.Entry::getNbr).findFirst()
                .orElse(0D);
        double c = entries.stream().filter(e -> e.getPower() == 0)
                .map(Equation.Entry::getNbr).findFirst()
                .orElse(0D);


        double delta = b * b - 4 * a * c;
        double deltaSqrt = Utils.sqrt(delta);

        if (delta > 0) {
            if (deltaSqrt - b == 0) {
                // 1 solution
            } else {
                // 2 solutions
            }
        } else if (delta == 0) {
            // 1 solution
        } else {
            // Imaginary double solutions
        }
    }
}
