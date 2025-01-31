package no.ntnu.stud;

public class Request {
    private Operation operation;
    private double one;
    private double other;

    public Request(String input) throws IllegalArgumentException {

        // Parse and/or complain
        String[] parts = input.trim().split(" "); 
        if (parts.length != 3) {
            throw new IllegalArgumentException("The request can only have three space separated parts");
        }

        // Expected parts in the request
        String operationPart = parts[0];
        String onePart = parts[1];
        String otherPart = parts[2];

        // Initialize fields
        this.operation = Operation.fromValue(operationPart);
        this.one = Double.valueOf(onePart);
        this.other = Double.valueOf(otherPart);
    }

    public Operation getOperation() {
        return this.operation;
    }

    public double getOne() {
        return this.one;
    }

    public double getOther() {
        return this.other;
    }
}
