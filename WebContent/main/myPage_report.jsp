<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ page import="com.main.board.*" %>

<fmt:requestEncoding value="UTF-8"/>    
<jsp:useBean id="rboardDAO" class="com.main.board.boardDAO"/>
<jsp:useBean class="com.koreait.member.MemberDTO" id="memberDTO" />
<jsp:useBean class="com.koreait.member.MemberDAO" id="memberDAO" />
<jsp:setProperty property="*" name="memberDTO"/>

<c:set var="countColumn" value="${rboardDAO.countColumn()}"/>
<c:set var="fileListidx" value="${rboardDAO.selectListidx()}"/>

<%
	String email = null;
	String name = null;
	if(session.getAttribute("email") != null){
		email = (String)session.getAttribute("email");
		name = (String)session.getAttribute("name");
	}
 
%>
<%
String url = "jdbc:mariadb://localhost:3306/webdev";
String uid = "root";
String upw = "1234";
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String mem_email = (String) session.getAttribute("email");
String mem_profilePic = "";
String mem_address3 = "";
String mem_username = "";

try {
	Class.forName("org.mariadb.jdbc.Driver");
	conn = DriverManager.getConnection(url, uid, upw);
	if(conn != null){
		
		String sql = "SELECT mem_profilePic, mem_address3, mem_username FROM pj_member WHERE mem_email=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mem_email);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			mem_profilePic = rs.getString("mem_profilePic");
			mem_address3 = rs.getString("mem_address3");
			mem_username = rs.getString("mem_username");
		}
	}
}catch(ClassNotFoundException e){
	e.printStackTrace();
}catch(SQLException e){
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>myPage</title>
    <link rel="stylesheet" href="./css/myPage.css?ver=123" />
     <link rel="stylesheet" href="./css/mypage_object.css?ver=1234">
     <link rel="stylesheet" href="./css/mypage_object_style.css?ver=12345">
      <link rel="stylesheet" href="./css/mypage_object_write.css?ver=123456">
  </head>
  <body>
<% 
	if(email == null){
%>
	
<%		
	}else{
%>
    <header id="fixedBar" class="fixedBarBoxShadow">
      <div id="fixedBarWrap">
        <h1 id="fixedBarLogoTitle">
          <a href="MainPageLogined.jsp">
            <span class="srOnly">????????????</span>
            <img
              class="fixedLogo"
              alt="????????????"
              src="./images/carrot.PNG"
            />
          </a>
        </h1>
        <!-- 
        <section id="fixedBarSearch">
          <div class="searchInputWrap">
            <span class="srOnly">??????</span>
            <input
              type="text"
              name="headerSearchInput"
              id="headerSearchInput"
              class="fixedSearchInput"
              placeholder="?????? ??????, ????????? ?????? ??????????????????!"
            />
            <button id="headerSearchButton">
              <img
                class="fixedSearchIcon"
                alt="Search"
                src="https://d1unjqcospf8gs.cloudfront.net/assets/home/base/header/search-icon-7008edd4f9aaa32188f55e65258f1c1905d7a9d1a3ca2a07ae809b5535380f14.svg"
              />
            </button>
          </div>
        </section>
         -->
        <section id="fixedBarLoginRegist">
          <h3 class="hide">????????????/????????????</h3>
          <p>
          <input type="button" value="????????????" onclick="location.href='./logout.jsp'" 
          	style="margin-right: 8px;
          	width: 140px;
    		border-radius: 5px;
    		border: solid 1px #e9ecef;
    		box-sizing: border-box;
    		text-decoration: none;
    		display: inline-block;
    		vertical-align: middle;
    		text-align: center;
    		padding: 0.9rem 0;
    		background-color: #none">

          <input type="button" value="?????? ??????" onclick="location.href='./myPage.jsp'" 
          	style="margin-right: 8px;
          	width: 140px;
    		border-radius: 5px;
    		border: solid 1px #e9ecef;
    		box-sizing: border-box;
    		text-decoration: none;
    		display: inline-block;
    		vertical-align: middle;
    		text-align: center;
    		padding: 0.9rem 0;">
         </p>
        </section>
      </div>
    </header>

    <section id="content">
      <section id="content">
        <section id="userProfile">
          <h2 id="nickname">
            <%=name %>
            <span id="regionName"><%=mem_address3 %></span>
            <input
              type="button"
              value="????????????"
              onclick="location.href='./profile.jsp'"
              style="
                margin-right: 8px;
                width: 140px;
                border-radius: 5px;
                border: solid 1px #e9ecef;
                box-sizing: border-box;
                text-decoration: none;
                display: inline-block;
                vertical-align: middle;
                text-align: center;
                padding: 0.9rem 0;
                margin-left: 200px;"/>
                
                <input
              type="button"
              value="????????????"
              onclick="location.href='../board/list.jsp'"
              style="
                margin-right: 8px;
                width: 140px;
                border-radius: 5px;
                border: solid 1px #e9ecef;
                box-sizing: border-box;
                text-decoration: none;
                display: inline-block;
                vertical-align: middle;
                text-align: center;
                padding: 0.9rem 0;
                margin-left: 276.5px;"/>
          </h2>
          <ul id="profileDetail">
            <li class="profileDetailTitle">
              ????????????
              <span class="profileDetailCount">38.8??C</span>
            </li>
            <li class="profileDetailTitle">
              ??????????????????
              <span class="profileDetailCount">100%</span>
            </li>
          </ul>
          <div id="profileImage">
            <img src="/Corrot/upload/<%=mem_profilePic %>" alt="<%=name %>"/>
          </div>
        </section>
        <div id="userRecordsDetail">
          <section id="userFilter">
            <ul>
              <li>
                <a class="" href="./myPage.jsp">????????????</a>
              </li>
              <li>
                <a class="active" href="myPage_report.jsp">????????? ???</a>
              </li>
            </ul>
          </section>
          <section
            id="userRecords"
            class="userArticles"
            data-total-page="1"
            data-current-page="1"
          >
            <h3 class="hide">????????? ??????</h3>
           
       <section class="cards-wrap" style="width:677px;">
              <c:set var = "keyWord" value="${param.keyWord}" scope="session"/>
    
    	<c:set var="fList" value="${rboardDAO.selectListAll(keyWord)}"/>
	    	<c:forEach var="item" items="${fList}" varStatus="status">
	    	
		
	        <article class="card-top ">
					
	        		<p style="display:none" id ="pageindex">${item.idx}</p>
	        		<!-- index/-->
	        		
		         	<!-- ??????????????? -->
		            <a class="card-link " data-event-label="142863274" href="../board/view.jsp?pageindex=${item.idx}">		       
		            <div class="card-photo ">
		            <img alt="${item.file1} " src="/Corrot/upload/${item.file1}?q=82&amp;s=300x300&amp;t=crop">
		            </div>
		            <div class="card-desc">
		                <h2 class="card-title"> <p>${item.title}</p></h2>
		                
		                <div class="card-region-name">
		                    	<!--?????? ????????? ??????????????? ?????????/ ????????????-->
		                    	<p><%=name %></p>
		            	</div>
		            </div>
		            </a>
	               		
	          
	        </article>
	      
	        </c:forEach>
            </section>
          </section>
        </div>
      </section>
    </section>
   

    <footer id="footer">
      <div class="footerContainer">
        <div class="footerTop">
          <div class="footerLogo">
            <img
              class="fixedLogo"
              alt="????????????"
              src="https://d1unjqcospf8gs.cloudfront.net/assets/home/base/header/logo-basic-24b18257ac4ef693c02233bf21e9cb7ecbf43ebd8d5b40c24d99e14094a44c81.svg"
            />
          </div>
          <ul class="footerList">
            <li class="footerListItem">
              <a class="linkHighlight" href="">?????? ??? ?????? ????????????</a>
            </li>
            <li class="footerListItem">
              <a class="linkHighlight" href="">?????? ?????? ??????</a>
            </li>
          </ul>
          <ul class="footerList">
            <li class="footerListItem">
              <a target="_blank" href="">?????? ??????</a>
            </li>
            <li class="footerListItem">
              <a target="_blank" href="">???????????????</a>
            </li>
            <li class="footerListItem">
              <a
                target="_blank"
                class="gaClick"
                data-event-category="townLinkFrom"
                data-event-action="indexBase"
                data-event-label="footerTown"
                href=""
                >????????????</a
              >
            </li>
          </ul>
          <ul class="footerList policy">
            <li class="footerListItem">
              <a target="_blank" href="">????????????</a>
            </li>
            <li class="footerListItem">
              <a target="_blank" href="">????????????????????????</a>
            </li>
            <li class="footerListItem">
              <a target="_blank" href="">????????????????????? ????????????</a>
            </li>
          </ul>
        </div>
        <div class="footerBottom">
          <div id="copyright">
            <ul class="copyrightList">
              <li class="copyrightListItem">
                ????????????
                <a href="">cs@daangnservice.com</a>
              </li>
              <li class="copyrightListItem">
                ????????????
                <a href="">contact@daangn.com</a>
              </li>
            </ul>
            <ul class="copyrightList">
              <li class="copyrightListItem">????????? ???????????? : 375-87-00088</li>
              <li class="copyrightListItem">
                <address>??????????????? ????????? ???????????? 30??? 28 609???</address>
              </li>
            </ul>
            <div class="copyrightText">??Danggeun Market Inc.</div>
          </div>
          <div id="social">
            <ul class="socialList">
              <li class="socialListItem">
                <a target="_blank" class="footerSocialLink" href="">
                  <img
                    alt="facebook"
                    src="https://d1unjqcospf8gs.cloudfront.net/assets/home/base/footer/icon-facebook-0563f4a93852d073b41f13b8bcabb03d47af3bb3a6755cdfedd8a73686c7f18c.svg"
                  />
                  <span class="srOnly">facebook</span>
                </a>
              </li>
              <li class="socialListItem">
                <a target="_blank" class="footerSocialLink" href="">
                  <span class="srOnly">instagram</span>
                </a>
              </li>
              <li class="socialListItem">
                <a target="_blank" class="footerSocialLink" href="">
                  <img
                    alt="blog"
                    src="https://d1unjqcospf8gs.cloudfront.net/assets/home/base/footer/icon-blog-e1b0d512d1766a6962ec5bbb5b0803d2a6a9c55ad97db5ba9eebb76013caceba.svg"
                  />
                  <span class="srOnly">blog</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
 <% 
	}
 %>

  </body>
</html>
