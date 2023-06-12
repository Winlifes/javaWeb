<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=path%>/css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/left.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.4.1.min.js"></script>
<script src="<%=path%>/css/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-pdm.js"></script>
<title>菜单栏</title>
</head>
<body>


	<!--main-->
	<div class="container-fluid mybody">
		<div class="main-wapper">
			<!--菜单-->
			<div id="siderbar" class="siderbar-wapper">

				<div class="panel-group menu" id="accordion" role="tablist" aria-multiselectable="true">

					<div class="panel panel-inner">
						<div class="panel-heading panel-heading-self" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
									<span class="glyphicon glyphicon-list-alt"></span>
									系统管理
								</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="list-group list-group-self">
								<a href="<%=path%>/equipment_list.action" target="appiframe" class="list-group-item">
									<span class="glyphicon glyphicon-menu-right"></span>
									设备预定
								</a>
								<a href="<%=path%>/scheduled_list.action?tname=${sessionScope.cuser.uname}&xname=${sessionScope.cuser.name}" target="appiframe" class="list-group-item">
									<span class="glyphicon glyphicon-menu-right"></span>
									预定申请
								</a>
								<a href="<%=path%>/admin/tadmin/tadmin_upwd.jsp" target="appiframe" class="list-group-item">
									<span class="glyphicon glyphicon-menu-right"></span>
									修改密码
								</a>
								<a class="list-group-item">
									<span class="glyphicon glyphicon-menu-right"></span>
									功能开发中
								</a>

							</div>
						</div>
					</div>

					 
				 
					 





				</div>

			</div>
			<!--菜单-->
			<!--内容-->
			<div id="content" class="main-content">
				<iframe src="<%=path%>/equipment_list.action" style="width: 100%; height: 100%;" id="appiframe" name="appiframe" frameborder="0"></iframe>
			</div>
			<!--内容-->
		</div>

	</div>

	<!--main-->

</body>
</html>