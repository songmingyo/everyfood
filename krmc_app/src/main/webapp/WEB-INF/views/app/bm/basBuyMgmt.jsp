<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		
		initBuyMgmtGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    	searchEvent); 	// 검색
		$('#btnMasterSave').unbind().click(null,	    saveEvent); 	// 저장
		$('#btnMasterInit').unbind().click(null,	    doClearForms); 	// 신규
		$('#btnExcel').unbind().click(null,	    		excelEvent); 		// 신규
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#container1List').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#container1List').setGridHeight(height-70);	//GRID  LIST
				    $('#buyMgmtData').height(height-105);  			//TALBE DATA
			    }
		        else if(height < 300){
			        $('#container1List').setGridHeight(height-70);	//GRID  LIST
			        $('#buyMgmtData').height(height-165);  			//TALBE DATA
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#buyNm, #bossNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : searchEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		/*-----------------------------------------------------------------*/


		/* Form validate --------------------------------------------------*/
        $('#masterForm').validate({
	        rules: {
	       		 buyNm   		 : { required: true, maxlength: 100}
	        } 
			,messages: {
				buyNm      : {required: "<div class='validate'><i class='fa fa-info-circle'></i>매입처명을 입력하세요!<div>"}  
			}
	    });
		
		
	});


	/* 마스터 데이터  그리드 초기화 */
	function initBuyMgmtGrid() {
		$("#container1List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '매입처명'
            			, '전화번호'
            			, '사용유무'
            			, '매입처코드'
            			
           ]
			,colModel:[
		                 {name:'buyNm'			, index:'buyNm'		, sortable:true, width:100 , align:"left"}
		            	,{name:'telNo'			, index:'telNo'		, sortable:true, width:100, align:"center"}
		            	,{name:'useYn'			, index:'useYn'		, sortable:true, width:50, align:"center"}
		            	,{name:'buyCd'			, index:'buyCd'		, sortable:true, hidden:true, width:50, align:"center"}
		            
		        
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	$(window).resize();
	        }
			,loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            } else{
	            	var ids = $("#container1List").getDataIDs();
	            	
	            	$.each( ids, function(idx, rowId) {
	            		rowData = $("#container1List").getRowData(rowId) ;
		            	    
		                if ( rowData.useYn == "N" ) {
			                // 색깔 변경하기
		                    $("#container1List").setRowData(rowId, false, {color:'red'}) ;
		                }
		            });
	            	
	            	if(ids && ids.length > 0){
	        			$('#container1List').jqGrid("setSelection", ids[0]);
	        		}
	            }
	            
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

                detailEvent(rowdata);
           }

           ,rowNum:-1
           ,loadui : "disable"
           ,gridview:    true
           //,pager: '#pageList'
           ,sortname: 'buyCd'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,loadonce:true
           ,autowidth:true
		});
	}


	/* 조회 */
	function searchEvent(event){
		doClearForms(); // 입력 From  초기화 
		
		var searchInfo = {};
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#container1List").jqGrid('clearGridData');
		
		document.getElementById("masterForm").reset();
		
		$("#container1List").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/buyMgmt_selList.json'/>",
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

	/* Grid onSelectRow Event */
	function detailEvent(searchInfo){

		doClearForms(); // 입력 From  초기화 
		if(!searchInfo || !searchInfo.buyNm) return;

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/bm/baseinfo/buyMgmt_selDetail.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {

				if(data != null) {
					
					$.form = $("#buyMgmtData");

					//조회 데이터 셋팅
					$.form.find("span, input, label").each(function(){
						if(this.type != "button" && this.type != "radio"){
							dataNm = $(this).attr("name");
							tagNm = this.tagName.toUpperCase();

							settingData = data[dataNm];

							if(tagNm == "SPAN"){
								$(this).text(settingData);
							}else{
								$(this).val(settingData);
							}
						}
					});
					
					$("#buyMgmtData").find("select[name='useYn']").val(data.useYn);
					$("#buyMgmtData").find("select[name='subsidyYn']").val(data.subsidyYn);
					$("#buyMgmtData").find("select[name='remFeeClass']").val(data.remFeeClass);
					$("#buyMgmtData").find("select[name='crPurYn']").val(data.crPurYn);

				}
			}
		});
	}	

	/*저장 버튼 이벤트 */
	function saveEvent(){

		if(!$('#masterForm').valid()) return;

		var dataInfo = {};
		$('#masterForm').find('input , select').map(function() {
			 dataInfo[this.name] = $(this).val();
		});
		var regex = /[^0-9]/g;
		dataInfo["startDt"] = dataInfo.startDt.replace(regex, 	""); 	//숫자만 추출 

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/bm/baseinfo/buyMgmt_insInfo.json" />',
		      data : JSON.stringify(dataInfo),
		      success : function(data){            

		    	if(data == null) msg = '<spring:message code="message.error.process" />'; <%--"처리중 오류가 발생했습니다.";--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="message.success.process" />'; 	<%--"정상적으로 처리되었습니다.";--%>
	            	dataInfo['buyCd'] = data.rtnValue01;
	            	searchEvent(); 			// 리스트 데이터 제조회 
	            	detailEvent(dataInfo);	// 수정데이터 상세 제조회 
	            }
	            else if('duple' == data.msgCd) {
	            	msg ='<spring:message code="message.error.duplication" />'; <%-- "중복된 데이터 입니다.";--%>
	            }
	            else msg = '<spring:message code="message.error.process" />';	<%--"처리중 오류가 발생 하였습니다.";--%>
	          	alert(msg);
		      }
		});
	}
	


	/*입력필드 초기화(신규,조회 Event)*/
	function doClearForms(){
		$('#buyMgmtData').find('input, textarea, select').each(function() {
			if(this.type != "button" && this.type != "radio"){
				$(this).val("");
			}
		});

		$("#buyMgmtData").find("input[name='useYn']:radio[value='Y']").prop("checked", true);
	
	}
	
	/* 엑셀  */
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/bm/baseinfo/buyMgmList_selExcelDown'/>").submit();
//		$('#searchForm').attr("action", "<c:url value='/app/as/account/accSalesDepList_selExcelDown'/>").submit();
	}
	
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
<sec:csrfInput/>
	<fieldset>
	<legend>매출처 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">매입처명</label></th>
					<td><input type="text" id="buyNm" name="buyNm"></td>
				<th><label for="sele2">대표자/담당자명</label></th>
					<td><input type="text" id="bossNm" name="bossNm"></td>
				<th><label for="sele2">사용유무</th>
					<td>
						<html:codeTag comType="SELECT" objId="searchUseYn" objName="useYn" parentID="9002" defName="${commonall}" /> <%--전체 --%>
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 분류 서브타이틀 및 이벤트 버튼 start  --------------------- -->
<!-- 
<div class="tit_area">
	<h2>TITLE</h2>
	<div>
		<html:button id="btnInit" 	auth="insert"  /> 			<%-- 신규 --%>  
		<html:button id="btnSave" 	auth="save"   /> 			<%-- 저장 --%>
		<%-- <html:button id="btnDelete" auth="delete"  /> 			 삭제 --%>
		&nbsp;
		<html:button id="btnSearch" auth="select" /> 			<%-- 조회 --%>
	</div>
