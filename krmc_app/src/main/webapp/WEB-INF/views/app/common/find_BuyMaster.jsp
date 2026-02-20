<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var comBuyMstCallbackFnc = null;

/* Layer Event Creat */
function setFindBuyMstFindLayerEvent(){

	/* 레이어 활성화 */
	$('#find_buymst_find_layer').show();
	if($('#opacity').css("display") == "none") {
		$('#opacity').show();
		$('#opacity').attr("style", "height: 190%;");
	}

	/* 레이어 드레그 Event */
	$("#find_buymst_find_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "window"
		,scroll: false
	});
	
	document.getElementById('find_buyNm').focus();

	/* 레이어 닫기버튼 Click Event */
	$('#find_buymst_find_layer a.close').click(function(e){
		closeFindBuyLayer();
	});

	$('#find_buymst_find_layer #btnBuySearch_find').unbind().click(null, eventBuySearchFind); 	// 조회버튼

	//조건 입력필드 enter key이벤트 --------------
// 	$('#find_buyNm, #find_bsnsNum, #find_bossNm, #find_salesPrNm').unbind().keydown(function(e) {
// 		switch(e.which) {
//     	case 13 : eventBuySearchFind(this); break; // enter
//     	default : return true;
//     	}
//     	e.preventDefault();
//    	});

	initBuyMst();
	$('#find_buymst_dataListbody').bind('resize', function() {
	    try{
	        // width
	        // 그리드의 width를 div 에 맞춰서 적용
        	$('#find_buymst_dataListbody').setGridHeight(300);
	        $('#find_buymst_dataListbody').setGridWidth($('#find_buymst_find_layer #gridcontainer').width()); //Resized to new width as per window
	    }catch(e){}
	}).trigger('resize');
}
function initBuyMst() {

	$("#find_buymst_dataListbody").jqGrid({
        datatype: 'local',
        data: [],

     // 헤더에 들어가는 이름
        colNames:[
				 '매입처코드'
				,'매입처명'
				,'사업자번호'
				,'대표자명'
				,'대표전화'
				,'영업담당자명'
				,'영업담당자전화'
				,'우편번호'
				,'우편주소'
				,'상세주소'

				,'매입처명 약어'
				,'법인번호'
				,'마감담당자'
		        ,'마감담당자 휴대폰'
		        ,'물류센터 담당자 휴대폰'
		        ,'물류센터 FAX'
				,'업태'
				,'업종'
				,'센터 우편번호'
				,'센터 주소'
				
				,'센터 주소 상세'
				,'이메일'
				,'결제조건'
				,'결제일자'
				,'은행예금주'
				,'은행명'
				,'은행계좌번호'
				,'장려금유무'
				,'송금수수료구분'
				,'외상매입여부'
				
				,'거래시작일'
				,'사용유무'
        ],

        colModel:[
             {name:'buyCd'		   	, index:'BUY_CD'			, sortable:true		, width:100, 	align:"center"	}	/*매입처코드*/
            ,{name:'buyNm'   		, index:'BUY_NM'			, sortable:true		, width:200, 	align:"left"	}	/*매입처명*/
            ,{name:'bsnsNum'  	    , index:'BSNS_NUM'			, sortable:true		, width:100, 	align:"center"	}	/*사업자번호*/
            ,{name:'bossNm'  	    , index:'BOSS_NM'			, hidden:true, sortable:true	, width:100, 	align:"center"	}	/*대표자명*/
            ,{name:'telNo'  	    , index:'TEL_NO'			, hidden:true, sortable:false	, width:100, 	align:"center"	}	/*대표전화*/
            ,{name:'salesPrNm'  	, index:'SALES_PR_NM'		, sortable:false	, width:100, 	align:"left"	}	/*영업담당자명*/
            ,{name:'salesPrHp'  	, index:'SALES_PR_HP'		, sortable:false	, width:100, 	align:"center"	}	/*영업담당자전화*/
            ,{name:'zipCd'  	    , index:'ZIP_CD'			, hidden:true, sortable:false	, width:80, 	align:"left"	}	/*우편번호*/
            ,{name:'addr1'  	    , index:'ADDR_1'			, hidden:true, sortable:false	, width:120, 	align:"left"	}	/*우편주소*/
            ,{name:'addr2'  	    , index:'ADDR_2'			, hidden:true, sortable:false	, width:150, 	align:"left"	}	/*상세주소*/

            ,{name:'buy_sNm'  	    , index:'BUY_S_NM'			, hidden:true		}	/*매입처명 약어*/
            ,{name:'corpNum'  	    , index:'CORP_NUM'			, hidden:true		}	/*법인번호*/
            ,{name:'closePrNm'  	, index:'CLOSE_PR_NM'		, hidden:true		}	/*마감담당자*/
            ,{name:'closePrHp'  	, index:'CLOSE_PR_HP'		, hidden:true		}	/*마감담당자 휴대폰*/
            ,{name:'centerPrHp'  	, index:'CENTER_PR_HP'		, hidden:true		}	/*물류센터 담당자 휴대폰*/
            ,{name:'centerFax'  	, index:'CENTER_FAX'		, hidden:true		}	/*물류센터 FAX*/
            ,{name:'busiCon'  	    , index:'BUSI_CON'			, hidden:true		}	/*업태*/
            ,{name:'busiType'  	    , index:'BUSI_TYPE'			, hidden:true		}	/*업종*/
            ,{name:'centerZipCd'  	, index:'CENTER_ZIP_CD'		, hidden:true		}	/*센터 우편번호*/
            ,{name:'centerZipAddr' 	, index:'CENTER_ZIP_ADDR'	, hidden:true		}	/*센터 주소*/
            
            ,{name:'centerDtlAddr' 	, index:'CENTER_DTL_ADDR'	, hidden:true		}	/*센터 주소 상세*/
            ,{name:'email' 			, index:'EMAIL'				, hidden:true		}	/*이메일*/
            ,{name:'setlCon'  	    , index:'SETL_CON'			, hidden:true		}	/*결제조건*/
            ,{name:'setlDt'  	    , index:'SETL_DT'			, hidden:true		}	/*결제일자*/
            ,{name:'bankDep'  	    , index:'BANK_DEP'			, hidden:true		}	/*은행예금주*/
            ,{name:'bankNm'  	    , index:'BANK_NM'			, hidden:true		}	/*은행명*/
            ,{name:'bankAccNum'  	, index:'BANK_ACC_NUM'		, hidden:true		}	/*은행계좌번호*/
            ,{name:'subsidyYn'  	, index:'SUBSIDY_YN'		, hidden:true		}	/*장려금유무*/
            ,{name:'remFeeClass'  	, index:'REM_FEE_CLASS'		, hidden:true		}	/*송금수수료구분*/
            ,{name:'crPurYn'  		, index:'CR_PUR_YN'			, hidden:true		}	/*외상매입여부*/
            
            ,{name:'startDt'  		, index:'START_DT'			, hidden:true		}	/*거래시작일*/
            ,{name:'useYn'  	   	, index:'USE_YN'			, hidden:true		}	/*사용유무*/
            		
        ],
        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	 var colCount = $(this).getGridParam("colNames").length;
             $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
             
            // 키 이벤트 처리
  			$("#find_buymst_find_layer").unbind('keydown').keydown(function (e) {
  				
  			    var grid = $("#find_buymst_dataListbody");
  			    var selectedRowId = $("#find_buymst_dataListbody").getGridParam("selrow");

  			    // 현재 선택된 행이 없는 경우, 첫 번째 행을 선택합니다.
  			    if (!selectedRowId) {
  			        selectedRowId = grid.getDataIDs()[0];
  			    }

  			    var nextRowId;
  			    var prevRowId;
  			    var rowIds = grid.getDataIDs();

  			    // 현재 선택된 행의 인덱스를 찾습니다.
  			    var currentIndex = rowIds.indexOf(selectedRowId);
  			     
  			    switch(e.which) {
  			        case 38: // 위쪽 화살표 키
  			            prevRowId = rowIds[currentIndex - 1];
  			            if (prevRowId) {
  			                e.preventDefault();
  			                grid.jqGrid('setSelection', prevRowId, false);
  			                scrollToVisibleRow(grid, prevRowId);
  			            }
  			            break;
  			        case 40: // 아래쪽 화살표 키
  			            nextRowId = rowIds[currentIndex + 1];
  			            if (nextRowId) {
  			                e.preventDefault();
  			                grid.jqGrid('setSelection', nextRowId, false);
  			                scrollToVisibleRow(grid, nextRowId);
  			            }
  			            break;
  			        case 13: // 엔터키
  			        	e.preventDefault();
  			        
  			        	thisRowId = rowIds[currentIndex];
  			        	
  			        	var rowdata = grid.getRowData(thisRowId);
  	  		            
  			        	if(!rowdata) return;
  	  		        	
  			        	if(comBuyMstCallbackFnc) comBuyMstCallbackFnc(rowdata);
  	  		            closeFindBuyLayer();
			            
  	  		            break;
  			    }
  			});

  			// 함수 정의: 선택된 행이 보이도록 스크롤 이동
  			function scrollToVisibleRow(grid, rowId) {
  			    var $grid = grid;
  			    var $selectedRow = $grid.find("#" + rowId);
  			    var $gridWrapper = $grid.closest(".ui-jqgrid-bdiv");
  			    var gridTop = $gridWrapper.scrollTop();
  			    var gridBottom = gridTop + $gridWrapper.height();
  			    var rowTop = $selectedRow.position().top;
  			    var rowBottom = rowTop + $selectedRow.outerHeight();

  			    // 선택된 행이 스크롤 범위를 벗어날 경우 스크롤 조정
  			    if (rowTop < gridTop) {
  			        // 위로 스크롤
  			        $gridWrapper.scrollTop(rowTop);
  			    } else if (rowBottom > gridBottom) {
  			        // 아래로 스크롤
  			        $gridWrapper.scrollTop(rowBottom - $gridWrapper.height());
  			    } else {
  			        // 스크롤 범위 내에 있는 경우
  			        // 스크롤을 조정하지 않음
  			    }
  			}
        },
        loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            }
            $("#find_buymst_dataListbody").trigger('resize');
            
            // 조회데이터가 한건일 결우 첫번째 로우 onSelectRow  처리  --
            if ($(this).getGridParam("records")==1) {
	   			var rowIds   = $('#find_buymst_dataListbody').jqGrid('getDataIDs');
	   			$('#find_buymst_dataListbody').jqGrid("setSelection", rowIds[0]);
   		 	}
            
            ids = $(this).jqGrid("getDataIDs");
            
            if(ids && ids.length > 0){
           	 $(this).jqGrid("setSelection", ids[0], false);
            }
            
        },
        loadError:function(xhr, status, error) {
        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            return false;
        },
        onSelectRow : function(id, status, e) {
        	if (id == 'popBlankRow') return;

            var rowdata = $(this).getRowData(id);
            if(!rowdata) return;

        	if(comBuyMstCallbackFnc) comBuyMstCallbackFnc(rowdata);
            closeFindBuyLayer();
        },
        //onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
        ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
        	var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
        }

        ,rowNum:100
        ,loadui : "disable"
        ,gridview:    true
        ,pager: '#pageList2'
        ,sortname: 'salesNm'
        ,sortorder: 'asc'
        ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
        ,viewrecords: true
        ,scroll : false
        ,rownumbers:true
        ,loadonce:true
        ,autowidth:true
    });
}
/*레이어 close*/
function closeFindBuyLayer(){

	$("#find_buymst_dataListbody").clearGridData();
	$('#find_buymst_find_layer').hide();
	comBuyMstCallbackFnc = null;

	if($(".pop_layer_new:visible").length == 0) {
		$('#opacity').hide();
	}
}

