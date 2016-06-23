//是否为空校验
function isEmpty(s) {
	if( s==null || s.length == 0 ){
		return true;
	}
	var lll=trim(s);
	if( lll == null || lll.length == 0 ){
		return true;
	}else{
		return false;
	}
}

//删除字符串左边的空格
function ltrim(str) { 
	if(str.length==0){
		return(str);
	}else {
		var idx=0;
		while(str.charAt(idx).search(/\s/)==0){
			idx=idx+1;
			if(idx>(str.length-1)){
				return "";
			}
		}
		return(str.substr(idx));
	}
}

//删除字符串右边的空格
function rtrim(str) { 
	if(str.length==0){
		return(str);
	}else {
		var idx=str.length-1;
		while(str.charAt(idx).search(/\s/)==0){
			idx=idx-1;
			if(idx<0){
				return "";
			}
		}
		return(str.substring(0,idx+1));
	}
}

//删除字符串左右两边的空格
function trim(str) { 
	if( str == null || str.length == 0 ){
		return "";
	}
	return(rtrim(ltrim(str)));
}

/*日期相比较*/
function compareDate(date1, date2) {
	if (trim(date1) == trim(date2)){
		return 0;
	}
	if (trim(date1) > trim(date2)){  	
		return 1;
	}
	if (trim(date1) < trim(date2)){  	
		return -1;
	}
}

//是否为邮箱地址
function isEmail(email){
	var reg=new RegExp("^[\\w\\-]+@[\\w\\-]+(\\.[\\w\\-]+)+$");
	return reg.test(email);
}

//是否为电话号码，如023-618895468或023 681432
function isTel(tel){
	var reg=new RegExp("^[\\d]+([\\-\\s]+[\\d]+)*$");
	return reg.test(tel);
}

//校验是否是实数
function isNumber(num) {
	var reg=new RegExp("^[\\-]?[\\d]+(\\.[\\d]+)?$");
	return reg.test(num);
}

//校验是否是整数
function isInteger(num)	{
	var reg=new RegExp("^[\\-]?[\\d]+$");
	return reg.test(num);
}

//校验是否为银行帐号
function isBankNo(num)	{
	var reg=new RegExp("^[\\d]+([\\s]+[\\d]+)*$");
	return reg.test(num);
}

//校验是否为正实数，可为0
function isPositiveNumber(num)	{
	var reg=new RegExp("^[\\d]+(\\.[\\d]+)?$");
	return reg.test(num);
}

//校验是否为正整数，可为0
function isPositiveInteger(num)	{
	var reg=new RegExp("^[\\d]+$");
	return reg.test(num);
}

//校验是否为正整数，可为0
function isDate(dateString)	{
	var reg=new RegExp("^[\\d]{4}\\-[\\d]{2}\\-[\\d]{2}$");
	return reg.test(dateString);
}

//取得上传文件的文件名的后缀
function getFileNameSuffix(fileName){
	var idx=fileName.length-1;
	while(fileName.charAt(idx).search(/\./)==-1){
		idx=idx-1;
		if(idx<0){
			return "";
		}
	}
	return fileName.substring(idx+1,fileName.length);
}