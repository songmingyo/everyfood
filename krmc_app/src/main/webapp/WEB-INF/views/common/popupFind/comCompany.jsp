<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var comCompanyCallbackFnc = null;

/* Layer Event Creat */
function setCommonCompanyFindLayerEvent(){

	/* 레이어 활성화 */
	$('#common_company_find_layer').show();
	if($('#opacity').css("display") == "none") {
		$('#opacity').show();
		$('#opacity').attr("style", "height: 190%;");
	}

	/* 레이어 드레그 Event */
	$("#common_company_find_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "window"
		,scroll: false
	});

	/* 레이어 닫기버튼 Click Event */
	$('#common_company_find_layer a.close').click(function(e){
		closeFindLayer();
	});

	$('#common_company_find_layer #btnSearch_find').unbind().click(null, eventSearchFind); 	// 조회버튼

	//사용자명,아이디,회사명 입력필드 enter key이벤트 --------------
	$('#find_compNm, #find_bmanNo, #find_ceoNm, #find_recName').unbind().keydown(function(e) {
		switch(e.which) {
    	case 13 : eventSearchFind(this); break; // enter
    	default : return true;
    	}
    	e.preventDefault();
   	});

	initCompany();
	$('#common_company_dataListbody').bind('resize', function() {
	    try{
	        // width
	        // 그리드의 width를 div 에 맞춰서 적용
        	$('#common_company_dataListbody').setGridHeight(300);
	        $('#common_company_dataListbody').setGridWidth($('#common_company_find_layer #gridcontainer').width()); //Resized to new width as per window
	    }catch(e){}
	}).trigger('resize');
}
function initCompany() {

	$("#common_company_dataListbody").jqGrid({
        datatype: 'local',
        data: [],

     // 헤더에 들어가는 이름
        colNames:[
				'<spring:message code="app.common.ComCompanyLayer.data.companyregnum" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.companynm" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.companyceonm" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.local" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.telno" />'
				,''
				,''
        ],

        colModel:[
            {name:'bmanNo'		   	, index:'BMAN_NO'				, sortable:true		, width:100, 	align:"right"	},
            {name:'compNm'   		, index:'COMP_NM'				, sortable:true		, width:150, 	align:"left"	},
            {name:'ceoNm'  	    	, index:'CEO_NM'				, sortable:true		, width:120, 	align:"left"	},
            {name:'zipAddr'  	    , index:'ZIP_ADDR'				, sortable:true		, width:120, 	align:"left"	},
            {name:'repTelNo'  	    , index:'REP_TEL_NO'			, sortable:false	, width:120, 	align:"right"	},
            {name:'masterId'  	    , index:'MASTER_ID'				, hidden:true		},
            {name:'corpnNo'  	    , index:'CORPN_NO'				, hidden:true		}

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
            $("#common_company_dataListbody").trigger('resize');
        },
        loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            return false;
        },
        onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
        	if (id == 'popBlankRow') return;

            var rowdata = $(this).getRowData(id);

            if(!rowdata) return;
            
        	if(comCompanyCallbackFnc) comCompanyCallbackFnc(rowdata);

            closeFindLayer();


        },
        //onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
        ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
        	var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
        },

        page: 1,                                            			 	// 현재 페이지
		rowNum: 20,                                             			// 한번에 출력되는 갯수
		rowList:[20,30,50,100],
		pager: '#pageList2',                                       			// page가 보여질 div
		loadui : "disable",                                       			// 이거 안 써주니 로딩 창 같은게 뜸
		emptyrecords : '<spring:message code="message.search.no.data" />',  //row가 없을 경우 출력 할 text
		gridview: true,                                          			// 그리드 속도
		sortname: 'COMP_NM',                                    			// load시 정렬의 기준이 되는 컬럼명
		sortorder: "ASC",                                       			// desc/asc
		viewrecords: true,                                       			// 하단에 1/1 또는 데이터가없습니다 추가
		rownumbers:true,
		loadonce :false

    });
}
/*레이어 close*/
function closeFindLayer(){

	$("#common_company_dataListbody").clearGridData();
	$('#common_company_find_layer').hide();
	comCompanyCallbackFnc = null;

	if($(".pop_layer_new:visible").length == 0) {
		$('#opacity').hide();
	}
}

