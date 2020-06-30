import java.sql.*;
import java.util.Date;

public class InfRepository {
   // public static String getChatId() {

   // }
    private static final String url="jdbc:h2:C:\\Users\\skapl\\Desktop\\Practika\\test\\DB\\TelegramBot";
    private static final String user="admin";
    private static final String password="admin";

    public String getFio(Long tgId) {
        try ( Connection con = DriverManager.getConnection(url, user, password);
              Statement stmt = con.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT * FROM STAFF WHERE TG_ID = " + tgId);) {

            if (rs.next()) {
                System.out.println(rs.getInt("ID") + " " + rs.getString( "FIRST_NAME"));
                //int count = rs.getInt(1);
                return rs.getString("FIRST_NAME");
            }

            return null;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return null;
        }
    }

    public void getName() {
        int iddd = 3;
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM STAFF WHERE ID < ?");) {
            preparedStatement.setInt(1, iddd);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("ID");
                String name = resultSet.getString("FIRST_NAME");
                System.out.println(resultSet.getInt("ID") + " " + resultSet.getString("FIRST_NAME"));

            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
   /* public static class Test {

        public static void main(String args[]) {

            Date date = new Date();
            //System.out.println(date.toString());
        }
    }*/

  public String getData(Long tgId){
      Date date = new Date();
      try (Connection con = DriverManager.getConnection(url, user, password);
           PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM STAFF WHERE D_CLEAN = ?");) {
         preparedStatement.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
         ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Long id = resultSet.getLong("TG_ID");
                if(tgId.equals(id)) {
                    return "duty";
                } else {
                    return "free";
                }
//                System.out.println(resultSet.getInt("ID") + " " + resultSet.getString( "FIRST_NAME"));
                //int count = rs.getInt(1);
//                return resultSet.getString("FIRST_NAME" + " " + "Today you clean");
            }
            return "today weekend";
      } catch (SQLException sqlEx) {
          sqlEx.printStackTrace();
      }
      return null;
   }

   /* public String getStaff() {
        try ( Connection con = DriverManager.getConnection(url, user, password);
              Scanner scanner = new Scanner(System.in);
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();

              System.out.println("Enter your second name: ");
              String sname = scanner.nextLine();

             // Statement stmt = con.createStatement();
              //ResultSet rs = stmt.executeQuery("SELECT * FROM STAFF WHERE TG_ID = " + tgId);) {

           /* if (rs.next()) {
                System.out.println(rs.getInt("ID") + " " + rs.getString( "FIRST_NAME") + " " + rs.getString("SECOND_NAME"));
                //int count = rs.getInt(1);
                return rs.getString("FIRST_NAME, SECOND_NAME");
            }*/

            //return null;

        //} catch (SQLException sqlEx) {
          //  sqlEx.printStackTrace();
            //return null;
        //}
    }




