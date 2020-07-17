package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import sample.utils.Element;
import sample.utils.Engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    public ArrayList<Node> children;

    public String name;
    public String fullpath;
    public HashMap<String, String> attrs;
    public Element e;


    public Element getElement() throws Exception {
        if(e == null)e = Engine.getEngine().findElement(fullpath);
        return e;
    }

    @Override
    public String toString() {
        return name;
    }

    public String attrsInfo()
    {
        if(attrs == null)return "";
        StringBuilder ret = new StringBuilder();
        for(String key : attrs.keySet())
        {
            ret.append(key).append("=").append(attrs.get(key)).append("  ");
        }
//        for(String value : attrs.values())
//        {
//            ret.append(value);
//        }
        return ret.toString();
    }
}