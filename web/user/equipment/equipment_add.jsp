<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增</title>
	<link href="<%=path%>/css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script src="<%=path%>/js/jquery-3.4.1.min.js"></script>
	<script src="<%=path%>/css/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/jquery.validate.min.js"></script>
	<script src="<%=path%>/js/jquery.validate.extend.js"></script>
    <script src="<%=path%>/js/jquery.validate.method.js"></script>
            <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    <link href="<%=path%>/css/pdmcontent01.css" rel="stylesheet">
</head>
<body>
<div class="page-content">
    <div class="panel panel-default">
        <div class="panel-heading">新增</div>
        <div class="panel-body">
            <form action="<%=path%>/equipment_add.action" method="post" id="addForm">
                <div class="container-fluid">
<%--               <div class="row rowmargin">--%>
<%--						<div class="col-sm-7">--%>
<%--							<div class="form-group">--%>
<%--                                                                            <label class="col-sm-3 control-label"><font color="red">*</font>设备id </label>--%>
<%--								<div class="col-sm-9 form-inline"  >--%>
<%--                                                    <input id="id" name="id" size="35" class="form-control"   type="text" value=""  tip="请输入设备id" />--%>
<%--								</div>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--					</div>--%>
               <div class="row rowmargin">
						<div class="col-sm-7">
							<div class="form-group">
                                                                            <label class="col-sm-3 control-label"><font color="red">*</font>设备名称 </label>
								<div class="col-sm-9 form-inline"  >
                                                    <input id="uname" name="uname" size="35" class="form-control"   type="text" value=""  tip="请输入设备名称" />
								</div>
							</div>
						</div>
					</div>
               <div class="row rowmargin">
						<div class="col-sm-7">
							<div class="form-group">
                                                                            <label class="col-sm-3 control-label"><font color="red">*</font>设备状态 </label>
								<div class="col-sm-9 form-inline"  >
                                                    <input id="state" name="state" size="35" class="form-control"   type="text" value=""  tip="请输入设备状态" />
								</div>
							</div>
						</div>
					</div>
               <div class="row rowmargin">
						<div class="col-sm-7">
							<div class="form-group">
                                                                            <label class="col-sm-3 control-label"><font color="red">*</font>预定情况 </label>
								<div class="col-sm-9 form-inline"  >
                                                    <input id="situation" name="situation" size="35" class="form-control"   type="text" value=""  tip="请输入预定情况" />
								</div>
							</div>
						</div>
					</div>
                    <div class="row">
                      <div class="col-sm-2">
						</div>
                       <div class="col-sm-4" style="margin-left: 20px;">
                         <button type="submit" class="btn btn-primary" style="margin-top: 40px;">提交</button>
                         <button type="button" id="rebackBtn" class="btn btn-default" style="margin-top: 40px;margin-left: 20px;">返回</button>
						</div>
					</div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
    	 $("#rebackBtn").click(function(){
	     window.history.go(-1); 
	 });
    $("#addForm").validate({
        submitHandler:function(form){//验证通过后的执行方法
                        form.submit();
        },
        rules: {
                                                    uname:{
                                                                    required:true,
                                                    },
                                                                    state:{
                                                                    required:true,
                                                    },
                                                                    name:{
                                                                    required:true,
                                                    },
                                                                    situation:{
                                                                    required:true,

                                                    },
                                    },
        messages:{
                                                uname:{
                                                                        required:'设备名称不能为空',

                                    },
                                                                state:{
                                                                        required:'设备状态不能为空',
                                    },
                                                                situation:{
                                                                        required:'预定情况不能为空',
                                    },


        }
    });
});
</script>
</html>