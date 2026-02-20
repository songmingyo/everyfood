<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #ordDt, #searchForm #buyDt").datepicker();
		
		$("#ordDt").datepicker('setDate','c');
		$("#buyDt").datepicker('setDate','c+1d');
		
		initSalesPrdtMstGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnSave').unbind().click(null,	        saveEvent);   // 저장
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		
		$('#btnAddPrdtDel').unbind().click(null,	addPrdtDel); // 추가품목삭제
		$('#btnOrdPrt').unbind().click(null,		ordPrt); 	 // 발주서출력
		$('#btnSaveNote').unbind().click(null,		saveNote);   // 비고 저장
		
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
		});		<%--매입처 팝업 찾아가기--%>
		
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
		$('#buyNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findBuyMst(this); break; // enter
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
		$('#ordQty').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : prdtOrdAdd(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initSalesPrdtMstGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '품목코드'
           			, '매입처명'
           			, '랙번호'
           			, '품명'
           			, '규격'
           			, '단위'
           			, '전월출고량'
           			, '당월출고량'
           			, '주간출고량'
           			, '하남재고'
           			, '여주재고'
           			, '재고일수'
           			, '발주량'
           			, 'BOX수량'
           			, '입고요청일'
           			, '단가'
           			, '공급가'
           			, '부가세'
           			, '합계'
           			
           			, 'box당수량'
           			, '규격유무'
           			, '발주일자'
           			, '매입처코드'
           			, '창고코드'
           			, '작업자id'
           			, '부가세유무'
           			, '순번'
           			, '추가데이터'
           			, '발주비고'
           			, '전표번호'
           			
           			, 'Row상태'
           			, '원 발주 수량'
           ],
			colModel:[
						{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:100 , align:"center"}
		            	,{name:'buyNm'			, index:'buyNm'			, sortable:true, width:150, align:"left"}
		            	,{name:'lackNo1'		, index:'lackNo1'		, sortable:true, width:80 , align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:150 , align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:80 , align:"center"}
		            	,{name:'ordUnit'		, index:'ordUnit'		, sortable:true, width:60 , align:"center"}
		            	,{name:'prevSalesQty'	, index:'prevSalesQty'	, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'} 
		            	,{name:'curSalesQty'	, index:'curSalesQty'	, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'} 
		            	,{name:'weekSalesQty'	, index:'weekSalesQty'	, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'} 
		            	,{name:'haStkQty'		, index:'haStkQty'		, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'} 
		            	,{name:'yeoStkQty'		, index:'yeoStkQty'		, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'} 
		            	,{name:'stkDay'			, index:'stkDay'		, sortable:true, width:90 , align:"right"}
		            	,{name:'ordQty'			, index:'ordQty'		, sortable:true, sorttype:'number', width:90 , align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour}
		            	,{name:'boxQty'			, index:'boxQty'		, sortable:true, width:90 , align:"right"}
		            	,{name:'buyDt'			, index:'buyDt'			, sortable:true, width:120 , align:"center", formatter:fmtSetGridDatePicker, unformat: unfmtGetGridDatePicker}
		            	                                               
		            	,{name:'cost'			, index:'cost'			, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'}
		            	,{name:'pureAmt'		, index:'pureAmt'		, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'}
		            	,{name:'vatAmt'			, index:'vatAmt'		, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'}
		            	,{name:'totAmt'			, index:'totAmt'		, sortable:true, sorttype:'number', width:90 , align:"right", formatter:'integer'}
		            	
		            	,{name:'qtyBox'			, index:'qtyBox'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'stdYn'			, index:'stdYn'			, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'ordDt'			, index:'ordDt'			, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'buyCd'			, index:'buyCd'			, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'whCd'			, index:'whCd'			, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'workId'			, index:'workId'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'vatYn'			, index:'vatYn'			, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'buySeq'			, index:'buySeq'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'addDataRow'		, index:'addDataRow'	, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'ordNote'		, index:'ordNote'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'buySlipNo'		, index:'buySlipNo'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	
		            	,{name:'gridFlag'		, index:'gridFlag'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
		            	,{name:'ordQtyOrg'		, index:'ordQtyOrg'		, sortable:true, hidden:true, width:90 , align:"right", formatter:'integer'}
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            ids = $("#tabList").jqGrid('getDataIDs');
	            if(ids.length > 0){
	            	if($("#buyNm").val() != ''){
	            		$('#ordNote').val($("#tabList").jqGrid('getRowData', ids[0]).ordNote);
	            	}	            	 
	            }
	            
	            
	            var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
	            
	            var gridData = $("#tabList");
	            ids = gridData.jqGrid('getDataIDs');
	            
//	            if(ids.length > 0){
//	           	    $('#remarks1').val($("#tabList").jqGrid('getRowData', ids[0]).remarks1);
//	            }
	             
			    $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color

				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'prdtCd' : '합계' });
			    
				var sum_ordQty = gridData.jqGrid('getCol','ordQty', false, 'sum');			
				gridData.jqGrid("footerData", "set", { 'ordQty': '<span style="color:red; font-weight:bold; align="right">' + sum_ordQty + '</span>'}, false); // The 'false' here is crucial
								
			    var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
				gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
				
				var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
				gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
				
				var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
				gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
			
	            
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
		         	
		            $("#tabList").unbind('keyup').keyup(function (e) {
		            	setGridOrdData();
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
	            }else{
	         	   var allRows = $(this).jqGrid('getDataIDs');
	        	   
	        	   for(var i = 0; i < allRows.length; i++){
						var cl = allRows[i];
						var rowData = $(this).jqGrid('getRowData', cl);
						
						$(this).find("#"+cl).find("input[name='buyDt']").datepicker();
	        	   }
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
			gridview: true,														// 그리드 속도
			viewrecords: true,													// 하단에 1/1 또는 데이터가없습니다 추가
			rownumbers:true,													// rowNumber 표시여부
			autowidth : true,
			shrinkToFit : false,		
			loadonce : true,
			//cellEdit : true,
			footerrow : true,
			sortorder : "desc"
		});
	}
	
	function setGridOrdData(){
		var ids = $("#tabList").jqGrid('getDataIDs');

	    for(var i=0; i<ids.length;i++){
	    	var rowData = $("#tabList").jqGrid('getRowData', ids[i]);

	    	var ordQty = parseFloat(rowData.ordQty);
		    var qtyBox = rowData.qtyBox;
		    var strQtyBox = parseInt(ordQty / qtyBox);
			
		    var strEa = ordQty % qtyBox;
		    if(strEa % 1 != 0){
		    	strEa = strEa.toFixed(2);
		    }
			
			var ordUnit = rowData.ordUnit;
		    var stdYn = rowData.stdYn;

			if(stdYn == "2"){
				$("#tabList").jqGrid('setCell',ids[i],'boxQty',String(ordQty).concat(" ", ordUnit));
			} else {
				if(ordQty < qtyBox){
					$("#tabList").jqGrid('setCell',ids[i],'boxQty',String(ordQty).concat(" ", ordUnit));
				} else if(strEa == 0){
					if(qtyBox > 1){
						$("#tabList").jqGrid('setCell',ids[i],'boxQty',String(strQtyBox).concat(" BOX"));
					} else{
						$("#tabList").jqGrid('setCell',ids[i],'boxQty',String(ordQty).concat(" ", ordUnit));
					}
				} else{
					$("#tabList").jqGrid('setCell',ids[i],'boxQty',String(strQtyBox).concat(" BOX/", strEa, ordUnit));
				}
			}
			
			var cost = parseInt(rowData.cost);
	        var vatYn = rowData.vatYn;
	        
	        var pureAmt = Math.round(ordQty * cost);
			var vatAmt = Math.round(ordQty * cost * 0.1);
	        
	        if(vatYn == "2"){
	        	$("#tabList").jqGrid('setCell',ids[i],'pureAmt',pureAmt);
	        	$("#tabList").jqGrid('setCell',ids[i],'vatAmt',0);
	        	$("#tabList").jqGrid('setCell',ids[i],'totAmt',pureAmt);
	        } else{
	        	$("#tabList").jqGrid('setCell',ids[i],'pureAmt',pureAmt);
	        	$("#tabList").jqGrid('setCell',ids[i],'vatAmt',vatAmt);
	        	$("#tabList").jqGrid('setCell',ids[i],'totAmt',pureAmt + vatAmt);
	        }
	    }
	}
	
	/*매입처 찾기팝업 호출 */
	function findBuyMst() {	
		commonBuyMstFindLayer('','',$("#buyNm").val(), setBuyMstFindLayer);
	}
	
	/*매입처 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {
		if (data != null){
			$("#buyCd").val(data.buyCd);
			$("#buyNm").val(data.buyNm);
			$("#telNo").val(data.telNo);
			$("#salesPrNm").val(data.salesPrNm);
			$("#salesPrHp").val(data.salesPrHp);
			$("#setlCon").val(data.setlCon);
			
			$('#prdtNm').focus();
		}
	}
	
	/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonPrdtMstFindLayer('','',$("#prdtNm").val(), $("#buyCd").val(), setPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
		if (data != null){
			$("#prdtCd").val(data.prdtCd);
			$("#prdtNm").val(data.prdtNm);
			
			$('#ordQty').focus();
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
        
		$("#ordDt").datepicker('setDate','c');
		$("#buyDt").datepicker('setDate','c+1d');
		$("#buyNm").attr("readonly", false);
		
		$("#tabList").jqGrid('clearGridData');
		initSalesPrdtMstGrid();
	}

	
	/* 조회 */
	function searchEvent(event){
		
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});
		
		searchInfo['useYn'] = $('#useYn').val();

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
				
		console.log(searchInfo);
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/br/buy/buyOrderReg_selList.json'/>",
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
		
		//setGridOrdData();
		
		$("#tabList").editCell(0, 0, false);

		if(!confirm("저장하시겠습니까?")){return;}

		var saveData = {'buyOrderRegList'  : $("#tabList").getRowData()};
		
		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/br/buy/buyOrderReg_insList.json'/>",
 
		    data : JSON.stringify(saveData),
	 	    success : function(data){
	   	 	
	 	    	if(data.msgCd =="0") {
					alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
					
					//$('#ordYn').val('10').trigger('change');
	      	    	
	   				searchEvent();			//  재조회
			   	}else{
					alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
				}
      	    }
		});

	}
	
	/* 비고 저장 */
	function saveNote(event){
		
		$("#tabList").editCell(0, 0, false);
		
		if($('#buyCd').val() == ''){
			alert('비고란 저장 시 매입처 선택은 필수입니다.');
			return;
		}

		if(!confirm("저장하시겠습니까?")){return;}

		var saveData = 
		{'ordNote'  : $('#ordNote').val()
		  , 'buyCd'   : $('#buyCd').val()
		};

		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/br/buy/buyOrderReg_insOrdNote.json'/>",
 
		    data : JSON.stringify(saveData),
	 	    success : function(data){
	   	 	
	 	    	if(data.msgCd =="0") {
					alert("비고가 정상 저장 되었습니다.");
					searchEvent();
			   	}else{
					alert("처리중 오류가 발생하였습니다. Code : "+msgCd+ "Message : "+message)
				}
      	    }
		});

	}
	
	/* 수량에서 엔터 눌렀을 경우 해당 품목의 데이터를 가지고 와서 그리드에 보여줌 */
	function prdtOrdAdd(event){
		if(!$('#buyNm').val()) {
			alert('매입처를 입력하세요.');
			$('#buyNm').focus();
			return false;
		}		
		if(!$('#prdtNm').val()) {
			alert('품목을 입력하세요.');
			$('#prdtNm').focus();
			return false;
		}
		if(!$('#ordQty').val()) {
			alert('발주 수량을 입력하세요.');
			$('#ordQty').focus();
			return false;
		}

		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url:"<c:url value='/app/br/buy/buyOrderReg_selPrdtAddList.json'/>",
			data: JSON.stringify(searchInfo),
			success : function(data){
				
				//blankRow 제거
				$("#tabList").jqGrid('delRowData', 'blankRow');
				
				rowId = $("#tabList").getGridParam("reccount");
				
				var inputOrdQty =  $("#ordQty").val();
				
				var ids = $("#tabList").jqGrid('getDataIDs');
				var rowData = $("#tabList").jqGrid('getRowData', ids[rowId]);
				
				$("#tabList").jqGrid('addRowData', rowId+1, data.buyOrderRegVo, 'first');
				$("#tabList").jqGrid('setCell',rowId+1,'ordQty',inputOrdQty);
				$("#tabList").jqGrid('setCell',rowId+1,'addDataRow',"A");
				
// 				setGridOrdData(data.buyOrderRegVo, inputOrdQty, rowId+1);
				
				//하나의 품목 입력 후 input 박스 초기화
				prdt_clear();
			}
			
		});
	}
	
	
	
	function addPrdtDel(event){
		
		var ids = $("#tabList").jqGrid('getDataIDs');

        for(var i=0; i<ids.length;i++){
        	var rowData = $("#tabList").jqGrid('getRowData', ids[i]);
        	var rowId = $.trim(ids[i]);

    		if(rowData.addDataRow == "A"){
    			$("#tabList").jqGrid('delRowData', rowId);
            }
        }
	}
	
	
	/* 발주서 출력 */
	function ordPrt(event){

		var searchInfo = {};
		
		$("#searchOrdDt").val($("#ordDt").val().replaceAll('-',''));
 		$("#searchBuyDt").val($("#buyDt").val().replaceAll('-',''));
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});
		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url:"<c:url value='/app/br/buy/ordListPrint_selCnt.json'/>",
			data: JSON.stringify(searchInfo),
			success : function(data){
				
				if(data.rtnValue01 >= "1") {
					
					var url = "<c:url value="/app/br/buy/ordListPrint" />";
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
				}else{
					alert("출력한 내역이 없습니다.");
				}
			}
			
		});
	}
	
	/* 엑셀  */
	function excelEvent(){
		
		var ordYn = $("#ordYn option:selected").val();	
		
		$("#searchOrdDt").val($("#ordDt").val().replaceAll('-',''));
		$("#searchBuyDt").val($("#buyDt").val().replaceAll('-',''));
		
		if(ordYn=="10"){
			$('#searchForm').attr("action", "<c:url value='/app/br/buy/buyOrderReg_selExcelDown'/>").submit();
		}else{
			$('#searchForm').attr("action", "<c:url value='/app/br/buy/buyOrderNotReg_selExcelDown'/>").submit();
		}
				
		
	}
	
	function buy_clear(event){
		
		if($("#buyNm").attr("readonly") != 'readonly'){
			$('#buyNm').val('');
			$('#buyCd').val('');
			$('#prdtNm').val('');
			$('#prdtCd').val('');
			$('#ordQty').val('');
		}	
	}
	
	function prdt_clear(event){
		$('#prdtNm').val('');
		$('#prdtCd').val('');
		$('#ordQty').val('');
	}
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post">
	<input type="hidden" id="searchOrdDt" name="searchOrdDt""/>
	<input type="hidden" id="searchBuyDt" name="searchBuyDt""/>
	<input type="hidden" id="searchordYn" name="searchordYn""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>매입처발주등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처발주등록 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="30%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="30%">
			<col width="100">
			<col width="20%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text"   id="buyNm"  name="buyNm" style="width:80%;" onclick="buy_clear()" autocomplete="off">
						<input type="hidden" id="buyCd"  name="buyCd" >
						<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">발주유무</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="ordYn" objName="ordYn" parentID="M007" defName="${commonall}" /> <%--전체 --%>
					</td>
				<th><label for="sele2">대표전화번호</label></th>
					<td>
						<input type="text"   id="telNo"  name="telNo" style="width:30%;" readonly="readonly">
						<input type="text"   id="salesPrNm"  name="salesPrNm" style="width:30%;" readonly="readonly">
						<input type="text"   id="salesPrHp"  name="salesPrHp" style="width:30%;" readonly="readonly">
					</td>
				<th><label for="sele2">결제조건</label></th>
					<td>
						<input type="text"   id="setlCon"  name=""setlCon""  readonly="readonly">
					</td>
			</tr>
			<tr>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;" onclick="prdt_clear()" autocomplete="off">
						<input type="hidden" id="prdtCd"  name="prdtCd" >
						<button id="btnPrdtCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">수량</label></th>
					<td>
						<input type="text"   id="ordQty"  name="ordQty" style="width:40%; text-align:right;" autocomplete="off" >
					</td>
				<th><label for="sele2">발주일자</label></th>
					<td>
						<input type="text" class="dt" id="ordDt" name="ordDt"  readonly="readonly" style="width: 100px !important;" />
					</td>
				<th><label for="sele2">입고요청일자</label></th>
					<td>
						<input type="text" class="dt" id="buyDt" name="buyDt"  readonly="readonly" style="width: 100px !important;" />
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<form id="dataForm" name="dataForm" method="post">
	<fieldset>
	<table style="width: 100%" summary="" class=type1>
		<colgroup>
			<col width="100">
			<col width="80%">
			<col width="10%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">비고</label></th>
					<td style="border-right:none;">
						<input type="text"   id="ordNote"  name="ordNote" autocomplete="off" >
					</td>
					<td style="border-left:none;">
						<html:button id="btnSaveNote"   name="btnSaveNote"  auth="save" value="비고 저장"  />			<%-- 저장 --%>
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 추가저장정보 end ----------------------------------------- -->


 <ul style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div  style="padding-left: 0px; ">
 		<div class="tit_area">
			<h2 class="subhead">매입처발주등록</h2>
			<div class="btn_l">
				사용여부: <html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="9002" defName="${commonall}" /> &nbsp;  								
				<html:button id="btnAddPrdtDel" 	name="btnAddPrdtDel"	auth="select"  value="추가품목삭제"  />
				<html:button id="btnOrdPrt" 		name="btnOrdPrt"   		auth="select"  value="발주서출력"  />
				&emsp;&emsp;&emsp;&emsp;&emsp;
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
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
 </ul>


<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />


<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />


<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>