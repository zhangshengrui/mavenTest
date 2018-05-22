package SendElves;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class OdinElves {

    private static String SYNC_AUTH_ID = "DDC798072101970B";
    private static String SYNC_APP = "odin";
    private static String SYNC_AUTH_KEY = "65D491CF41C28F46";

    private static String checkip="39.106.155.10";

    public static void main(String[] args) {
        SendElvesUtil.setSyncApp(SYNC_APP);
        SendElvesUtil.setSyncAuthId(SYNC_AUTH_ID);
        SendElvesUtil.setSyncAuthKey(SYNC_AUTH_KEY);
        test1();
    }

    public static void test1(){

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("ip", checkip);
        paramsMap.put("func", "getstartupinfo");
        paramsMap.put("proxy","python|app-worker.py");

        //Map<String, String> map = new HashMap<String, String>();
        //paramsMap.put("param", JSON.toJSONString(map));

        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        System.out.println(resultMessage);
    }

}
