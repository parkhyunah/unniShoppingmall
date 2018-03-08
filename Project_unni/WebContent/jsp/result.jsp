<%-- 요청 결과를 띄울 alert창 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	alert("${msg}");
	location.href = "unni?command=${url}";
</script>