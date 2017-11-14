package services;

import java.io.Serializable;

public class Cell<T> implements Serializable{
    private Column column;
    private T value;

    public Cell(Column column, T value) {
        this.column = column;
        this.value = value;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  value.toString();
    }

    public void setValue(T value) {
        this.value = value;
    }
}
