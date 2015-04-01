package calculator.datatypes.rational;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;
import calculator.OperationNotSupportedException;

import java.math.BigInteger;

/**
 * Created by kiselev on 21.03.15.
 */
public class RationalValue extends AbstractValue {
    private final long numerator, denominator;

    public RationalValue(long numerator, long denominator) {
        super();
        if (denominator != 0) {
            long gcd = BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).longValueExact();
            if (denominator > 0) {
                this.numerator = numerator / gcd;
                this.denominator = denominator / gcd;
            }
            else {
                this.numerator = -numerator / gcd;
                this.denominator = -denominator / gcd;
            }
        }
        else {
            this.denominator = 1;
            this.numerator = Integer.MAX_VALUE;
        }
    }

    @Override
    public AbstractValue add(AbstractValue operand) throws OperationNotSupportedException {
        RationalValue that = (RationalValue) operand;
        return new RationalValue(this.numerator*that.denominator + this.denominator*that.numerator,
                this.denominator*that.denominator);
    }

    @Override
    public AbstractValue sub(AbstractValue operand) throws OperationNotSupportedException {
        RationalValue that = (RationalValue) operand;
        return this.add(that.mul(new RationalValue(-1, 1)));
    }

    @Override
    public AbstractValue mul(AbstractValue operand) throws OperationNotSupportedException {
        RationalValue that = (RationalValue) operand;
        return new RationalValue(this.numerator*that.numerator,
                this.denominator*that.denominator);
    }

    private RationalValue invert() throws DivisionByZeroException {
        if (numerator != 0){
            return new RationalValue(denominator, numerator);
        } else
            throw new DivisionByZeroException();
    }

    @Override
    public AbstractValue div(AbstractValue operand) throws DivisionByZeroException, OperationNotSupportedException {
        RationalValue that = (RationalValue) operand;
        return this.mul(that.invert());
    }

    @Override
    public String toString() {
        return ("[" + numerator + " / " + denominator + "]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RationalValue that = (RationalValue) o;

        if (denominator != that.denominator) return false;
        if (numerator != that.numerator) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (numerator ^ (numerator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }
}