/* 레이어호출 */
function commonCompanyFindLayer(affiliate,hedoYn,searchVal,callbackFnc){

	/*조건 초기화 -----------------*/
	$("#find_compNm").val('');
	$("#find_bmanNo").val('');
	$("#find_ceoNm").val('');
	$("#find_recName").val('');
	$("#find_affiliateYn").val('');
	$("#find_hedoYn").val('');


	//setTbodyInit("common_company_dataListbody");	// dataList 초기화
	/*----------------------------*/

	setCommonCompanyFindLayerEvent();

	if(searchVal) {
		$("#find_compNm").val(searchVal);
		eventSearchFind();
	}

	$("#find_affiliateYn").val(affiliate);
	$("#find_hedoYn").val(hedoYn);
	$("#find_compNm").focus();
	comCompanyCallbackFnc = callbackFnc;
}

function eventSearchFind() {

	if(!$('#find_compNm').val() && !$('#find_bmanNo').val() && !$('#find_ceoNm').val() && !$('#find_recName').val() && !$('#find_tradeCompCd').val() ) {
		alert('<spring:message code="app.common.ComCompanyLayer._eventSearchFind.a1" />');<%-- 조회조건을 하나이상 입력하세요! --%>
		$('#find_compNm').focus();
		return;
	}

	var searchInfo = {};
	$('#member').find('input, select').map(function() {
		searchInfo[this.name] = $(this).val();
	});

	$("#common_company_dataListbody").jqGrid('setGridParam',{
        url:"<c:url value='/app/common/findCompany' />"			// url 주소
       ,datatype: "json"                                     // 보내는 데이터 타입
       ,postData: searchInfo                             // 보내는 데이터 형식
       ,mtype:'POST'                                         // POST,GET,LOCAL 3가지 존재
       ,ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' }  //ajax contentType 설정
       ,page: 1
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
<spring:message code="common.all" var="commonall" />	 <%--전체 --%>
<spring:message code="common.choice" var="commonchoice"/> <%--선택 --%>

<div class="pop_layer_new" id="common_company_find_layer"
	style="margin:-220px 0 0 -228px;width:800px; display:none;">
    <h1><spring:message code="app.common.ComCompanyLayer.label.companyfind" /></h1> <%--업체찾기 --%>
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         	<div class="tit_area">
                <h2><spring:message code="app.common.ComCompanyLayer.label.usercompanysel" /></h2> <%--사용자 소속선택 --%>
            </div>
            <form id="member" name="member" action="" method="post">
            	<input type="hidden" id="find_affiliateYn" 	name="find_affiliateYn" />	<%--계열사(Y),협력사(N) 여부 --%>
            	<input type="hidden" id="find_hedoYn" 		name="find_hedoYn" />		<%--본사(Y),지사(N)    여부 --%>
	            <table class="type1">
					<colgroup>
						<col width="150"/>
						<col width="300"/>
						<col width="120"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th><spring:message code="app.common.ComCompanyLayer.label.tradetype" /> / <spring:message code="app.common.ComCompanyLayer.label.companynm" /></th> <%--업체명 --%>
							<td>
								<html:codeTag comType="SELECT" objId="find_tradeCompCd" objName="find_tradeCompCd" parentID="M901" defName="${commonall}" /> <%--전체 --%>/
								<input type="text" id="find_compNm" name="find_compNm" style="width: 120px;" />
							</td>
							<th><spring:message code="app.common.ComCompanyLayer.label.companyceonm" /></th> <%--대표자명 --%>
							<td><input type="text" id="find_ceoNm" name="find_ceoNm"  style="width: 70%;"  /></td>
						</tr>
						<tr>
							
							<th><spring:message code="app.common.ComCompanyLayer.label.companyregnum" /></th> <%--사업자번호 --%>
							<td><input type="text" id="find_bmanNo" name="find_bmanNo" style="width: 230px;" /></td>
							<th><spring:message code="app.common.ComCompanyLayer.label.usernm" /></th> <%--담담자명 --%>
							<td><input type="text" id="find_recName" name="find_recName" style="width: 70%;"  /></td>
						</tr>
					</tbody>
				</table>
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2><spring:message code="app.common.ComCompanyLayer.label.companylist" /></h2> <%--업체목록 --%>
					<div>
						<spring:message code="app.common.ComCompanyLayer.btnSearch_find.objTitle" var="findObjTitle"/> <%--업체조회버튼 --%>
					   <html:button id="btnSearch_find" auth="select"  msgKey="button.select" objTitle="${findObjTitle }" /> <%--조회 --%>
					</div>
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer" class="gridcontainer">
                	<table id="common_company_dataListbody" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList2"></div>
            	<!-- centent List -------------------------->
		</div>
	</div>
</div>

