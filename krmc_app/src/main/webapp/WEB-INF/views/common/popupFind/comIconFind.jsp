<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

 $(document).ready(function($){
	$("#findIcon tr td").click(function(){
		setCheckIcon($(this).text())
	});
}); 

/* Layer Event Creat */
function _setIconFindLayerEvent(){
	
	/* 레이어 활성화 */
	$('#find_icon_layer').show();
	$('#opacity').show();
	
	/* 레이어 드레그 Event */
	$("#find_icon_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "parent"
		,scroll: false
	});
	
	/* 레이어 닫기버튼 Click Event */
	 $('#find_icon_layer a.close').click(function(e){
		closeIconWin();
	 });
}

function closeIconWin(){
	$('#find_icon_layer').hide();
	$('#opacity').hide();
}

function setCheckIcon(iconName){
	$("#menuIcon").val(iconName);
	$('#icontView').attr('class','fas '+iconName);
	
	closeIconWin();
}
</script>
<div  class="pop_layer_new" id="find_icon_layer" style="margin:-220px 0 0 -228px;width:700px;  display:none;">
	<h1><spring:message code="app.common.popup.label.iconFind" /></h1><!-- 아이콘찾기 -->
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	
	<div class="open_content" style="margin-top: 10px;">
		<div id="pop_section">
			<table id="findIcon" class="type1">
				<colgroup>
					<col width="25%"/>
					<col width="25%"/>
					<col width="25%"/>
					<col width="25%"/>
				</colgroup>
				<tbody>
					<tr>
						<td><i class="fas fa-comment"></i>fa-comment</td>
						<td><i class="fas fa-comments"></i>fa-comments</td>
						<td><i class="fas fa-bullhorn"></i>fa-bullhorn</td>
						<td><i class="fas fa-envelope"></i>fa-envelope</td>
					</tr>
					<tr>
						<td><i class="fas fa-dot-circle"></i>fa-dot-circle</td>
						<td><i class="fas fa-minus-square"></i>fa-minus-square</td>
						<td><i class="fas fa-plus-square"></i>fa-plus-square</td>
						<td><i class="fas fa-spinner"></i>fa-spinner</td>
					</tr>
					<tr>
						<td><i class="fas fa-database"></i>fa-database</td>
						<td><i class="fas fa-cubes"></i>fa-cubes</td>
						<td><i class="fas fa-edit"></i>fa-edit</td>
						<td><i class="fas fa-calendar-plus"></i>fa-calendar-plus</td>
					</tr>
					<tr>
						<td><i class="fas fa-calendar-minus"></i>fa-calendar-minus</td>
						<td><i class="fas fa-laptop"></i>fa-laptop</td>
						<td><i class="fas fa-box"></i>fa-box</td>
						<td><i class="fas fa-box-open"></i>fa-box-open</td>
					</tr>
					<tr>
						<td><i class="fas fa-chart-area"></i>fa-chart-area</td>
						<td><i class="fas fa-chart-bar"></i>fa-chart-bar</td>
						<td><i class="fas fa-chart-line"></i>fa-chart-line</td>
						<td><i class="fas fa-chart-pie"></i>fa-chart-pie</td>
					</tr>
					<tr>
						<td><i class="fas fa-address-card"></i>fa-address-card</td>
						<td><i class="fas fa-user-cog"></i>fa-user-cog</td>
						<td><i class="fas fa-cog"></i>fa-cog</td>
						<td><i class="fas fa-calculator"></i>fa-calculator</td>
					</tr>
					<tr>
						<td><i class="fas fa-user"></i>fa-user</td>
						<td><i class="fas fa-user-plus"></i>fa-user-plus</td>
						<td><i class="fas fa-user-secret"></i>fa-user-secret</td>
						<td><i class="fas fa-user-times"></i>fa-user-times</td>
					</tr>
					<tr>
						<td><i class="fas fa-chevron-circle-down"></i>fa-chevron-circle-down</td>
						<td><i class="fas fa-chevron-circle-left"></i>fa-chevron-circle-left</td>
						<td><i class="fas fa-chevron-circle-right"></i>fa-chevron-circle-right</td>
						<td><i class="fas  fa-chevron-circle-up"></i>fa-chevron-circle-up</td>
					</tr>
					<tr>
						<td><i class="fas fa-chevron-down"></i>fa-chevron-down</td>
						<td><i class="fas fa-chevron-left"></i>fa-chevron-left</td>
						<td><i class="fas fa-chevron-right"></i>fa-chevron-right</td>
						<td><i class="fas fa-chevron-up"></i>fa-chevron-up</td>
					</tr>
					<tr>
						<td><i class="fas fa-arrow-circle-down"></i>fa-arrow-circle-down</td>
						<td><i class="fas fa-arrow-circle-left"></i>fa-arrow-circle-left</td>
						<td><i class="fas fa-arrow-circle-right"></i>fa-arrow-circle-right</td>
						<td><i class="fas fa-arrow-circle-up"></i>fa-arrow-circle-up</td>
					</tr>
					<tr>
						<td><i class="fas fa-align-center"></i>fa-align-center</td>
						<td><i class="fas fa-align-justify"></i>fa-align-justify</td>
						<td><i class="fas fa-align-left"></i>fa-align-left</td>
						<td><i class="fas fa-align-right"></i>fa-align-right</td>
					</tr>
					<tr>
						<td><i class="fas fa-eraser"></i>fa-eraser</td>
						<td><i class="fas fa-file"></i>fa-file</td>
						<td><i class="fas fa-paste"></i>fa-paste</td>
						<td><i class="fas fa-list-alt"></i>fa-list-alt</td>
					</tr>
					<tr>
						<td><i class="fas fa-table"></i>fa-table</td>
						<td><i class="fas fa-th-large"></i>fa-th-large</td>
						<td><i class="fas fa-th-list"></i>fa-th-list</td>
						<td><i class="fas fa-th"></i>fa-th</td>
					</tr>
					<tr>
						<td><i class="fas fa-dot-circle"></i>fa-dot-circle</td>
						<td><i class="fas fa-minus-square"></i>fa-minus-square</td>
						<td><i class="fas fa-plus-square"></i>fa-plus-square</td>
						<td><i class="fas fa-spinner"></i>fa-spinner</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>