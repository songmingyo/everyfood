package com.doppio.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.management.service.SysAccessLogService;
import com.doppio.management.service.SysAccessVo;


@Controller(value = "sysAccessLogController")
public class SysAccessLogController {

	private static final Logger logger = LoggerFactory.getLogger(SysAccessLogController.class);


	@Resource
    SqlSessionFactory sqlSessionFactory;

	@Autowired
	SysAccessLogService sysAccessLogService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
	
	
	/**
	 * 접속로그 현황 조회
	 * @param model
	 * @param paramsVo
	 * @return
	 */
    @RequestMapping(value = "/app/mgr/manager/sysAccessPgm", method = RequestMethod.GET)
    public String accessLogInit(Model model, SysAccessVo paramsVo) {
        return "/management/sysAccessPgm";
    }


    /**
    * 접속로그 List 조회
	* @param MgrAccessVo
	* @return
	*/
    @ResponseBody
    @RequestMapping(value="/app/mgr/manager/sysAccessPgm_selSearchList", method=RequestMethod.POST)
    public  Map<String, Object> accessLogList(SysAccessVo paramsVo) throws Exception {
		return sysAccessLogService.selectAccessLogList(paramsVo);
    }
    /**
     * 상세로그 List 조회
	* @param nowDate
	* @return
	* 	* @throws Exception
	*/
    @ResponseBody
    @RequestMapping(value="/app/mgr/manager/sysAccessPgm_selDtlLogList", method=RequestMethod.POST)
    public  Map<String, Object> detailLogList(SysAccessVo paramsVo) throws Exception {
		return sysAccessLogService.selectDetailLogList(paramsVo);
    }

    /**
    * 접속통계  Action
 	* @param
 	* @return
 	*/
 	@RequestMapping(value="/app/mgr/manager/accessStat")
 	public String AccessStatList01(HttpServletRequest request, ModelMap model) throws Exception{

 		//List<HashMap<String, String>> dataList = mgrAccessLog001Service.selectAccessLogTimesList();
 		//model.addAttribute("dataList",  dataList);

 		return "/management/sysAccessStat";
 	}

    /**
    * 접속통계  Action
	* @param  HashMap<String, String>
	* @return List<HashMap<String, String>>
	*/
 	@RequestMapping(value="/app/mgr/manager/accessStat_selList", method=RequestMethod.POST, headers="Accept=application/json")
 	public @ResponseBody List<HashMap<String, String>> AccessStatList02(@RequestBody HashMap<String, String> search) throws Exception {

 		List<HashMap<String, String>> dataList = sysAccessLogService.selectAccessLogTimesList(search);
 		return dataList;

 	}

 	/**
	  * Jxl Excel DOWNLOAD
	  *  
	  */
   @RequestMapping(value="/app/mgr/manager/jxl/sysAccessLog_selExcelDownload", method = RequestMethod.POST)
   public ModelAndView sysAccessLogExcelDownload(SysAccessVo paramsVo,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
	   
	 //Excel파일 정보/dataList
		 ExcelFileVo ExcelFileVo = new ExcelFileVo();
		
		 //file name
		 String fileName = "sysAccessLog";
		 
		 List<HashMap<String, String>> datalist = sysAccessLogService.selectAccessLogExl(paramsVo);
		 
		//title
		 String sessionid = messageSource.getMessage("app.manager.accesspgmlist.head.sessionid");
		 String menuid = messageSource.getMessage("app.manager.accesspgmlist.head.menuid");
		 String sub = messageSource.getMessage("app.manager.accesspgmlist.head.title");
		 String resfullyrl = messageSource.getMessage("app.manager.accesspgmlist.head.resfullyrl");
		 String regnm = messageSource.getMessage("app.manager.accesspgmlist.head.regnm");
		 String regid = messageSource.getMessage("app.manager.accesspgmlist.head.regid");
		 String resip = messageSource.getMessage("app.manager.accesspgmlist.head.resip");
		 String rescnt = messageSource.getMessage("app.manager.accesspgmlist.head.rescnt");
		 String regdt = messageSource.getMessage("app.manager.accesspgmlist.head.regdt");
		 String moddt = messageSource.getMessage("app.manager.accesspgmlist.head.moddt");
		 String executetime = messageSource.getMessage("app.manager.accesspgmlist.head.executetime");
		 String dtllogyn = messageSource.getMessage("app.manager.accesspgmlist.head.dtllogyn");
		 
		 String[] title = {"NO",sessionid,menuid,sub,resfullyrl,regnm,regid,resip,rescnt,regdt,moddt,executetime,dtllogyn};
		 //cell style
		 String[] dataStyle = {"CENTER","LEFT","RIGHT","LEFT","LEFT","LEFT","LEFT","LEFT","RIGHT/#,##0","CENTER","CENTER","RIGHT","CENTER"};//cell width
		 int[] cellWidth = {0,40,10,30,40,15,15,15,5,20,20,20,15,10};
		 //Keyset
		 String[] keyset = {"NO","SESSION_ID","MENU_ID","TITLE","RES_FULL_URL","USER_NM","REG_ID","RES_IP","RES_CNT","REG_DT","MOD_DT","EXECUTE_TIME_AVG","DTL_LOG_YN"};

		 
		 ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		 // ModelAndView  modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		 
		
		// ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
			
		 return modelAndView;
	   
   }
}
