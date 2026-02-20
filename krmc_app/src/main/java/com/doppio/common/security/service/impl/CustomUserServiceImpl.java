package com.doppio.common.security.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doppio.common.security.CustomAuthenticationProvider;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.CustomUserService;
import com.doppio.common.security.service.Role;
import com.doppio.common.security.service.SecurityUtil;

/**
 * 
 * @Class : CustomUserServiceImpl.java
 * @Description : 사용자 로그인
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 0. 0.	                  최초 생성
 *
 * </pre>
 */
@Service("customUserService")
public class CustomUserServiceImpl implements CustomUserService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserServiceImpl.class);


	@Autowired
	private CustomUserMapper  customUserMapper;
	
	
	
	@Value("#{config['security.maxPassFailCnt']}")
	public String securityMaxPassFailCnt;
	
	@Value("#{config['system.env']}")
	public String systemEnv;
	
	@Value("#{config['security.deviceAuth.cookiename']}")
	public String devAuthCookieName;
	
   


	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
    /**
     * 로그인 사용자 정보 확인
     */
	public CustomUser loadUserByLoginUsername(final Authentication authentication) throws UsernameNotFoundException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		return loadUserByUserVerifiction(username, password);
	}

	
	/**
	 * REMEMBER-ME 사용자 정보 확인
	 */
	public CustomUser loadUserByRememberMe(String username) throws UsernameNotFoundException {
		return loadUserByUserVerifiction(username, "-1");
	}


	/**
	 * 사용자 정보 조회 검증 (일반로그인 이벤트 및 remember-me 공통사용)
	 *   - password 값이 있을경우 체크 (없는경우는 remember-me에서 호출)
	 *   - EXCEPTION 시 PERSISTENT_LOGINS 삭제 필요(remember-me 데이터)
	 *
	 */
	private CustomUser loadUserByUserVerifiction(String username, String password) throws UsernameNotFoundException {
		
	
	    CustomUser user = null;
	    
	    HashMap<String, String> customMap = new HashMap<>();
	    customMap.put("userName", username);
	    customMap.put("password", password);

	    // 사용자 정보 조회
	    user = loadUserByUserSession(customMap);
	    

		// 로그인 사용자 정보없음
        if(user == null || StringUtils.isEmpty(user.getUsername()) ) {
        	throw new BadCredentialsException("01");
        }
        
        // 사용자 유저타입
	    String userType = StringUtils.defaultString(user.getUserType());
	    
	    // 마지막 패스워드 변경 일자
	    String passLastDy = StringUtils.defaultString(user.getPassLastDy());
        
        //기간계 인터페이스 사용자
        if("9007002".equals(user.getGenDivnCd())) {
        	return user;
        }

        // 미사용여부
        if("N".equals(user.getUseYn())){
        	throw new BadCredentialsException("06");
        }        
        
        // 패스워드 불일치 카운트 초과로 인한 개정 잠김 상태(해당 사용자는 잠김 상태입니다. 패스워드 찾기 후 로그인 진행바랍니다.)
//        if("Y".equals(user.getLockYn())){
//        	throw new BadCredentialsException("05");
//        }

        // 패스워드 체크
		String encryptPassword = SecurityUtil.getEncryptedPassword(password);
		
		if(!"99991231".equals(passLastDy)) {
			if(!encryptPassword.equals(user.getPassword())) {
				//유저타입이 관리자(SA)가 아닐 경우, 피밀번호 실패 횟수 UPDATE 및 LOCK 처리
				if(!"SA".equals(userType)) {
					user.setMaxPassFailCnt(securityMaxPassFailCnt);
		        	customUserMapper.updateSecurityPassFailCntAndLock(user); // LOCK 처리
				}
	        	throw new BadCredentialsException("07");
	        }
		}else if(!user.getPassword().equals(password)) {
			throw new BadCredentialsException("07");
		}
		
//		if(!encryptPassword.equals(user.getPassword())) {
//			//유저타입이 관리자(SA)가 아닐 경우, 피밀번호 실패 횟수 UPDATE 및 LOCK 처리
//			if(!"SA".equals(userType)) {
//				user.setMaxPassFailCnt(securityMaxPassFailCnt);
//	        	customUserMapper.updateSecurityPassFailCntAndLock(user); // LOCK 처리
//			}
//        	throw new BadCredentialsException("07");
//        }
        
//        // 비밀번호 변경필요 --확인방식 변경(loginSuccessHandler, interceptor)
//        if("Y".equals(user.getPassChgNeedYn())){
//        	throw new BadCredentialsException("08");
//        }
        
        // 모두 정상통과인 경우 패스워드 실패카운트 0으로 초기화
    	if(!StringUtils.isEmpty(user.getPassFailCnt()) && !"0".equals(user.getPassFailCnt())){
    		customUserMapper.updateSecurityPassFailCntClear(user);
    	}
    	
        // 그룹정보존재여부 체크
        List<HashMap<String, Object>> groupMapList = new ArrayList<HashMap<String, Object>>();
        groupMapList = customUserMapper.selectMemberGroupAuth(user);
        int groupMapSize = groupMapList.size();
        
        // 사용자중 그룹정보가 미존재시에는 guest 권한부여
        if(groupMapSize <= 0){
    		customUserMapper.insertMemberAuth(user);
        }

		return user;
	}


	/**
	 * 사용자 ROLES 설정
	 * @param userRole
	 * @return
	 */
	private List<Role> loadUserByUserRole(String userRole){
		if(StringUtils.isEmpty(userRole)) return null;
		String[] userRoles = userRole.split(",");

        Role role = null;
        List<Role> roles = new ArrayList<Role>();
        for(String userRoleData : userRoles) {
        	role = new Role();
        	role.setName(userRoleData);

        	roles.add(role);
        }
         return roles;
	}

	
	
	/**
	 * 로그인 정보 생성 
	 *  1. 사용자(GEN_DIVN_CD : 9007001(시스템 전용 사용자)
	 * @param customMap
	 * @return CustomUser
	 */
	private CustomUser loadUserByUserSession(HashMap<String, String> customMap) throws UsernameNotFoundException{
		
		customMap.put("genDivnCd", "9007001");			//시스템  전용사용자
		
		CustomUser user = null;
		user = customUserMapper.selectSecurityData(customMap);
		
		if(user !=  null )  user.setAuthorities(loadUserByUserRole(user.getUserRoles()));  // USER ROLES 저장
	   
        return user;
	}
	
	
	
	public boolean loadUserByPlsUserChangedSession(String userId, String userPw, HttpServletRequest request) throws UsernameNotFoundException {
		HashMap<String, String> customMap = new HashMap<String,String>();
		customMap.put("userName", userId);		//사용자아이디
		customMap.put("password", userPw);		//사용자비밀번호
		customMap.put("genDivnCd", "9007001");	//PLS 전용사용자만 세션 변경 가능
		
		CustomUser user = null;
		
		//로그인한 사용자인지 확인
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || "anonymousUser".equals(auth.getPrincipal())) {
			// 로그인하지 않은 사용자 - 회원가입
			// 사용자 정보 조회 > authorities 셋팅 > 로그인 정보 생성
			user = customUserMapper.selectSecurityData(customMap);
			
			if(user != null) {
				user.setAuthorities(loadUserByUserRole(user.getUserRoles()));  // USER ROLES 저장
				
				//조회된 사용자 정보로 Authority 설정
				Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, userPw, authorities);
				request.getSession().setAttribute("deviceAuthChk", "Y");
				request.getSession().setAttribute("isNewMember", "Y");
			    Authentication new_authentic = customAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);
			    SecurityContextHolder.getContext().setAuthentication(new_authentic);
			    
				return true;
			}
			
		}else {
			// 로그인한 사용자 - 비밀번호 변경
			// 넘어온 정보대로 사용자 세션 변경
			user = (CustomUser) auth.getPrincipal();
			
			if(user != null) {
				Authentication chg_authentic = customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userId, userPw));
				SecurityContextHolder.getContext().setAuthentication(chg_authentic);
				return true;
			}
		}
		
		return false;
	}
	
}