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
    <title>申请信息</title>
</head>
<body>
<div class="page-content">
    <form action="<%=path%>/scheduled_list.action?tname=${sessionScope.cuser.uname}&xname=${sessionScope.cuser.name}" class="form-inline" method="post">
        <div class="panel panel-default">
            <div class="panel-heading">申请信息列表</div>
            <div class="panel-body">
                <div class="pull-left">
                    <div class="form-group qinfo">
                        <label>设备名称：</label>
                        <input name="uname" value="${uname}" class="form-control">
                    </div>
                    <c:if test="${sessionScope.cuser.uname=='admin'}">
                    <div class="form-group qinfo">
                        <label>预定人姓名：</label>
                        <input name="name" value="${name}" class="form-control">
                    </div>
                    </c:if>
                    <div style="margin-left: 20px;zoom:110%;" class="form-group ginfo">
                        <input type="checkbox" id="state" name="state" value="申请中" onchange="saveCheckboxState()">
                        <label for="state">申请中</label>
                    </div>

                    <button type="submit" class="btn btn-default">查询</button>
                </div>
            </div>
            <pg:pager url="scheduled_list.action" items="${itemSize}" maxPageItems="${pageItem}" maxIndexPages="${pageItem}" isOffset="${true}" export="offset,currentPageNumber=pageNumber" scope="request">
                <pg:param name="uname" value="${uname}" />
                <pg:param name="upwd" value="${upwd}" />
                <div class="table-responsive">
                    <table class="table table-striped table-hover" style="text-align: center;">
                        <thead>
                        <tr>
                            <td>预定设备</td>
                            <td>设备id</td>
                            <td>预定人姓名</td>
                            <td>预定时间</td>
                            <td>预定时长</td>
                            <td>预定原因</td>
                            <td>申请状态</td>
                            <th width="18%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="info">
                            <tr>
                                <td>${info.uname}</td>
                                <td>${info.uid}</td>
                                <td>${info.name}</td>
                                <td>${info.time}</td>
                                <td>${info.standing}</td>
                                <td>${info.reason}</td>
                                <td>${info.state}</td>
                                <td>
                                    <c:if test="${info.state=='申请中' && sessionScope.cuser.uname=='admin'}">
                                        <a href="javascript:void(0);" onclick="agree('${info.id}','${info.uid}')" class="btn btn-info btn-xs">
                                            <span class="glyphicon glyphicon-edit"></span>
                                            同意
                                        </a>
                                    </c:if>

                                    <c:if test="${info.state=='申请中' && sessionScope.cuser.uname=='admin'}">
                                        <a href="javascript:void(0);" onclick="refuse('${info.id}');" class="btn btn-danger btn-xs">
                                            <span class="glyphicon glyphicon-edit"></span>
                                            拒绝
                                        </a>
                                    </c:if>


                                    <c:if test="${info.state=='申请中' && sessionScope.cuser.uname!='admin'}">
                                        <a href="<%=path%>/scheduled_toUpdate.action?id=${info.id}" class="btn btn-info btn-xs">
                                            <span class="glyphicon glyphicon-edit"></span>
                                            修改
                                        </a>
                                    </c:if>


                                    <c:if test="${info.state=='申请中' && sessionScope.cuser.uname!='admin'}">
                                        <a href="javascript:void(0);" onclick="delInfo('${info.id}');" class="btn btn-danger btn-xs">
                                            <span class="glyphicon glyphicon-trash"></span>
                                            取消
                                        </a>
                                    </c:if>

                                    <c:if test="${info.state=='申请成功' && sessionScope.cuser.uname!='admin'}">
                                        <a href="javascript:void(0);" onclick="completeInfo('${info.id}','${info.uid}');" class="btn btn-info btn-xs">
                                            <span class="glyphicon glyphicon-edit"></span>
                                            完成
                                        </a>
                                    </c:if>
                                    <!--a href="#" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-eye-open"></span> 查看</a-->
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="panel-footer clearfix">
<%--                        <div class="pull-left">--%>
<%--                            <button type="button" id="addBtn" class="btn btn-info">新增</button>--%>
<%--                        </div>--%>
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
            window.location.href = '<%=path%>/tadmin_toAdd.action';
        });
    });
    function delInfo(id){
        if(confirm("是否确认删除？")){
            window.location.href = '<%=path%>/scheduled_delete.action?tname=${sessionScope.cuser.uname}&xname=${sessionScope.cuser.name}&id=' + id;
        }
    }
    function completeInfo(id,uid){
        if(confirm("设备是否出现问题？")){
            window.location.href = '<%=path%>/scheduled_complete.action?tname=${sessionScope.cuser.uname}&xname=${sessionScope.cuser.name}&id=' + id + '&uid=' + uid + '&error=true';
        }
        else
        {
            window.location.href = '<%=path%>/scheduled_complete.action?tname=${sessionScope.cuser.uname}&xname=${sessionScope.cuser.name}&id=' + id + '&uid=' + uid + '&error=false';
        }
    }
    function agree(id,uid){
        window.location.href = '<%=path%>/scheduled_agree.action?id=' + id + '&uid=' + uid;
    }
    function refuse(id){
        window.location.href = '<%=path%>/scheduled_refuse.action?id=' + id;
    }
    function saveCheckboxState() {
        var myCheckbox1 = document.getElementById('state');
        var isChecked1 = myCheckbox1.checked;

        localStorage.setItem('checkboxState1', isChecked1);
    }

    // 从存储中获取复选框状态并应用到复选框
    function loadCheckboxState() {
        var myCheckbox1 = document.getElementById('state');
        var isChecked1 = localStorage.getItem('checkboxState1');
        myCheckbox1.checked = isChecked1 === 'true';
    }

    // 在页面加载时调用加载函数
    window.onload = loadCheckboxState;
</script>