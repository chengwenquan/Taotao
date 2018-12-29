package com.taobao.common.utils;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 对象和json的工具类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class JsonAndObjUtils{

	/**
	 * 将对象集合的json字符串转化为对象集合
	 * @param e 需要转成的对象
	 * @param jsonStr json格式的字符串 
	 * @return 返回对象集合
	 */
	public static <E> List<E> jsonStrToArray(E e,String jsonStr){
		if(jsonStr==null||jsonStr.equals("")){
			return null;
		}
		JSONArray json = JSONArray.fromObject(jsonStr);
		List<E> list = (List<E>)JSONArray.toCollection(json, e.getClass());
		return list;
	}
	
	/**
	 * 将单个对象的json字符串转为对象
	 * @param e
	 * @param jsonStr
	 * @return
	 */
	public static <E>E jsonStrToObj(E e,String jsonStr){
		JSONObject jsonobject = JSONObject.fromObject(jsonStr);
		E obj = (E)JSONObject.toBean(jsonobject,e.getClass());
		return obj;
	}
	
	/**
	 * 将对象集合转为json字符串
	 * @param list 对象集合
	 * @return
	 */
	public static String jsonArrayToStr(List<?> list){
		JSONArray array = JSONArray.fromObject(list);
	    String jsonstr = array.toString();
		return jsonstr;
	}
	/**
	 * 将对象转为json字符串
	 * @param obj
	 * @return
	 */
	public static String jsonObjToStr(Object obj){
		JSONObject object = JSONObject.fromObject(obj);
	    String jsonstr = object.toString();
		return jsonstr;
	}
	
	
	

}
