<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
      <script type='text/javascript' src='<%=path %>/js/jquery-3.4.1.min.js'></script>
       <script type='text/javascript' src='<%=path %>/js/layer/layer.js'></script>
       
       
       <script type="text/javascript">
           <c:if test="${message!=null}">
               layer.msg("${message}")
           </c:if>
           <c:if test="${path!=null}">
           setTimeout(function(){
			   document.location.href="<%=path%>/${path}";
			},1500);
           </c:if>
       </script>
  </head>
  
  <body>
       
  </body>
</html>
