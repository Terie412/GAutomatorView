package sample.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellUtils {
    private static java.util.logging.Logger log;
    public static String execAdb(String cmd, String serial) throws IOException {
        if(log == null)log = Logger.getLogger();

        String command = "";
        if(serial == "" || serial == null){
            command = String.format("adb %s", cmd);
        }else{
            command = String.format("adb -s %s %s",serial,cmd);
        }

        log.info("execute: " + command);

        Process process = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder ret = new StringBuilder();
        String line = br.readLine();
        while(line != null){
            if(ret.length() == 0)ret.append(line);
            else ret.append("\n").append(line);
            line = br.readLine();
        }
        return ret.toString();
    }

}
