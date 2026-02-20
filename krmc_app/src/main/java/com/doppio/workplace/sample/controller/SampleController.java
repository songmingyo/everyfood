package com.doppio.workplace.sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.exception.CustomGenericException;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.util.FileUtil;
import org.springframework.tronic.util.RestApi;
import org.springframework.tronic.util.RfcUtil;
import org.springframework.tronic.util.StringUtil;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.InterfaceVo;
import com.doppio.common.model.Result;
import com.doppio.common.service.ImageConvertPdfService;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.workplace.common.model.UserInfoVo;
import com.doppio.workplace.sample.service.SampleExcelVo;
import com.doppio.workplace.sample.service.SampleJasperVo;
import com.doppio.workplace.sample.service.SampleLnchVo;
import com.doppio.workplace.sample.service.SampleService;
import com.doppio.workplace.sample.service.SampleVmPdfVo;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Controller(value = "sampleController")
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	private static final int List = 0;	
	
	@Autowired
	private SampleService sampleService;

	@Autowired
	private ImageConvertPdfService imageConvertPdfService;

    @Autowired
    private ServletContext servletContext;
    
    @Autowired
	private JasperDownloadService jasperDownloadService;

    
//    @Autowired
//	private EmsCommonService emsCommonService; 	// EMS SEND 서비스

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

    @Autowired
	RfcUtil rfcUtil;

//	@Autowired
//	private MessageCommonService messageCommonService; 	// EMS SEND 서비스
    
    
    
    
    	
    @RequestMapping(value={"app/smp/commonSample01.do"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String commonSample01(HttpServletRequest request) throws Exception {
		return "/sample/sampleCommon";
	}
    
	/**
	 * 샘플 엑셀 & vm 레포트
	 * @param paramVo
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/app/smp/sampleExcel", "/app/smp/link/sampleExcel"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String sampleExcelInit(HttpServletRequest request) throws Exception {
		return "/sample/sampleExcel";
	}


	/**
	 * 샘플 엑셀다운로드 데이터 조회
	 * @param paramVo
	 * @return
	 * @throws Exception
	 * 
	 * 권한관련해서 설정된  RequestMapping 주소패턴을 반드시 지키세요!! (패키지구분명 다음 언더바(_)를 붙여주고 이후에 조회(sel), 등록(ins), 수정(upd), 삭제(del)을 붙여줌)
	 */
    @ResponseBody	
	@RequestMapping(value="/app/smp/sample_selSampleExcel.json")
	public HashMap<String, Object> selectSampleExcel(SampleExcelVo paramVo) throws Exception {
		return sampleService.selectSampleExcelData(paramVo);
	}


	/**
	 * 샘플 엑셀다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 * 
	 * 권한관련해서 설정된  RequestMapping 주소패턴을 반드시 지키세요!! (패키지구분명 다음 언더바(_)를 붙여주고 이후에 조회(sel), 등록(ins), 수정(upd), 삭제(del)을 붙여줌)
	 */
	@RequestMapping(value="/app/smp/sample_selSampleExcelDown")
	public ModelAndView selectSampleExcelDown(SampleExcelVo paramVo) throws Exception {	
		
		//테스트 합니다. 수정
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();
		
		//file name
		String fileName = "sampleExcel";
		
		// 엑셀에 출력할 데이터들을 조회
		// 엑셀 데이터를 조회할때 null값은 반드시 공백으로 변경하세요!! 
		List<HashMap<String, String>> datalist = sampleService.selectSampleExcelDownData(paramVo);
		
		// 엑셀 필드타이틀
		//String comCd = messageSource.getMessage("app.manager.accesspgmlist.head.resfullyrl");  메세지 프로퍼티의 문구를 가져오는 방법
		String comCd = "코드마스터"; 
		String comNm = "코드명";
		String dtlCd = "상세코드";
		String dtlNm = "상세코드명";
		String codeDecs = "코드설명";
		String sortNo = "정렬번호";

		String[] title = {"NO", comCd, comNm, dtlCd, dtlNm, codeDecs, sortNo};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "LEFT", "RIGHT/#,##0"};
		// cell width
		int[] cellWidth = {0, 15, 30, 15, 30, 40, 15};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "COM_CD", "COM_NM", "DTL_CD", "DTL_NM", "CODE_DECS", "SORT_NO"};
		
		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;
	}

	
	
	 /**
	  * JASPER PDF DOWNLOAD
	  */
	 @RequestMapping(value="/app/smp/pdf/sampleJasper", method = RequestMethod.POST)
	 public void samplePdfDownload(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{

		 List<SampleJasperVo> list = sampleService.selectUserInfo(null);
		 String jasperName = "report3.jasper";
		 String fileName = "샘플@04";
		 String docType = "pdf";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();

		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);

		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", list); 									//DATA
		 map.put("docType", docType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름

		 jasperDownloadService.jasperDocumentDownload(map, request, response);

	 }
	 
	 
	 
	 /** 
	  * JASPER PDF REPORT 
	  */
	 @RequestMapping(value="/app/smp/pdf/sampleJasperView", method = RequestMethod.POST)
	 public ModelAndView samplePdfReportView(HttpServletRequest request, Model model) throws Exception{

        ModelAndView mav = new ModelAndView();

        List<SampleJasperVo> list = sampleService.selectUserInfo(null);

		int listCnt = 0;
		listCnt = list.size();

		if(listCnt > 0) {
			list.get(0).setBarcode("03512839192");
			JRDataSource dataSource = new JRBeanCollectionDataSource(list);
	        mav.addObject("dataSource",dataSource);
		}

		mav.setViewName("userInfoPrint");

        return mav;
	 }
	 
	 /**
	  * JASPER PDF 생성 Multi
	  */
	 @RequestMapping(value="/app/smp/pdf/sampleJasperMulti", method = RequestMethod.POST)
	 public void samplePdfMakeMulti( HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		 
		 String jasperName = "report3.jasper";
		 String[] userTypes= {"SA","FA"};					// 다중 레포트 병합 처리를 위한 For 조건 값 (데이터 조회 조건 마스터 키 )
		 
		 List<SampleJasperVo> list = null ;					// 대상 데이터 List ( Report 바인딩 데이터)
		 List<Map<String, Object>> contFileMapList = null;  // JASPER 파일 리스트 ( 복합 그리드 사용 인경우 List 레포트 종류 설정 가능)
		 
		 List<Map<String, Object>> jasperList = null;		// 병합 처리 할 레포트 리스트 
		
		 
		 Map<String, Object> contFileMap = null;			// 레포트 유형(Design)
		 Map<String, Object> map = null;					// 레포트 대상 마스터 Map
		 
		 jasperList = new ArrayList<>();
		 
		 SampleJasperVo paramVo = null;
		 
		 for(String userType : userTypes) {
			 paramVo = new SampleJasperVo();
			 paramVo.setUserType(userType);
			 
			 list =  sampleService.selectUserInfo(paramVo);
			
			 /*1번 */
			 contFileMapList = new ArrayList<>();
			 contFileMap = new HashMap<String, Object>();
			 contFileMap.put("jasperName1", jasperName);
			 contFileMapList.add(contFileMap);
			
			 map = new HashMap<String, Object>();
			 map.put("list", list); 									//DATA
			 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
			 map.put("fileName", "SampleReport");						//파일 이름
			 
			 jasperList.add(map);
		 }
		 
		 jasperDownloadService.jasperDocumentDownloadMulit(jasperList,request, response);
		 
	 }
	 

	
}