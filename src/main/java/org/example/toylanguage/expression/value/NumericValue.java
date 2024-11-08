package org.example.toylanguage.expression.value;

public class NumericValue extends ComparableValue<Double> {
    public NumericValue(Double value) {
        super(value);
    }

    @Override
    public String toString() {
        if ((getValue() % 1) == 0)
            return String.format("%.0f", getValue());
        return super.toString();
    }
}
