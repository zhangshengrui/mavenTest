package SendOperation;


import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.URLEncoder;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static String SYNC_CREATERT_URI="createVirtualMachine";
    private static String SYNC_CREATERT_URI2="updateVirtualMachine";
    private static String SYNC_CREATERT_URI3="deleteVirtualMachine";
    public static void main(String[] args) {
        test1();
    }
    public static void test1(){
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("ip","192.168.8.236");
        paramsMap.put("name","test");
        paramsMap.put("gysn","AM1012");
        paramsMap.put("idc_id","90");
        paramsMap.put("os","CentOS5.3");
        paramsMap.put("flag","1");
        paramsMap.put("virtual","3457");
        paramsMap.put("manager","TEST");
        paramsMap.put("atype","6");
        paramsMap.put("oper_user","测试中文");
        paramsMap.put("update_date","2018-04-19");
        paramsMap.put("main_id","68");
        paramsMap.put("ips","192.168.1.1;192.168.1.2;");
        paramsMap.put("app_memo","23131");
        try {
            paramsMap.put("setting", URLEncoder.encode("CPU*2/MEM*2G/DISK*10G + 20G", "utf-8"));
        }catch (java.io.UnsupportedEncodingException e){
            e.printStackTrace();
        }
        //String url = SYNC_CREATERT_URI;
        Map<String, String> map = new HashMap();
        map.put("param", JSON.toJSONString(paramsMap));
        //String resultMessage = SendElvesUtil.sendElvesOpenApi(map, url);

        String url = SYNC_CREATERT_URI2;
        String resultMessage = SendElvesUtil.sendElvesOpenApi(map, url);
        //System.out.println(resultMessage);

        //String url = SYNC_CREATERT_URI3;
        //String resultMessage = SendElvesUtil.sendElvesOpenApi(map, url);
        //System.out.println(resultMessage);
    }
}
