package services;

import exceptions.NoSuchTableException;
import exceptions.NotAllowedTableNameException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    Database testDatabase = new Database("asdf");


    @Test
    public void addSingleTable() {
    testDatabase.addTable("test");
    assertTrue(testDatabase.getTableByName("test")!= null);
    testDatabase.removeTable("test");
    }

    @Test(expected = NotAllowedTableNameException.class)
    public void addTableWitchExists(){
        testDatabase.addTable("test");
        testDatabase.addTable("test");
    }

    @Test
    public void removeSingleTable(){
        testDatabase.addTable("test");
        testDatabase.removeTable("test");
    }
    @Test(expected = NoSuchTableException.class)
    public void removeTableWitchIsNotExists(){
        testDatabase.removeTable("any");

    }
    @Test
    public void getSingleTableByName(){
        testDatabase.addTable("test");
        assertTrue(testDatabase.getTableByName("test")!=null);
    }
}