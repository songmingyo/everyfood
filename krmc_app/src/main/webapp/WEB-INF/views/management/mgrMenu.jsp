<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<script>

	$(document).ready(function($){

		initMenuDataList('1');		    // 모든 메뉴 초기화
		initMenuDataList('2');		    // 모든 메뉴 초기화
		initMenuDataList('3');		    // 모든 메뉴 초기화

		menuDataList('1','10001');		// 기본대메뉴조회

		// 버튼이벤트처리 ---------------------------------------------------------------------------------------------------
		$('#btnLagInit').unbind().click(function() { eventMenuInit('L'); }); 		// 메뉴 대분류 신규등록버튼 이벤트
		$('#btnMidInit').unbind().click(function() { eventMenuInit('M'); }); 		// 중분류메뉴 신규등록버튼 이벤트
		$('#btnSmaInit').unbind().click(function() { eventMenuInit('S'); }); 		// 소분류메뉴 신규등록버튼 이벤트

		$('#btnSave').unbind().click(null,			eventMenuSave); 				// 저장버튼(대중소 메뉴 저장:insert,update)
		$('#btnDelete').unbind().click(null,		eventMenuDelete); 				// 삭제버튼
		$('#findMenuIcon').unbind().click(null,		_setIconFindLayerEvent); 		// 아이콘찾기 버튼 
		//--------------------------------------------------------------------------------------------------------------------

		
		//grid resize
		$(window).bind('resize', function() {
			try{
				// width
				// 그리드의 width를 div 에 맞춰서 적용
				$('#tabList1').setGridWidth($('#grid1container').width()); //Resized to new width as per window
				$('#tabList2').setGridWidth($('#grid2container').width()); //Resized to new width as per window
				$('#tabList3').setGridWidth($('#grid3container').width()); //Resized to new width as per window

				// height
				var height = $(window).height()-$('#grid1container')[0].offsetTop;
				if(height > 525)
					$('#tabList1, #tabList2, #tabList3').setGridHeight(height-426);
				else if(height < 300)
					$('#tabList1, #tabList2, #tabList3').setGridHeight(300);
			}catch(e){}
		}).trigger('resize');

    	 $('#detailFrm').validate({

    		 rules: {
					title  : { required: true, maxlength: 50 }
					,sortInLevel  : { required: true, digits: true, maxlength: 3 }
					,webPage  : { required: true, maxlength: 100 }
					,extraInfo : { maxlength: 100 }
	        },

   			messages: {
   					title			:	{ required : '<spring:message code="app.manager.menuviewlist.head.menunm"/>'+'<spring:message code="message.required"/>'
										,maxlength : '<spring:message code="app.manager.menuviewlist.head.menunm"/>'+'<spring:message code="app.common.limit.maxLength" arguments = '50' />'}
					,sortInLevel	:	{ required : '<spring:message code="app.manager.mgrcode.label.sort"/>'+'<spring:message code="message.required"/>'}
					,webPage		:	{ required : '<spring:message code="app.manager.menuviewlist.label.webpage"/>'+'<spring:message code="message.required"/>'}
					,extraInfo		:	{ maxlength : '<spring:message code="app.manager.menuviewlist.label.extrainfo"/>'+'<spring:message code="app.common.limit.maxLength" arguments = '100' />'}
   	   			}
	    }); 

	});


	/*메뉴 그리드 초기화*/
	function initMenuDataList(gridNum) {

		$("#tabList" + gridNum).jqGrid({
        	datatype: 'local',
        	data: [],

	        colNames:[  '<spring:message code="app.manager.menuviewlist.head.menucd" />'	<%-- 메뉴코드 --%>
					   ,'<spring:message code="app.manager.menuviewlist.head.menufg" />'	<%-- 구분 --%>
					   ,'<spring:message code="app.manager.menuviewlist.head.menunm" />'	<%-- 메뉴명칭 --%>
					   ,'<spring:message code="app.manager.menuviewlist.head.excType" />' 	<%-- Dev.--%>
            	       , 'parentId'
                	   , 'commandFgNm'
                   	   , 'commandFgSubNm'
                   	   , 'title'
                   	   , 'titleSub'
                   	   , 'titleExtend'
                   	   , 'menuFg'
                   	   , 'commandFg'
                   	   , 'hideYn'
                   	   , 'useYn'
					   , 'vdiCd'
                   	   , 'webPage'
                   	   , 'extraInfo'
                       , 'sortInLevel'
                   	   , 'menuLevel'
                   	   , 'dtlLogYn'
                   	   , 'menuIcon'
                   	   ],
        	colModel:[
            	{name:'menuId'      		, index:'menuId'      		, sortable:true   , width:120	, align:"center"},  	// 프로그램 아이디
            	{name:'commandFgLocaleNm' 	, index:'commandFgLocaleNm' , sortable:true   , width:80	, align:"center"}, 		// 구분
   	    		{name:'menuTitleLocaleNm' 	, index:'menuTitleLocaleNm' , sortable:true   , width:300	, align:"left"	},  	// 메뉴명칭
   	    		{name:'excTypeFg'			, index:'excTypeFg'	  		, sortable:false  , width:35	, align:"center"},		// DEVICE[W/M/R]
            	{name:'parentId'	   		, index:'parentId'	    	, hidden:true},     // 프로그램 부모 아이디
            	{name:'commandFgNm'    		, index:'commandFgNm'   	, hidden:true},		// 구분
            	{name:'commandFgSubNm' 		, index:'commandFgSubNm' 	, hidden:true}, 	// 구분 확장명
            	{name:'title'  	 	 		, index:'title'   			, hidden:true},		// 프로그램명칭
            	{name:'titleSub'  	 		, index:'titleSub'   		, hidden:true}, 	// 프로그램명칭
            	{name:'titleExtend'  		, index:'titleExtend'   	, hidden:true}, 	// 프로그램명칭(추가확장언어)
            	{name:'menuFg'				, index:'menuFg'	  		, hidden:true},     // 메뉴타입(Y:기본메뉴(빌트인), N:추가메뉴
            	{name:'commandFg'			, index:'commandFg'	  		, hidden:true},     // 실행구분(CODE : 9010  0:폴더, 1:실행, 2:새창, 3:모달창)
            	{name:'hideYn'				, index:'hideYn'	  		, hidden:true},     // 숨김구분(Y/N)
            	{name:'useYn'				, index:'useYn'		  		, hidden:true},     // 사용여부구분(Y/N)
             	{name:'vdiCd'				, index:'vdiCd'		  		, hidden:true},     // 아이피접근정책 코드(9050)
            	{name:'webPage'				, index:'webPage'	  		, hidden:true},     // 페이지경로
            	{name:'extraInfo'			, index:'extraInfo'	  		, hidden:true},     // 메뉴경로 확장
            	{name:'sortInLevel'			, index:'sortInLevel' 		, hidden:true},     // 메뉴소트레벨
            	{name:'menuLevel'			, index:'menuLevel'	  		, hidden:true},     // 메뉴레벨
            	{name:'dtlLogYn'			, index:'dtlLogYn'	  		, hidden:true},     // 상세로그생성여부
				{name:'menuIcon'			, index:'menuIcon'	  		, hidden:true}      // 메뉴아이콘				
        	],

        	gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	            var colCount = $(this).getGridParam("colNames").length;
            	$("#blankRow" + gridNum + " td:nth-child(1)").attr("colspan", colCount);

            	$(window).resize();
        	},
        	loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
            	if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[0].name;
    	            var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                	$(this).addRowData("blankRow" + gridNum, msg);
            	}

            	$(window).resize();
	        },
        	loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
	        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            	return false;
        	},
        	onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
	            if (id == ('blankRow' + gridNum)) {
                	return;
            	}
	          
    	        var rowdata = $(this).getRowData(id);

            	if(!rowdata) return;

            	if (gridNum == '1') {
	                /* 대분류 목록 Click Event */
    	            setMenuDetail('1', rowdata);
                	menuDataList('2', rowdata.menuId);

				} else if (gridNum == '2') {
                	/* 중분류 목록 Click Event */
                	setMenuDetail('2', rowdata);
                	menuDataList('3', rowdata.menuId);

				} else if (gridNum == '3') {
                	/* 소분류 목록 Click Event */
                	setMenuDetail('3', rowdata);
				}
        	},

        	rowNum:30,                                                                          // 한번에 출력되는 갯수
        	loadui : "disable",                                                                 //  이거 안 써주니 로딩 창 같은게 뜸// 데이터 출력시 화면 조절
        	gridview:    true,                                                                  // 꼭써주어야한다....
        	//pager: '#pageList' + gridNum,
        	sortname: 'menuId',
        	sortorder: 'asc',
        	emptyrecords : '<spring:message code="message.search.no.data" />',                 <%-- 조회결과가 없습니다.--%>
        	viewrecords: true,
        	scroll : true,
        	//rownumbers:true,                                                                    //맨앞 일련번호
        	loadonce:true,
        	autowidth:true
    	});
	}



	/*메뉴 조회*/
	function menuDataList(gridNum, parentId) {
		//PARENT_ID
        var str = {'parentId' : parentId }; // 상위메뉴코드
		$("#tabList" + gridNum).jqGrid('setGridParam',{
			url:"<c:url value='/app/mgr/manager/mgrMenu_selMenuList'/>",
	        datatype: "json",
	        postData:str,
	        mtype:'POST',
	        //jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
	        loadBeforeSend: function(jqXHR) {
                jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
            },
            jsonReader : {
                root:  "rows",
                repeatitems: false,
            }

        }).trigger("reloadGrid");

	}


	/*선택메뉴 상세화면 data 바인드*/
	function setMenuDetail(nowType, rowdata){
		
		var menuId 		= rowdata.menuId;		// 프로그램아이디
		var parentId 	= rowdata.parentId;		// 프로그램 부모 아이디
		var title 		= rowdata.title;		// 프로그램명칭
		var titleSub 	= rowdata.titleSub;		// 프로그램확장명칭
		var titleExtend = rowdata.titleExtend;	// 프로그램확장명칭(VN)
		var commandFg 	= rowdata.commandFg;	// 실행구분
		var menuFg 	    = rowdata.menuFg;		// 메뉴유형
		var hideYn 	    = rowdata.hideYn;		// 숨김여부
		var useYn 	    = rowdata.useYn;		// 사용여부
 		var vdiCd 	    = rowdata.vdiCd;		// VDI적용여부
		var sortInLevel = rowdata.sortInLevel;	// 소트레벨
		var menuLevel 	= rowdata.menuLevel;	// 메뉴레벨
		var webPage 	= rowdata.webPage;		// 실행경로
		var extraInfo 	= rowdata.extraInfo;	// 확장경로
		var dtlLogYn	= rowdata.dtlLogYn;		// 상세로그생성여부[Y,N]
		var excTypeFg   = rowdata.excTypeFg;	// DEVICE[W/M/R]
		var menuIcon	= rowdata.menuIcon;		// 메뉴아이콘
		
		$("#detailFrm").find("input[name='menuId']").val(menuId);			// 프로그램아이디
		$("#detailFrm").find("input[name='parentId']").val(parentId);		// 프로그램 부모 아이디
		$("#detailFrm").find("input[name='title']").val(title);				// 프로그램명칭
		$("#detailFrm").find("input[name='titleSub']").val(titleSub);		// 프로그램확장명칭
		$("#detailFrm").find("input[name='titleExtend']").val(titleExtend);	// 프로그램확장명칭(VN)
		$("#detailFrm").find("input[name='commandFg']").val(commandFg);		// 실행구분
		$("#detailFrm").find("input[name='menuFg']").val(menuFg);			// 메뉴유형
		
		// 메뉴유형 SPAN TEXT 설정---------------------
		if(menuFg  == "Y")   {
			$("#webPage").attr("disabled",true);
			$("#menuFgNm").text('<spring:message code="app.manager.menuviewlist._setmenudetail.t1" />'); <%-- 빌트인메뉴 --%>
		} else {
			$("#webPage").attr("disabled",false);
			$("#menuFgNm").text('<spring:message code="app.manager.menuviewlist._setmenudetail.t2" />'); <%-- 기본메뉴 --%>
		}

		// 숨김여부 RADIO CHECKED 설정------------------
		if(hideYn =="N"){
			$("#detailFrm").find("input[name='hideYn']")[0].checked = true;
		}else{
			$("#detailFrm").find("input[name='hideYn']")[1].checked = true;
		}

		// 사용여부 RADIO CHECKED 설정------------------
		if(useYn =="Y"){
			$("#detailFrm").find("input[name='useYn']")[0].checked = true;
		}else{
			$("#detailFrm").find("input[name='useYn']")[1].checked = true;
		}

		// 상세로그생성 RADIO CHECKED 설정------------------
		if(dtlLogYn =="Y"){
			$("#detailFrm").find("input[name='dtlLogYn']")[0].checked = true;
		}else{
			$("#detailFrm").find("input[name='dtlLogYn']")[1].checked = true;
		}

		$("#detailFrm").find("input[name='sortInLevel']").val(sortInLevel);	// 소트레벨
		$("#detailFrm").find("input[name='menuLevel']").val(menuLevel);		// 메뉴레벨
		$('#icontView').attr('class','fas fa-question');				// 기본아이콘 설정
		// 프로그램 구분이 폴더이거나 메뉴레벨이 1일 경우 실행프로그램 등록불가능
		if(commandFg == "0" || menuLevel =="1") {
			$("#detailFrm").find("input[name='webPage']").val('');			// 실행경로
			$("#detailFrm").find("input[name='extraInfo']").val('');		// 확장경로
			$("#detailFrm").find("select[name='vdiCd']").val('');			// 아이피접근정책 코드 
			$("#detailFrm").find("select[name='excTypeFg']").val('');		// DEVICE[W/M/R]
			$("#detailFrm").find("input[name='menuIcon']").val(menuIcon);	// 메뉴아이콘
			
			$('#icontView').attr('class','fas '+menuIcon);	
			$("#webPageEdit").css('display','none');
			$("#webPageEditExt").css('display','none');
			//$("#titleExtendTr").css('display','none');
		}
		else {
			$("#detailFrm").find("input[name='webPage']").val(webPage);			// 실행경로
			$("#detailFrm").find("input[name='extraInfo']").val(extraInfo);		// 확장경로
			$("#detailFrm").find("select[name='vdiCd']").val(vdiCd);			// 아이피접근정책 코드 
			$("#detailFrm").find("select[name='excTypeFg']").val(excTypeFg);	// DEVICE[W/M/R]
			$("#detailFrm").find("input[name='menuIcon']").val(menuIcon);		// 메뉴아이콘
			$('#icontView').attr('class','fas '+menuIcon);		
			
			$("#webPageEdit").css('display','');
			$("#webPageEditExt").css('display','');
			//$("#titleExtendTr").css('display','');
		}

		// 수정모드에서 실행/폴더 구분 수정불가능처리함
		$("#commandFgEdit01").css('display','none');
		$("#commandFgEdit02").css('display','');

		if(commandFg =="0"){
			$("#commandFgEdit02").text('<spring:message code="app.manager.menuviewlist._setmenudetail.t3" />'); <%-- 폴더 --%>
		}else{
			$("#commandFgEdit02").text('<spring:message code="app.manager.menuviewlist._setmenudetail.t4" />'); 				 <%-- 실행 --%>
		}

		$("#nowTypes").val(nowType);

		// 중분류의 메뉴타입 체크용 플레그 설정------------------------------------------------
		if(nowType =='2'){
			$('#parentCommandFg').val(commandFg);
		}else{
			$('#parentCommandFg').val('');
		}

		if(nowType =='1') {
			$('#setLagId').val(parentId);
			$('#setMidId').val(menuId);
		}else if(nowType =='2') {
			$('#setMidId').val(parentId);
			$('#setSmlId').val(menuId);
		}else if(nowType =='3') {
			$('#setSmlId').val(parentId);
		}
	}

	// 신규버튼이벤트 (분류별 등록화면 초기화)
	function eventMenuInit(type){

		$("#detailFrm").find("label").filter(".error").remove();
		
		// 스탭별 상위분류 선택 유무 확인 (자기자신 또는 상위메뉴 선택 확인)
		if(type =="M" && !$("#setMidId").val()){
			alert('<spring:message code="app.manager.menuviewlist._eventmenuinit.a1" />');		<%-- 등록할 대분류 메뉴를선택하세요! --%>
			return;
		}
		
		if(type =="S" && !$("#setSmlId").val()){
			alert('<spring:message code="app.manager.menuviewlist._eventmenuinit.a2" />');		<%-- 등록할 중분류 메뉴를선택하세요! --%>
			return;
		}

		if(type =='S' && ($('#parentCommandFg').val() && $('#parentCommandFg').val() != "0")){
			alert('<spring:message code="app.manager.menuviewlist._eventmenuinit.a3" />');		<%-- 선택된 중분류 실행구분이 폴더가 아닙니다. --%>
			return;
		}
		
		/*
		if(type =='S' && !$('#excTypeFg').val()){
			//alert('<spring:message code="app.manager.menuviewlist._eventmenuinit.a4" />');	 디바이스 유형을 선택하세요! --%>
			//return;
		}
		*/

		if(type == 'L'){
			$("#nowTypes").val(1);
		}else if(type == 'M'){
			$("#nowTypes").val(2);
		}else if(type=='S'){
			$("#nowTypes").val(3);
		}
		
		var frm = document.detailFrm;
		$('#menuId').val('');			//프로그램아이디
		$('#title').val('');			//메뉴명
		$('#titleSub').val('');			//메뉴추가명
		$('#titleExtend').val('');		//메뉴추가명(VN)
		$('#webPage').val('');			//링크주소
		$('#extraInfo').val('');		//링크주소확장
		$('#sortInLevel').val('');		//소트레벨
		$('#excTypeFg').val('W');		//DEVICE[W/M/R]
		$("#menuFgNm").text('<spring:message code="app.manager.menuviewlist._setmenudetail.t2" />'); <%-- 기본메뉴 --%>	//메뉴유형 'Y:빌트인', N:기본메뉴
		$('#menuFg').val('N');
		$("#webPage").attr("disabled",false);
		$("#menuIcon").val('');			// 메뉴아이콘
		$('#icontView').attr('class','fas fa-question');				// 기본아이콘 설정

		$("#excTypeFg").prop('readonly',false); 	


		frm.hideYn[0].checked 		= true;	//숨김여부 'Y:숨김',   N:표시
		frm.useYn[0].checked 		= true;	//사용여부 'Y:사용,    N:미사용
// 		frm.vdiYn[0].checked 		= true;	//사용여부 'Y:사용,    N:미사용
		frm.dtlLogYn[0].checked 	= true;	//사용여부 'Y:사용,    N:미사용

		if(type == "L"){
			$('#parentId').val('10001');	//기본 최상위 코드
			$('#menuLevel').val('1'); 		//최위레벨 1:대,2:중,3:소
			$("#webPageEdit").hide();
			$("#webPageEditExt").hide();
			//$("#titleExtendTr").hide();

			$("#commandFgEdit01").hide();
			$("#commandFgEdit02").show();
			$("#commandFgEdit02").text('<spring:message code="app.manager.menuviewlist._eventmenuinit.t2" />');	<%-- 폴더--%>
			$("#commandFg").val('0');	//실행구분 폴더로 설정
			
		}else{
			$('#menuLevel').val('2'); 		//최위레벨 1:대,2:중,3:소
			$("#webPageEdit").show();
			$("#webPageEditExt").show();
			//$("#titleExtendTr").show();
			$("#commandFgEdit01").show();
			$("#commandFgEdit02").hide();
			$("#commandFg").val('0');	//메뉴구분 폴더로 설정

			if(type == "S") {
				
				$('#menuLevel').val('3'); 		//최위레벨 1:대,2:중,3:소
				$("#commandFgEdit01").hide();
				$("#commandFgEdit02").show();
				$("#commandFgEdit02").text('<spring:message code="app.manager.menuviewlist._eventmenuinit.t3" />');	<%-- 실행--%>
				$("#commandFg").val('1');	//메뉴구분 실행으로 설정
				$("#excTypeFg").val("W");// 디바이스유형 설정 (소분류는 모바일지원불가 )
				
				$("#excTypeFg").prop('disabled',true);
				
			}else{
				changeCommandFg();
			}
		}

		// PARENT ID 설정----------------------------------

		var menuLevel = Number($("#menuLevel").val());

		if(menuLevel == '2'){
			$("#parentId").val($("#setMidId").val());
		}else if(menuLevel == '3'){
			$("#parentId").val($("#setSmlId").val());			
		}else{
			$("#parentId").val($("#setLagId").val());
		}
		
		if(!$("#parentId").val()){
			$("#parentId").val("10001");
		}

		$('#title').focus();
	}


	// 메뉴저장버튼 저장전 data 검증
	function eventMenuSave(){
		if(!$('#nowTypes').val()){
			alert('<spring:message code="app.manager.menuviewlist._eventmenusave.a1" />'); <%-- 해당메뉴 리스트의 [신규] 버튼을 클릭하세요! --%>
			return false;
		}
				
		if($('#detailFrm').valid()){
			eventMenuSaveAction();	//메뉴저장 실행
		}
	}


	// 메뉴저장 실행
	function eventMenuSaveAction(){

		var menuInfo = {};
		var hideYn = null;
		var useYn = null;
		var dtlLogYn = null;
		
		$('#detailFrm').find('input , select').map(function() {

			if(this.type =='radio'){
				if(this.name =='hideYn'){
					//사용여부 RADIO 값설정
					if(!hideYn) {
						if( $(this)[0].checked) 	hideYn = "N";
						else hideYn = "Y";
						menuInfo[this.name] = hideYn;
					}
				}
				if(this.name =='useYn'){
					//사용여부 RADIO 값설정
					if(!useYn) {
						if( $(this)[0].checked) 	useYn = "Y";
						else useYn = "N";
						menuInfo[this.name] = useYn;
					}
				}
				/*
 				if(this.name =='vdiYn'){
 					//VDI적용여부 RADIO 값설정
 					if(!vdiYn) {
 						if( $(this)[0].checked) 	vdiYn = "Y";
 						else vdiYn = "N";
 						menuInfo[this.name] = vdiYn;
 					}
 				}
				*/
				if(this.name =='dtlLogYn'){
					//사용여부 RADIO 값설정
					if(!dtlLogYn) {
						if( $(this)[0].checked) 	dtlLogYn = "Y";
						else dtlLogYn = "N";
						menuInfo[this.name] = dtlLogYn;
					}
				}
			}else{
				menuInfo[this.name] = $(this).val();
			}

		});

// 		console.log(menuInfo);
		
		// 저장 이벤트 json 실행
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/app/mgr/manager/mgrMenu_insData'/>",
		      data : JSON.stringify(menuInfo),
		      success : function(data){
		    	  
					if(data == null){
						// 처리된 데이터가 없습니다.
						msg = '<spring:message code="app.manager.menuviewlist._eventmenusaveaction.m1" />';
					}else if('success' == data.msgCd){
						// 메뉴 저장이 정상적으로 처리되었습니다.
						msg = '<spring:message code="app.manager.menuviewlist._eventmenusaveaction.m2" />';
						menuDataListRe($("#nowTypes").val());
					}else{
						// 메뉴 저장중 오류가 발생하였습니다.
						msg = '<spring:message code="app.manager.menuviewlist._eventmenusaveaction.m3" />';
					}

					alert(msg);
		      }
		});
	}


	/* 선택메뉴 삭제*/
	function eventMenuDelete(){

		var menuId = $('#menuId').val();

		if(!menuId) {
			alert('<spring:message code="app.manager.menuviewlist._eventmenudelete.a1" />'); <%-- 선택된 메뉴 정보가 없습니다. --%>
			return;
		}

		if($('#menuFg').val() =="Y") {
			alert('<spring:message code="app.manager.menuviewlist._eventmenudelete.a2" />'); <%-- 빌트인된 메뉴는 삭제할 수 없습니다. --%>
			return;
		}

		// 저장확인 메세지 --------------------------------
		var msg = '<spring:message code="app.manager.menuviewlist._eventmenudelete.m1" />'+menuId
				+'<spring:message code="app.manager.menuviewlist._eventmenudelete.m2" />'; <%-- 선택된 메뉴 ["+menuId+"] 및 하위메뉴를 삭제 하시겠습니까? --%>
				if($('#nowTypes').val() == "S") msg = '<spring:message code="app.manager.menuviewlist._eventmenudelete.m3" />'+menuId
				+'<spring:message code="app.manager.menuviewlist._eventmenudelete.m4" />'; <%-- 선택된 메뉴 ["+menuId+"] 를 삭제 하시겠습니까? --%>
		if (!confirm( msg))   return false;
		//-----------------------------------------------


		var str = {'menuId' : menuId }; // 대분류코드

		// 삭제 이벤트 json 실행---------------------------
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/app/mgr/manager/mgrMenu_delData'/>",
		      data : JSON.stringify(str),
		      success : function(data){
		    	 if(data == null) msg = '<spring:message code="app.manager.menuviewlist._eventmenudelete.m5" />'; <%-- 처리된 결과가 없습니다. --%>
		         else if('nodata' == data.msgCd){
		         	msg = '<spring:message code="app.manager.menuviewlist._eventmenudelete.m6" />'; <%-- 삭제처리된 메뉴 Data가 없습니다.\n다시처리해 주세요! --%>
		         	menuDataListRe($("#nowTypes").val());
		         }
		         else if('success' == data.msgCd)
		         {
		         	msg = '<spring:message code="app.manager.menuviewlist._eventmenudelete.m7" />'; <%-- 메뉴 삭제처리가  정상적으로 처리되었습니다. --%>
		         	menuDataListRe($("#nowTypes").val());
		         }
		         else msg = '<spring:message code="app.manager.menuviewlist._eventmenudelete.m8" />'; <%-- 메뉴 삭제처리 중 오류가 발생하였습니다. --%>

	          	alert(msg);

		      }
		});
		//-----------------------------------------------
	}


	/* 등록후 메뉴 조회 별도 실행용*/
	function menuDataListRe(nowType) {
		
		var gridNum = nowType;
		var parentId = $('#parentId').val();
		if(!parentId) parentId = "10001";

		setRemoveTable(nowType);	// 리스트 초기화
    	eventMenuInit(nowType);	// 메뉴 등록화면 초기화

    	menuDataList(gridNum, parentId);
	}


	/*초기화 하고자하는 분류 리스트 */
	function setRemoveTable(type){

		if(type =='1'){
			$("#tabList1").jqGrid('clearGridData').trigger("reloadGrid");
			$("#tabList2").jqGrid('clearGridData').trigger("reloadGrid");
			$("#tabList3").jqGrid('clearGridData').trigger("reloadGrid");

		}else if(type =='2'){
			$("#tabList2").jqGrid('clearGridData').trigger("reloadGrid");
			$("#tabList3").jqGrid('clearGridData').trigger("reloadGrid");

		}else if(type =='3'){
			$("#tabList3").jqGrid('clearGridData').trigger("reloadGrid");
		}
	}


	/*실행구분 changed*/
	function changeCommandFg() {

		$("#detailFrm").find("label").filter(".error").remove();

		if($("#commandFg").val() == "0" || $("#menuLevel").val() =="1") {

			$("#webPage").val('');		// 실행경로
			$("#extraInfo").val('');	// 확장경로
			$("#menuIcon").val('');		// 메뉴아이콘
			$('#icontView').attr('class','fas fa-question');				// 기본아이콘 설정
			$("#webPageEdit").css('display','none');
			$("#webPageEditExt").css('display','none');
			
		}
		else {
			$("#webPageEdit").css('display','');
			$("#webPageEditExt").css('display','');
			$("#excTypeFg").prop('disabled',false);
		}
	}

	
