package sample.utils;

public class Commands {
    public static int GET_VERSION = 100;            // 获取版本号
    public static int FIND_ELEMENTS = 101;          // 查找节点
    public static int FIND_ELEMENT_PATH = 102;      // 模糊查找
    public static int GET_ELEMENTS_BOUND = 103;     // 获取节点的位置信息
    public static int GET_ELEMENT_WORLD_BOUND = 104;// 获取节点的世界坐标
    public static int GET_UI_INTERACT_STATUS = 105;// 获取游戏的可点击信息，包括scene、可点击节点，及位置信息
    public static int GET_CURRENT_SCENE = 106;// 获取Unity的Scene名称
    public static int GET_ELEMENT_TEXT = 107;// 获取节点的文字内容
    public static int GET_ELEMENT_IMAGE = 108;// 获取节点的图片名称
    public static int GET_REGISTERED_HANDLERS = 109;// 获取注册的函数的名称
    public static int CALL_REGISTER_HANDLER = 110;// 调用注册的函数
    public static int SET_INPUT_TEXT = 111;// input控件更换文字信息
    public static int GET_OBJECT_FIELD = 112;// 通过反射获取gameobject中component的属性值
    public static int FIND_ELEMENTS_COMPONENT = 113;//获取所有包含改组件的gameobject
    public static int SET_CAMERA_NAME = 114;//设置渲染的最佳的Camera
    public static int GET_COMPONENT_METHODS = 115;// 反射获取组件上的方法
    public static int CALL_COMPONENT_MOTHOD = 116;// 通过反射调用组件的函数
    public static int LOAD_TEST_LIB = 117;//初始化testlib服务

    public static int PRC_SET_METHOD = 118;//注册python端的方法
    public static int RPC_METHOD = 119;//游戏内的接口可调用，python端的方法
    public static int RESTART_SENDTHREAD = 120;//

    public static int HANDLE_TOUCH_EVENTS = 200;// 发送down move up

    public static int DUMP_TREE = 300;//
}
