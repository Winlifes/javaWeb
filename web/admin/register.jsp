<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="<%=path%>/css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="<%=path%>/js/jquery-3.4.1.min.js"></script>
  <script src="<%=path%>/css/bootstrap/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/jquery-pdm.js"></script>
  <script src="<%=path%>/js/jquery.validate.min.js"></script>
  <script src="<%=path%>/js/jquery.validate.extend.js"></script>
  <script src="<%=path%>/js/jquery.validate.method.js"></script>
  <link href="<%=path%>/css/style.min.css" rel="stylesheet">
  <link href="<%=path%>/css/register.min.css" rel="stylesheet">
  <%--    标题栏icon--%>
  <link rel="icon" href="<%=path%>/images/favicon.png" type="image/png" />
  <%--    收藏夹icon--%>
  <link rel="shortcut icon" href="<%=path%>/images/favicon.png"/>

  <title>注册页面</title>
</head>
<body class="signin">
<div class="signinpanel">
  <div  align="center">
    <div style="margin-bottom:50px;font-size: 30px;margin-top: -50px;">注册页面</div>
    <div style="width:400px;" align="center">
      <form action="<%=path%>/tadmin_register.action" method="post" id="addForm">

        <input type="text" class="form-control uname" placeholder="用户名" id="uname" name="uname"  >
        <input type="password" class="form-control pword m-b" placeholder="密码" id="upwd" name="upwd"  >
        <input type="password" class="form-control pword m-b" placeholder="确认密码" id="upwd1" name="upwd1"  >
        <input type="text" class="form-control pword m-b" placeholder="姓名" id="name" name="name"  >
        <input type="text" class="form-control pword m-b" placeholder="电话" id="tel" name="tel"  >

        <button type="submit" class="btn btn-success btn-block">注册</button>
        <button type="button" id="rebackBtn" class="btn btn-success btn-block">返回</button>
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
          alert("注册成功");
        },
        rules: {
          uname:{
            required:true,
            remote: {
              url: "<%=path%>/tadmin_unameExist.action",
              type: "post",
              dataType: "json",
              data: {
                uname: function (){ return $("#uname").val();}
              },
              dataFilter: function (data) {
                return data;
              }
            }
          },
          upwd:{
            required:true,
          },
          upwd1:{
            required:true,
            equalTo: "#upwd"
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
            remote:'用户名已存在',
          },
          upwd:{
            required:'密码不能为空',
          },
          upwd1 : {
            required : '请输入确认密码',
            equalTo: '两次密码输入不一致'
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
