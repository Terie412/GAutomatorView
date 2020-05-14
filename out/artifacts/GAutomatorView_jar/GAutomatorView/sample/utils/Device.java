package sample.utils;

import java.io.IOException;

public class Device {
    public static void forward(int port, String serial) throws IOException {
        String cmd = String.format("forward tcp:%d tcp:27019",port);
        String ret = ShellUtils.execAdb(cmd, serial);
        System.out.println(ret);
    }

    public static void screenshot(String serial) throws IOException {
        String cmd = "shell screencap -p /sdcard/screen.png";
        String ret = ShellUtils.execAdb(cmd, serial);
        cmd = "pull /sdcard/screen.png D:/pictures/screenshot.png";
        ret = ShellUtils.execAdb(cmd, serial);
    }
}
