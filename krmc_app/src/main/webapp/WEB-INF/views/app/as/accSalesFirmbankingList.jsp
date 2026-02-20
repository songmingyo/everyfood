<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		
		initAccSalesFirmbankingListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnSave').unbind().click(null,	    saveEvent);	// 저장 
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-120);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-180);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/

		/*-----------------------------------------------------------------*/

		
	});
	

	/* 마스터 데이터  그리드 초기화 */
	function initAccSalesFirmbankingListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '입금인명'
    			        , '입금일자'
				        , '입금시간'
            			, '가상계좌번호'
            			, '입금금액'
            			, '적용여부'
            			, '적용여부'

           ]
			,colModel:[
		                 {name:'custName'		    	, index:'custName'		, sortable:true, width:100, align:"center"}
		            	,{name:'dealDate'	         	, index:'dealDate'		, sortable:true, width:180, align:"center"}
		            	,{name:'dealTime'				, index:'dealTime'		, sortable:true, width:120, align:"center" }
		            	,{name:'vrAcctNo'		        , index:'vrAcctNo'		, sortable:true, width:120, align:"center" }
		            	,{name:'totalAmt'		    	, index:'totalAmt'		, sortable:true, width:120, align:"right", formatter:'integer'}
		            	,{name:'procFlagNm'		        , index:'procFlagNm'	, sortable:true, width:120, align:"center" }
		            	,{name:'procFlag'		        , index:'procFlag'		, sortable:true, width:120, align:"center", hidden:true }
		            	
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
				alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            
			,rowNum : -1
            ,loadui : "disable"
            ,gridview:    true
            ,sortorder: 'asc'
            ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
            ,viewrecords: true
            ,rownumbers:true
            //,shrinkToFit : false
// 	        ,footerrow: true
            ,autowidth:true
            ,loadonce:true
            ,scroll:true
		});
	}

	/* 조회 */
	function searchEvent(event){
  	
	    var searchInfo = {};
  	      
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/account/accSalesFirmbankingList_selList.json'/>",
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
	
	/*저장 버튼 이벤트 */
	function saveEvent(){

		if(!confirm("저장하시겠습니까?")){return;}
		
		var rowId = $("#tabList").getGridParam("selrow");

		if (rowId) {
		    // 2. 해당 ID의 행 데이터를 가져옴
		    
		    var rowData = $("#tabList").getRowData(rowId);
		    
		    if(rowData.procFlag == "Y"){
		    	alert("이미 반영된 데이터 입니다.");
		    	return;
		    }
		    
			var saveData = {'accSalesFirmbankingRegList'  : $("#tabList").getRowData()
					};
			
		} else {
		    alert("행을 먼저 선택해 주세요.");
		    return;
		}

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/as/account/accSalesFirmbankingReg_insInfo.json" />',
		      data : JSON.stringify(saveData),
		      success : function(data){            

		    	  if(data.msgCd =="0") {
						alert("신규 : "+ data.rtnValue01+" 건이 처리 정상 되었습니다.");
						
						searchEvent();			//  재조회
				   	}else{
						alert("매출처의 가상계좌를 확인하세요.\n"+data.message)
						searchEvent();
					}
		      }
		});
	}

	/* 엑셀  */
// 	function excelEvent(){
// 		$('#searchForm').attr("action", "<c:url value='/app/as/account/accSalesFirmbankingList_selExcelDown'/>").submit();
// 	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post">
<sec:csrfInput/>
	<fieldset>
	<legend>펌뱅킹 입금 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>검색조건</caption>
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
				<th><label for="sele2">입금일자</label></th>
					<td>
						<input type="text" class="dt" id="startDt" name="startDt" style="width: 30% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt" style="width: 30% !important;" maxlength="8" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"  />
					</td>
			</tr>
			
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 분류 서브타이틀 및 이벤트 버튼 start  --------------------- -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>펌뱅킹입금현황</h2>
        	<div>
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" /> 	<%-- 조회 --%>
        		<html:button id="btnSave" 		name="btnSave"		auth="save" value="재처리"/> 	   	<%-- 저장 --%>
        	</div>
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</LI>
 
<!-- CONTENT- BODY end ----------------------------------- -->

</div>


</body>
</html>