</script>
	<spring:message code="common.all" var="commonall" />	 <%--전체 --%>
	<spring:message code="common.choice" var="commonchoice"/> <%--선택 --%>
	<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
			<table style="width: 100%;">
				<tr>
					<td>
		             	<!-- 대분류 서브타이틀 및 이벤트 버튼 start  ----------------->
		                <div class="tit_area" >
		                	<h2><spring:message code="app.manager.menuviewlist.title.menu1st" /></h2>	<%-- 대분류 List --%>
							<div>
								<html:button id="btnLagInit" auth="insert"   /> <%--신규 --%> 
							</div>
						</div>
						<!-- 대분류 서브타이틀 및 이벤트 버튼 end     ---------------->

						<!-- centent List -------------------------->
                        <div id="grid1container" class="gridcontainer">
                            <table id="tabList1" ><tr><td></td></tr></table>
                        </div>

                        <div id="pageList1"></div>
                        <!-- centent List -------------------------->

					</td>
					<td style="width: 5px;"></td>
					<td>
						<!-- 중분류 서브타이틀 및 이벤트 버튼 start  ----------------->
		                <div class="tit_area">
		                	<h2><spring:message code="app.manager.menuviewlist.title.menu2nd" /></h2> <%--중분류 List --%>
							<div>
								<html:button id="btnMidInit" auth="insert" /> <%--신규 --%> 
							</div>
						</div>
						<!-- 서브타이틀 및 이벤트 버튼 end     ---------------->

                        <!-- centent List -------------------------->
                        <div id="grid2container" class="gridcontainer">
                            <table id="tabList2" ><tr><td></td></tr></table>
                        </div>

                        <div id="pageList2"></div>
                        <!-- centent List -------------------------->

					</td>
					<td style="width: 5px;"></td>
					<td>
					<!-- 소분류 서브타이틀 및 이벤트 버튼 start  ----------------->
		                <div class="tit_area">
		                	<h2><spring:message code="app.manager.menuviewlist.title.menu3rd" /></h2>	<%-- 소분류 List --%>
							<div>
								<html:button id="btnSmaInit" auth="insert" /> <%--신규 --%> 
							</div>
						</div>
						<!-- 서브타이틀 및 이벤트 버튼 end -->

						<!-- centent List -------------------------->
                        <div id="grid3container" class="gridcontainer">
                            <table id="tabList3" ><tr><td></td></tr></table>
                        </div>

                        <div id="pageList3"></div>
                        <!-- centent List -->

					</td>
				</tr>
			</table>

    		<!-- 등록화면 영역 end -->
    		<form id="detailFrm" name="detailFrm">
    		<div class="tit_area">
				<h2><spring:message code="app.manager.menuviewlist.title.menusave" /></h2>	<%-- 메뉴 등록/수정 --%>
				<div>
					<html:button id="btnSave" 	auth="save"    />
					<html:button id="btnDelete" auth="delete"  />
				</div>
			</div>
				<input type="hidden"   	name="setLagId"  		id="setLagId">
				<input type="hidden"   	name="setMidId"  		id="setMidId">
				<input type="hidden"   	name="setSmlId"  		id="setSmlId">
				<input type="hidden" 	name="menuLevel"  		id="menuLevel">
				<input type="hidden" 	name="nowTypes"   		id="nowTypes">
				<input type="hidden" 	name="parentCommandFg"	id="parentCommandFg">
				<table class="type1">
					<colgroup>
						<col width="150"/>
						<col width="*"/>
						<col width="150"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th><spring:message code="app.manager.menuviewlist.label.parentid" /></th> <%-- 상위메뉴 아이디 --%>
							<td><input type="text" id="parentId" name="parentId"  value="10001" style="width: 100px; border:0px;" readonly /></td>
							<th><spring:message code="app.manager.menuviewlist.label.menuid" /></th> <%-- 메뉴 아이디 --%>
							<td><input type="text" id="menuId" name="menuId"     style="width: 100px; border:0px;" readonly /></td>
						</tr>
						<tr>
							<th>* <spring:message code="app.manager.menuviewlist.label.menunm" /></th> <%-- 메 뉴 명 칭 --%>
							<td colspan="3"><input type="text" id="title" name="title"  			style="width: 28%;" placeholder="KO">
							  / <input type="text" id="titleSub" name="titleSub"  		style="width: 28%;" placeholder="EN">
							  ( <input type="text" id="titleExtend" name="titleExtend"  style="width: 28%;" placeholder="Extend"> )
							 </td>
							
						</tr>
						<tr id=menuIconEdit>				
							<th><spring:message code="app.manager.menuviewlist.label.menuIcon" /></th> <%-- 메 뉴 아이콘--%>
							<td>
								<span style="font-size:1.0pm;"><i id="icontView"  class="fas fa-question"></i></span>
								<input type="text" id="menuIcon" name="menuIcon" style="width: 200px;">
								<html:button id="findMenuIcon"  name="findMenuIcon" auth="select"  msgKey="button.find"/> 
							</td>
							<th>* <spring:message code="app.manager.menuviewlist.label.commndfg" /></th> <%-- 실 행 구 분 --%>
							<td>
								<span id="commandFgEdit01">
								<html:codeTag comType="SELECT" objName="commandFg"  width="100px;" objId="commandFg" event="onchange='changeCommandFg();'" parentID="9010" formName="menuInfo"></html:codeTag>
								</span>
								<span id="commandFgEdit02" style="display:none;"><spring:message code="app.manager.menuviewlist._setmenudetail.t3" /></span> <%-- 폴 더 --%>
							</td>
						</tr>
						<tr id=webPageEdit>
							<th>* <spring:message code="app.manager.menuviewlist.label.webpage" /></th> <%-- 실행프로그램경로 --%>
							<td><input type="text" id="webPage" name="webPage"  style="width: 90%;" placeholder="<spring:message code='app.manager.menuviewlist._eventmenusave.a3'/>">
							<img src="/resources/images/pearl/common/icons-note.gif" style="cursor: pointer;padding:3px;" onClick="$('#webPage').val('/web/common/working');" alt='working' title='working...'>
							</td>
							<th><spring:message code="app.manager.menuviewlist.label.extrainfo" /></th> <%-- 메뉴확장명칭 --%>
							<td><input type="text" id="extraInfo" name="extraInfo"  style="width: 90%;"></td>
						</tr>
						<tr id=webPageEditExt>
							<th><spring:message code="app.manager.menuviewlist.label.devicetype" /></th>	<%-- 디바이스유형 --%>
							<td>
								<html:codeTag comType="SELECT" objId="excTypeFg" objName="excTypeFg" parentID="9014" />
							</td>
							<th><spring:message code="app.manager.menuviewlist.label.interception" /> </th>  <%-- 접근설정 --%>
							<td>
								<%--상세로그 생성 여부 --%>
								<spring:message code="app.manager.menuviewlist.label.dtllogyn" /> : <html:codeTag comType="RADIO" objId="dtlLogYn" objName="dtlLogYn" parentID="9002" selectParam="0"  />&nbsp;
								<spring:message code="app.manager.menuviewlist.label.vdi" />:  <html:codeTag comType="SELECT" objName="vdiCd" objId="vdiCd" parentID="9050"  defName="선택" defValue="" /><%--아이피접근제어--%>
								
							</td>
						</tr>
						<tr>
							<th>* <spring:message code="app.manager.menuviewlist.label.sort" /></th> <%-- SORT 순서 --%>
							<td><input type="text" id="sortInLevel" name="sortInLevel" style="width:50px; ime-mode:disabled;" ></td>
							<th><spring:message code="app.manager.menuviewlist.label.menufg" /></th> <%-- 메 뉴 유 형 --%>
							<td>
								<span id="menuFgNm"><spring:message code="app.manager.menuviewlist._setmenudetail.t2" /></span> <%-- 기본메뉴 --%>
								<input type="hidden" id="menuFg" name="menuFg" value="N">
							</td>
						</tr>
						<tr>
							<th><spring:message code="app.manager.menuviewlist.label.hideyn" /></th> <%-- 숨 김 여 부 --%>
							<td>
								<html:codeTag comType="RADIO" objId="hideYn" objName="hideYn" parentID="9004" selectParam="0"  />
							</td>
							<th><spring:message code="app.manager.menuviewlist.label.useyn" /></th> <%-- 사 용 여 부 --%>
							<td>
								<html:codeTag comType="RADIO" objId="useYn" objName="useYn" parentID="9002" selectParam="0"  />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		    <!-- 등록화면 영역 end -->
    	</div>
    	
   
<!-- 아이콘찾기 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/common/popupFind/comIconFind.jsp" />
<!-- 아이콘찾기 레이어 영역 end  -->