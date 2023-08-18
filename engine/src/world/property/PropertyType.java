package world.property;

public enum PropertyType {
    DECIMAL {
        public Integer convert(Object value) {
            if (!(value instanceof Integer)) {
                try {
                    return Integer.parseInt(value.toString());
                }catch (Exception e) {
                    throw new IllegalArgumentException("value " + value + " is not of a DECIMAL type (expected Integer class)");
                }
            }
            return (Integer) value;
        }
    }, BOOLEAN {
        public Boolean convert(Object value) {
            if (!(value instanceof Boolean)) {
                try {
                    return Boolean.parseBoolean(value.toString());
                }catch (Exception e) {
                throw new IllegalArgumentException("value " + value + " is not of a BOOLEAN type (expected Integer class)");
                }
            }
            return (Boolean) value;
        }
    }, FLOAT {
        public Double convert(Object value) {
            if (!(value instanceof Double)) {
                try {
                    return Double.parseDouble(value.toString());
                }catch (Exception e) {
                    throw new IllegalArgumentException("value " + value + " is not of a Double type (expected Integer class)");
                }
            }
            return (Double) value;
        }
    }, STRING {
        public String convert(Object value) {
            if (!(value instanceof String)) {
                throw new IllegalArgumentException("value " + value + " is not of a String type (expected Integer class)");
            }
            return (String) value;
        }
    };

    public abstract <T> T convert(Object value);
}