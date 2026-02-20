<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>


<spring:eval expression="@config['File.StorePath.WebService.Url']" var="webhome"/>

<script type="text/javascript">

 //<![CDATA[

  var load = true;
  $(document).ready(function(){

	$("#banners div a").mouseover(function(){ load = false });
	$("#banners div a").mouseout(function(){ load = true });
	$("#controls li ").mouseover(function(){
	load = false;

    	var rel = $(this).attr("rel");
		if ( $("#" + rel).hasClass("current") ){
			return false;
		}

    	$("#" + rel).stop(true,true).show();  /* Bug Fix, thanks Dave -> added .stop(true,true) to stop any ongoing animation */
		$(".current").fadeOut(0).removeClass("current");
		$("#" + rel).addClass("current");
		$(".active-slide").removeClass("active-slide");
		$(this).parents("li").addClass("active-slide");
		return false;
	});

   $("#controls li a").mouseout(function(){ load = true });




  });



  function banner_switch(){
	if(load === false) return;
	var next =  $('.banner.current').next('.banner').length ? $('.banner.current').next('.banner') : $('#banners .banner:first');
	$(".current").fadeOut(0).removeClass("current");
	$(next).show();
	$(next).addClass("current");
	var next_link = $(".active-slide").next("li").length ? $('.active-slide').next('li') : $('#controls li:first');
	$(".active-slide").removeClass("active-slide");
	$(next_link).addClass('active-slide');

  }


  $(function() {
	slide = setInterval( "banner_switch()", 5000 );
  });



  function set_new_interval(interval){
	 clearInterval(slide);
   slide = setInterval("banner_switch()", interval);

  }


  function fn_popupZone(zoneCd) {

	   var $dialogZone = $('#zoneLayerView').dialog({
		    modal:true,
		    autoOpen: false,
		    resizable:false,
		    width: 500,
		    close: function() {
		        $(this).dialog('close'); //this is slow
		    }
		}); //init dialog

		var searchInfo = {	'zoneCd' 	 : zoneCd};

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/common/inc/popupZoneView"/>',
		      data : JSON.stringify(searchInfo),
		      success : function(data){

		    	$("#zoneLayerView #zoneNm").html(data.ZONE_NM);
	          	$("#zoneLayerView #popCn").html(data.POP_CN);

	          	$dialogZone.dialog('open');

		      }
		});

	}


//]]>

</script>
<style>
.banner_z_c {
	position: relative;
	float: left;
	background: #fafafa;
	width:250px;
	/*	border:1px solid #ddd; */
}

.banner_z_num {
	position: absolute;
	top: 2px;
	right: 2px;
}

.banner_z_num li {
	float: left;
	font-size: 11px;
	display: inline-block;
	margin: 0 1px;
	width: 15px;
	height: 15px;
}

.banner_z_num li.b_on {
	background: url("/resources/images/pearl/common/banner_bg_on.gif")
		no-repeat;
	color: #fff;
}

.zoneLayerStyle_data {
	text-align: left;
	background-color: #fdfdfd;
	background: -webkit-gradient(linear, left top, left bottom, from(#fdfdfd),
		to(#f8f8f8));
	border-bottom-left-radius: 3px;
	border-bottom-right-radius: 3px;
	text-shadow: 0px 1px #fff;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #fff;
	color: #666;
	border: 1px solid #eeeeee;
	margin-bottom: -2px;

}



#controls{
  position:absolute;
  right:3px;
  bottom:5px;
  z-index:10;
}

#controls li { list-style:none; display:inline;}

#controls li a{
  filter:alpha(opacity=30);
  opacity:0.7;
  display:inline-block;
  font-size:0.8em;
  width:15px;
  height:15px;
  text-align:center;
  color: #666;
  background:#fff; /* Specify a fall back color */
  background:rgba(204,204,204,7);
  border:1px solid #CCC;
  outline:none;
}


#controls li.active-slide a{
	border-color:#FFF;
  	color:#FFF;
  	background-color:#333;

}

#controls li a:hover{
	border-color:#FFF;
	color: #FFF;
	background-color:#333;
}
.banner.current{display:block;}


#banners {
  font-family: Arial, sans-serif;
}

#banners .banner{
  text-align:center;
  position: absolute;
  display:none;

}

