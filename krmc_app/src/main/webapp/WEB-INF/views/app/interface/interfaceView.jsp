<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp" />
<html>
<head>
<script type="text/javascript">
	/* onLoad or key event */
	$(document).ready(function($) { 
		
		$("#srchDate").datepicker();	// 일자 초기화
		
		initSearch('1');				// 그리드 초기화(작업 리스트)
		initSubSearch('2');				// 그리드 초기화(수작업 리스트)

		$('#btnSearch').unbind().click(null, eventSearch); // 검색버튼
		$('#btnSave').unbind().click(null, eventSave); 	   // 저장

		eventSearch(); 				// I/F 관리 데이터 조회 
		eventIfCodeSearch();		// I/F 메뉴 코드 조회
		
		//grid resize
		$(window).bind('resize',
			function() {
				try {
					// width
					// 그리드의 width를 div 에 맞춰서 적용
					$('#tabList').setGridWidth( $('#grid1container').width() ); //Resized to new width as per window
					$('#tabList2').setGridWidth( $('#grid2container').width() ); //Resized to new width as per window

					// height
					var height = $(window).height() - $('#grid1container')[0].offsetTop;
					$('#tabList, #tabList2').setGridHeight(height-310);
					
				} catch (e) {
				}
		}).trigger('resize');
		
	});


	//그리드 초기화(작업 리스트)
	function initSearch(gridNum) {
		
		$("#tabList").jqGrid({
							datatype: "local",  // 보내는 데이터 타입			
							data: [],	
							colNames : [ 
								  '<spring:message code="app.interface.interfaceView.grid.workNm" />' <%-- 작업명 --%>
								, '<spring:message code="app.interface.interfaceView.grid.workType" />' <%-- 작업종류 --%>
								, '<spring:message code="app.interface.interfaceView.grid.stDate" />' <%-- 시작일시 --%>
								, '<spring:message code="app.interface.interfaceView.grid.sucDate" />' <%-- 완료일시 --%>
								, '<spring:message code="app.interface.interfaceView.grid.procRes" />' <%-- 처리결과 --%>
								, '<spring:message code="app.interface.interfaceView.grid.msgCont" />' <%-- 메세지내용 --%>
								, '<spring:message code="app.interface.interfaceView.grid.procNum" />' <%-- 처리건수 --%>
								, 'hidden'
								, 'hidden'
								 ],

							colModel : [ 
								 { name : 'applNm',  	index : 'applNm',  sortable : true, align : "left" },
								 { name : 'autoYn',  	index : 'autoYn',  sortable : true, align : "center" },
								 { name : 'startDt', 	index : 'startDt', sortable : true, align : "center" },
								 { name : 'endDt',   	index : 'endDt',   sortable : true, align : "center" },
								 { name : 'succYn',  	index : 'succYn',  sortable : true, align : "center" },
							 	 { name : 'rsltCd',  	index : 'rsltCd',  sortable : true, align : "center" },
								 { name : 'recCnt',  	index : 'recCnt',  sortable : true, align : "right"  },
								 { name : 'startDt', 	index : 'startDt', hidden:true },
								 { name : 'endDt',   	index : 'endDt',   hidden:true }
							],

							gridComplete : function() { //데이터를 성공적으로 가져오면 실행 됨
								var colCount = $(this).getGridParam("colNames").length;
				            	$("#blankRow" + gridNum + " td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
								$(window).resize();

								var allRows = jQuery("#tabList").jqGrid('getDataIDs'); // 전체 rowCount
										
								var useYnText = '<spring:message code="app.interface.interfaceView.grid.useN" />'; //미사용
								// 사용여부 Y : 사용, N : 미사용
								for (var i = 0; i < allRows.length; i++) {
									var cl = allRows[i];
									var rowAllData = jQuery("#tabList").getRowData(allRows[i]);

									if (rowAllData.useYn == "Y") {
										useYnText = '<spring:message code="app.interface.interfaceView.grid.useY" />'; //사용
									}
									jQuery("#tabList").jqGrid('setRowData', cl,{ 'useYn' : useYnText }); 
								}
							},
							
							loadComplete : function() {
								$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
										
								if ($(this).getGridParam("records") == 0) {
									var firstColName = $(this).getGridParam("colModel")[1].name;
											
									var msg = new Function("return {'" + firstColName + "':'<spring:message code="app.interface.interfaceView.grid.noData" />'}") (); //DATA가 없습니다.
									$(this).addRowData("blankRow" + gridNum, msg);
								}

								$(window).trigger('resize');
							},
							
							loadError : function(xhr, status, error) { //데이터 못가져오면 실행 됨
								alert('<spring:message code="app.interface.interfaceView.alert.noData" />'); //시스템 오류로 인해 조회가 불가능 합니다. \n관리자에게 문의 하시기 바랍니다.
								return false;
							},
							
							onSelectRow : function(id, status, e) { //행 선택시 이벤트
								if (id == 'blankRow') {
									return;
								}

								var rowdata = $(this).getRowData(id);

								if (!rowdata.groupId || rowdata.groupId == '')
									return;

								
							},

							loadui : "disable", //  이거 안 써주니 로딩 창 같은게 뜸// 데이터 출력시 화면 조절
							gridview : true, // 꼭써주어야한다....
							sortname : 'applNm',
							sortorder : 'asc',
					        emptyrecords : '<spring:message code="message.search.no.data" />',  <%-- 조회결과가 없습니다.--%>
							viewrecords : true,
							rownumbers : true, //맨앞 일련번호
							loadonce : true,
				            shrinkToFit : false,
				            autowidth : true
		});
	}

	
	//그리드 초기화(수작업 리스트)
	function initSubSearch(gridNum) {
		
		$("#tabList2").jqGrid({
			 		datatype: "local",  // 보내는 데이터 타입
					data: [],
					colNames : [ '<spring:message code="app.interface.interfaceView.grid.dtlCd" />','<spring:message code="app.interface.interfaceView.grid.dtlNm" />'], // 헤더에 들어가는 이름(작업코드, 작업명)

					colModel : [ 
							{ name : 'dtlCd', index : 'dtlCd', sortable : true, width : 50,  align : "center", key: true },
							{ name : 'dtlNm', index : 'dtlNm', sortable : true, width : 150, align : "left" }
					],
					
					gridComplete : function(data) { //데이터를 성공적으로 가져오면 실행 됨
						var colCount = $(this).getGridParam("colNames").length;
						$("#blankRow" + gridNum + " td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
						$(window).resize();

						var allRows = jQuery("#tabList2").jqGrid('getDataIDs'); // 전체 rowCount
								
						var useYnText = '<spring:message code="app.interface.interfaceView.grid.useN" />'; //미사용
						// 사용여부 Y : 사용, N : 미사용
						for (var i = 0; i < allRows.length; i++) {
							var cl = allRows[i];
							var rowAllData = jQuery("#tabList2").getRowData(allRows[i]); 

							if (rowAllData.useYn == "Y") {
								useYnText = '<spring:message code="app.interface.interfaceView.grid.useY" />'; //사용
							}
							jQuery("#tabList2").jqGrid('setRowData', cl, { 'useYn' : useYnText }); 
						}
					},
					loadComplete : function() {
						$(".ui-jqgrid .ui-jqgrid-btable").css("cursor", "pointer");
								
						if ($(this).getGridParam("records") == 0) {
							var firstColName = $(this).getGridParam("colModel")[1].name; 
							var msg = new Function("return {'" + firstColName + "':'<spring:message code="app.interface.interfaceView.grid.noData" />'}") (); //DATA가 없습니다.
									
							$(this).addRowData("blankRow" + gridNum, msg);
						}

						$(window).trigger('resize');
					},
					loadError : function(xhr, status, error) { //데이터 못가져오면 실행 됨
						alert('<spring:message code="app.interface.interfaceView.alert.noData" />'); //시스템 오류로 인해 조회가 불가능 합니다. \n관리자에게 문의 하시기 바랍니다.
						return false;
					},
					onSelectRow : function(id, status, e) { //행 선택시 이벤트
						if (id == 'blankRow') {
							return;
						}

						var rowdata = $(this).getRowData(id);

						if (!rowdata.groupId || rowdata.groupId == '')
							return;
					},

					loadui : "disable", //  이거 안 써주니 로딩 창 같은게 뜸// 데이터 출력시 화면 조절
					gridview : true, // 꼭써주어야한다....
					//pager: '#pageList',
					sortname : 'groupNm',
					sortorder : 'asc',
		            emptyrecords 	: '<spring:message code="message.search.no.data" />',     <%-- 조회결과가 없습니다.--%>
					viewrecords : true,
					rownumbers : true, //맨앞 일련번호
	                multiselect: true,
					loadonce : true
		});
		
	}
	

	//조회버튼 클릭 이벤트
	function eventSearch() {

		var srchDate = $('#srchDate').val().replaceAll("/","");
		var srchMonDivnCd = $('#srchMonDivnCd').val();		
		var srchApplCd = $('#srchApplCd').val();
		var srchSuccYn = $('#srchSuccYn').val();

		var str = {
					'srchDate'	 : srchDate,
					'srchMonDivnCd': srchMonDivnCd,
					'srchApplCd' : srchApplCd,
					'srchSuccYn' : srchSuccYn,
				  };
		
		$("#tabList").jqGrid('setGridParam', {
			url : '<c:url value="/app/interface/interfaceView_selInterfaceViewList"/>', 
			datatype : "json",
			postData : str,
			mtype : 'POST'
		}).trigger("reloadGrid");
	}

	//수작업 코드 조회 이벤트
	function eventIfCodeSearch() {

		$("#tabList2").jqGrid('setGridParam', {
			url : '<c:url value="/app/interface/interfaceView_selInterfaceCodeList"/>', 
			datatype : "json",
			mtype : 'POST'
		}).trigger("reloadGrid");
		
	}

	/* 목록 실행버튼 이벤트*/
	function eventSave() {
		
        var grid = $("#tabList2");
        var rowKey = grid.getGridParam("selrow");
		var str = [];
		
        if (!rowKey) {
            alert("No rows are selected");
        	return false;
        } else {
            var selectedIDs = grid.getGridParam("selarrrow"); // dtlCd
            
            for (var i = 0; i < selectedIDs.length; i++) {
        		var dtlCd = {};
            	dtlCd['dtlCd'] = selectedIDs[i];
            	str.push(dtlCd);
            }
        }
        
		$('#wrapper').mask('<spring:message code="app.interface.interfaceView.alert.Proceeding" />');
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8'
		      , type : 'post'
		      , dataType : 'json'
		      , async : true
		      , url  : '<c:url value="/app/interface/interface_insExecuteIf"/>'
		      , data : JSON.stringify({'dtlCdAry':str})
		      , success : function(data){
// 		    	  console.log(data);
		    	  $('#wrapper').unmask();
		    	  var result = data.msgCd;
		    	  if(result != null && result == 'success'){
		    		  alert("작업이 완료되었습니다.");
			    	  eventSearch();
			    	  eventIfCodeSearch();		    		  
		    	  }else{
		    		  alert("작업요청이 실패하였습니다.");
		    	  }
		    	  
		      }, error:function(request,status,error){
		    	  $('#wrapper').unmask();
		      }
		});	
	}

