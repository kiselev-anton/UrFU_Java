package calculator.datatypes.real;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;

public class RealValue extends AbstractValue {

	private final double value;

	public RealValue(double value) {
		super();
		this.value = value;
	}

	public String toString() {
		return Double.toString(value);
	}

	public AbstractValue add(AbstractValue operand) {
		return new RealValue(value + ((RealValue) operand).value);
	}

	public AbstractValue sub(AbstractValue operand) {
		return new RealValue(value - ((RealValue) operand).value);
	}

	public AbstractValue mul(AbstractValue operand) {
		return new RealValue(value * ((RealValue) operand).value);
	}

	public AbstractValue div(AbstractValue operand)
			throws DivisionByZeroException {
		double realValue = ((RealValue) operand).value;
		if (realValue == 0.0)
			throw new DivisionByZeroException();
		return new RealValue(value / realValue);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RealValue)) return false;

        RealValue realValue = (RealValue) o;

        if (Double.compare(realValue.value, value) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
