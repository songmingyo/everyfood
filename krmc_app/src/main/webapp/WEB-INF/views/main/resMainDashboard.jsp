<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"		var="userType" />
</sec:authorize>



<style>
	.pagination li{font-size: 0.7em; }
	.pagination > li > a {margin-bottom: 10px;}
	
	 #ContentTableBody{
            width: 700px;
            overflow: auto;
            overflow-y: hidden;
            margin: 10 auto;
	}
	
		
	.visual_res { border-top: 1px solid #9c9c9a;   border-bottom: 1px solid #bebebe; clear:both; background:url('/resources/images/pearl/main/visual_img_10.jpeg') center top no-repeat;  width:100%; height:150px; background-size: 100%  100%;   position:relative;
	 border: 1px solid #46545f; border-radius: 0.6em;
	} 
	

</style>
<script>
$(document).ready(function($){
	 var token = $("meta[name='_csrf']").attr("content");
	 var header = $("meta[name='_csrf_header']").attr("content");
	 $(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	 });

	/*BUTTON EVENT ---------------------------------------------------------------------------------*/
	$('#btnCommunity').unbind().click(null, 	searchCommunityist);	<%-- 최근 커뮤니티 글조회 --%>
	/*----------------------------------------------------------------------------------------------*/

	searchCommunityist();	<%-- 최근 커뮤니티 글조회 --%>
	
});

 
 
 

	<%-- 커뮤니티 최근글 조회  --%>
	function searchCommunityist(){

	    var paramInfo 	= 	{};
	    var eleHtml 	= [], h = -1;
	    var rowCount 	= 5;
	    var boardCd  	= "001";

		paramInfo["boardCd"]	=	boardCd					;	<%-- 게시판 코드  		--%>
		paramInfo["rowCount"]	=	rowCount				;   <%-- 최근글갯수  		--%>
		paramInfo["boardTitle"]	=	$('#boardTitle').val()	;   <%-- 글제목(검색어)  	--%>
		console.log(paramInfo);

		  $.ajax({
		      url: '<c:url value="/app/common/communityList"/>',
		      data: JSON.stringify(paramInfo),
		      type: "POST",

		      beforeSend: function(xhr) {
		          xhr.setRequestHeader("Accept", "application/json");
		          xhr.setRequestHeader("Content-Type", "application/json");
		      },
		      success: function(data) {

		    	  var sz 		= data.length;

					setTbodyInit("communityList");	// 그리드 초기화
					if(sz <= 0)  {
						setTbodyNoResult("communityList",4);
						return;
					}

					$("#communityListTemplate").tmpl(data).appendTo("#communityList");
					$('#communityList tr').css('cursor','pointer');


					for (var j=sz; j<rowCount; j++) {
						eleHtml[++h] = "<tr >\n";
						for (var i=0; i<4; i++) {eleHtml[++h] = "<td>&nbsp;</td>\n"; }
						eleHtml[++h] = "</tr>\n";
					}

					$("#communityList").append(eleHtml.join(''));

		      }
		  });
		
	}

</script>





<!--최근5개 본사공지사항 Template [communityList]-->
<script id="communityListTemplate"  type="text/x-jquery-tmpl">
<tr id="\${BOARD_IDX}_\${BOARD_CD}" onclick="_fn_openCommunityPopup('\${BOARD_IDX}','\${BOARD_CD}' );">
<td class="text-center">
		{%if IS_URGENCY_YN == 'Y'%}
			<span class="label label-danger">중요</span>
		{%else%}
			<span class="label label-default">일반</span>
		{%/if%}
	</td>
	<td><p style="width: 300px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; "><a href='javascript:void(0);'>\${BOARD_TITLE}</a></p></td>
    <td class="text-center">\${USER_NM}</td>
    <td class="text-center">\${REG_DT_YMD}</td>
</tr>
</script>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
    	<h1> <small>Dashboard[My Page]</small></h1>
		<ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
		</ol>
	</section>
	
	 <!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div >
		 	<div class="visual_res"  id="PROG_CATEGORY" >
		 		<div style=" padding-top: 20px; padding-left: 0px;  text-align:center;" >
		 			<div><img src="/resources/images/pearl/main/krmc_img02.png" width="200px;"></div>
		 			<div style="position: absolute; bottom: 10px; width: 100%;  ">
			 			
							
			 			<div class="btn-group"  >
			 				<div style="color: #fff; opacity: 0.8; text-align: left; font-weight: bolder;"><small><i class="fa  fa-spinner"></i> Quick Menu</small></div>
			 				<button class="btn btn-primary"  onClick="_setNextUrl('/app/res/sm/custsales/cusOrdReg','10750','');" 		style="opacity: 0.9;"><i class="fa  fa-server"></i> 발주등록</button>
			 				<button class="btn btn-primary" onClick="_setNextUrl('/app/res/sm/custsales/cusOrdRegList','10751','');"	style="opacity: 0.9;"><i class="fa  fa-sign-in"></i> 발주조회</button>
			 				<c:if test="${userSession.userType == 'SA' || userSession.userType == 'SU'}">
			 					<button class="btn btn-primary" onClick="_setNextUrl('/app/res/sm/custsales/cusStkList','10752','');" 	style="opacity: 0.9;"><i class="fa  fa-cubes"></i> 재고조회</button>
			 				</c:if>
			 			</div>
			 		</div>
			 	</div>
		 	</div>	
	 	</div>
        <!-- /.Small boxes (Stat box) -->
        
        
        <!-- content Contract list -->
        <c:if test="${userSession.userType != 'B1' && userSession.userType != 'S1' && userSession.userType != 'S2' && userSession.userType != 'GU'}">
         <div class="row" style="padding-top: 5px;">
          <!-- content Community list -->  
            <div class="col-md-4">
              <div class="box box-primary">
                <!-- box-header -->
                <div class="box-header">
                  <h3 class="box-title">최근 공지사항</h3>
                  <div class="box-tools">
                    <div class="input-group">
                    	 <button id="btnCommunity" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
                    </div>
                  </div>
                </div>
                <!-- /.box-header -->
                
                 <!-- Data List box-body -->
                <div class="box-body table-responsive no-padding">
                  <div id="ContentTableBody">
                  <table class="table table-hover"  id="communityTable">
                  	<colgroup>
						<col width="100px">
						<col width="*">  
						<col width="100px">  
						<col width="140px"> 
    				</colgroup>
                    <thead>
                 		<tr>
                    		<th style="text-align: center">#</th>
                    		<th style="text-align: center">제목</th>
                    		<th class="text-center">작성자</th>
                    		<th class="text-center">등록일</th>
                  		</tr>
                  	</thead>
                    <tbody id="communityList" data-link="row" class="rowlink hand-cursor">
                  </table>
                  </div>
                </div>
                 <!-- /.Data List box-body -->
              </div><!-- /.box -->
            </div>
          </div>
          </c:if>
           <!-- /. content Community list -->  
           
        
	</section>	
	
	
 </div>
 
 

 <form id="searchFrm" name="searchFrm" method="post">
	<sec:csrfInput/>
</form>
 

<!-- 본사공지사항 상세보기 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/common/commonView/cmmCommunityPopView.jsp" />
<!-- 본사공지사항 상세보기 레이어 영역 end  -->
