package com.doppio.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.service.FileManagerService;
import com.doppio.common.service.VmConvertPdfService;
import com.doppio.common.service.impl.FileManagerMapper;

/**
 * @author Choyj
 * @Description : Asynchronous File Upload Controller
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2013.03.13	Choyj	최초등록
 * </pre>
 * @version :
 */

@Controller
public class FileUploadController {

	@Autowired
	FileManagerService fileManagerService;
	
	@Autowired
	VmConvertPdfService vmConvertPdfService;

	@Autowired
	FileUtil fileUtil;

	// 웹루트 경로 (웹서비스용 파일업로드 패스)
	@Value("#{config['File.StorePath.WebService']}")
	protected String FileStorePathWebService;

	@Value("#{config['accept.file.types.etc']}")
	protected String acceptFileTypesEtc;

	@Value("#{config['accept.file.types.doc']}")
	protected String acceptFileTypesDoc;

	@Value("#{config['accept.file.types.img']}")
	protected String acceptFileTypesImg;

	@Value("#{config['accept.file.types.jasper']}")
	protected String acceptFileTypesJasper;
	
	@Value("#{config['accept.file.types.vm']}")
	protected String acceptFileTypesVm;
	
	@Value("#{config['accept.file.types.pdf']}")
	protected String acceptFileTypesPdf;
	
	@Value("#{config['accept.file.types.html']}")
	protected String acceptFileTypesHtml;
	
	@Value("#{config['accept.file.types.csv']}")
	protected String acceptFileTypesCsv;

	@Value("#{config['max.number.of.files.default']}")
	protected String maxNumberOfFilesDefault;

	@Value("#{config['max.upload.size']}")
	protected String maxUploadSize;
	
	@Value("#{config['File.Sys.Path']}")	//어플리케이션 파일업로드 패스
	public String FileSysPath;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private FileManagerMapper fileManagerMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping(value="/web/common/fileUpload")
	public String executeFileUploadWindow(HttpServletRequest request, ModelMap model) throws Exception{

		String sync = StringUtils.defaultString(request.getParameter("sync"),"");
		long limitMaxUploadSize = Long.parseLong(maxUploadSize);
		int limitCount = Integer.parseInt(StringUtils.defaultString(request.getParameter("limitCount"),maxNumberOfFilesDefault));
		String atchFileId = StringUtils.defaultString(request.getParameter("atchFileId"),"");
		String atchType = StringUtils.defaultString(request.getParameter("atchType"),"etc");
		String kindCd = StringUtils.defaultString(request.getParameter("kindCd"),"");
		String dirPath = StringUtils.defaultString(request.getParameter("dirPath"),"");
		String returnParam = StringUtils.defaultString(request.getParameter("returnParam"),"");
		String thumbnail = StringUtils.defaultString(request.getParameter("thumbnail"),"");
		String acceptFileTypes = "";
		int existCount = 0;
		int exceptLimit = limitCount;

		logger.debug("limitMaxUploadSize:"+limitMaxUploadSize);
		logger.debug("limitCount:"+limitCount);
		logger.debug("atchFileId:"+atchFileId);
		logger.debug("atchType:"+atchType);
		logger.debug("kindCd:"+kindCd);
		logger.debug("returnParam:"+returnParam);
		logger.debug("thumbnail:"+thumbnail);

		if(atchFileId.equals("")){
			atchFileId = DateUtil.getTimeStamp();
			//atchFileId = fileManagerService.selectAttachFileId();
		}else{
			existCount = fileManagerService.selectAttachFileListCount(atchFileId);
			if(existCount > 0) exceptLimit = limitCount - existCount;
			if(exceptLimit < 0) exceptLimit = 0;
		}
		logger.debug("existCount:"+existCount);
		logger.debug("exceptLimit:"+exceptLimit);

		if(atchType.equals("doc")){
			acceptFileTypes = acceptFileTypesDoc;
		}else if(atchType.equals("img")){
			acceptFileTypes = acceptFileTypesImg;
		}else if(atchType.equals("jasper")){
			acceptFileTypes  = acceptFileTypesJasper;
		}else if(atchType.equals("vm")){
			acceptFileTypes = acceptFileTypesVm;
		}else if(atchType.equals("pdf")){
			acceptFileTypes = acceptFileTypesPdf;
		}else if(atchType.equals("html")) {
			acceptFileTypes = acceptFileTypesHtml;
		}else if(atchType.equals("file")){
			acceptFileTypes = acceptFileTypesImg +"|"+acceptFileTypesDoc+"|"+acceptFileTypesCsv;
		}else{
			acceptFileTypes  = acceptFileTypesImg +"|"+acceptFileTypesDoc+"|"+acceptFileTypesEtc;
		}


		logger.debug("acceptFileTypes:"+acceptFileTypes);

		model.addAttribute("sync", sync);	// 부모창과 팝업창 동기화 번호
		model.addAttribute("atchFileId", atchFileId);		// 첨부파일 아이디
		model.addAttribute("atchType", atchType);		// 첨부파일 타입
		model.addAttribute("dirPath", dirPath);		// 저장 디렉토리
		model.addAttribute("kindCd", kindCd);	// 첨부파일명의 접두사
		model.addAttribute("returnParam", returnParam);			// 반환 구분자
		model.addAttribute("limitCount", limitCount);			// 최초 등록가능한 수
		model.addAttribute("exceptLimit", exceptLimit);	// 가능한수 = 제한수 - 기등록건수
		model.addAttribute("acceptFileTypes",acceptFileTypes);		// 첨부파일확장자 체크
		model.addAttribute("limitMaxUploadSize",limitMaxUploadSize);		// 최대업로드용량제한사이즈
		model.addAttribute("thumbnail",thumbnail);							// 썸네일 생성 여부


		return "common/commonView/multiFileUploderWindow";
	}


