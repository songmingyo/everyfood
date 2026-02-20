package com.doppio.common.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.exception.BusinessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.service.CommonService;
import com.doppio.common.service.FileManagerService;

/**
 * @author hdh
 * @Description :
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * </pre>
 * @version :
 */
@Controller
public class FileDownLoadController {

	@Autowired
	CommonService commonService;

	@Autowired
	FileManagerService fileManagerService;

	@Resource(name="messageSourceAccessor")
	 private MessageSourceAccessor message;

	@Value("#{config['File.StorePath.WebService']}")
	public String downPath;

	//입점신청 Supplier 로그인세션명
	private static final String LAUNC_SESSION_NAME = "launcLoginSession";

	//입찰신청 Supplier 로그인세션명
	private static final String TENDERER_SESSION_NAME = "tendererLoginSession";
	
	private static final Logger logger = LoggerFactory.getLogger(FileDownLoadController.class);

	/**
    * 파일다운로드 공통 Controller
	* @param
	* @return
	*/
	@RequestMapping(value={"/app/common/{atchFileId}/{seq}/fileDownload", "/web/common/{atchFileId}/{seq}/fileDownload"})
	public ModelAndView fileDownloadInit(HttpServletRequest request
			, @PathVariable("atchFileId") String atchFileId
			, @PathVariable("seq") 		  String seq
			,ModelMap model) throws Exception{

		
		AttachFileVo attachFile = new AttachFileVo();

		attachFile.setAtchFileId(atchFileId);		//파일아이디
		attachFile.setSeq(seq);						//파일순번

		attachFile= fileManagerService.fileDownLoadData(attachFile, request);		//파일조회

		ModelAndView modelAndView = null;
		Result result = new Result();

		/*등록파일 테이블 정보 여부 확인*/
		if(attachFile == null || attachFile.getFilePathNm() == null ) {

			result.setMsgCd("ERROR-FILE-01");
			result.setMessage(this.message.getMessage("error.msg.custom.file.nodata"));	// message : 등록된 파일정보가 없습니다.
			result.setResultCode("File Data Not Found");

			modelAndView = new ModelAndView("common/error/commonException");
			modelAndView.addObject("result", result);
		}
		else{
			modelAndView = new ModelAndView("downloadView", "downloadFile", attachFile);
		}
		return modelAndView;

	}

	/**
	* 파일다운로드 리스트 INCLUDE 공통 Controller
	* @param String
	* @return
	*/
	@RequestMapping(value="/web/common/{atchFileId}/fileDownloadList")
	public String fileDownloadListInit(HttpServletRequest request
			, @PathVariable("atchFileId") String atchFileId
			, ModelMap model) throws Exception{


		List<AttachFileVo> list = fileManagerService.selectAttachFileList(atchFileId);		//파일조회

		model.addAttribute("attachFileList", list);

		return "common/commonView/comAttachFileList";
	}



	/**
	 * 파일다운로드 리스트 조회
	 * @param String
	 * @return List<AttachFileVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/web/common/fileDownloadList",  method=RequestMethod.POST, headers="Accept=application/json")
	public List<AttachFileVo> commonFileDownloadList(@RequestBody String atchFileId) throws Exception {

		List<AttachFileVo> list = fileManagerService.selectAttachFileList(atchFileId);

		return list;
	}
	
	/**
	 * 
	 * @Method : cntFileDownloadInit
	 * @Description : 파일다운로드 - 파일 갯수 확인하여 zipFileDownLoad or 일반 다운로드
	 * @param fileVo
	 * @param model
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value={"/app/common/cntFileDownload", "/web/common/cntFileDownload"})
	public ModelAndView cntFileDownloadInit(AttachFileVo fileVo,ModelMap model) throws Exception{
		
		ModelAndView modelAndView = null;
		Result result = new Result();
		int fileCnt = 0; //파일 갯수
		String atchFileId = StringUtils.defaultString(fileVo.getAtchFileId()); //첨부파일 아이디
		
		List<AttachFileVo> fileList = null;// 첨부파일 목록
		
		
		
		if("".equals(atchFileId)) {
			throw new BusinessException("ERR-FILE-01", "첨부파일 아이디가 없습니다.");
			
		}else {
			fileCnt = fileManagerService.selectAttachFileListCount(atchFileId);
			
			if(fileCnt>1) {
				fileList = fileManagerService.selectAttachFileList(atchFileId);		//파일목록 조회
				if(fileList !=null && fileList.size()>0) {
					modelAndView = new ModelAndView("downloadListView", "downloadListFile", fileList);
				}
			}else if(fileCnt ==1){
				AttachFileVo singleFileVo = new AttachFileVo();//파일정보
				singleFileVo.setAtchFileId(atchFileId);
				singleFileVo= fileManagerService.fileDownLoadData(singleFileVo, null);		//파일조회
				/*등록파일 정보 여부 확인*/
				if(singleFileVo == null || singleFileVo.getFilePathNm() == null ) {
					result.setMsgCd("ERR-FILE-02");
					result.setMessage(this.message.getMessage("error.msg.custom.file.nodata"));	// message : 등록된 파일정보가 없습니다.
					result.setResultCode("File Data Not Found");

					modelAndView = new ModelAndView("common/error/commonException");
					modelAndView.addObject("result", result);
				}
				else{
					modelAndView = new ModelAndView("downloadView", "downloadFile", singleFileVo);
				}
				return modelAndView;
				
			}else {
				throw new BusinessException("ERR-FILE-03", this.message.getMessage("error.msg.custom.file.nodata"));// message : 등록된 파일정보가 없습니다.
			}
			
		}
		
		return modelAndView;
	}
	
	/**
	 * 파일다운로드 리스트 조회 (atchFileIds)
	 * @param String
	 * @return List<AttachFileVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/web/common/fileDownloadListAtchFileIds",  method=RequestMethod.POST, headers="Accept=application/json")
	public List<AttachFileVo> fileDownloadListAtchFileIds(@RequestBody AttachFileVo paramVo) throws Exception {

		return  fileManagerService.selectAtchFileIdsFileList(paramVo);

	}
	
	
}