</script>
</head>
<body>
	<div id="section">
		<input type="hidden" id="selectedGroupId" name="selectedGroupId">
		<input type="hidden" id="selectedGroupNm" name="selectedGroupNm">
		<input type="hidden" id="selectedSysYn" name="selectedSysYn">

		<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />

		<!-- 검색조건 start -->
		<form id="searchForm" name="searchForm" method="post">
			<fieldset>
				<table style="width: 100%" summary="" class="type1">
					<colgroup>
						<col width="150">
						<col width="*">
						<col width="150">
						<col width="*">
					</colgroup>
					<tbody id="_search">
						<tr>
							<th>
								<spring:message code="app.interface.interfaceView.search.date" />
							</th>
							<td>
								<input type="text" style="width: 100px;" name="srchDate" id="srchDate" value="<c:out value='${util:getNowDate()}'/>" readonly="readonly" />
							</td>
							<th>
								업무유형
							</th>
							<td>
								<html:codeTag comType="SELECT" objName="srchMonDivnCd" objId="srchMonDivnCd"  parentID="9303" width="30%;" defName="전체" />
							</td>							
						</tr>
						<tr>
							<th>
								<spring:message code="app.interface.interfaceView.search.workType" />
							</th>
							<td>
								<html:codeTag comType="SELECT" objName="srchApplCd" objId="srchApplCd" parentID="O001" width="50%;" defName="전체" />
							</td>
							<th>
								<spring:message code="app.interface.interfaceView.search.procRes" />
							</th>
							<td>
								<select name="srchSuccYn" id="srchSuccYn" class="form-control">
									<option value=""><spring:message code="common.all" /></option>
									<option value="Y"><spring:message code="app.interface.interfaceView.search.success" /></option>
									<option value="N"><spring:message code="app.interface.interfaceView.search.fail" /></option>
								</select>
							</td>						
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
		<!-- 검색조건 end -->
		<br/>
		<table border="0" width="100%">
			<tr>
				<td width="66%">
					<div class="tit_area">
						<h2><spring:message code="app.interface.interfaceView.title.title1" /></h2>
							<div class="btn_l">
								<html:button id="btnSearch" auth="select" />
							</div> 
					</div>
					<div id="grid1container" class="gridcontainer">
						<table id="tabList">
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
			
					<div id="pageList"></div>
				</td>
				<td width="1%"></td>
				<td width="33%">
					<div class="tit_area">
						<h2><spring:message code="app.interface.interfaceView.title.title2" /></h2>
						<div class="btn_l">
							<html:button id="btnSave" auth="select"  msgKey="button.interfaceView.execu"/>
						</div>
					</div>
					<div id="grid2container" class="gridcontainer">
						<table id="tabList2">
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
			
					<div id="pageList2"></div>				
				</td>
			</tr>			
		</table>
	</div>

</body>
</html>
