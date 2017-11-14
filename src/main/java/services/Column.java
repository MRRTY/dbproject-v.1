package services;

import services.enums.SqlType;

import java.io.Serializable;

public class Column implements Serializable {
    private boolean isPrimaryKey;
    private String name;
    private SqlType type;

    public Column(boolean isPrimaryKey, String name, SqlType type) {
        this.isPrimaryKey = isPrimaryKey;
        this.name = name;
        this.type = type;
    }

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

    public SqlType getType() {
        return type;
    }

    public void setType(SqlType type) {
        this.type = type;
    }
}
