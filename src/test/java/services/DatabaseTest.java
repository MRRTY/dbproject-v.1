package services;

import exceptions.NoSuchTableException;
import exceptions.NotAllowedTableNameException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    Database testDatabase = new Database("asdf");






    @Test(expected = NoSuchTableException.class)
    public void removeTableWitchIsNotExists(){
        testDatabase.removeTable("any");

    }

}