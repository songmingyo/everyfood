<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script>
    'use strict';
    var gSearchForm     = "";
    var gDataForm       = "";

	/* onLoad or key event */
	$(document).ready(function($){


		$("#boardCd").inputmask("999");
		$("#fileCount").inputmask("9");
		$("#fileUploadSize").inputmask("999",{"placeholder": ""});


		gSearchForm = $("#searchForm");
		gDataForm = $("#dataForm");

		eventSearch();

		//버튼클릭 이벤트 ---------------------------------------------------
		$('#btnSearch').unbind().click(null, gridReload); 	// 검색버튼
		$('#btnNew').unbind().click(null, 	 eventNew); 		// 신규버튼
		$('#btnSave').unbind().click(null, 	 eventSave); 		// 저장버튼
		$('#btnDelete').unbind().click(null, eventDelete); 	// 삭제버튼

		$('#findMember').unbind().click(null, 	 eventFindMember); 		// 사용자 찾기

		//-----------------------------------------------------------------

		//게시판명칭, 입력필드 enter key이벤트 --------------
		$('#boardNm').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : gridReload(); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});


		//사용자 찾기  enter key이벤트 --------------
		$('#userNm').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : eventFindMember('E'); break; // enter
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
                if(height > 525) {
                	$('#tabList').setGridHeight(height-423);
                }
                else if(height < 300){
                	$('#tabList').setGridHeight(300);
                }
            }catch(e){}
        }).trigger('resize');


	});



	function eventFindMember(type){

		var findVal = "";

		if(type == "E") findVal = $("#userNm").val();
		_commonMemberFindLayer(findVal);
	}

	/*신규 Click Event*/
	function eventNew(){
		setClearDetail();
		gDataForm.find("input[name='boardCd']").focus();

	}


	/* 저장버튼 */
	function eventSave() {

		var msg ="";
		gDataForm.find('input , select').each(function() {

			if(this.name =="boardCd" && !$(this).val()){
				msg = '<spring:message code="app.manager.boardmanagerviewlist._eventsave.m1" />'; <%--분류코드를 입력하세요!--%>
				$(this).focus();
				return false;
			}
			if(this.name =="boardNm" && !$(this).val()) {
				msg = '<spring:message code="app.manager.boardmanagerviewlist._eventsave.m2" />'; <%--게시판명칭을 입력하세요!--%>
				$(this).focus();
				return false;
			}
			if(this.name =="boardKindCd" && !$(this).val()) {
				msg = '<spring:message code="app.manager.boardmanagerviewlist._eventsave.m3" />'; <%--게시판유형을 선택하세요!--%>
				$(this).focus();
				return false;
			}

			if(this.name =="memberCd" && !$(this).val()) {
				msg =  '<spring:message code="app.manager.boardmanagerviewlist._eventsave.m4" />'; <%--게시판 관리자를 선택하세요!--%>
				$(this).focus();
				return false;
			}

		});

		if(msg){
			alert(msg);
			return;
		}
		eventBoardManagerSaveAction();
	}

	/*저장이벤트 처리*/
	function eventBoardManagerSaveAction(){

		var dataInfo = {};
		var writeYn = null, replyYn = null, commentYn= null, useYn = null;
		gDataForm.find('input , select').map(function() {

			if(this.type =='radio' || this.type =='checkbox'){

				if(this.name =='writeYn'){
					//글쓰기 CHECKBOX 값설정
					if(!writeYn) {
						if( $(this)[0].checked) 	writeYn = "Y";
						else writeYn = "N";
						dataInfo[this.name] = writeYn;
					}
				}
				if(this.name =='replyYn'){
					//댓글유무 CHECKBOX 값설정
					if(!replyYn) {
						if( $(this)[0].checked) 	replyYn = "Y";
						else replyYn = "N";
						dataInfo[this.name] = replyYn;
					}
				}
				if(this.name =='commentYn'){
					//덧글유무 CHECKBOX 값설정
					if(!commentYn) {
						if( $(this)[0].checked) 	commentYn = "Y";
						else commentYn = "N";
						dataInfo[this.name] = commentYn;
					}
				}
				if(this.name =='useYn'){
					//사용유무 RADIO 값설정
					if(!useYn) {
						if( $(this)[0].checked) 	useYn = "Y";
						else useYn = "N";
						dataInfo[this.name] = useYn;
					}
				}
			}
			else if(this.type =='button'){}
			else dataInfo[this.name] = $(this).val();

		});

		// 저장 이벤트 json 실행---------------------------
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/boardManager_insData" />',
		      data : JSON.stringify(dataInfo),
		      success : function(data){

		    	var msg = '';

		    	if(data == null) msg = '<spring:message code="app.manager.boardmanagerviewlist._eventboardmanagersaveaction.m1" />'; <%--처리된 데이터가 없습니다--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="app.manager.boardmanagerviewlist._eventboardmanagersaveaction.m2" />'; <%--저장이 정상적으로 처리되었습니다.--%>
	            	gridReload();	// 제조회
	            }
	            else if('duple' == data.msgCd) {
	            	msg = '<spring:message code="app.manager.boardmanagerviewlist._eventboardmanagersaveaction.m3" />'; <%--이미 등록된 분류코드 입니다.\n분류코드를 변경하세요!--%>
	            	$("#boardCd").focus();
	            }
	            else msg = '<spring:message code="app.manager.boardmanagerviewlist._eventboardmanagersaveaction.m5" />'; <%--분류코드 저장중 오류가 발생하였습니다.--%>

	          	alert(msg);
		      }
		});
		//-----------------------------------------------

	}


	/*분류코드 삭제버튼 이벤트*/
	function eventDelete(){
		var oldBoardCd = $("#oldBoardCd").val();

		if(!oldBoardCd) {
			alert('<spring:message code="app.manager.boardmanagerviewlist._eventdelete.a1" />'); <%--선택된 게시판 정보가 없습니다.--%>
			return;
		}


		// 저장확인 메세지 --------------------------------
		<%-- "게시판분류["+oldBoardCd+"]삭제 및 등록된 게시글을 삭제Flage 처리 합니다.\n삭제하시겠습니까?"  --%>
		if (!confirm( '<spring:message code="app.manager.boardmanagerviewlist._eventdelete.c1" />'+oldBoardCd+'<spring:message code="app.manager.boardmanagerviewlist._eventdelete.c2" />' + "\n" + '<spring:message code="app.manager.boardmanagerviewlist._eventdelete.c3" />')) {
	        return false;
		}
		//-----------------------------------------------

		var str = {'oldBoardCd' : oldBoardCd // 게시글코드
		  		  };

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/boardManager_delData" />',
		      data : JSON.stringify(str),
		      success : function(data){
				var msg;
		    	if(data == null) msg = '<spring:message code="app.manager.boardmanagerviewlist._eventdelete.m1" />'; <%--처리된 데이터가 없습니다.--%>
	          	else if('success' == data.msgCd)
	            {
	          		msg = '<spring:message code="app.manager.boardmanagerviewlist._eventdelete.m2" />'; <%--삭제가 정상적으로 처리되었습니다.--%>
	            	gridReload();	// 제조회
	            }
	          	else msg = '<spring:message code="app.manager.boardmanagerviewlist._eventdelete.m3" />'; <%--분류코드 삭제 중 오류가 발생하였습니다.--%>
	          	alert(msg);
		      }
		});
	}


	//조회버튼 event
	function eventSearch(){
	  	setClearDetail();

		var searchInfo = {};
		gSearchForm.find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});



        $("#tabList").jqGrid({
            url:'<c:url value="/app/mgr/manager/boardManager_selList" />',                                                                    // url 주소
            datatype: "json",                                                                                           // 보내는 데이터 타입
            postData:searchInfo,                                                                // 보내는 데이터 형식
            mtype:'POST',                                                                                               // POST,GET,LOCAL 3가지 존재
            //ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },


            jsonReader : {
                root:  "rows",
                repeatitems: false,
            },


            colNames:[  '<spring:message code="app.manager.boardmanagerviewlist.head.boardcd" />'			<%-- 분류코드--%>
            		   ,'<spring:message code="app.manager.boardmanagerviewlist.head.boardnm" />'			<%-- 게시판명--%>
                       ,'boardCd'
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.boardkind" />'			<%-- 게시유형--%>
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.usernm" />'			<%-- 담당자명--%>
                       ,'userId'
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.filecount" />'			<%-- 갯수--%>
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.fileuploadsize" />'	<%-- 용량(M)--%>
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.write" />'				<%-- 쓰기--%>
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.reply" />'				<%-- 댓글--%>
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.comment" />'			<%-- 덧글--%>
                       ,'<spring:message code="app.manager.boardmanagerviewlist.head.useyn" />'				<%-- 사용여부--%>
                       , 'boardCount'
                       , 'memberCd'
                       ],     // 헤더에 들어가는 이름


            colModel:[
                {name:'boardCd'           , index:'boardCd'          , sortable:true   , width:50  , align:"center"},
                {name:'boardNm'           , index:'boardNm'          , sortable:true   , width:120 , align:"center"},
                {name:'boardKindCd'       , index:'boardKindCd'      , hidden:true},

                <c:choose>
            	<c:when test="${pageContext_response_locale} eq 'ko'}">
            		{name:'boardKindCdNm'     , index:'boardKindCdNm'    , sortable:true   , width:100 , align:"center"},
            	</c:when>
            	<c:otherwise>
            		{name:'boardKindCdSubNm'  , index:'boardKindCdNm'    , sortable:true   , width:100 , align:"center"},
            	</c:otherwise>
            	</c:choose>

                {name:'userNm'            , index:'userNm'           , sortable:true   , width:80  , align:"center"},
                {name:'userId'            , index:'userId'           , hidden:true},
                {name:'fileCount'         , index:'fileCount'        , sortable:true   , width:50  , align:"center"},
                {name:'fileUploadSize'    , index:'fileUploadSize'   , sortable:true   , width:70  , align:"center"},
                {name:'writeYn'           , index:'writeYn'          , sortable:true   , width:50  , align:"center"},
                {name:'replyYn'           , index:'replyYn'          , sortable:true   , width:50  , align:"center"},
                {name:'commentYn'         , index:'commentYn'        , sortable:true   , width:50  , align:"center"},
                {name:'useYn'             , index:'useYn'            , sortable:true   , width:70  , align:"center"},
                {name:'boardCount'        , index:'boardCount'       , hidden:true},
                {name:'memberCd'          , index:'memberCd'       	 , hidden:true},

            ],
            gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
                    var colCount = $(this).getGridParam("colNames").length;
                    $("#blankRow td:nth-child(2)").attr("colspan", colCount);
            },
            loadComplete: function() {
            	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
                if ($(this).getGridParam("records")==0) {
                	var firstColName = $(this).getGridParam("colModel")[1].name;
                	var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                	$(this).addRowData("blankRow", msg);
                }
            },
            loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
            	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
                return false;
            },
            onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
            	if (id == 'blankRow') {
            		return;
            	}

            	$('#boardCount').text(''); // 글등록갯수 초기화

            	var rowdata = $(this).getRowData(id);

            	gDataForm.find("input[name='boardCd']").val(rowdata.boardCd);                  // 게시판코드
                gDataForm.find("input[name='oldBoardCd']").val(rowdata.boardCd);               // 게시판코드 수정모드 참조용

                gDataForm.find("input[name='boardNm']").val(rowdata.boardNm);                  // 게시판명칭
                gDataForm.find("select[name='boardKindCd']").val(rowdata.boardKindCd);         // 게시판유형
                gDataForm.find("input[name='userNm']").val(rowdata.userNm);                    // 관리자명
                gDataForm.find("input[name='userId']").val(rowdata.userId);                	   // 관리자멤버코드
                gDataForm.find("input[name='fileCount']").val(rowdata.fileCount);              // 파일갯수
                gDataForm.find("input[name='fileUploadSize']").val(rowdata.fileUploadSize);    // 파일당 업로드 용량
                gDataForm.find("input[name='memberCd']").val(rowdata.memberCd);    // 사용자 코드명


                //--글쓰기 CHECKBOX CHECKED 설정----------------
                if(rowdata.writeYn =="Y")
                    $("#writeYn")[0].checked = true;
                else $("#writeYn")[0].checked = false;

                if(rowdata.replyYn =="Y")
                    $("#replyYn")[0].checked = true;
                else $("#replyYn")[0].checked = false;

                if(rowdata.commentYn =="Y")
                    $("#commentYn")[0].checked = true;
                else $("#commentYn")[0].checked = false;
                //---------------------------------------------

                //--사용여부 RADIO CHECKED 설정------------------
                if(rowdata.useYn =="Y")
                    $("#useYn")[0].checked = true;
                else $("#useYn")[1].checked = true;
                //---------------------------------------------

                $('#boardCount').text(rowdata.boardCount);          // 글등록갯수

            },

            rowNum:30,                                       // 한번에 출력되는 갯수
            loadui : "disable",                              //  이거 안 써주니 로딩 창 같은게 뜸// 데이터 출력시 화면 조절
            gridview:    true,                               // 꼭써주어야한다....
            //pager: '#pageList',
            emptyrecords : '<spring:message code="message.search.no.data" />',    <%-- 조회결과가 없습니다.--%>
            viewrecords: true,
            scroll : true,
            //shrinkToFit : true,                            // RowWidth 고정
            autowidth: true,
            rownumbers:true,                                 // 맨앞 일련번호
            loadonce:true
        });

        $("#tabList").jqGrid('setGroupHeaders', {
            useColSpanStyle: true,
            groupHeaders:[

                {startColumnName: 'fileCount', numberOfColumns: 2, titleText: '<spring:message code="app.manager.boardmanagerviewlist.head.uploadset" />'},	<%-- 파일업로드설정--%>
                {startColumnName: 'writeYn', numberOfColumns: 3, titleText: '<spring:message code="app.manager.boardmanagerviewlist.head.writeset" />'}		<%-- 글쓰기설정--%>
            ]
        });

    }

	/* 검색 및 조회 */
    function gridReload(){
    	setClearDetail();

        var searchInfo = {};

        gSearchForm.find('input, select').map(function() {
            if (this.name != "btnSearch") {
                searchInfo[this.name] = $(this).val();
            }
        });

        $("#tabList").jqGrid('setGridParam',{
            url:'<c:url value="/app/mgr/manager/boardManager_selList" />',                   // url 주소
            datatype        : 'json',
            postData:       searchInfo,
            mtype           : 'POST',
            page            : '1'
        }).trigger("reloadGrid");
    }


	function setClearDetail(){

		gDataForm.find("input[name='boardCd']").val('');			// 게시판코드
		gDataForm.find("input[name='oldBoardCd']").val('');		// 게시판코드 수정모드 참조용
		gDataForm.find("input[name='boardNm']").val('');			// 게시판명칭
		gDataForm.find("select[name='boardKindCd']").val('');		// 게시판유형
		gDataForm.find("input[name='userNm']").val('');			// 관리자명
		gDataForm.find("input[name='userId']").val('');			// 관리자멤버코드
		gDataForm.find("input[name='fileCount']").val('');			// 파일갯수
		gDataForm.find("input[name='fileUploadSize']").val('');	// 파일당 업로드 용량
		gDataForm.find("input[name='memberCd']").val('');	// 사용자 코드

		//--글쓰기 CHECKBOX CHECKED 설정----------------
		gDataForm.find("input[name='writeYn']")[0].checked = true;
		gDataForm.find("input[name='replyYn']")[0].checked = true;
		gDataForm.find("input[name='commentYn']")[0].checked = true;
		//---------------------------------------------

		//--사용여부 RADIO CHECKED 설정------------------
		gDataForm.find("input[name='useYn']")[0].checked = true;
		//---------------------------------------------

		$('#boardCount').text('');		 	// 글등록갯수

	}


	function copyClip(){
		if(!$("#oldBoardCd").val()) {
			alert('<spring:message code="app.manager.boardmanagerviewlist.copyclip.al" />');<%--"선택된 게시판이 없습니다."--%>
			return;
		}
		var urls = "/app/board/"+$("#oldBoardCd").val()+"/boardList"
		window.clipboardData.setData("Text", urls);
	}



