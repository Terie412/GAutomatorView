package sample;

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
        if(e == null)e = Engine.getEngine("127.0.0.1",53001, Controller.selected_serial,true).findElement(fullpath);
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
        for(String value : attrs.values())
        {
            ret.append(value);
        }
        return ret.toString();
    }
}