<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){
		initSalesPrdtMstGrid();


		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnSave').unbind().click(null,	        saveEvent); // 저장
		$('#btnNew').unbind().click(null,	        newEvent); // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent); // 엑셀
		
		$("#btnSales_find").unbind().click(function(){
			findSalesMst();
		});		<%--매출처 팝업 찾아가기--%>
		
		$("#btnPrdt_find").unbind().click(function(){
			findPrdtMst();
		});		<%--상품 팝업 찾아가기--%>
		
		$('#btnSelectPrdtDelete').unbind().click(null,	deleteSelectPrdtEvent); // 선택 품목 삭제		
		$('#btnSalesPrdtCopy').unbind().click(null,	 	salesPrdtCopyEvent); // 매출처 품목 일괄 복사
		$('#btnPrdtSalesInsert').unbind().click(null,	salesPrdtAllInsertEvent); // 품목일괄적용
		$('#btnPriceUpt').unbind().click(null,	priceUpt); // 일괄판매가 수정
		
		
		//Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-80);	//GRID  LIST
			    } 
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-100);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		// 조회조건 입력필드 enter key이벤트 --------------
		$('#salesNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findSalesMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#prdtNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findPrdtMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#prdtCdPriceUpt').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : prdtCdPriceUptKeyEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#salesNmOrg').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : salesCdOrgKeyEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#salesNmNew').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : salesCdNewKeyEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#salesNmAll').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : salesCdAllKeyEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#prdtNmAll').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : prdtCdAllKeyEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#priceAll').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : gridAdd(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------


		//최상단 체크박스 클릭 ------------------------------------------------
	    $("#checkAll").click(function(){
	    	var checkGbn = $('#checkGbn').val();
	    	if(checkGbn == 'Y'){
	    		$(this).attr("src", $(this).attr("src").replace("_yes", "_no"));
	    		//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $("input[name=chPrdt]").prop("checked",false);
	            $('#checkGbn').val("N");
	    	}else{
	    		$(this).attr("src", $(this).attr("src").replace("_no", "_yes"));
	    		//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $("input[name=chPrdt]").prop("checked",true);
	            //클릭이 안되있으면
	            $('#checkGbn').val("Y");
	    	}
	    })
	    //-----------------------------------------------------------------
		

	});


	/* 마스터 데이터  그리드 초기화 */
	function initSalesPrdtMstGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
					  '<img id="checkAll" src="<c:url value="/resources/images/pearl/common/icons_checkbox_no.png"/>" align=middle>'
				    , '품목코드'
           			, '품목명'
           			, '규격'
           			, '단위'
           			, '원가'
           			, '매출처'
           			, '매출처품목(BOH)'
           			, '매출처품목(FOH)'
           			, '매출단가'
           			, '이전단가'
           			, '과면세'
           			, '적용일'
           			, '등록자'
           			, '등록시간'
           			, '수정자'
           			, '수정시간'
           			
           			, '매출처품목'
           			, '매출처품목'
           			, '단가'
           			, '본사구분'
           			, '본사구분'
           ],
			colModel:[
				 {name:'Check_Box'      , index:'Check_Box'    	, sortable:false, width:50	, align:"center"}
				 ,{name:'prdtCd'		, index:'prdtCd'		, sortable:true,  width:100 , align:"center"}
	             ,{name:'prdtNm'		, index:'prdtNm'		, sortable:true,  width:180 , align:"left"}
	             ,{name:'prdtStd'		, index:'prdtStd'		, sortable:true,  width:100 , align:"center"}
	             ,{name:'ordUnit'		, index:'ordUnit'		, sortable:true,  width:60 , align:"center"}
	             ,{name:'cost'			, index:'cost'			, sortable:true, sorttype:'number',  width:80 , align:"right", formatter:'Integer'}
	             ,{name:'salesNm'		, index:'salesNm'		, sortable:true,  width:160 , align:"left"}
	             ,{name:'salesPrdtCd1'	, index:'salesPrdtCd1'	, sortable:true,  width:120 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
	             ,{name:'salesPrdtCd2'	, index:'salesPrdtCd2'	, sortable:true,  width:120 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
	             ,{name:'price'			, index:'price'			, sortable:true, sorttype:'number',  width:90 , align:"right", formatter:fmtSetGridInputAmt, unformat: unfmtGetGridInputAmt}
	             ,{name:'prevPrice'		, index:'prevPrice'		, sortable:true, sorttype:'number',  width:90 , align:"right", formatter:'Integer'}
	             ,{name:'vatYn'			, index:'vatYn'			, sortable:true,  width:100 , align:"center", formatter:function(cellvalue, options, rowObject){return fmtSetGridSelectBox(cellvalue, options, rowObject, "<c:out value='${codeList_vatYn}'/>");}	,unformat: unfmtGetGridSelectBox}
	             ,{name:'startDt'		, index:'startDt'		, sortable:true,  width:120 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
	             ,{name:'regNm'			, index:'regNm'			, sortable:true,  width:120 , align:"center"}
	             ,{name:'regDt'			, index:'regDt'			, sortable:true,  width:140 , align:"center"}
	             ,{name:'modNm'			, index:'modNm'			, sortable:true,  width:120 , align:"center"}
	             ,{name:'modDt'			, index:'modDt'			, sortable:true,  width:140 , align:"center"}
	             
	             ,{name:'salesPrdtCd1Org'			, index:'salesPrdtCd1Org',	hidden:true		, sortable:true,  width:140 , align:"center"}
	             ,{name:'salesPrdtCd2Org'			, index:'salesPrdtCd2Org',	hidden:true		, sortable:true,  width:140 , align:"center"}
	             ,{name:'priceOrg'			, index:'priceOrg',	hidden:true		, sortable:true,  width:140 , align:"center"}
	             ,{name:'hqClass'			, index:'hqClass',	hidden:true		, sortable:true,  width:140 , align:"center"}
	             ,{name:'salesCd'		, index:'salesCd'		, sortable:true,  width:160 , align:"left"}
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	         // input 타입이 text인 셀에 포커스가 잡힐 때의 이벤트 설정
	            $("input[type='text']").focus(function () {
	                var $cell = $(this).closest("td");
	                lastSelectedRow = $cell.closest("tr").attr("id");
	                lastSelectedCol = $cell.index();
	            
		         	// 키 다운 이벤트 설정
		            $("#tabList").unbind('keydown').keydown(function (e) {
		                var key = e.which;
	
		             // 오른쪽 화살표 키가 눌렸는지 확인
		                if (key == 39) {
		                    e.preventDefault();
		                    var $nextInput = getNextTextInputCell("next");
		                    if ($nextInput) {
		                        $nextInput.focus();
		                        $nextInput.select();
		                    }
		                }
		                // 왼쪽 화살표 키가 눌렸는지 확인
		                else if (key == 37) {
		                    e.preventDefault();
		                    var $prevInput = getNextTextInputCell("prev");
		                    if ($prevInput) {
		                        $prevInput.focus();
		                        $prevInput.select();
		                    }
		                }
		                // 위쪽 화살표 키가 눌렸는지 확인
		                else if (key == 38) {
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
	            
	        	$(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	                
	              //  $('#mainForm')[0].reset();
	                
	            }

	            var allRows = jQuery("#tabList").jqGrid('getDataIDs');			// 전체 rowCount
	            
	        	for(var i = 0; i < allRows.length; i++){
	        		var cl = allRows[i];
					var rowAllData = jQuery("#tabList").getRowData(allRows[i]);
					var checkFiled = ""
					if(rowAllData.prdtCd) {
						checkFiled = "<input type='checkbox' name='chPrdt'><input type='hidden' name='prdtCd' value='"+rowAllData.prdtCd+"'><input type='hidden' name='salesCd' value='"+rowAllData.salesCd+"'>";
						jQuery("#tabList").jqGrid('setRowData',cl,{'Check_Box':checkFiled});			// CheckBox
					}
			     }
	            
	        	$(window).resize();
	        },

			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            }

            ,rowNum:-1
            ,loadui : "disable"
            ,gridview:    true
            ,sortname: 'prdtCd'
            ,sortorder: 'asc'
            //,emptyrecords : '<spring:message code="message.search.no.data" />'
            ,viewrecords: true
            ,scroll: false
            //,cellEdit: true
            ,rownumbers:true
            ,loadonce:true
            ,shrinkToFit: false
            ,autowidth:true
		});
		
	}
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst() {
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer);
	}
	
	/*매츨처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		if (data != null){
			$("#salesCd").val(data.salesCd);
			$("#salesNm").val(data.salesNm);
			$("#hqClass").val(data.hqClass);
			
			$("#prdtNm").focus();
		}
	}

	/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonSalesPrdtMstFindLayer('', '', $("#prdtNm").val(), $("#salesCd").val(), setSalesPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
	function setSalesPrdtMstFindLayer(data) {
		if (data != null){
			$("#prdtCd").val(data.prdtCd);
			$("#prdtNm").val(data.prdtNm);
		}
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
        //Grid 초기화
		$("#tabList").jqGrid('clearGridData');	
	}

	
	/* 조회 */
	function searchEvent(event){
		
// 		if(!$('#salesNm').val()) {
// 			alert('매출처를 선택 입력하세요.');
// 			return;
// 		}

		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/salesPrdtMgmt_selList.json'/>",
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

		var saveData = {'salesPrdtMasterData'  : $("#tabList").getRowData()};
		
		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/bm/baseinfo/salesPrdtMgmt_insList.json'/>",
 
		    data : JSON.stringify(saveData),
	 	    success : function(data){
	   	 	
		 	    if('success' == data.msgCd)
	      	    {
	      	    	msg = '<spring:message code="message.success.insert" />'; <%-- 저장이 정상적으로 처리되었습니다. --%>
	      	    }else if ('noData' == data.msgCd){
	      	    	msg = '<spring:message code="message.fail.process" />'; <%-- 저장된 데이터가 없습니다. --%>
	      	    }
      	    	else msg = '<spring:message code="message.error.process" />'; <%-- 저장중 오류가 발생하였습니다. --%>
	
	      	  	alert(msg);
      	    }
		});

	}
	
	// 선택 품목 삭제
	function deleteSelectPrdtEvent ()
	{
       
		$("#tabList").editCell(0, 0, false);
		
		var chkRowCnt = $("input[name='chPrdt']:checked:visible").length;
		$("#checkAll").attr("src",$('#checkAll').attr("src").replace("_yes", "_no"));

		var prdtJobList = [];
		
		$("input[name='chPrdt']:checked:visible").each(function(){
		
			var prdtInfo = {};
			
			prdtInfo['prdtCd']  = $(this).parent().children('input[name="prdtCd"]').attr('value');
			prdtInfo['salesCd'] = $(this).parent().children('input[name="salesCd"]').attr('value');

			prdtJobList.push(prdtInfo);
		});

		if(prdtJobList.length <= 0) {
			alert('삭제할 항목을 선택해 주세요.');
			return false;
		}
	
		if (!confirm('선택한 항목를 삭제 하시겠습니까?')) {
			return false;
		}
		
		var param = {prdtJobList : prdtJobList};

		$.ajax({
			contentType : 'application/json; charset=utf-8',
	      	type : 'post',
	      	dataType : 'json',
	      	url:'<c:url value="/app/bm/baseinfo/salesPrdt_delSelectRow.json"/>',
	      	data : JSON.stringify(param),
	      	success : function(data){
	      		if('success' == data.msgCd)
	      	    {
	      	    	msg = '<spring:message code="message.success.insert" />'; <%-- 저장이 정상적으로 처리되었습니다. --%>
	      	    	
	      	    	searchEvent();
	      	    }else if ('noData' == data.msgCd){
	      	    	msg = '<spring:message code="message.fail.process" />'; <%-- 저장된 데이터가 없습니다. --%>
	      	    }
      	    	else msg = '<spring:message code="message.error.process" />'; <%-- 저장중 오류가 발생하였습니다. --%>
	
	      	  	alert(msg);
	      	}
		});
	}
	
	
	// 매출처에 품목데이터 복사
	function salesPrdtCopyEvent ()
	{       
		var salesCdOrg = $('#salesCdOrg').val();
		var salesCdNew = $('#salesCdNew').val();
		
		if (!salesCdOrg){
			alert('원매출처를 선택하셔야 합니다.');
			$('#salesNmOrg').focus();
			return false;
		}
		
		if (!salesCdNew){
			alert('수정매출처을 선택하셔야 합니다.');
			$('#salesNmNew').focus();
			return false;
		}
		
		if (!confirm('매출처 품목을 복사 하시겠습니까?')) {
			return false;
		}
		
		var param = {
			salesCdOrg : salesCdOrg
			, salesCdNew : salesCdNew
		};
		
		$.ajax({
			contentType : 'application/json; charset=utf-8',
	      	type : 'post',
	      	dataType : 'json',
	      	url:'<c:url value="/app/bm/baseinfo/salesPrdt_insSalesCdOrgNewInsert.json"/>',
	      	data : JSON.stringify(param),
	      	success : function(data){
	      		if('success' == data.msgCd)
	      	    {
	      	    	msg = '<spring:message code="message.success.insert" />'; <%-- 저장이 정상적으로 처리되었습니다. --%>
	      	    }else if ('noData' == data.msgCd){
	      	    	msg = '<spring:message code="message.fail.process" />'; <%-- 저장된 데이터가 없습니다. --%>
	      	    }
      	    	else msg = '<spring:message code="message.error.process" />'; <%-- 저장중 오류가 발생하였습니다. --%>
	
	      	  	alert(msg);
	      	  	
	      	    $('form').each(function() {
	                this.reset();
	            });
	      	}
		});
	}
	
	//품목일괄적용 조회
	function gridAdd(){
		if(!$('#salesCdAll').val()) {
			alert('매출처를 선택 입력하세요.');
			return;
		}

		var searchInfo = {};

		searchInfo['salesCdAllSch'] = $("#salesCdAll").val();
		searchInfo['hqclassAllSch'] = $("#hqClassAll").val();
		searchInfo['prdtCdAllSch'] = $("#prdtCdAll").val();
		searchInfo['priceAllSch'] = $("#priceAll").val();
		searchInfo['salesPrdtCd1AllSch'] = $("#salesPrdtCd1All").val();
		searchInfo['salesPrdtCd2AllSch'] = $("#salesPrdtCd2All").val();

		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url:"<c:url value='/app/bm/baseinfo/salesPrdtMgmtAdd_selList.json'/>",
			data: JSON.stringify(searchInfo),
			success : function(data){
				
				//blankRow 제거
				$("#tabList").jqGrid('delRowData', 'blankRow');
				
				var rowId = $("#tabList").getGridParam("reccount");
				
				var rowSubData = $("#tabList").jqGrid('getRowData', rowId[0]);
				
				$("#tabList").jqGrid('addRowData', rowId+1, data.salesPrdtMgmtVo, 'first');
				$("#tabList").jqGrid('setCell',rowId+1,'addDataRow',"A");

				ThreeAll_clear();
			}			
		});		
		
	}
	
	
	// 품목일괄적용
	function salesPrdtAllInsertEvent ()
	{
		$("#tabList").editCell(0, 0, false);

		if(!confirm("저장하시겠습니까?")){return;}

		var saveData = {'salesPrdtMasterData'  : $("#tabList").getRowData()};
		
		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/bm/baseinfo/salesPrdt_insSalesPrdtAllInsert.json'/>",
 
		    data : JSON.stringify(saveData),
	 	    success : function(data){
	   	 	
		 	    if('success' == data.msgCd)
	      	    {
	      	    	msg = '<spring:message code="message.success.insert" />'; <%-- 저장이 정상적으로 처리되었습니다. --%>
	      	    }else if ('noData' == data.msgCd){
	      	    	msg = '<spring:message code="message.fail.process" />'; <%-- 저장된 데이터가 없습니다. --%>
	      	    }
      	    	else msg = '<spring:message code="message.error.process" />'; <%-- 저장중 오류가 발생하였습니다. --%>
	
	      	  	alert(msg);
      	    }
		});
	}
	
	
	
	function priceUpt(){
		var prdtCdPriceUpt = $('#prdtCdPriceUpt').val();
		var priceUpt = $('#priceUpt').val();
		
		if (!prdtCdPriceUpt){
			alert('대상품목을 선택하셔야 합니다.');
			$('#prdtCdPriceUpt').focus();
			return false;
		}
		
		if (!priceUpt){
			alert('판매가를 입력하세요.');
			$('#priceUpt').focus();
			return false;
		}
		
		if (!confirm('판매가를 일괄 수정하시겠습니까?')) {
			return false;
		}
		
		var param = {
			prdtCdPriceUpt : prdtCdPriceUpt
			, priceUpt : priceUpt
		};

		$.ajax({
			contentType : 'application/json; charset=utf-8',
	      	type : 'post',
	      	dataType : 'json',
	      	url:'<c:url value="/app/bm/baseinfo/salesPrdt_insSalesPriceUpt.json"/>',
	      	data : JSON.stringify(param),
	      	success : function(data){
	      		if('success' == data.msgCd)
	      	    {
	      	    	msg = '<spring:message code="message.success.insert" />'; <%-- 저장이 정상적으로 처리되었습니다. --%>
	      	 	   	
	      	    }else if ('noData' == data.msgCd){
	      	    	msg = '<spring:message code="message.fail.process" />'; <%-- 저장된 데이터가 없습니다. --%>
	      	    }
      	    	else msg = '<spring:message code="message.error.process" />'; <%-- 저장중 오류가 발생하였습니다. --%>
	
	      	  	alert(msg);
	      	  	
	      	    $('form').each(function() {
	                this.reset();
	            });
	      	}
		});
	}
	
	
	function salesCdOrgKeyEvent()
	{
		commonSalesMstFindLayer('','',$("#salesNmOrg").val(), 'Y', setSalesMstOrgFindLayer);
	}
	
	/*매츨처 콜백(후처리 함수 )*/
	function setSalesMstOrgFindLayer(data) {
		if (data != null){
			$("#salesCdOrg").val(data.salesCd);
			$("#salesNmOrg").val(data.salesNm);
			
			$("#salesNmNew").focus();
		}
	}
	
	function salesCdNewKeyEvent()
	{
		commonSalesMstFindLayer('','',$("#salesNmNew").val(), 'Y', setSalesMstNewFindLayer);
	}
	
	/*매츨처 콜백(후처리 함수 )*/
	function setSalesMstNewFindLayer(data) {
		if (data != null){
			$("#salesCdNew").val(data.salesCd);
			$("#salesNmNew").val(data.salesNm);
		}
	}
	
	function salesCdAllKeyEvent()
	{
		commonSalesMstFindLayer('','',$("#salesNmAll").val(), 'Y', setSalesMstAllFindLayer);
	}
	
	/*매츨처 콜백(후처리 함수 )*/
	function setSalesMstAllFindLayer(data) {
		if (data != null){
			$("#salesCdAll").val(data.salesCd);
			$("#salesNmAll").val(data.salesNm);
			$("#hqClassAll").val(data.hqClass);
			
			$("#prdtNmAll").focus();
		}
	}
	
	function prdtCdAllKeyEvent()
	{
		commonPrdtMstFindLayer('','',$("#prdtNmAll").val(), '', setPrdtMstAllFindLayer);
	}
	
	/*품목 콜백(후처리 함수 )*/
	function setPrdtMstAllFindLayer(data) {
		if (data != null){
			$("#prdtCdAll").val(data.prdtCd);
			$("#prdtNmAll").val(data.prdtNm);
			$("#prdtStd").val(data.prdtStd);
			$("#ordUnit").val(data.ordUnit);
			$("#ordUnitNm").val(data.ordUnitNm);
			$("#cost").val(data.cost);
			
			$("#priceAll").focus();
		}
	}
	
	function prdtCdPriceUptKeyEvent()
	{
		commonPrdtMstFindLayer('','',$("#prdtCdPriceUpt").val(), '', setPrdtCdPriceUptFindLayer);
	}
	
	/*품목 콜백(후처리 함수 )*/
	function setPrdtCdPriceUptFindLayer(data) {
		if (data != null){
			$("#prdtCdPriceUpt").val(data.prdtCd);
			
			$("#priceUpt").focus();
		}
	}
	
	function salesNm_clear(){
		$("#salesNm").val('');
		$("#salesCd").val('');		
		$("#hqClass").val('');
	}
	
	function prdtNm_clear(){
		$("#prdtNm").val('');
		$("#prdtCd").val('');
	}
	
	function salesNmOrg_clear(){
		$("#salesNmOrg").val('');
		$("#salesCdOrg").val('');		
	}
	
	function salesNmNew_clear(){
		$("#salesNmNew").val('');
		$("#salesCdNew").val('');		
	}
	
	function salesNmAll_clear(){
		$("#salesNmAll").val('');
		$("#salesCdAll").val('');		
		$("#hqClassAll").val('');
	}
	
	function prdtNmAll_clear(){
		$("#prdtNmAll").val('');
		$("#prdtCdAll").val('');
	}
	
	function ThreeAll_clear(){
		$("#prdtNmAll").val('');
		$("#prdtCdAll").val('');
		$("#priceAll").val('');
		$("#salesPrdtCd1All").val('');
		$("#salesPrdtCd2All").val('');
		
		$("#prdtNmAll").focus();
	}
	
	function prdtCdPriceUpt_clear(){
		$("#prdtCdPriceUpt").val('');
	}
	
	/* 엑셀  */
	function excelEvent(){
		let gridObj = $("#tabList");
		let excelName = "매출처품목현황";
		
		exportExcel(gridObj, excelName);
		//$('#searchForm').attr("action", "<c:url value='/app/bm/baseinfo/salesPrdtList_selExcelDown'/>").submit();
	}

	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
