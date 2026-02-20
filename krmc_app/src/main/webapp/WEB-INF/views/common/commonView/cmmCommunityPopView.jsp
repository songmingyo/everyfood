<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
	$(document).ready(function(){


		//-----ㅡmodal open event
		$('#communityPopup').on('shown.bs.modal', function (e) {
			setTbodyNoResult("dataListbody", "5");
		});



	});


	/*선택된 커뮤니티 상세조회 실행 */
	function _fn_openCommunityPopup(boardIdx,boardCd){
		$('#communityPopup').modal("show");
	    var paramInfo 	= 	{};

		paramInfo["boardIdx"] =	boardIdx;	<%-- 게시판인덱스 --%>
		paramInfo["boardCd"]  =	boardCd;     <%--게시판코드   --%>
		//console.log(paramInfo);


		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url  : '<c:url value="/app/common/communityDetail" />',
			data : JSON.stringify(paramInfo),
			success : function(data){
				 if(!data && data.length <= 0) {
		    		  $('#communityPopup').modal("hide");
		    		  return;
		    	  }

		    	  $('#communityPopup span[id="boardTitle"]').text(data.BOARD_TITLE);		<%-- 게시글 제목 	--%>
		    	  $('#communityPopup span[id="userNm"]').text(data.USER_NM);				<%-- 작성자명 		--%>
		    	//  $('#communityPopup span[id="email"]').text(data.EMAIL);					<%-- 작성자 메일 	--%>
		    	  $('#communityPopup span[id="regDt"]').text(data.REG_DT_YMD);				<%-- 작성일자  	--%>
		    	  $('#communityPopup span[id="boardCount"]').text(data.BOARD_COUNT);	    <%-- 조회카운트  	--%>

		    	  /*게시구분 span 스타일 적용 ------------------------------------------------ */
		    	  var pubTypCd_class = "";
		    	  var pubTypNm = "";

		    	  if(data.IS_URGENCY_YN == 'Y') 	{
		    		  pubTypCd_class = "label label-danger";
		    		  pubTypNm       = "중요";
		    	  }else{
		    		  pubTypCd_class = "label label-default";
		    		  pubTypNm       = "일반";
		    	  }
		    	  $('#communityPopup span[id="pubTypCd"]').attr("class",pubTypCd_class );
		    	  $('#communityPopup span[id="pubTypCd"]').text(pubTypNm);			<%-- 게시구분  	--%>
		    	  /*-----------------------------------------------------------------------*/

				  $('#boardContent').html(data.BOARD_CONTENT);						<%-- 게시글 내용 	--%>
			}
		});

	}



</script>

<div class="modal fade" id="communityPopup" role="dialog">

  <div class="modal-dialog modal-lg">




    <!-- Modal content-->
    <div class="modal-content">
	    	<!-- modal header start -------------------------------------------------->
	    	<div class="modal-header ">
	        	<button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<h4 class="modal-title text-blue"><i class="fa fa-comments-o"></i> 공지사항 상세보기</h4>
	      	</div>
	      	<!-- modal header end -------------------------------------------------->

	      	<!-- modal body start -------------------------------------------------->
	      	<div class="modal-body">

				<div class="mailbox-read-info">
					<h3> <span id="boardTitle"></span></h3>
                	<h5><span id="pubTypCd"></span> From: <span id="userNm">-</span> <%--| <span id="email"></span> --%> <span class="mailbox-read-time pull-right"><span id="regDt"></span> <span id="boardCount" class='badge bg-yellow'>20</span></span>

                	</h5>
                </div><!-- /.mailbox-read-info -->
				<%--
				<div class="mailbox-controls with-border text-center">
					<div class="btn-group">
	                	<button class="btn btn-default btn-sm" data-toggle="tooltip" title="Delete"><i class="fa fa-trash-o"></i></button>
	                    <button class="btn btn-default btn-sm" data-toggle="tooltip" title="Reply"><i class="fa fa-reply"></i></button>
	                    <button class="btn btn-default btn-sm" data-toggle="tooltip" title="Forward"><i class="fa fa-share"></i></button>
	                </div><!-- /.btn-group -->
	                <button class="btn btn-default btn-sm" data-toggle="tooltip" title="Print"><i class="fa fa-print"></i></button>
                </div><!-- /.mailbox-controls -->
                --%>
                <div class="mailbox-read-message" id="boardContent">

                </div><!-- /.mailbox-read-message -->
	      	</div>
	      	<!-- modal body end -------------------------------------------------->

	      	<!-- modal footer start----------------------------------------------->
		    <div class="modal-footer">
		    	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		    </div>
		    <!-- modal footer end ------------------------------------------------>

    </div>

  </div>
</div>
