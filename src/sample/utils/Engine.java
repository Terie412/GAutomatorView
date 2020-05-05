package sample.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class Engine {
    private SocketClient socketClient;
    public static Engine engine;
    private String address;
    private int port;
    private String serial;

    public Engine(String address, int port, String serial) throws IOException {
        Device.forward(port, serial);
        socketClient = new SocketClient(address, port);
    }

    public static Engine getEngine()
    {
        return engine;
    }

    public Element findElement(String path) throws Exception {
        ArrayList<String> paths = new ArrayList<>();
        paths.add(path);
        JSONArray rets = (JSONArray) socketClient.sendCommand(Commands.FIND_ELEMENTS, paths);
        JSONObject ret = rets.getJSONObject(0);
        HashMap<String, Object> map = JSONObject.parseObject(ret.toJSONString(), HashMap.class);
        if ((int) map.get("instance") == -1) return null;
        return new Element((String) ret.get("name"), (int) ret.get("instance"));
//        return null;
    }

    public ElementBound getElementBound(Element e) throws Exception {
        ArrayList<Integer> params = new ArrayList<Integer>();
        params.add(e.instance);

        JSONArray ret = (JSONArray) socketClient.sendCommand(Commands.GET_ELEMENTS_BOUND, params);
        JSONObject obj = ret.getJSONObject(0);
        HashMap<String, Object> map = JSONObject.parseObject(obj.toJSONString(), HashMap.class);
        boolean existed = (boolean) map.get("existed");
        if (!existed) return null;
        ElementBound eb =  new ElementBound(
                Float.parseFloat(map.get("x").toString()),
                Float.parseFloat(map.get("y").toString()),
                Float.parseFloat(map.get("width").toString()),
                Float.parseFloat(map.get("height").toString()),
                (boolean) map.get("visible"));
        e.eb = eb;
        return eb;
    }

    public String getScene() throws Exception {
        String ret = (String) socketClient.sendCommand(Commands.GET_CURRENT_SCENE, "");
        return ret;
    }

    public String getClickableNodes() throws Exception {
        String ret = (String) socketClient.sendCommand(Commands.GET_UI_INTERACT_STATUS, "");
        return ret;
    }

    public String getDumpTree() throws Exception {
        JSONObject obj = (JSONObject) socketClient.sendCommand(Commands.DUMP_TREE, "");
        String ret = obj.toJSONString();
        HashMap<String, Object> map = JSONObject.parseObject(ret, HashMap.class);
        String xmlStr = (String) map.get("xml");
        return xmlStr;
    }

    public ArrayList<Element> getTouchableElements() throws Exception {
        Object ret = socketClient.sendCommand(Commands.GET_UI_INTERACT_STATUS, null);
        ArrayList<Element> elements = new ArrayList<>();
        if (ret == null) return elements;
        String jsonStr = ((JSONObject) ret).toJSONString();
        HashMap<String, Object> map = JSONObject.parseObject(jsonStr, HashMap.class);
        JSONArray array = (JSONArray) map.get("elements");
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            HashMap<String, Object> node = JSONObject.parseObject(obj.toJSONString(), HashMap.class);
            Element e = new Element((String) node.get("name"), (int) node.get("instanceid"));
            elements.add(e);
        }
        return elements;
    }
}
