import java.sql.*;

public class Main {

    private static final String url="jdbc:h2:C:\\Users\\skapl\\Desktop\\Practika\\test\\DB\\TelegramBot";
    private static final String user="admin";
    private static final String password="admin";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) {

        try{

            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

                rs = stmt.executeQuery("SELECT * FROM STAFF");
                while (rs.next()) {
                    System.out.println(rs.getInt("ID") + " " + rs.getString( "FIRST_NAME"));
                    //int count = rs.getInt(1);


                }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try { con.close(); } catch(SQLException se) {}
            try { stmt.close(); } catch(SQLException se) {}
            try { rs.close(); } catch(SQLException se) {}
        }




    }




}
