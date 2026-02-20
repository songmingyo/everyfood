<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

/* 등록 또는 수정 처리 여부 - 참일 경우 부모창을 재조회 한다. */
var _SYSTEM_UPDATE_PROC_COUNT = false;

$(document).ready(function($){

	 //버튼클릭 이벤트 ---------------------------------------------------
	 $('#btnAuthUserSearch').unbind().click(null, groupGroupGridReLoad); 	 	// 조회버튼
 	 $('#btnAddMove').unbind().click(function()  { eventMoveSave('I'); });		// 추가
 	 $('#btnDelMove').unbind().click(function()  { eventMoveSave('D'); });		// 제거
 	 //-----------------------------------------------------------------

	 //사용자명,아이디,소속 조회조건 입력필드 enter key이벤트 ---------------------
	$('#authUserSearchForm input').unbind().keydown(function(e) {
		switch(e.which) {
	   	case 13 : groupGroupGridReLoad(); break; // enter
	   	default : return true;
	   	}
	   	e.preventDefault();
	});
	 //------------------------------------------------------------------



	initAuthGrid();
	iniUserGrid();

});

/* 레이어호출 */
function userAuthGroupUpdateLayer(groupId){

	_SYSTEM_UPDATE_PROC_COUNT = false;	// 초기화

	/* 레이어 활성화 */
	$('#userAuth_group_layer').show();
	$('#opacity').show();


	/* 레이어 드레그 Event */
	$("#userAuth_group_layer").draggable({
	 	handle: $("h1")
		,cancle: $("a.close")
		,containment: "#opacity"
		,scroll: false
	});

	/* 레이어 닫기버튼 Click Event */
	 $('#userAuth_group_layer a.close').click(function(e){

	 	$('#userAuth_group_layer').hide();
		$('#opacity').hide();

		e.preventDefault();
	 });

	$.ajax({
		contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		url  : '<c:url value="/app/mgr/manager/mgrGroup_selGroupEdit" />',
		data : JSON.stringify({'groupId'   : groupId }),
		success : function(data){
			if(data == null)
				alert('<spring:message code="app.manager.groupedit._systemgroupupdatelayer.a2" />'); <%--선택된 그룹의 정보가 조회되지 않았습니다.--%>
			else {
				$('#auth_groupId').text(data.groupId);
				$('#auth_groupNm').text(data.groupLocaleNm);
				$('#auth_useYn').text(data.useYn);
				$('#auth_groupDesc').text(data.groupDesc);

				groupGroupGridReLoad();
				groupAuthGridReLoad();

			}

		}
	});


}


	/* 권한사용자 Grid */
	function initAuthGrid(){
		$("#authDataList").jqGrid({
	        datatype: "local",  // 보내는 데이터 타입
	        data: [],

	        colNames:[
					'<spring:message code="app.manager.group.title.name" />' <%--사원명 --%>
					,'memberCd'
			],     // 헤더에 들어가는 이름


	        colModel:[
	              {name:'userNm'      , index:'userNm'      , sortable:true   , width:168,  align:"left"},
	              {name:'memberCd'		, index:'memberCd'	  		, hidden:true},     // 메뉴레벨
	        ],
	        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	            var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
	        },
	        loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        },
	        loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
	        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
	            return false;
	        },
	        onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
	            if (id == 'blankRow') {
	                return;
	            }

	            var rowdata = $(this).getRowData(id);
	        },

	         rowNum			: 20,
	         height			: 230,
	         loadui 		: 'disable',
	         gridview		: true,
	         viewrecords	: true,
	         loadonce		: true,
	         multiselect	: true,
	         autowidth		: true
		});
	}
	/* 사용자 조회 */
	function iniUserGrid(){
		$("#userDataList").jqGrid({
	        datatype: "local",  // 보내는 데이터 타입
	        data: [],

	        colNames:[
				'<spring:message code="app.manager.group.title.company" />' 	<%--소속명			--%>
				,'<spring:message code="app.manager.group.title.name" />' 		<%--사용자명 			--%>
				,'<spring:message code="app.manager.group.title.usertype" />' 	<%--사용자유형 		--%>
				,'memberCd'
			],

	        colModel:[
	              {name:'compNm'    		, index:'compNm'    		, sortable:true   , width:150, 	align:"left"},
	              {name:'userNm'    		, index:'userNm'    		, sortable:true   , width:150, 	align:"left"},
	              {name:'userTypeName'  	, index:'userTypeName'      , sortable:true   , width:100,  align:"left"},
	              {name:'memberCd'			, index:'memberCd'	  		, hidden:true},
	        ],

	        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	            var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
	        },

	        loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        },

	        loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
	        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
	            return false;
	        },
	        onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
	        	if (id == 'blankRow') {
	                return;
	            }
	            var rowdata = $(this).getRowData(id);
	        },

	         rowNum			: 20,
	         height			: 230,
	         loadui 		: 'disable',
	         gridview		: true,
	         viewrecords	: true,
		     loadonce		: true,
	         multiselect	: true,
	         autowidth		: true
	   });
	}

	/* 그룹조회 */
	function groupAuthGridReLoad(){

		//Resized to new width as per window   -----------------------------
		$('#authDataList').setGridWidth($('#layoutGrid1container').width());
		//--------------------------------------------------------------------
		var searchInfo = {'groupId' : $('#auth_groupId').text() };

		$("#authDataList").jqGrid('setGridParam',{
			url  : '<c:url value="/app/mgr/manager/mgrGroup_selAuthMemberList" />',
	        datatype: "json",
	        postData: searchInfo,
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

	/* 사용자 조회 */
	function groupGroupGridReLoad(){

		//Resized to new width as per window   -----------------------------
		$('#userDataList').setGridWidth($('#layoutGrid2container').width());
		//--------------------------------------------------------------------

		var searchInfo = {};
		$('#authUserSearchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

	 	searchInfo['groupId'] =  $('#auth_groupId').text();

		$("#userDataList").jqGrid('setGridParam',{
			url  : '<c:url value="/app/mgr/manager/mgrGroup_selMemberList" />',
	        datatype: "json",
	        postData: searchInfo,
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

	 // 권한추가 버튼 클릭 이벤트
	function eventMoveSave(state){

		var formName = "";
		if(state =="I") formName = "userDataList";
		else formName = "authDataList";

		var memberCds = eventReferSaveLayer(formName);
		if(!memberCds || memberCds.length == 0) {
			alert('<spring:message code="app.manager.groupedit._eventmovesave.a1" />'); <%--'추가할 사용자를 선택하세요!'--%>
			return;
		}

		var fromData = {'groupId' 	 : $("#auth_groupId").text() 	// 그룹아이디
						,'memberCds' : memberCds					// 선택한 사용자 list
						,'state'	 : state						// ADD(추가), DEL(삭제)
		};

		// 저장 이벤트 json 실행---------------------------
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/mgrGroup_insAuthGroup" />',
		      data : JSON.stringify(fromData),
		      success : function(data){
		    	  if(data == null) alert('<spring:message code="app.manager.groupedit._eventmovesave.a2" />'); <%-- 처리된 데이터가 없습니다. --%>
	            else if('success' == data.msgCd){

	            	groupGroupGridReLoad();
					groupAuthGridReLoad();

	            }
	            else alert('<spring:message code="app.manager.groupedit._eventmovesave.a3" />'); <%-- 저장중 오류가 발생하였습니다. --%>
	      	}
		});
		//-----------------------------------------------
	}


	//선택
	function eventReferSaveLayer(tabName){
		var saveInfoList = [];
		var ids = $("#"+tabName).jqGrid('getDataIDs');

		for(var i=0; i<ids.length; i++){
			var rowData = $('#'+tabName).getRowData(ids[i]);

			if($("#jqg_"+tabName+"_"+ids[i]).is(":checked") == true){
				saveInfoList.push(rowData.memberCd);
			}
		}

		return saveInfoList;
	}

</script>
<spring:message code="common.all" var="commonall" /> <%--전체 --%>
<div class="pop_layer_new" id="userAuth_group_layer" style="position:absolute;left:25%;top:10%;width:900px; display:none;">
    <h1><spring:message code="app.manager.groupAuthMember.toptitle" /></h1>	<%-- 그룹별 사용자 권한 설정 --%>
    <spring:message code="common.close" var="commonclose"/> <%-- 닫기 --%>
	<a id="btnClose" href="javascript:void(0);" class="close"><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
            <div class="tit_area">
                <h2><spring:message code="app.manager.groupAuthMember.title.userinfo" /></h2>	<%-- 사용자조회 --%>
                <div>
                <html:button id="btnAuthUserSearch" auth="select"   /> <%-- 조회 --%>
                </div>
            </div>
            <div style="margin-bottom: 1px;">
			<table class="type1" summary="<spring:message code="app.manager.groupAuthMember.webacc.bdtag.table.summary.usergroupinfo" />">	<%--사용자 그룹 정보 --%>
                    <caption><spring:message code="app.manager.groupAuthMember.webacc.bdtag.table.caption.usergroupinfo" /></caption>		<%--사용자 그룹 정보 --%>
                    <colgroup>
                  		<col width="100">
                   		<col width="*">
                   		<col width="100">
                   		<col width="*">
                   		<col width="100">
                   		<col width="*">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th><spring:message code="app.manager.groupAuthMember.label.groupid" /></th> <%--그룹 아이디 --%>
                        <td><label id="auth_groupId"></label></td>
                    	<th><spring:message code="app.manager.groupAuthMember.label.groupnm" /></th> <%--그룹명 --%>
                        <td><label id="auth_groupNm"></label></td>
                    	<th><spring:message code="app.manager.groupAuthMember.label.useyn" /></th> <%--사용여부 --%>
                        <td><label id="auth_useYn"></label></td>
                    </tr>
                     <tr>
                        <th><spring:message code="app.manager.groupAuthMember.label.groupdesc" /></th> <%--그룹설명 --%>
                        <td colspan="5" ><label id="auth_groupDesc"></label></td>
                     </tr>

                    </tbody>
			</table>
			</div>
			<!-- 검색조건 start ----------------------------------------------------->
			<form id="authUserSearchForm" name="authUserSearchForm" method="post">
			<fieldset>
				<legend><spring:message code="app.manager.groupAuthMember.webacc.hdtag.fieldset.legend" /></legend> 	<%--마스터 디테일 사용자권한 검색 --%>
				<table style="width:100%" summary="" class=type1>
					<caption><spring:message code="app.manager.groupAuthMember.webacc.hdtag.table.caption" /></caption>	<%--마스터 디테일 사용자권한 검색 --%>
					<colgroup>
						<col width="100">
						<col width="*">
						<col width="100">
						<col width="*">
						<col width="100">
						<col width="*">
					</colgroup>
					<tbody id="_searchAuth">
					<tr>
						<th><label for="sele2"><spring:message code="app.manager.groupAuthMember.label.usernm" /></label></th><%--사용자명 --%>
						<td><input type="text" id="userNm" name="userNm" style="width: 98%"></td>
						<th><label for="sele2"><spring:message code="app.manager.groupAuthMember.label.useselect" /></label></th><%--사용구분 --%>
						<td><html:codeTag comType="SELECT" objId="userType" width="98%" objName="userType"  parentID="9001" subCode="1" defName="${commonall}" /></td>
						<th width="120"><label for="sele2"><spring:message code="app.manager.groupAuthMember.label.userid" /></label></th>	<%--사용자아이디 --%>
						<td><input type="text" id="userId" name="userId" style="width: 98%"></td>
					</tr>
					<tr>
						<th width="120"><label for="sele2"></label><spring:message code="app.manager.groupAuthMember.label.useyn" /></th><%--사용여부 --%>
						<td><html:codeTag comType="SELECT" objId="useYn" width="98%" objName="useYn" parentID="9002" defName="${commonall}" /></td>
						<th width="120"><label for="sele2"></label></th>
						<td></td>
						<th width="120"></th>
						<td></td>
					</tr>
					</tbody>
				</table>
			</fieldset>
			</form>



		   <!-- 검색조건 end ------------------------------------------------>
			<table style="width: 100%">
				<tr>
					<td width="200">
						<div class="tit_area" >
		                	<h2><spring:message code="app.manager.groupAuthMember.title.userauth" /></h2> <%-- 권한 사용자 List --%>
							<div>
							</div>
						</div>
						<!-- 사용자 조회 Data 그리트 영역 start -------------------------->
						<div id="layoutGrid1container" class="gridcontainer" >
							<table id="authDataList" class="type4" summary="<spring:message code="app.manager.groupAuthMember.webacc.bdtag.table.summary.usersearch" />">	<%--사용자 조회 내역 --%>
			                </table>
						</div>
						<!-- 사용자 조회 Data 그리트 영역 end --------------------------->
					</td>


					<td width="50" align="center">
						<span style="margin-top: 50px;">
						<html:button id="btnAddMove" auth="select" value="&lt;&lt;" style="margin-bottom:5px;" />
						<html:button id="btnDelMove" auth="select" value="&gt;&gt;"  />
						</span>
					</td>


					<td>
		             	<div class="tit_area" >
		                	<h2><spring:message code="app.manager.groupAuthMember.title.userlist" /> </h2><%--사용자  List --%>
							<div>

							</div>
						</div>

		                <!-- 권한부여 Data 그리트 영역 start -------------------------->
						<div id="layoutGrid2container" class="gridcontainer" >
							<table id="userDataList" class="type4" summary="<spring:message code="app.manager.groupAuthMember.webacc.bdtag.table.summary.authsearch" />">	<%--권한 조회 내역 --%>
			                </table>
						</div>
						<!-- 권한부여 Data 그리트 영역 end --------------------------->

					</td>
				</tr>
			</table>

		</div>
	</div>
</div>


