<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>

<!-- PhoneController 포워드 시켰다 personList -->
<%

	List<PersonVo> personList =(List<PersonVo>)request.getAttribute("pList"); //List<PersonVo> 으로 형변환 , 주소를 list.jsp로 바로 들어갈수 없음 
	System.out.println("======list.jsp======");
	System.out.println(personList);


%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>phonebook 모델2</title>
</head>
<body>


	<h1>전화번호 리스트</h1>

	<p>입력한 전화번호 내역입니다.</p>


	<%for(int i = 0; i<personList.size(); i++){ %>
	<table border="1">
		<tr>
			<td>이름 (name) , id </td>
			<td><%=personList.get(i).getName() %>,<%=personList.get(i).getPerson_id()%></td>
		</tr>
		<tr>
			<td>핸드폰(hp)</td>
			<td><%=personList.get(i).getHp() %></td>
		</tr>
		<tr>
			<td>회사(company)</td>
			<td><%=personList.get(i).getCompany() %></td>
		</tr>
		<tr>
			<td><a href="/phonebook2/pbc?action=upForm&id=<%= personList.get(i).getPerson_id()%>">[수정]</a></td>
			<td><a href="/phonebook2/pbc?action=deform&id=<%= personList.get(i).getPerson_id()%>">[삭제]</a></td>
		</tr>
		
	</table>
	<br>
	<%} %>
	
	<br>
	
	<a href="/phonebook2/pbc?action=wform">추가번호 등록</a>


</body>
</html>