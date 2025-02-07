package no.ntnu.stud;

public class MathHandler {
    public static double perform(Request request){
        double result = 0;
        switch(request.getOperation()) {
            case ADDITION       -> { result = request.getOne() + request.getOther(); }
            case SUBTRACTION    -> { result = request.getOne() - request.getOther(); }
            case DIVISION       -> { result = request.getOne() / request.getOther(); }
            case MULTIPLICATION -> { result = request.getOne() * request.getOther(); }
        }

        try { // Simulate heavy operation
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }

        return result;
    }
}