#banners img{
	border-top-left-radius: 3px;
 	border-top-right-radius: 3px;
 	border:1px solid #eeeeee;
 	border-bottom:0px;
}

#zoneLayerView{
 padding: 10px;
}
#zoneLayerView .zoneNm {
	font-weight: bolder;
	border-bottom-width:1px;
	border-bottom-color:#73739e;
}

#zoneInfo{
	border-top:1px solid #f0f0f0;
	margin-left:5px;
	margin-right:5px;
	text-align: left;
	height:20px;
	margin-top:10px;
	font-size: 0.7em;
	font-weight:normal;
	color: #939795;
}
</style>








<div class="banner_z_c">
		<ul id="controls" class="banner_z_num">
			<c:forEach var="result" items="${popupZone}" varStatus="status">
				<c:choose>
			 			<c:when test="${status.count ==  1}">
					    	<li class="active-slide" rel="bn${status.count}"><a href="javascript:void(0);"><b>${status.count}</b></a></li>
						</c:when>
						<c:otherwise>
							<li rel="bn${status.count}"><a href="javascript:void(0);"><b>${status.count}</b></a></li>
						</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>


		<div id="banners">
				<c:forEach var="result" items="${popupZone}" varStatus="status">
					<c:choose>
				 			<c:when test="${status.count ==  1}">
						    	<div class="banner current" style="display:block;" id="bn${status.count}">
							</c:when>
							<c:otherwise>
								<div class="banner" id="bn${status.count}">
							</c:otherwise>
					</c:choose>



					<c:choose>
					 		<c:when test="${result.EXP_TYPE_CD eq  '9030001' }">
					 			<a	href='${result.POP_URL}' target='_new'>
									<img onerror="this.src='<c:url value="/resources/images/pearl/common/noimage3.png"/>';" src="<c:url value='/app/common/${result.IMAGE_INFO}/loadImageCommon'/>" width=250 height=145 title="<c:out value="${result.POP_URL}"/>">
								</a>
							</c:when>
							<c:otherwise>
								<a	href="javascript:void(0);" onclick="fn_popupZone('<c:out value="${result.ZONE_CD}"/>');">
									<img onerror="this.src='<c:url value="/resources/images/pearl/common/noimage3.png"/>';" src="<c:url value='/app/common/${result.IMAGE_INFO}/loadImageCommon'/>" width=250 height=145 title="<c:out value="${result.ZONE_NM}"/>">
								</a>
							</c:otherwise>
					</c:choose>
						<div class="zoneLayerStyle_data">
							<div id="zoneTitle" style="margin-left:5px; margin-top: 5px;  color: #646866;"><i class="fa fa-comments-o"></i> <c:out value="${result.ZONE_NM}"/></div>

							<div style="margin:5px;  width: 98%; font-weight: normal;">
								<span style="font-size: 0.85em; color: #939795; text-align:left;"><c:out value="${result.SUBJECT}"/></span>
							</div>

							<div id="zoneInfo">
								<div style="float: left; margin-top:2px;"><i class="fa fa-user"></i> <c:out value="${result.USER_NM}"/></div>
								<fmt:parseDate value="${result.REG_DT}" var="parsedRegDt" pattern="yyyyMMddHHmm" />
								<div style="float: right; margin-top:2px;"><i class="fa fa-calendar"></i>  <fmt:formatDate pattern="${localeDatePattern}" value="${parsedRegDt}"   /></div>
							</div>
						</div>

			</div>
				</c:forEach>
				<c:if test="${empty popupZone}">
				<div class="zoneLayerStyle_data">
					<div style="height: 145px; text-align: center;">
						<img onerror="this.src='<c:url value="/resources/images/pearl/common/noimage1.png"/>';" src="<c:url value="/resources/images/pearl/common/popupzone_default.jpg"/>" width=248 height=145 >
					</div>
					<div style="color:#87888a; text-align: center; margin-top:10px; margin-bottom:10px; font-weight: bold;">
						<i class="fa fa-exclamation-triangle"></i> <label  style="font-size: 0.8em;">No Data Found!</label>
					</div>
				</div>
				</c:if>

</div>

<!-- 팝업 존 레이어 팝업 -->
<div id="zoneLayerView"   class="modal-dialog" role="dialog" title="POPUP-ZONE">
<div  class="zoneNm" id="zoneNm"></div>
<span id="popCn"></span>
</div>
