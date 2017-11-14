package services;

import exceptions.NoSuchDatabaseException;
import exceptions.NotAllowedDatabaseNameException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager implements Serializable {
    private static final DatabaseManager databaseManager = new DatabaseManager();
    private final List<Database> LIST_DATABASE = new ArrayList<>();

    private DatabaseManager() {

    }

    public  Database createDatabase(String name) {
        if(!LIST_DATABASE.stream().anyMatch(db -> db.getName().equals(name))){
            Database database = new Database(name);
            LIST_DATABASE.add(database);

            System.out.println("Creating database "+ database.getName());
            return database;
        }else {
            throw new NotAllowedDatabaseNameException();
        }
    }

    public void removeDatabase(String name) {
        Database removingDatabase = getDatabaseByName(name);
        LIST_DATABASE.remove(removingDatabase);
        Connection connection = null;

        System.out.println("Removing "+ name+" database");


    }

    public Database getDatabaseByName(String name){
        if (LIST_DATABASE.stream().anyMatch(db -> db.getName().equals(name))) {
            return LIST_DATABASE.stream().findAny().filter(db -> db.getName().equals(name)).get();
        } else {
            throw new NoSuchDatabaseException();
        }
    }

    public static DatabaseManager getInstance(){
        return databaseManager;
    }


}
