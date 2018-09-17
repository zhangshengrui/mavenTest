package SendElves;

import cn.gy4j.frame.utils.MD5Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import weixin.HttpRequestUtil;

import java.util.*;

/**
 * @Title: SendFaultGo
 * @Description:
 * @author zhangshengrui@gyyx.cn
 * @date 2018/7/24 16:51
 */
public class SendFaultGo {
    public static void main(String[] args) {
        //test1();
        String msg = "{'msg': u'\\\\u4e0b\\\\u4e00\\\\u8df3\\\\u4e0d\\\\u662f\\\\u516c\\\\u7f51\\\\u7f51\\\\u5173\\\\u548c\\\\u4e91\\\\u4e3b\\\\u673a\\\\u65f6\\\\uff0c\\\\u76ee\\\\u7684\\\\u7aef\\\\u4e0d\\\\u80fd\\\\u76f8\\\\u540c\\\\uff0c\\\\u8bf7\\\\u68c0\\\\u67e5\\\\u5df2\\\\u6709\\\\u8def\\\\u7531\\\\u7b56\\\\u7565', 'code': 0}";
        //String msg = "{'msg': u'success', 'code': 0}";
        //msg = msg.replace("u'","'");
        JSONObject jsonObject = JSON.parseObject(msg);
        System.out.println(decodeUnicode(jsonObject.get("msg").toString()));

    }
    public static String decodeUnicode(String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public static void test1(){
        //String url = "http://fault-go.test.gyyx.cn/applicationInfoStorage";
        String url = "http://localhost:8083/applicationInfoStorage";
        //String url = "http://fault-go.gyyx.cn/applicationInfoStorage";
        String authKey = "wx6c13ec2f4ee7b2d2";
        Map<String,String> map = new TreeMap<>();
        //必填参数
        map.put("auth_id","wx6c13ec2f4ee7b2d2");
        map.put("app_id","1");
        map.put("timestamp",System.currentTimeMillis()/1000+"");
        map.put("title","使用说明");
        map.put("app_name","故障管理");
        map.put("simple_text",jdkAES("..."));

        //非必填参数
        map.put("detailed_text","");
        map.put("links","");
        map.put("mark","[1,2]");

        //附加子业务参数
        Map<String,String> appJson = new HashMap<>();
        appJson.put("fault_id","2");
        appJson.put("find_time", "2018-07-27 10:41:00");
        map.put("app_json", jdkAES(JSON.toJSONString(appJson)));

        //自然排序
        String str="";
        for (String s : map.keySet()) {
            str += s + "=" + map.get(s) + "&";
        }

        //增加uri 拼接authKey
        if (str.length() > 0){
            str = str.substring(0,str.length()-1);
            str =  "/applicationInfoStorage?" + str;
            str += authKey;
        }

        //加密
        map.put("sign", MD5Utils.MD5(str));

        //System.out.println("get"+SendElvesUtil.sendGet(url,map));
        System.out.println("post"+SendElvesUtil.sendPost(url,map));
    }

    public static  String jdkAES (String msg){
        String result = new String(Base64.encodeBase64(msg.getBytes()));
        result = result.replace("+","-");
        result = result.replace("/","_");
        //System.out.println(result);
        return result;
    }

    public static void test3(){
        //String url = "http://fault-go.gyyx.cn/applicationInfoStorage";
        //String url = "http://fault-go.test.gyyx.cn/applicationInfoStorage";
        //String url = "http://192.168.8.209:80/applicationInfoStorage";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("corpid", "wwff9180098efe6d03");
        map.put("corpsecret", "5bQMOZ559LEf4AXNB78KaHrEHy7mPK3sT3JuBvho_gE");
        String result = HttpRequestUtil.sendGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken",map);
        JSONObject jsonObj = JSON.parseObject(result);
        String access_token = jsonObj.getString("access_token");
        System.out.println(access_token);
        String code = "FzDkmft9Z1TDO5GV3ngSyQUH_2gq3MHxSTIGn3WzPZU&state";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+access_token+"&code="+code+"";
        String json = SendElvesUtil.sendPost(url,null);
        System.out.println(json);
    }

    public static void test4(){
        Map<String,String> map = new HashMap();
        map.put("id","37");
        //map.put("action_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("monitor_flag","1");
        map.put("monitor_status","疑似");
        map.put("action_person","疑似");
        //String url = "http://incident.test.oa.gyyx.cn/index.php/MyCenter/Monitor/ResponseTime";
        String url = "http://incident.test.oa.gyyx.cn/index.php/MyCenter/Monitor/ResponseData";
        String result = SendElvesUtil.sendPost(url,map);
        System.out.println(result);
    }
}
