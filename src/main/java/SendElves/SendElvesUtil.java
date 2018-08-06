package SendElves;

import cn.gy4j.frame.utils.DateUtils;
import cn.gy4j.frame.utils.JSONUtils;
import cn.gy4j.frame.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.Map.Entry;

/**
 * @ClassName: SendElvesUtil
 * @Description: 发送数据到elves，调用接口工具类
 * @author gongwenlong@gyyx.cn
 * @date 2017年7月4日 下午3:56:47
 *
 */
public class SendElvesUtil {

	//private static String SYNC_AUTH_ID = "AAB4D95DA15F7E86";
	//private static String SYNC_APP = "webops";
	//private static String SYNC_AUTH_KEY = "40A2F680F77F988E";

	private static String SYNC_AUTH_ID = "1BC23436ADCB4F0C";
	private static String SYNC_APP = "leader";
	private static String SYNC_AUTH_KEY = "FD4E8AB10130C651";

	private static String SYNC_INTERFACE_URL = "http://api.elves.gyyx.cn";

    public static String getSyncAuthId() {
        return SYNC_AUTH_ID;
    }

    public static void setSyncAuthId(String syncAuthId) {
        SYNC_AUTH_ID = syncAuthId;
    }

    public static String getSyncApp() {
        return SYNC_APP;
    }

    public static void setSyncApp(String syncApp) {
        SYNC_APP = syncApp;
    }

    public static String getSyncAuthKey() {
        return SYNC_AUTH_KEY;
    }

    public static void setSyncAuthKey(String syncAuthKey) {
        SYNC_AUTH_KEY = syncAuthKey;
    }

    public static String getSyncInterfaceUrl() {
        return SYNC_INTERFACE_URL;
    }

    public static void setSyncInterfaceUrl(String syncInterfaceUrl) {
        SYNC_INTERFACE_URL = syncInterfaceUrl;
    }

    /**
	 * @Title: sendElvesOpenApi
	 * @Description: 调用Elves-openapi接口
	 * @param paramsMap
	 * @return String    返回类型
	 */
	public static String sendElvesOpenApi(Map<String, String> paramsMap,String interfaceUri){
		//添加必传参数 auth_id 、timestamp
		if(null==paramsMap){
			paramsMap=new HashMap<String, String>();
		}
		paramsMap.put("auth_id", SYNC_AUTH_ID);
		paramsMap.put("timestamp", (System.currentTimeMillis()/1000)+"");
		if(StringUtils.isBlank(paramsMap.get("mode"))){
			paramsMap.put("mode","ssnp");
		}
		if(StringUtils.isBlank(paramsMap.get("app"))){
			paramsMap.put("app",SYNC_APP);
		}
		//可选参数
		paramsMap.put("timeout","300");
		
		//System.out.println("param map :"+paramsMap.toString());
		//制作签名
		StringBuffer sortUri=new StringBuffer(interfaceUri);
		if(paramsMap.size()>0){
			sortUri.append("?");
			Set<String> keys=paramsMap.keySet();
			List<String> list=new ArrayList<String>();
			list.addAll(keys);
			Collections.sort(list);
			for(String k:list){
				sortUri.append(k+"=");
				sortUri.append(paramsMap.get(k)!=null?paramsMap.get(k):"");
				sortUri.append("&");
			}
			if(sortUri.length()>0){
				sortUri.deleteCharAt(sortUri.length()-1);
			}
		}

		sortUri.append(SYNC_AUTH_KEY);
		//MD5
		//System.out.println("sortUri:"+sortUri);
		String signFinal= MD5Utils.MD5(sortUri.toString());
		//System.out.println("signFinal:"+signFinal);
		paramsMap.put("sign_type","MD5");
		paramsMap.put("sign",signFinal);
		//封装参数，post 发送
		String result=sendPost(SYNC_INTERFACE_URL+interfaceUri,paramsMap);
		//System.out.println("result:"+result);
		return result;
	}

