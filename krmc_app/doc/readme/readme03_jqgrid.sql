#1. jqGrid \uAE30\uBCF8\uD615 #####################################################################################

$("#tabList").jqGrid({
		datatype: "local",  // \uBCF4\uB0B4\uB294 \uB370\uC774\uD130 \uD0C0\uC785
		data: [],
		// \uCEEC\uB7FC\uBA85
		colNames:[
			 '\uB300\uBD84\uB958'
			 ,hidden
		],
		// \uD5E4\uB354\uC5D0 \uB4E4\uC5B4\uAC00\uB294 \uC774\uB984
		colModel:[
			{name:'largeCatCdNm'		, index:'LARGE_CAT_CD_NM'		, sortable:false		, width:180		,align:"center"		},
			{name:'lnchStatCd'			, index:'LNCH_STAT_CD'			, hidden:true},
		],
		gridComplete : function() {                                      // \uB370\uC774\uD130\uB97C \uC131\uACF5\uC801\uC73C\uB85C \uAC00\uC838\uC624\uBA74 \uC2E4\uD589 \uB428
			var colCount = $(this).getGridParam("colNames").length;
			$("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
			$(window).resize();
		},
		loadComplete: function() {
			$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
			
			if ($(this).getGridParam("records")==0) {
				<%--\uC870\uD68C\uACB0\uACFC\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4. --%>
				$(this).addRowData("blankRow", {});
				$(this).find("#blankRow td:nth-child(2)").empty();
				$(this).find("#blankRow td:nth-child(2)").append("<spring:message code='message.search.no.data'/>");	
				
			}else{
				<%-- \uB370\uC774\uD130\uB97C \uC131\uACF5\uC801\uC73C\uB85C \uAC00\uC838\uC654\uC744\uB54C \uBCC4\uB3C4\uC758 \uB370\uC774\uD130 \uCC98\uB9AC\uAC00 \uD544\uC694\uD55C\uACBD\uC6B0 --%>
			}
			$(window).resize();
		},
		loadError:function(xhr, status, error) {										//\uB370\uC774\uD130 \uBABB\uAC00\uC838\uC624\uBA74 \uC2E4\uD589 \uB428
			alert('<spring:message code="message.error.process" />');					<%-- \uCC98\uB9AC\uC911 \uC624\uB958\uAC00 \uBC1C\uC0DD \uD558\uC600\uC2B5\uB2C8\uB2E4.--%>
			return false;
		},
		beforeSelectRow: function (rowid, e) {
		},
		onSelectRow : function(rowid, colld, val, e) {		//\uD589 \uC120\uD0DD\uC2DC \uC774\uBCA4\uD2B8
		},
		onCellSelect : function(rowid, iCell, content){		// \uC140 \uC120\uD0DD\uC2DC \uC774\uBCA4\uD2B8 (\uD55C\uBC88\uD074\uB9AD),iCell : \uC120\uD0DD\uC5F4\uBC88\uD638	, content : \uC120\uD0DD\uC140\uC758 \uAC12
		},
		ondblClickRow : function(id, cellcontent, iCol, e) {
        	
		},
		onSelectAll: function(aRowids,status) {	 // \uC804\uCCB4\uC120\uD0DD \uD074\uB9AD\uC2DC \uC774\uBCA4\uD2B8
		      	
		},
		<%-- jqGrid \uC18D\uC131, \uD544\uC694\uC5D0 \uB530\uB77C \uC8FC\uC11D\uCC98\uB9AC \uD558\uC5EC \uC0AC\uC6A9 (\uC0AD\uC81CX) --%>
		page: 1,															// \uD604\uC7AC \uD398\uC774\uC9C0
		rowNum: 500,														// \uD55C\uBC88\uC5D0 \uCD9C\uB825\uB418\uB294 \uAC2F\uC218
		rowList:[100,500,1000],												// \uD55C\uBC88\uC5D0 \uCD9C\uB825\uB418\uB294 \uAC2F\uC218 SelectBox
		pager: '#pageList',													// page\uAC00 \uBCF4\uC5EC\uC9C8 div
		loadui : "disable",													// \uC774\uAC70 \uC548 \uC368\uC8FC\uB2C8 \uB85C\uB529 \uCC3D \uAC19\uC740\uAC8C \uB738
		emptyrecords : '<spring:message code="message.search.no.data" />',  // row\uAC00 \uC5C6\uC744 \uACBD\uC6B0 \uCD9C\uB825 \uD560 text
		gridview: true,														// \uADF8\uB9AC\uB4DC \uC18D\uB3C4
		viewrecords: true,													// \uD558\uB2E8\uC5D0 1/1 \uB610\uB294 \uB370\uC774\uD130\uAC00\uC5C6\uC2B5\uB2C8\uB2E4 \uCD94\uAC00
		rownumbers:true,													// rowNumber \uD45C\uC2DC\uC5EC\uBD80
		sortorder: "DESC",                                       			// desc/asc
		loadonce : false,													// reload \uC5EC\uBD80. [true: \uD55C\uBC88\uB9CC \uB370\uC774\uD130\uB97C \uBC1B\uC544\uC624\uACE0 \uADF8 \uB2E4\uC74C\uBD80\uD130\uB294 \uB370\uC774\uD130\uB97C \uBC1B\uC544\uC624\uC9C0 \uC54A\uC74C]
// 		multiselect	: true,													// \uCCB4\uD06C\uBC15\uC2A4 show
		scroll : false,														// \uC2A4\uD06C\uB864 \uD398\uC774\uC9D5 \uC5EC\uBD80
		autowidth:true,
		shrinkToFit:false,													// \uCEEC\uB7FC width \uC790\uB3D9\uC9C0\uC815\uC5EC\uBD80 (\uAC00\uB85C \uC2A4\uD06C\uB864 \uC774\uC6A9\uD558\uAE30 \uC704\uD574 false\uC9C0\uC815)
	});
	
	
#2. jqGrid \uC0AC\uC6A9\uC608\uC2DC #####################################################################################
smpLnchPrcond.jsp > fncInitGrid \uCC38\uACE0

