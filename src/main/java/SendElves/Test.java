package SendElves;


import cn.gy4j.frame.utils.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.*;

class AclParam {
    private String ip;
    private String port;
    private String action;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

public class Test {
    private static String SYNC_AUTH_ID = "1BC23436ADCB4F0C";
    private static String SYNC_APP = "leader";
    private static String SYNC_AUTH_KEY = "FD4E8AB10130C651";

    private static String checkip="42.62.121.104";

    //private static String checkip="115.182.1.41";
    //private static String checkip="10.12.51.12";
    public static void test1(){
        Map<String, String> map = new HashMap<String, String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        map.put("start_time", "2018-04-23 00:00:00");
        //System.out.println(start_time);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("ip", checkip);
        paramsMap.put("func", "loginInfo");
        //paramsMap.put("func", "getpsInfo");
        paramsMap.put("param", JSON.toJSONString(map));
        paramsMap.put("proxy","python|app-worker.py");
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        System.out.println(resultMessage.toString());
    }

    public static void test2(){
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> paramsMap = new HashMap<String, String>();
        String queueId = "A0737A67A5D61677";
        String authId = "AAB4D95DA15F7E86";
        paramsMap.put("queue_id", queueId);
        paramsMap.put("auth_id", authId);
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_QUEUE_RESULT").getUri());
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

    public static  void test4(){//游戏管理工具 维护acl
        ArrayList<AclParam> aclList = new ArrayList();
        AclParam a = new AclParam();
        a.setIp("221.228.201.24/32");
        a.setPort("8081:8082");
        a.setAction("DROP");
        aclList.add(a);

        a = new AclParam();
        a.setIp("221.228.201.108/32");
        a.setPort("8081");
        a.setAction("ACCEPT");
        aclList.add(a);

        a = new AclParam();
        a.setIp("211.159.151.131/32");
        a.setPort("8082");
        a.setAction("ACCEPT");
        aclList.add(a);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("func", "add_service_iptables");
        paramsMap.put("ip",checkip);
        paramsMap.put("param",JSON.toJSONString(aclList));


        // 调用接口，发送数据到
        String result= SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        Map<String,JSONObject> map = JSON.parseObject(result,new HashMap().getClass());
        String message = map.get("result").get("worker_message").toString();
        Map<String,String> map2 = JSON.parseObject(message,new HashMap().getClass());
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(map2.get("msg")));
        String m ="";
        for(Object obj:jsonArray){
            m += obj.toString()+"<br/>";
        }
        System.out.println(m);
    }

    public static void test5(){
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("func", "change_asktao_time");
        paramsMap.put("ip",checkip);
        paramsMap.put("timeout","500");
        Map<String, String> map = new HashMap<>();
        map.put("newtime","2018-01-01 15:00:00");
        paramsMap.put("param",JSON.toJSONString(map));


        // 调用接口，发送数据到
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String result= SendElvesUtil.sendElvesOpenApi(paramsMap,SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        System.out.println(result);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //Map<String,JSONObject> map = JSON.parseObject(result,new HashMap().getClass());
        //String message = map.get("result").get("worker_message").toString();
        //Map<String,String> map2 = JSON.parseObject(message,new HashMap().getClass());
        //JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(map2.get("msg")));
        //String m ="";
        //for(Object obj:jsonArray){
        //    m += obj.toString()+"<br/>";
        //}
        System.out.println(result);
    }

    public static void test6(){//创建队列任务// 调用接口，发送数据到

        String message= SendElvesUtil.sendElvesOpenApi(null,SendElvesEnum.valueOf("SYNC_CREATEQUEUE_URI").getUri() );
        String queueId="";
        if(StringUtils.isNotBlank(message)){
            Map<String,String> map = JSON.parseObject(message,new HashMap().getClass());
            if(map.get("flag") != null && "true".equals(map.get("flag"))){
                queueId = map.get("id");  //获取queueId
            }
        }

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("func", "change_asktao_time");
        paramsMap.put("ip",checkip);
        paramsMap.put("queue_id",queueId);
        paramsMap.put("mode","NP");
        Map<String, String> map = new HashMap<>();
        map.put("newtime","2018-01-01 15:00:00");
        paramsMap.put("param",JSON.toJSONString(map));
        message= SendElvesUtil.sendElvesOpenApi(paramsMap,SendElvesEnum.valueOf("SYNC_ADDTASK_URI").getUri() );

        System.out.println(queueId);

        paramsMap = new HashMap<>();
        paramsMap.put("queue_id",queueId);
        message= SendElvesUtil.sendElvesOpenApi(paramsMap,SendElvesEnum.valueOf("ELVES_COMMIT_URI").getUri());
        System.out.println(message);


        paramsMap = new HashMap<>();
        paramsMap.put("queue_id",queueId);
        message= SendElvesUtil.sendElvesOpenApi(paramsMap,SendElvesEnum.valueOf("ELVES_RESULT_URI").getUri());
        System.out.println(message);


    }
    public  static void test7(){//获取队列结果 //49A867331DAD978B//EC5983DD2A9015AE
        String queueId ="060910A451AE21AF";
        String taskId="D49E4200A0B3BD67";
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("queue_id",queueId);
        String message= SendElvesUtil.sendElvesOpenApi(paramsMap,SendElvesEnum.valueOf("ELVES_RESULT_URI").getUri());
        System.out.println(message);
        Map<String,String> map = JSON.parseObject(message,new HashMap().getClass());
        if(StringUtils.isNotBlank(map.get("flag").toString()) && map.get("flag").toString().equals("true")){
            Map<String,JSONObject> map2 = JSON.parseObject(JSON.toJSONString(map.get("result")),new HashMap().getClass());
            Map<String,String> map3 = JSON.parseObject(map2.get(taskId).toJSONString(),new HashMap().getClass());
            if(map3.get("status").toString().equals("finish")){
                Map<String,Object> map4 = JSON.parseObject(map3.get("worker_message"),new HashMap().getClass());
                System.out.println(map4.get("code"));
                System.out.println(map4.get("msg"));
            }
        }

    }
    public static void test8(){
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("func", "change_asktao_time");
        paramsMap.put("ip",checkip);
        paramsMap.put("mode","NP");
        paramsMap.put("timeout","200");
        Map<String, String> map = new HashMap<>();
        map.put("newtime","2018-01-01 15:00:00");
        paramsMap.put("param",JSON.toJSONString(map));
        String message= SendElvesUtil.sendElvesOpenApi(paramsMap,SendElvesEnum.valueOf("ELVES_CREATEQKSQUEUE_URI").getUri() );
        if(StringUtils.isNotBlank(message)){
            map = JSON.parseObject(message,new HashMap().getClass());
            if(map.get("flag") != null && "true".equals(map.get("flag"))){
                //提交任务成功
                Map<String,String>map2 = JSON.parseObject(JSON.toJSONString(map.get("result")),new HashMap().getClass());
                System.out.println(map2.get("id"));
                System.out.println(map2.get("task_id"));
            }
        }
        System.out.println(message);
    }

    public static void main(String[] args) {
        SendElvesUtil.setSyncApp(SYNC_APP);
        SendElvesUtil.setSyncAuthId(SYNC_AUTH_ID);
        SendElvesUtil.setSyncAuthKey(SYNC_AUTH_KEY);
        test5();
        //test7();
    }
}


