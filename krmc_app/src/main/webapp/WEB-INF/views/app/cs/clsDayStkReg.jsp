<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchStkDt").datepicker();
		
		$("#searchStkDt").datepicker('setDate','c');
		
		initInventoryGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnSave').unbind().click(null,	        saveEvent);   // 저장
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		
		$('#btnInventoryChkPrt').unbind().click(null,	inventoryChkPrtEvent); 	 // 재고조사표
		
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
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initInventoryGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '품목코드'
           			, '랙번호'
           			, '품명'
           			, '규격'
           			, '박스당수량'
           			
           			, '전월재고'
           			, '입고'
           			, '반품'
           			, '입고계'
           			
           			, '출고'
           			, '반품'
           			, '출고계'
           			
           			, '수량'
           			, '금액'
           			
           			, 'A+B동'
           			, 'C+D동'
           			, '합계수량'
           			, '합계금액'
           			
           			, '수량'
           			, '금액'
           			
           			, '단가'
           			
           			, '재고일자'
           			, '창고'
           			, 'Row상태'
           ],
			colModel:[
						{name:'prdtCd'			, index:'prdtCd'		, sortable:true, editable:false, width:100 , align:"center"}
		            	,{name:'lackNo1'		, index:'lackNo1'		, sortable:true, editable:false, width:120 , align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, editable:false, width:180 , align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'qtyBox'			, index:'qtyBox'		, sortable:true, editable:false, width:80 , align:"center"}
		            	
		            	,{name:'prevStkQty'		, index:'prevStkQty'	, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'} 
		            	
		            	,{name:'buyQty'			, index:'buyQty'		, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'} 
		            	,{name:'buyRtnQty'		, index:'buyRtnQty'		, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'} 
		            	,{name:'buyTotQty'		, index:'buyTotQty'		, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'} 
		            	
		            	,{name:'dlvQty'			, index:'dlvQty'		, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'} 
		            	,{name:'salesRtnQty'	, index:'salesRtnQty'	, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'}
		            	,{name:'dlvTotQty'		, index:'dlvTotQty'		, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'}
		            	
		            	,{name:'lStkQty'		, index:'lStkQty'		, sortable:true, editable:false, width:90 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'lStkAmt'		, index:'lStkAmt'		, sortable:true, editable:false, width:100 , align:"right", formatter:'integer'}   
		            	
		            	,{name:'area1Qty'		, index:'area1Qty'		, sortable:true, editable:false, width:80 , align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour}
		            	,{name:'area2Qty'		, index:'area2Qty'		, sortable:true, editable:false, width:80 , align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour}
		            	,{name:'rStkQty'		, index:'rStkQty'		, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'}
		            	,{name:'rStkAmt'		, index:'rStkAmt'		, sortable:true, editable:false, width:90 , align:"right", formatter:'integer'}
		            	
		            	,{name:'stkDiffQty'		, index:'stkDiffQty'	, sortable:true, editable:false, width:80 , align:"right", formatter:'integer'}
		            	,{name:'stkDiffAmt'		, index:'stkDiffAmt'	, sortable:true, editable:false, width:90 , align:"right", formatter:'integer'}
		            	
		            	,{name:'cost'			, index:'cost'			, sortable:true, editable:false, width:90 , align:"right", formatter:'integer'}
		            	
		            	,{name:'stkDt'		, index:'stkDt'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'whCd'		, index:'whCd'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'gridFlag'		, index:'gridFlag'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount)
                							  .attr("style", "text-align: center;");
	            
	            $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
	            
	         	// ===================== Footer Sum
				$("#tabList").jqGrid('footerData', 'set', { 'prdtStd' : '합계' });
	            
	            ids = $("#tabList").jqGrid('getDataIDs');
	            
	            /* 조회후 데이터가 한건이라도 있을경우  */
				if(ids.length > 0 && $("#blankRow").val() != ""){
					setFooter();
				}
	            
	         // input 타입이 text인 셀에 포커스가 잡힐 때의 이벤트 설정
	            $("input[type='text']").focus(function () {
	                var $cell = $(this).closest("td");
	                lastSelectedRow = $cell.closest("tr").attr("id");
	                lastSelectedCol = $cell.index();
	            
		         	// 키 다운 이벤트 설정
		            $("#tabList").unbind('keydown').keydown(function (e) {
		                var key = e.which;

		                // 위쪽 화살표 키가 눌렸는지 확인
		                if (key == 38) {
		                    e.preventDefault();
		                    var $prevInput = getNextTextInputCell("prevRow");
		                    if ($prevInput) {
		                        $prevInput.focus();
		                        $prevInput.select();
		                    }
		                }
		                // 아래쪽 화살표 키가 눌렸는지 확인
		                else if (key == 40) {
		                    e.preventDefault();
		                    var $nextInput = getNextTextInputCell("nextRow");
		                    if ($nextInput) {
		                        $nextInput.focus();
		                        $nextInput.select();
		                    }
		                }
		            });

		         	// 다음 input 타입이 text인 셀을 찾는 함수
		            function getNextTextInputCell(direction) {
		                var $currentCell = $("#" + lastSelectedRow + " td:eq(" + lastSelectedCol + ")");
		                var $nextCell;

		                if (direction === "next") {
		                    $nextCell = $currentCell.nextAll('td').find('input[type="text"]').first();
		                    if ($nextCell.length === 0) {
		                        $nextCell = $currentCell.closest('tr').nextAll('tr').find('td').find('input[type="text"]').first();
		                    }
		                }
		                else if (direction === "prev") {
		                	$nextCell = $currentCell.prevAll('td').find('input[type="text"]').last();
	                        if ($nextCell.length === 0) {
	                            $nextCell = $currentCell.closest('tr').prevAll('tr').find('td').find('input[type="text"]').last();
	                        }
		                }
		         		else if (direction === "prevRow") {
		                    var $prevRow = $currentCell.closest('tr').prev('tr');
		                    var $grid = $("#tabList").jqGrid('getDataIDs');
		                    var rowIndex;
		                    
		                    $nextCell = $prevRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
	                        
		                    $grid.forEach(function(id, index) {
	                            if (id === lastSelectedRow) {
	                                rowIndex = index;
	                                return false; // 반복 종료
	                            }
	                        });
	                        
		                    if ($nextCell.length === 0 && rowIndex != 0) {
		                        for(var i = 0; $nextCell.length < 1; i++ ) {
				                    $prevRow = $prevRow.prev('tr');
			                        $nextCell = $prevRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
		                        }
		                    }
		                }
		         		else if (direction === "nextRow") {
		                    var $nextRow = $currentCell.closest('tr').next('tr');
		                    var $grid = $("#tabList").jqGrid('getDataIDs');
		                    var rowIndex, lastRowIndex;
		                    
	                        $nextCell = $nextRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
	                       
	                        //현재 focus된 row의 인덱스를 구함
	                        $grid.forEach(function(id, index) {
	                            if (id === lastSelectedRow) {
	                                rowIndex = index;
	                                return false; // 반복 종료
	                            }
	                        });
	                        
	                        // 행의 개수를 가져옴
							var rowCount = $("#tabList").jqGrid('getGridParam', 'records');
	                        
							// 가장 마지막 행부터 역순으로 확인하여 input 타입이 text인 셀을 찾음
							for (var i = rowCount; i >= 1; i--) {
							    var rowHasTextInput = false;
							    $("#tabList").find("tr").eq(i).find('input[type="text"]').each(function() {
							        if ($(this).val() !== "") {
							            rowHasTextInput = true;
							            return false; // forEach 반복 종료
							        }
							    });
							    if (rowHasTextInput) {
							        lastRowIndex = i - 1;
							        break;
							    }
							}
							
		                    if ($nextCell.length === 0 && rowIndex < lastRowIndex) {
		                        for(var i = 0; $nextCell.length < 1; i++ ) {
				                    $nextRow = $nextRow.next('tr');
				                    $nextCell = $nextRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
		                        }
		                    }
		                }

		                return $nextCell;
		            }
	         	});
	         	//여기까지 커서 이동
	         	
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	            
	        	$(window).resize();
	        },
	        
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },

            rowNum:1000,
            pager: '#pageList',
			loadui : "disable",													// 이거 안 써주니 로딩 창 같은게 뜸
			gridview : true,													// 그리드 속도
			viewrecords : true,													// 하단에 1/1 또는 데이터가없습니다 추가
			rownumbers : true,													// rowNumber 표시여부
			autowidth : true,
			shrinkToFit : false,
			loadonce : true,
			footerrow: true,
			sortorder : "desc"
		});
		
		//2줄 헤더
		jQuery("#tabList").jqGrid('setGroupHeaders', {
			useColSpanStyle: true, 
			groupHeaders:[
				{startColumnName: 'buyQty', numberOfColumns: 3, titleText: '당월 입고현황'},
				{startColumnName: 'dlvQty', numberOfColumns: 3, titleText: '당월 출고현황'},
				{startColumnName: 'lStkQty', numberOfColumns: 2, titleText: '수불현황(전산)'},
				{startColumnName: 'area1Qty', numberOfColumns: 4, titleText: '실재고'},
				{startColumnName: 'stkDiffQty', numberOfColumns: 2, titleText: '재고차'},
			],
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
		}
	}

	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
        $("#searchStkDt").datepicker('setDate','c');
		
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
			url:"<c:url value='/app/cs/clsDayStkReg_selList.json'/>",
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

		var saveData = {'clsDayStkRegList'  : $("#tabList").getRowData()};
		
		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/cs/clsDayStkReg_insList.json'/>",
 
		    data : JSON.stringify(saveData),
	 	    success : function(data){
	   	 	
	 	    	if(data.msgCd =="0") {
					alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
					
					$('#ordYn').val('10').trigger('change');
	      	    	
	   				searchEvent();			//  재조회
			   	}else{
					alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
				}
      	    }
		});

	}
	
	/* 조회된 결과의 합계 */
	function setFooter(){
		var searchInfo = {};
		
		$('#searchForm').find('input').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});
		
		$('#searchForm').find('select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/cs/clsDayStkRegFooter_selList.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {
				if(data != null){
					$("#tabList").jqGrid('footerData', 'set', { 'prevStkQty':data.prevStkQty });
					$("#tabList").jqGrid('footerData', 'set', { 'buyQty':data.buyQty });
					$("#tabList").jqGrid('footerData', 'set', { 'buyRtnQty':data.buyRtnQty });
					$("#tabList").jqGrid('footerData', 'set', { 'buyTotQty':data.buyTotQty });
					$("#tabList").jqGrid('footerData', 'set', { 'dlvQty':data.dlvQty });
					$("#tabList").jqGrid('footerData', 'set', { 'salesRtnQty':data.salesRtnQty });
					$("#tabList").jqGrid('footerData', 'set', { 'dlvTotQty':data.dlvTotQty });
					$("#tabList").jqGrid('footerData', 'set', { 'lStkQty':data.lStkQty });
					$("#tabList").jqGrid('footerData', 'set', { 'lStkAmt':data.lStkAmt });
					$("#tabList").jqGrid('footerData', 'set', { 'area1Qty':data.area1Qty });
					$("#tabList").jqGrid('footerData', 'set', { 'area2Qty':data.area2Qty });
					$("#tabList").jqGrid('footerData', 'set', { 'rStkQty':data.rStkQty });
					$("#tabList").jqGrid('footerData', 'set', { 'rStkAmt':data.rStkAmt });
					$("#tabList").jqGrid('footerData', 'set', { 'stkDiffQty':data.stkDiffQty });
					$("#tabList").jqGrid('footerData', 'set', { 'stkDiffAmt':data.stkDiffAmt });
				}
			}
		});
	}
	
	
	/* 엑셀  */
	function excelEvent(){
		$("#excelStkDt").val($("#searchStkDt").val().replaceAll('-',''));
		
		$('#searchForm').attr("action", "<c:url value='/app/cs/clsDayStkReg_selExcelDown'/>").submit();
	}


	/*재고조사표*/
	function inventoryChkPrtEvent(){
		
		var url = "<c:url value="/app/cs/clsDayStkReg_prtInspList" />";
		var target = "PDFPrint";
		var agt = navigator.userAgent.toLowerCase();

		if (agt.indexOf("msie") != -1) {		
			searchForm.action = url;
			searchForm.submit();
		}
		else {
			window.open('','PDFPrint','toolbar=no,resizable=no,location=0,scrollbars=0,width=800,height=700,top=50,left=200');
			searchForm.action=url;
			searchForm.target="PDFPrint";
			searchForm.submit();
		}
		
	}
	
	
	function prdt_clear(event){
		$('#searchPrdtNm').val('');
		$('#searchPrdtCd').val('');
	}
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<input type="hidden" id="excelStkDt" name="excelStkDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>일재고등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>일재고등록 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="15%">
			<col width="100">
			<col width="30%">
			<col width="100">
			<col width="25%">
			<col width="100">
			<col width="15%">
			<col width="100">
			<col width="15%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">재고조사일</label></th>
					<td>
						<input type="text" class="dt" id="searchStkDt" name="searchStkDt"  readonly="readonly" style="width: 100px !important;" />
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:80%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdtCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">대분류</label></th>
						<td>
							<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="searchLCd" objName="searchLCd" parentID="Y" defName="${commonall}"/>
						</td>
				<th><label for="sele2">저장형태</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchStrgType" objName="searchStrgType" parentID="M001" defName="${commonall}" /> <%--전체 --%>
					</td>
				<th><label for="sele2">입고창고 :</label></th>
					<td>
			 			<html:codeTag comType="SELECT" dataType="PRC3" objId="searchWhCd" objName="searchWhCd" height="24px" parentID="Y" defValue=""/>
			 		</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">일재고등록</h2>
			<div class="btn_l">
				<html:button id="btnInventoryChkPrt" name="btnInventoryChkPrt" auth="print"  value="재고조사표출력"  />
				&emsp;&emsp;&emsp;&emsp;&emsp;
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnSave"   		name="btnSave"     		auth="save"   />		<%-- 저장 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
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