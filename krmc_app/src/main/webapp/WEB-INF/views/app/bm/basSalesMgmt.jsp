<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		$("#warrExpDt").datepicker();
		
		initSaleMgmtGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    	searchEvent); 	// 검색
		$('#btnSave').unbind().click(null,	            saveEvent); 	// 저장
		$('#btnInit').unbind().click(null,	            doClearForms); 	// 신규  
		$('#btnExcel').unbind().click(null,	    		excelEvent); 	// 엑셀
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#container1List').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#container1List').setGridHeight(height-70);	//GRID  LIST
				    $('#salesMgmtData').height(height-105);  			//TALBE DATA
			    }
		        else if(height < 300){
			        $('#container1List').setGridHeight(height-130);	//GRID  LIST
			        $('#salesMgmtData').height(height-165);  			//TALBE DATA
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#bossNm, #salesNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : searchEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		/*-----------------------------------------------------------------*/
		
	});


	/* 마스터 데이터  그리드 초기화 */
	function initSaleMgmtGrid() {
		$("#container1List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '매출처명'
            			, '전화번호'
            			, '구분'
            			
            			, '매출처코드'
            			, '사용유무'
           ]
			,colModel:[
		                 {name:'salesNm'		, index:'salesNm'		, sortable:true, width:150 , align:"left"}
		            	,{name:'telNo'			, index:'telNo'			, sortable:true, width:80, align:"center"}
		            	,{name:'hqClassNm'		, index:'hqClassNm'		, sortable:true, width:50, align:"center"}
		            	
		            	,{name:'salesCd'		, index:'salesCd'		, sortable:true, hidden:true, width:50 , align:"center"}
		            	,{name:'useYn'			, index:'useYn'			, sortable:true, hidden:true, width:50 , align:"center"}
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

           ,rowNum:1000
           ,loadui : "disable"
           ,gridview:    true
           ,pager: '#pageList'
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
		
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#container1List").jqGrid('clearGridData');
		
		document.getElementById("masterForm").reset();
		
		$("#container1List").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/salesMgmt_selList.json'/>",
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

	/* 상세조회 */
	function detailEvent(searchInfo){

		if(!searchInfo || !searchInfo.salesCd) return;

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/bm/baseinfo/salesMgmt_selDetail.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {

				if(data != null) {
					
					$.form = $("#salesMgmtData");
					
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
					
					$("#salesMgmtData").find("select[name='hqClass']").val(data.hqClass);
					$("#salesMgmtData").find("select[name='areaClass']").val(data.areaClass);
					$("#salesMgmtData").find("select[name='useYn']").val(data.useYn);
					$("#salesMgmtData").find("select[name='hqCd']").val(data.hqCd);
					$("#salesMgmtData").find("select[name='salesClass']").val(data.salesClass);
					$("#salesMgmtData").find("select[name='dlvDtClass']").val(data.dlvDtClass);
					$("#salesMgmtData").find("select[name='salesPrCd']").val(data.salesPrCd);
					$("#salesMgmtData").find("select[name='carCd']").val(data.carCd);
					$("#salesMgmtData").find("select[name='taxYn']").val(data.taxYn);
					$("#salesMgmtData").find("select[name='crSalesYn']").val(data.crSalesYn);
					$("#salesMgmtData").find("select[name='ordDdlnYn']").val(data.ordDdlnYn);
					$("#salesMgmtData").find("select[name='profitClass']").val(data.profitClass);
					$("#salesMgmtData").find("select[name='balDisplayYn']").val(data.balDisplayYn);
					$("#salesMgmtData").find("select[name='priceDisplayYn']").val(data.priceDisplayYn);
					$("#salesMgmtData").find("select[name='newSalesPrCd']").val(data.newSalesPrCd);
				}
			}
		});
	}
	

	/*저장 버튼 이벤트 */
	function saveEvent(){

		var dataInfo = {};
		
		if($("#subsidyRate").val() == ""){
			$("#subsidyRate").val('0')
		}

		if($("#creLim").val() == ""){
			$("#creLim").val('0')
		}
		
		$('#masterForm').find('input , select').map(function() {
			 dataInfo[this.name] = $(this).val();
		});
		
		var regex = /[^0-9]/g;
		dataInfo["startDt"] 	= dataInfo.startDt.replace(regex, 		""); 	//숫자만 추출 
		dataInfo["endDt"] 		= dataInfo.endDt.replace(regex, 		""); 	//숫자만 추출
		dataInfo["warrExpDt"] 	= dataInfo.warrExpDt.replace(regex, 	""); 	//숫자만 추출
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/bm/baseinfo/salesMgmt_insInfo.json" />',
		      data : JSON.stringify(dataInfo),
		      success : function(data){            

		    	if(data == null) msg = '<spring:message code="message.error.process" />'; <%--"처리중 오류가 발생했습니다.";--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="message.success.process" />'; 	<%--"정상적으로 처리되었습니다.";--%>
	            	dataInfo['salesCd'] = data.rtnValue01;
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
	
	/* 엑셀  */
	function excelEvent(){
		
		$('#searchForm').attr("action", "<c:url value='/app/bm/baseinfo/salesMgmt_selExcelDown'/>").submit();

	}

	/*입력필드 초기화(신규,조회 Event)*/
	function doClearForms(){
        $('form').each(function() {
            this.reset();
        });
		
        //Grid 초기화
		$("#container1List").jqGrid('clearGridData');
	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post">
<sec:csrfInput/>
	<fieldset>
	<legend>매출처 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처 검색조건</caption>
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
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="salesNm" name="salesNm">
					</td>
				<th><label for="sele2">대표자/담당자</label></th>
					<td>
						<input type="text" id="bossNm" name="bossNm">
					</td>
				<th><label for="sele2">사용유무</th>
					<td>
						<html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="M009" defName="${commonall}" /> <%--전체 --%>
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 분류 서브타이틀 및 이벤트 버튼 start  --------------------- -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 30%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매출처마스터관리</h2>
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
 		<form name="masterForm" id="masterForm"  >
   		<div class="tit_area">
			<h2 class="subhead">매출처 등록/수정</h2>
			<div>
				<html:button id="btnInit" 		auth="insert" /> 	<%-- 신규 --%>
				<html:button id="btnSearch" 	auth="select" /> 	<%-- 조회 --%>
				<html:button id="btnSave"       auth="save"   /> 	<%-- 저장 --%>
				<html:button id="btnExcel"      auth="excel"   /> 	<%-- 엑셀 --%>
			</div>
		</div>
		<div id="salesMgmtData" class="datatableType1">
		<table class="type1">
			<colgroup>
				<col width="120"/>
				<col width="*"/>
				<col width="100"/>
				<col width="*"/>
				<col width="100"/>
				<col width="*"/>
				<col width="100"/>
				<col width="*"/>
			</colgroup>
			<tbody>
			<tr>
				<th>본사/지사구분</th>
					<td>
						<html:codeTag comType="SELECT" objId="hqClass" objName="hqClass" parentID="M004" /> 
					</td>				
				<th>지역</th>
					<td>
						<html:codeTag comType="SELECT" objId="areaClass" objName="areaClass" parentID="M017" /> 
					</td>
				<th>운영여부</th>
					<td colspan=3>
						<html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="M009" /> 
					</td>
			</tr>
			<tr>
				<th>본사</th>
					<td colspan=3>
						<html:codeTag comType="SELECT" 	dataType="HQCD"	objId="hqCd" objName="hqCd" parentID="Y"  defName="${commonall}"/>
					</td>
				
				<%-- 2025.08.22  추가 --%>
				<th>매장수</th>
					<td>
						<input type="text"   id="storeCnt"    name="storeCnt" > 
					</td>
				<th>에상매출액</th>
					<td>						 
						<input type="text"   id="expSalesAmt"    name="expSalesAmt" 	value="0" 	style="text-align:right">
					</td>
				<%-- 2025.08.22  추가  end --%>
				
						
			</tr>
			<tr>
				<th>매출처코드</th>
					<td>
						<input type="text"   id="salesCd"    name="salesCd" maxlength="5" readonly="readonly">
					</td>
				<th>매출처명</th>
					<td colspan=2>
						<input type="text"   id="salesNm"    name="salesNm">
					</td>
				<th>매출처약명</th>
					<td colspan=2>
						<input type="text"   id="salesSNm"    name=salesSNm> 
					</td>			
			</tr>
			<tr>
				<th>대표자명</th>
					<td>
						<input type="text"   id="bossNm"    name="bossNm">
					</td>
				<th>대표번호</th>
					<td >
						<input type="text"   id="telNo"   name="telNo" >
					</td>
				<th>FAX번호</th>
					<td>
						<input type="text" id="faxNo"  name="faxNo">
					</td>
				<th>가상계좌번호</th>
					<td >
						<input type="text"   id="vrAcctNo"   name="vrAcctNo" >
					</td>
			</tr>
			<tr>
				<th>구매담당자명</th>
					<td>
						<input type="text"   id="purPrNm"    name="purPrNm">
					</td>
				<th>H.P</th>
					<td>
						<input type="text"   id="purPrHp"    name="purPrHp">
					</td>
				<th>마감담당자명</th>
					<td>
						<input type="text"   id="closePrNm"    name="closePrNm" >
					</td>
				<th>H.P</th>
					<td>
						<input type="text"   id="closeHp"    name="closeHp" >
					</td>
			</tr>
			<tr>
				<th>사업자번호</th>
					<td >
						<input type="text"   id="bsnsNum"   name="bsnsNum" >
					</td>
				<th>법인/주민번호</th>
					<td>
						<input type="text"   id="corpNum"    name="corpNum" >
					</td>
				<th>업태</th>
					<td>
						<input type="text"   id="busiCon"    name="busiCon" >
					</td>
				<th>종목</th>
					<td>
						<input type="text"   id="busiType"    name="busiType">
					</td>
			</tr>
			<tr>
				<th>센터담당자명</th>
					<td>
						<input type="text"   id="centerPrNm"    name="centerPrNm" >
					</td>
				<th>H.P</th>
					<td>
						<input type="text"   id="centerPrHp"    name="centerPrHp">
					</td>
				<th>센터전화번호</th>
					<td>
						<input type="text"   id="centerPrTel"    name="centerPrTel">
					</td>
				<th>Fax번호</th>
					<td>
						<input type="text"   id="centerFax"    name="centerFax">
					</td>
			</tr>
			<tr>
				
				<th>사업자우편번호</th>
					<td>
						<input type="text"   id="zipCd"    name="zipCd" >
					</td>
				<th>사업자주소</th>
					<td colspan=3>
						<input type="text"   id="addr1"    name="addr1" >
					</td>
					<td colspan=2>
						<input type="text"   id="addr2"    name="addr2" >
					</td>
			</tr>
			<tr>
				<th>EMAIL</th>
					<td>
						<input type="text"   id="email"    name="email" >
					</td>
				<th>배송지주소</th>
					<td colspan=3>
						<input type="text"   id="dlvAddr1"    name="dlvAddr1">
					</td>
					<td colspan=2>
						<input type="text"   id="dlvAddr2"    name="dlvAddr2">
					</td>
			</tr>
			<tr>
				<th>매출처구분</th>
					<td>
						<html:codeTag comType="SELECT" objId="salesClass" objName="salesClass" parentID="M013" />
					</td>
				<th>출고일설정</th>
					<td>
						<html:codeTag comType="SELECT" objId="dlvDtClass" objName="dlvDtClass" parentID="M018" />
					</td>
				<th>개설 영업사원</th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="SALESPRCD"	objId="newSalesPrCd" objName="newSalesPrCd" parentID="Y"  defName="${commonall}"/>
					</td>	
					
				<th>담당영업사원</th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="SALESPRCD"	objId="salesPrCd" objName="salesPrCd" parentID="Y"  defName="${commonall}"/>
					</td>
				
			</tr>
			<tr>
				<th>세금계산서발행</th>
					<td>
						<html:codeTag comType="SELECT" objId="taxYn" objName="taxYn" parentID="M009" />
					</td>
				<th>결제조건</th>
					<td>
						<input type="text"   id="setlCon"    name="setlCon" >
					</td>
				<th>결제일자</th>
					<td>
						<input type="text"   id="setlDt"    name="setlDt" >
					</td>
				<th>여신한도</th>
					<td>
						<input type="text"   id="creLim"    name="creLim" value="0" style="text-align:right">
					</td>
			</tr>
			<tr>
				<th>외상매출</th>
					<td>
						<html:codeTag comType="SELECT" objId="crSalesYn" objName="crSalesYn" parentID="M009" />
					</td>
				<th>발주마감시간</th>
					<td>
						<input type="text"   id="ordDdlnTm"    name="ordDdlnTm" >
					</td>
				<th>발주마감체크</th>
					<td>
						<html:codeTag comType="SELECT" objId="ordDdlnYn" objName="ordDdlnYn" parentID="M009" />
					</td>
					
				<th>배송차량</th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="CAR"	objId="carCd" objName="carCd" parentID="Y"  defName="${commonall}"/>
					</td>	
					
					
<!-- 				<th>이익율적용</th> -->
<!-- 					<td> -->
<%-- 						<html:codeTag comType="SELECT" objId="profitClass" objName="profitClass" parentID="M019" /> --%>
<!-- 					</td>				 -->
			</tr>
			<tr>
				<th>거래시작일</th>
					<td>
						<input type="text"   id="startDt"    name="startDt" style="width:70%;">
					</td>
				<th>거래종료일</th>
					<td>
						<input type="text"   id="endDt"    name="endDt"  style="width:70%;">
					</td>
				<th>거래처코드</th>
					<td>
						<input type="text"   id="cltSalesCd"    name="cltSalesCd" >
					</td>
				<th>장려비율</th>
					<td>
						<input type="text"   id="subsidyRate"    name="subsidyRate"  value="0.00" style="text-align:right">
					</td>
			</tr>
			<tr>
				<th>미수잔액표시유무</th>
					<td>
						<html:codeTag comType="SELECT" objId="balDisplayYn" objName="balDisplayYn" parentID="M022" />
					</td>
				<th>단가표시유무</th>
					<td>
						<html:codeTag comType="SELECT" objId="priceDisplayYn" objName="priceDisplayYn" parentID="M009" />
					</td>
					
				<th>보증금액</th>
					<td>
						<input type="text"   id="warrAmt"    	name="warrAmt" 	value="0" style="text-align:right">
					</td>
				<th>보증만기일</th>
					<td>
						<input type="text"   id="warrExpDt"    name="warrExpDt"	style="width:70%;">
					</td>		
		</tr>
			<tr>
				<th>비고</th>
					<td colspan=7>
						<input type="text"   id="remarks"    name="remarks" >
					</td>
			</tr>
			<tr>
				<th>등록자</th>
					<td>
						<input type="text"   id="regNm"    name="regNm" readonly="readonly">
					</td>
					<td colspan=2>
						<input type="text"   id="regDt"    name="regDt" readonly="readonly">
					</td>
				<th>수정자</th>
					<td>
						<input type="text"   id="modNm"    name="modNm" readonly="readonly">
					</td>
					<td colspan=2>
						<input type="text"   id="modDt"    name="modDt" readonly="readonly">
					</td>
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