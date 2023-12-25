import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //DatabaseUtil.classDbInit();
        String sql = "select * from user";
        ResultSet resultSet = DatabaseUtil.executeQuery(sql);
        while (resultSet.next()) {
            String name = resultSet.getString("username");
            System.out.println(name);
        }
        System.out.println("Hello world!");
        DatabaseUtil.closeDb();
    }
}