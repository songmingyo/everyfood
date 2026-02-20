<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script>

	/* onLoad or key event */
	$(document).ready(function($){

		$("#accFromDt").datepicker();
		$("#accToDt").datepicker();


		initSearch();
		_eventSearch();
		//버튼클릭 이벤트 ---------------------------------------------------
		$('#btnSearch').click(function(e){
			/* 조회버튼을 클릭한 경우는 페이지번호는 1로 초기화 한다. */
			$("#page").val(1);
			_eventSearch();
			/* 버튼이 form 안에 존재하면 e.preventDefault(); 필수이다. 안쓰면 autoSubmit 된다. */
			e.preventDefault();
		});
		$('#btnJxlExcel').unbind().click(null,  eventbtnJxlExcel); 					// Excel Download
		//-----------------------------------------------------------------


		//사용자명,아이디,메뉴링크url,기간 조회조건 입력필드 enter key이벤트 --------------

		$('#userNm, #userId, #resPgmUrl, #accFromDt, #accToDt, #title, #titleSub').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : _eventSearch(); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------

		//grid resize
		$(window).bind('resize', function() {
		    try{
		        // width
		        // 그리드의 width를 div 에 맞춰서 적용
		        $('#tabList').setGridWidth($('#grid1container').width()); //Resized to new width as per window

		        // height
		        var height = $(window).height()-$('#grid1container')[0].offsetTop;
		        if(height > 275)    $('#tabList').setGridHeight(height-168);
		        else if(height < 300)
                	$('#tabList').setGridHeight(300);
		    }catch(e){}
		}).trigger('resize');


	});



	function initSearch() {

		// 상세로그 설정 ( 리스트에서 상세로그 : Y일때 버튼 생성 N 일때는 공백 )
		var dtlLogYn = function(cellVal,options,rowObject) { // cellVal - 현재셀값,  rowObject - 현재행값
			var rtn = "";
			if(cellVal == "Y"){	// 상세로그의 값이 Y이면  실행
				rtn = "<input type='button' class='button btn_white' value='"+cellVal+"' onclick=\"_eventDetailLogLogEvent('"+rowObject.logIdx+"' );\" />";
			}else{ // 상세로그의 값이  없거나 다른값이면 실행
				rtn = "";
			}
			return rtn;
		};

        $("#tabList").jqGrid({
            datatype: 'local',
            data: [],



         // 헤더에 들어가는 이름
            colNames:[

					' <spring:message code="app.manager.accesspgmlist.head.sessionid" />'	<%-- Session ID		--%>
					,'<spring:message code="app.manager.accesspgmlist.head.menuid" />'		<%-- 메뉴아이디		--%>
					,'<spring:message code="app.manager.accesspgmlist.head.title" />'		<%-- 메뉴명			--%>
					,'<spring:message code="app.manager.accesspgmlist.head.resfullyrl" />'	<%-- 메뉴링크 URL FULL	--%>
					,'<spring:message code="app.manager.accesspgmlist.head.regnm" />'		<%-- 접근자명			--%>
					,'<spring:message code="app.manager.accesspgmlist.head.regid" />'		<%-- 접근 ID			--%>
					,'<spring:message code="app.manager.accesspgmlist.head.resip" />'		<%-- 접근 IP			--%>
					,'<spring:message code="app.manager.accesspgmlist.head.rescnt" />'		<%-- 횟수				--%>
					,'<spring:message code="app.manager.accesspgmlist.head.regdt" />'		<%-- 최초접근일시		--%>
					,'<spring:message code="app.manager.accesspgmlist.head.moddt" />'		<%-- 최종접근일시		--%>
					,'<spring:message code="app.manager.accesspgmlist.head.executetime" />'	<%-- 실행시간			--%>
					,'<spring:message code="app.manager.accesspgmlist.head.dtllogyn" />'	<%-- 상세로그여부		--%>
					,'hidden'																<%-- 접근로그인덱스		--%>
            ],

            colModel:[

                {name:'sessionId'   	, index:'sessionId'			, sortable:true		, width:200, 	align:"left"	},
                {name:'menuId'   		, index:'menuId'			, sortable:true		, width:90, 	align:"center"	},
                {name:'titlelocale'  	, index:'title'				, sortable:true		, width:180, 	align:"left"	},
                {name:'resFullUrl'   	, index:'resFullUrl'		, sortable:true		, width:300, 	align:"left"	},
                {name:'userNm'      	, index:'userNm'			, sortable:true		, width:80,		align:"left"	},
                {name:'regId'       	, index:'regId'				, sortable:true		, width:100, 	align:"left"	},
                {name:'resIp'       	, index:'resIp'				, sortable:true		, width:110, 	align:"left"	},
                {name:'resCnt'      	, index:'resCnt'			, sortable:true		, width:45 , 	align:"center"	},
                {name:'regDtFmt'       	, index:'regDt'				, sortable:true		, width:130, 	align:"center"	},
                {name:'modDtFmt'       	, index:'modDt'				, sortable:true		, width:130, 	align:"center"	},
                {name:'executeTimeAvg'	, index:'executeTimeAvg'  	, sortable:true		, width:80, 	align:"right"	},
                {name:'dtlLogYn'    	, index:'dtlLogYn'			, sortable:true	 	, width:80, 	align:"center", 	formatter:dtlLogYn },	//상세로그여부
                {name:'logIdx'			, index:'logIdx'  			, hidden:true	},
            ],
            gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
                var colCount = $(this).getGridParam("colNames").length;
                $("#blankRow td:nth-child(2)").attr("colspan", colCount)
                                              .attr("style", "text-align: center;");
            },
            loadComplete: function() {

            	var allRows = jQuery("#tabList").jqGrid('getDataIDs');			// 전체 rowCount

                if ($(this).getGridParam("records")==0) {
                    var firstColName = $(this).getGridParam("colModel")[1].name;
                    var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                    $(this).addRowData("blankRow", msg);
                }
                $('#list').val("");

                $(window).trigger('resize');
            },

            loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
            //	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
                return false;
            },

            onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
                if (id == 'blankRow') return;

                var rowdata = $(this).getRowData(id);
                if(!rowdata) return;
            },
            //onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
            ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
            	var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다

            	/* if(iCell == 12){	// 상세로그 ( 리스트에서 상세로그 : Y 랑 N 모두 출력, Y일때만 상세로그 레이어 호출 N일때는 이벤트 X )
            		if(content == 'Y')
            			_eventDetailLogLogEvent(rowdata.logIdx);	// 상세로그 레이어 Start
            	 }else{
            		 return;
            	 } */
            },
            rowNum:100,
            width: "auto",
            height: "auto",
            scroll: 1,
            loadui : 	'disable',
            gridview:    true,
            pager: 		'#pageList',
            viewrecords: true,
            rownumbers:true,
            loadonce:false,
            shrinkToFit: true,
            scrollOffset:5			// 페이지 스크롤사용시 해당 타임옵션 ..0 으로 주면 무한 스크롤시 서버 뻗어버림
			,autowidth:true

        });
    }

	//조회버튼 이벤트
	function _eventSearch(){

		if(!$('#userNm').val().trim() && !$('#userId').val().trim() && !$('#resPgmUrl').val().trim() && !$('#accFromDt').val().trim() && !$('#accToDt').val().trim()  ) {
			alert('<spring:message code="app.manager.accesspgmlist._eventsearch.a1" />');<%-- 검색어는 하나이상 입력(선택) 하세요! --%>
			$('#resPgmUrl').focus();
			return;
		}

		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		$("#tabList").jqGrid('setGridParam',{
	           url:"<c:url value='/app/mgr/manager/sysAccessPgm_selSearchList'/>"		// url 주소
	          ,datatype: "json"                                     // 보내는 데이터 타입
	          ,postData:searchInfo                                  // 보내는 데이터 형식
	          ,mtype:'POST'                                         // POST,GET,LOCAL 3가지 존재
	          ,loadBeforeSend: function(jqXHR) {
	           		jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
	           }
			  ,jsonReader : {
	           		root:  "list",
	            	repeatitems: false,
	           }
	    }).trigger("reloadGrid");

	}
	// 상세로그 레이어
	function _eventDetailLogLogEvent(logIdx){
		_eventDetailLogFindLayer(logIdx);	//상세로그 레이어
	}

	/* Excel Download */
	function eventbtnJxlExcel(){
		
		//var form = document.searchForm;
		var url = "<c:url value='/app/mgr/manager/jxl/sysAccessLog_selExcelDownload' />";
		$('#searchForm').attr('action',url);
		$('#searchForm').submit();
		//searchForm.action=url;
		//searchForm.submit();
	}
