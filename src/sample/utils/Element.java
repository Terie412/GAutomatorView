package sample.utils;

import sample.Controller;

import java.io.IOException;

public class Element {
    public String objectName;
    public int instance;
    public ElementBound eb;

    public Element(String name, int instance) {
        this.objectName = name;
        this.instance = instance;
    }

    public void setElementBound() throws Exception {
        eb = Engine.getEngine("127.0.0.1", 53001, Controller.selected_serial, true).getElementBound(this);
    }

    public ElementBound getElementBound() throws Exception{
        if (eb == null)
            eb = Engine.getEngine("127.0.0.1", 53001, Controller.selected_serial, true).getElementBound(this);
        return eb;
    }

    @Override
    public String toString() {
        return String.format("GameObject %s Instance = %d", this.objectName, this.instance);
    }
}
