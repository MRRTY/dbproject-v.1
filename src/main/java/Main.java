import exceptions.NotAllowedDatabaseNameException;
import services.Database;
import services.DatabaseManager;

public class Main {
    public static void main(String[] args){
        DatabaseManager databaseManager = new DatabaseManager();
        Database database = databaseManager.createDatabase("sdf");
        database.addTable("asdf");
        database.removeTable("asdf");

        databaseManager.removeDatabase("sdf");

    }
}
