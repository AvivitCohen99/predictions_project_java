package world.property;

public enum PropertyType {
    DECIMAL {
        public Integer convert(Object value) {
            if (!(value instanceof Integer)) {
                throw new IllegalArgumentException("value " + value + " is not of a DECIMAL type (expected Integer class)");
            }
            return (Integer) value;
        }
    }, BOOLEAN {
        public Boolean convert(Object value) {
            if (!(value instanceof Boolean)) {
                throw new IllegalArgumentException("value " + value + " is not of a BOOLEAN type (expected Integer class)");
            }
            return (Boolean) value;
        }
    }, FLOAT {
        public Double convert(Object value) {
            if (!(value instanceof Double)) {
                throw new IllegalArgumentException("value " + value + " is not of a Double type (expected Integer class)");
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