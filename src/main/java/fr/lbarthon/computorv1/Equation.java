package fr.lbarthon.computorv1;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Equation class.
 * Each equation has two parts, left and right part.
 * Each part has an array of entries, containing the power of the unknown number (x),
 * and the number by whom he's multiplied.
 */
public class Equation {

    @Getter
    private Part leftPart;
    @Getter
    private Part rightPart;
    @Getter
    private boolean isReduced;

    public Equation() {
        this.leftPart = new Part();
        this.rightPart = new Part();
        this.isReduced = false;
    }

    public int getDegree() {
        // Default entry returned if one of the list is empty
        Entry defaultEntry = new Entry(-1, 0);

        Entry leftMax = leftPart.getEntries().stream()
                .max(Comparator.comparingInt(Entry::getPower))
                .orElse(defaultEntry);
        Entry rightMax = rightPart.getEntries().stream()
                .max(Comparator.comparingInt(Entry::getPower))
                .orElse(defaultEntry);

        return Math.max(leftMax.getPower(), rightMax.getPower());
    }

    /**
     * Changes the equation to it's reduced form.
     * The goal is to get a right part as small as possible (always should be 0)
     */
    public void reduce() {
        this.rightPart.getEntries().forEach(rightEntry -> {
            Entry leftEntry = this.leftPart.getEntryByPower(rightEntry.getPower());
            if (leftEntry != null) {
                leftEntry.setNbr(leftEntry.getNbr() - rightEntry.getNbr());
            } else {
                this.leftPart.addEntry(rightEntry.getPower(), -rightEntry.getNbr());
            }
        });

        this.rightPart.getEntries().clear();
        this.isReduced = true;
    }

    public String toString() {
        return this.leftPart.toString() + " = " + this.rightPart.toString();
    }

    @NoArgsConstructor
    public class Part {
        @Getter
        private List<Entry> entries = new ArrayList<>();

        public void addEntry(Entry e) {
            this.entries.add(e);
        }

        public void removeEntry(Entry e) {
            this.entries.remove(e);
        }

        public void addEntry(int power, double nbr) {
            this.addEntry(new Entry(power, nbr));
        }

        public Entry getEntryByPower(int power) {
            return this.entries.stream().filter(e -> e.getPower() == power).findFirst().get();
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();

            this.entries.sort(Comparator.comparingInt(Entry::getPower));
            this.entries.forEach(entry -> {
                // If it isn't empty, adds a space
                if (builder.length() > 0) builder.append(' ');
                builder.append(entry.getNbr());
                builder.append(" * X^");
                builder.append(entry.getPower());
            });

            // If the part is empty, displays only a 0
            if (builder.length() == 0) {
                builder.append(0);
            }

            return builder.toString();
        }
    }

    @Data
    @AllArgsConstructor
    public class Entry {
        private int power;
        private double nbr;
    }
}
