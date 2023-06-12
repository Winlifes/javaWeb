<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改</title>
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
        <div class="panel-heading">修改</div>
        <div class="panel-body">
            <form action="<%=path%>/scheduled_update.action?tname=${sessionScope.cuser.uname}&xname=${sessionScope.cuser.name}" method="post" id="addForm">
                 <input name="id" type="hidden" value="${scheduled.id}"/>
                <div class="container-fluid">
                    <div class="row rowmargin">
                        <div class="col-sm-7">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><font color="red">*</font>预定设备 </label>
                                <div class="col-sm-9 form-inline"  >
                                    <input id="uname" name="uname" size="35" class="form-control"  type="text" readonly="readonly" value="${scheduled.uname}"  tip="请输入预定设备" />
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row rowmargin">
                        <div class="col-sm-7">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><font color="red">*</font>设备id </label>
                                <div class="col-sm-9 form-inline"  >
                                    <input id="uid" name="uid" size="35" class="form-control"  type="text" readonly="readonly" value="${scheduled.uid}"  tip="请输入设备id" />
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row rowmargin">
                        <div class="col-sm-7">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><font color="red">*</font>预定人姓名 </label>
                                <div class="col-sm-9 form-inline"  >
                                    <input id="name" name="name" size="35" class="form-control" readonly="readonly" type="text" value="${scheduled.name}"  tip="请输入预定人姓名" />
                                </div>
                            </div>
                        </div>
                    </div>

					                        <div class="row rowmargin">
                            <div class="col-sm-7">
                                <div class="form-group">
                                                                            <label class="col-sm-3 control-label"><font color="red">*</font>预定时间 </label>
                                                                        <div class="col-sm-9 form-inline"  >
                                            <input id="time" name="time" size="35" class="form-control"  type="text" value="${scheduled.time}"  tip="请输入预定时间" />
                                    </div>
                                </div>
                            </div>
                        </div>
					                        <div class="row rowmargin">
                            <div class="col-sm-7">
                                <div class="form-group">
                                                                            <label class="col-sm-3 control-label"><font color="red">*</font>预定时长 </label>
                                                                        <div class="col-sm-9 form-inline"  >
                                            <input id="standing" name="standing" size="35" class="form-control"   type="text" value="${scheduled.standing}"  tip="请输入预定时长" />
                                    </div>
                                </div>
                            </div>
                        </div>
					                        <div class="row rowmargin">
                            <div class="col-sm-7">
                                <div class="form-group">
                                                                            <label class="col-sm-3 control-label"><font color="red">*</font>预定原因 </label>
                                                                        <div class="col-sm-9 form-inline"  >
                                            <input id="reason" name="reason" size="35" class="form-control"   type="text" value="${scheduled.reason}"  tip="请输入预定原因" />
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
                                upwd:{
                                    required:true,
                    },
                                name:{
                                    required:true,
                    },
                                tel:{
                                    required:true,
                                    telphone:true,
                    },
            },
    messages:{
                                    uname:{
                                                            required:'用户名不能为空',
                                                            
                            },
                                                upwd:{
                                                            required:'密码不能为空',
                            },
                                                name:{
                                                            required:'姓名不能为空',
                            },
                                                tel:{
                                                            required:'电话不能为空',
                                                            telphone:'电话格式错误',
                            },
    }
});
});
    </script>
</html>