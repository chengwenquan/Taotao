package com.taobao.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static String getStackTrace(Throwable t){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			//将异常信息打印到pw,而pw构造依赖sw,pw将信息打印到sw中返回sw.toString()
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}	
		
	}
}
