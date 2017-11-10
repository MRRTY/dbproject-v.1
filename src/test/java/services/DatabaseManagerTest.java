package services;

import exceptions.NoSuchDatabaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseManagerTest {
    DatabaseManager testDatabaseManager;
    @Before
    public void setUp()  {
        testDatabaseManager = new DatabaseManager();
    }

    @After
    public void tearDown(){
    }

    @Test
    public void createOneDatabase(){
        testDatabaseManager.createDatabase("testDatabase");
        assertTrue(testDatabaseManager.getDatabaseByName("testDatabase")!= null);
        testDatabaseManager.removeDatabase("testDatabase");
    }

    @Test
    public void removeOneDatabase(){
        testDatabaseManager.createDatabase("testDatabase");
        testDatabaseManager.removeDatabase("testDatabase");
    }
    @Test(expected = NoSuchDatabaseException.class)
    public void removeNoExistsDatabase(){
        testDatabaseManager.removeDatabase("notExistsDatabase");
    }

    @Test
    public void getDatabaseByName() {
        String databaseName = "test";
        testDatabaseManager.createDatabase(databaseName);
        assertTrue(testDatabaseManager.getDatabaseByName(databaseName)!=null);
    }

}