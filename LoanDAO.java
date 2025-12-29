import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class LoanDAO {

    public static void createTable() throws Exception {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS loans (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "borrower TEXT," +
                     "amount REAL," +
                     "interest REAL)";

        st.execute(sql);
        con.close();
    }

    public static void insertLoan(Loan loan) throws Exception {
        Connection con = DBUtil.getConnection();

        String sql = "INSERT INTO loans (borrower, amount, interest) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, loan.borrowerName);
        ps.setDouble(2, loan.amount);
        ps.setDouble(3, loan.interest);

        ps.executeUpdate();
        con.close();
    }

    
    public static void viewLoans() throws Exception {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM loans");

        System.out.println("\n--- Loan Records ---");
        while (rs.next()) {
            System.out.println(
                "ID: " + rs.getInt("id") +
                ", Name: " + rs.getString("borrower") +
                ", Amount: " + rs.getDouble("amount") +
                ", Interest: " + rs.getDouble("interest")
            );
        }

        con.close();
    }
    public static void calculateTotalInterest() throws Exception {
    Connection con = DBUtil.getConnection();
    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery(
        "SELECT SUM(amount * interest / 100) AS totalInterest FROM loans"
    );

    if (rs.next()) {
        double totalInterest = rs.getDouble("totalInterest");
        System.out.println("\nTotal Interest of all loans: " + totalInterest);
    }

    con.close();
}
public static void updateInterest(int id, double newInterest) throws Exception {
    Connection con = DBUtil.getConnection();

    String sql = "UPDATE loans SET interest = ? WHERE id = ?";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setDouble(1, newInterest);
    ps.setInt(2, id);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Loan updated successfully!");
    } else {
        System.out.println("Loan ID not found.");
    }

    con.close();
}
public static void deleteLoan(int id) throws Exception {
    Connection con = DBUtil.getConnection();

    String sql = "DELETE FROM loans WHERE id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, id);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Loan deleted successfully!");
    } else {
        System.out.println("Loan ID not found.");
    }

    con.close();
}



}
