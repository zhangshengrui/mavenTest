package weixin;

import SendElves.SendElvesUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WeiXinTest {
    public static String CORP_ID = "wwff9180098efe6d03";
    public static String CORP_SECRET = "5bQMOZ559LEf4AXNB78KaHrEHy7mPK3sT3JuBvho_gE";
    public static String AGENT_ID = "1000005";


    public static void main(String[] args) {
        System.out.println(getToken());
    }

    public static String getToken(){
        Map<String,String> map = new HashMap();
        map.put("corpid", CORP_ID);
        map.put("corpsecret", CORP_SECRET);
        String result = SendElvesUtil.sendGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken",map);
        JSONObject jsonObj = JSON.parseObject(result);
        String access_token = jsonObj.getString("access_token");
        //System.out.println(access_token);
        return access_token;
    }

    public static String test2(){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+getToken()+"&userid=1";
        String result = SendElvesUtil.sendGet(url,null);
        return result;
    }

    public static void test3(){
        String token_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+getToken();
        Map<String,Object> map = new HashMap();
        Map<String,String> map1 = new HashMap();
        map1.put("title", "客服反馈");
        map1.put("description", "发现时间:");
        map1.put("url", "www.baidu.com");
        map.put("touser", "@all");
        map.put("msgtype", "textcard");
        map.put("agentid", AGENT_ID);
        map.put("textcard",map1);
        JSONObject jsonObj = HttpRequestUtil.httpRequest(token_url,"POST", JSON.toJSONString(map));
        Integer code = jsonObj.getInteger("errcode");
        System.out.println(code);
    }
}
