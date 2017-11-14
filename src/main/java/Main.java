import services.Database;
import services.DatabaseManager;
import services.enums.SqlType;

import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Database database = databaseManager.createDatabase("asd");
        HashMap<String,SqlType> colums = new HashMap<String, SqlType>();
        colums.put("id",SqlType.INTEGER);
        database.addTable("asdf",colums,"asdf");
        database.localSave("D:/Test/");

        database = Database.loadFromFile("D:/Test/asd");
        System.out.println(database.getTableByName("asdf").getName());
        database.removeTable("asdf");

        databaseManager.removeDatabase("asd");



    }
}
