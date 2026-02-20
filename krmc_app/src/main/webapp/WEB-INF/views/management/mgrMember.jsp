<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script>

	/* onLoad or key event */
	$(document).ready(function($){
		initSearch();
		//eventSearch();

		//버튼클릭 이벤트 ---------------------------------------------------
		$('#btnSearch').click(function(e){
			/* 조회버튼을 클릭한 경우는 페이지번호는 1로 초기화 한다. */
			$("#page").val(1);
				eventSearch();
			/* 버튼이 form 안에 존재하면 e.preventDefault(); 필수이다. 안쓰면 autoSubmit 된다. */
			e.preventDefault();
		});

		$('#btnNew').unbind().click(null, 	 systemUserInsertLayer); 	// 신규버튼
		//-----------------------------------------------------------------


		//사용자명,아이디,소속 조회조건 입력필드 enter key이벤트 --------------
		$('#search_userNm, #search_userId').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : eventSearch(this); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------
		// 접속아이피갯수 선택시 이벤트
		$('#search_ipCnt').change(function() {
			var selected = jQuery('#search_ipCnt option:selected').val(); // 값 가져오기
			if(selected == '') {	// 값이 '전체' 이면 레이블 숨김
				$('#more').hide();
			} else {	// '전체' 가 아닐때 레이블 show
				$('#more').show();
			}
		});

		//grid resize
		$(window).bind('resize', function() {
		    try{
		        // width
		        // 그리드의 width를 div 에 맞춰서 적용
		        $('#tabList').setGridWidth($('#grid1container').width()); //Resized to new width as per window

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;
		        if(height > 275)
		        	$('#tabList').setGridHeight(height-142);
		        else if(height < 300)
		        	$('#tabList').setGridHeight(height-300);

		    }catch(e){}
		}).trigger('resize');

	});


	function initSearch(){
		$("#tabList").jqGrid({
			datatype: "local",  // 보내는 데이터 타입
			data: [],

			colNames:[
				'<spring:message code="app.manager.memberviewlist.head.code" />'			<%--고유번호 --%>
				,'<spring:message code="app.manager.memberviewlist.head.usernm" />'			<%--사용자명 --%>
				,'<spring:message code="app.manager.memberviewlist.head.userid" />'			<%--아이디 --%>
				,'<spring:message code="app.manager.memberviewlist.head.telno" />'			<%--전화 --%>
				,'<spring:message code="app.manager.memberviewlist.head.hpno" />'			<%--휴대전화 --%>
				,'<spring:message code="app.manager.memberviewlist.head.email" />'			<%--EMAIL --%>
				,'<spring:message code="app.manager.memberviewlist.head.useyn" />'			<%--사용여부 --%>
				,'<spring:message code="app.manager.memberviewlist.head.ipcnt" />'			<%--금일 접속아이피 갯수 --%>
				,'hidden' 																	<%-- 사용자 생성 유형 --%>
				,'<spring:message code="app.manager.memberviewlist.head.userCreateType" />'	<%-- 사용자 생성 유형 --%>
				,'hidden'																	<%-- 탈퇴여부확인 --%>
			],		// 헤더에 들어가는 이름
			colModel:[
				{name:'memberCd'		, index:'memberCd'		, sortable:true		, width:80		,align:"center", hidden:true},
				{name:'userNm'			, index:'userNm'		, sortable:true		, width:140		,align:"left"},
				{name:'userId'			, index:'userId'		, sortable:true 	, width:140		,align:"center"},
				{name:'telNo'			, index:'telNo'			, sortable:true		, width:100		,align:"center"},
				{name:'hpNo'			, index:'hpNo'			, sortable:true		, width:100		,align:"center"},
				{name:'email'			, index:'email'			, sortable:true		, width:140		,align:"center"},
				{name:'useYn'			, index:'useYn'			, sortable:true		, width:70		,align:"center"},
				{name:'ipCnt'			, index:'ipCnt'			, sortable:true		, width:130		,align:"center"},
				{name:'ifYn'			, index:'ifYn'			, sortable:true		, width:130		,align:"center", hidden:true},
				{name:'userCreateType'	, index:'userCreateType', sortable:true		, width:130		,align:"center"},
				{name:'withDrawFg'		, index:'withDrawFg'	, sortable:false	, width:130		,align:"center", hidden:true}
			],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
				var colCount = $(this).getGridParam("colNames").length;
				$("#blankRow td:nth-child(2)").attr("colspan", colCount)
											  .attr("style", "text-align: center;");
				$(window).resize();
			},
			loadComplete: function() {
				$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
				if ($(this).getGridParam("records")==0) {
					var firstColName = $(this).getGridParam("colModel")[1].name;
					var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
					$(this).addRowData("blankRow", msg);
					$(window).resize();
				}

// 				var allRows = jQuery("#tabList").jqGrid('getDataIDs');	// 전체 rowCount
// 				var ipCnt = "0";
// 				var memberCd = "";
// 				var withDrawFg = "";
// 				var withDrawMsg = "<spring:message code='app.manager.memberviewlist.head.withdrawY'/>";
// 				var setComment = "";
// 				var setClass   = "";
				
// 				for(var i = 0; i < allRows.length; i++){
// 					var cl = allRows[i];
// 					var rowAllData = jQuery("#tabList").getRowData(allRows[i]);
					
// 					ipCnt			= rowAllData.ipCnt;
// 					memberCd		= rowAllData.memberCd;
// 					withDrawFg		= rowAllData.withDrawFg;
					
// 					if(ipCnt) {
// 						if(Number(ipCnt) 	  > 4) setColor  = "#9e0a04";	//나쁜사람
// 						else if(Number(ipCnt) > 1) setColor  = "#e08e0b";	//조금나쁜사람
// 						else setColor  						 = "#3c8dbc";	//착한사람

// 						setComment  = "<input type='button' class='button btn_white' style='color:"+setColor+";' value='"+ipCnt+"' onclick=\"_eventDetailEvent('"+memberCd+"' );\" />";
// 					}
// 					else setComment = "Not Access!";

					
// 					jQuery("#tabList").jqGrid('setRowData', cl, {'ipCnt':setComment});

// 					if(withDrawFg == "T"){
// 						jQuery("#tabList").jqGrid('setRowData', cl, {'userNm':withDrawMsg});
// 						$("#tabList").setRowData(cl, false, {background:"#eaeaea"}) ;
// 					}
// 				}
			},
			loadError:function(xhr, status, error) {										//데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
			},
// 			onSelectRow : function(rowid, colld, val, e) {									//행 선택시 이벤트
// 				$("#systemUserUpdateForm").find("label").filter(".error").remove();
// 				if (rowid == 'blankRow') {
// 					return;
// 				}
// 				var rowdata = $(this).getRowData(rowid);
// 				if(!rowdata) return;
// 			},
			//onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
			ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
				var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
				//조회결과가 없을 시 return
				if (rowid == 'blankRow') return;
					
				if(iCell < 8){	// 1~7번째의 셀을 선택했을 시 , 금일접속아이피갯수를 제외한 셀을 선택시 사용자정보 상세보기 팝업레이어 Start
					//if(rowdata.ifYn == "N") 	//인터페이스 유무
					$('#systemUserUpdateForm input[name="memberCd"]').val(rowdata.memberCd);
					systemUserUpdateLayer();
				}else{	// 금일접속아이피갯수 이벤트 제어
					return;
				}
			},
			//page : 1,
			rowNum:-1,
			//pager: 		'#pageList',
			//rowList:[20,30,50,100],
			loadui : 		'disable',
			gridview:   	true,
			viewrecords: true,
			sortorder : "desc",
			rownumbers:true,
			shinkToFit:true,
			loadonce:true,
 	        autowidth:true
		});
	}

	//조회버튼 이벤트
	function eventSearch(){
		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/mgr/manager/mgrMember_selList'/>",
			datatype: "json",
			postData: searchInfo,
			ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' },
			page: 1,
			mtype:'POST',
			//jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
			loadBeforeSend: function(jqXHR) {
				jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
			},
			jsonReader : {
				root:  "list",
				repeatitems: false,
			}
		}).trigger("reloadGrid");
	}


	//금일접속아이피갯수
	function _eventDetailEvent(memberCd){
		if(!memberCd) return;
		_commonAccessIpFindLayer(memberCd);
	}
