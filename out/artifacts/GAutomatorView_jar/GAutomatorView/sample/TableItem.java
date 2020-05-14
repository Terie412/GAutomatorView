package sample;

import javafx.beans.property.SimpleStringProperty;

public class TableItem {
    public SimpleStringProperty key;
    public SimpleStringProperty value;


    public TableItem(String key, String value)
    {
        this.key = new SimpleStringProperty(key);
        this.value = new SimpleStringProperty(value);
//        this.key.set(key);
//        this.value.set(value);
    }

    public String getKey()
    {
        return this.key.get();
    }

    public String getValue()
    {
        return this.value.get();
    }

    public void setKey(String key)
    {
        this.key.set(key);
    }

    public void setValue(String value){
        this.value.set(value);
    }
}
