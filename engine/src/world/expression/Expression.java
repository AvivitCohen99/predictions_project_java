package world.expression;

import world.World;
import world.entity.IEntity;
import world.property.PropertyDefinition;
import world.property.PropertyType;

import java.util.Optional;
import java.util.Random;

public class Expression {
    String function;
    String arg;
    Object value;
    Random random;
    String entity;

//    TODO: should support different expression types.
    public Expression(String expression, String entity) {
        int openParenIndex = expression.indexOf('(');
        int closeParenIndex = expression.indexOf(')');

        if (openParenIndex != -1 && closeParenIndex != -1 && closeParenIndex > openParenIndex) {
            this.function = expression.substring(0, openParenIndex);
            this.arg = expression.substring(openParenIndex + 1, closeParenIndex);
        } else {
            this.value = expression;
        }
        random = new Random();
        this.entity = entity;
    }

    public Object getValue(World world) throws Exception {
        if(this.value != null){
            return this.value;
        }
        if(function.equals("environment")){
            PropertyDefinition envProp = world.getEnv().getEnvironmentVariable(arg);
            if(envProp != null) {
                return envProp.getValue();
            }
        }

        if(function.equals("random")){
            Integer argInt = PropertyType.DECIMAL.convert(this.arg);
            return random.nextInt(argInt);
        }
        throw new Exception("unsupported expression");
    }

    public Number getNumericValue(World world, IEntity entity) throws Exception {
        if (this.value instanceof String) {
            try {
                Number value = PropertyType.FLOAT.convert(this.value);
                return value;
            } catch (Exception e) {
                Optional<PropertyDefinition> prop = entity.getProp((String) this.value);
                if (prop.isPresent()) {
                    return PropertyType.DECIMAL.convert(prop.get().getValue());
                }
            }
        } else if (this.function != null) {
            return (Number) getValue(world);
        }

        throw new Exception("Failed to get numeric value");
    }
}