	@RequestMapping(value="/web/common/fileDeleteSingle", method={RequestMethod.POST,RequestMethod.GET})
	public void executeFileDeleteSingle(HttpServletRequest request, HttpServletResponse response){
		logger.debug("start executeFileDeleteSingle........");

		try{
			String atchFileId = StringUtils.defaultString(request.getParameter("atchFileId"),"");
			String seq = StringUtils.defaultString(request.getParameter("seq"),"");
			if(!atchFileId.isEmpty() && !seq.isEmpty()){
				AttachFileVo vo = new AttachFileVo();
				AttachFileVo delFileVo = new AttachFileVo();
				String fileFullPath;
				
				vo.setAtchFileId(atchFileId);
				vo.setSeq(seq);
				delFileVo = fileManagerMapper.selectAttachFileDataList(vo);
				delFileVo.getFilePathNm();
				//서버 경로에서 파일 삭제(물리파일삭제)
				fileFullPath = FileSysPath+delFileVo.getFilePathNm()+delFileVo.getSaveFileNm();
				java.io.File f = new java.io.File(fileFullPath);
				f.delete();
				//atchFile테이블 정보 삭제
				fileManagerService.deleteUploadFile(vo);
			}
		}catch(Exception e){
			logger.error(e.toString());
		}
	}


