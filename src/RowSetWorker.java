import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.RowSetFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Al on 19.08.2016.
 */
public class RowSetWorker {
    private String USER = "root";
    private String PWD = "database2016";
    private String DB = "mysql";
    private String UserDb = "comfy_db";
    private String SERVER = "localhost";
    private String PORT = "3306";

    public  Connection getConnection(){
        Connection conn = null;
        try {
          Class.forName("com.mysql.jdbc.Driver");
          Properties connectionProp = new Properties();
          connectionProp.put("user", this.USER);
          connectionProp.put("password", this.PWD);
          conn = DriverManager.getConnection("jdbc:" + this.DB + "://" + this.SERVER + ":"
                  + this.PORT + "/" + this.UserDb, connectionProp);
            System.out.println("get connection..");
            return conn;
      } catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
            return conn;
    }

    public static void main(String[] args) {
        RowSetWorker rsw = new RowSetWorker();
        Connection conn = null;
           conn = rsw.getConnection();
        try {
            JdbcRowSetImpl jdbcRsImpl = new JdbcRowSetImpl(conn);
            jdbcRsImpl.setCommand("Select * From conditionings");
            jdbcRsImpl.execute();
           // RowSetFactory myRowSetFactory = null;
            int i = 1;
            int colCount = jdbcRsImpl.getMetaData().getColumnCount();
            while(i < colCount) {
                System.out.print("|" + jdbcRsImpl.getMetaData().getColumnName(i));
            i++;
            }
            while(jdbcRsImpl.next()){
             for(i = 1; i < colCount; i++ )
               System.out.print("|" + jdbcRsImpl.getObject(i));
                System.out.println();
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
