<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

var commonMemberFindCallback;
/* Layer Event Creat */
function _setCommonMemberFindLayerEvent(){


	/* 레이어 활성화 */
	var layerID = 'common_member_find_layer';
	//setLayerPopupLocationSettings(layerID, 70, );

	/* 레이어 활성화 */
	$('#common_member_find_layer').show();
	$('#opacity').show();
	$('#opacity').attr("style", "height: 190%;");
	/* 레이어 드레그 Event */
	$("#common_member_find_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "#opacity"
		,scroll: false
	});


	/* 레이어 닫기버튼 Click Event */
	$('#common_member_find_layer a.close').click(function(e){
		closeMemberFindLayer();
	});


	 $('#btnSearch_find').unbind().click(null, eventMemberFind); 	// 조회버튼


	//사용자명,아이디,회사명 입력필드 enter key이벤트 --------------
	$('#findMember_userNm, #findMember_userId, #findMember_compNm').unbind().keydown(function(e) {
		switch(e.which) {
    	case 13 : eventMemberFind(this); break; // enter
    	default : return true;
    	}
    	e.preventDefault();
   	});

	initMember();
	$('#common_member_dataListbody').setGridHeight(240);
	
	if($("#findMember_examiner").val() == 'Y') {
		eventMemberFind();
	}
}


function initMember() {

	$("#common_member_dataListbody").jqGrid({
        datatype: 'local',
        data: [],

     // 헤더에 들어가는 이름
        colNames:[
				 '<spring:message code="app.common.ComMember.data.usernm"/>'
				,'<spring:message code="app.common.ComMember.data.userid"/>'
				,'<spring:message code="app.common.ComMember.data.company"/>'
				,'<spring:message code="app.common.ComMember.data.telno"/>'
				,'<spring:message code="app.common.ComMember.data.hpno"/>'
				,'<spring:message code="app.common.ComMember.data.useyn"/>'
				,'hidden'
				,'hidden'
        ],

        colModel:[
             {name:'userNm'		   	, index:'USER_NM'				, sortable:true		, width:100, 	align:"left"	}
            ,{name:'userId'   		, index:'USER_ID'				, sortable:true		, width:150, 	align:"center"	}
            ,{name:'compNm'  	    , index:'COMP_NM'				, sortable:true		, width:120, 	align:"left"	}
            ,{name:'telNo'  	    , index:'TEL_NO'				, sortable:false	, width:120, 	align:"center"	}
            ,{name:'hpNo'  	    	, index:'HP_NO'					, sortable:false	, width:120, 	align:"center"	}
            ,{name:'useYn'  	    , index:'USE_YN'				, sortable:true		, width:120, 	align:"center"	}
            ,{name:'memberCd'		, index:'MEMBER_CD'				, hidden  :true		}
            ,{name:'email'			, index:'EMAIL'					, hidden  :true		}
        ],
        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	 var colCount = $(this).getGridParam("colNames").length;
             $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
        },
        loadComplete: function() {

            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            }
        },
        loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            return false;
        },
        onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
        	if (id == 'popBlankRow') return;

            var rowdata = $(this).getRowData(id);

            var memberCd 		= rowdata['memberCd'];
        	var userNm 			= rowdata['userNm'];
        	
        	if($("#memberCd")) $("#memberCd").val(memberCd);
        	if($("#userNm")) $("#userNm").val(userNm);

        	if($("#dcMemberCd")) $("#dcMemberCd").val(memberCd);
        	if($("#dcUserNm")) $("#dcUserNm").val(userNm);

            if(!rowdata) return;
            if(commonMemberFindCallback) commonMemberFindCallback(rowdata);

            closeMemberFindLayer();


        },
        //onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
        ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
        	var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
        },

        page: 1,                                            			 	// 현재 페이지
		rowNum: 20,                                             			// 한번에 출력되는 갯수
		rowList:[20,30,50,100],
		pager: '#common_member_find_layer_pageList',                        // page가 보여질 div
		loadui : "disable",                                       			// 이거 안 써주니 로딩 창 같은게 뜸
		emptyrecords : '<spring:message code="message.search.no.data" />',  //row가 없을 경우 출력 할 text
		gridview: true,                                          			// 그리드 속도
		autowidth: true,
		sortname: 'USER_ID',                                    			// load시 정렬의 기준이 되는 컬럼명
		sortorder: "ASC",                                       			// desc/asc
		viewrecords: true,                                       			// 하단에 1/1 또는 데이터가없습니다 추가
		rownumbers:true,
		loadonce :false

    });
}

/*레이어 close*/
function closeMemberFindLayer(){
	/*조건 초기화 -----------------*/
	$("#findMember_userNm").val('');
	$("#findMember_userId").val('');
	$("#findMember_compNm").val('');
	$("#findMember_examiner").val('');
	
	$("#common_member_dataListbody").clearGridData();
	$('#common_member_find_layer').hide();
	
	if($(".pop_layer_new:visible").length == 0) {
	 	$('#opacity').hide();
	}

}

