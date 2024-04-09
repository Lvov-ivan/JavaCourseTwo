package lvov.course2.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

   @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getSum(Range range) {
        if (range.from < to) {
            return new Range(range.from, to);
        }

        if (from < range.from && range.to < to) {
            return new Range(range.from, range.to);
        }

        if (from == range.from && to < range.to) {
            return new Range(range.from, to);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (range.from <= to) {
            return new Range[]{new Range(this.from, range.to)};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (range.from < from && range.to > to) {
            return new Range[0];
        }

        if (range.from < to) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public static String printRanges(Range[] ranges) {
        StringBuilder stringBuilder = new StringBuilder();
        if (ranges.length == 0) {
            return "[]";
        }

        stringBuilder.append('[');

        for (Range range : ranges) {
            stringBuilder
                    .append(range)
                    .append(", ");
        }

        stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length())
                .append(']');

        return stringBuilder.toString();
    }
}