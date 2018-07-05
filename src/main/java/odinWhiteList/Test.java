package odinWhiteList;


import SendElves.SendElvesEnum;
import SendElves.SendElvesUtil;
import com.alibaba.fastjson.JSONObject;
import cn.gy4j.frame.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Test {
    private static String SYNC_AUTH_ID = "DDC798072101970B";
    private static String SYNC_APP = "odin";
    private static String SYNC_AUTH_KEY = "65D491CF41C28F46";

    private static String checkip="221.228.201.91";
    public static void main(String[] args) {
        test2();
        //test3();
    }

    public static boolean checkVagueBusinessList(Startup startup,StartupWhiteList startupWhiteList){
        try {
            String[] fbsArr = { "\\", "$", "(", ")", "+", "[", "]", "?", "^", "{", "}", "|" };
            String content = startup.getStartupName().replace(" ","");
            String pattern = startupWhiteList.getBoot().replace(" ","");
            for (String key : fbsArr) {
                if (pattern.contains(key)) {
                    pattern = pattern.replace(key, "\\" + key);
                }
            }

            if(StringUtils.isBlank(pattern)){
                return false;
            }
            if(pattern.indexOf("*") != -1){    //包含* 属于模糊匹配 适配正则表达式
                if(pattern.startsWith("*")){
                    if(pattern.endsWith("*")){
                        //以*开头 以*结尾
                        //*aa*    --     .*aa.*
                        pattern = pattern.substring(1,pattern.length() -1).replace("*",".*");
                        pattern = ".*" + pattern + ".*";
                    }else {
                        //以*开头
                        // *aa    -- .*aa$
                        pattern = pattern.substring(1).replace("*",".*");
                        pattern = ".*" + pattern + "$";
                    }
                }else if(pattern.endsWith("*")){
                    //以*结尾
                    // aa*     --  ^aa.*
                    pattern = "^"+pattern.substring(0,pattern.length()-1).replace("*",".*")+".*";
                }else{
                    // aa*bb   --  aa.*bb
                    pattern = pattern.replace("*",".*");
                }
            }
            return Pattern.matches(pattern, content);
        } catch (java.util.regex.PatternSyntaxException e){
            return false;
            //字符转义异常
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static void test1(){
        // 字符*
        /*String content = "dbkkkkkk";
        String pattern = "^db.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("是否满足 字符* : " + isMatch);

        // 字符.
        content = "dbk";
        pattern = "^db.";
        isMatch = Pattern.matches(pattern, content);
        System.out.println("是否满足 字符. : " + isMatch);

        // *字符
        content = "ggggdb";
        pattern = ".*db$";
        isMatch = Pattern.matches(pattern, content);
        System.out.println("是否满足 *字符  : " + isMatch);

        // .字符
        content = "gdb";
        pattern = ".db$";
        isMatch = Pattern.matches(pattern, content);
        System.out.println("是否满足 .字符  : " + isMatch);

        // [*] # 系统进程
        content = "[dbfff]";
        pattern = "^\\[.*\\]$";
        isMatch = Pattern.matches(pattern, content);
        System.out.println("是否满足 [*]字符  : " + isMatch);*/

        /*String appName = "1cs*";
        System.out.println(appName.substring(appName.length()-1));
        System.out.println(appName.substring(0,1));
        System.out.println(appName.indexOf("."));*/

        String app_name = "*java*";
        if(!app_name.substring(0,1).equals("*") && !app_name.substring(0,1).equals(".") ){
            app_name = "^"+app_name;
        }
        if(!app_name.substring(app_name.length()-1).equals("*") && !app_name.substring(app_name.length()-1).equals(".")){
            app_name =app_name+"$";
        }
        if(app_name.contains("*")){
            app_name =app_name.replace("*", ".*");
        }

        String content = "/usr/java/jdk1.7.0_67-cloudera/bin/java";
//		String content = "java/usr/java/jdk1.7.0_67-cloudera/bin/";
//		String pattern = ".*java$";
        String pattern = app_name;
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("是否满足 *字符  : " + isMatch);

        /*String str = "*.db";
        System.out.println(str.substring(0,1).equals("*"));
        System.out.println("412222222222");*/
    }

    public static void test2(){
        Map<String, String> paramsMap = new HashMap();
        paramsMap.put("ip", checkip);
        paramsMap.put("func", "getstartupinfo");
        paramsMap.put("proxy","python|app-worker.py");

        SendElvesUtil.setSyncAuthKey(SYNC_AUTH_KEY);
        SendElvesUtil.setSyncAuthId(SYNC_AUTH_ID);
        SendElvesUtil.setSyncApp(SYNC_APP);
        String resultMessage = SendElvesUtil.sendElvesOpenApi(paramsMap, SendElvesEnum.valueOf("SYNC_CREATERT_URI").getUri());
        //System.out.println(resultMessage);
        Map<String,JSONObject>map1 =JSONUtils.parseObject(resultMessage,new HashMap().getClass());
        Map<String,Object>map2 =JSONUtils.parseObject(map1.get("result").toString(),new HashMap().getClass());
        List<Object> result =JSONUtils.parseObject(map2.get("worker_message").toString(),new ArrayList().getClass());
        List<Startup> list = new ArrayList<>();
        for(Object obj:result){
            Startup startup = new Startup();
            startup.setStartupName(obj.toString());
            list.add(startup);
        }

        List<StartupWhiteList> startupWhiteLists = new ArrayList<>();
        StartupWhiteList startupWhiteList = new StartupWhiteList();
        startupWhiteList.setBoot("touch /var/lock/subsys/local");
        startupWhiteLists.add(startupWhiteList);

        startupWhiteList = new StartupWhiteList();
        startupWhiteList.setBoot("route  add -net 10.0.0.0/8  gw 10.13.255.254");
        startupWhiteLists.add(startupWhiteList);

        for(Startup s:list){
            for(StartupWhiteList whiteList:startupWhiteLists){
                if(checkVagueBusinessList(s,whiteList)){
                    System.out.println(s.getStartupName() +whiteList.getBoot());
                }
                //System.out.println(checkVagueBusinessList(s,whiteList)+"____"+s.getStartupName() +whiteList.getBoot());
            }
        }
    }

    public static void test3(){
        List<Startup> list = new ArrayList<>();
        Startup startup = new Startup();
        startup.setStartupName("route add -net 10.0.0.0/8 gw 10.13.255.254");
        list.add(startup);

        List<StartupWhiteList> startupWhiteLists = new ArrayList<>();
        StartupWhiteList startupWhiteList = new StartupWhiteList();

        startupWhiteList = new StartupWhiteList();
        startupWhiteList.setBoot("route add -net 10.0.0.0/8 gw 10.13.255.254");
        startupWhiteLists.add(startupWhiteList);

        for(Startup s:list){
            for(StartupWhiteList whiteList:startupWhiteLists){
                if(checkVagueBusinessList(s,whiteList)){
                    System.out.println(s.getStartupName() +whiteList.getBoot());
                }
            }
        }
    }
}
