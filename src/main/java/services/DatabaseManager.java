package services;

import exceptions.NoSuchDatabaseException;
import exceptions.NotAllowedDatabaseNameException;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final List<Database> LIST_DATABASE = new ArrayList<>();
    //Mock
    public Database createDatabase(String name) {
        if(!LIST_DATABASE.stream().anyMatch(db -> db.getName().equals(name))){
            Database database = new Database(name);
            LIST_DATABASE.add(database);
            System.out.println("Creating database "+ database.getName());
            return database;
        }else {
            throw new NotAllowedDatabaseNameException();
        }
    }
    //Mock
    public void removeDatabase(String name) {
        Database removingDatabase = getDatabaseByName(name);
        LIST_DATABASE.remove(removingDatabase);
        System.out.println("Removing "+ name+" database");


    }

    public Database getDatabaseByName(String name){
        if (LIST_DATABASE.stream().anyMatch(db -> db.getName().equals(name))) {
            return LIST_DATABASE.stream().findAny().filter(db -> db.getName().equals(name)).get();
        } else {
            throw new NoSuchDatabaseException();
        }
    }


}
