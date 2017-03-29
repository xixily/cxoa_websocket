<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- my97日期控件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/My97DatePickerBeta4.8b2/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<!-- easyui控件 -->
<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- easyui portal插件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-portal/portal.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<!-- cookie插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.cookie.js" charset="utf-8"></script>
<%-- <!-- xhEditor插件库 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>
 --%><!-- 自己定义的样式和JS扩展 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/oaCss.css" type="text/css"></link>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/jsController/app.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/north.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/west.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/employee.js"></script>
 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/util.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.md5.js" charset="utf-8"></script>
<%-- <!-- 动态加载js代码  -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/LAB.min.js"></script> --%>