<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script>
	$(document).ready(function($){

		initMasterCodeGrid();
		initSubCodeGrid();

		codeMasterList();


		/* BUTTON CLICK 이벤트 처리 ----------------------------------------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    codeMasterList); 			// 검색버튼
		$('#btnMasterInit').unbind().click(null,	eventMasterCodeInit); 		// 분류코드 신규등록버튼 이벤트
		$('#btnMasterSave').unbind().click(null,	eventMasterCodeSave);  		// 분류코드 저장버튼 이벤트
		$('#btnMasterDelete').unbind().click(null,	eventMasterCodeDelete); 	// 분류코드 삭제버튼 이벤트

		$('#btnSubInit').unbind().click(null, 		eventSubCodeInit);  		// 세부코드 신규등록버튼 이벤트
		$('#btnSubSave').unbind().click(null, 		eventSubCodeSave);  		// 세부코드 저장등록버튼 이벤트
		$('#btnSubDelete').unbind().click(null, 	eventSubCodeDelete);  		// 세부코드 삭제등록버튼 이벤트
		/*-----------------------------------------------------------------------------------------------------*/


		//코드명칭 조회조건 입력필드 enter key이벤트 --------------
		$('#searchNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : codeMasterList(this); break; // enter
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
                $('#masterCodeList').setGridWidth($('#grid1container').width()); //Resized to new width as per window
                $('#subCodeList').setGridWidth($('#grid2container').width()); //Resized to new width as per window

                // height
                var height = $(window).height()-$('#grid1container')[0].offsetTop;

                if(height > 475)
                	$('#masterCodeList, #subCodeList').setGridHeight(height-467);
                else if(height < 300)
                	$('#masterCodeList, #subCodeList').setGridHeight(300);
            }catch(e){}
        }).trigger('resize');


        $('#masterForm').validate({
	        rules: {
	        	 comCd   : { required: true, maxlength: 6}
	            ,comNm   : { required: true, maxlength: 50}
	          ,comSubNm  : { maxlength: 50}
	        }
			,messages: {
				 comCd   : {required: "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.mgrcode._eventmastercodesave.m1" /><div>"}	<%--분류코드를 입력하세요!--%>
				,comNm   : {required: "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.mgrcode._eventmastercodesave.m2" /><div>"}   <%--분류명칭을 입력하세요!--%>
			}

	    });


    	$('#subForm').validate({
	        rules: {
	             dtlCd   : { required: true, maxlength: 10}
	            ,dtlNm	 : { required: true, maxlength: 100}
	            ,sortNo  : { digits: true, maxlength: 9}
	           ,dtlSubNm : { maxlength: 100}
	           ,extent01 : { maxlength: 100}
	           ,extent02 : { maxlength: 100}
	        }
			,messages: {
				 dtlCd   : {required: "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.mgrcode._eventsubcodesave.m2" /><div>"}   <%--세부코드를 입력하세요!--%>
				,dtlNm   : {required: "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.mgrcode._eventsubcodesave.m3" /><div>"}   <%--세부코드명칭을 입력하세요!--%>
			}

	    });

	});



	/* 마스터코드 그리드 초기화 */
	function initMasterCodeGrid() {
		$("#masterCodeList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[  '<spring:message code="app.manager.mgrcode.head.code" />' <%--코드 --%>
            			, '<spring:message code="app.manager.mgrcode.head.name" />' <%--분류명칭 --%>
            			, 'comNm'
            			, 'comSubNm'
            			, 'comExtendNm'
            			, 'modDt'
            			, 'modFmt'
            			, 'modId'
            ]
			,colModel:[
		                {name:'comCd'     	 , index:'comCd'   			, sortable:true   , width:50 , align:"center"},
		            	{name:'comLocaleNm'  , index:'comLocaleNm'    	, sortable:true   , width:120, align:"left"},
		            	{name:'comNm'  	 	 , index:'comNm'   		, hidden:true},
		                {name:'comSubNm'  	 , index:'comSubNm' 	, hidden:true},
		                {name:'comExtendNm'  , index:'comExtendNm' 	, hidden:true},
		                {name:'modDt'     	 , index:'modDt'   		, hidden:true},
		                {name:'modDtFmt'     , index:'modDtFmt' 	, hidden:true},
		                {name:'modId'     	 , index:'modId'   		, hidden:true},

		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	$(window).resize();
	        }
			,loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

           		if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

               if(!rowdata.comCd || rowdata.comCd == '') return;

               showEditView("M");  // 마스터 코드 등록화면 활성화

               $("#masterForm").find("input[name='comCd']").val(rowdata.comCd);
               $("#masterForm").find("input[name='comCdOld']").val(rowdata.comCd);
               $("#masterForm").find("input[name='comNm']").val(rowdata.comNm);
               $("#masterForm").find("input[name='comSubNm']").val(rowdata.comSubNm);
               $("#masterForm").find("input[name='comExtendNm']").val(rowdata.comExtendNm);

               $('#modDt').text(rowdata.modDtFmt);
               $('#modId').text(rowdata.modId);

               $("#subForm").find("input[name='comCd']").val(rowdata.comCd);
               $("#subForm").find("input[name='comNm']").val(rowdata.comNm);

               searchSubCodeList(rowdata.comCd);
           }

           ,rowNum:30
           ,loadui : "disable"
           ,gridview:    true
           //,pager: '#pageList'
           ,sortname: 'comCd'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,loadonce:true
           ,autowidth:true

		});
	}

	/* 세부코드 그리드 초기화 */
	function initSubCodeGrid() {
        $("#subCodeList").jqGrid({
             datatype: 'local'
            ,data: []
            ,colNames:[    '<spring:message code="app.manager.mgrcode.head.code" />'		<%--코드 --%>
                        , '<spring:message code="app.manager.mgrcode.head.name" />'		<%--분류명칭 --%>
                        , 'comCd'
                        , 'comNm'
                        , 'comSubNm'
                        , 'comExtendNm'
                        , 'comLocaleNm'
                        , 'dtlNm'
                        , 'dtlSubNm'
                        , 'dtlExtendNm'
                        , 'modDt'
                        , 'modDtFmt'
                        , 'modId'
                        , 'sortNo'
                        , 'useYn'
                        , 'extent01'
                        , 'extent02'
             ]
            ,colModel:[
                 {name:'dtlCd'       , index:'dtlCd'      	   , sortable:true   , width:50  , align:"center"}
            	,{name:'dtlLocaleNm' , index:'dtlLocaleNm'     , sortable:true   , width:120 , align:"left"}
            	,{name:'comCd'       , index:'comCd'       , hidden:true}
                ,{name:'comNm'       , index:'comNm'       , hidden:true}
                ,{name:'comSubNm'    , index:'comSubNm'    , hidden:true}
                ,{name:'comExtendNm' , index:'comExtendNm' , hidden:true}
                ,{name:'comLocaleNm' , index:'comLocaleNm' , hidden:true}
                ,{name:'dtlNm'    	 , index:'dtlNm'   	   , hidden:true}
                ,{name:'dtlSubNm'    , index:'dtlSubNm'    , hidden:true}
                ,{name:'dtlExtendNm' , index:'dtlExtendNm' , hidden:true}
                ,{name:'modDt'       , index:'modDt'       , hidden:true}
                ,{name:'modDtFmt'    , index:'modDtFmt'    , hidden:true}
                ,{name:'modId'       , index:'modId'       , hidden:true}
                ,{name:'sortNo'      , index:'sortNo'      , hidden:true}
                ,{name:'useYn'       , index:'useYn'       , hidden:true}
                ,{name:'extent01'    , index:'extent01'    , hidden:true}
                ,{name:'extent02'    , index:'extent02'    , hidden:true}
            ]
           ,gridComplete : function() {
            	var colCount = $(this).getGridParam("colNames").length;
                $("#blankRow td:nth-child(2)").attr("colspan", colCount);
           }
           ,loadComplete: function() {
                if ($(this).getGridParam("records")==0) {
                    var firstColName = $(this).getGridParam("colModel")[1].name;
                    var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                    $(this).addRowData("blankRow", msg);
                }
            }
           ,loadError:function(xhr, status, error) {
                alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
                message.error.process
                return false;
            }
           ,onSelectRow : function(id, status, e) {

                if (id == 'blankRow')return;
            	var rowdata = $(this).getRowData(id);

                if(!rowdata.comCd || rowdata.comCd == '') return;

                showEditView("S");  // 서브코드 등록화면 활성화

                // 마스터코드,명 정보----------------------------
                $("#subForm").find("input[name='comCd']").val(rowdata.comCd);  			// 마스터코드
                $("#subForm").find("input[name='comNm']").val(rowdata.comLocaleNm);  	// 마스터코드명
                //---------------------------------------------

                $('#modDtSub').text(rowdata.modDtFmt);         // 최종수정일자
                $('#modIdSub').text(rowdata.modId);            // 최종작업자

                //서브코드 정보----------------------------------
                $("#subForm").find("input[name='dtlCd']").val(rowdata.dtlCd);       // 서브코드
                $("#subForm").find("input[name='dtlCdOld']").val(rowdata.dtlCd);    // 서브코드이전값체크용
                $("#subForm").find("input[name='dtlNm']").val(rowdata.dtlNm);       // 서브코드명
                $("#subForm").find("input[name='dtlSubNm']").val(rowdata.dtlSubNm); // 서브코드확장명
                $("#subForm").find("input[name='dtlExtendNm']").val(rowdata.dtlExtendNm); // 서브코드확장명
                $("#subForm").find("input[name='sortNo']").val(rowdata.sortNo);     // 세부수치(기타숫자)


                //--사용여부 RADIO CHECKED 설정------------------
                if(rowdata.useYn =="Y")
                    $("#subForm").find("input[name='useYn']")[0].checked = true;
                else $("#subForm").find("input[name='useYn']")[1].checked = true;
                //---------------------------------------------

                if(rowdata.extent01 != 'null')
                    $("#subForm").find("input[name='extent01']").val(rowdata.extent01);        // 예비속성1
                if(rowdata.extent02 != 'null')
                    $("#subForm").find("input[name='extent02']").val(rowdata.extent02);        // 예비속성2
                //---------------------------------------------
            }
			,rowNum			:30
            ,loadui 		: "disable"
            ,gridview		: true
            //,pager		: '#pageList'
            ,sortname		: 'dtlCd'
            ,sortorder		: 'asc'
            ,emptyrecords 	: '<spring:message code="message.search.no.data" />'     <%-- 조회결과가 없습니다.--%>
            ,viewrecords	: true
            ,scroll 		: true
            ,rownumbers		: true
            ,loadonce		: true
        });
	}


	/* CODE MASTER  조회 */
	function codeMasterList(event) {
		var comNm   = "";
        var dtlNm   = "";

        /*검색버튼 : 이벤트일때만 검색조건으로 검색하도록 함  */
        /*검색조건 : M:분류명칭 검색, S:세부명칭 검색      */
        if(event){
            if($('input[name="searchType"]')[0].checked) comNm = $('#searchNm').val();
            else   dtlNm = $('#searchNm').val();
        }
        /*-------------------------------------------*/

        languageType = $("input[name='searchLanguageType']:checked").val();

        var str = { 'comNm'   		: comNm
                   ,'dtlNm'   		: dtlNm
                   ,'languageType'	: languageType
        };

        $("#masterCodeList").jqGrid('setGridParam',{
           url:'<c:url value="/app/mgr/manager/mgrCode_selMasterList" />'				// url 주소
          ,datatype: "json"                                     // 보내는 데이터 타입
          ,postData:str                                         // 보내는 데이터 형식
          ,mtype:'POST'                                         // POST,GET,LOCAL 3가지 존재
          ,loadBeforeSend: function(jqXHR) {
          	 jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
          }
        }).trigger("reloadGrid");
	}


	function searchSubCodeList(comCd){

		//서브코드 리스트 조회------------------------------------------
		var str = {'comCd' : comCd // 코드구분마스터코드
				  };

	  	$("#subCodeList").jqGrid('setGridParam',{
            url:'<c:url value="/app/mgr/manager/mgrCode_selSubList" />', 				// url 주소
            datatype: "json",                                   // 보내는 데이터 타입
            postData:str,                                       // 보내는 데이터 형식
            mtype:'POST',                                       // POST,GET,LOCAL 3가지 존재
            loadBeforeSend: function(jqXHR) {
                jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
            },
			jsonReader : {
                root:  "rows",
                repeatitems: false,
            }

        }).trigger("reloadGrid");
	  	//-------------------------------------------------------------
	}


	/*분류코드 신규등록 버튼 이벤트*/
	function eventMasterCodeInit(){
		// 서브코드   dataList 초기화
		$("#subCodeList").jqGrid('clearGridData').trigger("reloadGrid");

		showEditView("M");				// 분류코드 등록화면 활성화
		setClearMstCodeDetail(); 		// 분류코드 화면 초기화
	}


	/*분류코드 저장(등록/수정) 버튼 이벤트 validate*/
	function eventMasterCodeSave(){

		if($('#masterForm').valid()){
			masterCodeSaveAction();
		}

	}

	/*분류코드 저장 실행*/
	function masterCodeSaveAction() {
		var codeInfo = {};
		$('#masterForm').find('input , select').map(function() {
			 codeInfo[this.name] = $(this).val();
		});

		// 저장 이벤트 json 실행---------------------------
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/mgrCode_insMasterData" />',
		      data : JSON.stringify(codeInfo),
		      success : function(data){

		    	  if(data == null) msg = '<spring:message code="app.manager.mgrcode._eventmastercodesaveaction.m1" />'; <%--"처리된 데이터가 없습니다.";--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="app.manager.mgrcode._eventmastercodesaveaction.m2" />'; <%--"분류코드 저장이 정상적으로 처리되었습니다.";--%>
	            	codeMasterList();			// 분류코드 제조회
	            	setClearMstCodeDetail();	// 분류코드 등록화면 초기화
	            }
	            else if('duple' == data.msgCd) {
	            	msg ='<spring:message code="app.manager.mgrcode._eventmastercodesaveaction.m3" />'; <%-- "이미 등록된 분류코드 입니다.\n분류코드를 변경하세요!";--%>
	            }
	            else msg = '<spring:message code="app.manager.mgrcode._eventmastercodesaveaction.m5" />';<%--"분류코드 저장중 오류가 발생하였습니다.";--%>
	          	alert(msg);
		      }
		});
		//-----------------------------------------------
	}


	/*분류코드 삭제버튼 이벤트*/
	function eventMasterCodeDelete(){
		var form = document.masterForm;
		var comCdOld = form.comCdOld.value;

		if(!comCdOld) {
			alert('<spring:message code="app.manager.mgrcode._eventmastercodedelete.al" />');<%--선택된 분류코드 정보가 없습니다.--%>
			return;
		}

		// 저장확인 메세지 --------------------------------
		if (!confirm( '<spring:message code="app.manager.mgrcode._eventmastercodedelete.c1" arguments="'+comCdOld+'" />')){<%--"분류코드 ["+comCdOld+"] 및 세부코드를 삭제 하시겠습니까?"--%>
        	return false;
		}
		//-----------------------------------------------

		var str = {'comCdOld' : comCdOld}; // 대분류코드

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/mgrCode_delMasterData" />',
		      data : JSON.stringify(str),
		      success : function(data){

		    	if(data == null) msg = '<spring:message code="app.manager.mgrcode._eventmastercodedelete.m1" />'; <%--"처리된 데이터가 없습니다.";--%>
	          	else if('success' == data.msgCd)
	            {
	          		msg = '<spring:message code="app.manager.mgrcode._eventmastercodedelete.m2" />'; <%--"분류코드 삭제가 정상적으로 처리되었습니다.";--%>
	            	codeMasterList();			// 분류코드 제조회
	            	setClearMstCodeDetail();	// 분류코드 등록화면 초기화
	            }
	          	 else msg = '<spring:message code="app.manager.mgrcode._eventmastercodedelete.m3" />'; <%--"분류코드 삭제 중 오류가 발생하였습니다.";--%>

	          	alert(msg);
		      }
		});
	}

	/*서브코드 신규등록 버튼 이벤트*/
	function eventSubCodeInit() {
		var setFrom = document.subForm;

		if(!setFrom.comCd.value) {
			alert('<spring:message code="app.manager.mgrcode._eventsubcodeinit.a1" />');<%--분류코드를 먼저선택하세요!--%>
			return false;
		}

		showEditView("S");			// 서브코드 등록화면 활성화
		setClearSubCodeDetail(); 	// 서브코드 화면 초기화
	}


	/*세부코드 저장(등록/수정) 버튼 이벤트*/
	function eventSubCodeSave(){

		if($('#subForm').valid()){
			eventSubCodeSaveAction();
		}

	}

	/*세부코드 저장 실행*/
	function eventSubCodeSaveAction(){

		var comCd 	= "";	//제조회용 key
		var useYn = "";	//사용여부 Y/N
		var codeInfo = {};
		$('#subForm').find('input, select').map(function() {

			if(this.name =='useYn'){
				//사용여부 RADIO 값설정
				if(!useYn) {
					if( $(this)[0].checked) 	useYn = "Y";
					else useYn = "N";
					codeInfo[this.name] = useYn;
				}
			}
			else codeInfo[this.name] = $(this).val();

			if(this.name == "comCd") comCd = $(this).val();
		});

		// 저장 이벤트 json 실행---------------------------
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/mgrCode_insSubData" />',
		      data : JSON.stringify(codeInfo),
		      success : function(data){

		    	  	if(data == null) msg = '<spring:message code="app.manager.mgrcode._eventsubcodesaveaction.m1" />'; <%--"처리된 데이터가 없습니다.";--%>
		            else if('success' == data.msgCd)
		            {
		            	msg = '<spring:message code="app.manager.mgrcode._eventsubcodesaveaction.m2" />'; <%--"세부코드 저장이 정상적으로 처리되었습니다.";--%>
		            	searchSubCodeList(comCd);		// 세분류코드 제조회
		            	setClearSubCodeDetail();	// 세분류코드 등록화면 초기화
		            }
		            else if('duple' == data.msgCd) 	msg = '<spring:message code="app.manager.mgrcode._eventsubcodesaveaction.m3" />'; <%--"이미 등록된 세부코드 입니다."--%>
		            else msg = '<spring:message code="app.manager.mgrcode._eventsubcodesaveaction.m5" />'; <%--"세부코드 저장중 오류가 발생하였습니다.";--%>

	          		alert(msg);
		      }
		});
		//-----------------------------------------------
	}

	/*세부코드 삭제버튼 이벤트*/
	function eventSubCodeDelete(){

		var form 		= document.subForm;
		var comCd 		= form.comCd.value;
		var dtlCdOld	= form.dtlCdOld.value;


		if(!dtlCdOld) {

			alert('<spring:message code="app.manager.mgrcode._eventsubcodedelete.a1" />'); <%--선택된 세부코드 정보가 없습니다.--%>
			return;
		}

		// 저장확인 메세지 --------------------------------
		<%--"코드 ["+comCd+" | "+dtlCdOld+"]  세부코드를 삭제 하시겠습니까?"--%>
		if (!confirm( '<spring:message code="app.manager.mgrcode._eventsubcodedelete.c1" arguments="'+comCd+','+dtlCdOld+'"  />')) {
	        return false;
		}
		//-----------------------------------------------

		var str = {'comCd'    : comCd, 		// 대분류코드
				   'dtlCdOld' : dtlCdOld	// 세부  코드
		  		  };

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/mgrCode_delSubData" />',
		      data : JSON.stringify(str),
		      success : function(data){
		    	  if(data == null) msg = '<spring:message code="app.manager.mgrcode._eventsubcodedelete.m1" />';<%-- "처리된 데이터가 없습니다.";--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="app.manager.mgrcode._eventsubcodedelete.m2" />';<%--"세부코드 삭제가 정상적으로 처리되었습니다.";--%>
	            	searchSubCodeList(comCd); // 세부코드 제조회
	            	setClearSubCodeDetail();	 // 세부코드 등록화면 초기화
	            }
	            else msg = '<spring:message code="app.manager.mgrcode._eventsubcodedelete.m3" />';<%--"세부코드 삭제 중 오류가 발생하였습니다.";--%>

	          	alert(msg);
		      }
		});
	}




	/*분류코드 등록 화면초기화*/
	function setClearMstCodeDetail(){

		$("#masterForm").find("label").filter(".error").remove();

		$('#modDt').text('');		// 최종수정일자
		$('#modId').text('');		// 최종작업자

		$("#masterForm").find("input[name='comCd']").val('');
		$("#masterForm").find("input[name='comCdOld']").val('');
		$("#masterForm").find("input[name='comNm']").val('');
		$("#masterForm").find("input[name='comSubNm']").val('');
		$("#masterForm").find("input[name='comExtendNm']").val('');
		$("#subForm").find("input[name='comCd']").val('');
		$("#subForm").find("input[name='comNm']").val('');

		$("#masterForm").find("input[name='comCd']").focus();
	}

	/*서브코드 등록 화면초기화*/
	function setClearSubCodeDetail() {

		$("#subForm").find("label").filter(".error").remove();

		$('#modDtSub').text('');			// 최종수정일자
		$('#modIdSub').text('');		// 최종작업자

		//서브코드 정보----------------------------------
		$("#subForm").find("input[name='dtlCd']").val('');				// 서브코드
		$("#subForm").find("input[name='dtlCdOld']").val('');			// 서브코드이전값체크용
		$("#subForm").find("input[name='dtlNm']").val('');				// 서브코드명
		$("#subForm").find("input[name='dtlSubNm']").val('');			// 서브코드확장명
		$("#subForm").find("input[name='dtlExtendNm']").val('');		// 서브코드확장명(VN)
		$("#subForm").find("input[name='sortNo']").val('');				// 소트순서
		$("#subForm").find("input[name='useYn']")[0].checked = true;	// 사용여부'Y설정'
		$("#subForm").find("input[name='extent01']").val('');			// 예비속성1
		$("#subForm").find("input[name='extent02']").val('');			// 예비속성2

		$("#subForm").find("input[name='dtlCd']").focus();
	}

	function showEditView(type) {

		if(type == "M") {
			$("#masterRegister").show();
			$("#subRegister").hide();
			$("#masterForm").find("label").filter(".error").remove();
		}
		else
		{
			$("#masterRegister").hide();
			$("#subRegister").show();
			$("#subForm").find("label").filter(".error").remove();
		}

	}
