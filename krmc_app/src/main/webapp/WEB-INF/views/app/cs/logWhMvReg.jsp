<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchMvDt").datepicker();
		
		$("#searchMvDt").datepicker('setDate','c');
		
		initWhMvGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnSave').unbind().click(null,	        saveEvent);   // 저장
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		
		$("#btnPrdtCd").unbind().click(function(){
			findPrdtMst();
		});		<%--상품 팝업 찾아가기--%>
		
		
		//Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    } 
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-120);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		// 조회조건 입력필드 enter key이벤트 --------------
		$('#searchPrdtNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findPrdtMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#mvQty').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : prdtMvAdd(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initWhMvGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[
				    '출고창고'
				    , '입고창고'
				    , '품목코드'
				    , '품명'
           			, '규격'
           			, '단위'
           			, '이고수량'
           			, '소비기한'
           			, '이고사유'
           			
           			, '출고창고'
           			, '입고창고'
           			, 'Row상태'
           			, '원 수량'
           ],
			colModel:[
						{name:'outWhNm'		, index:'outWhNm'		, sortable:true, width:80 , align:"center"}
						,{name:'inWhNm'		, index:'inWhNm'		, sortable:true, width:80 , align:"center"}
						,{name:'prdtCd'		, index:'prdtCd'		, sortable:true, width:80 , align:"center"}
		            	,{name:'prdtNm'		, index:'prdtNm'		, sortable:true, width:150 , align:"left"}
		            	,{name:'prdtStd'	, index:'prdtStd'		, sortable:true, width:80 , align:"center"}
		            	,{name:'ordUnitNm'	, index:'ordUnitNm'		, sortable:true, width:50 , align:"center"}
		            	,{name:'mvQty'		, index:'mvQty'			, sortable:true, width:50 , align:"right", formatter:fmtSetGridInputAmt, unformat: unfmtGetGridInputAmt} 
		            	,{name:'termVal'	, index:'termVal'		, sortable:true, width:120 , align:"center", formatter:fmtSetGridDatePicker, unformat: unfmtGetGridDatePicker} 
		            	,{name:'remarks'	, index:'remarks'		, sortable:true, width:200 , align:"left", formatter:function(cellvalue, options, rowObject){return fmtSetGridInputLen(cellvalue, options, rowObject, "100");}	,unformat: unfmtGetGridInput}
		            	
		            	,{name:'outWhCd'	, index:'outWhCd'		, sortable:true, hidden:true,  width:80 , align:"center"}
						,{name:'inWhCd'		, index:'inWhCd'		, sortable:true, hidden:true,  width:80 , align:"center"}
		            	,{name:'gridFlag'	, index:'gridFlag'		, sortable:true, hidden:true,  width:50 , align:"center"}
		            	,{name:'mvQtyOrg'	, index:'mvQtyOrg'		, sortable:true, hidden:true,  width:50 , align:"center"}
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            $(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }else{
	            	var allRows = $(this).jqGrid('getDataIDs');
		        	   
		        	for(var i = 0; i < allRows.length; i++){
						var cl = allRows[i];
						var rowData = $(this).jqGrid('getRowData', cl);
							
						$(this).find("#"+cl).find("input[name='termVal']").datepicker();
		        	}
	            }
	            
	        	$(window).resize();
	        },
	        
// 			afterEditCell: function(rowid, name, val, iRow, iCol){
// 				$("#" + rowid + "_" + cellname).blur(function(){
// 			        $("#tabList").jqGrid("saveCell",iRow,iCol);
// 			    });
// 			},
			
// 			afterSaveCell: function(rowid,name,val,iRow,iCol) {
				
// 	            var data = $("#tabList").getRowData(rowid);
	            
// 	            var gridFlag = data.gridFlag;
	            
// 	            if(gridFlag == "N" ) {
// 	            	$("#tabList").jqGrid('setCell', rowid, 'gridFlag' , 'U');
// 	            } else{
// 	            	$("#tabList").jqGrid('setCell', rowid, 'gridFlag' , 'I');
// 	            }
	            
// 	            $("#tabList").saveRow(rowid, false);
	            
// 	        },
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },

            rowNum:1000,
            pager: '#pageList',
			loadui : "disable",													// 이거 안 써주니 로딩 창 같은게 뜸
			gridview: true,														// 그리드 속도
			viewrecords: true,													// 하단에 1/1 또는 데이터가없습니다 추가
			rownumbers:true,													// rowNumber 표시여부
			autowidth:true,		
			loadonce : true,
			//cellEdit : true,
			sortorder : "desc"
		});
	}
	
	
	/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonPrdtMstFindLayer('','',$("#searchPrdtNm").val(), '', setPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
		if (data != null){
			$("#searchPrdtCd").val(data.prdtCd);
			$("#searchPrdtNm").val(data.prdtNm);
			
			$('#mvQty').focus();
		}
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
		}
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
		$("#mvDt").datepicker('setDate','c');
		
		$("#tabList").jqGrid('clearGridData');
	}

	
	/* 조회 */
	function searchEvent(event){
		var searchInfo = {};
		
		$('#searchForm').find('input').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});
		
		$('#searchForm').find('select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/cs/logistics/logWhMvReg_selList.json'/>",
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
		
		$("#tabList").editCell(0, 0, false);

		if(!confirm("저장하시겠습니까?")){return;}

		var saveData = {'logWhMvRegList'  : $("#tabList").getRowData()
					    , 'mvDt' : $("#searchMvDt").val().replaceAll('-','')
		               };
		
		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/cs/logistics/logWhMvReg_insList.json'/>",
 
		    data : JSON.stringify(saveData),
	 	    success : function(data){
	   	 	
	 	    	if(data.msgCd =="0") {
// 					alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
					alert(data.rtnValue01+"건이 저장 되었습니다.");
					
// 					$('#ordYn').val('10').trigger('change');
	      	    	
	   				searchEvent();			//  재조회
			   	}else{
					alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
				}
      	    }
		});

	}
	
	
	/* 수량에서 엔터 눌렀을 경우 해당 품목의 데이터를 가지고 와서 그리드에 보여줌 */
	function prdtMvAdd(event){
		if(!$('#searchPrdtNm').val()) {
			alert('품목을 입력하세요.');
			$('#searchPrdtNm').focus();
			return false;
		}
		if(!$('#mvQty').val()) {
			alert('이동 수량을 입력하세요.');
			$('#mvQty').focus();
			return false;
		}

		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url:"<c:url value='/app/cs/logistics/logWhMvReg_selPrdtAddList.json'/>",
			data: JSON.stringify(searchInfo),
			success : function(data){
				
				//blankRow 제거
				$("#tabList").jqGrid('delRowData', 'blankRow');
				
				rowId = $("#tabList").getGridParam("reccount");
				
				var mvQty =  $("#mvQty").val()
				
				$("#tabList").jqGrid('addRowData', rowId+1, data.logWhMvRegVo, 'first');
				$("#tabList").jqGrid('setCell',rowId+1,'mvQty',mvQty);							
				
				var allRows = $("#tabList").jqGrid('getDataIDs');
	        	   
	        	var cl = allRows[0];
				var rowData = $("#tabList").jqGrid('getRowData', cl);
						
				$("#tabList").find("#"+cl).find("input[name='termVal']").datepicker();
	        	
				
// 				$("#tabList").jqGrid('setCell',rowId+1,'gridFlag',"I");
// 				$("#tabList").jqGrid('setCell',rowId+1,'addDataRow',"A");
				
				//하나의 품목 입력 후 input 박스 초기화
				prdt_clear();
			}
			
		});
	}
	
	function prdt_clear(event){
		$('#searchPrdtNm').val('');
		$('#searchPrdtCd').val('');
		$('#mvQty').val('');
	}
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<input type="hidden" id="searchOrdDt" name="searchOrdDt""/>
	<input type="hidden" id="searchBuyDt" name="searchBuyDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>센터이동등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>센터이동등록 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="25%">
			<col width="100">
			<col width="35%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="20%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">이고일자</label></th>
					<td>
						<input type="text" class="dt" id="searchMvDt" name="searchMvDt"  readonly="readonly" style="width: 100px !important;" />
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:50%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">수량</label></th>
					<td colspan=5>
						<input type="text"   id="mvQty"  name="mvQty" style="width:40%; text-align:right;" >
					</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="searchLCd" objName="searchLCd" parentID="Y"  defName="${commonall}"/>
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="searchMCd" objName="searchMCd" parentID="Y"  defName="${commonall}" />
					</td>
				<th><label for="sele2">출고창고</label></th>
					<td>
						<html:codeTag comType="SELECT" dataType="PRC3" objId="searchOutWhCd" objName="searchOutWhCd" height="24px" parentID="Y" defValue="" />
					</td>
				<th><label for="sele2">입고창고</label></th>
					<td>
						<html:codeTag comType="SELECT" dataType="PRC3" objId="searchInWhCd" objName="searchInWhCd" height="24px" parentID="Y" defValue="" />
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 추가저장정보 end ----------------------------------------- -->

 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">매입처발주등록</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnSave"   		name="btnSave"     		auth="save"   />		<%-- 저장 --%>
			</div>
		</div>
		
	</form>
	</div>	
 	</li>
 	<li style="float: down; width: 100%; ">
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>            

<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />


<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>