<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<title> <spring:message code="app.community.boardsave.pagetitle" /> </title> <%--게시판 내용입력--%>
<link rel="stylesheet" type="text/css" href="/resources/plugins/summernote/summernote-lite.css">
<script type="text/javascript" src="/resources/plugins/summernote/summernote-lite.js"></script>
<script type="text/javascript" src="/resources/plugins/summernote/lang/summernote-ko-KR.js"></script>
<script type="text/javascript" src="/resources/plugins/jQuery/jquery.popupWindow.js"></script>
<script type="text/javascript" src="/resources/plugins/jQuery/jquery.form-3.25.js"></script>
<script type="text/javascript" src="/resources/js/pearl/common/common-upload-popup.js"></script>


<script>
	$(document).ready(function($){
		$("[data-mask]").inputmask();
		if(__locale =="ko"){
			$("#accFromDt").inputmask("yyyy/mm/dd");
			$("#accToDt").inputmask("yyyy/mm/dd");
		}else{
			$("#accFromDt").inputmask("dd/mm/yyyy");
			$("#accToDt").inputmask("dd/mm/yyyy");
		}

		$("#alertFrDt").datepicker();
		$("#alertToDt").datepicker();

		/*
		$("#boardContent").cleditor({
	        width:800,	//editor width
	        height:200 //edithr height
	   	});
		*/
		
		
		$("#boardContent").summernote({
			height: 200,
			minHeight : 200,
			maxHeight : 200,
			focus : true,
			lang : "ko-KR",
			disableDragAndDrop:true,
			codeviewFilter: true,
			  codeviewIframeFilter: true
		});

		//$(".note-insert,.note-view").remove();
		
		$('#btnSave').click(function(e){
			eventSave();
	    	e.preventDefault();
	   	});

		$('#btnCancel').click(function(e){
			eventCancel();
	    	e.preventDefault();
	   	});

		$('#btnFile').click(function(e){
			eventFile();
	    	e.preventDefault();
	   	});

		//파일 삭제 후 화면 정리
		$("#emptyFile").hide();
		if($('#fileListbody tr:last').attr("id")=="emptyFile"){
			$("#emptyFile").show();
		}


/* 		$.validator.setDefaults({
			onkeyup:false,
			onclick:false,
			onfocusout:false,
		    showErrors:function(errorMap, errorList){
	            if(this.numberOfInvalids()) {
	                alert(errorList[0].message);
	                $(errorList[0].element).focus();
		          }
		    }
		});


		$('#saveFrm').validate({
		 	  rules: {
			 	 	 email   : { required: true, email: true }
		       ,boardTitle   : { required: true, maxlength: 30}
		 	  		 }

		    ,messages:{
					 email   : {required: "<spring:message code='app.community.boardsave.validation.m1' />",
								   email: "<spring:message code='app.community.boardsave.validation.m2' />"	}
			   ,boardTitle   : {required: "<spring:message code='app.community.boardsave.validation.m3' />",
				   				maxlength:"<spring:message code='app.community.boardsave.validation.m8' />" }
					  }

		 }); */


	});

	/*validation*/
	function validation(){
		var msg ="";
		var alertFrDt = $('#alertFrDt').val() == undefined ? "" : $('#alertFrDt').val();
		var alertToDt = $('#alertToDt').val() == undefined ? "" : $('#alertToDt').val();
		

		alertFrDt = getConvertDate(alertFrDt);			<%-- 팝업공지 시작일시--%>
		alertToDt = getConvertDate(alertToDt);			<%-- 팝업공지 마감일시--%>
		
	if("900" != "<c:out value="${mgrVO.boardCd}"/>") {			<%-- Supplier find notice작성 시 이메일 미입력으로 validation 부분 삭제--%>
		if(!$.trim($('#email').val())){
			msg = '<spring:message code="app.community.boardsave.validation.m1" />'; 		 <%--이메일을 입력해 주세요.--%>
			alert(msg);
			$('input[name="email"]').focus();
			return false;
		}
		if(!/^[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+)*@[0-9a-zA-Z-]+(\.)+([0-9a-zA-Z-]+)([\.0-9a-zA-Z-])*$/.test($('#email').val())) {
			msg = '<spring:message code="app.community.boardsave.validation.m2" />';         <%--E-mail주소 형식이 아닙니다.\n\r다시 입력해 주세요.--%>
			alert(msg);
			$('input[name="email"]').focus();
	   		return false;
	  	}
	}
		if(!$.trim($('#boardTitle').val())){
			msg = '<spring:message code="app.community.boardsave.validation.m3" />';         <%--제목을 입력하세요!--%>
			alert(msg);
			$('input[name="boardTitle"]').focus();
			return false;
		}

		if(!$.trim($('#boardContent').val())){
			msg = '<spring:message code="app.community.boardsave.validation.m4" />';<%--내용을 입력하세요!--%>
			alert(msg);
			$('#boardContent').cleditor().focus();
			return false;
		}
		if($.trim($('#alertFrDt').val()) || $.trim($('#alertToDt').val())){
			if(!$.trim($('#alertFrDt').val())){
				msg = '<spring:message code="app.community.boardsave.validation.m5" />';<%--팝업공지시작기간을 입력하세요!--%>
				alert(msg);
				$('input[name="alertFrDt"]').focus();
				return false;
			}
			else if(!$.trim($('#alertToDt').val())){
				msg = '<spring:message code="app.community.boardsave.validation.m6" />';<%--팝업공지종료기간을 입력하세요!--%>
				alert(msg);
				$('input[name="alertToDt"]').focus();
				return false;
			}
			else if(alertFrDt > alertToDt){
				msg = '<spring:message code="app.community.boardsave.validation.m7" />';<%--팝업공지기간 시작기간이 종료기간보다 늦습니다--%>
				alert(msg);
				$('input[name="alertFrDt"]').focus();
				return false;
			}

		}
		return true;
	}


	/*저장 이벤트*/
	function eventSave(){

			if(!validation())return;
			if(!filePopUpCheck())return;

			<%--저장하시겠습니까? --%>
			if(!confirm('<spring:message code="message.confirm.save" />')) return;


			var saveInfo = {};
			checkedYn();
		

			$('#saveFrm').find('input, textarea, select').map(function() {
				if(this.type == 'checkbox' || this.type == 'text' || this.name == 'boardContent' ||this.type=='hidden')
					saveInfo[this.name] = $(this).val();
			
			});
			console.log(saveInfo);
			
			$.ajax({
		      	contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      	url  : "<c:url value='/app/board/boardArticle_insData'/>",
		      	data : JSON.stringify(saveInfo),
		      	success : function(data){
		    	  	if($('#boardKindCd').val()=="9020003")eventList();
		    	  	else eventSearchView(data);
		      	}
			});
			
		
	}

	/*취소 이벤트*/
	function eventCancel(){
		if(!filePopUpCheck())return;
		if(confirm('취소하시겠습니까?')){
			var f = document.searchForm;
			var url ="<c:url value='/app/community/"+$("#boardCd").val()+"/community'/>";
			if($('#boardKindCd').val()=="9020003") url = "/web/community/"+$('#boardCd').val()+"/community";
			f.action = url;
		   	f.submit();
		}
	}

	/* 등록 후 화면 이동 */
	function eventSearchView(data){
		var boardCd   = $("#saveFrm input[name='boardCd']").val();
		var url 	  = "<c:url value='/app/community/"+boardCd+"/community?boardIdx="+encodeURIComponent(data)+"'/>";
		location.href = url;
	}

	/*FAQ등록 후 이동*/
	function eventList(){
		if(!filePopUpCheck())return;
		var f = document.searchForm;
		url = "web/community/"+$('#boardCd').val()+"/community";
		f.action = "<c:url value='/'/>"+url;
	   	f.submit();
	}

	/* 파일 등록 이벤트 */
	function eventFile(){

 		var idx = $('#fileListbody tr last').index();
		var path = "<spring:eval expression="@config['File.StorePath.AppService.Bbs']"/>";
		var param = 'limitCount='+$('#fileCount').val()+'&atchFileId=&atchType=file&dirPath='+path+'&kindCd=ABBS&returnParam=new|'+idx;
		if($('#atchFileId').val()!=""){
			param = 'limitCount='+$('#fileCount').val()+'&atchFileId='+$('#atchFileId').val()+'&atchType=file&dirPath='+path+'&kindCd=ABBS&returnParam=old|'+idx;
		}
		param = encodeURI(param);
		_setUploadWindow(param);
	}

	
