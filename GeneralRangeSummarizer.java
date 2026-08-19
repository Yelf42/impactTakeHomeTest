package numberrangesummarizer;

import java.util.*;

public class GeneralRangeSummarizer implements NumberRangeSummarizer{

    @Override
    public Collection<Integer> collect(String input) {
        if (input.isEmpty()) return List.of();

        List<String> split = Arrays.stream(input.split("[^-0-9]+")).filter((s) -> !s.isEmpty()).toList();
        if (split.isEmpty()) {
            throw new IllegalArgumentException("Non-empty input contains no numbers");
        }

        return split.stream().map(Integer::parseInt).toList();
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input.isEmpty()) return "";

        StringBuilder output = new StringBuilder();

        Iterator<Integer> iterator = input.iterator();
        Range current = new Range(iterator.next());
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!current.join(next)) {
                output.append(current.rangeToString());
                output.append(", ");
                current.set(next);
            }
        }

        output.append(current.rangeToString());

        return output.toString();
    }

    private static class Range {
        int a;
        int b;

        Range(int a) {
            this.a = a;
            this.b = a;
        }

        void set(int a) {
            this.a = a;
            this.b = a;
        }

        // Join if c == b +- 1 AND
        // a == b OR adding c maintains inequality between a and b
        // Return true if c can be inserted into the range, false otherwise
        boolean join(int c) {
            if (Math.abs(b - c) != 1) return false;

            if ((a == b)
                    || ((a < b) && (c == b + 1))
                    || ((a > b) && (c == b - 1))) {
                this.b = c;
                return true;
            }

            return false;
        }

        String rangeToString() {
            if (a == b) return String.valueOf(a);
            return a + "-" + b;
        }

    }
}
