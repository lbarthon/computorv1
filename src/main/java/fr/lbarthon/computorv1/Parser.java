package fr.lbarthon.computorv1;

import fr.lbarthon.computorv1.exceptions.InvalidPowerException;
import fr.lbarthon.computorv1.exceptions.ParserException;

import java.util.Arrays;

public class Parser {
    private Parser() {}

    public static Equation parse(String str) throws
            ParserException,
            InvalidPowerException,
            ArrayIndexOutOfBoundsException
    {
        Equation equation = new Equation();

        String[] parts = str.split("=");

        if (parts.length != 2) {
            throw new ParserException(str, str.length());
        }

        parsePart(equation.getLeftPart(), parts[0]);
        parsePart(equation.getRightPart(), parts[1]);

        return equation;
    }

    private static void parsePart(Equation.Part part, String str) throws
            ParserException,
            InvalidPowerException,
            ArrayIndexOutOfBoundsException
    {
        int i = 0;
        boolean isNegative;
        double nbr;
        double power;
        // The X or whatever
        Character unknown = null;
        char[] chars = str.toCharArray();

        while (i < chars.length) {
            if (Character.isSpaceChar(chars[i])) {
                i++;
                continue;
            }
            isNegative = false;

            if (chars[i] == '+' || chars[i] == '-') {
                isNegative = chars[i] == '-';

                // Skipping all the spaces
                do i++;
                while (Character.isSpaceChar(chars[i]));
            }


            if (!Character.isDigit(chars[i])) {
                throw new ParserException(str, i);
            }

            nbr = atoi(Arrays.copyOfRange(chars, i, chars.length));

            // After getting the atoi value, skipping all digits
            do i++;
            while (i < chars.length && Character.isDigit(chars[i]));

            if (i < chars.length && (chars[i] == '.' || chars[i] == ',')) {
                do i++;
                while (i < chars.length && Character.isDigit(chars[i]));
            }

            // Skipping all the spaces
            while (i < chars.length && Character.isSpaceChar(chars[i])) {
                i++;
            }

            if (i >= chars.length) {
                part.addEntry(0, isNegative ? -nbr : nbr);
                continue;
            }

            if (chars[i] != '*') {
                if (chars[i] == '+' || chars[i] == '-') {
                    part.addEntry(0, isNegative ? -nbr : nbr);
                    continue;
                } else {
                    throw new ParserException(str, i);
                }
            }

            // Skipping all the spaces
            do i++;
            while (Character.isSpaceChar(chars[i]));

            // Handling custom unknowns (X, Y, Z, x, y, z, ....)
            if (unknown != null && chars[i] != unknown) {
                throw new ParserException(str, i);
            }
            if (unknown == null) {
                unknown = chars[i];
            }

            if (chars[++i] != '^') {
                throw new ParserException(str, i);
            }

            if (!Character.isDigit(chars[++i])) {
                throw new ParserException(str, i);
            }

            power = atoi(Arrays.copyOfRange(chars, i, chars.length));

            if (power % 1 != 0 || power < 0) {
                throw new InvalidPowerException(power);
            }

            // After getting the atoi value, skipping all digits
            do i++;
            while (i < chars.length && Character.isDigit(chars[i]));

            part.addEntry((int) power, isNegative ? -nbr : nbr);
        }
    }
    /**
     * Equivalent of c atoi function.
     * This function does not handle the signs (+ & -)
     * @param chars
     * @return
     */
    private static double atoi(char[] chars) {
        double ret = 0;
        int i = 0;

        while (Character.isSpaceChar(chars[i])) {
            i++;
        }

        for (; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) break;
            ret *= 10;
            ret += Character.getNumericValue(chars[i]);
        }

        if (i < chars.length && (chars[i] == '.' || chars[i] == ',')) {
            i++;

            int nbrsAfterDot = 0;
            for (; i < chars.length; i++) {
                if (!Character.isDigit(chars[i])) break;
                nbrsAfterDot++;
                ret += Character.getNumericValue(chars[i]) / power(10, nbrsAfterDot);
            }
        }

        return ret;
    }

    private static double power(double nbr, int power) {
        if (power == 0) return 1;
        return nbr * power(nbr, power - 1);
    }
}
