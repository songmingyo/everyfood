package com.doppio.common.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.service.EmsCommonService;
import com.doppio.common.util.velocity.ReportTool;


/**
 * 
 * @Class : EmsCommonServiceImpl.java
 * @Description : 메일공통
 * @author : 안석진
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 2. 20.            안석진           최초 생성
 *
 * </pre>
 */
@Service("emsCommonService")
public class EmsCommonServiceImpl implements EmsCommonService{

	private static final Logger logger = LoggerFactory.getLogger(EmsCommonServiceImpl.class);
	
	@Autowired
	EmsCommonMapper emsCommonMapper;
	
	@Value("#{config['system.env']}")
	public String systemEnv;
	
	@Value("#{config['ems.send.title']}")
	public String emsSendTitle;	
	
	@Value("#{config['ems.send.emsFromNm']}")
	public String emsFromNm;
	
	@Value("#{config['ems.send.emsFromEmail']}")
	public String emsFromEmail;
	
	@Value("#{config['ems.send.sysNm']}")
	public String emsSysNm;
	
	@Value("#{config['ems.send.sysUrl']}")
	public String emsSysUrl;	
	
	/**
	 * 업무별 상태 변경 안내 EMAIL 발송 (전자계약)
	 * @throws Exception 
	 */
	@Override 
	public void emailSendBusiness(HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
	/* 	
		 메일 발송구분 코드(2058)

		  [ 입점상담 발송구분 코드 ] 
			* 로그인시 기기인증은 SMS만 발송
			* 001~003은 메일만 발송
			* 028~088은 메일/알림톡 발송
			
			2058001 비밀번호 찾기(사업자 번호로/이메일로)
			2058002 임시비밀번호 발급 (관리자가 비밀번호 초기화 후 안내 발송)
			2058003 협력사 입점제안 MD 요청 알림 (요청탭 내용 등록 안내 발송)
			
			2058028 본사 방송 입점제안 결과 알림		-수신자: 담당md
			2058029 본사 모바일 입점제안 결과알림		-수신자: 담당md
			2058067 본사 입점제안 상담 심사 알림		-수신자: 담당md -처리기한 :서류승인일(2054040이 된날 )로부터 5영업일
			2058068 본사 입점제안 서류 심사 알림		-수신자: 담당md -처리기한: 접수완료일(2054020이 된 날)로부터 5영업일
			2058069 본사 입점제안 접수 알림			-수신자: 담당md -처리기한:신청완료(2054011이 된 날)일로부터 3영업일
			2058087 협력사 입점제안 진행상태 안내		-수신자: 협력사 입점제안 담당자
			2058088 협력사 입점제안 부가서류 요청		-수신자: 협력사 입점제안 담당자
	 */
		try {
			if(paramMap == null || paramMap.get("EMS_DIVN_CD") == null) return;
			String emsDivnCd = (String)paramMap.get("EMS_DIVN_CD");
			
			/* SELECT Mail send Master data -------------------------------------*/
			List<HashMap<String,Object>> sendData  = null;
			
			if(emsDivnCd.contains("2058028")||emsDivnCd.contains("2058029")) { //제안업체명,대표상품명,담당md
				sendData = emsCommonMapper.selectMailDataMd(paramMap);
				
			}else if(emsDivnCd.contains("2058087")||emsDivnCd.contains("2058088")) {//제안업체명,대표상품명,입점제안 담당자
				sendData = emsCommonMapper.selectMailDataPart(paramMap);
				
			}else if(emsDivnCd.contains("2058067")||emsDivnCd.contains("2058068")||emsDivnCd.contains("2058069")) { 	//제안업체명,대표상품명,처리기한(#월#일),담당md ,각 진행상태별 처리기한 계산 필요 
				// TODO 진행상태별 처리기한 계산 필요
				sendData = emsCommonMapper.selectMailProcPerdDataMd(paramMap);
			}else {}
			/* ------------------------------------------------------------------- */
			if(sendData != null) emailSend(paramMap, request, sendData);
			
		}catch(Exception e) {
			logger.error("EMS SEND 요청 실패 : " + e.getMessage());
		}
		
	}
	

	/**
	 * EMAIL 발송 
	 */
	
