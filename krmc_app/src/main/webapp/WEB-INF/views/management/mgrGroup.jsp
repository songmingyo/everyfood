<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script>
	$(document).ready(function($){

		initSearch();
		initAuthMenuList();

		/* BUTTON CLICK 이벤트 처리 ----------------------------------------------------------------------------*/
		$('#btnSearch').unbind().click(null,	eventSearch);			// 검색버튼
		$('#btnAuth').unbind().click(null,		eventAuth);				// 사용자권한 조회팝업

		$('#btnDelete').unbind().click(null,	eventDelete);			// 삭제
		$('#btnNew').unbind().click(function()  { systemGroupUpdateLayer('I'); });	// 신규
		$('#btnEdit').unbind().click(function() { systemGroupUpdateLayer('U'); });	// 수정

		$('#btnSave').unbind().click(null,   eventSave);	 			// 저장

		//그룹아이디,그룹명칭 조회조건 입력필드 enter key이벤트 --------------
		$('#groupId, #groupNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : eventSearch(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------


		/* 권한체크박스 Click Event */
		$(document).on('click', '#tabList2 input[type="checkbox"]' , function(e){
			var name = $(this).attr('name');
			var checked = $(this).is(':checked');
			var trObject = $(this).parent().parent();
			var trChild = trObject.children();

			if(checked) $(this).val('Y');
			else $(this).val('');

			if(name == "authAllYn"){
				if(checked){
					trChild.children('input[type="checkbox"]').each(function(i,v){
						$(this).prop('checked',true);
						$(this).val('Y');
					});

				}else{
					trChild.children('input[type="checkbox"]').each(function(i,v){
						$(this).prop('checked',false);
						$(this).val('');
					});
				}
			}else{
				if(trChild.children('input[type="checkbox"][name!="authAllYn"][value="Y"]').length == 5){
					trChild.children('input[name="authAllYn"]').prop('checked',true);
					trChild.children('input[name="authAllYn"]').val('Y');
				}else{
					trChild.children('input[name="authAllYn"]').prop('checked',false);
					trChild.children('input[name="authAllYn"]').val('');
				}
			}
		});
		/*----------------------------------------------------------------------------------------------------*/


		//grid resize
		$(window).bind('resize', function() {
            try{
                // width
                // 그리드의 width를 div 에 맞춰서 적용
                $('#tabList').setGridWidth($('#grid1container').width()); //Resized to new width as per window
                $('#tabList2').setGridWidth($('#grid2container').width()); //Resized to new width as per window

             	// height
				var height = $(window).height()-$('#grid1container')[0].offsetTop;

				if(height > 275) {
					$('#tabList').setGridHeight(120);

					if(height-320 < 120)
						$('#tabList2').setGridHeight(120);
					else
						$('#tabList2').setGridHeight(height-320);
				}
            }catch(e){}
        }).trigger('resize');

	});


	//그리드 초기화
	function initSearch() {
		var groupId   = $('#groupId').val();
        var groupNm   = $('#groupNm').val();

        $('#selectedGroupId').val('');      // 선택된 그룹아이디 초기화
        $('#selectedGroupNm').val('');      // 선택된 그룹명 초기화
        $('#selectedSysYn').val('');        // 시스템빌드인여부 초기화

        $("#tabList2").jqGrid('clearGridData').trigger("reloadGrid");      // 권한 그리드 초기화


        var str = {'groupId'   : groupId,  'groupNm'   : groupNm};

        $("#tabList").jqGrid({
            url:'<c:url value="/app/mgr/manager/mgrGroup_selGroupList" />',            		  // url 주소
            datatype: "json",                                                // 보내는 데이터 타입
            postData: str,                                                   // 보내는 데이터 형식
            mtype:'POST',                                                    // POST,GET,LOCAL 3가지 존재
            //ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
			//jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
	        loadBeforeSend: function(jqXHR) {
                jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
            },
            jsonReader : {
                root:  "rows",
                repeatitems: false,
            },

            // 헤더에 들어가는 이름
            colNames:[
				 '<spring:message code="app.manager.groupviewlist.head.groupid" />'		<%-- 그룹아이디 --%>
				,'<spring:message code="app.manager.groupviewlist.head.groupnm" />'		<%-- 그룹명칭 --%>
				,'<spring:message code="app.manager.groupviewlist.head.groupdesc" />'	<%-- 그룹설명 --%>
				,'<spring:message code="app.manager.groupviewlist.head.useyn" />'		<%-- 사용여부 --%>
				,'<spring:message code="app.manager.groupviewlist.head.buildyn" />'		<%-- 빌드인여부 --%>
				,'<spring:message code="app.manager.groupviewlist.head.groupFg" />'		<%-- 그룹사용 유형 --%>
        	],

            colModel:[
                {name:'groupId'     	, index:'groupId'    		, sortable:true   , width:50 , align:"center"},
        		{name:'groupLocaleNm' 	, index:'groupLocaleNm'   	, sortable:true   , width:120, align:"left"},
                {name:'groupDesc'   	, index:'groupDesc'  		, sortable:true   , width:350, align:"left"},
                {name:'useYn'       	, index:'useYn'      		, sortable:true   , width:40,  align:"center"},
                {name:'sysYn'       	, index:'sysYn'      		, sortable:true   , width:50,  align:"center"},
                {name:'groupFgNm'  		, index:'groupFgNm' 		, sortable:true   , width:50,  align:"center"},
            ],
            gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
                var colCount = $(this).getGridParam("colNames").length;
                $("#blankRow td:nth-child(2)").attr("colspan", colCount);
                $(window).resize();

                var allRows = jQuery("#tabList").jqGrid('getDataIDs');			// 전체 rowCount
                for(var i = 0; i < allRows.length; i++){
					var cl = allRows[i];
					var rowAllData = jQuery("#tabList").getRowData(allRows[i]);
				}

            	$(window).resize();

            },
            loadComplete: function() {
            	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
                if ($(this).getGridParam("records")==0) {

                    var firstColName = $(this).getGridParam("colModel")[1].name;
                    var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                    $(this).addRowData("blankRow", msg);
                }

            	$(window).resize();
            },
            loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
            	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
                return false;
            },
            onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
                if (id == 'blankRow') {
                    return;
                }

                var rowdata = $(this).getRowData(id);

                if(!rowdata.groupId || rowdata.groupId == '') return;

                setGroupList(rowdata);
            },

            rowNum:20,                          // 한번에 출력되는 갯수
            loadui : "disable",                 //  이거 안 써주니 로딩 창 같은게 뜸// 데이터 출력시 화면 조절
            gridview:    true,                  // 꼭써주어야한다....
            //pager: '#pageList',
            sortname: 'groupNm',
            sortorder: 'asc',
            emptyrecords : '<spring:message code="message.search.no.data" />',    <%-- 조회결과가 없습니다.--%>
            viewrecords: true,
            scroll : true,
            rownumbers:true,                   //맨앞 일련번호
            loadonce:true,
            autowidth:true
        });
	}


	/* 그룹메뉴권한 조회버튼 이벤트 처리 json function  */
	function initAuthMenuList() {
        var parentId = '';
        var attrSetting = function (rowId, val, rawObject, cm) {
            var result = '';
            if (cm.name == 'parentNm' || cm.name =='parentSubNm') {
                if (rawObject.parentId != parentId && rawObject.rowspan) {
                    result = ' rowspan=' + '"' + rawObject.rowspan + '"';
                } else {
                    result = ' style="display:none;"';
                }
                parentId = rawObject.parentId;
            }
            return result;
        };
        var ellipsisFormatter = function(cellvalue, options, rowObject) {
			return '<span class="ellipsis" title="' + cellvalue + '">' + cellvalue + '</span>';
		};
        var hiddenFormatter = function (cellvalue, options, rawObject) {
            var result = '<input type="hidden" ';
            var col = options.colModel;

            // name 속성
            var attrName = ' name="' + col.name + '" '

            // value 속성
            var attrValue = ' value="' + cellvalue + '" ';

            result += attrName + attrValue + ' />';
            return result;
        }
        var checkboxFormatter = function (cellvalue, options, rawObject) {
            var result = '<input type="checkbox" ';
            var col = options.colModel;

            // name 속성
            var attrName = ' name="' + col.name + '" '

            // value 속성
            var attrValue = ' value="' + cellvalue + '" ';

            // checked 속성
            var attrChecked = '';
            var hidden = '';
            if (col.name == 'pgmQry') {
                if (rawObject.pgmQry == 'Y') {
                    attrChecked = ' checked="checked" ';
                }
                hidden = '<input type="hidden" name="' + col.name + 'Old" value="' + rawObject.pgmQry + '" />';
            } else if (col.name == 'pgmInt') {
                if (rawObject.pgmInt == 'Y') {
                    attrChecked = ' checked="checked" ';
                }
                hidden = '<input type="hidden" name="' + col.name + 'Old" value="' + rawObject.pgmInt + '" />';
            } else if (col.name == 'pgmUpt') {
                if (rawObject.pgmUpt == 'Y') {
                    attrChecked = ' checked="checked" ';
                }
                hidden = '<input type="hidden" name="' + col.name + 'Old" value="' + rawObject.pgmUpt + '" />';
            } else if (col.name == 'pgmDel') {
                if (rawObject.pgmDel == 'Y') {
                    attrChecked = ' checked="checked" ';
                }
                hidden = '<input type="hidden" name="' + col.name + 'Old" value="' + rawObject.pgmDel + '" />';
            } else if (col.name == 'pgmPrt') {
                if (rawObject.pgmPrt == 'Y') {
                    attrChecked = ' checked="checked" ';
                }
                hidden = '<input type="hidden" name="' + col.name + 'Old" value="' + rawObject.pgmPrt + '" />';
            } else if (col.name == 'authAllYn') {
                if (rawObject.authAllYn == 'Y') {
                    attrChecked = ' checked="checked" ';
                }
                hidden = '<input type="hidden" name="' + col.name + 'Old" value="' + rawObject.authAllYn + '" />';
            }

            result += attrName + attrValue + attrChecked + ' />' + hidden;
            return result;
        }

        $("#tabList2").jqGrid({
            datatype: 'local',
            data: [],

            colNames:[
					     '<spring:message code="app.manager.groupviewlist.head.parentnm" />' <%-- 상위분류명 --%>
						,'<spring:message code="app.manager.groupviewlist.head.title" />'	 <%-- 프로그램명 --%>
						,'<spring:message code="app.manager.groupviewlist.head.excType" />'  <%-- Dev.--%>
						,'<spring:message code="app.manager.groupviewlist.head.webpage" />'	 <%-- 실행URL --%>
						,'<spring:message code="app.manager.groupviewlist.head.qry" />'		 <%-- 조회 --%>
						,'<spring:message code="app.manager.groupviewlist.head.int" />'		 <%-- 등록 --%>
						,'<spring:message code="app.manager.groupviewlist.head.upt" />'		 <%-- 수정 --%>
						,'<spring:message code="app.manager.groupviewlist.head.del" />'		 <%-- 삭제 --%>
						,'<spring:message code="app.manager.groupviewlist.head.prt" />'		 <%-- 출력 --%>
						,'<spring:message code="app.manager.groupviewlist.head.all" />'		 <%-- 전체 --%>
			            ,'groupId'
                        ,'menuId'
                        ,'parentId'
                        ,'rowspan'
                     ],     // 헤더에 들어가는 이름


            colModel:[




            	{name:'parentLocaleNm'    , index:'parentLocaleNm'    , width:240, align:"right"
	        		,cellattr: function (rowId) {return 'id=\'parentLocaleNm' + rowId + "\'";} },		  		// 상위분류추가명
	        	{name:'titleLocaleNm' , index:'titleLocaleNm'       , width:200, align:"left" },      			// 메뉴명
	        	{name:'excTypeFg'	  , index:'excTypeFg'   , width:35,	 align:"center",  sortable:false },  	// DEVICE[W/M/R]
                {name:'webPage'       , index:'webPage'     , width:400, align:"left"  ,  formatter:ellipsisFormatter },		// URL
                {name:'pgmQry'        , index:'pgmQry'      , width:50,  align:"center",  formatter:checkboxFormatter },
                {name:'pgmInt'        , index:'pgmInt'      , width:50,  align:"center",  formatter:checkboxFormatter },
                {name:'pgmUpt'        , index:'pgmUpt'      , width:50,  align:"center",  formatter:checkboxFormatter },
                {name:'pgmDel'        , index:'pgmDel'      , width:50,  align:"center",  formatter:checkboxFormatter },
                {name:'pgmPrt'        , index:'pgmPrt'      , width:50,  align:"center",  formatter:checkboxFormatter },
                {name:'authAllYn'     , index:'authAllYn'   , width:50,  align:"center",  formatter:checkboxFormatter },
                {name:'groupId'       , index:'groupId'     , hidden:true,  formatter:hiddenFormatter },
                {name:'menuId'        , index:'menuId'      , hidden:true,  formatter:hiddenFormatter },
                {name:'parentId'      , index:'parentId'    , hidden:true},
                {name:'rowspan'       , index:'rowspan'     , hidden:true}

            ],
            gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
                var colCount = $(this).getGridParam("colNames").length;
                $("#blankRow2 td:nth-child(2)").attr("colspan", colCount)
                                              .attr("style", "text-align: center;");
                Merger('tabList2', 'parentLocaleNm');

	            $(window).trigger('resize');
            },

            loadError:function(xhr, status, error) {           				//데이터 못가져오면 실행 됨
            	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
               return false;
            },
            onSelectRow : function(id, status, e) {                                  //행 선택시 이벤트
                if (id == 'blankRow2') {
                    return;
                }

                var rowdata = $(this).getRowData(id);
            },
	        sortname: 'parentSubNm',
            cmTemplate: {sortable:false},                                         // Column Model Templates
            rowNum:500,                                                            // 한번에 출력되는 갯수
            loadui : "disable",                                                   //  이거 안 써주니 로딩 창 같은게 뜸// 데이터 출력시 화면 조절
            gridview:    true,                                                    // 꼭써주어야한다....
            //pager: '#pageList2',
            emptyrecords : '<spring:message code="message.search.no.data" />',    <%-- 조회결과가 없습니다.--%>
            viewrecords: true,
            scroll : true,
            rownumbers:true,                                                      //맨앞 일련번호
        	loadonce:true,
        	autowidth:true

        });

	}

	//조회버튼 클릭 이벤트
	function eventSearch(){
		var groupId   = $('#groupId').val();
        var groupNm   = $('#groupNm').val();
        var groupFg   = $('#groupFg').val();


        $('#selectedGroupId').val('');      // 선택된 그룹아이디 초기화
        $('#selectedGroupNm').val('');      // 선택된 그룹명 초기화
        $('#selectedSysYn').val('');        // 시스템빌드인여부 초기화

        $("#tabList2").jqGrid('clearGridData').trigger("reloadGrid");      // 권한 그리드 초기화

        var str = {'groupId'   : groupId,  'groupNm'   : groupNm,  'groupFg'  : groupFg};

        $("#tabList").jqGrid('setGridParam',{
            datatype: "json",
            postData: str,
            mtype:'POST'
        }).trigger("reloadGrid");
	}



	/*사용자별 그룹권한 조회/등록/수정 버튼 이벤트*/
	function eventAuth(){
		var groupId = $('#selectedGroupId').val();

		if(!groupId){
			alert('<spring:message code="app.manager.groupviewlist._eventauth.a1" />'); <%--선택된 그룹이 없습니다.--%>
			return;
		}

		userAuthGroupUpdateLayer(groupId);

		/*
		var param = new Object()
		, site = "/app/manager/mgrGroup_selGroupData.po?groupId=" +groupId
		, style ="dialogWidth:800px;dialogHeight:550px;";

		param.groupId = groupId;
		param.reLoad;
		window.showModalDialog(site, param ,style);

		if(param && param.reLoad) _eventSearch();	// 변동정보가 있으면 화면 다시 조회
		*/

		return false;
	}


	function setGroupList(rowdata){
		var groupId = rowdata.groupId;
		var groupNm = rowdata.groupNm;
		var sysYn = rowdata.sysYn;

		//삭제/수정, 권한메뉴 조회용 Parameter--
		$('#selectedGroupId').val(groupId);
		$('#selectedGroupNm').val(groupNm);
		$('#selectedSysYn').val(sysYn);

		authMenuList();	// ROW 선택후 선택된 그룹의 권한메뉴 리스트 조회
	}


	/* 그룹메뉴권한 조회버튼 이벤트 처리 json function  */
	function authMenuList() {
		if(!$('#selectedGroupId').val()) {
			alert('<spring:message code="app.manager.groupviewlist.authmenulist.a1" />');<%--권한그룹을 선택하세요!--%>
            return;
        }

        var str = {'groupId' : $('#selectedGroupId').val(), // 권한그룹코드
                   'menuId'  : $('#selectedMenuId').val()   // 프로그램아이디
                  };

        $("#tabList2").jqGrid('setGridParam',{
            url:'<c:url value="/app/mgr/manager/mgrGroup_selAuthMenuList" />',
            datatype: "json",
            postData: str,
            mtype:'POST',
            //jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
	        loadBeforeSend: function(jqXHR) {
                jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
            },
            jsonReader : {
                root:  "rows",
                repeatitems: false,
            }

        }).trigger("reloadGrid");
	}


	/*권한그룹 onChanged EVENT*/
	function changemenuAuthGrp() {
		authMenuList();
	}


	//삭제버튼 클릭 이벤트
	function eventDelete(){
		var groupId = $('#selectedGroupId').val();
		var groupNm = $('#selectedGroupNm').val();
		var sysYn   = $('#selectedSysYn').val();

		if(!groupId){
			alert('<spring:message code="app.manager.groupviewlist._eventdelete.a1" />'); <%--선택된 그룹이 없습니다.--%>
			return;
		}

		if(sysYn =="Y") {
			alert('<spring:message code="app.manager.groupviewlist._eventdelete.a2" />');<%--빌트인된 그룹은 삭제할 수 없습니다.--%>
			return;
		}

		// 삭제확인 메세지 --------------------------------
		if (!confirm( groupNm+" ["+groupId+"] " + '<spring:message code="app.manager.groupviewlist._eventdelete.c1" />')) { <%--삭제 하시겠습니까?--%>
		        return false;
		}
		//-----------------------------------------------

		var str = {'groupId' : groupId // 그룹아이디
		  };

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/mgr/manager/mgrGroup_delGroupData" />',
		      data : JSON.stringify(str),
		      success : function(data){

		    	if(data == null) msg = '<spring:message code="app.manager.groupviewlist._eventdelete.m1" />'; <%--"처리된 데이터가 없습니다.";--%>
	          	else if('success' == data.msgCd)
	            {
	          		msg = '<spring:message code="app.manager.groupviewlist._eventdelete.m2" />'; <%--"그룹 삭제가 정상적으로 처리되었습니다.";--%>
	            	eventSearch();			// 그룹 제조회
	            }
	          	else msg = '<spring:message code="app.manager.groupviewlist._eventdelete.m3" />'; <%--"그룹 삭제 중 오류가 발생하였습니다.";--%>

	          	alert(msg);
		      }
		});

	}


	/*권한메뉴 목록 저장버튼 이벤트*/
	function eventSave() {

		// 저장data 생성---------------------------------
		var authInfoList = [];
		$('input[name=menuId]').each(function() {
	    	// 선택된 사용자 정보 생성
	    	var authInfo = {};
			$(this).parents('tr:first').find('input').map(function(){
				authInfo[this.name] = $(this).val();
			});
			authInfoList.push(authInfo);

		});
		//-----------------------------------------------

		if(authInfoList.length <= 0) {
			alert('<spring:message code="app.manager.groupviewlist._eventsave.a1" />'); <%--조회된 메뉴목록이 없습니다. --%>
			return;
		}


		// 저장 이벤트 json 실행---------------------------
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url : '<c:url value="/app/mgr/manager/mgrGroup_insAuthMenu" />',
		      data : JSON.stringify({'authInfoList':authInfoList}),
		      success : function(data){
		    	    if(data == null) msg = '<spring:message code="app.manager.groupviewlist._eventsave.m1" />'; <%--"처리된 결과가 없습니다.";--%>
		          	else if('nodate' == data.msgCd) msg = '<spring:message code="app.manager.groupviewlist._eventsave.m2" />'; <%--"변경된 내용이 없습니다.";--%>
		          	else if('success' == data.msgCd) msg = '<spring:message code="app.manager.groupviewlist._eventsave.m3" />'; <%--"메뉴권한 설정이 정상적으로 처리되었습니다.";--%>
		          	else msg = '<spring:message code="app.manager.groupviewlist._eventsave.m4" />'; <%--"메뉴권한 설정중 오류가 발생하였습니다.";--%>

	          	alert(msg);
	          	authMenuList();
		      }
    	});
		//-----------------------------------------------

	}

