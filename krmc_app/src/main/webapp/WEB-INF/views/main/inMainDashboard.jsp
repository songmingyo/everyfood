<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<spring:eval expression="@config['Common.Decorator.Menu.Tabs']"		var="decoratorMenuTabs" />
<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"		var="userType" />
</sec:authorize>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="generator" content="ICERT">
	<meta name="author" content="ICERT">
	<meta name="keywords" content="">
	<meta name="description" content="">
	
	
<script type="text/javascript">
		$(document).ready(function($){

			onLoaddata();

			$('#prog_sum_box').delegate( "label", "click", function() {
				conProgressStateList($(this).attr("id"));	/* 선택된 LABEL object ID  */
			});

		});
		
		function onLoaddata(){
			  if($("#sumDetailList").length > 0){
					$('#sumDetailList').hide();
					progressState();		//카운트 정보 조회(로그인사용자 )
			  }
		}



		<%-- 카운트 정보 상세 페이지 이동 --%>
		function conProgressStateList(sumCd){

		var sumCnt		= $.trim($('#'+sumCd).text());		/* 선택된 LABEL object 내용(카운트값)				*/
			var appliStatCd =[];								/* 합계유형별 진행상태 'n'개 코드 Parameter처리 	*/
			if(!sumCnt || sumCnt == "0" ) return;				/* 카운트 결과 값이 없거나 '0'일경우 상세조회 예외처리  */
			
			/**
				페이지 이동 로직
			*/

			
		}



		<%--  집계카운트 --%>
		function progressState(){
			var searchInfo = {};
			
			$.ajax({
				contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
				url  : '<c:url value="/main/mainDashboard_selSumCont" />',
				data : JSON.stringify(searchInfo),
				success : function(data){

					/*  집계카운트 Set Data--*/
					if(data.PROG != null) {
						$.each(data.PROG, function(key, value){
						//	$('#PROG_CATEGORY label[id="'+key+'"]').text(numberComma(value));	
							numberAn($('#PROG_CATEGORY label[id="'+key+'"]'), value);
								
						});
					}
				}
			});
		}




	

		<%-- 선택된 대상  이동(공통) --%>
		function detailLaunchingView(rowData){
			if (jQuery.isEmptyObject(rowData)) return;
			
			var appliNo = rowData.appliNo;
			var saleChnnCd = rowData.saleChnnCd;
			var appliStatCd = rowData.appliStatCd;
			
			var f = document.securityDetailForm;
			$("#securityDetailForm input[name='appliNo']").val(appliNo);
			
			var url = "";
			
			<sec:authorize access="isAuthenticated()">
				<c:choose>
					<c:when test="${userSession.userType == 'SA'}">
					url = "<c:url value='/app/lnch/lnchPropMgrMst_selDetail.do?_code=10608'/>";
					</c:when>
					<c:when test="${userSession.userType == 'SU'}">
						if($.trim(saleChnnCd) == "3004001"){
							url = "<c:url value='/app/lnch/lnchPropMgrTv_selDetail.do?_code=10614'/>";
						}else if($.trim(saleChnnCd) == "3004002"){
							url = "<c:url value='/app/lnch/lnchPropMgrMc_selDetail.do?_code=10615'/>";
						}else{
							alert("선택하신 제안 건의 채널 정보가 존재하지 않아 제안 상세정보에 접근하실 수 없습니다.");
							return;
						}
					</c:when>
					<c:otherwise>
						if($.trim(appliStatCd) == "2054010"){
							url = "<c:url value='/front/lnch/lnchProposal?_code=10605'/>";
						}else{
							url = "<c:url value='/front/lnch/lnchPropPrcond_selDetail?_code=10626'/>";
						}
					</c:otherwise>
				</c:choose>
			</sec:authorize>
			
			f.action = url;
			f.submit();
		}


		function numberAn(objId, memberCountConTxt){
			  $({ val : 0 }).animate({ val : memberCountConTxt }, {
			   duration: 500,
			  step: function() {
			    var num = numberWithCommas(Math.floor(this.val));
			    objId.text(num);
			  },
			  complete: function() {
			    var num = numberWithCommas(Math.floor(this.val));
			    objId.text(num);
			  }
			});
		}

		function numberWithCommas(x) {
		    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}

		//페이지 이동
		function _eventMove(type){
			var f = document.securityDetailForm;
			
			if($.trim(type)=="G"){
				f.action = "<c:url value='/web/lnch/menu1.do' />";
				
			}else if($.trim(type)=="J"){
				f.action = "<c:url value='/security/securityJoinus' />";
				
			}else if($.trim(type)=="C"){
				f.action = "<c:url value='/front/lnch/lnchPropPrcond' />";
			}
			
			f.submit();
		}

	</script>
	
	
	<script id="progressStateListTemplate"  type="text/x-jquery-tmpl">
		<tr>	
			<td style="text-align:center;">\${COUNT}</td>
			<td style="text-align:left;">\${COMP_NM}</td>
			<td style="text-align:left;">\${MAIN_PROD_NM}</td>
			<td style="text-align:center;">\${APPLI_DY}</td>
			<td style="text-align:center;">\${APPLI_NO}
				<input type='hidden' id='appliNo' 		value='\${APPLI_NO}'/>
				<input type='hidden' id='appliStatCd'	value='\${APPLI_STAT_CD}'/>
				<input type='hidden' id='saleChnnCd'	value='\${SALE_CHNN_CD}'/>
			</td>
		</tr>
	</script>
	