// 	function _setFileUploadSendResult(data, returnParam){
// 		var idx = returnParam;
// 		var atchFileArea = $("#fileListbody")
// 		ext = data[0].orgFileNm.slice(data[0].orgFileNm.lastIndexOf(".")+1);
// 		data[0].fileTypeNm = ext.toLowerCase();
// 		data[0].count = idx;
		
// 		$("#").tmpl(data).appendTo(atchFileArea);
		
// 	}
	
	/* Popup Apply Button Click Event */
 	function _setFileUploadSendResult(data, returnParam){
		//기존에는 파일업로드 팝업에서 업로드한 데이터 값을 가져오면서 다중 파일 일경우 seq값이 전부 1 이라 각각 파일을  삭제 할수가 없었음.
		//팝업창에서 업로드 후 다시 db에서 재조회 하게끔 로직 변경.
   		//var arr = returnParam.split("|");
 		//var row = 0;
 		var str = data[0].atchFileId;

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/web/common/fileDownloadList'/>",
		      data : str,
		      success : function(result){
		 		$("#emptyFile").hide();
		 		for(var i = 0; i < result.length ; i++){
		 			$("#FileListTemplate").tmpl(result[i]).appendTo("#fileListbody");
		 		}
		 		$('#atchFileId').val(result[0].atchFileId);
		  	  }
		});
	}

	function _setFileUploadClose(){
 	}

	/*첨부파일 삭제*/
	function deleteRowDoc(obj,atchFileId,seq){
		var deleteInfo = {};
		deleteInfo['atchFileId'] = atchFileId;
		deleteInfo['seq'] = seq;
		var row = $(obj);
		if(!filePopUpCheck())return;
		if(confirm('<spring:message code="message.confirm.delete" />')){<%--삭제하시겠습니까? --%>
			$.ajax({
			      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			      url  :  "<c:url value='/app/board/boardArticle_delFile'/>",
			      data : JSON.stringify(deleteInfo),
			      success : function(data){
			    	  if(data.msgCd == "success") {
			    		  row.parent().parent().remove();
					  		if($('#fileListbody tr:last').attr("id")=="emptyFile"){
					  			$("#emptyFile").show();
					  			$('#atchFileId').val("");
			    	  		}
				  	  }

			      }
			});
		}
	}
	/*파일 업로드 팝업 실행 여부 CHECK*/
	function filePopUpCheck(){
		if(_FILE_UPLOAD_POPUP_OPEN_ == true){
			alert('<spring:message code="app.community.boardsave.filePopUpCheck.a1" />');<%--파일첨부 실행중입니다. 실행중인 파일첨부 팝업을 닫으신 후 작업 하시기 바랍니다.--%>
			return false;
		}
		return true;
	}

	/*상단공지/강조사용/게시취소 체크 확인*/
	function checkedYn(){
		if($('#boardNoticeYn').is(':checked')) $("#boardNoticeYn").val("Y");
		else  $("#boardNoticeYn").val("N");
		
		if($('#isUrgencyYn').is(':checked')) $("#isUrgencyYn").val("Y");
		else $("#isUrgencyYn").val("N");
		
		if($('#delYn').is(':checked'))$("#delYn").val("Y");
		else $("#delYn").val("N");
		
	}

	/* 날짜 초기화 */
	function remove(id) {
		var test = $(id).parent().find("input").val("");
	}
	
	/* 파일다운로드 */
	function atchFileDownload(atchFileId, seq){
		var f = document.formDownloadFile;
		f.action = "<c:url value='/web/common/"+atchFileId+"/"+seq+"/fileDownload'/>";
		f.submit();
		f.action = "";
	}

