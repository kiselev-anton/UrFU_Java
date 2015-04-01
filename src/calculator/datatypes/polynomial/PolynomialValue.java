package calculator.datatypes.polynomial;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;
import calculator.OperationNotSupportedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kiselev on 23.03.15.
 */
public class PolynomialValue extends AbstractValue {

    private TreeMap<Integer, Double> monomials = new TreeMap<>(); // (Degree, Coefficient)
    private int degree;

    public PolynomialValue(Map<Integer, Double> monomials) {
        super();
        this.monomials.putAll(monomials);
        Set<Integer> possibleMaxes = new HashSet<>();
        possibleMaxes.add(0);
        possibleMaxes.addAll(
                monomials.keySet().stream()
                        .filter(possibleMax -> Double.compare(monomials.get(possibleMax), 0.0) != 0)
                        .collect(Collectors.toList())
        );
        degree = Collections.max(possibleMaxes);
        if (monomials.isEmpty()) {
            monomials.put(0,0.0);
            degree = 0;
        }
//        System.out.println(degree);
    }

    public int getDegree() {
        return degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PolynomialValue)) return false;

        PolynomialValue that = (PolynomialValue) o;

        Set<Integer> degrees = new HashSet<>();
        degrees.addAll(this.monomials.keySet());
        degrees.retainAll(that.monomials.keySet());
//        System.out.println(degrees.toString());
        return degrees.stream()
                .map((degree) -> Double
                        .compare(monomials.get(degree), that.monomials.get(degree)) == 0)
                .reduce(true, (a, b) -> a && b);

    }

    @Override
    public int hashCode() {
        int result = monomials.hashCode();
        result = 31 * result + degree;
        return result;
    }

    @Override
    public AbstractValue add(AbstractValue operand) throws OperationNotSupportedException {
        PolynomialValue that = (PolynomialValue) operand;
        HashMap<Integer, Double> newValues = new HashMap<>();
        HashSet<Integer> sameDegrees = new HashSet<>();
        sameDegrees.addAll(this.monomials.keySet());
        sameDegrees.retainAll(that.monomials.keySet());

        HashSet<Integer> differentDegrees = new HashSet<>();
        differentDegrees.addAll(this.monomials.keySet());
        differentDegrees.addAll(that.monomials.keySet());
        differentDegrees.removeAll(sameDegrees);

        for (int degree : sameDegrees) {
            newValues.put(degree, this.monomials.get(degree) + that.monomials.get(degree));
        }

        for (int degree : differentDegrees) {
            if (this.monomials.containsKey(degree))
                newValues.put(degree, this.monomials.get(degree));
            else
                newValues.put(degree, that.monomials.get(degree));
        }

        return new PolynomialValue(newValues);
    }

    @Override
    public AbstractValue sub(AbstractValue operand) throws OperationNotSupportedException {
        HashMap<Integer, Double> minusOne = new HashMap<>();
        minusOne.put(0, -1.0);
        return this.add( operand.mul(new PolynomialValue(minusOne)) );
    }

    @Override
    public AbstractValue mul(AbstractValue operand) throws OperationNotSupportedException {
        PolynomialValue that = (PolynomialValue) operand;
        HashMap<Integer, Double> newValues = new HashMap<>();
        for (int degree1 : this.monomials.keySet()) {
            for (int degree2 : that.monomials.keySet()) {
                int newDegree = degree1+degree2;
                double newCoefficient = this.monomials.get(degree1)*that.monomials.get(degree2);
                if (newValues.containsKey(newDegree))
                    newValues.put(newDegree, newValues.get(newDegree) + newCoefficient);
                else
                    newValues.put(degree1+degree2, newCoefficient);
            }
        }
        return new PolynomialValue(newValues);
    }

    @Override
    public AbstractValue div(AbstractValue operand) throws DivisionByZeroException, OperationNotSupportedException {
        PolynomialValue that = (PolynomialValue) operand;
        if (that.degree > this.degree) {
            HashMap<Integer, Double> zeroMonomialList = new HashMap<>();
            zeroMonomialList.put(0,0.0);
            return new PolynomialValue(zeroMonomialList);
        } else {
            int differenceDegree = this.degree - that.degree;
//            System.out.println(differenceDegree);
            Map<Integer, Double> firstMonomial = new HashMap<>();
            firstMonomial.put(differenceDegree,
                    this.monomials.get(this.degree) / that.monomials.get(that.degree));
            PolynomialValue monomial = new PolynomialValue(firstMonomial);

            return monomial.add(this.sub(that.mul(monomial)).div(that));
        }
    }

    private String toMonomialString(Map.Entry<Integer, Double> e) {
        Double coefficient = e.getValue();
        Integer degree = e.getKey();

        if (degree == 0) {
            if (Double.compare(coefficient, 0) == 0) return "";
            else return coefficient.toString();
        }

        if (coefficient < 0) {
            return coefficient.toString() + "*x^" + degree.toString();
        } else if (coefficient > 0) {
            return "+" + coefficient.toString()+ "*x^" + degree.toString();
        } else return "";
    }

    @Override
    public String toString() {
        String monomialString = monomials.entrySet().stream()
                .map(this::toMonomialString)
                .reduce("", (a, b) -> a + b);
        if (monomialString.equals(""))
            return monomialString;
        if (monomialString.charAt(0) == '+')
            return monomialString.substring(1);
        else return monomialString;
    }
}
