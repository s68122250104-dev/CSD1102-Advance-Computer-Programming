public class Fine {


    private int lateDays;    
    private double fineRate;  
    private double totalFine; 

    public Fine() {
        this.lateDays = 0;
        this.fineRate = 0.0;
        this.totalFine = 0.0;
    }

    public Fine(int lateDays, double fineRate) {
        this.lateDays = lateDays;
        this.fineRate = fineRate;
        this.totalFine = lateDays * fineRate;
    }

    public void calculateFine() {
        this.totalFine = lateDays * fineRate;
    }

    public int getLateDays() {
        return lateDays;
    }

    public double getFineRate() {
        return fineRate;
    }

    public double getTotalFine() {
        return totalFine;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
        calculateFine();
    }

    public void setFineRate(double fineRate) {
        this.fineRate = fineRate;
        calculateFine();
    }
}