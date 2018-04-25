package SendElves;


import cn.gy4j.frame.utils.JSONUtils;
import com.alibaba.fastjson.JSON;

import javax.print.DocFlavor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
    private static String SYNC_CREATERT_URI="/api/v2/rt/exec";
    private static String SYNC_QUEUE_RESULT="/api/v2/queue/result";
    //private static String checkip="10.12.51.12";
    private static String checkip="115.182.1.41";
    public static void main(String[] args) {
        test1();
    }
    public static void test1(){
        Map<String, String> map = new HashMap<String, String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        map.put("start_time", "2018-04-23 00:00:00");
        //System.out.println(start_time);
        String url = SYNC_CREATERT_URI;
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("ip", checkip);
        paramsMap.put("func", "loginInfo");
        //paramsMap.put("func", "getpsInfo");
        paramsMap.put("param", JSON.toJSONString(map));
        paramsMap.put("proxy","python|app-worker.py");
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, url);
        System.out.println(resultMessage.toString());
    }

    public static void test2(){
        Map<String, String> map = new HashMap<String, String>();
        String url = SYNC_QUEUE_RESULT;
        Map<String, String> paramsMap = new HashMap<String, String>();
        String queueId = "A0737A67A5D61677";
        String authId = "AAB4D95DA15F7E86";
        paramsMap.put("queue_id", queueId);
        paramsMap.put("auth_id", authId);
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, url);
        Map<String, Object> rsMap = JSONUtils.parseObject(resultMessage, new HashMap<String, Object>().getClass());
        Map<String, Object> rsMap2= JSONUtils.parseObject(rsMap.get("result").toString(), new HashMap<String, Object>().getClass());
        Map<String, Object> rsMap3= JSONUtils.parseObject(rsMap2.get("935DABF6FEF1A7D6").toString(), new HashMap<String, Object>().getClass());
        System.out.println(rsMap3.get("worker_message"));
    }

    public  static void test3(){
        StringBuffer j = new StringBuffer();
        j.append("2222");
        j.append("\n");
        System.out.println(j.length());
        System.out.println(j.delete(j.length()-1,j.length()));
    }
}