/* 레이어호출 */
function _commonMemberFindLayer(callbackFnc){

	/*조건 초기화 -----------------*/
	$("#findMember_userNm").val('');
	$("#findMember_userId").val('');
	$("#findMember_compNm").val('');
	$("#findMember_examiner").val('');

	//setTbodyInit("common_member_dataListbody");	// dataList 초기화
	/*----------------------------*/
	
	if(callbackFnc && typeof callbackFnc == "function") {
		commonMemberFindCallback = callbackFnc;
	}else {
		commonMemberFindCallback = null;
	}

	_setCommonMemberFindLayerEvent();

/* 	if(searchVal) {

		$("#find_userNm").val(searchVal);
		eventMemberFind();
	} */

	$("#findMember_userNm").focus();
}

/* 검토요청에서 레이어호출 */
function _commonExaminerFindLayer(callbackFnc){

	/*조건 초기화 -----------------*/
	$("#findMember_userNm").val('');
	$("#findMember_userId").val('');
	$("#findMember_compNm").val('');
	$("#findMember_examiner").val('Y');

	/*----------------------------*/
	
	if(callbackFnc && typeof callbackFnc == "function") {
		commonMemberFindCallback = callbackFnc;
	}else {
		commonMemberFindCallback = null;
	}

	_setCommonMemberFindLayerEvent();

	$("#findMember_userNm").focus();
}


//조회버튼 event
function eventMemberFind(){

	<%-- 검토요청에서는 특정 사용자로 조회가 제한되어있어 검사하지않음 --%>
	if(!$('#findMember_userNm').val() && !$('#findMember_userId').val() && !$('#findMember_compNm').val() && $('#findMember_examiner').val() != 'Y'){
      alert('<spring:message code="app.common.ComCompanyLayer._eventSearchFind.a1" />');
      return;
	}

	var param = {};
	
	$('#searchForm_find').find('input').map(function() {
		param[this.name] = $(this).val();
	});

	$("#common_member_dataListbody").jqGrid('setGridParam',{
           url:"<c:url value='/app/common/findMember.json'/>"	// url 주소
          ,datatype: "json"                                     // 보내는 데이터 타입
          ,postData: param 	                     				// 보내는 데이터 형식
          ,mtype:'POST'                                         // POST,GET,LOCAL 3가지 존재
          ,ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' }  //ajax contentType 설정
          ,loadBeforeSend: function(jqXHR) {
           		jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
           }
		  ,jsonReader : {
           		root:  "list",
            	repeatitems: false,
           }
    }).trigger("reloadGrid");
}

</script>

<div class="pop_layer_new" id="common_member_find_layer"
	style="position:absolute; left:17% !important; top:5% !important; width:900px; height:421px; display:none;">
    <h1><spring:message code="app.common.ComMember.label.userfind" /></h1> <%--사용자찾기 --%>

	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content" style="margin-top: 5px;">
         <div id="pop_section">
            <form id="searchForm_find" name="searchForm_find" action="" method="post">
				<input type="hidden" id="findMember_examiner" name="findMember_examiner" />
	            <table class="type1">
					<colgroup>
						<col width="85"/>
						<col width="*"/>
						<col width="80"/>
						<col width="*"/>
						<col width="110"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th><spring:message code="app.common.ComMember.label.usernm" /></th> <%--사용자명 --%>
							<spring:message code="app.common.ComMember.find_usernm.title" var="userNmTitle"/> <%--사용자명조건 --%>
							<td><input type="text" id="findMember_userNm" name="findMember_userNm" title="${userNmTitle }" style="width: 70%;" /></td>

							<th><spring:message code="app.common.ComMember.label.userid" /></th><%--아이디 --%>
							<spring:message code="app.common.ComMember.find_userid.title" var="userIdTitle"/> <%--사용자아이디조건 --%>
							<td><input type="text" id="findMember_userId" name="findMember_userId" title="${userIdTitle }"  style="width: 70%;" /></td>

							<th><spring:message code="app.common.ComMember.label.companynm" /></th> <%--소속회사명 --%>
							<spring:message code="app.common.ComMember.find_compnm.title" var="compNmTitle"/> <%--소속회사명조건 --%>
							<td>
								<input type="text" id="findMember_compNm" name="findMember_compNm" title="${compNmTitle }"  style="width: 70%;" />
							</td>
						</tr>
					</tbody>
				</table>
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2><spring:message code="app.common.ComMember.label.userlist"/></h2> <%--사용자 목록 --%>
					<div>
					   <spring:message code="app.common.ComMember.btnSearch_find.objTitle" var="userFindTitle"/>
					   <html:button id="btnSearch_find" auth="search" msgKey="common.search" />
					</div>
	            </div>
				<!-- centent List -------------------------->
            	<div id="gridcontainer" class="gridcontainer">
                	<table id="common_member_dataListbody" ><tr><td></td></tr></table>
            	</div>
            	<div id="common_member_find_layer_pageList"></div>
            	<!-- centent List -------------------------->
	    	</div>
		</div>
	</div>
</div>
