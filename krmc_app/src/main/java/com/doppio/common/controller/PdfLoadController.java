package com.doppio.common.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.exception.BusinessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doppio.common.security.service.CustomUser;
import com.doppio.common.service.VmConvertPdfService;
import com.doppio.workplace.sample.service.SampleService;
import com.doppio.workplace.sample.service.SampleVmPdfVo;


/**
 * @author Yoo Jong-Sun
 * @Description : Jasper Report Pdf File Load
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2020.03.05 Init
 * </pre>
 * @version :
 */
@Controller
public class PdfLoadController {

	
	@Value("#{config['File.Sys.Path']}")					// 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String FileSysPath;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;


	@Autowired
	private VmConvertPdfService vmConvertPdfService;
	
	@Autowired
	private SampleService sampleService;
	
	@RequestMapping(value="/app/common/{pdfInfo}/loadPdfCommonView", method = RequestMethod.GET)
	public void loadImageCommon(@PathVariable("pdfInfo") String pdfInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		//Login Session
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userType = customUser.getUserType();
		
		String[] tempArr =  pdfInfo.split("_");
		
		// 파라메터 누락시 
		if(tempArr == null || tempArr.length != 3) {
			throw new BusinessException("001", "Parameter Exception");	
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();						    // Report Parameter
		
		String pdfType  = tempArr[0];					// pdfType
		String workType = tempArr[1];					// 업무구분
		String keyValue = tempArr[2];					// Key Value

		 if("HTML".equals(pdfType)){			// HTML(VM) 파일로 출력할 경우
			
			if("SMP".equals(workType)){				// 샘플
				// 검색 키값이 다수면 요청 url에 &을 붙여서 전달
				String[] comCodeArr = null;
				if (keyValue != null) {comCodeArr = keyValue.split("&");}

				String comCd = null;
				if (comCodeArr != null && comCodeArr.length >= 1) {comCd = comCodeArr[0];}
				//String dtlCd = comCodeArr[1];
				
				SampleVmPdfVo sampleVmPdfVo = new SampleVmPdfVo();
				sampleVmPdfVo.setSrchComCd(comCd);
				//sampleVmPdfVo.setDtlCd(dtlCd);
				
				// 레포트 양식분류 및 레포트 조회
				HashMap resultMap = sampleService.selectSampleReportData(sampleVmPdfVo);
				pMap.putAll(resultMap);
								
				// 리스트 데이터 
				pMap.put("workType", workType);				// 업무구분
				pMap.put("printMode", "vertical");			// 출력모드(vertical : 세로, horizontal : 가로)
				
			}
			/*else if("ECSFORM".equals(workType)){				// 전자계약-양식관리
				// 검색 키값이 다수면 요청 url에 &을 붙여서 전달
				String[] comCodeArr = null;
				if (keyValue != null) {comCodeArr = keyValue.split("&");}

				String frmCatCd = null;
				String docSeq = null;
				if (comCodeArr != null && comCodeArr.length >= 1) {frmCatCd = comCodeArr[0];}
				if (comCodeArr != null && comCodeArr.length >= 2) {docSeq = comCodeArr[1];}
				
				TecDocInfoVo tecDocInfoVo = new TecDocInfoVo();
				tecDocInfoVo.setFrmCatCd(frmCatCd);
				tecDocInfoVo.setDocSeq(docSeq);
				
				// 레포트 양식분류 및 레포트 조회
				HashMap resultMap = ecsFormMgrService.selectDocInfoReportData(tecDocInfoVo);
				pMap.putAll(resultMap);
				
				// 리스트 데이터 
				pMap.put("workType", workType);				// 업무구분
				pMap.put("printMode", "vertical");			// 출력모드(vertical : 세로, horizontal : 가로)
				
			} */

			vmConvertPdfService.vmConvertToPdf(pMap, request, response);	// VM Convert to PDF LOAD
			
		}else{
			throw new BusinessException("002", "Parameter Error");			// PDF 구분 누락	
		}
	}
	
}