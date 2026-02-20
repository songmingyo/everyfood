<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<jsp:directive.include
	file="/WEB-INF/views/common/include/incTaglib.jsp"
/>

<html>
<head>

<script type="text/javascript">
	/* onLoad or key event */
	$(document).ready(function($) {

		$("#searchDate").datepicker();

		_eventSearch();


		$("input[name$='dateType']").click(null, _eventSearch); // 조회기간 radio click event
		$("input[name$='typeFlage']").click(null, _eventSearch); // 집계기준 radio click event
		$("input[name$='sumFlage']").click(null, _eventSearch); // 합계row checkBox click event

		//사용자명,아이디,메뉴링크url,기간 조회조건 입력필드 enter key이벤트 --------------
		$('#searchDate').unbind().keydown(function(e) {
			switch (e.which) {
			case 13:
				_eventSearch();
				break; // enter
			default:
				return true;
			}
			e.preventDefault();
		});

	});



	function _eventSearch() {

		var dayType = $(':radio[name="dateType"]:checked').val(); // 검색 기간설정
		var typeFlage = $(':radio[name="typeFlage"]:checked').val(); // 검색 기준(user / session)
		var searchDate = $("#searchDate").val().replaceAll('/', ''); // 기준검색일자

		if (!$("#searchDate").val()) {
			alert('<spring:message code="app.manager.accessstatlist._eventsearch.al" />');
	<%-- 기준일자를 입력(선택)하세요! --%>
		$("#searchDate").focus();
				return;
			}

			var str = {
				dayType : dayType,
				typeFlage : typeFlage,
				searchDate : searchDate
			};

			$.ajax({
				contentType : 'application/json; charset=utf-8',
				type : 'post',
				dataType : 'json',
				async : false,
				url : "<c:url value='/app/mgr/manager/accessStat_selList'/>",
				data : JSON.stringify(str),
				success : function(data) {
					_setTbodyMasterValue(data);
					//Bar 차트
					//initBarChart(data);
					makeChartData(data);


				}
			});

	}



	/* _eventSearch() 후처리(data 마스터코드 객체 그리기) */
	function _setTbodyMasterValue(data) {

		var sz = data.length;
		var eleHtmlHead = [];
		var eleHtmlBody = [];

		var h = -1;
		var b = -1;

		$("#dataListbodys tr").remove();

		if (sz <= 0)
			return;

		var dayType = Number($(':radio[name="dateType"]:checked').val()); // 검색 기간설정
		var s01 = 0;
		var s02 = 0;
		var s03 = 0;
		var s04 = 0;
		var s05 = 0;
		var s06 = 0;
		var s07 = 0;
		var s08 = 0;
		var s09 = 0;
		var s10 = 0;

		for (var k = 0; k < sz; k++) {

			if (k == 0) {

				eleHtmlBody[++h] = "<tr height='23'>		 	\n";

				eleHtmlBody[++h] = "<td style='width: 148px;' ><img src='<c:url value="/resources/images/pearl/common/icons-time_day.gif" />' ></td>		 \n";

				if (dayType > 3) {

					if (dayType > 5) {
						eleHtmlBody[++h] = "<th>" + data[k].DT_01 + "</th>	\n";
						eleHtmlBody[++h] = "<th>" + data[k].DT_02 + "</th>	\n";
						eleHtmlBody[++h] = "<th>" + data[k].DT_03 + "</th>	\n";
						eleHtmlBody[++h] = "<th>" + data[k].DT_04 + "</th>	\n";
						eleHtmlBody[++h] = "<th>" + data[k].DT_05 + "</th>	\n";
					}
					eleHtmlBody[++h] = "<th>" + data[k].DT_06 + "</th>	\n";
					eleHtmlBody[++h] = "<th>" + data[k].DT_07 + "</th>	\n";
				}

				eleHtmlBody[++h] = "<th>" + data[k].DT_08 + "</th>	\n";
				eleHtmlBody[++h] = "<th><span style=margin-right:2px;><img src='<c:url value='/resources/images/pearl/common/icons-old.gif'/>' ></span>"
						+ data[k].DT_09 + "</th>	\n";
				eleHtmlBody[++h] = "<th><span style=margin-right:2px;><img src='<c:url value='/resources/images/pearl/common/icons-new.gif'/>' ></span><strong>"
						+ data[k].DT_10 + "</strong></th>	\n";

				eleHtmlBody[++h] = "</tr>		 				\n";

			} else {

				if (data[k].DTS == "1") {
					eleHtmlBody[++h] = "<tr bgcolor='#e2e7ff'>		 		\n";
					eleHtmlBody[++h] = "<th ><span style=margin-left:-10px;><img src='<c:url value='/resources/images/pearl/common/icons-old.gif'/>' ><strong style=margin-left:2px;>"
							+ data[k].TCODE_NM + "</strong></span></th>			\n";
				} else if (data[k].DTS == "2") {
					eleHtmlBody[++h] = "<tr  bgcolor='#f6f6f6'>		 		\n";
					eleHtmlBody[++h] = "<th ><span style=margin-left:-10px;><img src='<c:url value='/resources/images/pearl/common/icons-new.gif'/>' ><span style=margin-left:2px;>"
							+ data[k].TCODE_NM + "</span></span></th>			\n";
				} else {
					eleHtmlBody[++h] = "<tr>		 						\n";
					eleHtmlBody[++h] = "<th >" + data[k].TCODE_NM + "</th>		\n";
				}

				if (dayType > 3) {

					if (dayType > 5) {
						eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_01
								+ "</td> 	\n";
						eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_02
								+ "</td> 	\n";
						eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_03
								+ "</td> 	\n";
						eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_04
								+ "</td> 	\n";
						eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_05
								+ "</td> 	\n";
					}
					eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_06
							+ "</td> 	\n";
					eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_07
							+ "</td> 	\n";
				}
				eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_08
						+ "</td> 	\n";
				eleHtmlBody[++h] = "<td class=tdmr>" + data[k].DT_09
						+ "</td> 	\n";
				eleHtmlBody[++h] = "<td class=tdmr><strong><font color=#3a4dac>"
						+ data[k].DT_10 + "</font><strong></td> 	\n";

				eleHtmlBody[++h] = "</tr>		 							\n";

				s01 += Number(data[k].DT_01);
				s02 += Number(data[k].DT_02);
				s03 += Number(data[k].DT_03);
				s04 += Number(data[k].DT_04);
				s05 += Number(data[k].DT_05);
				s06 += Number(data[k].DT_06);
				s07 += Number(data[k].DT_07);
				s08 += Number(data[k].DT_08);
				s09 += Number(data[k].DT_09);
				s10 += Number(data[k].DT_10);
			}
		}

		if ($('#sumFlage').is(':checked')) {

			eleHtmlBody[++h] = "<tr style='background:#abafc5;'>		 					\n";
			eleHtmlBody[++h] = "<th><strong>Sum</strong></th>		 						\n";
			if (dayType > 3) {
				if (dayType > 5) {
					eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
							+ s01 + "</strong></th>		 		\n";
					eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
							+ s02 + "</strong></th>		 		\n";
					eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
							+ s03 + "</strong></th>		 		\n";
					eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
							+ s04 + "</strong></th>		 		\n";
					eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
							+ s05 + "</strong></th>		 		\n";
				}
				eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
						+ s06 + "</strong></th>		 		\n";
				eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
						+ s07 + "</strong></th>		 		\n";
			}
			eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
					+ s08 + "</strong></th>		 		\n";
			eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
					+ s09 + "</strong></th>		 		\n";
			eleHtmlBody[++h] = "<td class=tdmr><strong style=color:#ffffff>"
					+ s10 + "</strong></th>		 		\n";
			eleHtmlBody[++h] = "</tr>		 												\n";
		}

		$("#dataListbodys").append(eleHtmlBody.join(''));

	}


	//랜덤 컬러 생성 ----------------------------------------------------------
	function randomColorGenerator() {
		 return '#' + (Math.random().toString(16) + '0000000').slice(2, 8);
	}
	//--------------------------------------------------------------------



	//차트 데이터 생성 ----------------------------------------------------------
	function makeChartData(data){
		var areaChartData = {};
		var labels = [];
		var datasets = [];

		var dayType = Number($(':radio[name="dateType"]:checked').val()); // 검색 기간설정

		for(var i = 0; i<data.length; i++){
			//라벨 등록
			if(i == 0){
				if (dayType > 3) {
					if (dayType > 5) {
						labels.push(data[i].DT_01);
						labels.push(data[i].DT_02);
						labels.push(data[i].DT_03);
						labels.push(data[i].DT_04);
						labels.push(data[i].DT_05);
					}
					labels.push(data[i].DT_06);
					labels.push(data[i].DT_07);
				}
				labels.push(data[i].DT_08);
				labels.push(data[i].DT_09);
				labels.push(data[i].DT_10);
			}else{
				//datasets 등록
				var infoData = {};
				var valueData = [];

				if (dayType > 3) {
					if (dayType > 5) {
						valueData.push(data[i].DT_01);
						valueData.push(data[i].DT_02);
						valueData.push(data[i].DT_03);
						valueData.push(data[i].DT_04);
						valueData.push(data[i].DT_05);
					}
					valueData.push(data[i].DT_06);
					valueData.push(data[i].DT_07);
				}
				valueData.push(data[i].DT_08);
				valueData.push(data[i].DT_09);
				valueData.push(data[i].DT_10);

				infoData["label"] = data[i].TCODE_NM;
				infoData["backgroundColor"] = randomColorGenerator();
				infoData["borderColor"] = randomColorGenerator();
				infoData["data"] = valueData;
				datasets.push(infoData);
			}
		}
		areaChartData["labels"] = labels;
		areaChartData["datasets"] = datasets;

	  	myBarChart.config.data = areaChartData;
	  	myBarChart.update();

	}