	private int emailSend(HashMap<String, Object> paramMap, HttpServletRequest request, List<HashMap<String,Object>> sendData) {
		int rtnCnt =0;
		
		try{
		
			/* MAIL HEAD DATA 설정 ------------------------------------------------ */
			if(sendData == null || sendData.size() <= 0) return 0;
			HashMap<String, Object> sendHead = sendData.get(0);
			/* ------------------------------------------------------------------- */
				  
			/*Set mail Title */
			if(sendHead.get("MSG_TITLE") == null || StringUtils.isEmpty((String)sendHead.get("MSG_TITLE")) ) {
				sendHead.put("MSG_TITLE", emsSendTitle);
			}
			
			
			if(sendHead.get("SND_USER_NM") == null || StringUtils.isEmpty((String)sendHead.get("SND_USER_NM")) ) {
				sendHead.put("SND_USER_NM",emsFromNm);
			}
			
			if(sendHead.get("SND_COMP_NM") == null || StringUtils.isEmpty((String)sendHead.get("SND_COMP_NM")) ) {
				sendHead.put("SND_COMP_NM",emsFromNm);
			}
			
			if(sendHead.get("SND_EMAIL") == null || StringUtils.isEmpty((String)sendHead.get("SND_EMAIL")) ) {
				sendHead.put("SND_EMAIL",emsFromEmail);
			}
			
			
			
			/*Head Detail Data insert*/
			sendHead.put("RSERV_SND_DY" 	, paramMap.get("RSERV_SND_DY"));	// 예약발송일자 YYYYmmdd
			sendHead.put("RSERV_SND_TM" 	, paramMap.get("RSERV_SND_DY"));	// 예약발송시간 HHmm
			emsCommonMapper.insertEmsHeadData(sendHead);  // TCM_EMS_HEAD
			
			StringBuffer sb = null;
			
			/*MAKE MAIL FORM(VM) INSERT PARAM PUT -------------------------------- */
			for(HashMap<String, Object> sendDetail :sendData) {
				
				/*HEAD INDEX KEY 설정*/
				sendDetail.put("EMS_IDX", sendHead.get("EMS_IDX"));
				String emsDivnCd= (String)sendDetail.get("EMS_DIVN_CD");
				
				
				/*추가정보 설정 -------------------------- --*/
				// URL 설정 (바로가기 및 이미지 url 사용)
				sendDetail.put("DOMAIN", emsSysUrl);
				// EMS 머릿말 설정
				sendDetail.put("EMS_HEAD_CONTENT", sendHead.get("MSG_TITLE"));
	
				
				/*EMS_HEAD_CONTENT set default-------- --*/
				if(sendDetail.get("EMS_HEAD_CONTENT") == null || StringUtils.isEmpty((String)sendDetail.get("EMS_HEAD_CONTENT")) ) {
					sendDetail.put("EMS_HEAD_CONTENT", emsSendTitle); 
				}
				/*------------------------------------ --*/
					
				
				/*mail form data vm ------------------ --*/
				sb = new StringBuffer();
				makeEmsBodyContent2(request, sendDetail, sb );
				/*------------------------------------ --*/
				
				/*put Mail Body Data */
				sendDetail.put("EMS_BODY_CONTENT", sb.toString());
				
				
				emsCommonMapper.insertEmsDetailData(sendDetail);	// TCM_EMS_DETAIL
				
				rtnCnt++;
			}
			
		}catch (Exception e) {
			logger.error("EMS SEND 요청 실패 : " + e.getMessage());
		}
		/* ------------------------------------------------------------------- */
		return rtnCnt;
	}
	
	/**
	 * Mail form(VM) Bind Data 생성 
	 * @param request
	 * @param hData
	 * @param sb
	 */
	private void makeEmsBodyContent2(HttpServletRequest request, HashMap<String,Object> hData, StringBuffer sb) {
		
		if(hData == null || hData.get("EMS_DIVN_CD") == null || String.valueOf(hData.get("EMS_DIVN_CD")).length() <=0 ) return;
		
		ReportTool tool    = null;
		String vmFilePath  = "";				// VM 파일경로 물리적 전체 경로 (경로정보 미설정 시 Context/WEB-INF/velocity/ 참조함)
		String vmFileName  = String.valueOf(hData.get("EMS_DIVN_CD"))+".html";	// VM 파일명칭
		
		
		try{
			tool = new ReportTool(request, vmFilePath, vmFileName);
			tool.context.put("Data", hData);	  // Mail Data Mapping 
			String strVelocity = tool.getBindVelData().replaceAll("\u00A0","").replaceAll("\n","").replaceAll("\r","").replaceAll("\t","");
			sb.append(strVelocity); // OUTPUT DATA (VM+DATA)
			
			
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}
