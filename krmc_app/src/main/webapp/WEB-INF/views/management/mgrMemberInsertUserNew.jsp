<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>


<script type="text/javascript">
var insertFormChkIdValid = "N";
var insertFormChkedId = "";

var insertFormChkEmailValid = "N";
var insertFormChkedEmail = "";
	$(document).ready(function($){

	 	//버튼클릭 이벤트 ---------------------------------------------------
	 	$('#btnInsertSave').unbind().click(null, eventInsertFormSave); 		  					// 저장버튼
	    $('#insertFormFindCompany').unbind().click(null, findInsertFormCompany); 	  			// 회사찾기
	    $('#insertValidationIdCheck').unbind().click(null, validationInsertFormId);				// 아이디중복검사
	 	$('#validationInsertFormEmailCheck').unbind().click(null, validationInsertFormEmail);	// 이메일중복검사
  	 	//-----------------------------------------------------------------

  	 	
	 	/* spacebar key event prevent */
  	 	//아이디, 비밀번호는 공백입력 불가
		$('#userId, #userPw, #userPwCheck').keydown(function() {
		    if (event.keyCode == 32) {
	    		event.preventDefault();
		    }
		});

		// 아이디 입력값 변경 감지
	 	$("#userId").on("propertychange change keyup paste input", function(){
			var currId = $(this).val();
			if(currId != insertFormChkedId){
				insertFormChkIdValid = "N";
			}
			event.preventDefault();
	 	});

	 	// 이메일 입력값 변경 감지
	 	$("#email").on("propertychange change keyup paste input", function(){
			if(event.keyCode == 13){return;}	//enter 이벤트 제외
			var currEmail = $(this).val();
			if(currEmail != insertFormChkedEmail){
				insertFormChkEmailValid = "N";
			}
			event.preventDefault();
		 });
		
		$('#systemUserInsertForm').validate({
			rules: {
	        	userNm   		: { required: true, noSpace: true },
	        	userId   		: { required: true, noSpace: true, minlength:5 },
	        	//userPw   		: { required: true, noSpace: true, minlength : 4 , maxlength:20, notInc: $('#userId').val(), exprChk: true},
	        	//userPwCheck  	: { required: true, noSpace: true, equalTo: "#userPw", maxlength:20 },
	        	userType 		: { required: true, noSpace: true },
	        	//email	 		: { required: true, email:true, noSpace: true },	        	
	        	passLastDy   	: { maxlength:10 },
	        	//hpNo			: { required: true, noSpace : true}
//	        	masterId	 		: { required: true, noSpace: true },	        	
// 	        	findPwdCd_U		: { required: true, noSpace : true},
// 	        	findPwdAnswer_U : { required : true, noSpace : true},
	        }
			,messages: {
				userNm   		: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a1" /><div>"		}<!-- 사용자명을 입력하세요!  	-->
        		,userId   		: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a2" /><div>"		}<!--  아이디를 입력하세요!  	-->
				,email			: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._validationid.a8" /><div>"	}<!-- 이메일형식으로 입력하세요!-->
        		,userPw   		: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a3" /><div>"		}<!-- 비밀번호를 입력하세요!  	-->
        		,userPwCheck   	: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a4" /><div>"		}<!-- 비밀번호를 입력하세요!  	-->
        		,userType   	: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a7" /><div>"		}<!-- 사용자유형을 입력하세요! 	-->
