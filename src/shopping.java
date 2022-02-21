import java.sql.*;

public class shopping {
    static Connection con = null;
    static PreparedStatement stmt = null;
    static ResultSet rs;

    public void dbCon() throws Exception{
        con = DbConnection.getDbConnection();
    }

    //Ques 1
    public void putDataToProductsTable(int pid, double price, String name) throws Exception{
        String query = "INSERT INTO products VALUES (?,?,?)";
        stmt = con.prepareStatement(query);
        stmt.setInt(1,pid);
        stmt.setDouble(2,price);
        stmt.setString(3,name);
        int count = stmt.executeUpdate();
        System.out.println(count + " rows Effected");

    }
    public void putDataToCartTable(int pid, int qty) throws Exception{
        String query = "INSERT INTO cartdata VALUES (?,?)";
        stmt =con.prepareStatement(query);
        stmt.setInt(1,pid);
        stmt.setInt(2,qty);

        int count = stmt.executeUpdate();
        System.out.println(count + " rows Effected");
    }
    //.....

    //Ques 2
    public void printData(int pid) throws Exception{
        String query = "SELECT * FROM products WHERE products.pid =" +pid;
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            System.out.println(rs.getDouble(2) + " " + rs.getString(3));
        }
        else{
            System.out.println("Empty");
        }

    }

    //Ques 3
    public void findAveragePrice() throws Exception{
        String query = "SELECT pid ,AVG(price) AS 'Avg Price' FROM products GROUP BY pid";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt(1) + "  " + rs.getDouble(2));
        }
    }


    public static void main(String[] args) throws Exception{
        shopping mainObj = new shopping();
        mainObj.dbCon();
        mainObj.putDataToProductsTable(1,2,"Reynolds 405_Shivam");
        mainObj.putDataToCartTable(4,5);
        mainObj.printData(10);
        mainObj.findAveragePrice();
        stmt.close();
        con.close();
    }
}