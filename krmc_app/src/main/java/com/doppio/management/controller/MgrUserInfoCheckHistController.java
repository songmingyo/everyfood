package com.doppio.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver_bak;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.management.service.MgrPerInfoVo;
import com.doppio.management.service.MgrUserInfoCheckHistService;
import com.doppio.workplace.sample.service.SampleExcelVo;

/**
 * 
 * @Class : MgrUserInfoCheckHistController.java
 * @Description : 개인정보조회 및 사용자별 파일조회 
 * @author : JS
 * 
 *         <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 1. 18.        최진성            최초 생성
 *
 *         </pre>
 */
@Controller(value = "MgrUserInfoCheckHistController")
public class MgrUserInfoCheckHistController {

	private static final Logger logger = LoggerFactory.getLogger(MgrUserInfoCheckHistController.class);

	@Autowired
	MgrUserInfoCheckHistService mgrUserInfoCheckHistService;

	/**
	 * 
	 * @Method : mgrUserInfoCheckHist
	 * @Description : 개인정보조회 페이지
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrUserInfoCheckHist", method = RequestMethod.GET)
	public String mgrUserInfoCheckHist() throws Exception {
		return "/management/mgrUserInfoCheckHist";
	}

	/**
	 * 
	 * @Method : selectMgrUserInfoCheckList
	 * @Description : 개인정보 가져오기
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/app/mgr/manager/mgrUserInfoCheckHist_selectUserCheckList.json", method = RequestMethod.POST)
	public Map<String, Object> selectMgrUserInfoCheckList(MgrPerInfoVo mgrPerInfoVo) throws Exception {
		return mgrUserInfoCheckHistService.selectMgrUserInfoCheckList(mgrPerInfoVo);
	}

	/**
	 * 
	 * @Method : selectUserCheckListExcelDown
	 * @Description :개인정보 엑셀다운로드 데이터 조회
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 * 
	 *                   권한관련해서 설정된 RequestMapping 주소패턴을 반드시 지키세요!! (패키지구분명 다음
	 *                   언더바(_)를 붙여주고 이후에 조회(sel), 등록(ins), 수정(upd), 삭제(del)을 붙여줌)
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrUserInfoCheckHist_selectUserCheckListExcelDown")
	public ModelAndView selectUserCheckListExcelDown(MgrPerInfoVo mgrPerInfoVo) throws Exception {

		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// file name
		String fileName = "개인정보 조회이력";

		// 엑셀에 출력할 데이터들을 조회
		// 엑셀 데이터를 조회할때 null값은 반드시 공백으로 변경하세요!!
		List<HashMap<String, String>> datalist = mgrUserInfoCheckHistService.selectUserCheckListExcelDown(mgrPerInfoVo);

		// 엑셀 필드타이틀
		// String comCd =
		// messageSource.getMessage("app.manager.accesspgmlist.head.resfullyrl"); 메세지
		// 프로퍼티의 문구를 가져오는 방법
		String no = "NO";
		String logSeq = "이력번호";
		String accessDyFmt = "조회일자";
		String persBizsCdNm = "열람업무구분";
		String persPclInfo = "조회사용자 식별정보";
		String persInfo = "개인정보 조회내용";
		String rendDivnCdNm = "개인정보 열람사유";
		String userNm = "담당자";
		String regDtFmt = "생성일자";

		String[] title = { no, logSeq, accessDyFmt, persBizsCdNm, persPclInfo, persInfo, rendDivnCdNm, userNm,
				regDtFmt };

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = { "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER",
				"CENTER" };
		// cell width
		int[] cellWidth = { 10, 10, 15, 20, 30, 30, 60, 10, 20 };

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = { "NO", "LOG_SEQ", "ACCESS_DY_FMT", "PERS_BIZS_CD_NM", "PERS_PCL_INFO", "PERS_INFO",
				"REND_DIVN_CD_NM", "USER_NM", "REG_DT_FMT" };

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);

//		ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);

		return modelAndView;
	}
	/**
	 * 
	 * @Method : mgrUserFileHistory
	 * @Description : 사용자별 파일이력조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrUserFileHistory", method = RequestMethod.GET)
	public String mgrUserFileHistory() throws Exception {
		return "/management/mgrUserFileHistory";
	}
	/**
	 * 
	 * @Method : selectMgrUserFileHistory
	 * @Description : 사용자별 파일이력조회 갯수
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/app/mgr/manager/mgrUserFileHistory_selectMgrUserFileHistory.json", method = RequestMethod.POST)
	public Map<String, Object> selectMgrUserFileHistory(MgrPerInfoVo mgrPerInfoVo) throws Exception {
		return mgrUserInfoCheckHistService.selectMgrUserFileHistory(mgrPerInfoVo);
	}
	/**
	 * 
	 * @Method : selectUserCheckListExcelDown
	 * @Description : 엑셀 다운로드 데이터조회
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrUserInfoCheckHist_selectMgrUserFileHistoryExcelDown")
	public ModelAndView selectMgrUserFileHistoryExcelDown(MgrPerInfoVo mgrPerInfoVo) throws Exception {

		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// file name
		String fileName = "사용자별 파일조회이력";

		// 엑셀에 출력할 데이터들을 조회
		// 엑셀 데이터를 조회할때 null값은 반드시 공백으로 변경하세요!!
		List<HashMap<String, String>> datalist = mgrUserInfoCheckHistService.selectMgrUserFileHistoryExcelDown(mgrPerInfoVo);

		// 엑셀 필드타이틀
		// String comCd =
		// messageSource.getMessage("app.manager.accesspgmlist.head.resfullyrl"); 메세지
		// 프로퍼티의 문구를 가져오는 방법
		String no = "NO";
		String logSeq = "이력번호";
		String fileKindCdNm = "파일종류";
		String dwldCmd = "다운로드 내용";
		String fileInfo = "다운로드 파일";
		String executeTime = "실행시간MS";
		String accessDyFmt = "접근일자";
		String resIp = "접근아이피";
		String userNm = "등록자아이디";
		String regDt = "등록자일시";

		String[] title = { no, logSeq, fileKindCdNm, dwldCmd, fileInfo, executeTime, accessDyFmt, resIp,
				userNm,regDt };

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = { "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "CENTER", "CENTER",
				"CENTER","CENTER" };
		// cell width
		int[] cellWidth = { 10, 10, 20, 60, 80, 20, 20, 20, 20 ,30 };

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = { "NO", "LOG_SEQ", "FILE_KIND_CD_NM", "DWLD_CMD", "FILE_INFO",
				"EXECUTE_TIME", "ACCESS_DY_FMT", "RES_IP","USER_NM","REG_DT" };

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);

//		ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver_bak());
		modelAndView.addObject("excelFile", ExcelFileVo);

		return modelAndView;
	}

}