</script>


<div id="section">
<input type="hidden" id="selectedGroupId" 	name="selectedGroupId">
<input type="hidden" id="selectedGroupNm" 	name="selectedGroupNm">
<input type="hidden" id="selectedSysYn" 	name="selectedSysYn">

	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
			<spring:message code="common.all" var="nameall" />  <%--전체  --%>
			<!-- 검색조건 start ----------------------------------------->
			<form id="searchForm" name="searchForm" method="post">
				<fieldset>
					<legend><spring:message code="app.manager.groupviewlist.webacc.hdtag.fieldset.legend" /></legend>		<%--마스터 디테일 그룹권한 검색 --%>
					<table style="width: 100%" summary="" class=type1>
						<caption><spring:message code="app.manager.groupviewlist.webacc.hdtag.table.caption" /></caption>	<%--마스터 디테일 그룹권한 검색 --%>
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
								<th><label for="sele2"><spring:message code="app.manager.groupviewlist.label.groupid" /></label></th>	<%-- 그룹아이디 --%>
								<td><input type="text" id="groupId" name="groupId"></td>
								<th><label for="sele2"><spring:message code="app.manager.groupviewlist.label.groupnm" /></label></th>	<%-- 그룹명 --%>
								<td><input type="text" id="groupNm" name="groupNm"></td>
								<th><label for="sele2"><spring:message code="app.manager.groupviewlist.label.groupfg" /></label></th>	<%-- 그룹명 --%>
								<td><html:codeTag comType="SELECT" objName="groupFg" parentID="9202" objId="groupFg" defName="${nameall}" /> </td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</form>
			<!-- 검색조건 end ------------------------------------------->


            <!-- 분류 서브타이틀 및 이벤트 버튼 start  -------------------->
			<div class="tit_area">
				<h2><spring:message code="app.manager.groupviewlist.title.group" /></h2>	<%--그룹 List --%>
				<div>
					<html:button id="btnNew" auth="insert"  /> 								<%-- 신규 --%>  
					<html:button id="btnEdit" auth="edit"   /> 								<%-- 수정 --%>
					<html:button id="btnDelete" auth="delete"  /> 							<%-- 삭제 --%>
					&nbsp;
					<html:button id="btnAuth" auth="select" msgKey="button.auth" /> 		<%-- 사용자권한 --%>
					<html:button id="btnSearch" auth="select" /> 							<%-- 조회 --%>
				</div>
			</div>
			<!-- 대분류 서브타이틀 및 이벤트 버튼 end     ---------------->

			<!-- centent List -------------------------->
            <div id="grid1container" class="gridcontainer">
                <table id="tabList" ><tr><td></td></tr></table>
            </div>

            <div id="pageList"></div>
            <!-- centent List -------------------------->


			<!--  서브타이틀 및 이벤트 버튼 start  -------------------->
            <div class="tit_area" >
            	<h2>
            		<spring:message code="app.manager.groupviewlist.title.auth" />	<%--그룹별 메뉴권한 List   --%>
            		&nbsp; &nbsp;
				</h2>
				<div>
					<span style="margin-right:10px">
	            		<img src="<c:url value='/resources/images/pearl/common/icons-subhead.gif'/>"/>
	            		<span style="font-weight: normal; color: #6265d4;">
	            			<spring:message code="app.manager.groupviewlist.label.sel" /> : <%--대분류메뉴  --%>
						</span>
						<html:codeTag  objId="selectedMenuId" objName="selectedMenuId"  comType="SELECT" dataType="MENU" parentID = "" event="onchange='changemenuAuthGrp(this);'" defName="${nameall}" width="150px;"/>
					</span>
					<html:button id="btnSave" 	auth="save"  />
				</div>
			</div>
			<!-- 서브타이틀 및 이벤트 버튼 end     ------------------>

			<!-- centent List -------------------------->
            <div id="grid2container" class="gridcontainer">
                <table id="tabList2" ><tr><td></td></tr></table>
            </div>

            <div id="pageList2"></div>
            <!-- centent List -------------------------->
</div>


<!-- 그룹별 사용자 권한 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/management/mgrGroupUserAuth.jsp" />
<!-- 그룹별 사용자 권한 레이어 영역 end  -->

<!-- 그룹정보 신규 등록 및 수정 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/management/mgrGroupModifyGroup.jsp" />
<!-- 그룹정보 신규 등록 및 수정 레이어 영역 end  -->
