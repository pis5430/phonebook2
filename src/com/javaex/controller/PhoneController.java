package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

//리다이렉트 코드랑 포워드 정리하기


@WebServlet("/pbc") //가상의 주소 PhoneController
public class PhoneController extends HttpServlet {

    public PhoneController() { //기본생성자, 생략가능(지워도됨)
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath()); 기본생성
		
		//컨트롤러 테스트(이클립스 출력창에 나오는 문구)
		System.out.println("controller");
		
		//파라미터 action값을 읽어서
		String action = request.getParameter("action");
		System.out.println(action); //null 값으로 나옴
		
		
		if("wform".equals(action)) {
			System.out.println("등록폼 처리");

			/*
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp"); //포워드함
			rd.forward(request, response);
			*/
			
			//포워드를 유틸에 넣어서 포워드 메소드로 이용하기
			WebUtil.forward(request, response, "./WEB-INF/writeForm.jsp");
			
			
		}else if("insert".equals(action)) { //등록
			System.out.println("전화번호 저장");
			
			//파라미터 3개값 꺼내기 
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//personVo 묶고			
			PersonVo personVo = new PersonVo(name,hp,company);
			
			
			//new dao 만들고			
			PhoneDao phoneDao = new PhoneDao();
			
			//dao personInsert() 에 저장 			
			phoneDao.personInsert(personVo);
			
			
			/*
			//다시 list.jsp 화면이 보이게 만들어줘야함 , 리다이렉트코드
			response.sendRedirect("/phonebook2/pbc?action=list");
			*/
			
			WebUtil.rdirecte(request, response, "/phonebook2/pbc");
			
			
		}else if("update".equals(action)) {
			System.out.println("정보 수정");
			
			//파라미터 4개값 꺼내기 
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("id"));
			
			//personVo 묶고			
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			System.out.println(personVo);
			
			//new dao 만들고	
			PhoneDao phoneDao = new PhoneDao();
			
			//dao personUpdate() 로 수정	
			phoneDao.personUpdate(personVo);
			
			WebUtil.rdirecte(request, response, "/phonebook2/pbc");// WebUtil사용
			
			
		}else if("upForm".equals(action)) {
			
			System.out.println("수정폼");
			
			int pId = Integer.parseInt(request.getParameter("id")); //id값을 잘못줌..ㅜㅜ
			
			//한명만 불러와야함 getPerson (수정폼에 들어가잇을 정보 불러오기 )
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVo = phoneDao.getPerson(pId);
			   
			//id 데이터값을 전달 --> updateForm에서 request.getAttribute("personId");
			request.setAttribute("pVo", personVo);
			
			//포워드를 유틸에 넣어서 포워드 메소드로 이용하기
			WebUtil.forward(request, response, "/WEB-INF/updateForm.jsp");
			
			
		}else if("delete".equals(action)) {
			
			System.out.println("정보 삭제");
			
			int pId = Integer.parseInt(request.getParameter("id")); //id값을 잘못줌..ㅜㅜ
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(pId); //삭제
			
			// 사이사이 중복되는 코드값들은 바깥으로 뺄수 있을것 같음..dao ,vo
			
			response.sendRedirect("/phonebook2/pbc");
	
		}else { //기본값을 리스트로 설정
			
			//action = list --> 리스트 출력 관련
			
			//리스트 출력 관련
			
			//리스트 출력 처리
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList =	phoneDao.getPersonList();
			
			//System.out.println(personList.toString());  테스트용
			
			//html --> 엄청복잡 -->jsp에서 작업하는게 편함 (작업할 list.jsp 생성)
			
			//데이터 전달
			request.setAttribute("pList", personList); //request.setAttribute("별명", 실제이름);
			
			/*
			//jsp에 포워드 시킨다.
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp"); //request.getRequestDispatcher(jsp파일 위치 )
			rd.forward(request, response);
			 */
			
			//포워드를 유틸에 넣어서 포워드 메소드로 이용하기
			WebUtil.forward(request, response, "./WEB-INF/list.jsp");
			
			
		}
				
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // 이렇게 열어두면 doGet으로 감
	}

}