	@RequestMapping(value="/web/common/fileUploadSingle", method= {RequestMethod.POST,RequestMethod.GET})
	public void executeFileUploadSingle(DefaultMultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response){

		StringBuffer sb = new StringBuffer();
		MultipartFile multipartFile = null;
		Iterator<String> iterator = null;

		logger.debug("start executeFileUploadSingle........");


		if (request.getHeader("accept").indexOf("application/json") != -1) {
	        response.setContentType("application/json; charset=UTF-8");
	    } else {
	        response.setContentType("text/plain; charset=UTF-8");
	    }


		Object exception = request.getAttribute("MultipartResolverException");
		if (exception != null && FileUploadBase.SizeLimitExceededException.class.equals(exception.getClass())) {
			sb.append(getMakeResult(null, null, "파일용량이 제한사이즈를 초과하였습니다."));
			logger.error(exception.toString());

		}else{
			String atchFileId = StringUtils.defaultString(request.getParameter("atchFileId"),"");		// 첨부파일번호
			String kindCd = StringUtils.defaultString(request.getParameter("kindCd"),"").toUpperCase();		// 업무구분코드
			String dirPath = StringUtils.defaultString(request.getParameter("dirPath"),"/");		// 저장 디렉토리
			String thumbnail = StringUtils.defaultString(request.getParameter("thumbnail"),"");		// 썸네일 생성 여부
			int limitCount = Integer.parseInt(StringUtils.defaultString(request.getParameter("limitCount"),maxNumberOfFilesDefault));		// 해당 첨부의 갯수 제한 수

			logger.debug("Request atchFileId:"+atchFileId);
			logger.debug("Request kindCd:"+kindCd);
			logger.debug("Request dirPath:"+dirPath);
			logger.debug("Request thumbnail:"+thumbnail);
			logger.debug("Request limitCount:"+limitCount);

			Map<String,MultipartFile> multipartMap = new HashMap<String,MultipartFile>();
			List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();
			List<AttachFileVo> attachFileList = null;
			List<AttachFileVo> attachFileResultList = null;
			//files라는 동일한 이름으로 넘어오기때문에 iterator 대신 multipartfile 이용
			//iterator= multipartRequest.getFileNames();
			List<MultipartFile> file = multipartRequest.getMultiFileMap().get("files");
			int ListSize = file.size();
			
			for(int i=0; i<ListSize; i++) {
				multipartFile = file.get(i);
				multipartMap.put(String.valueOf(i), multipartFile);
			}
			
//			 int k = 0; 
//			 while (iterator.hasNext()) {
//				 String name = (String)iterator.next(); 
//				 multipartFile = multipartRequest.getFile(name); //
//				 multipartFileList.add(multipartFile); // N건이 아니라 single 이라 단정한다.
//				 multipartMap.put(String.valueOf(k), multipartFile);
//				 logger.debug("File Name["+k+"]:" + name); k++;
//			}
			
			int k = 0;
			try {
				if(atchFileId.equals("") || atchFileId.isEmpty()){
					sb.append(getMakeResult(null, multipartFile, "필수 파라메타가 넘어오지않았습니다."));
				}else{
					attachFileList = fileUtil.parseFileInf(multipartMap, kindCd, FileStorePathWebService, dirPath, thumbnail);
					attachFileResultList = fileManagerService.insertFileRaiseSeq(attachFileList,atchFileId,limitCount);
					
					if(!attachFileResultList.isEmpty() && attachFileResultList.size() > 0){
						int FileListSize = attachFileList.size();
						if(FileListSize >1) { //(드래그앤드롭 추가)드래그 해서 파일 업로드 시작했을경우 버튼 한번 클릭으로 여러개 파일이 같이 들어옴
								k = 0;
								for(AttachFileVo vo:attachFileResultList){
										if(k > 0) sb.append(",");
										
										if(attachFileResultList.get(k).getFileSize().equals("0")) {
											sb.append(getMakeResult(null, null,this.message.getMessage("message.fail.filesizezero")+"(Error: File Size 0)")); //업로드에 실패하였습니다.
										}else {
											sb.append(getMakeResult(vo, null, ""));
										}
										
										k++;
							}
							
						}else { //파일 선택버튼으로 파일업로드 시작했을경우(기존로직)
								for(int i =0; i<attachFileList.size(); i++) {
									
									if(attachFileList.get(i).getFileSize().equals("0")) {
										sb.append(getMakeResult(null, null,this.message.getMessage("message.fail.filesizezero")+"(Error: File Size 0)")); //업로드에 실패하였습니다.
									}
									else {
										k = 0;
											for(AttachFileVo vo:attachFileResultList){
												if(k > 0) sb.append(",");
												sb.append(getMakeResult(vo, null, ""));
												k++;
											}
									}
								
							}
						}
							
						
					}else{
						sb.append(getMakeResult(null, multipartFile, "업로드된 파일이 존재하지않습니다."));
					}
				}
			} catch (Exception e) {
				logger.error("Exception", e);
				sb.append(getMakeResult(null, multipartFile, "업로드시에 오류가 발생하였습니다."));
			}
		}

		sb.insert(0,"{\"files\":[");
		sb.append("]}");

		logger.debug(sb.toString());

		PrintWriter printwriter;
        try {
			printwriter = response.getWriter();
			printwriter.write(sb.toString());
		} catch (IOException e) {
			logger.error("Error writing to output stream", e);
		}
	}