/* 레이어호출 */
function commonBuyMstFindLayer(affiliate,hedoYn,searchVal,callbackFnc){

	/*조건 초기화 -----------------*/
	$('#find_buymst_find_form').find('input').map(function() {
		$(this).val('');
	});

	//setTbodyInit("find_buymst_dataListbody");	// dataList 초기화
	/*----------------------------*/

	setFindBuyMstFindLayerEvent();

	if(searchVal) {
		$("#find_buyNm").val(searchVal);
		eventBuySearchFind();
	}

	$("#find_buyNm").focus();
	comBuyMstCallbackFnc = callbackFnc;
}

function eventBuySearchFind() {
	
// 	if(!$('#find_buyNm').val() && !$('#find_bsnsNum').val() && !$('#find_bossNm').val() && !$('#find_salesPrNm').val()) {
<%-- 		alert('<spring:message code="app.common.ComCompanyLayer._eventSearchFind.a1" />');조회조건을 하나이상 입력하세요! --%>
// 		$('#find_buyNm').focus();
// 		return;
// 	}

	var searchInfo = {};
	$('#find_buymst_find_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val();
	});

	$("#find_buymst_dataListbody").jqGrid('setGridParam',{
        url:"<c:url value='/app/common/findBuyMaster' />"	
       ,datatype: "json" 
       ,postData: searchInfo
       ,mtype:'POST'
       ,ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' }  //ajax contentType 설정
       ,page: 1
       ,loadBeforeSend: function(jqXHR) {
        		jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
        }
	   ,jsonReader : {
		   root:  "resultList",	//조회결과 데이터
			page: "page",			//현재 페이지	
			total: "pagePerCount",	//총 페이지 수
			records: "totalCount",	// 전체 조회된 데이터 총갯수 
			repeatitems: false
        }
 	}).trigger("reloadGrid");
}

</script>

<div class="pop_layer_new" id="find_buymst_find_layer"
	style="margin:-220px 0 0 -228px;width:900px; display:none;">
    <h1>매입처 찾기</h1> 
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         	<div class="tit_area">
                <h2>매입처 선택 </h2>
            </div>
            <form id="find_buymst_find_form" name="find_buymst_find_form" action="" method="post" autocomplete="off">
            	<table class="type1">
					<colgroup>
						<col width="150"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th>매입처명</th> <%--업체명 --%>
							<td>
								<input type="text" id="find_buyNm" name="find_buyNm" style="width: 70%;" />
							</td>
						</tr>
					</tbody>
				</table>
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2>매입처 현황</h2> <%--매입처 현황 --%>
					<div>
						<html:button id="btnBuySearch_find" auth="select"  /> <%--조회 --%>
					</div>
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer" class="gridcontainer">
                	<table id="find_buymst_dataListbody" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList2"></div>
            	<!-- centent List -------------------------->
		</div>
	</div>
</div>

