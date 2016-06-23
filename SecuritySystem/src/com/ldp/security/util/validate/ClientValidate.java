package com.ldp.security.util.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输入验证类
 * @author Administrator
 *
 */
public class ClientValidate {
	
	private static Pattern telP=
		Pattern.compile("\\d+([\\-\\s]+\\d+)*");

	private static Pattern bankNoP=
		Pattern.compile("\\d+([\\s]+\\d+)*");
	
	private static Pattern emailP=
		Pattern.compile("[\\w\\-\\.]+@[\\w\\.\\-]+(\\.[\\w\\.\\-]+)+");
	
	private static Pattern integerP=
		Pattern.compile("\\d+");

	private static Pattern dateP=
		Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");
	
	/**
	 * 字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		if(s==null || s.length()==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 是否是电话号码
	 * @param s
	 * @return
	 */
	public static boolean isTel(String s){
		Matcher m=telP.matcher(s);
		return m.matches();
	}

	/**
	 * 是否是电子邮箱
	 * @param s
	 * @return
	 */
	public static boolean isEmail(String s){
		Matcher m=emailP.matcher(s);
		return m.matches();
	}
	
	/**
	 * 是否是整数
	 * @param s
	 * @return
	 */
	public static boolean isInteger(String s){
		Matcher m = integerP.matcher(s);
		return m.matches();
	}
	
	/**
	 * 是否是日期
	 * @param s
	 * @return
	 */
	public static boolean isDate(String s){
		Matcher m = dateP.matcher(s);
		return m.matches();
	}
	
	/**
	 * 是否是银行帐号
	 * @param s
	 * @return
	 */
	public static boolean isBankNo(String s){
		Matcher m = bankNoP.matcher(s);
		return m.matches();
	}
	
	public static void main(String [] arg){
		System.out.print(isTel("23"));
		System.out.print(isTel("23-3434-2"));
		System.out.print(isEmail("ja.-_ck@s.c"));
		System.out.println(isInteger("23123"));
		System.out.println(isDate(""));
		System.out.println(isDate(null));
	}
	
}
