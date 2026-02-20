package com.doppio.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doppio.common.security.service.CustomUser;
import com.doppio.dashboard.service.DashboardMainService;
import com.doppio.workplace.common.model.UserInfoVo;


@Service("dashboardMainService")
public class DashboardMainServiceImpl implements DashboardMainService{

	@Autowired
	DashboardMainMapper dashboardMainMapper;
	
	
	

	

}