	/**
	 * @Title: sendElvesOpenApi
	 * @Description: 调用Elves-openapi接口
	 * @param paramsMap
	 * @param interfaceUri
	 * @return String    返回类型
	 */
	public static String sendElvesOpenApi3(Map<String, String> paramsMap,String interfaceUri){
		//添加必传参数 auth_id 、timestamp
		if(null==paramsMap){
			paramsMap=new HashMap<String, String>();
		}
		paramsMap.put("auth_id", SYNC_AUTH_ID);
		paramsMap.put("timestamp", (System.currentTimeMillis()/1000)+"");
		if(StringUtils.isBlank(paramsMap.get("queue_id"))){
			paramsMap.put("queue_id",paramsMap.get("queue_id"));
		}
		//可选参数
		paramsMap.put("timeout","300");
		//System.out.println("param map :"+paramsMap.toString());
		//制作签名
		StringBuffer sortUri=new StringBuffer(interfaceUri);
		if(paramsMap.size()>0){
			sortUri.append("?");
			Set<String> keys=paramsMap.keySet();
			List<String> list=new ArrayList<String>();
			list.addAll(keys);
			Collections.sort(list);
			for(String k:list){
				sortUri.append(k+"=");
				sortUri.append(paramsMap.get(k)!=null?paramsMap.get(k):"");
				sortUri.append("&");
			}
			if(sortUri.length()>0){
				sortUri.deleteCharAt(sortUri.length()-1);
			}
		}

		sortUri.append(SYNC_AUTH_KEY);
		//MD5
		////System.out.println("sortUri:"+sortUri);
		String signFinal=MD5Utils.MD5(sortUri.toString());
		////System.out.println("signFinal:"+signFinal);
		paramsMap.put("sign_type","MD5");
		paramsMap.put("sign",signFinal);
		//封装参数，post 发送
		String result=sendGet(SYNC_INTERFACE_URL+interfaceUri,paramsMap);
		////System.out.println("result:"+result);
		return result;
	}


	/**
	 * @Title: sendElvesOpenApi2
	 * @Description: 调用Elves-openapi接口
	 * @param paramsMap
	 * @param interfaceUri
	 * @param clazz
	 * @return T    返回类型
	 */
	public static <T> T sendElvesOpenApi2(Map<String, String> paramsMap,String interfaceUri,Class<T> clazz){
		//添加必传参数 auth_id 、timestamp
		if(null==paramsMap){
			paramsMap=new HashMap<String, String>();
		}
		paramsMap.put("auth_id", SYNC_AUTH_ID);
		paramsMap.put("timestamp", (System.currentTimeMillis()/1000)+"");
		////System.out.println("param map :"+paramsMap.toString());
		/**
		 * 制作签名
		 */
		StringBuffer sortUri=new StringBuffer(interfaceUri);
		if(paramsMap.size()>0){
			sortUri.append("?");
			Set<String> keys=paramsMap.keySet();
			List<String> list=new ArrayList<String>();
			list.addAll(keys);
			Collections.sort(list);
			for(String k:list){
				sortUri.append(k+"=");
				sortUri.append(paramsMap.get(k)!=null?paramsMap.get(k):"");
				sortUri.append("&");
			}
			if(sortUri.length()>0){
				sortUri.deleteCharAt(sortUri.length()-1);
			}
		}

		sortUri.append(SYNC_AUTH_KEY);
		//MD5
		////System.out.println("sortUri:"+sortUri);
		String signFinal=MD5Utils.MD5(sortUri.toString());
		////System.out.println("signFinal:"+signFinal);
		paramsMap.put("sign_type","MD5");
		paramsMap.put("sign",signFinal);
		//封装参数，post 发送
		String result=sendPost(SYNC_INTERFACE_URL+interfaceUri,paramsMap);
		////System.out.println("result:"+result);
		if(StringUtils.isNotBlank(result) && !"error".equals(result)){
			try {
				T rsMap = JSONUtils.parseObject(result,clazz);
				return rsMap;
			} catch (Exception e) {
				System.out.println(e);
            }
		}
		return null;
	}


	/**
	 * @Title: sendPost
	 * @Description: 发起POST请求
	 * @param params 请求参数
	 * @return String 返回类型
	 */
	public static String sendPost(String url, Map<String, String> params) {
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        HttpURLConnection conn =null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
            ////System.out.println("write :"+DateUtils.currentTimestamp2String("yyyy-MM-dd HH:mm:ss SS"));
            // 发送请求参数
            out.print(sb.toString());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //System.out.println("write :"+ DateUtils.currentTimestamp2String("yyyy-MM-dd HH:mm:ss SS"));
            //System.out.println(result);
            //result= new String(result.getBytes("GBK"),"UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }finally {
			if (conn != null) {
				conn.disconnect();
			}
        }
        return result;
	}

	public static String sendGet(String url, Map<String, String> params) {
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}

        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + sb.toString();
            //System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //result= new String(result.getBytes("GBK"),"UTF-8");
        } catch (Exception e) {
        	 System.out.println(e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
        return result;
    }
}