</head>
<body>

 <div class="visual" >
 		<div class="visual_con">
			<p class="visual_text_member"></p>
			<div class="main_ing" id="PROG_CATEGORY">
					
					<div class="main_ing" id="PROG_CATEGORY" >
						<%-- 
						<p class="visual_text_member"><i class="fa fa-bar-chart"></i> 진행 중인 나의 정보</p>
						--%>
						<ul class="ul_main_ing" id="prog_sum_box" >
						<%--
						<c:choose>
							<c:when test="${userSession.userType == 'SA' || userSession.userType == 'SU'}">
							
								<li style="width: 185px;" >
									<!-- Default small shadow-->
					                <div class="card shadow-sm" style="background-color: #727272;">
					                    <div class="card-body" >
					                      	<h2>매입처 발주</h2>
					                       <span><label id="PRG_SUM_TB">0</label><small>건</small></span>
					                        <div class="quick_menu_head" style="text-align:center; line-height:25px; border-top: 1px solid #5a5959;"> 매입처 별 발주 대상 정보</div>
					                    </div>
					                </div>
								</li>
								<li style="width: 35px; height:24px; margin-top: 50px; text-align: center; " >
									<i class="fa  fa-arrow-right" style="color: #fff;" ></i>
								</li>
								
								
								<li style="width: 185px;">
									<div class="card shadow-sm" style="background-color: #47768d;">
					                    <div class="card-body">
					                        <h2>매입처 출고</h2>
					                        <span><label id="PRG_SUM_TC">0</label><small>건</small></span>
					                        <div class="quick_menu_head"  style="text-align:center; line-height:25px; border-top: 1px solid #3b5764;">매입처 별 출고대상 정보</div>
					                    </div>
					                </div>
								</li>
							
								<li style="width: 35px; height:24px; margin-top: 50px; text-align: center; " >
									<i class="fa  fa-arrow-right" style="color: #fff;" ></i>
								</li>
								
								<li style="width: 185px;">
									<div class="card shadow-sm" style="background-color: #47768d;">
					                    <div class="card-body">
					                        <h2>매출실적(전월/당월)</h2>
					                        <span><label id="PRG_SUM_TG"></label><small>%</small></span>
					                        <div class="quick_menu_head"  style="text-align:center; line-height:25px; border-top: 1px solid #3b5764;">전월 대비 당월 매출식적 </div>
					                    </div>
					                </div>
								</li>
								
								<li style="width: 35px; height:24px; margin-top: 50px; text-align: center; " >
									<i class="fa  fa-arrow-right" style="color: #fff;" ></i>
								</li>
								
								<li style="width: 170px;">
									<div class="card shadow-sm" style="background-color: #c44c3b;">
					                    <div class="card-body">
					                        <h2>관리대상 재고</h2>
					                        <span><label id="PRG_SUM_TK">0</label><small>건</small></span>
					                        <div class="quick_menu_head"  style="text-align:center; line-height:25px; border-top: 1px solid #9c3c2e;">악성재고 및 폐기 로스 ...</div>
					                    </div>
					                </div>
								</li>
								
							</c:when>
							<c:otherwise>
								
								<li style="width: 185px;" >
									<!-- Default small shadow-->
					                <div class="card shadow-sm" style="background-color: #727272;">
					                    <div class="card-body" >
					                      	<h2>발주요청</h2>
					                       <span><label id="PRG_SUM_TA">0</label><small>건</small></span>
					                        <div class="quick_menu_head" style="text-align:center; line-height:25px; border-top: 1px solid #5a5959;">발주요청 대상 </div>
					                    </div>
					                </div>
								</li>
								<li style="width: 35px; height:24px; margin-top: 50px; text-align: center; " >
									<i class="fa  fa-arrow-right" style="color: #fff;" ></i>
								</li>
							 
								<li style="width: 185px;" >
									<!-- Default small shadow-->
					                <div class="card shadow-sm" style="background-color: #47768d;">
					                    <div class="card-body" >
					                      	<h2>반품요청</h2>
					                       <span><label id="PRG_SUM_TP">0</label><small>건</small></span>
					                        <div class="quick_menu_head" style="text-align:center; line-height:25px; border-top: 1px solid #3b5764;">반품요청 정보</div>
					                    </div>
					                </div>
								</li>
								<li style="width: 35px; height:24px; margin-top: 50px; text-align: center; " >
									<i class="fa  fa-arrow-right" style="color: #fff;" ></i>
								</li>
								
								<li style="width: 185px;">
									<div class="card shadow-sm" style="background-color: #47768d;">
					                    <div class="card-body">
					                        <h2>미납정보</h2>
					                        <span><label id="PRG_SUM_TG">0</label><small>건</small></span>
					                        <div class="quick_menu_head"  style="text-align:center; line-height:25px; border-top: 1px solid #3b5764;">발주 미납 정보</div>
					                    </div>
					                </div>
								</li>
							
								<li style="width: 35px; height:24px; margin-top: 50px; text-align: center; " >
									<i class="fa  fa-arrow-right" style="color: #fff;" ></i>
								</li>
									
								<li style="width: 185px;">
									<div class="card shadow-sm" style="background-color: #c44c3b;">
					                    <div class="card-body">
					                        <h2>대금정산</h2>
					                        <span><label id="PRG_SUM_TN">0</label><small>건</small></span>
					                        <div class="quick_menu_head"  style="text-align:center; line-height:25px; border-top: 1px solid #9c3c2e;">대금지급 내역 정보</div>
					                    </div>
					                </div>
								</li>
								
								
							</c:otherwise>
						</c:choose>	
						--%>
					</ul>
				</div>
		
				
				
				
			</div>
		</div>
	</div>


	<div class="content_body">
		
		
			<div class="content_layout" style="margin-top: 10px;">
				<div  class="notices" style="width: 1000px;">
				<c:if test="${userSession.userType != 'B1' && userSession.userType != 'S1' && userSession.userType != 'S2' && userSession.userType != 'GU'}">
	 				<h1>공지사항
						<a class="btn-more" href="<c:url value="/web/community/001/community"/>">MORE &gt;</a>
					</h1>
					<c:import url="/app/common/inc/communityFullList" charEncoding="utf-8">
							<c:param name="boardCd" 	value="001" />
							<c:param name="rowCount" 	value="10" />
					</c:import>
				</c:if>
			   </div>
				
			</div>
		
		
	</div>
	
	<form id="securityDetailForm" name="securityDetailForm" method="post">
		<sec:csrfInput/>
		<input type="hidden" name="appliNo"/>
	</form>


</body>
</html>

 s 