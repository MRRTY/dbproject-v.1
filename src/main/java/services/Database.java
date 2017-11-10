package services;

import exceptions.NoSuchTableException;
import exceptions.NotAllowedTableNameException;
import services.enums.SqlType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Database {
    private String name;
    private List<Table> tables;

    protected Database(String name) {
        this.name = name;
        tables = new ArrayList<>();

    }
    //Mock
    public void addTable(String tableName, Map<String,SqlType> columns, String primaryKey) throws NotAllowedTableNameException {
        if(!tables.stream().anyMatch(table -> table.getName().equals(tableName)) && columns !=null){
            Table.Builder builder = new Table.Builder();
            builder.setTableName(tableName);
            columns.forEach((String name, SqlType type) ->{

                    builder.addColumn(new Column(primaryKey.equals(name),name,type));

            });
            Table table = builder.build();
            table.create(this);
            tables.add(table);
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
