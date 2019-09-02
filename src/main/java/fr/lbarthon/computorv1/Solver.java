package fr.lbarthon.computorv1;

import fr.lbarthon.computorv1.exceptions.DegreeLimitExceededException;

import java.util.List;

public class Solver {
    private Solver() {}

    public static void solve(Equation equation) throws
            DegreeLimitExceededException
    {
        if (!equation.isReduced()) {
            equation.reduce();
        }

        System.out.println("Reduced form: " + equation.toString());

        int degree = equation.getDegree();
        System.out.println("Polynomial degree: " + degree);

        if (degree > 2) {
            throw new DegreeLimitExceededException(degree);
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

        if (a == 0 && b == 0) {
            if (c == 0) {
                System.out.println("All reals are solutions.");
            } else {
                System.out.println("There's no solution to this equation.");
            }
            return;
        }

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
