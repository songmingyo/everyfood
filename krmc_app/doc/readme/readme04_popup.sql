#1. \uD31D\uC5C5import #####################################################################################

1. \uAE30\uBCF8\uD615
	1) smpLnchPrcond.jsp \uCC38\uACE0
	
		<!-- \uC5C5\uCCB4\uCC3E\uAE30 \uD31D\uC5C5-->
			<jsp:include page="/WEB-INF/views/common/popupFind/comPopSrchComp.jsp">
				<jsp:param name="popupId" value="popSrchComp"/>
			</jsp:include>
		<!-- \uC5C5\uCCB4\uCC3E\uAE30 \uD31D\uC5C5-->
		
2. \uD0ED\uC5D0\uC11C\uB3C4 \uD31D\uC5C5\uC744 \uC0AC\uC6A9\uD560\uB54C
	2) \uD0ED \uB9AC\uC2A4\uD2B8\uB97C \uD3EC\uD568\uD55C jsp\uC5D0 \uD31D\uC5C5 import
	 	ex) smpLnchDtl.jsp, smpLnchCompTab.jsp
	 	
#2. \uD31D\uC5C5 \uC0AC\uC6A9\uC608\uC2DC #####################################################################################
	- smpLnchPrcond.jsp \uCC38\uACE0

	1) \uD31D\uC5C5 \uD638\uCD9C	
		/*\uC5C5\uCCB4\uCC3E\uAE30 \uD31D\uC5C5 \uD638\uCD9C*/
		function fnOpenPopSrchComp(gbn){
			
			var paramObj = {};
				
			var srchCompNm = $("#searchForm input[name='srchCompNm']").val();
			paramObj['srchCompNm'] = srchCompNm;
			
			// {\uD31D\uC5C5id}.fnOpenLayer(\uCF5C\uBC31\uD568\uC218,\uAC80\uC0C9param, \uC870\uD68C\uACB0\uACFC \uAC2F\uC218\uC5D0 \uB530\uB978 return\uC5EC\uBD80:Y\uC774\uBA74 \uC870\uD68C\uACB0\uACFC\uAC00 1\uAC1C\uC77C\uB54C \uBC14\uB85C return, multiselect\uC5EC\uBD80)
			popSrchComp.fnOpenLayer(fnOpenPopSrchCompCallBack, paramObj,gbn,null);  
		}
		
	2) \uCF5C\uBC31 \uD568\uC218
		/*\uC5C5\uCCB4\uCC3E\uAE30 \uCF5C\uBC31*/
		function fnOpenPopSrchCompCallBack(data){
			$("#searchForm #srchCompNm").val(data.compNm);	//\uC5C5\uCCB4\uBA85 \uC14B\uD305
			eventSearch();
		}
