package calculator.datatypes.complex;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;
import calculator.OperationNotSupportedException;

public class ComplexValue extends AbstractValue {
    private final double real, imaginary;

    public ComplexValue(double real, double imaginary) {
        super();
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public AbstractValue add(AbstractValue operand) throws OperationNotSupportedException {
        ComplexValue that = (ComplexValue) operand;
        return new ComplexValue(this.real + that.real, this.imaginary + that.imaginary);
    }

    @Override
    public AbstractValue sub(AbstractValue operand) throws OperationNotSupportedException {
        ComplexValue that = (ComplexValue) operand;
        return this.add(that.mul(new ComplexValue(-1, 0)));
    }

    @Override
    public AbstractValue mul(AbstractValue operand) throws OperationNotSupportedException {
        ComplexValue that = (ComplexValue) operand;
        return new ComplexValue(this.real * that.real - this.imaginary*that.imaginary,
                this.imaginary*that.real + that.imaginary * this.real);
    }

    private ComplexValue inverse() throws DivisionByZeroException {
        double squareOfModule = real*real + imaginary*imaginary;
        if (squareOfModule != 0) {
            return new ComplexValue(real / squareOfModule, -imaginary / squareOfModule);
        } else throw new DivisionByZeroException();
    }

    @Override
    public AbstractValue div(AbstractValue operand) throws DivisionByZeroException, OperationNotSupportedException {
        ComplexValue that = (ComplexValue) operand;
        return this.mul(that.inverse());
    }

    @Override
    public String toString() {
        if (imaginary >= 0) {
            return (real + "+" + imaginary + "i");
        }
        else {
            return (real + imaginary + "i");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexValue)) return false;

        ComplexValue that = (ComplexValue) o;

        if (Double.compare(that.imaginary, imaginary) != 0) return false;
        if (Double.compare(that.real, real) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(real);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imaginary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
