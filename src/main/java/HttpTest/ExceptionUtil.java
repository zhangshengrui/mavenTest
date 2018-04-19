package HttpTest;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName: ExceptionUtil
 * @Description: 异常工具类
 * @author gongwenlong@gyyx.cn
 * @date 2017年7月4日 下午3:50:33
 */
public class ExceptionUtil {
	
	/**
	 * @Title: getExceptionMsg
	 * @Description: 得到堆栈异常信息字符串
	 * @param e
	 * @return String    返回类型
	 */
	public static String getExceptionMsg(Throwable e) {
		StringWriter stm = new StringWriter();
		PrintWriter pw = new PrintWriter(stm);
		e.printStackTrace(pw);
		pw.close();
		return stm.toString();
	}
}
