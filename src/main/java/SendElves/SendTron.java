package SendElves;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

public class SendTron {
    private static String SYNC_AUTH_ID = "AAB4D95DA15F7E86";
    private static String SYNC_APP = "webops";
    private static String SYNC_AUTH_KEY = "40A2F680F77F988E";
    public static void main(String[] args) {
        SendElvesUtil.setSyncApp(SYNC_APP);
        SendElvesUtil.setSyncAuthId(SYNC_AUTH_ID);
        SendElvesUtil.setSyncAuthKey(SYNC_AUTH_KEY);
        test1( );
    }
    public static void test1(){
        Map<String, String> paramsMap = new HashMap();
        paramsMap.put("ip", "10.0.0.64");
        paramsMap.put("func", "getDBSMDelayByDBName");
        Map<String, String> map = new HashMap();
        map.put("dbName","g123123");
        paramsMap.put("param", JSON.toJSONString(map));
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        JSONObject jsonObject = JSON.parseObject(resultMessage);
        String message="";
        if(jsonObject.get("flag") != null){  //是否含有flag字段
            if(jsonObject.get("flag").equals("true")|| jsonObject.get("result") != null){ //flag字段为true
                jsonObject = JSON.parseObject(jsonObject.get("result").toString()); //获取result部分
                message = jsonObject.get("worker_message") == null?"":jsonObject.get("worker_message").toString();
                if(jsonObject.get("worker_flag") != null && "1".equalsIgnoreCase(jsonObject.get("worker_flag").toString())){ //脚本执行成功
                    jsonObject = JSON.parseObject(message.replace("u'","'")); //解析结果
                    System.out.println(jsonObject);
                    System.out.println(jsonObject.get("Master"));
                }
            }else{
                message = jsonObject.get("error") == null?"":jsonObject.get("error").toString();
            }
        }else{
            System.out.println(message);
        }
    }
}
