import services.Database;
import services.DatabaseManager;
import services.MysqlManager;
import services.enums.SqlType;

import java.util.HashMap;
import java.util.Properties;

public class Main {
    public static void main(String[] args){
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Database database = databaseManager.createDatabase("asd");
        HashMap<String,SqlType> colums = new HashMap<String, SqlType>();
        colums.put("id",SqlType.INTEGER);
        database.addTable("asdf",colums,"asdf");
        database.removeTable("asdf");

        databaseManager.removeDatabase("asd");



    }
}