</script>

<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />

			<!-- 검색조건 start ----------------------------------------------------->
			<form id="searchForm" name="searchForm" method="post">
			<sec:csrfInput/>
				<input type="hidden" name="page" id="page"	value="1" />
			    <fieldset class="search_area">
			    	<legend><spring:message code="app.manager.accesspgmlist.webacc.hdtag.fieldset.legend" /></legend> <%--마스터 접근로그 검색 --%>
					<table style="width: 100%" class="type1 inputWidth">
						<caption><spring:message code="app.manager.accesspgmlist.webacc.hdtag.table.caption" /></caption> <%--마스터 접근로그 검색 --%>
	                   	<colgroup>
	                   		<col width="120">
	                   		<col width="*">
	                   		<col width="100">
	                   		<col width="*">
	                   		<col width="100">
	                   		<col width="*">
	                   	</colgroup>
						<tbody id="_search">
						<tr>
	                       <th><label for="sele2"><spring:message code="app.manager.accesspgmlist.label.respgmurl" /></label></th>	<%--메뉴링크 URL --%>
	                       <td><input type="text" id="resPgmUrl" name="resPgmUrl" ></td>
	                       <th><label for="sele2"><spring:message code="app.manager.accesspgmlist.label.accdt" /></label></th>		<%--접속일자 --%>
	                       <td>
	                       		<input type="text" style="width: 40%;margin-right:4px;" name="accFromDt" id="accFromDt"  value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />" readonly="readonly" >
	                       		~
	                       		<input type="text" style="width: 40%;margin-right:4px;" name="accToDt"   id="accToDt"    value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />" readonly="readonly">
	                       </td>
	                       <th><label for="sele2"><spring:message code="app.manager.accesspgmlist.label.typeflag" /></label></th>	<%--프로그램 속성 --%>
	                       	<td>
	                       		<spring:message code="common.all" var="commonall" /> <%--전체 --%>
	                       		<html:codeTag comType="SELECT" objId="typeFlageCd" objName="typeFlageCd" parentID="9011" defName="${commonall}" />
	                       	</td>
	                   </tr>
	                   <tr>
	                   		<th><label for="sele2"><spring:message code="app.manager.accesspgmlist.label.usernm" /></label></th>	<%--접근자명 --%>
	                       	<td><input type="text" id="userNm" name="userNm" ></td>
	                       	<th><label for="sele2"><spring:message code="app.manager.accesspgmlist.label.userid" /></label></th>	<%--접근자 아이디 --%>
	                       	<td><input type="text" id="userId" name="userId"></td>
	                       	<th><label for="sele2"><spring:message code="app.manager.accesspgmlist.label.title" /></label></th>		<%--메뉴명 --%>

	                       <c:choose>
					       		<c:when test="${pageContext.response.locale} eq 'en'}">
					                	<td>
					                		<input type="hidden" id="title" 	 name="title">
					                		<input type="text"   id="titleSub" name="titleSub">
					                	</td>
					            </c:when>
					            <c:otherwise>
					                	<td>
					                		<input type="text" 		id="title" 	 name="title">
					                		<input type="hidden"    id="titleSub" name="titleSub">
					                	</td>
					            </c:otherwise>
					       </c:choose>
	                   </tr>
	                   	<tr>
	                   		<th><label for="sele2"><spring:message code="app.manager.accesspgmlist.head.dtllogyn" /></label></th>	<%--프로그램 속성 --%>
	                   		<td>
	                       		<html:codeTag comType="SELECT" objId="search_useYn" objName="search_useYn" parentID="9002" defName="${commonall}" />
	                       	</td>
	                   		<th></th><td></td>
	                   		<th></th><td></td>
	                   	</tr>
	                  </tbody>
					</table>
	           </fieldset>
			</form>

          	<!-- 분류 서브타이틀 및 이벤트 버튼 start  ----------------->
            <div class="tit_area" >
               	<h2><spring:message code="app.manager.accesspgmlist.title.loglist" /> </h2>
				<div>
					<html:button id="btnJxlExcel" auth="excel"  msgKey="button.excel" />				<!-- Jxl Excel Download -->
					<html:button id="btnSearch" auth="select"   />				<!-- Search -->
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

<!-- 상세로그 list 조회 팝업 start  -->
<jsp:include page="/WEB-INF/views/management/sysAccessLogList.jsp" />
<!-- 상세로그 list 조회 팝업 end  -->

<form id="hiddenForm" name="searchForm" method="post">
<input type="hidden" name="memberCd" id="memberCd">
</form>

