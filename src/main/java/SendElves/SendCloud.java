package SendElves;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SendCloud {

    private static String SYNC_AUTH_ID = "060A2FDAEEB061C8";
    private static String SYNC_APP = "wizard";
    private static String SYNC_AUTH_KEY = "9B01D07C2500749C";
    public static void main(String[] args) {
        SendElvesUtil.setSyncApp(SYNC_APP);
        SendElvesUtil.setSyncAuthId(SYNC_AUTH_ID);
        SendElvesUtil.setSyncAuthKey(SYNC_AUTH_KEY);
        test1();
    }
    public static void test1(){
        String ip = "42.62.121.103";
        String function = "destroy_net_port_set";
        //参数
        Map<String, String> param = new HashMap();
        param.put("name", "business");
        String params = JSON.toJSONString(param);
        Map<String, String> paramsMap = new HashMap();
        paramsMap.put("ip", ip);
        paramsMap.put("func", function);
        paramsMap.put("param",params);
        // 调用接口，发送数据到
        String result = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        System.out.println(result);

        //JSONObject jsonObject = JSON.parseObject(result);
        //jsonObject = JSON.parseObject(jsonObject.get("result").toString());
    }
}
