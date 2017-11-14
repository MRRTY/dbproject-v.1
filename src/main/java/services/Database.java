package services;

import exceptions.NoSuchTableException;
import exceptions.NotAllowedTableNameException;
import services.enums.SqlType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Database implements Serializable {
    private String name;
    private List<Table> tables;

    public static Database loadFromFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Database database = (Database) ois.readObject();
        ois.close();
        return database;
    }
    protected Database(String name) {
        this.name = name;
        tables = new ArrayList<>();

    }

    public void addTable(String tableName, Map<String,SqlType> columns, String primaryKey) throws NotAllowedTableNameException {
        if(!tables.stream().anyMatch(table -> table.getName().equals(tableName)) && columns !=null){
            Table.Builder builder = new Table.Builder();
            builder.setTableName(tableName);
            columns.forEach((String name, SqlType type) ->{

                    builder.addColumn(new Column(primaryKey.equals(name),name,type));

            });
            Table table = builder.build();
            tables.add(table);
            System.out.println("Table "+ tableName+" creating...");
        }else {
            throw new NotAllowedTableNameException();
        }

    }

    public void removeTable(String tableName){
        if(tables.stream().anyMatch(table -> table.getName().equals(tableName))){
            Table removingTable = tables.stream().findAny().filter(table -> table.getName().equals(tableName)).get();
            tables.remove(removingTable);
            System.out.println("Table "+ tableName+" deleting...");
        }else {
            throw new NoSuchTableException();
        }
    }

    public void localSave(String path) throws IOException {
        FileOutputStream fs = new FileOutputStream(path+this.name);
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(this);
        os.flush();
        os.close();
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
