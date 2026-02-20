package com.doppio.community.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.service.impl.FileManagerMapper;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.community.service.CmtBoardService;
import com.doppio.community.service.CmtBoardVo;
import com.doppio.community.service.CmtCommentVo;
import com.doppio.community.service.CmtManagerVo;

@Service("cmtBoardServiceImpl")
public class CmtBoardServiceImpl implements CmtBoardService {

	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount;
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount;
	
	@Value("#{config['File.StorePath.WebService']}")			//웹서비스 루트 패스 
	public String FileStorePathWebService;
	 
	@Value("#{config['File.Sys.Path']}")						//어플리케이션 파일업로드 패스
	public String FileSysPath;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private CmtBoardMapper cmtBoardMapper;
	
	@Autowired
	private FileManagerMapper fileManagerMapper;
	
	
	/**
	 * 게시물 상세보기 Model 
	 */
	@Override
	public ModelAndView boardDetail(CmtBoardVo paramVo) throws Exception {
		
		
		/*-- FAQ 게시판 여부 체크 Return View Jsp 분기 [ CODE: 902 ]--- 
		 *  9020001 : 일반게시판
		 *  9020002 : 묻고답하기
		 *  9020003 : FAQ
		 *  9020004 : 자료실
		 * ---------------------------------------------------------*/
		ModelAndView modelView = null;
		String       views     = "";
		
		List<CmtBoardVo> preNextlist = null;
		
		
		/* 1. 게시판 유무 및 게시글 여부 체크 (boardIdx 값이 있으면 게시글여부까지 체크함)-------*/
		if(!selectBoardDataCheck(paramVo)) {

			Result  result 	=  new Result();
			
			result.setMessage(this.message.getMessage("error.msg.custom.board.notfound"));	//
			modelView  		= new ModelAndView("common/error/commonException");
			modelView.addObject("result", result);
			
			return modelView;
		}
		/*-------------------------------------------------------------------------------*/
		
		/*게시판 관리정보 조회*/
		CmtManagerVo mgrVO = cmtBoardMapper.selectBoardManager(paramVo);
		
		/* boardIdx 이 존재할경우 상세보기, 아닌경우 List보기 VIEW 설정--------*/
		if(StringUtils.isEmpty(paramVo.getBoardIdx()) ) {
			if(mgrVO.getBoardKindCd().equals("9020003")) {		// FAQ 여부
				views =  "community/cmtBoardFaq";				// FAQ LIST
			}
			else {
				views =  "community/cmtBoard";				    // 일반 LIST
			}
			
			modelView = new ModelAndView(views);
			
			//FAQ List일 경우,fileVo 추가
			if(mgrVO.getBoardKindCd().equals("9020003")) {
				List<AttachFileVo> fileVo = cmtBoardMapper.selectListFile(paramVo);
				modelView.addObject("fileVo",fileVo);
			}
		}
		else {
			
			
			updateBoardCount(paramVo);
			CmtBoardVo boardDetail = cmtBoardMapper.selectListDetail(paramVo);
			
			views = "community/cmtBoardDetail";				// 상세보기
			modelView = new ModelAndView(views);
		
			//FAQ일때 이전글/다음글 출력안함
			if(!"9020003".equals(mgrVO.getBoardKindCd()))
				preNextlist = cmtBoardMapper.selectListPreNext(paramVo); // 이전 다음글 조회
			
			modelView.addObject("vo", 			boardDetail );		// 상세정보
			modelView.addObject("searchVO",    	paramVo);			// 검색조건
			modelView.addObject("preNextList", 	preNextlist );		// 이전다음글정보
			
			
		}
		
		modelView.addObject("mgrVO", 		mgrVO );			// 관리정보
		/*----------------------------------------------------------------*/
		
		
		return modelView;
	}

	
	/**
	 * 접근한 게시판 및 게시글 존재하는지 체크
	 */
	@Override
	public boolean selectBoardDataCheck(CmtBoardVo paramVo) throws Exception {

		/* 게시판코드 유무 확인 */
		if(StringUtils.isEmpty(paramVo.getBoardCd())) return false;
		
		/* 게시판 및 게시글 유무 카운트 체크 */
		int cnt = cmtBoardMapper.selectBoardDataCheck(paramVo);			
		
		if(cnt > 0) return true;	// 정보가 존재하면 return true
		
		return false;
	}
	
	

	/**
	 * 조회수 수정
	 */
	@Override
	public void updateBoardCount(CmtBoardVo paramVo) throws Exception {
		cmtBoardMapper.updateBoardCount(paramVo);
	}
	
	
	
