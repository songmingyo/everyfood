<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp" />


<spring:eval expression="@config['Common.Decorator.Res.Menu.Type']" 	var="decoratorResMenuType"/>

<script type="text/javascript">
var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;
	$(document).ready(function($){

	/*	  
		if(isMobile) {
			$('#ToggleNav').hide(); 
		}	 
		else $('#ToggleNav').show();
		*/
	});

	function logout(){
		$('#logoutForm').submit();
		return false;
	}

</script>
 
<header class="main-header">
	<!-- Logo -->
	<a href='<c:url value="/res/main/mainDashboard"/>' class="logo">
		<!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><img src='<c:url value="/resources/favicon.ico"/>' width="40px;"></span>
		<!-- logo for regular state and mobile devices -->
		<span class="logo-lg" ><img src="<c:url value="/resources/images/pearl/main/logo.png"/>" width="80px;"> </span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
	
		<!-- Sidebar toggle button-->		

		 <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" id="ToggleNav">
        	<span class="sr-only">Toggle navigation</span>
         </a>
	
          <span style=" display:inline-block; margin-top: 15px; margin-left: 20px; line-height: 90%;">
          	<strong>코리아알엠씨</strong><br><small style="color: #757d87; font-size: 0.7em;"> <strong>KOREA</strong> <strong>R</strong>esturant <strong>M</strong>anagement <strong>C</strong>enter</small>
          </span> 
         
          <div class="navbar-custom-menu">
         
          	<ul class="nav navbar-nav">
            
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <span style="font-size: 0.85em; font-weight: bolder;">
              	
                 <sec:authorize access="isAuthenticated()">	
                 	<div><sec:authentication property="principal.fullName"/></div>
                 </sec:authorize>
                 <sec:authorize access="isAnonymous()">
                 	<a th:href="@{/security/securityLogin}" >Login</a>
                 </sec:authorize>
 
                  </span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header" style="margin-top: -1px; height: 50px;">
                    <p  style="margin-top: -5px;"><sec:authentication property="principal.fullName"/> (<sec:authentication property="principal.username"/>)
                       <small><sec:authentication property="principal.compNm"/> (<sec:authentication property="principal.masterId"/>)</small> 
                    </p>
                  </li>
                   
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div>
                     <%-- <a href="<c:url value='/login/logoutReActionProc.ra'/>" class="btn btn-default btn-flat">Sign out</a> --%>
                      <a class="btn btn-default btn-flat"  onclick="logout();">Sign out</a>
                    </div>
                  </li>
                  <!-- Control Sidebar Toggle Button -->
              
                  
                </ul>
              </li>
             
              <!-- Control Sidebar Toggle Button -->
             
              
            </ul>
           
          </div>
        </nav>
     </header>
