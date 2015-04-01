package calculator.datatypes.vector;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.ParseValueException;
import calculator.datatypes.ValueParserFactory;

import java.util.ArrayList;

/**
 * Created by kiselev on 21.03.15.
 */
public class VectorValueParser<TValue extends AbstractValue> implements AbstractValueParser {

    private AbstractValueParser valueParser;

    public VectorValueParser(Class<TValue> valueType) {
        ValueParserFactory parsersFactory = new ValueParserFactory();
        this.valueParser = parsersFactory.createParser(valueType);
    }

    @Override
    public AbstractValue parse(String value) throws ParseValueException {
        ArrayList<TValue> values = new ArrayList<TValue>();
        String[] valueStrings = value.replaceAll("[ \\]\\[]", "").split(",");
        for (int i = 0; i < valueStrings.length; i++) {
//            System.out.println(valueStrings[i]);
            values.add((TValue) valueParser.parse(valueStrings[i]));
        }
        return new VectorValue<>(values);
    }

    @Override
    public String getDatatypeName() {
        return "Vector of " + valueParser.getDatatypeName();
    }

}
