package com.hades.mylibrary.utils;

import com.google.gson.Gson;

/***
 * 
 * 类说明：json字符串转换成对象插件
 * 获取对象 Gson.getInstance().fromJson(jsonData, cls);
 * 获取对象List List<Person> retList = Gson.getInstance().fromJson(json_list, new TypeToken<List<Person>>() {}.getType());
 * @author 祝崇彬 2015年8月24日
 */
public class GsonUtils {
	/***
	 * 单列
	 */
	private static Gson gson = new Gson();

	public static Gson getInstance() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}
}
