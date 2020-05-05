package sample.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SocketClient {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private java.util.logging.Logger log;

    public SocketClient(String address, int port) throws IOException {
        socket = new Socket(address, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        log = Logger.getLogger();
    }

    public Object sendCommand(int cmd, Object params) throws Exception {
        if (params == null) params = "";
        HashMap<String, Object> command = new HashMap<String, Object>();
        command.put("cmd", cmd);
        command.put("value", params);
        sendData(command);
        Object ret = recvData();
        return ret;
    }

    private void sendData(HashMap<String, Object> data) throws IOException {
        String serialized = JSONArray.toJSONString(data);
        int length = serialized.length();
        byte[] lenByte = new byte[4]; // the length of the data that ready to send
        for (int i = 0; i < 4; i++) {
            // 64 = 100 0000, 8= 1000; 64 >> 8 = 000,1000
            lenByte[i] = (byte) ((length >> (i * 8)) & 0xff);
        }
        byte[] sentByte = serialized.getBytes(); // the data that ready to send
        outputStream.write(lenByte);
        outputStream.write(sentByte);
        outputStream.flush();
    }

    private Object recvData() throws Exception {
        HashMap<String, Object> deserialized = recvPackage();
        if((int)deserialized.get("status")!=0)
        {
            throw new Exception("Error code occured in RecvData");
        }
        return deserialized.get("data");
    }

    private HashMap<String, Object> recvPackage() throws IOException {
        byte[] lenByte = new byte[4]; // receive the length of data
        inputStream.read(lenByte, 0, 4);
        int length = (int) (
                (0xff & lenByte[0]) |
                        (0xff & lenByte[1]) << 8 |
                        (0xff & lenByte[2]) << 16 |
                        (0xff & lenByte[3]) << 24
        );

        byte[] readByte = new byte[length];

        log.info("ready to read length: " + length);
        int offset = 0;
        while(length > 0)
        {
            int readlength = inputStream.read(readByte, offset, length);
            log.info(String.valueOf(readlength));
            length -= readlength;
            offset += readlength;
        }

        String readStr = new String(readByte, StandardCharsets.UTF_8);
        log.info("\n"+readStr);
        return JSONObject.parseObject(readStr, HashMap.class);
    }
}