	/**
	 * 게시판 리스트 불러오기 
	 */
	@Override
	public Map<String, Object> selectBoardList(CmtBoardVo paramVo) throws Exception {
		
		List<CmtBoardVo> list = null;
		Map<String,Object> result = new HashMap<String,Object>();
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		// 상단공지개수에 상관 없이 항상 10개씩만 보여주고 싶을때 이 부분 주속 해제 필요
		// 주석처리 : 상단 공지를 제외하고 10개의 리스트를 보여줌. ex) 상단공지가 4개면 총 14개의 row 출력
		/**
		int topCount = 0;
		topCount = cmtBoardMapper.selectListTopTotCnt(paramVo);
		pageRowCount = pageRowCount - topCount;
		**/
		
		
		int totalCount = 0;
		totalCount = cmtBoardMapper.selectListTotCnt(paramVo);
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
			paramVo.setBoardExposureCd("S");
		} else {
			/*작업자 아이디  생성---------------------------------------*/
			CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if("SA".equals(customUser.getUserType())) {}/*내부*/
			else if("SU".equals(customUser.getUserType()))
				paramVo.setBoardExposureCd("I");
			else /*외부*/
				paramVo.setBoardExposureCd("S");
			/*-------------------------------------------------------*/
		}
		
		
		if (totalCount > 0){
			list = cmtBoardMapper.selectBoardList(paramVo);
		}
		