//        		,masterId   	: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a9" /><div>"		}<!-- 소속회사를 입력하세요!  	-->
//         		,findPwdCd_U	: {required : "<spring:message code='app.common.pwd.label.pwdQuestion'/> <spring:message code='message.required' />"}								<!-- 비밀번호찾기 질문 	-->
// 				,findPwdAnswer_U : {required: "<spring:message code='app.common.pwd.label.pwdAnswer'/> <spring:message code='message.required' />",noSpace : "<spring:message code='app.common.ou.login.alert.blank2'/>"}
			}
	    });

	});

	//숫자만 입력가능하도록
	function chgToNum(obj){
		obj.value=obj.value.replace(/[^0-9]/g,'');
	}

	<%-- 사용자 정보 저장 --%>
	function eventInsertFormSave(){

		if($('#systemUserInsertForm').valid()){

			if(checkedNum() == 0) {
				alert('<spring:message code="app.manager.memberviewlist._eventsave.a10" />'); <%-- 선택된 그룹권한이 없습니다. --%>
				return;
			}

// 			if(insertFormChkEmailValid !="Y") {
<%-- 				alert("<spring:message code='app.manager.memberviewlist._validationid.a11'/>");	이메일 중복확인을 먼저 진행 해주세요. --%>
// 				return;
// 			}

			
			<%-- 기존 사용자일 경우, 아이디(이메일)가 변경됐으면 저장 안하고 return --%>
			if($("#systemUserInsertForm #newUserChk").val() != "Y" &&$('#systemUserInsertForm #emailOld').val().trim() != $("#email").val().trim()){
				alert("<spring:message code='app.manager.memberviewlist._validationid.a10'/>");	<%-- 기존회원의 아이디는 변경할 수 없습니다. --%>
				return;
			}
			
			
			<%-- 신규사용자일 경우에만 아이디 중복확인 여부 체크 --%>
			if($("#systemUserInsertForm #newUserChk").val() == "Y"){
				if(insertFormChkIdValid !="Y") {
					alert("<spring:message code='app.manager.memberviewlist._validationid.a9'/>");	<%-- 아이디 중복확인을 먼저 진행 해주세요. --%>
					return;
				}
			}else{
			<%-- 기존 사용자일 경우, 아이디가 변경됐으면 저장 안하고 return --%>
				if($('#systemUserInsertForm #userIdOld').val().trim() != $("#userId").val().trim()){
					alert("<spring:message code='app.manager.memberviewlist._validationid.a10'/>");	<%-- 기존회원의 아이디는 변경할 수 없습니다. --%>
					return;
				}
			}
			
			var groupIds =  doMakeRowData(); //선택한 권한그룹

			var passChgYn 	= "";	//패스워드 필수변경여부 Y/N
			var useYn 		= "";	//사용여부 Y/N
			var lockYn 		= "";

			var fromData = {};
			$('#systemUserInsertForm').find('input, select').map(function() {

				if(this.name) {
					if(this.name =='passChgYn'){
						if(!passChgYn) {
							if( $(this)[0].checked) 	passChgYn = "N";
							else passChgYn = "Y";
							fromData[this.name] = passChgYn;
						}
					}
					else if(this.name =='useYn'){
						if(!useYn) {
							if( $(this)[0].checked) 	useYn = "Y";
							else useYn = "N";
							fromData[this.name] = useYn;
						}
					}
					else if(this.name =='lockYn'){
						if(!lockYn) {
							if( $(this)[0].checked) 	useYn = "Y";
							else lockYn = "N";
							fromData[this.name] = lockYn;
						}
					}
					else if(this.name =='userId'){
						fromData[this.name] = $(this).val().trim();
					}
					else if(this.name =='hpNo'){
						fromData[this.name] = $(this).val().replace(/[^0-9]/g,'');
					}
					else fromData[this.name] = $(this).val().trim();
				}
			});

			fromData['groupIds'] =	groupIds;
// 			var findPwdCd = $('#findPwdCd_U').val();
// 			var findPwdAnswer = $('#findPwdAnswer_U').val();
// 			fromData['findPwdCd'] =findPwdCd;
// 			fromData['findPwdAnswer'] = findPwdAnswer;


			// 저장 이벤트 json 실행---------------------------
			$.ajax({
			      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			      url  : "<c:url value='/app/mgr/manager/mgrMember_insData'/>",
			      data : JSON.stringify(fromData),
		 	     success : function(data){
		   	 	if(data == null) msg = '<spring:message code="app.manager.memberviewlist._eventsave.m1" />'; <%-- 처리된 데이터가 없습니다. --%>
	       	     else if('success' == data.msgCd)
	        	    {
	        	    	msg = '<spring:message code="app.manager.memberviewlist._eventsave.m2" />'; <%-- 저장이 정상적으로 처리되었습니다. --%>
	        	 	   	_SYSTEM_UPDATE_PROC_COUNT = true;
	        	 	    $("#systemUserInsertForm #userId").prop("readonly", true);
	        	 	   	$("#systemUserInsertForm #userNm").prop("readonly", true);
	       				$("#systemUserInsertForm #email").prop("readonly", true);
	        	  	    $("#insertFormAuthUserGroup tr").remove();
	        	  	     systemUserInsertLayerClose();
	        	  	    
// 	        	 	   	$('#system_user_insert_layer').hide();
// 	       				$('#opacity').hide();									//레이어 닫기
// 	       				if(_SYSTEM_UPDATE_PROC_COUNT) eventSearch();			//  재조회
	        	    }
	       	     else if('dupl'  == data.msgCd ){
	        	    	msg = '<spring:message code="app.manager.memberviewlist._eventsave.m3" />'; <%-- 입력하신 아이디는 이미 사용중입니다. --%>
	        	    }
	        	    else msg = '<spring:message code="app.manager.memberviewlist._eventsave.m4" />'; <%-- 저장중 오류가 발생하였습니다. --%>

	        	  	alert(msg);
		    	  }
			});
			//----------------------------------------------
		}
	}


	function checkedNum(){

		var num =  $('input[name=userAuth]').filter(':checked').length;
		return num;
	}


	/*  선택된 ROW 값생성 userAuth groupId*/
	function doMakeRowData() {
		var chkGroupId = "";

		$('input[name=userAuth]:checked').each(function() {
			$(this).parents('tr:first').find('input:hidden').map(function() {
				if(chkGroupId) chkGroupId +=",";
				chkGroupId += $(this).val();
			});
		});

		return chkGroupId.split(",");
	}

	/*소속회사찾기*/
	function findInsertFormCompany() {
		_SYSTEM_PARENT_TYPE =  false;

		comCompany_compCdId = "masterId";
		comCompany_compNmId	= "compNm";
		commonCompanyFindLayer('','','', setInsertFormCompany);
	}

	/*소속회사찾기 콜백*/
	function setInsertFormCompany(data) {
		$("#systemUserInsertForm #masterId").val(data.masterId);
		$("#systemUserInsertForm #compNm").val(data.compNm);
	}


	/* 입력된 아이디 중복확인 */
	function validationInsertFormId() {
		var chkId = $('#systemUserInsertForm #userId').val();
		
		if(!$('#systemUserInsertForm #userId').val().trim()) {
			alert('<spring:message code="app.manager.memberviewlist._validationid.a1" />'); <%-- 아이디를 입력하세요! --%>
			$('#userId').focus();
			return;
		}
		
		if($("#systemUserInsertForm #newUserChk").val() == "N"){	//신규회원인지 아닌지 체크
			alert("<spring:message code='app.manager.memberviewlist._validationid.a10'/>");	<%-- 기존회원의 아이디는 변경할 수 없습니다. --%>
			return;
		}

		var str = {"userId": chkId};

		$.ajaxSetup({contentType: "application/json; charset=utf-8" });
	  	$.post("<c:url value='/app/common/userIdValidation'/>"
		  		,JSON.stringify(str)
		  		,function(data){
	  				if('success' == data.msgCd) {
		  				var userId = $('#systemUserInsertForm #userId').val();
		  				
		  				<%-- 아이디 중복확인 ok --%>
		  				insertFormChkedId = userId;
						insertFormChkIdValid = "Y";
						
		  				alert('<spring:message code="app.manager.memberviewlist._validationid.a4" arguments="'+userId+'" />'); <%-- 입력하신 [ "+$('#userId').val()+" ]는 사용가능한 아이디 입니다. --%>
	  				}else if('dupl' == data.msgCd) {
	  					<%-- 입력창 초기화 --%>
						$("#userId").val("");
						$("#userId").focus();
						
		  				alert('<spring:message code="app.manager.memberviewlist._validationid.a6" />'); <%-- 입력하신아이디는 이미사용중입니다. --%>
		  			}
		  			else alert('<spring:message code="app.manager.memberviewlist._validationid.a7" />'); <%-- 아이디중복검사 오류발생! --%>
	  		}, 'json');
	}

	/*이메일중복확인*/
	function validationInsertFormEmail() {
		var chkEmail = $('#systemUserInsertForm #email').val();

		var emailChk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

		if(!$('#systemUserInsertForm #email').val().trim()) {
			alert("<spring:message code='app.manager.memberviewlist._eventsave.a8' />"); <%-- 이메일을 입력하세요! --%>
			$('#systemUserInsertForm #email').focus();
			return;
		}
		
		if(!emailChk.test(chkEmail)) {
			var msg = "<spring:message code='app.manager.memberviewlist._validationid.a8'/>";<%-- 이메일 형식으로 입력하세요! --%>
			$('#systemUserInsertForm #email').focus();
			alert(msg);
			return false;
		}
		

		// 기존회원이면서, 이메일이 변경이 이뤄지지 않았을 경우 return
		if($("#systemUserInsertForm #newUserChk").val() == "N" && ($("#systemUserInsertForm #emailOld").val().trim() == $("#systemUserInsertForm #email").val().trim())){
			alert("<spring:message code='app.manager.memberviewlist._validationid.a15'/>"); <%-- 이메일 정보가 기존과 동일합니다. --%>
			$("#systemUserInsertForm #email").focus();
			return;
		}
			

		var str = {"email": chkEmail};

		$.ajaxSetup({contentType: "application/json; charset=utf-8" });
	  	$.post("<c:url value='/app/common/userEmailValidation'/>"
		  		,JSON.stringify(str)
		  		,function(data){
	  				if('success' == data.msgCd) {
		  				var email = $('#systemUserInsertForm #email').val();
		  				
		  				<%-- 아이디 중복확인 ok --%>
		  				insertFormChkedEmail = email;
						insertFormChkEmailValid = "Y";
		  				alert("<spring:message code='app.manager.memberviewlist._validationid.a14' arguments='"+email+"' />"); 	<%-- 입력하신 [이메일]은 사용가능한 이메일입니다. --%>
		  				setEmailToId(chkEmail); <%--아이디에 이메일 세팅--%>
		  				
	  				}else if('dupl' == data.msgCd) {
	  					<%-- 입력창 초기화 --%>
	  					$("#email").val("");
						$("#email").focus();
						
		  				alert("<spring:message code='app.manager.memberviewlist._validationid.a12' arguments='"+chkEmail+"'/>"); <%-- 입력하신 [이메일]은 이미 사용중입니다. --%>
		  			}
		  			else alert("<spring:message code='app.manager.memberviewlist._validationid.a13' />"); <%-- 이메일 중복검사 오류발생! --%>
	  		}, 'json');
	}

	<%--아이디에 이메일 세팅 --%>
	function setEmailToId(email){
		$('#systemUserInsertForm input[name="userId"]').attr("disabled",false);
		$('#systemUserInsertForm input[name="userId"]').val(email);
		$('#systemUserInsertForm input[name="userId"]').attr("disabled",true);
		insertFormChkIdValid = "Y"; <%--아이디 중복확인 ok--%>
	}

	/* 등록 또는 수정 처리 여부 - 참일 경우 부모창을 재조회 한다. */
	var _SYSTEM_UPDATE_PROC_COUNT = false;

	/* Layer Event Create */
	function setSystemUserInsertLayerEvent(){

		/* 레이어 활성화 */
		$('#system_user_insert_layer').show();
		$('#opacity').show();

		/* 레이어 드레그 Event */
		$("#system_user_insert_layer").draggable({
			handle: $("h1")
			,cancle: $("a.close")
			,containment: "#opacity"
			,scroll: false
		});

		/* 레이어 닫기버튼 Click Event */
		 $('#system_user_insert_layer a.close').click(function(e){
			systemUserInsertLayerClose();
			//e.preventDefault();
		 });

	}

	/*레이어 닫기*/
	function  systemUserInsertLayerClose(){
		$("#insertFormAuthUserGroup tr").remove();
		$('#systemUserInsertForm').find('input, select').map(function() {
			if(this.type != 'button') $(this).val('');
		});
		$('#t').find('input').map(function() {
			if(this.type == 'checkbox') $(this).prop('checked', false);
		});
		$('#system_user_insert_layer').hide();
		$('#opacity').hide();

		$('*').filter(function(){
			return $(this).data('tooltipsterNs');
		}).tooltipster('hide');

		
		if(_SYSTEM_UPDATE_PROC_COUNT) eventSearch();			//  재조회
	}
	
	/* 레이어호출(신규) */
	function systemUserInsertLayer(){

		var memberCd = "";
		memberCd = $('#systemUserInsertForm input[name="memberCd"]').val();
		$('#systemUserInsertForm #newUserChk').val("Y");
		$("#systemUserInsertForm #userId").prop("readonly", false);
		$("#systemUserInsertForm #userNm").prop("readonly", false);
		$("#systemUserInsertForm #email").prop("readonly", false);
		_SYSTEM_UPDATE_PROC_COUNT = false;
		setSystemUserInsertLayerEvent();

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url  : "<c:url value='/app/mgr/manager/mgrMember_selDetail'/>",
			data : JSON.stringify({'memberCd'   : memberCd }),
			success : function(data){
				if(data == null) alert('<spring:message code="app.manager.memberviewpop._systemUserUpdateLayer.a1" />'); /* 선택된 사용자 정보가 없습니다. */
				else setInsertFormInit(data);
			}
		});
	}
	
	<%-- 사용자 정보 data Setting--%>
	function setInsertFormInit(data){

		$("#passLastDy").datepicker();

		var userData  = data.memberData;
		var groupList = data.groupList;
		
		if(userData) {
			//$("#insertValidationIdCheck").hide();
			$("#newUserChk").val("N");
			$("#systemUserInsertForm #userId").prop("readonly", true);
			$("#systemUserInsertForm #userNm").prop("readonly", true);
			$("#systemUserInsertForm #email").prop("readonly", true);

			$('#systemUserInsertForm input[name="userNm"]').val(userData.userNm);
			$('#systemUserInsertForm input[name="memberCd"]').val(userData.memberCd);
			$('#systemUserInsertForm input[name="userId"]').val(userData.userId);
			$('#systemUserInsertForm input[name="userIdOld"]').val(userData.userId);
			$('#systemUserInsertForm input[name="hiddenPasswd"]').val(userData.userPw);
			$('#systemUserInsertForm input[name="telNo"]').val(userData.telNo);
			$('#systemUserInsertForm input[name="hpNo"]').val(userData.hpNo);
			$('#systemUserInsertForm select[name="userType"]').val(userData.userType);
			$('#systemUserInsertForm input[name="email"]').val(userData.email);
			$('#systemUserInsertForm input[name="masterId"]').val(userData.masterId);
			$('#systemUserInsertForm input[name="compNm"]').val(userData.compNm);
			$('#systemUserInsertForm input[name="positionNm"]').val(userData.positionNm);
			$('#systemUserInsertForm input[name="classNm"]').val(userData.classNm);
			$('#systemUserInsertForm input[name="passLastDy"]').val(userData.passLastDyFmt);
			$('#systemUserInsertForm input[name="emailOld"]').val(userData.email);

			var passFailCnt = userData.passFailCnt == null ? '0' : userData.passFailCnt;
			$('#systemUserInsertForm input[name="passFailCnt"]').val(passFailCnt);
			$('#systemUserInsertForm span[name="passFailCnt"]').text("("+passFailCnt+")");

			$('#systemUserInsertForm input[name="lockYn"]').val(userData.lockYn);

// 			$('#systemUserInsertForm #findPwdCd_U').val(userData.findPwdCd);
// 			$('#systemUserInsertForm #findPwdAnswer_U').val(userData.findPwdAnswer);
			
			if(userData.passChgYn =="N")
				$("#systemUserInsertForm").find("input[name='passChgYn']")[0].checked = true;
			else  $("#systemUserInsertForm").find("input[name='passChgYn']")[1].checked = true;

			if(userData.useYn =="Y")
				$("#systemUserInsertForm").find("input[name='useYn']")[0].checked = true;
			else  $("#systemUserInsertForm").find("input[name='useYn']")[1].checked = true;

			insertFormChkEmailValid = "Y";		<%-- email 중복확인 여부 기본세팅 : 기존회원 --%>
		}else{
			$('#systemUserInsertForm input[name="passLastDy"]').val(getConvertDateReverse('99991230'))
		}
		if(groupList != null && groupList.length >0){
			var lngth = groupList.length;
			for(var k=0;k<lngth;k++){
				groupList[k].count = k+1;

				groupList[k].groupLocaleNm = groupList[k].groupLocaleNm;
			}
		}
		$("#authUserGroupTemplate").tmpl(groupList).appendTo("#insertFormAuthUserGroup");

		
	}
