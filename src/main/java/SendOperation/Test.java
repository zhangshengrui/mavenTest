package SendOperation;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static String SYNC_CREATERT_URI="createVirtualMachine";
    public static void main(String[] args) {
        test1();
    }
    public static void test1(){
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("ip","192.168.8.236");
        String url = SYNC_CREATERT_URI;
        Map<String, String> map = new HashMap();
        map.put("param", JSON.toJSONString(paramsMap));
        String resultMessage = SendElvesUtil.sendElvesOpenApi(map, url);
        System.out.println(resultMessage);
    }
}
