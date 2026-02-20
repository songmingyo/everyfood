<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
	
	/* Layer Event Creat */
	function _setInfoProcessLayerEvent(){
	
		/* 레이어 활성화 */
		$('#info_process_layer').show();
		$('#opacity').show();
		
		/* 레이어 드레그 Event */
		$("#info_process_layer").draggable({
			handle: $("h1")
			,cancle: $("a.close")
			,containment: "parent"
			,scroll: false
		});
		
		/* 레이어 닫기버튼 Click Event */
		$('#info_process_layer a.close').click(function(e){
			closeProcessFindLayer();
		});
		
	}
	
	/*레이어 close*/
	function closeProcessFindLayer(){
		
		$('#info_process_layer').hide();
	 	$('#opacity').hide();
	}
	
	
	
	/* 레이어호출 */
	function _infoProcessLayer(){
		
		_setInfoProcessLayerEvent();
	
	}
</script>




<div class="pop_layer_warp" id="info_process_layer" style="margin:-220px 0 0 -228px;width:900px; left:40%; display:none;">
    <h1 style="box-sizing:initial;">전자계약 업무 절차</h1>
    <spring:message code="common.close" var="commonclose"/> <%-- 닫기 --%>
	<a href="#" class="close"><i class="fa fa-times" style="color:white;"></i></a>	
	<div id="pop_content" class="open_content">
	     <div id="pop_section">   
	     	<div style="margin: 0; padding: 0; height: 450px; overflow-y: scroll; overflow-x: hidden">       
	           	<img src='/resources/images/pearl/common/manual_01.png' style='width:880px; height:680px;' />
	           	<img src='/resources/images/pearl/common/manual_02.png' style='width:880px; height:680px;' />
	           	<img src='/resources/images/pearl/common/manual_03.png' style='width:880px; height:680px;' />
	           	<img src='/resources/images/pearl/common/manual_04.png' style='width:880px; height:680px;' />
	           	<img src='/resources/images/pearl/common/manual_05.png' style='width:880px; height:680px;' />
	           	<img src='/resources/images/pearl/common/manual_06.png' style='width:880px; height:680px;' />
	           	<img src='/resources/images/pearl/common/manual_07.png' style='width:880px; height:680px;' />
	           	<img src='/resources/images/pearl/common/manual_08.png' style='width:880px; height:680px;' />
	       </div>
	     </div>   
	</div>
</div>