</script>


</head>
<body>
	<div id="section">
		<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />

		<!-- 검색조건 start ----------------------------------------------------->
		<form id="searchForm" name="searchForm" method="post">
			<fieldset class="search_area">
				<%--마스터 접근통계 검색 --%>
				<table style="width: 100%" summary="" class=type1>
					<caption>
						<spring:message
							code="app.manager.accessstatlist.webacc.hdtag.table.caption"
						/>
					</caption>
					<%--마스터 접근통계 검색 --%>
					<colgroup>
						<col width="120">
						<col width="120">
						<col width="120">
						<col width="40%">
						<col width="140">
						<col width="35%">
						<col width="100">
						<col width="30">
					</colgroup>
					<tbody id="_search">
						<tr>
							<th><label for="sele2">
									<spring:message	code="app.manager.accessstatlist.label.searchdate"/>
								</label></th>
							<%-- 기준일자--%>
							<td>
								<fmt:formatDate value="${now}" pattern="${localeDatePattern}" var="tody" />
								<input type=text style="width: 80%;margin-right:4px" name="searchDate" readonly="readonly"
									id="searchDate" value="<c:out value='${tody}'></c:out>" maxlength="8" title="<spring:message code="app.manager.accessstatlist.label.searchdate" />">
							</td>
							<th><label for="sele2">
									<spring:message	code="app.manager.accessstatlist.label.datetype"/>
								</label>
							</th>
							<%-- 조회기간--%>
							<td>
								<input type="radio" name='dateType' id="dateType" value="10"
									title="<spring:message code="app.manager.accessstatlist.title.lasttenday" />"
									checked="checked"
								>
								<spring:message	code="app.manager.accessstatlist.title.lasttenday"/>
								&nbsp;
								<%-- 최근 10일--%>
								<input type="radio" name='dateType' id="dateType" value="5"
									title="<spring:message code="app.manager.accessstatlist.title.lastfiveday" />"
								>
								<spring:message	code="app.manager.accessstatlist.title.lastfiveday"/>
								&nbsp;
								<%-- 최근 5일--%>
								<input type="radio" name='dateType' id="dateType" value="3"
									title="<spring:message code="app.manager.accessstatlist.title.lastthreeday" />"
								>
								<spring:message	code="app.manager.accessstatlist.title.lastthreeday"/>
								<%-- 최근 3일--%>
							</td>
							<th><label for="sele2">
									<spring:message	code="app.manager.accessstatlist.label.typeflage"/>
								</label>
							</th>
							<%-- 집계기준--%>
							<td>
								<input type="radio" name='typeFlage' id="typeFlage" value="1"
									title="User Count " checked="checked"
								>
								<c:out value='User Count '></c:out>
								<input type="radio" name='typeFlage' id="typeFlage" value="2"
									title="Access Count "
								>
								<c:out value='Access Count'></c:out>
							</td>
							<th><label for="sele2">
									<spring:message	code="app.manager.accessstatlist.label.sumflage"/>
								</label>
							</th>
							<%-- 합계 Row--%>
							<td>
								<input type='checkBox' name='sumFlage' id='sumFlage'>
							</td>
						</tr>

					</tbody>
				</table>
			</fieldset>
		</form>
		<!-- 검색조건 end ------------------------------------------------>


		<!-- 분류 서브타이틀 및 이벤트 버튼 start  ----------------->
		<div class="tit_area">
			<h2 class="subhead">
				<spring:message code="app.manager.accessstatlist.title.timelist" />
			</h2>
			<%-- 시간별 접속 통계조회--%>
			<div class="btn_l"></div>
		</div>
		<!--  서브타이틀 및 이벤트 버튼 end     ---------------->



		<!--  Data 그리트 영역 start -------------------------->
		<div class="table_type1">
			<table id="table_v" class="type2">
				<caption>
					<spring:message	code="app.manager.accessstatlist.webacc.bdtag.table.caption.masteraccess"/>
					</legend>
					<%--기간 / 시간별 접속 통계 차트 --%>
					<tbody id="dataListbodys"></tbody>
			</table>
		</div>
		<div class="chart">
			<canvas id="barChart" height="250px;" width="667px;" style="width: 667px; height: 250px;"></canvas>
		</div>
		<!-- Bar 차트 생성 ---------------------------------------------------------- -->
		<script type="text/javascript">
		  var ctx = document.getElementById("barChart").getContext('2d');
		  var myBarChart = new Chart(ctx, {
			    type: 'bar',
			    options: {
			    	responsive: true,
			    	responsiveAnimationDuration: '3000',
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero:true
			                }
			            }]
			        },
			        legend: {
			            display: true,
			            position: 'bottom',
			        },
			        title: {
			            display: true,
			            position: 'top',
			            text: '<spring:message	code="app.manager.accessstatlist.webacc.bdtag.table.caption.masteraccess"/>'
			        }
			    }
			});
		</script>
		<!-- ----------------------------------------------------------------------- -->
	</div>

</body>
</html>
