package SendElves;


import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

        String msg = "{\"{start_time\": \"2017-05-30 12:42:00\"}";
        System.out.println(JSON.parseObject(msg));
    }

    public static void test1(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("start_time", "2018-04-23 00:00:00");
        System.out.println(JSON.toJSONString(map));
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("ip", checkip);
        paramsMap.put("func", "loginInfo");
        //paramsMap.put("param", JSON.toJSONString(map));
        paramsMap.put("param", "{\"{start_time\": \"2017-05-30 12:42:00\"}");
        paramsMap.put("proxy","python|app-worker.py");
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        System.out.println(resultMessage.toString());
    }

}
