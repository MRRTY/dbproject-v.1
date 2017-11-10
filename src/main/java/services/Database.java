package services;

import exceptions.NoSuchTableException;
import exceptions.NotAllowedTableNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {
    private String name;
    private List<Table> tables;

    protected Database(String name) {
        this.name = name;
        tables = new ArrayList<>();

    }
    //Mock
    public void addTable(String tableName) throws NotAllowedTableNameException {
        if(!tables.stream().anyMatch(table -> table.getName().equals(tableName))){
            tables.add(new Table(tableName));
            System.out.println("Table "+ tableName+" creating...");
        }else {
            throw new NotAllowedTableNameException();
        }

    }
    //Mock
    public void removeTable(String tableName){
        if(tables.stream().anyMatch(table -> table.getName().equals(tableName))){
            Optional<Table> removingTable = tables.stream().findAny().filter(table -> table.getName().equals(tableName));
            tables.remove(removingTable.get());
            System.out.println("Table "+ tableName+" deleting...");
        }else {
            throw new NoSuchTableException();
        }
    }
    public Table getTableByName(String name){
        if (tables.stream().anyMatch(table -> table.getName().equals(name))) {
            return tables.stream().findAny().filter(table -> table.getName().equals(name)).get();
        } else {
            throw new NoSuchTableException();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