</div>
 -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 30%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매입처마스터관리</h2>
        	<div>
        	
        	</div>
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="container1List" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</LI>
 
 	<li style="float: left; width: 70%;">
 		<div  style="padding-left: 5px; " id="masterFormData">
 		<form name="masterForm" id="masterForm"  autocomplete="off" >
	   		<div class="tit_area">
				<h2 class="subhead">매입처 등록/수정</h2>
				<div>
					<html:button id="btnExcel"	 		auth="excel" /> 	<%-- Excel 다운로드 --%>
					<html:button id="btnSearch" 		auth="select" /> 	<%-- 조회 --%>
					<html:button id="btnMasterInit" 	auth="insert" /> 	<%-- 신규 --%>
					<html:button id="btnMasterSave"     auth="save"   /> 	<%-- 저장 --%>
				<%--	<html:button id="btnMasterDelete" 	auth="delete" />  	 삭제 --%>
				</div>
			</div>
			<div id="buyMgmtData" class="datatableType1">
				<table class="type1">
					<colgroup>
						<col width="100"/>
						<col width="*"/>
						<col width="100"/>
						<col width="*"/>
						<col width="100"/>
						<col width="*"/>
					</colgroup>
					<tbody>
					<tr>
						<th>매입처코드</th>
						<td><input type="text"   id="buyCd"    name="buyCd" maxlength="5" readonly="readonly"></td>
						
						<th>운영여부</th>
							<td colspan=3>
								<html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="M009" /> 
			     	
					</tr>
					<tr>
						<th>매입처명</th>
							<td>
								<input type="text"   id="buyNm"   name="buyNm" >
							</td>
						<th>매입처명약어</th>
							<td colspan=3>
								<input type="text"   id="buySNm"   name="buySNm" >
							</td>
					</tr>
					<tr>
						<th>사업자번호</th>
						<td >
							<input type="text"   id="bsnsNum"   name="bsnsNum" >
						</td>
						<th>대표전화번호</th>
						<td>
							<input type="text"   id="telNo"    name="telNo">
						</td>
							<th>Fax</th>
						<td>
							<input type="text"   id="faxNo"    name="faxNo">
						</td>
					</tr>
					<tr>
						<th>대표자</th>
						<td >
							<input type="text"   id="bossNm"   name="bossNm" >
						</td>
						<th>영업담당자명</th>
						<td>
							<input type="text"   id="salesPrNm"    name="salesPrNm" >
						</td>
						<th>H.P</th>
						<td>
							<input type="text"   id="salesPrHp"    name="salesPrHp" >
						</td>
					
					</tr>
					<tr>
						<th>법인번호</th>
						<td>
							<input type="text"   id="corpNum"    name="corpNum" >
						</td>
						<th>마감담당자명</th>
						<td>
							<input type="text"   id="centerPrNm"    name="centerPrNm" >
						</td>
						<th>H.P</th>
						<td>
							<input type="text"   id="closePrHp"    name="closePrHp" >
						</td>
			
						
					</tr>
					<tr>
						<th>업태</th>
							<td>
								<input type="text"   id="busiCon"    name="busiCon">
							</td>
						<th>센터전화번호</th>
							<td>
								<input type="text"   id="centerPrHp"    name="centerPrHp">
							</td>
						<th>Fax</th>
							<td>
								<input type="text"   id="centerFax"    name="centerFax">
							</td>
					</tr>
					<tr>
						<th>종목</th>
							<td>
								<input type="text"   id="busiType"    name="busiType">
							</td>
						<th>이메일(연락)</th>
							<td>
								<input type="text"   id="email"    name="email" >
							</td>
						<th>이메일(세금계산서)</th>
						<td>
							<input type="text"   id="emailTax"    name="emailTax" />
						</td>
		
					</tr>
					<tr>
						<th>본사우편번호</th>
						<td>
							<input type="text"   id="zipCd"    name="zipCd" >
						</td>
						 <th>본사주소</th>
						<td>
							<input type="text"   id="addr1"    name="addr1">
						</td>
						<th>주소2</th>
						<td>
							<input type="text"   id="addr2"    name="addr2">
						</td>
					
					</tr>
					<tr>
						<th>센터 우편번호</th>
						<td>
							<input type="text"   id="centerZipCd"    name="centerZipCd" >
						</td>
						<th>센터주소</th>
						<td>
							<input type="text"   id="centerZipAddr"    name="centerZipAddr" >
						</td>
							<th>주소2</th>
						<td>
							<input type="text"   id="centerDtlAddr"    name="centerDtlAddr">
						</td>
		
					</tr>
					<tr>	
						<th>결제조건</th>
						<td>
							<input type="text"   id="setlCon"    name="setlCon" >
						</td>
						<th>결제일자</th>
						<td colspan=3>
							<input type="text"   id="setlDt"    name="setlDt" style="width:45%">
						</td>
					
					</tr>
					<tr>
						<th>예금주</th>
						<td>
							<input type="text"   id="bankDep"    name="bankDep" >
						</td>
						<th>은행명</th>
						<td>
							<input type="text"   id="bankNm"    name="bankNm" >
						</td>
						<th>결제계좌번호</th>
						<td>
							<input type="text"   id="bankAccNum"    name="bankAccNum">
						</td>
					</tr>
				  	<tr>
				  		<th>장려금유무</th>
						<td>
							<html:codeTag comType="SELECT" objId="subsidyYn" objName="subsidyYn" parentID="M009" />
						</td>	
						<th>송금수수료</th>
						<td>
							   <html:codeTag comType="SELECT" objId="remFeeClass" objName="remFeeClass" parentID="M011" />
						</td>	
							<th>외상매입여부</th>
						<td>
							<html:codeTag comType="SELECT" objId="crPurYn" objName="crPurYn" parentID="M009" />
						</td>
					</tr>
					<tr>
						<th>거래시작일</th>
						<td><input type="text"   id="startDt"    name="startDt"  style="width:50%;"></td>
						<th>거래종료일</th>
						<td colspan=3><input type="text"   id=""    name=""  style="width:30%;"></td>
						
					</tr>
					<tr>
						<th>등록자</th>
						<td><input type="text"   id="regNm"    name="regNm" readonly="readonly"></td>
						<th>등록일시</th>
						<td colspan=3><input type="text"   id="regDt"    name="regDt" readonly="readonly" style="width:30%;"></td>
					</tr>
					<tr>
						<th>수정자</th>
						<td><input type="text"   id="modNm"    name="modNm" readonly="readonly"></td>
						<th>수정일시</th>
						<td colspan=3><input type="text"   id="modDt"    name="modDt" readonly="readonly" style="width:30%;"></td>
					</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>	
 	</li>
 </UL>
 
 
 
<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>