</script>

<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
		<!-- 검색조건 start ----------------------------------------------------->
		<spring:message code="common.all" var="commonall" /> <%--전체 --%>
		<form id="searchForm" name="searchForm" method="post">
			<fieldset>
				<legend><spring:message code="app.manager.memberviewlist.webacc.hdtag.fieldset.legend" /></legend> <%--사용자 검색 --%>
				<table style="width:100%" summary="" class=type1>
					<caption><spring:message code="app.manager.memberviewlist.webacc.hdtag.table.caption" /></caption> <%--사용자 검색 --%>
					<colgroup>
						<col width="150">
						<col width="*">
						<col width="150">
						<col width="*">
					</colgroup>
					<tbody id="_search">
					<tr>
						<th width="100"><label for="sele2"><spring:message code="app.manager.memberviewlist.label.usernm" /></label></th> <%--사용자명 --%>
							<td><input type="text" id="search_userNm" name="search_userNm"></td>
						<th width="120"><label for="sele2"><spring:message code="app.manager.memberviewlist.label.userid" /></label></th><%--사용자아이디 --%>
							<td><input type="text" id="search_userId" name="search_userId"></td>
						<th width="120"><label for="sele2"><spring:message code="app.manager.memberviewlist.label.useyn" /></label></th><%--사용여부 --%>
							<td>
								<html:codeTag comType="SELECT" objId="search_useYn" objName="search_useYn" parentID="9002" defName="${commonall}" /> <%--전체 --%>
							</td>
					</tr>
					<tr>
						<%-- 2022.12.16 필요시 TCM_MEMBER 테이블 컬럼 확인후 이용 --%>
						<%-- <th width="120"><label for="sele2"><spring:message code='app.manager.memberviewlist.head.withdrawYn'/></label></th>탈퇴여부
						<td>
							<select id="withDrawFg" name="withDrawFg" class="form-control">
								<option value=""><c:out value='${commonall }'/></option>
								<option value="T"><spring:message code='app.manager.memberviewlist.head.withdrawY'/></option>	<!-- 탈퇴한회원 -->
								<option value="F"><spring:message code='app.manager.memberviewlist.head.withdrawN'/></option>	<!-- 일반사용자 -->
							</select>
						</td> --%>
						<th width="120"><label for="sele2"><spring:message code="app.manager.memberviewlist.label.ipcnt" /></label></th>	<%--금일아이피갯수 --%>
							<td>
								<html:codeTag comType="SELECT" objId="search_ipCnt" objName="search_ipCnt" parentID="9006" subCode="1" defName="${commonall}" />
								<label for="sele2"><spring:message code="app.manager.memberviewlist.label.more" /></label>
							</td>
						<th width="120"><label for="sele2"><spring:message code="app.manager.memberviewlist.label.usertype" /></label></th><%--사용여부 --%>
							<td colspan="3">
								<html:codeTag comType="SELECT" objId="search_userType" objName="search_userType" parentID="9001" defName="${commonall}" /> <%--전체 --%>
							</td>
					</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
		<!-- 검색조건 end ------------------------------------------------>


           <!-- 분류 서브타이틀 및 이벤트 버튼 start  ----------------->
               <div class="tit_area" >
               	<h2><spring:message code="app.manager.memberviewlist.title.userlist" /></h2> <%-- 사용자 List --%>
				<div>
					<html:button id="btnNew"    auth="insert" 	 />
					<html:button id="btnSearch" auth="select" 	  />
				</div>
			</div>
			<!-- 대분류 서브타이틀 및 이벤트 버튼 end     ---------------->

			<!-- centent List -------------------------->
            <div id="grid1container" class="gridcontainer">
                <table id="tabList" ><tr><td></td></tr></table>
            </div>

            <div id="pageList"></div>
            <!-- centent List -------------------------->

</div>

<!-- 사용자정보 신규 등록 레이어 영역 start  -->
<%-- <jsp:include page="/WEB-INF/views/management/mgrMemberInsertUser.jsp" /> --%>
<jsp:include page="/WEB-INF/views/management/mgrMemberInsertUserNew.jsp" />
<!-- 사용자정보 신규 등록 레이어 영역 end  -->

<!-- 사용자정보 수정 레이어 영역 start  -->
<%-- <jsp:include page="/WEB-INF/views/management/mgrMemberModifyUser.jsp" /> --%>
<jsp:include page="/WEB-INF/views/management/mgrMemberModifyUserNew.jsp" />
<!-- 사용자정보 수정 레이어 영역 end  -->

<!-- 소속회사찾기 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/common/popupFind/comCompany.jsp" />
<!-- 소속회사찾기 레이어 영역 end  -->

<!-- 접근아이피 list 조회 팝업 start  -->
<jsp:include page="/WEB-INF/views/management/mgrMemberUserAccessIP.jsp" />
<!-- 접근아이피 list 조회 팝업 end  -->

<form id="hiddenForm" name="searchForm" method="post">
<input type="hidden" name="memberCd_search" id="memberCd_search">
</form>
