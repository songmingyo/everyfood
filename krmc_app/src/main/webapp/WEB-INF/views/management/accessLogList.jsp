<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">


$(document).ready(function($){


	$(function () {
		  var token = $("meta[name='_csrf']").attr("content");
		  var header = $("meta[name='_csrf_header']").attr("content");
		  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		  });
		 searchAccessLogList();
	});

	$("#btnAccessSearch").unbind().click(null, 		searchAccessLogList);
	$("#btnClearSqlCache").unbind().click(null, 	clearCacheAccessLogList);


	// 페이지번호 Click Event 발생시 조회함수 호출 */
	$('#paginationArea ul[id="pagination"]').delegate( "a", "click", function() {
		$('#paginationArea span[id="page"]').text($(this).attr('link')); //페이지번호설정
		searchAccessLogList();	//페이지번호 변경 후 조회 실행
	});

});


function doDelete(logIdx){
	location.href='<c:url value="/mgr/accessLogDelete" />'+'?logIdx='+logIdx;
}

function clearCacheAccessLogList(){
	var codeInfo = {}
	// 저장 이벤트 json 실행---------------------------
	$.ajax({
		  contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : '<c:url value="/mgr/accessLog_delClearCache"/>',
	      data : JSON.stringify(codeInfo),
	      timeout : 100000,
	      success : function(data){
	    	console.log("SUCCESS: ", data);
			console.log(JSON.stringify(data, null, 4));

	      },
		  done : function(e) {
			console.log("DONE");
		  }
	});
	//-----------------------------------------------
}



function searchAccessLogList(){

	/* AccessLog search parameter  --------------*/
	var paramInfo		=	{};
	$('#formSearch').find('input, select').map(function() {
		paramInfo[this.name] = $(this).val();
	});
	/*-------------------------------------------*/


	/* AccessLog page parameter  --------------*/
	var page		 =	$('#paginationArea span[id="page"]').text();
	var pageRowCount =	$('#paginationArea :radio[name="pageRowCount"]:checked').val();

	paramInfo["page"]				=	page				;
	paramInfo["pageRowCount"]		=	pageRowCount		;
	/*-------------------------------------------*/


	 $.ajax({
			url: '<c:url value="/mgr/accessLog_selSearchList"/>',
	      	data: JSON.stringify(paramInfo),
	      	type: "POST",

	      	beforeSend: function(xhr) {
	      	    xhr.setRequestHeader("Accept", "application/json");
	        	xhr.setRequestHeader("Content-Type", "application/json");
	      	},
	      	timeout : 100000,
	      	success: function(data) {
	      		setLoadDataList(data);
	      	}
	 });
}


function setLoadDataList(json){
	var data 	 = json.list;		// 조회 데이터
	var page 	 = json.pagination;	// 페이징 정보

	$('#paginationArea ul li').remove();
	$('#paginationArea span[id="pagingInfo"]').empty();


	$("#accessLogList tr").remove();
	if(!data || data.length <= 0)  {
		setTbodyNoResult("accessLogList");//There is no query results.
		return;
	}

	$("#accessLogListTemplate").tmpl(data).appendTo("#accessLogList");

	$('#paginationArea span[id="pagingInfo"]').text("Showing "+page.startRowNum+" to "+page.endRowNum+" of "+page.totalCount+" entries");
	$('#paging-tmpl').tmpl(page.list).insertBefore($('#pagination').empty()).appendTo('#pagination');
}


function setTbodyNoResult(listTable,cols, msg){
	defaultMsg = "There is no query results.";

	if(!cols) cols = $("#"+listTable).parents("table:first").find("tr:first th").length;
	if(!msg) msg = defaultMsg;
	$("#"+listTable).append('<tr><td colspan="'+cols+'" class="tdm" align=center height=30>'+msg+'</span></td></tr>');
}

</script>

