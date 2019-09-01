package fr.lbarthon.computorv1;

import javax.naming.SizeLimitExceededException;

public class ComputorV1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            return; // Handle wrong input
        }

        Equation equation = Parser.parse(args[0]);

        try {
            Solver.solve(equation);
        } catch (SizeLimitExceededException e) {
            // Not handling this degree
        }
    }
}
