package fr.lbarthon.computorv1;

import fr.lbarthon.computorv1.exceptions.DegreeLimitExceededException;
import fr.lbarthon.computorv1.exceptions.InvalidPowerException;
import fr.lbarthon.computorv1.exceptions.ParserException;

public class ComputorV1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar computor <equation>");
            return;
        }

        try {
            Equation equation = Parser.parse(args[0]);
            Solver.solve(equation);
        } catch (ParserException e) {
            e.displayProblematicPart();
        } catch (DegreeLimitExceededException e) {
            System.out.println("The polynomial degree is strictly greater than 2, I can't solve.");
        } catch (InvalidPowerException e) {
            System.out.println("Unhandled power: " + e.getPower());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing the input !");
        }
    }
}