<input type="hidden" 	id="checkGbn"	name="checkGbn" value="N">
<input type="hidden" 	id="hqClass"	name="hqClass" >
<input type="hidden" 	id="salesCdAllSch"	name="salesCdAllSch" >
<input type="hidden" 	id="hqclassAllSch"	name="hqclassAllSch" >
<input type="hidden" 	id="prdtCdAllSch"	name="prdtCdAllSch" >
<input type="hidden" 	id="priceAllSch"	name="priceAllSch" >
<input type="hidden" 	id="salesPrdtCd1AllSch"	name="salesPrdtCd1AllSch" >
<input type="hidden" 	id="salesPrdtCd2AllSch"	name="salesPrdtCd2AllSch" >
<sec:csrfInput/>
	<fieldset>
	<legend>매출처품목등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처품목관리 검색조건</caption>
		<colgroup>
			<col width="10%">
			<col width="20%">
			<col width="10%">
			<col width="20%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">매출처명</label></th>
				<td>
					<input type="text"   id="salesNm"  name="salesNm" style="width:40%;" onclick="salesNm_clear()" />
					<input type="hidden" id="salesCd"  name="salesCd" style="width:30%;">
					<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
				</td>
				<th><label for="sele2">품목명</label></th>
				<td>
					<input type="text"   id="prdtNm"  name="prdtNm" style="width:40%;" onclick="prdtNm_clear()" />
					<input type="hidden" id="prdtCd"  name="prdtCd" style="width:30%;">
					<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
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
 	<li style="float: top; width: 100%;">
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">매출처품목일괄작업</h2>
			<div class="btn_l">
				<html:button id="btnNew"   		name="btnNew"      auth="insert" /> 		<%-- 신규  --%>
				<html:button id="btnSearch" 	name="btnSearch"   auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 		name="btnExcel"    auth="excel" /> 		<%-- 조회 --%>
				<html:button id="btnSave"   	name="btnSave"     auth="save"   />			<%-- 저장 --%>
			</div>
		</div>
		<form name="mainForm" id="mainForm"  autocomplete="off" >   		
		<div id="logisticsWareData" class="datatableType1">
		<table class="type1">
			<colgroup>
				<col width="150"/>
				<col width="*"/>
				<col width="100"/>
				<col width="*"/>
				<col width="100"/>
				<col width="*"/>				
				<col width="100"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr>
					<th style="text-align:left;">1. 선택 품목 삭제</th>
						<td colspan="11">
							<html:button id="btnSelectPrdtDelete"  name="btnSelectPrdtDelete"  auth="select"  value="실행"/> 
						</td>
				</tr>
				<tr>
					<th style="text-align:left;">2. 매출처품목복사</th>
						<td>
							<html:button id="btnSalesPrdtCopy"  name="btnSalesPrdtCopy"  auth="select"  value="실행"/> 
						</td>
					<td style="background-color:Beige; text-align:center;">원매출처</td>
					<td>
						<input type="text"   id="salesNmOrg"   name="salesNmOrg" onclick="salesNmOrg_clear()" />
						<input type="hidden" id="salesCdOrg"  name="salesCdOrg">
					</td>
					<td style="background-color:Beige; text-align:center;">수정매출처</td>
					<td colspan=7>
						<input type="text"   id="salesNmNew"   name="salesNmNew"  style="width:11%;" onclick="salesNmNew_clear()" />
						<input type="hidden" id="salesCdNew"   name="salesCdNew"/>
					</td>
				</tr>
				<tr>
					<th style="text-align:left;">3. 품목일괄적용</th>
						<td>
							<html:button id="btnPrdtSalesInsert"  name="btnPrdtSalesInsert"  auth="select"  value="실행"/> 
						</td>
					<td style="background-color:Beige; text-align:center;">매출처</td>
					<td>
						<input type="text"   id="salesNmAll"	name="salesNmAll" onclick="salesNmAll_clear()" />
						<input type="hidden" id="salesCdAll"	name="salesCdAll"/>
						<input type="hidden" id="hqClassAll"   	name="hqClassAll"/>
					</td>
					<td style="background-color:Beige; text-align:center;">대상품목</td>
					<td>
						<input type="text"   id="prdtNmAll"   name="prdtNmAll" onclick="prdtNmAll_clear()" />
						<input type="hidden" id="prdtCdAll"   name="prdtCdAll"/>
						<input type="hidden" id="prdtStd"     name="prdtStd"/>
						<input type="hidden" id="ordUnit"     name="ordUnit"/>
						<input type="hidden" id="ordUnitNm"   name="ordUnitNm"/>
						<input type="hidden" id="cost"        name="cost"/>
					</td>
					<td style="background-color:Beige; text-align:center;">판매가</td>
					<td>
						<input type="text"   id="priceAll"   name="priceAll" />
					</td>
					<td style="background-color:Beige; text-align:center;">매출처 품목1</td>
					<td>
						<input type="text"   id="salesPrdtCd1All"   name="salesPrdtCd1All" />
					</td>
					<td style="background-color:Beige; text-align:center;">매출처 품목2</td>
					<td>
						<input type="text"   id="salesPrdtCd2All"   name="salesPrdtCd2All" />
					</td>
				</tr>
				<tr>
					<th style="text-align:left;">4. 일괄판매가수정</th>
						<td>
							<html:button id="btnPriceUpt"  name="btnPriceUpt"  auth="select"  value="실행"/> 
						</td>
					<td style="background-color:Beige; text-align:center;">대상품목</td>
					<td>
						<input type="text" id="prdtCdPriceUpt"  name="prdtCdPriceUpt" onclick="prdtCdPriceUpt_clear()">
					</td>
					<td style="background-color:Beige; text-align:center;">판매가</td>
					<td colspan=7>
						<input type="text"   id="priceUpt"   name="priceUpt"  style="width:11%;" />
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		</form>
	</div>	
 	</li>
 	<li style="float: down; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매출처품목등록</h2>	
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>            


<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />


<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />

<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesPrdtMaster.jsp" />


<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>