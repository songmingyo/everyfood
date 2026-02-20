<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		
		initDeliVehicleGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    	searchEvent); 	// 검색
		$('#btnMasterSave').unbind().click(null,	    saveEvent); 	// 저장
		$('#btnMasterDelete').unbind().click(null,	    deleteevent); 	// 삭재
		$('#btnMasterInit').unbind().click(null,	    doClearForms); 	// 신규  
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#container1List').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#container1List').setGridHeight(height-160);	//GRID  LIST
				    $('#deliVehicleData').height(height-105);  			//TALBE DATA
			    }
		        else if(height < 300){
			        $('#container1List').setGridHeight(height-220);	//GRID  LIST
			        $('#deliVehicleData').height(height-165);  			//TALBE DATA
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#carCd_search, #carNm_search, #carNum_search').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : searchEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		/*-----------------------------------------------------------------*/


		/* Form validate --------------------------------------------------*/
        $('#masterForm').validate({
	        rules: {
	        	 carNm   : { required: true, maxlength: 50}
	          	,carNum  : { required: true, maxlength: 10}
	          	,drvSnm  : { required: true, maxlength: 10}
	        }
			,messages: {
				 carNm   : {required: "<div class='validate'><i class='fa fa-info-circle'></i>차량명을 입력하세요!<div>"}  
				,carNum  : {required: "<div class='validate'><i class='fa fa-info-circle'></i>차량번호를 입력하세요!<div>"}  
				,drvSnm  : {required: "<div class='validate'><i class='fa fa-info-circle'></i>기사명을 입력하세요!<div>"}  
			}
	    });
		
		
	});


	/* 마스터 데이터  그리드 초기화 */
	function initDeliVehicleGrid() {
		$("#container1List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '코드'
            			, '차량명'
            			, '차량번호'
            			, '기사명'
            			, '연락처'
            ]
			,colModel:[
		                 {name:'carCd'			, index:'carCd'		, sortable:true, width:50 , align:"center"}
		            	,{name:'carNm'			, index:'carNm'		, sortable:true, width:100, align:"left"}
		            	,{name:'carNum'			, index:'carNum'	, sortable:true, width:50 , align:"center"}
		                ,{name:'drvSnm'			, index:'drvSnm'	, sortable:true, width:50 , align:"center"}
		                ,{name:'mtelNo'			, index:'mtelNo'	, sortable:true, width:50 , align:"center"}
		        
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	$(window).resize();
	        }
			,loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

                detailEvent(rowdata);
           }

           ,rowNum:30
           ,loadui : "disable"
           ,gridview:    true
           ,pager: '#pageList'
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


	/* grid1container / container1List Data 조회 */
	function searchEvent(event){
		doClearForms(); // 입력 From  초기화 
		
		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#container1List").jqGrid('clearGridData');		
		
		$("#container1List").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/empDeliveryVehicle_selList.json'/>",
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
				root:  "resultList",	//조회결과 데이터
				page: "page",			//현재 페이지	
				total: "pagePerCount",	//총 페이지 수
				records: "totalCount",	// 전체 조회된 데이터 총갯수 
				repeatitems: false
			}
		}).trigger("reloadGrid");

	}

	/* Grid onSelectRow Event */
	function detailEvent(searchInfo){

		doClearForms(); // 입력 From  초기화 
		if(!searchInfo || !searchInfo.carCd) return;

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/bm/baseinfo/empDeliveryVehicle_selDetail.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {

				if(data != null) {

					$("#deliVehicleData").find("input[id='carCd']").val(data.carCd);
					$("#deliVehicleData").find("input[name='useYn']:radio[value='"+data.useYn+"']").prop("checked", true);
					$("#deliVehicleData").find("input[id='carNm']").val(data.carNm);
					$("#deliVehicleData").find("input[id='carNum']").val(data.carNum);
					$("#deliVehicleData").find("input[id='drvSnm']").val(data.drvSnm);
					$("#deliVehicleData").find("input[id='mtelNo']").val(data.mtelNo);
					$("#deliVehicleData").find("input[id='carType']").val(data.carType);

					$("#deliVehicleData").find("input[id='startDt']").val(data.startDtFmt);
					$("#deliVehicleData").find("input[id='endDt']").val(data.endDtFmt);

					$("#deliVehicleData").find("input[id='regUserInfo']").val(data.regUserInfo);
					$("#deliVehicleData").find("input[id='regDt']").val(data.regDt);
					$("#deliVehicleData").find("input[id='modUserInfo']").val(data.modUserInfo);
					$("#deliVehicleData").find("input[id='modDt']").val(data.modDt);

				}
			}
		});
	}

	

	/*저장 버튼 이벤트 */
	function saveEvent(){

		if(!$('#masterForm').valid()) return;

		var dataInfo = {};
		$('#masterForm').find('input , select').map(function() {
			 dataInfo[this.name] = $(this).val();
		});

		dataInfo["useYn"] 	= $("#deliVehicleData").find("input[name='useYn']:checked").val();  //사용여부 (Y/N)

		var regex = /[^0-9]/g;
		dataInfo["startDt"] = dataInfo.startDt.replace(regex, 	""); 	//운영시작일 - 숫자만 추출 
		dataInfo["endDt"] 	= dataInfo.endDt.replace(regex, 	""); 	//운영종료일 - 숫자만 추출 
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/bm/baseinfo/empDeliveryVehicle_insData.json" />',
		      data : JSON.stringify(dataInfo),
		      success : function(data){            

		    	if(data == null) msg = '<spring:message code="message.error.process" />'; <%--"처리중 오류가 발생했습니다.";--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="message.success.process" />'; 	<%--"정상적으로 처리되었습니다.";--%>
	            	dataInfo['carCd'] = data.rtnValue01;
	            	searchEvent(); 			// 리스트 데이터 제조회 
	            	detailEvent(dataInfo);	// 수정데이터 상세 제조회 
	            }
	            else if('duple' == data.msgCd) {
	            	msg ='<spring:message code="message.error.duplication" />'; <%-- "중복된 데이터 입니다.";--%>
	            }
	            else msg = '<spring:message code="message.error.process" />';	<%--"처리중 오류가 발생 하였습니다.";--%>
	          	alert(msg);
		      }
		});
	}
	


	function deleteevent(){

		var dataInfo = {};
		var carCd =  $("#deliVehicleData").find("input[name='carCd']").val();  

		if(!carCd) {
			alert('선택된 정보가 없습니다.');
			return;
		}


		// 저장확인 메세지 --------------------------------
		if (!confirm( '<spring:message code="message.confirm.delete" />')){<%--" 삭제 하시겠습니까?"--%>
        	return false;
		}
		//
		
		dataInfo["carCd"] 	= carCd;

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/bm/baseinfo/empDeliveryVehicle_delData.json" />',
		      data : JSON.stringify(dataInfo),
		      success : function(data){            

		    	if(data == null) msg = '<spring:message code="message.error.process" />'; <%--"처리중 오류가 발생했습니다.";--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="message.success.process" />'; 	<%--"정상적으로 처리되었습니다.";--%>
	            	searchEvent(); 			// 리스트 데이터 제조회 
	            	doClearForms();			// 입력화면 초기화 
	            }
	            else if('duple' == data.msgCd) {
	            	msg ='<spring:message code="message.error.duplication" />'; <%-- "중복된 데이터 입니다.";--%>
	            }
	            else msg = '<spring:message code="message.error.process" />';	<%--"처리중 오류가 발생 하였습니다.";--%>
	          	alert(msg);
		      }
		});
		
	}

	/*입력필드 초기화(신규,조회 Event)*/
	function doClearForms(){
		$('#deliVehicleData').find('input, textarea, select').each(function() {
			if(this.type != "button" && this.type != "radio"){
				$(this).val("");
			}
		});

		$("#deliVehicleData").find("input[name='useYn']:radio[value='Y']").prop("checked", true);
	
	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="nameall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<fieldset>
	<legend>배송차량관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>배송차량관리 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">처량코드</label></th>
				<td><input type="text" id="carCd_search" name="carCd"></td>
				<th><label for="sele2">차량명</label></th>
				<td><input type="text" id="carNm_search" name="carNm"></td>
				<th><label for="sele2">차량번호</label></th>	<%-- 그룹명 --%>
				<td><input type="text" id="carNum_search" name="carNum"></td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 50%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>배송차량관리</h2>	
			<div>
				
			</div>
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="container1List" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</LI>
 
 	<LI style="float: left; width: 50%;">
 		<div  style="padding-left: 5px; " id="masterFormData" autocomplete="off">
 		<form name="masterForm" id="masterForm"  >
	   		<div class="tit_area">
				<h2 class="subhead">배송차량정보 등록/수정</h2>
				<div>
					<html:button id="btnSearch" 		auth="select" /> 	<%-- 조회 --%>
					<html:button id="btnMasterInit" 	auth="insert" /> 	<%-- 신규 --%>
					<html:button id="btnMasterSave"     auth="save"   /> 	<%-- 저장 --%>
					<html:button id="btnMasterDelete" 	auth="delete" />  	<%-- 삭제 --%>
				</div>
			</div>
			<div id="deliVehicleData" class="datatableType1">
				<table class="type1">
					<colgroup>
						<col width="150"/>
						<col width="*"/>
						<col width="100"/>
						<col width="*"/>
					</colgroup>
					<tbody>
					<tr>
						<th>코드</th>
						<td><input type="text"   id="carCd"    name="carCd" maxlength="5" readonly="readonly"></td>
						<th>운영여부</th>
						<td><html:codeTag comType="RADIO" objId="useYn" objName="useYn" parentID="9002" selectParam="0"  /></td>
					</tr>
					<tr>
						<th>* 차량명</th>
						<td colspan="3"><input type="text"   id="carNm"   name="carNm" ></td>
					</tr>
					<tr>
						<th>* 차량번호</th>
						<td colspan="3"><input type="text"   id="carNum"   name="carNum" ></td>
					</tr>
					<tr>
						<th>* 기사명</th>
						<td colspan="3"><input type="text"   id="drvSnm"   name="drvSnm" ></td>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<td  colspan="3"><input type="text"   id="mtelNo"   name="mtelNo" oninput ="fncSetDefInputHpNo(this);" onkeyup="chkByte(this,14);"></td>
					</tr>
					<tr>
						<th>차량유형</th>
						<td colspan="3"><input type="text"   id="carType"   name="carType" ></td>
					</tr>
					<tr>
						<th>운영 시작일자 </th>
						<td colspan="3"><input type="text"   id="startDt"    name="startDt" readonly="readonly"></td>
					</tr>
					<tr>
						<th>운영 종료일자 </th>
						<td colspan="3"><input type="text"   id="endDt"    name="endDt" readonly="readonly"></td>
					</tr>
					<tr>
						<th>등록자</th>
						<td><input type="text"   id="regUserInfo"    name="regUserInfo" readonly="readonly"></td>
						<th>등록일시</th>
						<td><input type="text"   id="regDt"    name="regDt" readonly="readonly"></td>
					</tr>
					<tr>
						<th>수정자</th>
						<td><input type="text"   id="modUserInfo"    name="modUserInfo" readonly="readonly"></td>
						<th>수정일시</th>
						<td><input type="text"   id="modDt"    name="modDt" readonly="readonly"></td>
					</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>	
 	</LI>
 </UL>
<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>