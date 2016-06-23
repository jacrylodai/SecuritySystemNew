<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>
<HTML>
<!--
 ---------------------------------------------------------------------------
 this script is copyright (c) 2001 by Michael Wallner!
 http://www.wallner-software.com
 mailto:dhtml@wallner-software.com

 you may use this script on web pages of your own
 you must not remove this copyright note!

 Sie d黵fen dieses Script auf Ihren Webseiten verwenden
 Sie d黵fen diesen Copyright Hinweis jedoch nicht entfernen!
 ---------------------------------------------------------------------------
-->
<HEAD>
	<base href="<%=basePath %>"/>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=UTF-8">
<TITLE>Outlook Like Bar</TITLE>
		<link rel="stylesheet" type="text/css" href="style/drp.css">

<!--
  you need this style or you will get an error in ns4 on first page load!
  diese Style Angabe wird ben鰐igt, oder man erh鋖t im ns4 beim ersten Laden
  eine Fehlermeldung!
-->
<STYLE>
  div    {
         position:absolute;
         }
</STYLE>

<script language="JavaScript" src="outlookMenuRes/crossbrowser.js" type="text/javascript">
</script>
<script language="JavaScript" src="outlookMenuRes/outlook.js" type="text/javascript">
</script>

<SCRIPT>

// ---------------------------------------------------------------------------
// Example of howto: use OutlookBar
// ---------------------------------------------------------------------------


  //create OutlookBar
  var o = new createOutlookBar('Bar',0,0,screenSize.width,screenSize.height,'#606060','black')//'#000099') // OutlookBar

  var p

  //create first panel

  p = new createPanel('p2','统计管理');
  p.addButton('images/netm.gif','自定义统计维护','parent.frames.main.location.href="<%=basePath %>sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=listCustomStaPeriodSecurity"');
  o.addPanel(p);

  p = new createPanel('p2','基础数据管理');
  p.addButton('images/netm.gif','车站维护','parent.frames.main.location.href="basedata/department/departmentFunc.do?command=listDepartment"');
  o.addPanel(p);

  p = new createPanel('p2','系统功能');
  p.addButton('images/netm.gif','修改密码','parent.frames.main.location.href="<%=basePath %>system/password/passwordFunc.do?command=updatePasswordPrepare"');
  o.addPanel(p);
  
  o.draw();         //draw the OutlookBar

//-----------------------------------------------------------------------------
//functions to manage window resize
//-----------------------------------------------------------------------------
//resize OP5 (test screenSize every 100ms)
function resize_op5() {
  if (bt.op5) {
    o.showPanel(o.aktPanel);
    var s = new createPageSize();
    if ((screenSize.width!=s.width) || (screenSize.height!=s.height)) {
      screenSize=new createPageSize();
      //need setTimeout or resize on window-maximize will not work correct!
      //ben鰐ige das setTimeout oder das Maximieren funktioniert nicht richtig
      setTimeout("o.resize(0,0,screenSize.width,screenSize.height)",100);
    }
    setTimeout("resize_op5()",100);
  }
}

//resize IE & NS (onResize event!)
function myOnResize() {
  if (bt.ie4 || bt.ie5 || bt.ns5) {
    var s=new createPageSize();
    o.resize(0,0,s.width,s.height);
  }
  else
    if (bt.ns4) location.reload();
}

</SCRIPT>

</head>
<!-- need an onResize event to redraw outlookbar after pagesize changes! -->
<!-- OP5 does not support onResize event! use setTimeout every 100ms -->
<body onLoad="resize_op5();" onResize="myOnResize();">
</body>
</html>