</script>


<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />

<!-- 검색조건 start ------------------------------------>
	<form id="codeInfo" name="codeInfo" method="post">
	<fieldset>
		<legend><spring:message code="app.manager.mgrcode.webacc.hdtag.fieldset.legend" /></legend> <%--마스터 디테일 코드 검색 --%>
		<table style="width:100%">
			<caption><spring:message code="app.manager.mgrcode.webacc.hdtag.table.caption" /></caption> <%-- 마스터 디테일 코드 검색 --%>
			<colgroup>
				<col width="200">
                <col width="*">
			</colgroup>
			<tbody id="_search">
            	<tr>
                	<th style="padding:5px; border:1px solid #e5e5e5; color:#555; background:#f2f2f2;"><label for="sele2"><spring:message code="app.manager.mgrcode.label.classified" /></label></th>
                    <td style="padding:5px; border:1px solid #e5e5e5; font-size:11px;">
                    	<table style="width:100%">
                      		<tr>
                      			<td>
                         			<html:codeTag comType="RADIO" objId="searchType" objName="searchType" parentID="9003" selectParam="0"  />
                         			&nbsp;
                         			<input type="text"  id="searchNm"  style="width:200px;" name="searchNm">
                       			</td>
	                        	<td width="300" align=right style="border-left-color:#ffffff ">

	                        		<html:codeTag comType="RADIO" objId="searchLanguageType" objName="searchLanguageType"  parentID="9000" selectParam="${pageContext.response.locale}"   />
	                        		<html:button id="btnSearch"  auth="select"  />
	                        	</td>
	                        </tr>
                      	</table>
                    </td>
                </tr>
            </tbody>
		</table>
    </fieldset>
    </form>
    <!-- 검색조건 end ----------------------------------->

    <!-- CONTENT BODY start  ----------------->
    <TABLE style="width: 100%">
    	<colgroup>
				<col width="50%"/>
				<col width="5"/>
				<col width="50%"/>
		</colgroup>
    	<TR>
    		<TD>
				<!-- 분류 서브타이틀 및 이벤트 버튼 start  ----------------->
                <div class="tit_area" >
                	<h2> <spring:message code="app.manager.mgrcode.title.classified" /></h2>	<%-- 분류코드 List--%>
					<div>
						<html:button id="btnMasterInit" auth="insert" /> 
					</div>
				</div>
				<!-- 대분류 서브타이틀 및 이벤트 버튼 end  ----------------->

                <!-- centent List -------------------------->
                <div id="grid1container" class="gridcontainer">
                	<table id="masterCodeList" ><tr><td></td></tr></table>
                </div>

                <div id="pageList"></div>
                <!-- centent List -------------------------->

			</TD>
			<TD></TD>
			<TD>
				<!-- 코드 서브타이틀 및 이벤트 버튼 start  ------------------>
                <div class="tit_area">
                	<h2><spring:message code="app.manager.mgrcode.title.subcode" /></h2>	<%--세부코드 List --%>
					<div>
						<html:button id="btnSubInit" auth="insert" />
					</div>
				</div>
				<!-- 서브타이틀 및 이벤트 버튼 end     --------------------->

                <!-- centent List -------------------------->
				<div id="grid2container" class="gridcontainer" >
                	<table id="subCodeList" ><tr><td></td></tr></table>
				</div>

				<div id="pageList2"></div>
                <!-- centent List -------------------------->
			</TD>
		</TR>
	</TABLE>
	<!-- CONTENT BODY end  ----------------->


	<!--마스터 코드 등록/수정 화면 영역  --------------------------->
   		<div id="masterRegister">
   		<form name="masterForm" id="masterForm"  >
   		<div class="tit_area">
			<h2 class="subhead"><spring:message code="app.manager.mgrcode.title.classcodedesc" /></h2>	<%-- 분류코드 등록/수정 --%>
			<div>
				<html:button id="btnMasterSave"   name="btnMasterSave"      auth="save" 	 />
				<html:button id="btnMasterDelete" name="btnMasterDelete" 	auth="delete" 	 />
			</div>
		</div>

		<table class="type1">
			<colgroup>
				<col width="200"/>
				<col width="*"/>
				<col width="200"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr height="26">
					<%-- 등록자아이디 --%>
					<th><spring:message code="app.manager.mgrcode.label.regid" /></th>
					<td><span id="modId" class="txt_c"></span></td>
					<%-- 최종수정일자 --%>
					<th><spring:message code="app.manager.mgrcode.label.moddt" /></th>
					<td><span id="modDt" class="txt_c"></span></td>
				</tr>
				<tr>
					<%-- 분 류 코 드 --%>
					<th>* <spring:message code="app.manager.mgrcode.label.classcode" /></th>
					<td>
						<input type="text"   id="comCd"    name="comCd" style="width: 100px; ime-mode:disabled;" >
						<input type="hidden" id="comCdOld" name="comCdOld">
					</td>
					<%-- 분 류 명 칭 --%>
					<th>* <spring:message code="app.manager.mgrcode.label.classnamekor" /></th>
					<td><input type="text" id="comNm" name="comNm"  style="width: 90%;"></td>
				</tr>
				<tr>
					<%-- 분류영문명칭 --%>
					<th>&nbsp;&nbsp; </th>
					<td>&nbsp;</td>
					<th><spring:message code="app.manager.mgrcode.label.classnameeng" /></th>
					<td><input type="text" id="comSubNm" name="comSubNm"  style="width: 90%;" ></td>

				</tr>
				<tr height="35">
					<th>&nbsp;&nbsp; </th>
					<td>&nbsp;</td>
					<%-- 분류명칭(VN) --%>
					<th><spring:message code="app.manager.mgrcode.label.classnamevn" /></th>
					<td><input type="text" id="comExtendNm" name="comExtendNm"  style="width: 90%;" ></td>
				</tr>
				<tr height="35">
					<th>&nbsp;&nbsp; </th>
					<td>&nbsp;</td>
					<th>&nbsp;&nbsp; </th>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		</form>
		</div>
		<!-- 마스터 코드 등록/수정 화면 영역 end ------------------------->



		<!--서브 코드 등록/수정 화면 영역  --------------------------->
   		<div id="subRegister" style="display:none;">
   		<form name="subForm" id="subForm"  >
   		<div class="tit_area">
			<h2 class="subhead"><spring:message code="app.manager.mgrcode.title.subdesc" /></h2> <%--세부코드 등록/수정 --%>
			<div>
				<html:button id="btnSubSave"   name="btnSubSave"	auth="save" 	 	 />
				<html:button id="btnSubDelete" name="btnSubDelete"	auth="delete"	     />
			</div>
		</div>

		<table class="type1">
			<colgroup>
				<col width="200"/>
				<col width="*"/>
				<col width="200"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr height="26">
					<%-- 등록자아이디 --%>
					<th><spring:message code="app.manager.mgrcode.label.regid" /></th>
					<td><span id="modIdSub" class="txt_c"></span></td>
					<%-- 최종수정일자 --%>
					<th><spring:message code="app.manager.mgrcode.label.moddt" /></th>
					<td><span id="modDtSub" class="txt_c"></span></td>
				</tr>
				<tr>
					<%-- 분 류 코 드 --%>
					<th><spring:message code="app.manager.mgrcode.label.classcode" /></th>
					<td><input type="text" id="comCd" name="comCd"  style="width: 100px; border:0px;" readonly ></td>
					<%-- 분 류 명 칭 --%>
					<th><spring:message code="app.manager.mgrcode.label.classname" /></th>
					<td><input type="text" id="comNm" name="comNm"  style="width: 90%; border:0px;" readonly></td>
				</tr>
				<tr>
					<%-- 세 부 코 드 --%>
					<th><spring:message code="app.manager.mgrcode.label.subcode" /></th>
					<td>
						<input type="text" id="dtlCd" name="dtlCd" style="width: 100px; ime-mode:disabled;"  >
						<input type="hidden" id="dtlCdOld" name="dtlCdOld" style="width: 90%;">
					</td>
					<%-- 분 류 명 칭(KR) --%>
					<th><spring:message code="app.manager.mgrcode.label.classnamekor" /></th>
					<td><input type="text" id="dtlNm" name="dtlNm"  style="width: 90%;" ></td>
				</tr>

				<tr>
					<%-- 분류영문명칭 --%>
					<%-- 정렬순서/사용여부 --%>
					<th><spring:message code="app.manager.mgrcode.label.sort_useyn" /></th>
					<td>
					    <input type="text" id="sortNo" name="sortNo" style="width: 100px; ime-mode:disabled;" > /
						<html:codeTag comType="RADIO" objId="useYn" objName="useYn" parentID="9002" selectParam="0"  />
					</td>
					<th><spring:message code="app.manager.mgrcode.label.classnameeng" /></th>
					<td><input type="text" id="dtlSubNm" name="dtlSubNm"  style="width: 90%;" ></td>

				</tr>
				<tr>
					<%-- 추가속성(1) --%>
					<th><spring:message code="app.manager.mgrcode.label.detail1" /></th>
					<td><input type="text" id="extent01" name="extent01"  style="width: 90%;" ></td>
					<%-- 분류명칭(VN) --%>
					<th><spring:message code="app.manager.mgrcode.label.classnamevn" /></th>
					<td><input type="text" id="dtlExtendNm" name="dtlExtendNm"  style="width: 90%;" ></td>
				</tr>
				<tr>

					<%-- 추가속성(2) --%>
					<th><spring:message code="app.manager.mgrcode.label.detail2" /></th>
					<td><input type="text" id="extent02" name="extent02"  style="width: 90%;" ></td>
					<th></th><td></td>
				</tr>
			</tbody>
		</table>
		</form>
		</div>
		<!-- 마스터 코드 등록/수정 화면 영역 end ------------------------->

</div>