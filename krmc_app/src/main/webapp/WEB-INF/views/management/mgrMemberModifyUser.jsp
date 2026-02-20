<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var chkIdValid = "N";
var chkedId = "";

var chkEmailValid = "N";
var chkedEmail = "";
	$(document).ready(function($){

	 	//버튼클릭 이벤트 ---------------------------------------------------
	 	$('#btnSave').unbind().click(null, eventUpdateFormSave); 		  						// 저장버튼
	    $('#systemUserUpdateForm #findCompany').unbind().click(null, findCompany); 	  			// 회사찾기
	 	$('#systemUserUpdateForm #validationIdCheck').unbind().click(null, validationId);		// 아이디중복검사
	 	$('#systemUserUpdateForm #validationEmailCheck').unbind().click(null, validationEmail);	// 이메일중복검사
	 	$('#pwdchSendBtn').unbind().click(null, pwdchSend);	// 비밀번호 변경 Email 발송
  	 	//-----------------------------------------------------------------

  	 	
	 	/* spacebar key event prevent */
  	 	//아이디, 비밀번호는 공백입력 불가
		$('#modUserId, #modUserPw, #modUserPwCheck').keydown(function() {
		    if (event.keyCode == 32) {
	    		event.preventDefault();
		    }
		});

		// 아이디 입력값 변경 감지
	 	$("#modUserId").on("propertychange change keyup paste input", function(){
			var currId = $(this).val();
			if(currId != chkedId){
				chkIdValid = "N";
			}
			event.preventDefault();
	 	});

	 	// 이메일 입력값 변경 감지
	 	$("#modEmail").on("propertychange change keyup paste input", function(){
			if(event.keyCode == 13){return;}	//enter 이벤트 제외
			var currEmail = $(this).val();
			if(currEmail != chkedEmail){
				chkEmailValid = "N";
			}
			event.preventDefault();
		 });
		
		$('#systemUserUpdateForm').validate({
			rules: {
	        	userNm   		: { required: true, noSpace: true },
	        	userId   		: { required: true, noSpace: true, minlength:5 },
//	        	userPw   		: { required: true, noSpace: true, minlength : 8 , maxlength:20, notInc: $('#userId').val(), exprChk: true},
//	        	userPwCheck  	: { required: true, noSpace: true, equalTo: "#userPw", maxlength:20 },
	        	userType 		: { required: true, noSpace: true },
	        	email	 		: { required: true, email:true, noSpace: true },
	        	/* masterId 		: { required: true, noSpace: true }, */
	        	passLastDy   	: { maxlength:10 },
// 	        	findPwdCd_U		: { required: true, noSpace : true},
// 	        	findPwdAnswer_U : { required : true, noSpace : true},
	        	hpNo			: { required: true, noSpace : true}
	        }
			,messages: {
				userNm   		: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a1" /><div>"		}<!-- 사용자명을 입력하세요!  	-->
        		,userId   		: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a2" /><div>"		}<!--  아이디를 입력하세요!  	-->
        		,email			: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._validationid.a8" /><div>"	}<!-- 이메일형식으로 입력하세요!-->
//        		,userPw   		: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a3" /><div>"		}<!-- 비밀번호를 입력하세요!  	-->
//        		,userPwCheck   	: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a4" /><div>"		}<!-- 비밀번호를 입력하세요!  	-->
        		,userType   	: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a7" /><div>"		}<!-- 사용자유형을 입력하세요! 	-->
        		/* ,masterId   	: {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.memberviewlist._eventsave.a9" /><div>"		} */<!-- 소속회사를 입력하세요!  	-->
//         		,findPwdCd_U	: { required : "<spring:message code='app.common.pwd.label.pwdQuestion'/> <spring:message code='message.required' />"}								<!-- 비밀번호찾기 질문 	-->
// 				,findPwdAnswer_U : { required: "<spring:message code='app.common.pwd.label.pwdAnswer'/> <spring:message code='message.required' />",noSpace : "<spring:message code='app.common.ou.login.alert.blank2'/>"}
				,hpNo			: { required: "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code="app.manager.suMemberviewpop._eventsave.a6" /><div>",noSpace : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.common.ou.login.alert.blank2'/></div>"}
			}
	    });

	});

	//비밀번호 변경 요청 발송
	function pwdchSend() {
		var searchInfo = {};
		var userId = $("#systemUserUpdateForm #modUserId").val();
		var email = $("#systemUserUpdateForm #modEmail").val();
		var bmanNo = $("#systemUserUpdateForm #modBmanNo").val();
		
		searchInfo.userId = userId;
		searchInfo.email = email;
		searchInfo.bmanNo = bmanNo;

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url : '<c:url value = "/security/pwForgotForm_insData"/>',
			data : JSON.stringify(searchInfo),
			success : function(data){
				if(data.resultCode =="success"){
					alert(data.message);
				}else{
					alert(data.message);
					return;
				}
			}
		});
	}

	//숫자만 입력가능하도록
	function chgToNum(obj){
		obj.value=obj.value.replace(/[^0-9]/g,'');
	}


	function eventUpdateFormSave(){

		if($('#systemUserUpdateForm').valid()){


			if(checkedNum() == 0) {
				alert('<spring:message code="app.manager.memberviewlist._eventsave.a10" />'); <%-- 선택된 그룹권한이 없습니다. --%>
				return;
			}

			<%-- 신규사용자일 경우에만 아이디 중복확인 여부 체크 --%>
			if($("#systemUserUpdateForm #newUserChk").val() == "Y"){
				if(chkIdValid !="Y") {
					alert("<spring:message code='app.manager.memberviewlist._validationid.a9'/>");	<%-- 아이디 중복확인을 먼저 진행 해주세요. --%>
					return;
				}
			}else{
			<%-- 기존 사용자일 경우, 아이디가 변경됐으면 저장 안하고 return --%>
				if($('#systemUserUpdateForm #userIdOld').val().trim() != $("#systemUserUpdateForm #modUserId").val().trim()){
					alert("<spring:message code='app.manager.memberviewlist._validationid.a10'/>");	<%-- 기존회원의 아이디는 변경할 수 없습니다. --%>
					return;
				}
			}

			// 비밀번호란이 비어있지 않을 경우, 일치확인
			if(!($("#modUserPw").val() == null || $("#modUserPw").val() == "" || $("#userPwCheck").val() == null || $("#userPwCheck").val() == "")){
				//비밀번호와 비밀번호 확인이 일치하지 않을 경우
				if($("#modUserPw").val() != $("#userPwCheck").val()){
					alert("<spring:message code='app.common.user.alert.notmatched'/>");		/* 비밀번호와 비밀번호 확인이 일치하지 않습니다. */ 
					$("#modUserPw").val("");
					$("#userPwCheck").val("");
					$("#modUserPw").focus();
					return;
				}
			}

			if(chkEmailValid !="Y") {
				alert("<spring:message code='app.manager.memberviewlist._validationid.a11'/>");	<%-- 이메일 중복확인을 먼저 진행 해주세요. --%>
				return;
			}

			var groupIds =  doMakeRowData(); //선택한 권한그룹

			var passChgYn 	= "";	//패스워드 필수변경여부 Y/N
			var useYn 		= "";	//사용여부 Y/N
			var lockYn 		= "";

			var fromData = {};
			$('#systemUserUpdateForm').find('input, select').map(function() {

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
							if( $(this)[0].checked) 	lockYn = "Y";
							else lockYn = "N";
							fromData[this.name] = lockYn;
						}
					}
					else if(this.name =='userId'){
						fromData[this.name] = $(this).val().trim();
					}
					else fromData[this.name] = $(this).val();
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
	        	  	    $("#authUserGroup tr").remove();
	        	 	   	$('#system_user_layer').hide();
	       				$('#opacity').hide();									//레이어 닫기
	       				if(_SYSTEM_UPDATE_PROC_COUNT) eventSearch();			//  재조회
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
	function findCompany() {
		_SYSTEM_PARENT_TYPE =  false;

		comCompany_compCdId = "masterId";
		comCompany_compNmId	= "compNm";
		commonCompanyFindLayer('','','',setModifyFormCompany);
	}

	/*소속회사찾기 콜백*/
	function setModifyFormCompany(data) {
		$("#systemUserUpdateForm #masterId").val(data.masterId);
		$("#systemUserUpdateForm #compNm").val(data.compNm);
	}


	/* 입력된 아이디 중복확인 */
	function validationId() {
		var chkId = $('#systemUserUpdateForm #modUserId').val();
		
		if(!$('#systemUserUpdateForm #modUserId').val().trim()) {
			alert('<spring:message code="app.manager.memberviewlist._validationid.a1" />'); <%-- 아이디를 입력하세요! --%>
			$('#systemUserUpdateForm #modUserId').focus();
			return;
		}
		
		if($("#systemUserUpdateForm #newUserChk").val() == "N"){	//신규회원인지 아닌지 체크
			alert("<spring:message code='app.manager.memberviewlist._validationid.a10'/>");	<%-- 기존회원의 아이디는 변경할 수 없습니다. --%>
			return;
		}

		var str = {"userId": chkId};

		$.ajaxSetup({contentType: "application/json; charset=utf-8" });
	  	$.post("<c:url value='/app/common/userIdValidation'/>"
		  		,JSON.stringify(str)
		  		,function(data){
	  				if('success' == data.msgCd) {
		  				var userId = $('#systemUserUpdateForm #userId').val();
		  				
		  				<%-- 아이디 중복확인 ok --%>
		  				chkedId = userId;
						chkIdValid = "Y";
						
		  				alert('<spring:message code="app.manager.memberviewlist._validationid.a4" arguments="'+userId+'" />'); <%-- 입력하신 [ "+$('#userId').val()+" ]는 사용가능한 아이디 입니다. --%>
	  				}else if('dupl' == data.msgCd) {
	  					<%-- 입력창 초기화 --%>
						$("#systemUserUpdateForm #modUserId").val("");
						$("#systemUserUpdateForm #modUserId").focus();
						
		  				alert('<spring:message code="app.manager.memberviewlist._validationid.a6" />'); <%-- 입력하신아이디는 이미사용중입니다. --%>
		  			}
		  			else alert('<spring:message code="app.manager.memberviewlist._validationid.a7" />'); <%-- 아이디중복검사 오류발생! --%>
	  		}, 'json');
	}

	/*이메일중복확인*/
	function validationEmail() {
		var chkEmail = $('#systemUserUpdateForm #modEmail').val();

		var emailChk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

		if(!$('#systemUserUpdateForm #modEmail').val().trim()) {
			alert("<spring:message code='app.manager.memberviewlist._eventsave.a8' />"); <%-- 이메일을 입력하세요! --%>
			$('#systemUserUpdateForm #modEmail').focus();
			return;
		}
		
		if(!emailChk.test(chkEmail)) {
			var msg = "<spring:message code='app.manager.memberviewlist._validationid.a8'/>";<%-- 이메일 형식으로 입력하세요! --%>
			$('#systemUserUpdateForm #modEmail').focus();
			alert(msg);
			return false;
		}
		

		// 기존회원이면서, 이메일이 변경이 이뤄지지 않았을 경우 return
		if($("#systemUserUpdateForm #newUserChk").val() == "N" && ($("#systemUserUpdateForm #emailOld").val().trim() == $("#systemUserUpdateForm #email").val().trim())){
			alert("<spring:message code='app.manager.memberviewlist._validationid.a15'/>"); <%-- 이메일 정보가 기존과 동일합니다. --%>
			$("#systemUserUpdateForm #modEmail").focus();
			return;
		}
			

		var str = {"email": chkEmail};

		$.ajaxSetup({contentType: "application/json; charset=utf-8" });
	  	$.post("<c:url value='/app/common/userEmailValidation'/>"
		  		,JSON.stringify(str)
		  		,function(data){
	  				if('success' == data.msgCd) {
		  				var email = $('#systemUserUpdateForm #modEmail').val();
		  				
		  				<%-- 아이디 중복확인 ok --%>
		  				chkedEmail = email;
						chkEmailValid = "Y";
						
		  				alert("<spring:message code='app.manager.memberviewlist._validationid.a14' arguments='"+email+"' />"); 	<%-- 입력하신 [이메일]은 사용가능한 이메일입니다. --%>
	  				}else if('dupl' == data.msgCd) {
	  					<%-- 입력창 초기화 --%>
	  					$("#systemUserUpdateForm #modEmail").val("");
						$("#systemUserUpdateForm #modEmail").focus();
						
		  				alert("<spring:message code='app.manager.memberviewlist._validationid.a12' arguments='"+chkEmail+"'/>"); <%-- 입력하신 [이메일]은 이미 사용중입니다. --%>
		  			}
		  			else alert("<spring:message code='app.manager.memberviewlist._validationid.a13' />"); <%-- 이메일 중복검사 오류발생! --%>
	  		}, 'json');
	}


	/* 등록 또는 수정 처리 여부 - 참일 경우 부모창을 재조회 한다. */
	var _SYSTEM_UPDATE_PROC_COUNT = false;

	/* Layer Event Create */
	function setSystemUserUpdateLayerEvent(){

		/* 레이어 활성화 */
		$('#system_user_layer').show();
		$('#opacity').show();

		/* 레이어 드레그 Event */
		$("#system_user_layer").draggable({
			handle: $("h1")
			,cancle: $("a.close")
			,containment: "document"
			,scroll: false
		});

		/* 레이어 닫기버튼 Click Event */
		 $('#system_user_layer a.close').click(function(e){
			$("#authUserGroup tr").remove();
			$('#systemUserUpdateForm').find('input, select').map(function() {
				if(this.type != 'button') $(this).val('');
			});
			$('#t').find('input').map(function() {
				if(this.type == 'checkbox') $(this).prop('checked', false);
			});
			$('#system_user_layer').hide();
			$('#opacity').hide();

			$('*').filter(function(){
				return $(this).data('tooltipsterNs');
			}).tooltipster('hide');

			
			if(_SYSTEM_UPDATE_PROC_COUNT) eventSearch();			//  재조회

			//e.preventDefault();

		 });

	}

	/* 레이어호출 */
	function systemUserUpdateLayer(){

		var memberCd = "";
		memberCd = $('#systemUserUpdateForm input[name="memberCd"]').val();
		$('#newUserChk').val("N");
		_SYSTEM_UPDATE_PROC_COUNT = false;
		setSystemUserUpdateLayerEvent();

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url  : "<c:url value='/app/mgr/manager/mgrMember_selDetail'/>",
			data : JSON.stringify({'memberCd'   : memberCd }),
			success : function(data){
				if(data == null) alert('<spring:message code="app.manager.memberviewpop._systemUserUpdateLayer.a1" />'); /* 선택된 사용자 정보가 없습니다. */
				else setUpdateFormInit(data);
			}
		});
	}

	function setUpdateFormInit(data){

		$("#modPassLastDy").datepicker();

		var userData  = data.memberData;
		var groupList = data.groupList;
		
		if(userData) {

			//탈퇴회원인 경우 정보수정불가, 저장버튼 hide
			if(userData.withDrawFg == "T"){
				$("#btnSave").hide();
				$("#ifWithDraw_hideDiv").hide();
				$("#ifWithDraw_hideDiv").find('input, select').each(function(){
					$(this).attr("disabled",true);
				});
				$("#systemUserUpdateForm").find('input,select').each(function(){
					$(this).attr("disabled",true);
				});
			}else{
				$("#btnSave").show();
				$("#ifWithDraw_hideDiv").show();
				$("#ifWithDraw_hideDiv").find('input, select').each(function(){
					$(this).attr("disabled",false);
				});
				$("#systemUserUpdateForm").find('input,select').each(function(){
					if(this.id != "masterId" && this.id != "compNm"){
						$(this).attr("disabled",false);
					}
				});
			}
			
			$("#validationIdCheck").hide();
			$("#validationEmailCheck").hide();
			$("#systemUserUpdateForm #newUserChk").val("N");
			$("#systemUserUpdateForm #modUserId").attr("readonly",true);
			$("#systemUserUpdateForm #modUserNm").attr("readonly",true);
			$("#systemUserUpdateForm #modEmail").attr("readonly",true);
			$("#systemUserUpdateForm #findCompany").hide();

			$('#systemUserUpdateForm input[name="userNm"]').val(userData.userNm);
			$('#systemUserUpdateForm input[name="memberCd"]').val(userData.memberCd);
			$('#systemUserUpdateForm input[name="userId"]').val(userData.userId);
			$('#systemUserUpdateForm input[name="userIdOld"]').val(userData.userId);
			$('#systemUserUpdateForm input[name="hiddenPasswd"]').val(userData.userPw);
			$('#systemUserUpdateForm input[name="telNo"]').val(userData.telNo);
			$('#systemUserUpdateForm input[name="hpNo"]').val(userData.hpNo);
			$('#systemUserUpdateForm select[name="userType"]').val(userData.userType);
			$('#systemUserUpdateForm input[name="email"]').val(userData.email);
			$('#systemUserUpdateForm input[name="masterId"]').val(userData.masterId);
			$('#systemUserUpdateForm input[name="compNm"]').val(userData.compNm);
			$('#systemUserUpdateForm input[name="positionNm"]').val(userData.positionNm);
			$('#systemUserUpdateForm input[name="classNm"]').val(userData.classNm);
			$('#systemUserUpdateForm input[name="passLastDy"]').val(userData.passLastDyFmt);
			$('#systemUserUpdateForm input[name="emailOld"]').val(userData.email);

			var passFailCnt = userData.passFailCnt == null ? '0' : userData.passFailCnt;
			$('#systemUserUpdateForm input[name="passFailCnt"]').val(passFailCnt);
			$('#systemUserUpdateForm span[name="passFailCnt"]').text("("+passFailCnt+")");

			$('#systemUserUpdateForm input[name="lockYn"]').val(userData.lockYn);

// 			$('#systemUserUpdateForm #findPwdCd_U').val(userData.findPwdCd);
// 			$('#systemUserUpdateForm #findPwdAnswer_U').val(userData.findPwdAnswer);
			
			if(userData.passChgYn =="N")
				$("#systemUserUpdateForm").find("input[name='passChgYn']")[0].checked = true;
			else  $("#systemUserUpdateForm").find("input[name='passChgYn']")[1].checked = true;

			if(userData.useYn =="Y")
				$("#systemUserUpdateForm").find("input[name='useYn']")[0].checked = true;
			else  $("#systemUserUpdateForm").find("input[name='useYn']")[1].checked = true;

			chkEmailValid = "Y";		<%-- email 중복확인 여부 기본세팅 : 기존회원 --%>
		}else{
			$('#systemUserUpdateForm input[name="passLastDy"]').val(getConvertDateReverse('99991230'))
		}
		if(groupList != null && groupList.length >0){
			var lngth = groupList.length;
			for(var k=0;k<lngth;k++){
				groupList[k].count = k+1;

				groupList[k].groupLocaleNm = groupList[k].groupLocaleNm;
			}
		}
		$("#authUserGroupTemplate").tmpl(groupList).appendTo("#authUserGroup");
		
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
<div class="pop_layer_new" id="system_user_layer" style="position:absolute;left:25%;top:-5%;width:800px; display:none;">
    <h1><spring:message code="app.manager.memberviewpop.title.userinfo" /></h1> <%--사용자정보 --%>
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
            <div class="tit_area">
                <h2><spring:message code="app.manager.memberviewmodpop.title.userinfo" /></h2> <%--사용자정보 --%>
                <div>
                	<html:button id="btnSave"    auth="save" msgKey="button.save"/><%--저장 --%>
                </div> 
            </div>
            <form id="systemUserUpdateForm" name="systemUserUpdateForm" method="post">
            <input type=hidden id='modHiddenMemberCd' name='memberCd'>
	            <input type=hidden id='modHiddenPassFailCnt' name='passFailCnt'>
	            <input type=hidden id='modHiddenlockYn' name='lockYn'>
	            <input type=hidden id='modHiddenPasswd' name='hiddenPasswd'>
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
		                        <td><input type="text" style="width: 90%" name="userNm" id="modUserNm" onkeyup="chkByte(this,150);" value="<c:out value='${data.userNm}'></c:out>" ></td>
		                        <th>* <spring:message code="app.manager.memberviewmodpop.label.userid" /></th> <%--아이디 --%>
		                        <td><input type="text" style="width: 90%; ime-mode:disabled;" name="userId" id="modUserId" value="<c:out value='${data.userId}'></c:out>" >
		                         <input type="button" id="validationIdCheck" class="button btn_gray02"
		                         <spring:message code="button.duplchk" var="duplchk"/>
		                         value="<c:out value='${duplchk }'></c:out>"> <%--중복확인 --%>
		                         <input type="hidden" id="userIdOld" name="userIdOld" value="<c:out value='${data.userId}'/>">
		                         <input type="hidden" id="modNewUserChk" name="newUserChk" value="N"/>
		                        </td>
		                    </tr>
		                    <!-- <tr>
		                        <th>* <spring:message code="app.manager.memberviewmodpop.label.userpw" /></th> <%--비밀번호 --%>
		                        <td><input type="password" style="width: 90%" name="userPw"   id="userPw" value=""></td>
		                        <th>* <spring:message code="app.manager.memberviewmodpop.label.userpwchk" /></th> <%--비밀번호 확인--%>
		                        <td><input type="password" style="width: 90%" name="userPwCheck"  id="userPwCheck" value=""></td>
		                    </tr> -->
		                    <tr>
		                        <th><spring:message code="app.manager.memberviewmodpop.label.telno" /></th> <%--전화번호 --%>
		                        <td><input type="text" style="width: 90%" name="telNo"  id="modTelNo" oninput ="fncSetDefInputHpNo(this);" onkeyup="chkByte(this,14);" value="<c:out value='${data.telNo}'></c:out>"></td>
		                        <th>* <spring:message code="app.manager.memberviewmodpop.label.hpno" /></th> <%--휴대폰번호 --%>
		                        <td><input type="text" style="width: 90%" name="hpNo"    id="modHpNo" oninput ="fncSetDefInputHpNo(this);" onkeyup="chkByte(this,14);" value="<c:out value='${data.hpNo}'></c:out>"></td>
		                    </tr>
		                    <tr>
		                    	<th>* <spring:message code="app.manager.memberviewmodpop.label.usertype" /></th> <%--사용자유형 --%>
		                        <td>
		                        	<html:codeTag comType="SELECT" objId="modUserType" objName="userType" parentID="9001"  defName="${commonChoice }" />
		                        </td>
		                        <th>* <spring:message code="app.manager.memberviewmodpop.label.email" /></th> <%--이메일 --%>
		                        <td>
		                        	<input type="text" style="width: 90%;  ime-mode:disabled; " name="email" id="modEmail" value="<c:out value='${data.email}'></c:out>">
			                        <input type="button" id="validationEmailCheck" class="button btn_gray02" value="<spring:message code='button.duplchk'/>"/> <%--중복확인 --%>
			                        <input type="hidden" id="modEmailOld" name="emailOld" value="<c:out value='${data.email}'/>"/>
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
		                        	<input type="text" readonly class="typeGHL" style="width: 100px;" name="masterId" id="modMasterId" value="<c:out value='${data.masterId}'></c:out>">
		                        	<input type="text" readonly style="width: 300px;" name="compNm" id="modCompNm" value="<c:out value='${data.compNm}'></c:out>">
		                        	<input type="button" id="findCompany"  class="button btn_gray02" value='<spring:message code="common.search"/>'>	 <%--찾기 --%>
		                        </td>
		                    </tr>
		                     <tr>
		                        <th><spring:message code="app.manager.memberviewmodpop.label.positionnm" /></th> <%--직책 --%>
		                        <td><input type="text" style="width: 90%" name="positionNm" id="modPositionNm" onkeyup="chkByte(this,100);" value="<c:out value='${data.positionNm}'></c:out>" ></td>
		                        <th><spring:message code="app.manager.memberviewmodpop.label.classnm" /></th> <%--직급 --%>
		                        <td><input type="text" style="width: 90%" name="classNm" id="modClassNm" onkeyup="chkByte(this,100);" value="<c:out value='${data.classNm}'></c:out>" ></td>
		                    </tr>
		                    <tr>
		                        <th><spring:message code="app.manager.memberviewmodpop.label.passchgyn" /></th> <%--비밀번호 필수변경여부 --%>
		                        <td>
		                        	<input type="radio"  id="modPassChgY" name="passChgYn" value="N" checked >
		                        	<label for="modPassChgY"><spring:message code="app.manager.memberviewmodpop.passChgNo" /></label> <%--변경안함 --%>
		                        	<input type="radio"  id="modPassChgN" name="passChgYn" value="Y"  >
		                        	<label for="modPassChgN"><spring:message code="app.manager.memberviewmodpop.passChgYes" /></label> <%--필수변경 --%>&nbsp;
		
					            </td>
		                        <th><spring:message code="app.manager.memberviewmodpop.label.passlastdy" /></th> <%--다음변경일 --%>
		                        <td>
		                        	<input type="text" style="width: 80%; margin-right:4px" name="passLastDy" id="modPassLastDy" />
		                        </td>
		                    </tr>
		                     <tr>
		                        <th><spring:message code="app.manager.memberviewmodpop.label.useyn" /></th><%--사용여부 --%>
		                        <td>
		                        	<input type="radio"  id="modUseY" name="useYn" value="Y" checked="checked" >
		                        	<label for="modUseY"><spring:message code="app.manager.memberviewmodpop.UseYes" />&nbsp; </label><%--사용 --%>
					                <input type="radio"  id="modUseN" name="useYn" value="N"   >
					                <label for="modUseN"><spring:message code="app.manager.memberviewmodpop.UseNo" /></label> <%--미사용 --%>
		                        </td>
		                        <th><spring:message code="app.manager.memberviewmodpop.label.lockYn" /></th>
		                        <td>
		                        	<input type="radio" id="modLockY" name="lockYn" value="Y" />
			                        <label for ="modLockY" ><spring:message code="app.manager.memberviewmodpop.UseYes" />&nbsp; <%--사용 --%></label>
						            <input type="radio" id="modLockN" name="lockYn" value="N" checked="checked"/>
		                     		<label for ="modLockN" ><spring:message code="app.manager.memberviewmodpop.UseNo" /> <%--미사용 --%></label>
						            <span name="passFailCnt"></span>
						        </td>
		                    </tr>
	                    </tbody>
				</table>
			</form>
			<div id="ifWithDraw_hideDiv">
				<%-- <c:if test='${userSession.userType == "SA"}'>로그인 유저가 내부관리자인지 확인
			        <div class="tit_area" id="userPasswordChangeTitle">
		                <h2><spring:message code="app.manager.suMemberviewpop.title.userPwChg" /></h2> 비밀번호 변경
		            </div>
	
	        		<input type="hidden" name="bmanNo" value=""/>사업자번호
	
	        		<table class="type1" summary="<spring:message code="app.manager.suMemberviewpop.webacc.pctag.table.summary" />"> 비밀번호 변경
	                    <caption><spring:message code="app.manager.suMemberviewpop.webacc.pctag.table.caption" /></caption> 비밀번호 변경
	                    <colgroup>
		                    <col width="300">
		                    <col width="*">
	                    </colgroup>
	                    <tbody>
		                    <tr>
		                        <th><spring:message code="app.manager.suMemberviewpop.label.reqpasschg" /></th> 비밀번호 변경요청
		                        <td style='text-align:center'><input type="button" id="pwdchSendBtn" class="button btn_red" value=" <spring:message code="app.manager.suMemberviewpop.label.send" /> "/></td>발송
		                    </tr>
						</tbody>
					</table>
	        	</c:if> --%>
				
				 <div class="tit_area">
	                <h2><spring:message code="app.manager.memberviewpop.title.authgroup" /></h2> <%--사용권한 그룹 --%>
	                <div>
	
	                </div>
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
		                    	<tbody id="authUserGroup"></tbody>
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
</div>