		result.put("totalCount", totalCount);
		result.put("list", list);
		result.put("search", paramVo.getSearch());
		result.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return result;
	}
	
	
	
	/**
	 * 게시판 리스트 불러오기 
	 */
	@Override
	public Map<String, Object> selectBoardMainList(CmtBoardVo paramVo) throws Exception {
		
		List<CmtBoardVo> list = null;
		Map<String,Object> result = new HashMap<String,Object>();
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		// 상단공지개수에 상관 없이 항상 10개씩만 보여주고 싶을때 이 부분 주속 해제 필요
		// 주석처리 : 상단 공지를 제외하고 10개의 리스트를 보여줌. ex) 상단공지가 4개면 총 14개의 row 출력
		/**
		int topCount = 0;
		topCount = cmtBoardMapper.selectListTopTotCnt(paramVo);
		pageRowCount = pageRowCount - topCount;
		**/
		
		
		int totalCount = 0;
		totalCount = cmtBoardMapper.selectListTotCnt(paramVo);
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
			paramVo.setBoardExposureCd("S");
		} else {
			/*작업자 아이디  생성---------------------------------------*/
			CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if("SA".equals(customUser.getUserType())) {}/*내부*/
			else if("SU".equals(customUser.getUserType()))
				paramVo.setBoardExposureCd("I");
			else /*외부*/
				paramVo.setBoardExposureCd("S");
			/*-------------------------------------------------------*/
		}
		
		
		if (totalCount > 0){
			list = cmtBoardMapper.selectBoardMainList(paramVo);
		}
		
		result.put("totalCount", totalCount);
		result.put("list", list);
		result.put("search", paramVo.getSearch());
		result.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return result;
	}
	
	
	
	/**
	 * 게시판 코드/제한값 확인
	 */
	@Override
	public CmtManagerVo selectBoardManager(CmtBoardVo paramVo) throws Exception {
		return cmtBoardMapper.selectBoardManager(paramVo);
	}
	
	

	/**
	 * 게시물 총 수
	 */
	@Override
	public int selectListTotCnt(CmtBoardVo paramVo) throws Exception {
		return cmtBoardMapper.selectListTotCnt(paramVo);
	}


	
	/**
	 *  게시판 상세보기
	 */
	@Override
	public CmtBoardVo selectListDetail(CmtBoardVo paramVo) throws Exception {
		return cmtBoardMapper.selectListDetail(paramVo);
	}
	

	/**
	 *  다음글/이전글 조회
	 */
	@Override
	public List<CmtBoardVo> selectListPreNext(CmtBoardVo paramVo) throws Exception {
		return cmtBoardMapper.selectListPreNext(paramVo);
	}

	
	/**
	 *	부모글 Thread/Depth 가져오기 
	 */
	@Override
	public CmtBoardVo selectListThreadDepth(CmtBoardVo paramVo) throws Exception {
		return cmtBoardMapper.selectListThreadDepth(paramVo);
	}
	
	
	/**
	 *  수정 시 파일 조회
	 */
	@Override
	public List<AttachFileVo> selectListFile(CmtBoardVo paramVo) throws Exception {
		return cmtBoardMapper.selectListFile(paramVo);
	}
	
	
	/**
	 * 게시물 Index 가져오기
	 */
	@Override
	public String selectListIndex() throws Exception {
		return cmtBoardMapper.selectListIndex();
	}
	

	/**
	 * 게시글 등록/수정
	 */
	@Override
	public void insertUpdateBoardList(CmtBoardVo paramVo, HttpServletRequest request) throws Exception {
		String updateAnswer = paramVo.getUpdateAnswer();
		
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		paramVo.setMemberCd(customUser.getMemberCd());
		paramVo.setUserNm(customUser.getFullName());
		paramVo.setRegId(customUser.getMemberCd());
		if("U".equals(updateAnswer)){
			cmtBoardMapper.updateBoardList(paramVo);
		} else {
			if(!"".equals(paramVo.getBoardIdx())){
				paramVo.setDepth(paramVo.getDepth()+1);
				paramVo.setThread(paramVo.getThread()-1);
				paramVo.setParentBoardIdx(paramVo.getBoardIdx());
			} 
			paramVo.setBoardIdx(cmtBoardMapper.selectListIndex()); //oracle용 DB가 오라클일때는 시퀀스를 가져오기 위해서 주석 제거하시오. mssql은 NVL(MAX)로 처리
			cmtBoardMapper.insertBoardList(paramVo);
		}
    }
	
	
	/**
	 * 게시판 글 삭제
	 */
	@Override
	public String deleteBoardList(CmtBoardVo paramVo) throws Exception {
		cmtBoardMapper.deleteBoardList(paramVo);
		return "success";
	}
	
	
	
	/**
	 * 한줄답글 조회
	 */
	@Override
	public List<CmtCommentVo> selectListComment(CmtCommentVo paramVo) throws Exception {
		return cmtBoardMapper.selectListComment(paramVo);
	}
	
	
	/**
	 * 한줄답글 입력
	 */
	@Override
	public String insertBoardComment(CmtCommentVo paramVo) throws Exception {
		cmtBoardMapper.insertBoardComment(paramVo);
		return "success";
	}
	

	/**
	 * 한줄답글 삭제
	 */
	@Override
	public String deleteBoardComment(CmtCommentVo paramVo) throws Exception {
		cmtBoardMapper.deleteBoardComment(paramVo);
		return "success";
	}
	

	/**
	 * 파일 삭제
	 */
	@Override
	public String deleteBoardFile(CmtBoardVo paramVo) throws Exception {
		
		AttachFileVo vo = new AttachFileVo();
		AttachFileVo delFileVo = new AttachFileVo();
		String fileFullPath;
		vo.setAtchFileId(paramVo.getAtchFileId());
		vo.setSeq(paramVo.getSeq());
		delFileVo = fileManagerMapper.selectAttachFileDataList(vo);
		delFileVo.getFilePathNm();
		fileFullPath = FileSysPath+delFileVo.getFilePathNm()+delFileVo.getSaveFileNm();
		java.io.File f = new java.io.File(fileFullPath);
		f.delete();
		fileManagerMapper.deleteFileData(vo);
		
		return "success";
	}
	

	/**
	 * 게시물 DATA 권한 체크  *.do Action 에대한 Exception return 
	 * @param String
	 * @return boolean
	 * @throws AccessDeniedException, HttpSessionRequiredException, IOException, SQLException
	 */
	public boolean checkedAuthorityException(String boardIdx, HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException, HttpSessionRequiredException, IOException,  SQLException{
		
		// ajax 인 경우 Header 값  ( application/json, text/javascript, */*; q=0.01 )
		boolean json = false;
		if(request.getHeader("Accept").indexOf("application/json") > -1) json = true;
		
		/*해당게시글의 작업권한 확인 (게시판관리자 또는 작성자 확인)*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(customUser == null) {
			if(json) response.sendError(9999);
			else throw new HttpSessionRequiredException(this.message.getMessage("error.msg.custom.permission.change.denied"));
		}
		String memberCd = customUser.getMemberCd();
		if (StringUtils.isEmpty(memberCd)) {
			if (json) {
				response.sendError(9999);
			} else {
				throw new HttpSessionRequiredException(this.message.getMessage("error.msg.custom.permission.change.denied")); // 해당 Data의 수정(삭제) 권한이 없습니다.
			}
		}

		HashMap<String, String> hData = cmtBoardMapper.selectBoardManagerData(boardIdx);
		if(hData == null) {
			if(json) response.sendError(9992);
			else throw new AccessDeniedException(this.message.getMessage("error.msg.custom.datanotfound.title"));
		}
		
		/* HashMap Data :  BOARD_IDX, BOARD_CD, WRI_MEMBER, MGR_MEMBER */
		if( !(memberCd.equals(hData.get("WRI_MEMBER"))  ||  memberCd.equals(hData.get("MGR_MEMBER")) ) ) { 
			if(json) response.sendError(9991);
			else throw new AccessDeniedException(this.message.getMessage("error.msg.custom.permission.change.denied"));
		}
		return true;
		
	}
	
	
}
