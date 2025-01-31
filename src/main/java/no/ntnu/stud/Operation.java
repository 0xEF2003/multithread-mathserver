package no.ntnu.stud;

public enum Operation {
    ADDITION("A"),
    SUBTRACTION("S"),
    DIVISION("D"),
    MODULUS("M");
    
    private final String value;
    private Operation(String value) {
         this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Operation fromValue(String value) {
        Operation operation = null;
        for (Operation type : Operation.values()) {
            if (type.getValue() == value)
                operation = type;
        }
        return operation;
    }
}
