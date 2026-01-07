public class FineTest {

    public static void main(String[] args) {

        System.out.println("=== Test Constructor 1 ===");
        Fine fine1 = new Fine();

        System.out.println("Late Days: " + fine1.getLateDays());
        System.out.println("Fine Rate: " + fine1.getFineRate());
        System.out.println("Total Fine: " + fine1.getTotalFine());

        fine1.setLateDays(5);
        fine1.setFineRate(10.0);

        System.out.println("After Setting Values");
        System.out.println("Late Days: " + fine1.getLateDays());
        System.out.println("Fine Rate: " + fine1.getFineRate());
        System.out.println("Total Fine: " + fine1.getTotalFine());

        System.out.println();

        System.out.println("=== Test Constructor 2 ===");
        Fine fine2 = new Fine(3, 15.0);

        System.out.println("Late Days: " + fine2.getLateDays());
        System.out.println("Fine Rate: " + fine2.getFineRate());
        System.out.println("Total Fine: " + fine2.getTotalFine());
    }
}
