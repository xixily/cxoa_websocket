<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 舍弃  -->
<div id = "renshi_toolbar">
<!-- <a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-edit'">编辑</a> -->
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="employee.view()">查看</a>
<span class="btn-separator"></span>
<a href="#" class="easyui-menubutton" data-options="menu:'#menu_employee_speedQuery',iconCls:'icon-search'">快捷查询</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="employee.editEmployee()">编辑</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="employee.addEmployee()">新增</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
onclick="confirmDialog.createDialog('您确定要删除吗？',employee.deleteEmployee);">删除</a>
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">变更</a> -->
<span class="btn-separator"></span>
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="employee.exportExcel()">导出报表</a>
<span class="btn-separator"></span> -->
<a href="#" class="easyui-menubutton" data-options="menu:'#menu_employee_export',iconCls:'icon-excel'">导出报表</a>
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-copy',plain:true">复制</a> -->
</div>
<%
Calendar cal = Calendar.getInstance();
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH) + 1;
%>
<div id="menu_employee_export" style="width:160px;">
	 <div>
        <span>当月入职情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(111)">导出<%= "" + year+ "-"+ (month - 1) %>月入职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(112)">导出<%= "" + year + "-"+ month %>月入职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(113)">导出<%= "" + year + "-"+ (month + 1) %>月入职信息(正式)</div>
            <div class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(121)">导出<%= "" + year+ "-"+ (month - 1) %>月入职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(122)">导出<%= "" + year + "-"+ month %>月入职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(123)">导出<%= "" + year + "-"+ (month + 1) %>月入职信息(实习)</div>
        </div>
    </div>
	 <div>
        <span>当月转正情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(211)">导出<%= "" + year+ "-"+ (month - 1) %>月转正信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(212)">导出<%= "" + year + "-"+ month %>月转正信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(213)">导出<%= "" + year + "-"+ (month + 1) %>月转正信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(221)">导出<%= "" + year+ "-"+ (month - 1) %>月转正信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(222)">导出<%= "" + year + "-"+ month %>月转正信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(223)">导出<%= "" + year + "-"+ (month + 1) %>月转正信息(实习)</div>
        </div>
    </div>
	 <div>
        <span>当月离职情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(311)">导出<%= "" + year+ "-"+ (month - 1) %>月离职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(312)">导出<%= "" + year + "-"+ month %>月离职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(313)">导出<%= "" + year + "-"+ (month + 1) %>月离职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(321)">导出<%= "" + year+ "-"+ (month - 1) %>月离职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(322)">导出<%= "" + year + "-"+ month %>月离职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(323)">导出<%= "" + year + "-"+ (month + 1) %>月离职信息(实习)</div>
        </div>
    </div>
	 <div>
        <span>当月异动情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(411)">导出<%= "" + year+ "-"+ (month - 1) %>月异动信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(412)">导出<%= "" + year + "-"+ month %>月异动信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(413)">导出<%= "" + year + "-"+ (month + 1) %>月异动信息(正式)</div>
            <div class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(421)">导出<%= "" + year+ "-"+ (month - 1) %>月异动信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(422)">导出<%= "" + year + "-"+ month %>月异动信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(423)">导出<%= "" + year + "-"+ (month + 1) %>月异动信息(实习)</div>
        </div>
    </div>
	 <div>
        <span>异动表<%= "" + year+(month - 1) %></span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(211)">导出<%= "" + year+ "-"+ (month - 1) %>新员工入职人表</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(212)">导出<%= "" + year+ "-"+ (month - 1) %>离职人员表</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(213)">导出<%= "" + year + "-"+ (month - 1) %>员工转正人员表</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(214)">导出<%= "" + year + "-"+ (month - 1) %>部门调整表</div>
        </div>
    </div>
	 <div>
        <span>异动表<%= ""+year+month %></span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(311)">导出<%= "" + year+ "-"+ (month) %>新员工入职人表</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(312)">导出<%= "" + year+ "-"+ (month) %>离职人员表</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(313)">导出<%= "" + year + "-"+ (month) %>员工转正人员表</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportYDExcel(314)">导出<%= "" + year + "-"+ (month) %>部门调整表</div>
        </div>
    </div>
	<div>
        <span>社保表</span>
        <div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.shebaoSummary.exportShebaoDetail(111)">导出<%= "" + year+ "-"+ (month - 1) %>月社保明细</div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.shebaoSummary.exportShebaoDetail(112)">导出<%= "" + year+ "-"+ (month) %>社保增员</div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.shebaoSummary.exportShebaoDetail(113)">导出<%= "" + year+ "-"+ (month) %>社保减员</div>        </div>
    </div>
    <div class="menu-sep"></div>
<%--     <div data-options="iconCls:'icon-excel'" onclick="employee.shebaoSummary.exportShebaoDetail(111)">导出<%= "" + year+ "-"+ (month - 1) %>月社保明细</div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.shebaoSummary.exportShebaoDetail(112)">导出<%= "" + year+ "-"+ (month) %>社保增员</div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.shebaoSummary.exportShebaoDetail(113)">导出<%= "" + year+ "-"+ (month - 1) %>社保减员</div> --%>
    <div data-options="iconCls:'icon-excel'" onclick="employee.employee.exportExcel(0)">根据当前查询条件导出</div>
</div>

    <div id="menu_employee_speedQuery" style="width:160px;">
    <div>
    <span>工资快捷查询</span>
    <div>
    <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(111)">查询<%= "" + year+ "-"+ (month - 1) %>入职，工资为0</div>
    <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(112)">查询<%= "" + year + "-"+ month %>入职，工资为0</div>
    <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(401)">查询转正报表<%= "" + year+(month - 1) %>，工资为空</div>
    <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(113)">查询卡号有现金的人员名单</div>
    </div>
    </div>
    <div>
    <span>其他快捷查询</span>
    <div>
    <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(301)">查询本月合同到期名单</div>
    <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(302)">查询没有身份证的名单</div>
    </div>
    </div>
    <div class="menu-sep"></div>
    <%-- <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(211)">查询<%= "" + year+ "-"+ (month - 1) %>转正，历史工资为0</div>
    <div data-options="inconCLs:'icon-search'" onclick="employee.employee.quickQuery(212)">查询<%= "" + year + "-"+ month %>入职，历史工资为0</div> --%>
    </div>