<script id="accessLogListTemplate"  type="text/x-jquery-tmpl">
 <tr>
	<td class="text-center">\${rnum}</td>
	<td class="text-center">\${title}</td>
	<td class="text-center">\${typeFlageNm}</td>
	<td class="text-left">\${resPgmUrl}</td>
	<td class="text-center">\${modDt}</td>
 </tr>
</script>


<!-- Paging 공통 -->
<script type="text/x-jquery-tmpl" id="paging-tmpl" >
{%if pageNumber == '<<'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-double-left"></i></a></li>
{%elif pageNumber == '<'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-left"></i></a></li>
{%elif pageNumber == '>'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-right"></i></a></li>
{%elif pageNumber == '>>'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-double-right"></i></a></li>
{%else%}
	<li><a href="javascript:;"  link="\${linkPageNumber}">\${pageNumber}</a></li>
{%/if%}
</script>


<div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            User Access Log
            <small>sample List</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href='<c:url value="/" />'><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li class="active"><i class="fa fa-map-marker"></i> User Access Log</li>
          </ol>
        </section>

       <!-- Main content -->
        <section class="content">
        	<!-- Main row -->
          <div class="row">
           <!--  col (We are only adding the ID to make the widgets sortable)-->
            <section class="col-lg-12 connectedSortable">
            <div class="box box-danger">
                <div class="box-header">
                  <i class="ion ion-clipboard"></i>
                  <h3 class="box-title">ACCESS LOG LIST</h3>
                  <h6 style="margin-bottom: -5px;"><span class="text-muted"><i class="fa fa-clock-o"></i> ${now}</span>
                  <small class="text-muted pull-right">mamagement</small>
                  </h6>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
                	<button class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-footer">
	                <div class="box-header with-border">
	                	<form:form name="formSearch" id="formSearch" onsubmit="return false;" cssClass="cleanform">
						<div class="form-group">
							<div class="col-lg-6 col-md-6 col-xs-12">
								<label>Access Type </label>
								<html:codeTag comType="SELECT" objId="typeFlageCd" objName="typeFlageCd" parentID="9011" defName="ALL" />
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12">
								<label for="deptName">Program Name</label>
								<input type="text" 	id="title"		name="title"		class="form-control">
							</div>
							<div class="col-xs-12" style="margin-top: 10px;">
								<button class="btn btn-block btn-primary btn-primary" id="btnAccessSearch"	name="btnAccessSearch">
									<i class="fa fa-search"></i> Search
								</button>
							</div>
						</div>
						</form:form>
					</div>

	                <div class="box-body table-responsive">
			            <div class="row">
				        	<div class="col-sm-6 dataTables_length">
				        		<label>Show <html:codeTag comType="SELECT" classNm="form-control input-sm" width="40px;" objId="pageRowCount" objName="pageRowCount" parentID="9050" selectParam="0"  />entries</label>
				           </div>
	               			<div class="col-sm-6">
	               				<span class="pull-right"><button id="btnClearSqlCache"  class="btn btn-default btn-sm"><i class="fa fa-rocket"></i> Mapper clear Cache</button></span>
	               			</div>
               			</div>
               			<table id="ex" class="table table-bordered table-striped">
		                    <tr class="bg-black-active-cus">
		                    	<th class="text-center">No.</th>
		                    	<th class="text-center">Program Name</th>
		                    	<th class="text-center">Access Type</th>
		                    	<th class="text-center">Program URL</th>
		                    	<th class="text-center">Date</th>
		                    </tr>
	                   	 <tbody id="accessLogList" />
	                  	</table>
	                </div>
	                <div class="box-footer clearfix" id="paginationArea">
	                	<strong  class="small"><span id="page">1</span> / </strong>
	                	<span id="pagingInfo" style="margin-right:10px;"></span>
	                	<div class="box-tools pull-right small" >
	                    	<ul id="pagination" class="pagination pagination-md inline"></ul>
	                    </div>
               		</div> <!-- /.box-footer clearfix -->
                </div>
              </div><!-- /.box -->
            </section>
          </div><!-- /.content-wrapper -->

		</section>
</div>
