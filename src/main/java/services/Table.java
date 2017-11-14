package services;

import exceptions.*;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Table implements Serializable {
    private String name;
    private List<Column> columns;
    private List<Row> rows;

    private Table(String tableName, List<Column> columns) {
        this.name = tableName;
        this.columns = columns;
        rows = new ArrayList<Row>();
    }

    public void addColumn(Column column){
        if(!columns.stream().findAny().filter(column1 -> column1.getName().equals(column.getName())).isPresent()){
            columns.add(column);
            System.out.println("Column "+ column.getName()+" added in to "+ name+" table");
        }else {
            throw new NotAllowedColumnNameException();
        }
    }
    //Mock
    public void addRow(Row row){
        Cell primaryCell = row.getCells().stream().findAny().filter(cell -> cell.getColumn().isPrimaryKey()).get();
        if(rows.stream().map(row1 ->
                findPrimaryCell(row1)).anyMatch(cell ->
                cell.getValue().equals(primaryCell.getValue()))) {
            rows.add(row);
        }else {
            throw new RowWithSuchPrimaryKeyAlreadyExistsException();
        }
    }
    public List<Row> selectRowByColumnAndValue(Map<Column,Object> map){
        List<Row> result = new ArrayList<>();
        List<Row> tempTable = new ArrayList<>(rows);
        map.forEach((Column c, Object o)-> {
           result.remove(tempTable.stream().findAny().filter(row -> !(row.getCellByColumn(c).getValue().equals(o))).get());
        });
        return result;
    }
    private Cell findPrimaryCell(Row row){
        return row.getCells().stream().findAny().filter(cell -> cell.getColumn().isPrimaryKey()).get();
    }

    public Column getColumnByName(String name){
        if(columns.stream().anyMatch(column -> column.getName().equals(name))){
            return columns.stream().findAny().filter(column -> column.getName().equals(name)).get();
        }else {
            throw new NoSuchColumnException();
        }
    }
    public Row getRowByIndex(int index){
        if(rows.get(index)!=null){
            return rows.get(index);
        }else {
            throw new NoSuchRowException();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder{
        private List<Column> builderColumn = new ArrayList<>();
        private String name;

        public Builder setTableName(String name){
            this.name = name;
            return this;
        }
        public Builder addColumn(Column column){
            builderColumn.add(column);
            return this;
        }
        public Table build(){
            if(builderColumn.size()<1 || name==null){
                throw  new InvalidTableException();
            }
            return new Table(this.name,this.builderColumn);
        }

    }
}
