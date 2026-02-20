<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

	/* onLoad or key event */
	$(document).ready(function($){

		$('#btnDetailLogClose').unbind().click(null, 		closeDataLogFindLayer); 	// 닫기버튼

		initAccessDetailLog();

		//grid resize
		$(window).bind('resize', function() {
		    try{
		        // width
		        // 그리드의 width를 div 에 맞춰서 적용
		        $('#tabListFindAccessDetailLog').setGridWidth($('#gridAccessDetailLogcontainer').width()); //Resized to new width as per window

		        // height
		        var height = $(window).height()-$('#grid1container')[0].offsetTop;
		        if(height > 255)
		            $('#tabList1').setGridHeight(height-130);
		    }catch(e){}
		}).trigger('resize');
	});


	/* Layer Event Creat */
	function _setCommonDetailLogLayerEvent(){

		/* 레이어 활성화 */
		$('#common_access_Log_layer').show();
		$('#opacity').show();

		$("#jajaeSearchFrm #jajaecd").focus();

		/* 레이어 드레그 Event */
		$("#common_access_Log_layer").draggable({
			handle: $("h1")
			,cancle: $("a.close")
			,containment: "window"
			,scroll: false
		});


		/* 레이어 닫기버튼 Click Event */
		$('#common_access_Log_layer a.close').click(function(e){
			$("#accessDetailLogSearchFrm input[name='logIdx']").val('');
		});
	}


	/*레이어 close*/
	function closeDataLogFindLayer(){
		$('#common_access_Log_layer').hide();
	 	$('#opacity').hide();
	}


	/* 레이어호출 */
	function _eventDetailLogFindLayer(logIdx){

		$("#accessDetailLogSearchFrm input[name='logIdx']").val(logIdx);

		_setCommonDetailLogLayerEvent();	//Layer Event Creat

		_eventDetailLogSearch();	//조회 event

	}

	var gExistFindGrid = false;
	function initAccessDetailLog() {
		if (gExistFindGrid) {
			$("#gExistFindGrid").jqGrid('clearGridData').trigger("reloadGrid"); // dataList 초기화
			return;
		}

		gExistFindGrid = true;


		$("#tabListFindAccessDetailLog").jqGrid({
            datatype: "local",                                                // 보내는 데이터 타입
        	data:[],
            // 헤더에 들어가는 이름
            colNames:[
            	  '<spring:message code="app.manager.accesspgmlist.head.logidx" />'			<%-- 접근로그인덱스	--%>
            	 ,'<spring:message code="app.manager.accesspgmlist.head.no" />'      		<%-- 순번			--%>
            	 ,'<spring:message code="app.manager.accesspgmlist.head.dtlcommand" />'     <%-- 로그상세내역	--%>
            	 ,'<spring:message code="app.manager.accesspgmlist.head.regdt" />'      	<%-- 접근일자		--%>
            	 ,'<spring:message code="app.manager.accesspgmlist.head.regid" />'     		<%-- 접근아이디		--%>
				],
        	colModel:[
					  {name:'logIdx'     			, index:'logIdx'   		, sortable:true    , width:100  , align:"center"}
        	         ,{name:'seq'     				, index:'seq'   		, sortable:true    , width:50   , align:"center"}
					 ,{name:'dtlCommand'     		, index:'dtlCommand'   	, sortable:true    , width:380  , align:"center"}
        	         ,{name:'regDtFmt'     			, index:'regDt'  		, sortable:true    , width:120  , align:"center"}
        	         ,{name:'regId'     			, index:'regId'    		, sortable:true    , width:120  , align:"center"}
        	         ],
            gridComplete : function() {
            	 var colCount = $(this).getGridParam("colNames").length;
                 $("#blankAccessDetailLogRow td:nth-child(2)").attr("colspan", colCount);
                 $(window).resize();
            },
            loadComplete: function() {
              	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
                if ($(this).getGridParam("records")==0) {

                    var firstColName = $(this).getGridParam("colModel")[1].name;
                    var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                    $(this).addRowData("blankAccessDetailLogRow", msg);
                }

                $(window).trigger('resize');
            },
            loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
            	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
                return false;
            },
            onSelectRow : function(id, status, e) {
            	   if (id == 'blankAccessDetailLogRow') {
                       return;
                   }
            },

			rowNum:500,
		    sortname: 'seq',	// load시 정렬의 기준이 되는 컬럼명
			sortorder: "desc",				// desc/asc
			height:250,
		    autowidth: true,
		   	gridview	: true,				// 그리드 속도
		    viewrecords : true,				// page 하단 푸터 삽입
            scroll 		: true,
            rownumbers:true,        		// 행번호 표시여부
            shrinkToFit: false,
            loadonce:false					// reload 여부이며 true 로 설정하면 한번만 데이터를 받아오고 그 다음부터는 데이터를 받아오지 않는다.
		});

	}


	//조회 event
	function _eventDetailLogSearch(){

		var searchInfo = {};
		$.ajaxSetup({
			contentType: "application/x-www-form-urlencoded"
		});

		$('#accessDetailLogSearchFrm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		$("#tabListFindAccessDetailLog").jqGrid('setGridParam',{
			url:"<c:url value='/app/mgr/manager/sysAccessPgm_selDtlLogList'/>",
	        datatype: "json",
	        postData: searchInfo,
	        mtype:'POST',

	        jsonReader : {
	            root:  "layoutList",
	            repeatitems: false,
	        }
	    }).trigger("reloadGrid");
	}

</script>

<div class="pop_layer_new" id="common_access_Log_layer" style="margin:-260px 0 0 -358px;width:830px; display:none;">
    <h1><c:out value="Access Log List"></c:out></h1>
	<a id="btnDetailLogClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         <form id="accessDetailLogSearchFrm" name="accessDetailLogSearchFrm" method="post">
         <input type="hidden" name="logIdx" id="logIdx">
			<!-- 검색조건 start ----------------------------------------------------->
	    	<fieldset class="search_area">
		    	<legend>상세로그</legend> <%-- 상세로그 --%>
				<table style="width:100%;" class="type2" >
					<caption>상세로그</caption> <%-- 상세로그  --%>
	                  	<colgroup>
	                  		<col width="110px">
	                  		<col width="*">
	                  	</colgroup>
				</table>
	          	</fieldset>
	         <!-- 검색조건 end ------------------------------------------------>
	         </form>
	         <div class="tit_area" >
               	<h2 class="subhead"><c:out value="Access Log "></c:out></h2>
			 </div>

             <!-- centent Lis -------------------------->
             <div id="gridAccessDetailLogcontainer" class="gridcontainer">
      			   <table id="tabListFindAccessDetailLog" ><tr><td></td></tr></table>
  			  </div>

		</div>   <!-- END pop_section -->
	</div>	<!-- END pop_content -->

</div>	<!-- END pop_layer_new -->
