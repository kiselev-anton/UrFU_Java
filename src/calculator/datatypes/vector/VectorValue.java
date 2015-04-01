package calculator.datatypes.vector;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;
import calculator.OperationNotSupportedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiselev on 21.03.15.
 */
public class VectorValue<TValue extends AbstractValue> extends AbstractValue {
    private ArrayList<TValue> values = new ArrayList<>();
    private final int dim;

    public VectorValue(List<TValue> values) {
        super();
        this.values.addAll(values);
        dim = values.size();
    }

    @Override
    public AbstractValue add(AbstractValue operand) throws OperationNotSupportedException {
        VectorValue<TValue> that = (VectorValue<TValue>) operand;
//        System.out.println("this = " + this.values);
//        System.out.println("that = " + that.values);
        if (this.dim == that.dim) {
            List<TValue> values = new ArrayList<TValue>();
            for (int i = 0; i < dim; i++) {
//                System.out.println("result[" + i + "] = " + that.values.get(i).add(this.values.get(i)));
                values.add((TValue) this.values.get(i).add(that.values.get(i)));
            }
            return new VectorValue<>(values);
        } else throw new OperationNotSupportedException("different length of vectors addition");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VectorValue)) return false;

        VectorValue that = (VectorValue) o;
        if (dim != that.dim) return false;

//        System.out.println(this.values);
//        System.out.println(that.values);
//        System.out.println(this.values.equals(that.values));
        return this.values.equals(that.values);
    }

    @Override
    public int hashCode() {
        int result = values.hashCode();
        result = 31 * result + dim;
        return result;
    }

    @Override
    public AbstractValue sub(AbstractValue operand) throws OperationNotSupportedException {
        VectorValue<TValue> that = (VectorValue) operand;
        if (this.dim == that.dim) {
            List<TValue> values = new ArrayList<>();
            for (int i = 0; i < dim; i++) {
//                System.out.println("result[" + i + "] = " + this.values.get(i).sub(that.values.get(i)));
                values.add( (TValue) this.values.get(i).sub(that.values.get(i)) );
            }
            return new VectorValue<>(values);

        } else throw new OperationNotSupportedException("Cannot subtract vectors with different length.");
    }

    @Override
    public AbstractValue mul(AbstractValue operand) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Cannot multiply vectors.");
    }

    @Override
    public AbstractValue div(AbstractValue operand) throws DivisionByZeroException, OperationNotSupportedException {
        throw new OperationNotSupportedException("Cannot divide vectors.");
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