</script>

</head>
<body>
<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />

			<!-- 검색조건 start ----------------------------------------------------->
			<form id="searchForm" name="searchForm" method="post">
			 <fieldset>
				<%--마스터 게시판 관리 검색 --%>
		    	<legend><spring:message code="app.manager.boardmanagerviewlist.webacc.hdtag.fieldset.legend" /></legend>
				<table style="width:100%" summary="" class=type1>
					<%--마스터 게시판 관리 검색 --%>
					<caption><spring:message code="app.manager.boardmanagerviewlist.webacc.hdtag.table.caption" /></caption>
                   	<colgroup>
                   		<col width="120">
                   		<col width="*">
                   		<col width="120">
                   		<col width="*">
                   		<col width="120">
                   		<col width="*">
                   	</colgroup>
					<tbody id="_search">
					<tr>
						<%-- 게시판명--%>
                       <th><label for="boardNm" ><spring:message code="app.manager.boardmanagerviewlist.label.boardnm" /></label></th>
                       <td>
                       	   <input  type="text"  name="boardNm" id="boardNm" title="<spring:message code="app.manager.boardmanagerviewlist.title.boardnminput" />" style="width: 80%">
                       </td>
                       <%-- 게시판유형--%>
                       <th><label for="boardKindCd" ><spring:message code="app.manager.boardmanagerviewlist.label.boardkind" /></label></th>
                       <td>
                       <spring:message code="app.manager.boardmanagerviewlist.title.boardkind" var="boardkind"/>	<%--게시판유형 선택 --%>
                       <spring:message code="common.all" var="all"/>	<%--전체 --%>
                       	   <html:codeTag comType="SELECT" objId="boardKindCd" objName="boardKindCd" objTitle="${boardkind}"  parentID="9020" defName="${all}" />
                       </td>
                       <%-- 사용여부--%>
                       <th><label for="useYn" ><spring:message code="app.manager.boardmanagerviewlist.label.useyn" /></label></th>
                       <td>
                       <spring:message code="app.manager.boardmanagerviewlist.title.useyn" var="useynselect"/>	<%--사용여부 선택 --%>
                       		<html:codeTag comType="SELECT" objName="useYn" objTitle="${useynselect }"  width="100px;" objId="useYn"  parentID="9002" defName="${all}" formName="contInfo" />
                       </td>
                   </tr>
                  </tbody>
				</table>
           </fieldset>
           </form>


            <!--  서브타이틀 및 이벤트 버튼 start  ----------------->
            <div class="tit_area" >
               	<h2><spring:message code="app.manager.boardmanagerviewlist.title.boardlist" /></h2><%--게시판 List --%>
				<div>
					<html:button id="btnSearch" auth="select"  />
				</div>
			</div>
			<!--  서브타이틀 및 이벤트 버튼 end     ---------------->

            <!-- centent List -------------------------->
            <div id="grid1container" class="gridcontainer">
                <table id="tabList" ><tr><td></td></tr></table>
            </div>

            <div id="pageList"></div>
            <!-- centent List -------------------------->

			<!--게시판 등록/수정 화면 영역  --------------------------->
	   		<div id="boardRegister">
	   		<div class="tit_area">
				<h2><spring:message code="app.manager.boardmanagerviewlist.title.boardreg" /></h2><%--게시판 등록/수정 --%>
				<div>
					<html:button id="btnNew"    	auth="insert"    />&nbsp;
					<html:button id="btnSave" 		auth="save" 	 /> 
					<html:button id="btnDelete" 	auth="delete"	 />
				</div>
			</div>
			<form id="dataForm" name="dataForm" method="post">
			<table class="type1">
				<colgroup>
					<col width="150"/>
					<col width="*"/>
					<col width="150"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<spring:message code="app.manager.boardmanagerviewlist.label.boardcd" var="titleboardcd" /> <%--분류코드--%>
						<th>* <c:out value="${titleboardcd}"></c:out></th>
						<td>
							<input type="text"   id="boardCd"  title="${titleboardcd}"  name="boardCd"  style="width: 100px; ime-mode:disabled;" >
							<spring:message code="app.manager.boardmanagerviewlist.title.urlcopy" var="urlcopy" /> <%--URL 클립보드복사--%>
							<a href="javascript:void(0);" onClick="copyClip();"><img src='<c:url value="/resources/images/pearl/common/etc.gif" />' title="${urlcopy}"></a>
							<input type="hidden"   id="oldBoardCd" name="oldBoardCd">
						</td>
						<th>*
						 <%--게시판명--%>
						<spring:message code="app.manager.boardmanagerviewlist.label.boardnm" var="labelboardnm" /><c:out value="${labelboardnm}"></c:out></th>
						<td><input type="text" id="boardNm" name="boardNm" title="${labelboardnm}" style="width: 90%;"></td>
					</tr>
					<tr>
						<th>* <spring:message code="app.manager.boardmanagerviewlist.label.boardkind" /></th> <%--게시판유형--%>
						<td>
						<spring:message code="common.select" var="commonselect" /> <%--선택 --%>
							 <html:codeTag comType="SELECT" objId="boardKindCd" objName="boardKindCd" objTitle="${boardkind}" width="100px"  parentID="9020" defName="${commonselect}" />
						</td>
						<th>* <spring:message code="app.manager.boardmanagerviewlist.label.usernm" /></th> <%--게시판관리자 --%>
						<td>
							<input type="text" readonly class="typeGHL" style="width: 70px;" name="memberCd" id="memberCd" >
                        	<input type="text" style="width: 100px;" name="userNm" id="userNm">
                        	<spring:message code="common.search" var="commonsearch" /> <%--찾기 --%>
                        	<spring:message code="app.manager.boardmanagerviewlist.title.usersearchpop" var="usersearchpop" /> <%--사용자 찾기 팝업 --%>
                        	<input type="button" class="button btn_gray" id="findMember" name="findMember" value="<c:out value='${commonsearch}'></c:out>" title="${usersearchpop}">
						</td>
					</tr>
					<tr>
						<%--파일갯수--%>
						<th><spring:message code="app.manager.boardmanagerviewlist.label.filecount" var="labelfilecount" /><c:out value='${labelfilecount}'></c:out></th>
						<td><input name="fileCount"  id="fileCount" type="text"  style="width:45px;"  title="${labelfilecount}"  ></td>
						<%--파일용량--%>
						<th><spring:message code="app.manager.boardmanagerviewlist.label.fileuploadsize" var="labelfileuploadsize"/><c:out value='${labelfileuploadsize}'></c:out></th>
						<td><input name="fileUploadSize"  id="fileUploadSize" type="text" style="width:45px;"  title="${labelfileuploadsize}"> M</td>
					</tr>
					<tr>
						<%-- 글쓰기기능 --%>
						<th><spring:message code="app.manager.boardmanagerviewlist.label.write" /></th>
						<td colspan="3">
							<p style="margin-top: 5px;">
							<spring:message code="app.manager.boardmanagerviewlist.label.msgwrite" var="msgwrite" /> <%--글 쓰 기 --%>
							<input type="checkbox" id="writeYn"   name="writeYn" title="${msgwrite}"      value="Y" checked><strong> <c:out value='${msgwrite}'></c:out></strong>
							&nbsp;: &nbsp;<spring:message code="app.manager.boardmanagerviewlist.label.msgwritedtl" /> <%--관리자 외 일반사용자의 글쓰기가 가능합니다. --%>

							<p style="margin-top: 5px;">
							<spring:message code="app.manager.boardmanagerviewlist.label.msgreply" var="msgreply" /> <%--댓글쓰기 --%>
							<input type="checkbox" id="replyYn"   name="replyYn" title="${msgreply}"    value="Y" checked><strong> <c:out value='${msgreply}'></c:out></strong>
							&nbsp;: &nbsp;<spring:message code="app.manager.boardmanagerviewlist.label.msgreplydtl" /> <%--댓글등록이 가능한 게시판으로 설정합니다.  --%>

							<p style="margin-top: 5px; margin-bottom: 5px;">
							<spring:message code="app.manager.boardmanagerviewlist.label.msgcomment" var="msgcomment" /> <%--덧글쓰기 --%>
							<input type="checkbox" id="commentYn" name="commentYn" title="${msgcomment}"  value="Y" checked><strong> <c:out value='${msgcomment}'></c:out></strong>
							&nbsp;: &nbsp;<spring:message code="app.manager.boardmanagerviewlist.label.msgcommentdtl" /> <%--한줄 덧글등록이 가능한 게시판으로 설정합니다.  --%>
						</td>
					</tr>
					<tr>
						<%--등록된 게시글 --%>
						<th><spring:message code="app.manager.boardmanagerviewlist.label.boardcount" /></th>
						<%--해당 게시판에 건의 등록글이 있습니다. --%>
						<td> &nbsp;<spring:message code="app.manager.boardmanagerviewlist.label.msgcount1" /> <font color=red><strong><span id="boardCount"></span></strong></font> <spring:message code="app.manager.boardmanagerviewlist.label.msgcount2" /> </td>
						<%--사용여부 --%>
						<th><spring:message code="app.manager.boardmanagerviewlist.label.useyn" /></th>
						<td>
							<html:codeTag comType="RADIO" objId="useYn" objName="useYn" parentID="9002" selectParam="0"  />
						</td>
					</tr>

				</tbody>
			</table>
			</form>
			</div>
			<!-- 마스터 코드 등록/수정 화면 영역 end ------------------------->

</div>

<!-- 사용자 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/common/popupFind/comMember.jsp" />
<!-- 사용자 찾기  레이어 영역 end -->

</body>
</html>
