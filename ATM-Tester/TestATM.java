class MyTestException extends RuntimeException {

    private int errorCode;

    public MyTestException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

class ATM {

    public static void withdraw(double balance, double amount) {

        if (amount > balance) {
            throw new MyTestException("You don't have enough money in account", 2001);
        }

        System.out.println("Withdraw successful");
    }
}

public class TestATM {

    public static void main(String[] args) {

        double balance = 5000;
        double amount = 7000;

        try {
            ATM.withdraw(balance, amount);
        } catch (MyTestException e) {
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
        }

    }
}