</script>
<script id="authUserGroupTemplate"  type="text/x-jquery-tmpl">
	<tr>
		<td class='tdm'><c:out value='\${groupId}'></c:out></td>
		<td ><c:out value='\${groupLocaleNm}'></c:out></td>
		<td ><c:out value='\${groupDesc}'></c:out></td>
		<td class='tdm'>
			<input type="hidden" name="groupId" id="groupId" value="<c:out value='\${groupId}'></c:out>">
			<input type="checkbox" name="userAuth" id="userAuth" {%if userAuth == 'Y'%}checked='checked'{%/if%}  >
		</td>
	</tr>
</script>
<div class="pop_layer_new" id="system_user_insert_layer" style="position:absolute;left:25%;top:-5%;width:800px; display:none;">
    <h1><spring:message code="app.manager.memberviewpop.title.userinfo" /></h1> <%--사용자정보 --%>
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
            <div class="tit_area">
                <h2><spring:message code="app.manager.memberviewmodpop.title.userinfo" /></h2> <%--사용자정보 --%>
                <div>
                	<html:button id="btnInsertSave" auth="save" msgKey="button.save"/> <%--저장 --%>
                </div>  
            </div>
            <form id="systemUserInsertForm" name="systemUserInsertForm" method="post">
	            <input type=hidden id='memberCd' name='memberCd'>
	            <input type=hidden id='passFailCnt' name='passFailCnt'>
	            <input type=hidden id='lockYn' name='lockYn'>
	            <input type=hidden id='hiddenPasswd' name='hiddenPasswd'>
	            <table class="type1" summary="<spring:message code="app.manager.memberviewmodpop.webacc.hdtag.table.summary" />"> <%--사용자정보 입력 --%>
	                    <caption><spring:message code="app.manager.memberviewmodpop.webacc.hdtag.table.caption" /></caption> <%--사용자정보 입력 --%>
	                    <colgroup>
	                    <col width="150">
	                    <col width="*">
	                    <col width="150">
	                    <col width="*">
	                    </colgroup>
	                    <tbody>
	                    <spring:message code="common.choice" var="commonChoice"/> <%--선택 --%>
	                    <tr>
	                        <th>* <spring:message code="app.manager.memberviewmodpop.label.usernm" /></th> <%--사용자명 --%>
	                        <td><input type="text" style="width: 90%" name="userNm" id="userNm" onkeyup="chkByte(this,100);"  value="<c:out value='${data.userNm}'></c:out>" ></td>
	                        <th>* <spring:message code="app.manager.memberviewmodpop.label.userid" /></th> <%--아이디 --%>
	                        <td><input type="text" style="width: 140px; ime-mode:disabled;" name="userId" id="userId" onkeyup="chkByte(this,60);"  placeholder=""   value="<c:out value='${data.userId}'></c:out>" >
	                         <input type="button" id="insertValidationIdCheck" class="button btn_gray02"
	                         <spring:message code="button.duplchk" var="duplchk"/>
	                         value="<c:out value='${duplchk }'></c:out>">  <%--중복확인 --%>
	                         <input type="hidden" id="userIdOld" name="userIdOld" value="<c:out value='${data.userId}'></c:out>">
	                         <input type="hidden" id="newUserChk" name="newUserChk" value="Y"/>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>* <spring:message code="app.manager.memberviewmodpop.label.userpw" /></th> <%--비밀번호 --%>
	                        <td><input type="password" style="width: 90%" name="userPw"   id="userPw" value=""></td>
	                        <th>* <spring:message code="app.manager.memberviewmodpop.label.userpwchk" /></th> <%--비밀번호 확인--%>
	                        <td><input type="password" style="width: 90%" name="userPwCheck"  id="userPwCheck" value=""></td>
	                    </tr>
	                    <tr>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.telno" /></th> <%--전화번호 --%>
	                        <td><input type="text" style="width: 90%" name="telNo"  id="telNo" oninput ="fncSetDefInputHpNo(this);" onkeyup="chkByte(this,14);" value="<c:out value='${data.telNo}'></c:out>"></td>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.hpno" /></th> <%--휴대폰번호 --%>
	                        <td><input type="text" style="width: 90%" name="hpNo"    id="hpNo" oninput ="fncSetDefInputHpNo(this);" onkeyup="chkByte(this,14);" value="<c:out value='${data.hpNo}'></c:out>"></td>
	                    </tr>
	                    <tr>
	                    	<th>* <spring:message code="app.manager.memberviewmodpop.label.usertype" /></th> <%--사용자유형 --%>
	                        <td>
	                        	<html:codeTag comType="SELECT" objId="userType" objName="userType" parentID="9001"  defName="${commonChoice }" />
	                        </td>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.email" /></th> <%--이메일 --%>
	                        <td>
	                        	<input type="text" style="width: 90%;  ime-mode:disabled; " name="email" id="email" value="<c:out value='${data.email}'></c:out>">
		                        <%--<input type="button" id="validationInsertFormEmailCheck" class="button btn_gray02" value="<spring:message code='button.duplchk'/>"/> --%> <%--중복확인 --%>
		                        <input type="hidden" id="emailOld" name="emailOld" value="<c:out value='${data.email}'/>"/>
	                        </td>
	                    </tr>
	 <%--                   <tr>
	                    	 <th>* <spring:message code="app.common.pwd.label.pwdQuestion" /></th> <!--비밀번호 찾기 질문 -->
	                         <td><html:codeTag comType="SELECT" objName="findPwdCd_U" parentID="9015" objId="findPwdCd_U" defName="${select}" width="90%;" ></html:codeTag></td>
	                         <th>* <spring:message code="app.common.pwd.label.pwdAnswer" /></th> <!--비밀번호 찾기 답변 -->
	                         <td><input type="text" style="width:90%" name="findPwdAnswer_U" id="findPwdAnswer_U"  /></td>
	                    </tr>
	 --%>
	                    <tr>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.compnm" /></th> <%--소속 --%>
	                        <td colspan="3">
	                        	<input type="text" readonly class="typeGHL" style="width: 100px;" name="masterId" id="masterId" value="<c:out value='${data.masterId}'></c:out>">
	                        	<input type="text" readonly style="width: 300px;" name="compNm" id="compNm" value="<c:out value='${data.compNm}'></c:out>">
	                        	<input type="button" id="insertFormFindCompany"  class="button btn_gray02" value='<spring:message code="common.search"/>'>	 <%--찾기 --%>
	                        </td>
	                    </tr>
	                     <tr>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.positionnm" /></th> <%--직책 --%>
	                        <td><input type="text" style="width: 90%" name="positionNm" id="positionNm" onkeyup="chkByte(this,14);" value="<c:out value='${data.positionNm}'></c:out>" ></td>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.classnm" /></th> <%--직급 --%>
	                        <td><input type="text" style="width: 90%" name="classNm" id="classNm" onkeyup="chkByte(this,14);"  value="<c:out value='${data.classNm}'></c:out>" ></td>
	                    </tr>
	                    <tr>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.passchgyn" /></th> <%--비밀번호 필수변경여부 --%>
	                        <td>
	                        	<input type="radio" id="passChgY" name="passChgYn" value="N" checked >
	                        	<label for="passChgY"><spring:message code="app.manager.memberviewmodpop.passChgNo" /></label> <%--변경안함 --%>
	                        	<input type="radio" id="passChgN" name="passChgYn" value="Y"  >
	                        	<label for="passChgN"><spring:message code="app.manager.memberviewmodpop.passChgYes" /> </label><%--필수변경 --%>&nbsp;
	
				            </td>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.passlastdy" /></th> <%--다음변경일 --%>
	                        <td>
	                        	<input type="text" style="width: 80%; margin-right:4px" name="passLastDy" id="passLastDy" />
	                        </td>
	                    </tr>
	                     <tr>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.useyn" /></th><%--사용여부 --%>
	                        <td>
	                        	<input type="radio" id="useY" name="useYn" value="Y" checked="checked" >
	                        	<label for="useY"><spring:message code="app.manager.memberviewmodpop.UseYes" />&nbsp; </label><%--사용 --%>
				                <input type="radio" id="useN" name="useYn" value="N"   >
				                <label for="useN"><spring:message code="app.manager.memberviewmodpop.UseNo" /> </label><%--미사용 --%>
	                        </td>
	                        <th><spring:message code="app.manager.memberviewmodpop.label.lockYn" /></th>
	                        <td>
	                        	<input type="radio" id="lockY" name="lockYn" value="Y" />
		                        <label for ="lockY" ><spring:message code="app.manager.memberviewmodpop.UseYes" />&nbsp; <%--사용 --%></label>
					            <input type="radio" id="lockN" name="lockYn" value="N" checked="checked"/>
	                     		<label for ="lockN" ><spring:message code="app.manager.memberviewmodpop.UseNo" /> <%--미사용 --%></label>
					            <span name="passFailCnt"></span>
					        </td>
	                    </tr>
	                    </tbody>
				</table>
			</form>
			 <div class="tit_area">
                <h2><spring:message code="app.manager.memberviewpop.title.authgroup" /></h2> <%--사용권한 그룹 --%>
             </div>
			<!-- data list -->
			<div class="table_type1" id="epro_groupList">
				<table class="type1" summary="<spring:message code="app.manager.memberviewpop.webacc.bdtag.table.summary" />"> <%--사용권한 그룹 내역 --%>
					<caption><spring:message code="app.manager.memberviewpop.webacc.bdtag.table.caption" /></caption> <%--사용권한 그룹 내역 --%>
					<colgroup>
						<col width="101">
						<col width="200">
						<col width="400">
						<col width="*">
						<col width="18">
					</colgroup>
					<thead>
						<tr>
							<th><spring:message code="app.manager.memberviewpop.head.groupcd" /></th> <%--그룹코드 --%>
							<th><spring:message code="app.manager.memberviewpop.head.groupnm" /></th> <%--그룹명칭 --%>
							<th><spring:message code="app.manager.memberviewpop.head.groupdesc" /></th> <%--설명 --%>
							<th><spring:message code="app.manager.memberviewpop.head.sel" /></th> <%--설명 --%>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5" nowrap style="margin: 0; padding: 0; ">
								<div id="_dataList" style="margin: 0; padding: 0; height:150px; overflow-y: scroll; overflow-x: hidden">
								<table id="selectable" class="type1s" style="border-collapse: collapse;">
			                    	<colgroup>
			                    		<col width="100">
										<col width="200">
										<col width="400">
										<col width="*">
									 </colgroup>
			                    	<tbody id="insertFormAuthUserGroup"></tbody>
			                    </table>
			                    </div>
			                </td>
			            </tr>
		            </tbody>
		        </table>
	       </div>
        </div>
	</div>
</div>