</script>


<spring:message code="common.delete" var="commondel" />
<spring:message code="common.all" var="nameall" />  <%--전체  --%>

<script id="FileListTemplate"  type="text/x-jquery-tmpl">
	<tr>
		<td class='tdms' style="padding:5px 10px; border-right:0px;">
	        <a href="javascript:;" title="\${orgFileNm}" onclick="atchFileDownload('<c:out value='\${atchFileId}'/>', '<c:out value='\${seq}'/>');">
	         	\${orgFileNm}
	      	</a>
   		</td>
		<td class='tdm' style="border-left:0px;"><input id="btnDel" type="button" class="type1_1" value="${commondel}"  onclick="javascript:deleteRowDoc(this,'\${atchFileId}','\${seq}')"/></td>
	</tr>
</script>

</head>
<body>

<input type="hidden" name="fileCount" id="fileCount" value="${mgrVO.fileCount}"/>
<input type="hidden" name="fileUploadSize" id="fileUploadSize" value="${mgrVO.fileUploadSize}"/>
<form id="saveFrm" name="saveFrm" method="post">
<sec:csrfInput/>
<input type="hidden" id="atchFileId" name="atchFileId" value="${vo.atchFileId}"/>
<input type="hidden" id="boardCd" name="boardCd" value="${mgrVO.boardCd}"/>
<input type="hidden" id="boardKindCd" name="boardKindCd" value="${mgrVO.boardKindCd}"/>
<input type="hidden" id="boardIdx" name="boardIdx" value="${vo.boardIdx}"/>
<input type="hidden" name="thread" id="thread" value="${vo.thread}">
<input type="hidden" id="updateAnswer" name="updateAnswer" value="${vo.updateAnswer}"/>
<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
	<!-- 등록/취소 버튼 start ------------------------------------>
	<div class="tit_area">
		<h2><spring:message code="app.community.boardsave.title.save" /></h2><%--글등록--%>
		<div>
			<spring:message code="common.save" var="save"/>
			<html:button id="btnSave" name="btnSave"  auth="save"  objTitle="${save}"/>
			<html:button id="btnCancel" name="btnCancel" auth="select" msgKey="button.cancle"/>
		</div>
	</div>
	<!--  등록/취소 버튼  end ----------------------------------->
	<!-- 게시물 내용 입력 start ------------------------------------>
		<table class="type1" summary="">
			<caption><spring:message code="app.community.boardsave.webacc.bdtag.table.caption.boardinput" /></caption> <%--게시물 내용 입력 --%>
			<colgroup>
				<col width="130">
				<col width="*">
				<col width="130">
				<col width="*">
			</colgroup>
			<tbody>
            	<tr>
                	<th><label for="sele2"><spring:message code="app.community.boardsave.label.usernm" /></label></th><%--작성자--%>
				<c:choose>
				<c:when test="${mgrVO.boardCd eq '900'}">		<!-- Supplier find notice 작성페이지에서 이메일 내용 X: 화면 깨짐 방지 -->
                    <td colspan="3">
				</c:when>
				<c:otherwise>
					<td>
				</c:otherwise>
				</c:choose>
                    	<c:if test="${vo.userNm==null}">
                    		${userSession.fullName}
                    	</c:if>
                    	<c:if test="${vo.userNm!=null}">
                    		${vo.userNm}
                    	</c:if>
                    </td>
				<c:if test="${mgrVO.boardCd ne '900'}">			<!-- Supplier find notice 작성 페이지에서는 이메일 정보 insert X -->
                    <th><label for="sele2">email</label></th>
                    <td>
                    	<c:if test="${vo.email==null}">
                   			<input type="text" name="email" id="email" value="${userSession.email}" title="Email"/>
                   		</c:if>
                   		<c:if test="${vo.email!=null}">
                   			<input type="text" name="email" id="email" value="${vo.email}" title="Email"/>
                   		</c:if>
                    </td>
				</c:if>
				</tr>
			
				
				<c:if test="${userSession.userType eq 'SA'}">
					<tr>
	                	<th><label for="sele2"><spring:message code="app.community.boardsave.label.contentstype" /></label></th><%--공지속성--%>
	                    <td colspan="3">
	                    <spring:message code="app.community.boardsave.label.boardnoticeyn" var="lableboardnoticeyn" /> 	<%--상단공지 --%>
	                    <spring:message code="app.community.boardsave.label.isurgencyyn" var="lableisurgencyyn" /> 		<%--강조사용 --%>
	                    <spring:message code="app.community.boardsave.label.delyn" var="labledelyn" />  <%--게시취소 --%>
							<input type="checkbox" id="boardNoticeYn" name="boardNoticeYn" title="${lableboardnoticeyn}" value="N" <c:if test="${vo.boardNoticeYn eq 'Y'}">checked</c:if><c:if test="${vo.updateAnswer eq 'A' or vo.depth ne '0'}">disabled="disabled"</c:if>> ${lableboardnoticeyn}
							<input type="checkbox" id="isUrgencyYn"   name="isUrgencyYn"   title="${lableisurgencyyn}"   value="N" <c:if test="${vo.isUrgencyYn eq 'Y'}">checked</c:if><c:if test="${vo.updateAnswer eq 'A' or vo.depth ne '0'}">disabled="disabled"</c:if>>    ${lableisurgencyyn}
							<input type="checkbox" id="delYn" name="delYn" title="${labledelyn}" value="N" <c:if test="${vo.delYn eq 'Y'}">checked</c:if>> ${labledelyn}
	                   
	                   		<span style="margin-left: 10px; font-weight: bold;"><i class="fa fa-caret-right"></i><spring:message code="app.community.boardsave.label.biztype" />: <html:codeTag comType="SELECT" parentID="9022" objName="alertTypeCd" objId="alertTypeCd"  selectParam="${vo.alertTypeCd}" /></span>
	                   		<span style="margin-left: 10px; font-weight: bold;"><i class="fa fa-caret-right"></i><spring:message code="app.community.boardsave.label.expousertype" /> : <html:codeTag comType="SELECT" parentID="9021" objName="boardExposureCd" objId="boardExposureCd"  selectParam="${vo.boardExposureCd}" /></span>
	                    </td>
	                 </tr>
				</c:if>
				
				<c:if test="${mgrVO.boardKindCd ne '9020003'}">
					
					<c:if test="${mgrVO.boardCd eq '001' }">
						<tr>
		                	<th><label for="sele2"><spring:message code="app.community.boardsave.label.alertterm" /></label></th><%--팝업공지기간--%>
		                    <td colspan="3">
		                    <spring:message code="app.community.boardsave.label.startterm"  var="startterm" />
		                    <spring:message code="app.community.boardsave.label.endterm"	var="endterm" />
		                    <fmt:parseDate var="alertFrDtParse" value="${vo.alertFrDt}"  pattern="yyyyMMdd"/>
		                    <fmt:parseDate var="alertToDtParse" value="${vo.alertToDt}"  pattern="yyyyMMdd"/>
		                   		<input type="text" style="width: 80px;" name="alertFrDt" id="alertFrDt" readonly="readonly"  maxlength="8"  value="<fmt:formatDate value="${alertFrDtParse}" pattern="${localeDatePattern}" />" title="${startterm}">
		                      		~
								<input type="text" style="width: 80px;" name="alertToDt" id="alertToDt" readonly="readonly"  maxlength="8"  value="<fmt:formatDate value="${alertToDtParse}" pattern="${localeDatePattern}" />" title="${endterm}">
								<i class="far fa-trash-alt" style="margin-left: 5px; cursor: pointer;" onclick="remove(this)"></i>
		                    </td>
		                </tr>
					</c:if>
				</c:if>
				<tr>
				  		
				<spring:message code="app.community.boardsave.label.title" var="labletitle" /><%--제목--%>
				<spring:message code="common.Required" var="Required" /><%--제목--%>
                	<th><label for="sele2">${labletitle}</label></th>
                    <td colspan="3">
                    	<input type="text" name="boardTitle" id="boardTitle" title="${labletitle}" style="width: 80%;" value="${vo.boardTitle}" alt="${Required}" maxlength="66"/>
                    </td>
                </tr>
            </tbody>
		</table>

		<table class="type1" summary="" style="margin-top: 10px">
			<caption><spring:message code="app.community.boardsave.webacc.bdtag.table.caption.contentinput" /></caption><%--내용 작성 --%>
			<tbody>
            	<tr>
                    <td height="100px">
                    <spring:message code="app.community.boardsave.label.content" var="content" /><%--내용--%>
                    	<textarea id="boardContent" name="boardContent" class="textarea" title="${content}" style="width: 100%;height: 100%;"><c:out value="${vo.boardContent}"/> </textarea>
                    </td>
				</tr>
            </tbody>
		</table>
		<!-- 게시물 내용 입력 end ------------------------------------>

		<c:if test="${mgrVO.fileCount > 0}">
		<!-- 게시물 첨부파일 등록  start------------------------------------>
		<div class="tit_area">
		<spring:message code="app.community.boardsave.title.attatchfile" var="attatchfile" />	<%--첨부파일 등록--%>
			<h2>${attatchfile}</h2>
			<div>
				<html:button id="btnFile" name="btnFile" auth="save" msgKey="button.fileupdate"/>
			</div>
		</div>

		<table class="type1" summary="">
			<caption><spring:message code="app.community.boardsave.webacc.bdtag.table.caption.fileupdate" /></caption> <%--첨부파일 등록--%>
			<colgroup>
				<col width="*">
				<col width="80">
			</colgroup>
           	<tbody id="fileListbody">
           		<tr id="emptyFile"><td colspan="2" style="text-align: center;"><spring:message code="app.community.boardsave.label.filevalidation"/></td></tr>
           		<spring:message code="button.delete" var="buttonDel" />
           		<c:if test="${not empty fileVO}">
           			<c:forEach items="${fileVO}" var="list">
           				<tr>
           					<td class='tdms' style="padding:5px 10px; border-right:0px;">
	           					<a href="javascript:;" title="${list.orgFileNm}" onclick="atchFileDownload('<c:out value='\${list.atchFileId}'/>', '<c:out value='\${list.seq}'/>');">
	           					${list.orgFileNm}
	           					</a>
           					</td>
							<td class='tdm' style="border-left:0px;"><input id="btnDel" type="button" class="type1_1" value="${buttonDel}"  onclick="javascript:deleteRowDoc(this,'${list.atchFileId}','${list.seq}')"/></td>
           				</tr>
           			</c:forEach>
           		</c:if>
			</tbody>
		</table>
		<!-- 첨부파일 등록 end  ------------------------------------>
		</c:if>
</div>
</form>
<form id="hiddenForm" name="searchForm" method="post">
<sec:csrfInput/>
<input type="hidden" name="boardIdx" id="boardIdx">
<input type="hidden" name="boardCd" id="boardCd">
</form>

<form id="formDownloadFile" name="formDownloadFile" method="post">
	<sec:csrfInput/>
</form>
</body>
</html>
