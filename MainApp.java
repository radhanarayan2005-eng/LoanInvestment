public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);
    LoanDAO.createTable();

    while (true) {
        System.out.println("\n==== Loan Investment System ====");
        System.out.println("1. Add Loan");
        System.out.println("2. View Loans");
        System.out.println("3. Update Loan Interest");
        System.out.println("4. Delete Loan");
        System.out.println("5. Calculate Total Interest");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                sc.nextLine();
                System.out.print("Borrower Name: ");
                String name = sc.nextLine();

                System.out.print("Loan Amount: ");
                double amount = sc.nextDouble();

                System.out.print("Interest Rate: ");
                double interest = sc.nextDouble();

                Loan loan = new Loan(name, amount, interest);
                LoanDAO.insertLoan(loan);
                System.out.println("Loan added successfully!");
                break;

            case 2:
                LoanDAO.viewLoans();
                break;

            case 3:
                System.out.print("Enter Loan ID: ");
                int id = sc.nextInt();

                System.out.print("New Interest Rate: ");
                double newInterest = sc.nextDouble();

                LoanDAO.updateInterest(id, newInterest);
                break;

            case 4:
                System.out.print("Enter Loan ID to delete: ");
                int delId = sc.nextInt();

                LoanDAO.deleteLoan(delId);
                break;

            case 5:
                LoanDAO.calculateTotalInterest();
                break;

            case 6:
                System.out.println("Thank you! Exiting...");
                System.exit(0);

            default:
                System.out.println("Invalid choice!");
        }
    }
}
