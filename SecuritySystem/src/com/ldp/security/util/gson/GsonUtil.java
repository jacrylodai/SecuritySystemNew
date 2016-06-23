package com.ldp.security.util.gson;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ldp.security.sta.domain.SecurityMachineCheckInfo;

/**
 * google的json工具类
 * @author Administrator
 *
 */
public class GsonUtil {

	private static final Gson gson = new Gson();
	
	private static final Type TYPE_LIST_INTEGER =
		new TypeToken<List<Integer>>(){}.getType();
				
	private static final Type TYPE_LIST_SECU_MACH_CHECK_INFO =
		new TypeToken<List<SecurityMachineCheckInfo>>(){}.getType();
		
	/**
	 * 把json转化为list
	 * @param gsonString
	 * @return
	 */
	public static List<Integer> convertFromGsonToListInteger(String gsonString){
		
		return gson.fromJson(gsonString, TYPE_LIST_INTEGER);
	}
	
	/**
	 * 把json转化为List<SecurityMachineCheckInfo>
	 * @param gsonString
	 * @return
	 */
	public static List<SecurityMachineCheckInfo> convertFromGsonToListCheckInfo(
			String gsonString){
		
		return gson.fromJson(gsonString, TYPE_LIST_SECU_MACH_CHECK_INFO);
	}
}
