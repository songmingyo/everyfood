<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){
		initLogisticsWhGrid();


		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnSave').unbind().click(null,	        saveEvent); // 저장
		$('#btnNew').unbind().click(null,	        newEvent); // 저장
		
		
		//Resized to new width as per window -------------------------------*/
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
  

		// 조회조건 입력필드 enter key이벤트 --------------
		$('#searchWhNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : searchEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initLogisticsWhGrid() {
		$("#container1List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '창고코드'
            			, '창고명'
            			, '우편번호'
            			, '주소'
            			, '주소상세'
            			, '사용유무'
            			, '등록자'
            			, '등록ID'
            			, '등록시간'
            			, '수정자'
            			, '수정ID'
            			, '수정시간'
            ]
			,colModel:[
		                 {name:'whCd'			, index:'whCd'		, sortable:true, width:50 , align:"center"}
		            	,{name:'whNm'			, index:'whNm'		, sortable:true, width:100, align:"left"}
		            	,{name:'zipCd'			, index:'zipCd'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'zipAddr'		, index:'zipAddr'	, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'dtlAddr'		, index:'dtlAddr'	, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'useYn'			, index:'useYn'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'regNm'			, index:'regNm'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'regId'			, index:'regId'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'regDt'			, index:'regDt'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'modNm'			, index:'modNm'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'modId'			, index:'modId'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'modDt'			, index:'modDt'		, hidden:true, sortable:true, width:50 , align:"center"}
		        
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
	                
	                $('#mainForm')[0].reset();
	                
	            }
	        	$(window).resize();
	        	
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트
            	if (id == 'blankRow') {
                    return;
                }
                
                var rowdata = $("#container1List").getRowData(id);
                
                fncSetDtlInfo(rowdata);
                
           }

           ,rowNum:100
           ,loadui : "disable"
           ,gridview:    true
           ,pager: '#pageList'
           ,sortname: 'whCd'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,loadonce:true
           ,autowidth:true
		});
	}



	//데이터 셋팅
	function fncSetDtlInfo(data){
		//comp:업체정보, rslt:평가결과
		$.form = $("#mainForm");
			
		if(data != null){

			//조회 데이터 셋팅
			$.form.find("span, input").each(function(){
				dataNm = $(this).attr("name");
				tagNm = this.tagName.toUpperCase();

				settingData = data[dataNm];

				if(tagNm == "SPAN"){
					$(this).text(settingData);
				}else{
					$(this).val(settingData);
				}
			});
			//사용여부 RADIO 값설정
			if(data.useYn == "Y") {
				$("#mainForm").find("input[name='useYn']")[0].checked = true;
			}
			else{
				$("#mainForm").find("input[name='useYn']")[1].checked = true;
			}
					
			
		}
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
	}

	
	/* 조회 */
	function searchEvent(event){

		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#container1List").jqGrid('clearGridData');		
		
		$("#container1List").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/logisticsWarehouse_selList.json'/>",
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
	

	/* 저장 */
	function saveEvent(event){

		if(!confirm("저장하시겠습니까?")){return;}
		
		var saveInfo = {};
		
		var useYn = null;
		
		$('#mainForm').find('input, select').map(function() {
			if(this.type =='radio'){
				if(this.name =='useYn'){
					//사용여부 RADIO 값설정
					if(!useYn) {
						if( $(this)[0].checked) useYn = "Y";
						else useYn = "N";
						
						saveInfo[this.name] = useYn;
					}
				}
			}else{
				saveInfo[this.name] = $(this).val();
			}
		});

		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/bm/baseinfo/logisticsWarehouse_insInfo.json'/>",

		    data : JSON.stringify(saveInfo),
	 	    success : function(data){
	   	 	
		 	    if(data == null) msg = '<spring:message code="app.manager.memberviewlist._eventsave.m1" />'; <%-- 처리된 데이터가 없습니다. --%>
	     	    else if('success' == data.msgCd)
	      	    {
	      	    	msg = '<spring:message code="app.manager.memberviewlist._eventsave.m2" />'; <%-- 저장이 정상적으로 처리되었습니다. --%>
	      	 	   	
	   				searchEvent();			//  재조회
	      	    }
	      	    else msg = '<spring:message code="app.manager.memberviewlist._eventsave.m4" />'; <%-- 저장중 오류가 발생하였습니다. --%>
	
	      	  	alert(msg);
      	    }
		});

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
	<legend>물류창고관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>물류창고 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">창고명</label></th>
				<td><input type="text" id="searchWhNm" name="searchWhNm" style="width:20%;"></td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

 <UL style="width: 100%;">
 	<li style="float: left; width: 40%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>물류창고관리</h2>	
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="container1List" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 	
 	<li style="float: left; width: 60%;">
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">물류창고정보 등록/수정</h2>
			<div class="btn_l">
				<html:button id="btnSearch" 	name="btnSearch"   auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnNew"   		name="btnNew"      auth="insert" /> 		<%-- 신규  --%>
				<html:button id="btnSave"   	name="btnSave"     auth="save"   />		<%-- 저장 --%>
			</div>
		</div>
		<form name="mainForm" id="mainForm"  autocomplete="off" >   		
		<div id="logisticsWareData" class="datatableType1">
		<table class="type1">
			<colgroup>
				<col width="150"/>
				<col width="*"/>
			</colgroup>
			<tbody>
			<tr>
				<th>창고코드</th>
				<td>
					<input type="text" name="whCd" id="whCd" readonly>
				</td>
				<th>사용여부</th>
				<td><html:codeTag comType="RADIO" objId="useYn" objName="useYn" parentID="9002" selectParam="0"  /></td>
			</tr>
			<tr>
				<th>창고명</th>
				<td colspan="3">
					<input type="text"   id="whNm"   name="whNm" >
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td colspan=3>
					<input type="text"   id="zipCd"   name="zipCd" style="width:30%;">
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td colspan="3">
					<input type="text"   id="zipAddr"   name="zipAddr" >
				</td>
			</tr>
			<tr>
				<th>주소상세</th>
				<td colspan="3">
					<input type="text"   id="dtlAddr"   name="dtlAddr" >
				</td>
			</tr>
			<tr>
				<th>등록자</th>
				<td>
					<input type="text"   id="regNm"    name="regNm" readonly>
				</td>
				<th>등록일시</th>
				<td><input type="text"   id="regDt"    name="regDt" readonly></td>
			</tr>
			<tr>
				<th>수정자</th>
				<td><input type="text"   id="modNm"    name="modNm" readonly></td>
				<th>수정일시</th>
				<td><input type="text"   id="modDt"    name="modDt" readonly></td>
			</tr>
			</tbody>
		</table>
		</div>
		</form>
	</div>	
 	</li>
 </UL>            



<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>