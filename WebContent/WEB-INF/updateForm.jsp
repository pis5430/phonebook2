<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import= "com.javaex.vo.PersonVo" %>

<%

//  수정을 위해선 vo에 넣어야함 get으로 namd, ph,company 불러와야함
//	int Id = (int)(request.getAttribute("personId"));	
//	PhoneDao phoneDao = new PhoneDao();
	PersonVo personVo = (PersonVo)request.getAttribute("pVo"); //PersonVo형변환
	System.out.println("personVo : " + personVo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>표이슬 전화번호 수정화면</h1>
	
	<p>
		수정화면 입니다.<br>
		아래항목을 수정하고 "수정"버튼을 클릭하세요
	</p>
	
	<form action="/phonebook2/pbc" method="get">
		이름(name) : <input type="text" name="name" value="<%=personVo.getName() %>"> <br>
		핸드폰(hp) : <input type="text" name="hp" value="<%=personVo.getHp() %>"> <br>
		회사(company) : <input type="text" name="company" value="<%=personVo.getCompany()%>"> <br>
		
		 <input type="hidden" name="id" value="<%=personVo.getPerson_id()%>">
		 <input type="hidden" name="action" value="update"> 
	<button type="submit" >수정</button>
		
	</form>
	
	<br>
	<a href="./list.jsp">리스트 바로가기</a>

</body>
</html>