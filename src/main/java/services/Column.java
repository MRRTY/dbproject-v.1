package services;

public class Column<T> {
    private boolean isPrimaryKey;
    private String name;
    private T type;

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

}
