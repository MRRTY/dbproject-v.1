package services;

import exceptions.NoSuchColumnException;
import exceptions.NoSuchRowException;
import exceptions.NotAllowedColumnNameException;
import exceptions.RowWithSuchPrimaryKeyAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Table {
    private String name;
    private List<Column> columns;
    private List<Row> rows;

    public Table(String tableName) {
        this.name = tableName;
        columns = new ArrayList<Column>();
        rows = new ArrayList<Row>();
    }
    //Mock
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
}
