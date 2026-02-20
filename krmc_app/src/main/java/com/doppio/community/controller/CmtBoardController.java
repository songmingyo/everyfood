package com.doppio.community.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;
import com.doppio.community.service.CmtBoardService;
import com.doppio.community.service.CmtBoardVo;
import com.doppio.community.service.CmtCommentVo;
import com.doppio.community.service.CmtManagerVo;


@Controller(value = "cmtBoardController")
public class CmtBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmtBoardController.class);
	
	@Autowired
	private CmtBoardService cmtBoardService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	/**
	 * 게시판 목록 및 상세 공통  Init
	 * @param String, CmtBoardVo
	 * @return ModelAndView
	 * @throws Exception
	 */
    @RequestMapping(value={"/app/community/{boardCd}/community","/web/community/{boardCd}/community"})
    public ModelAndView  CmtBoardList(@PathVariable("boardCd") String boardCd, CmtBoardVo searchParam) throws Exception {
    	
    	searchParam.setBoardCd(boardCd);
    	
        return cmtBoardService.boardDetail(searchParam);
    }
    
    
	/**
	 * 게시글 조회
	 * @param CmtBoardVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@RequestMapping(value={"/web/board/community_selList"}, method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Map<String,Object> CmtBoardList(@RequestBody CmtBoardVo searchParam) throws Exception {
		return cmtBoardService.selectBoardList(searchParam);
	}
	
	/**
	 * 게시글 조회
	 * @param CmtBoardVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@RequestMapping(value={"/app/board/community_selList"}, method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Map<String,Object> CmtBoardMainList(@RequestBody CmtBoardVo searchParam) throws Exception {
		return cmtBoardService.selectBoardMainList(searchParam);
	}
	
    
	
	/**
	 * 게시물 입력
	 * @param model
	 * @param CmtBoardVo
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/app/board/boardAricle")
	public String CmtBoardSave(Model model, CmtBoardVo searchParam, HttpServletRequest request, HttpServletResponse response)throws Exception {
		CmtManagerVo mgrVO = cmtBoardService.selectBoardManager(searchParam);
		if(!cmtBoardService.selectBoardDataCheck(searchParam)) 
		{
			Result  result 	=  new Result();
			
			result.setMessage(this.message.getMessage("error.msg.custom.board.notfound"));	//
			model.addAttribute("result", result);
			return "common/error/commonException";
		}
		
		List<AttachFileVo> fileVO = null;
		CmtBoardVo vo = new CmtBoardVo();
		String updateAnswer = StringUtils.defaultString(searchParam.getUpdateAnswer(),"");
		
		/**
		 * updateAnswer 'U' : 글수정 
		 * 				'A' : 답글달기
		 * */
		if("U".equals(updateAnswer)){

			/*권한이 없을경우 Exception 반환*/
			cmtBoardService.checkedAuthorityException(searchParam.getBoardIdx(),request, response);

			vo =  cmtBoardService.selectListDetail(searchParam);
			fileVO = cmtBoardService.selectListFile(searchParam);
		} else if("A".equals(updateAnswer)){
			vo = cmtBoardService.selectListThreadDepth(searchParam); 
		}
		vo.setUpdateAnswer(updateAnswer);
		
		model.addAttribute("mgrVO", mgrVO );	
		model.addAttribute("vo", vo);
		model.addAttribute("fileVO", fileVO);
		return "community/cmtBoardWrite";
	}	
	
	
	/**
	 * 게시물 저장
	 * @param CmtBoardVo
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/app/board/boardArticle_insData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String CmtBoardSave(@RequestBody CmtBoardVo searchParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*권한이 없을경우 Exception 반환*/
		if("U".equals(searchParam.getUpdateAnswer())){
			cmtBoardService.checkedAuthorityException(searchParam.getBoardIdx(),request, response);
		}
		
		cmtBoardService.insertUpdateBoardList(searchParam, request);
		return searchParam.getBoardIdx();
	}
	

	
	/**
	 * 게시글 삭제
	 * @param CmtBoardVo
	 * @throws Exception
	 */
	@RequestMapping(value="/app/board/boardArticle_delData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result CmtBoardListDelete(@RequestBody CmtBoardVo searchParam,HttpServletResponse response, HttpServletRequest request, Result result) throws Exception {
		
		result.setMsgCd("fail");
		
		/*권한이 없을경우 Exception 반환*/
		cmtBoardService.checkedAuthorityException(searchParam.getBoardIdx(),request, response);
		String res = cmtBoardService.deleteBoardList(searchParam);
		
		if("success".equals(res)) result.setMsgCd(res);
		
		return result;
	}
	
	
	/**
	 * 게시글 Comment 조회
	 * @param CmtCommentVo
	 * @return List<CmtCommentVo>
	 * @throws Exception
	 */
	@RequestMapping(value={"/app/board/boardArticle_selComment","/web/board/boardArticle_selComment"}, method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<CmtCommentVo> CmtBoardComment(@RequestBody CmtCommentVo searchParam) throws Exception {
		return cmtBoardService.selectListComment(searchParam);
	}
	
	
	/**
	 * 한줄답글 저장
	 * @param CmtCommentVo
	 * @throws Exception
	 */
	@RequestMapping(value="/app/board/boardArticle_insComment", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result CmtBoardCommentSave(@RequestBody CmtCommentVo searchParam, Result result) throws Exception {
	
		result.setMsgCd("fail");
		
		String res = cmtBoardService.insertBoardComment(searchParam);
		if("success".equals(res)) result.setMsgCd(res);
		
		return result;
	}
	
	
	/**
	 * 한줄 답글 삭제
	 * @param CmtCommentVo
	 * @throws Exception
	 */
	@RequestMapping(value="/app/board/boardArticle_delComment", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result CmtBoardCommentDelete(@RequestBody CmtCommentVo searchParam, Result result) throws Exception {
		
		result.setMsgCd("fail");
		
		String res = cmtBoardService.deleteBoardComment(searchParam);
		if("success".equals(res)) result.setMsgCd(res);
		
		return result;
		
	}
	
	
	/**
	 * 첨부 파일 삭제
	 * @param CmtBoardVo
	 * @throws Exception
	 */
	@RequestMapping(value="/app/board/boardArticle_delFile", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result CmtBoardFileDelete(@RequestBody CmtBoardVo searchParam, Result result) throws Exception {
	
		result.setMsgCd("fail");
		
		String res = cmtBoardService.deleteBoardFile(searchParam);
		
		if("success".equals(res)) result.setMsgCd(res);
		
		return result;
	}
	
	/**
	 * 첨부파일 리스트 조회
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/app/board/boardArticle_selListFile","/web/board/boardArticle_selListFile"}, method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<AttachFileVo> CmtBoardFileListSelect(@RequestBody CmtBoardVo paramVo) throws Exception {
		return cmtBoardService.selectListFile(paramVo);
	}
   
}
