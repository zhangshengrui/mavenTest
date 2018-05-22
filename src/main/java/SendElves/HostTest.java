package SendElves;


import java.util.HashMap;
import java.util.Map;

public class HostTest {
    private static String SYNC_CREATERT_URI="/api/v2/rt/exec";
    private static String SYNC_QUEUE_RESULT="/api/v2/queue/result";
    //private static String checkip="10.15.10.123";
    private static String checkip="10.15.10.122";
    private static String agentIp="192.168.4.150";
    public static void main(String[] args) {
        test1();
    }
    public static void test1(){
        //获取当前host
        String paramStr = "{\"clusterDir\":\"/data/TestBusiness/TomcatTestCluster\"}";
        Map<String, String> paramsMap = new HashMap<String, String>();
        Map<String, String> map = new HashMap<String, String>();
        String url = SYNC_CREATERT_URI;
        paramsMap.put("ip", checkip);
        paramsMap.put("func", "getHostByClusterDir");
        //paramsMap.put("func", "getpsInfo");
        paramsMap.put("param", paramStr);
        paramsMap.put("mode","np");
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        System.out.println(resultMessage.toString());
    }
}