	public StringBuffer getMakeResult(AttachFileVo vo, MultipartFile mf, String error){
		StringBuffer sb = new StringBuffer();

		if(vo == null){
			if(mf == null){
				sb.append("\"atchFileId\":\"\",\"seq\":\"\",\"name\":\"\",\"size\":0");
				if(error.isEmpty()){
					sb.append(",\"error\":\"ERROR\"");
				}else{
					sb.append(",\"error\":\"" + error + "\"");
				}
				sb.append(",\"thumbnail_url\":\"\",\"delete_url\":\"\",\"delete_type\":\"DELETE\",\"url\":\"\"");
			}else{
				sb.append("\"atchFileId\":\"\",\"seq\":\"\"");
				if(error.isEmpty()){
					sb.append(",\"error\":\"ERROR\"");
				}else{
					sb.append(",\"error\":\"" + error + "\"");
				}
				sb.append(",\"name\":\"" + mf.getOriginalFilename() + "\"");
				sb.append(",\"size\":" + mf.getSize());
				sb.append(",\"thumbnail_url\":\"\",\"delete_url\":\"\",\"delete_type\":\"DELETE\",\"url\":\"\"");
			}

		}else{
			if(vo.getSeq().isEmpty()){
				sb.append("\"atchFileId\":\"\"");
				sb.append(",\"seq\":\"\"");
				if(vo.getError().isEmpty()){
					sb.append(",\"error\":\"ERROR\"");
				}else{
					sb.append(",\"error\":\"" + vo.getError() + "\"");
				}
			}else{
				sb.append("\"atchFileId\":\"" + vo.getAtchFileId() + "\"");
				sb.append(",\"seq\":\"" + vo.getSeq() + "\"");
			}
			sb.append(",\"name\":\"" + vo.getOrgFileNm() + "\"");
			sb.append(",\"size\":" + Long.parseLong(StringUtils.defaultString(vo.getFileSize(),"0")));
			sb.append(",\"thumbnail_url\":\"\"");
			sb.append(",\"delete_url\":\"" + "/web/common/fileDeleteSingle?atchFileId=" + vo.getAtchFileId() + "&seq=" + vo.getSeq() +"&delete=true\"");
			sb.append(",\"delete_type\":\"DELETE\"");
			sb.append(",\"url\":\"\"");
		}
		sb.insert(0,"{");
		sb.append("}");

		return sb;
	}
	
	/**
	 * @Method : vmFileValidation
	 * @Description : vm파일 유효성 검사
	 * @param attachFileVo
	 * @param request
	 * @param response
	 * @return Result
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/web/common/vmFileValid.json", method= {RequestMethod.POST})
	public Result vmFileValidation(@RequestBody AttachFileVo attachFileVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Result result = vmConvertPdfService.vmFileValidation(attachFileVo, request);
		
		return result;
	}
	
	/**
	 * 
	 * @Method : delNotUploadFile
	 * @Description : 파일 삭제(파일 업로드 팝업 종료시 이용)
	 * @param request
	 * @param searchParam
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/web/common/fileUpload_delNotUploadFile", method=RequestMethod.POST)
    public @ResponseBody Result delNotUploadFile(HttpServletRequest request, AttachFileVo searchParam, Result result) throws Exception {
		result.setMsgCd("fail");
		
		String fileFullPath;
		AttachFileVo vo = new AttachFileVo();
		AttachFileVo delFileVo = new AttachFileVo();
		
		vo.setAtchFileId(StringUtils.defaultString(searchParam.getAtchFileId()));
		vo.setSeq(StringUtils.defaultString(searchParam.getSeq()));
		
		delFileVo = fileManagerMapper.selectAttachFileDataList(vo);
		
		//서버 경로에서 파일 삭제(물리파일삭제)
		fileFullPath = FileSysPath+StringUtils.defaultString(delFileVo.getFilePathNm())+StringUtils.defaultString(delFileVo.getSaveFileNm());
		java.io.File f = new java.io.File(fileFullPath);
		f.delete();
		
		fileManagerService.deleteUploadFile(delFileVo);

		result.setMsgCd("success");
		return result;
    }
}
