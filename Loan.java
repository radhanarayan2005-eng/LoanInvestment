public class Loan {
    int id;
    String borrowerName;
    double amount;
    double interest;

    public Loan(String borrowerName, double amount, double interest) {
        this.borrowerName = borrowerName;
        this.amount = amount;
        this.interest = interest;
    }
}
