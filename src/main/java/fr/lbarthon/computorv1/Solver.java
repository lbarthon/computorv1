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

        long degree = equation.getDegree();
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

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("All reals are solutions.");
                } else {
                    System.out.println("There's no solution to this equation.");
                }
                return;
            }

            System.out.println("The solution is:");

            if (c == 0) {
                System.out.println(0D);
            } else {
                System.out.println(c / b);
            }

            return;
        }

        double delta = b * b - 4 * a * c;
        double deltaSqrt = Utils.sqrt(delta);

        if (delta > 0) {
            System.out.println("Discriminant is strictly positive, the two solutions are:");
            System.out.println((-b - deltaSqrt) / (2 * a) + 0.0);
            System.out.println((-b + deltaSqrt) / (2 * a) + 0.0);
        } else if (delta == 0) {
            System.out.println("Discriminant is zero, the solution is:");
            System.out.println((-b / (2 * a)) + 0.0);
        } else {
            System.out.println("Discriminant is negative, imaginary solutions are:");
            System.out.println((-b / (2 * a)) + " + i√" + delta + "/" + 2 * a);
            System.out.println((-b / (2 * a)) + " - i√" + delta + "/" + 2 * a);
        }
    }
}
