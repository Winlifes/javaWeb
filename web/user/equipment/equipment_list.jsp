<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<%=path%>/css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="<%=path%>/css/left.css" rel="stylesheet" type="text/css">
    <link href="<%=path%>/css/pdmcontent01.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=path%>/js/jquery-3.4.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    <script src="<%=path%>/css/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-pdm.js"></script>
    <title>设备信息</title>
</head>
<body>
<div class="page-content">
    <form action="<%=path%>/equipment_list.action" class="form-inline" method="post">
        <div class="panel panel-default">
            <div class="panel-heading">设备信息列表</div>
            <div class="panel-body">
                <div class="pull-left">
                    <div class="form-group qinfo">
                        <label>设备名称：</label>
                        <input name="uname" value="${uname}" class="form-control">
                    </div>
                    <div style="margin-left: 20px;zoom:110%;" class="form-group ginfo">
                        <input type="checkbox" id="state" name="state" value="未使用" onchange="saveCheckboxState()">
                        <label for="state">未使用</label>
                    </div>
                    <div style="margin-left: 20px;zoom:110%" class="form-group ginfo">
                        <input type="checkbox" id="situation" name="situation" value="未被预定" onchange="saveCheckboxState()">
                        <label for="situation">未被预定</label>
                    </div>

                    <button style="margin-left: 20px"  type="submit" class="btn btn-default">查询</button>
                </div>
            </div>
            <pg:pager url="equipment_list.action" items="${itemSize}" maxPageItems="${pageItem}" maxIndexPages="${pageItem}" isOffset="${true}" export="offset,currentPageNumber=pageNumber" scope="request">
                <pg:param name="uname" value="${uname}" />
                <pg:param name="upwd" value="${upwd}" />
                <div class="table-responsive">
                    <table class="table table-striped table-hover" style="text-align: center;">
                        <thead>
                        <tr>
                            <td>设备id</td>

                            <td>设备名称</td>
                            <td>设备状态</td>
                            <td>预定情况</td>
                            <th width="18%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="info">
                            <tr>
                                <td>${info.id}</td>
                                <td>${info.uname}</td>
                                <td>${info.state}</td>
                                <td>${info.situation}</td>
                                <td>
                                    <c:if test="${sessionScope.cuser.uname=='admin'}">
                                        <a style="color: #2b669a" href="<%=path%>/scheduled_toView.action?uid=${info.id}" class="btn btn-info btn-xs">
                                            <span style="color: #2b669a" class="glyphicon glyphicon-edit"></span>
                                            详情
                                        </a>
                                    </c:if>

                                    <c:if test="${sessionScope.cuser.uname=='admin'}">
                                        <a href="<%=path%>/equipment_toUpdate.action?id=${info.id}" class="btn btn-info btn-xs">
                                            <span class="glyphicon glyphicon-edit"></span>
                                            修改
                                        </a>
                                    </c:if>


                                    <c:if test="${sessionScope.cuser.uname!='admin' && info.situation=='未被预定'}">
                                        <a href="<%=path%>/scheduled_toAdd.action?id=${info.id}&uname=${info.uname}" class="btn btn-info btn-xs">
                                            <span class="glyphicon glyphicon-edit"></span>
                                            预定
                                        </a>
                                    </c:if>


                                    <c:if test="${sessionScope.cuser.uname=='admin'}">
                                        <a href="javascript:void();" onclick="delInfo('${info.id}');" class="btn btn-danger btn-xs">
                                            <span class="glyphicon glyphicon-trash"></span>
                                            删除
                                        </a>
                                    </c:if>
                                    <!--a href="#" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-eye-open"></span> 查看</a-->
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="panel-footer clearfix">
                        <c:if test="${sessionScope.cuser.uname=='admin'}">
                        <div class="pull-left">
                            <button type="button" id="addBtn" class="btn btn-info">新增</button>
                        </div>
                        </c:if>
                        <nav class="pull-right"> <pg:index>
                            <jsp:include page="/common/pagination_tag.jsp" flush="true" />
                        </pg:index> </nav>
                    </div>
                </div>
            </pg:pager>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        $("#addBtn").click(function(){
            window.location.href = '<%=path%>/equipment_toAdd.action';
        });
    });
    function delInfo(id){
        if(confirm("是否确认删除？")){
            window.location.href = '<%=path%>/equipment_delete.action?id=' + id;
        }
    }
    // 存储复选框状态
    function saveCheckboxState() {
        var myCheckbox1 = document.getElementById('state');
        var myCheckbox2 = document.getElementById('situation');
        var isChecked1 = myCheckbox1.checked;
        var isChecked2 = myCheckbox2.checked;

        localStorage.setItem('checkboxState1', isChecked1);
        localStorage.setItem('checkboxState2', isChecked2);
    }

    // 从存储中获取复选框状态并应用到复选框
    function loadCheckboxState() {
        var myCheckbox1 = document.getElementById('state');
        var myCheckbox2 = document.getElementById('situation');
        var isChecked1 = localStorage.getItem('checkboxState1');
        var isChecked2 = localStorage.getItem('checkboxState2');
        myCheckbox1.checked = isChecked1 === 'true';
        myCheckbox2.checked = isChecked2 === 'true';
    }

    // 在页面加载时调用加载函数
    window.onload = loadCheckboxState;

</script>