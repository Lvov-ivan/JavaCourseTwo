package lvov.range;

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

    public String toString() {
        return "(" + this.from + "; " + this.to + ")";
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (range.from <= this.to) {
            return new Range(range.from, this.to);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (range.from <= this.to) {
            return new Range[]{new Range(this.from, range.to)};
        }

        return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (this.from == range.from && this.to == range.to) {
            return new Range[0];
        }

        if (this.from == range.from && this.to != range.to) {
            return new Range[]{new Range(this.to + 1, range.to)};
        }

        if (this.to < range.from) {
            return new Range[]{new Range(range.from, range.to)};
        }

        return new Range[]{new Range(this.from, range.from - 1), new Range(this.to + 1, range.to)};
    }
}