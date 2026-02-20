<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<%-- <c:set var="tody"  value="${now}" /> --%>
<%-- <fmt:formatDate value='${now}' pattern='yyyy-MM-dd' var="tody"/> --%>

<script type="text/javascript">

	/* onLoad or key event */
	$(document).ready(function($){

		$('#btnAccessIpClose').unbind().click(null, 		closeAccessIpFindLayer); 	// 닫기버튼
		$('#btnAccessIpSearch').unbind().click(null, 		_eventAccessIpSearch); 		// 검색

		$("#accessIpSearchFrm input[name='accessDateFr']").datepicker();
		$("#accessIpSearchFrm input[name='accessDateTo']").datepicker();

		//$('#tabListFindAccessIp').setGridWidth($('#gridAccessIpcontainer').width()); //Resized to new width as per window
		initAccessIp();

		//grid resize
		$(window).bind('resize', function() {
		    try{
		        // width
		        // 그리드의 width를 div 에 맞춰서 적용
		        $('#tabListFindAccessIp').setGridWidth($('#gridAccessIpcontainer').width()); //Resized to new width as per window

		        // height
		        var height = $(window).height()-$('#grid1container')[0].offsetTop;
		        if(height > 255)
		            $('#tabList1').setGridHeight(height-130);
		    }catch(e){}
		}).trigger('resize');
	});


	/* Layer Event Creat */
	function _setCommonAccessIpFindLayerEvent(){




		/* 레이어 활성화 */
		$('#common_access_find_layer').show();
		$('#opacity').show();

		$("#jajaeSearchFrm #jajaecd").focus();

		/* 레이어 드레그 Event */
		$("#common_access_find_layer").draggable({
			handle: $("h1")
			,cancle: $("a.close")
			,containment: "window"
			,scroll: false
		});


		/* 레이어 닫기버튼 Click Event */
		$('#common_access_find_layer a.close').click(function(e){
			$("#accessIpSearchFrm input[name='access_memberCd']").val('');
		});
	}


	/*레이어 close*/
	function closeAccessIpFindLayer(){
		$('#common_access_find_layer').hide();
	 	$('#opacity').hide();
	}


	/* 레이어호출 */
	function _commonAccessIpFindLayer(memberCd){

		/*조건 초기화 -----------------*/
		//$("#accessIpSearchFrm input[name='accessDateFr']").val("${tody}");
		//$("#accessIpSearchFrm input[name='accessDateTo']").val("${tody}");
		$("#accessIpSearchFrm input[name='access_memberCd']").val(memberCd);


		_setCommonAccessIpFindLayerEvent();

		_eventAccessIpSearch();

	}

	var gExistFindAccessIpGrid = false;
	function initAccessIp() {
		if (gExistFindAccessIpGrid) {
			$("#tabListFindAccessIp").jqGrid('clearGridData').trigger("reloadGrid"); // dataList 초기화
			return;
		}

		gExistFindAccessIpGrid = true;


		$("#tabListFindAccessIp").jqGrid({
            datatype: "local",                                                // 보내는 데이터 타입
        	data:[],
            // 헤더에 들어가는 이름
            colNames:[
            	  '<spring:message code="app.manager.memberviewlist.head.code" />'				<%-- 사용자고유번호   --%>
				  ,'<spring:message code="app.manager.memberviewlist.head.userid" />'			<%-- 사용자 아이디   --%>
				  ,'<spring:message code="app.manager.memberviewlist.head.usernm" />'			<%-- 사용자 명      --%>
				  ,'<spring:message code="app.manager.memberviewlist.head.accessdatelocale" />'	<%-- 접속시간       --%>
				  ,'<spring:message code="app.manager.memberviewlist.head.accessIp" />'			<%-- 접속아이피      --%>
				],
        	colModel:[
					  {name:'memberCd'     			, index:'memberCd'   		, sortable:true    , width:80  , align:"center"}
        	         ,{name:'userId'     			, index:'userId'   			, sortable:true    , width:150  , align:"left"}
					 ,{name:'userNm'     			, index:'userNm'   			, sortable:true    , width:100  , align:"left"}
        	         ,{name:'accessDateLocale'     	, index:'accessDateLocale'  , sortable:true    , width:120  , align:"center"}
        	         ,{name:'accessIp'     			, index:'accessIp'    		, sortable:true    , width:120  , align:"left"}
        	         ],
            gridComplete : function() {
            	 var colCount = $(this).getGridParam("colNames").length;
                 $("#blankAccessIpRow td:nth-child(2)").attr("colspan", colCount);
                 $(window).resize();
            },
            loadComplete: function() {
              	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
                if ($(this).getGridParam("records")==0) {

                    var firstColName = $(this).getGridParam("colModel")[1].name;
                    var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                    $(this).addRowData("blankAccessIpRow", msg);
                }

                $(window).trigger('resize');
            },
            loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
            	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
                return false;
            },
            onSelectRow : function(id, status, e) {
            	   if (id == 'blankAccessIpRow') {
                       return;
                   }
            },

			rowNum:500,
		    sortname: 'accessDateLocale',	// load시 정렬의 기준이 되는 컬럼명
			sortorder: "desc",				// desc/asc
			height:250,
		    autowidth: true,
		   	gridview	: true,				// 그리드 속도
		    viewrecords : true,				// page 하단 푸터 삽입
            scroll 		: true,
            rownumbers:true,        		// 행번호 표시여부
            loadonce:true					// reload 여부이며 true 로 설정하면 한번만 데이터를 받아오고 그 다음부터는 데이터를 받아오지 않는다.
		});

	}


	//조회버튼 event
	function _eventAccessIpSearch(){

		if(!$("#accessIpSearchFrm input[name='access_memberCd']").val() ) return;

		var searchInfo = {};
		$.ajaxSetup({
			contentType: "application/x-www-form-urlencoded"
		});

		$('#accessIpSearchFrm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		$("#tabListFindAccessIp").jqGrid('setGridParam',{
			url:"<c:url value='/app/mgr/manager/mgrMember_selAccessIpList'/>",
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

<div class="pop_layer_new" id="common_access_find_layer" style="margin:-330px 0px 0px -390px;width:616px; display:none;">
    <h1>Access IP List</h1>
	<!-- <a id="btnAccessIpClose" href="javascript:void(0);" class="close" ><img src="/images/app/common/btn_close_none.gif" alt="닫기"></a> -->
	<a id="btnAccessIpClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
            <form id="accessIpSearchFrm" name="accessIpSearchFrm" method="post">
			<input type="hidden" name="access_memberCd" id="access_memberCd">
			<!-- 검색조건 start ----------------------------------------------------->
	    	<fieldset class="search_area">
		    	<legend>접속아이피검색</legend> <%-- 접속아이피검색 --%>
				<table style="margin-top: 10px;" class="type2" >
					<caption>접속아이피검색</caption> <%-- 접속아이피검색  --%>
	                  	<colgroup>
	                  		<col width="110px">
	                  		<col width="*">
	                  	</colgroup>
					<tbody id="_search">
	                 <tr>
	                   	<th><label for="sele2"><c:out value="Access date"></c:out></label></th> <%-- 접속일자--%>
	                   	<td>
	                   		<input type="text" style="width: 20%;margin-right: 4px;" name="accessDateFr" id="accessDateFr"  value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />" >
	                       		~
	                       	<input type="text" style="width: 20%;margin-right: 4px;" name="accessDateTo"   id="accessDateTo"    value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />" >
	                   	</td>
	                 </tr>
	                 </tbody>
				</table>
	          	</fieldset>
	         </form>
	         <!-- 검색조건 end ------------------------------------------------>

	         <div class="tit_area" >
               	<h2 class="subhead"><c:out value="Access IP"></c:out> </h2>
				<div class="btn_l">
					<html:button id="btnAccessIpSearch" 	auth="select"/>
				</div>
			 </div>

             <!-- centent Lis -------------------------->
             <div id="gridAccessIpcontainer" class="gridcontainer">
      			   <table id="tabListFindAccessIp" ><tr><td></td></tr></table>
  			  </div>
             <!-- detail -------------------------->
		</div>
	</div>

</div>
