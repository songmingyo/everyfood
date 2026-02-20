
/*-----------TABLE-------------*/

CREATE TABLE `PERSISTENT_LOGINS` (
  `SERIES` varchar(64) NOT NULL COMMENT 'SERIES',
  `USERNAME` varchar(64) DEFAULT NULL COMMENT '접속고유명칭',
  `TOKEN` varchar(64) DEFAULT NULL COMMENT 'TOKEN',
  `LAST_USED` timestamp NULL DEFAULT NULL COMMENT '최종사용시간',
  PRIMARY KEY (`SERIES`),
  UNIQUE KEY `PK_PERSISTENT_LOGINS` (`SERIES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='REMEMBER-ME';


CREATE TABLE `TCM_ATTACH_FILE` (
  `ATCH_FILE_ID` varchar(17) NOT NULL COMMENT '첨부파일아이디',
  `SEQ` int(3) NOT NULL COMMENT '순번',
  `ORG_FILE_NM` varchar(200) DEFAULT NULL COMMENT '원본파일명',
  `SAVE_FILE_NM` varchar(50) DEFAULT NULL COMMENT '저장파일명',
  `FILE_KIND_CD` varchar(7) DEFAULT NULL COMMENT '파일종류코드',
  `FILE_SIZE` bigint(20) DEFAULT NULL COMMENT '파일사이즈',
  `FILE_PATH_NM` varchar(50) DEFAULT NULL COMMENT '파일경로명',
  `FILE_TYPE_NM` varchar(6) DEFAULT NULL COMMENT '파일확장자명',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`ATCH_FILE_ID`,`SEQ`),
  UNIQUE KEY `PL_CM_ATTACH_FILE` (`ATCH_FILE_ID`,`SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='업로드파일정보';



CREATE TABLE `TCM_BOARD_COMMENT` (
  `COMMENT_IDX` decimal(10,0) NOT NULL COMMENT '댓글인덱스',
  `BOARD_IDX` decimal(10,0) DEFAULT NULL COMMENT '게시인덱스',
  `BOARD_CD` varchar(3) DEFAULT NULL COMMENT '게시판코드',
  `COMMENTS_CONTENT` varchar(2000) DEFAULT NULL COMMENT '댓글내용',
  `MEMBER_CD` varchar(10) DEFAULT NULL COMMENT '등록자고유번호',
  `USER_NM` varchar(120) DEFAULT NULL COMMENT '등록자명',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`COMMENT_IDX`),
  UNIQUE KEY `PK_CM_BOARD_COMMENT` (`COMMENT_IDX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='통합게시판한줄답글';



CREATE TABLE `TCM_BOARD_MANAGER` (
  `BOARD_CD` varchar(3) NOT NULL COMMENT '게시판코드',
  `BOARD_KIND_CD` varchar(7) DEFAULT NULL COMMENT '게시판유형코드',
  `BOARD_NM` varchar(50) DEFAULT NULL COMMENT '게시판명',
  `MEMBER_CD` varchar(10) DEFAULT NULL COMMENT '관리자고유번호',
  `FILE_COUNT` int(2) DEFAULT NULL COMMENT '업로드파일갯수',
  `FILE_UPLOAD_SIZE` int(5) DEFAULT NULL COMMENT '파일업로드사이즈',
  `WRITE_YN` char(1) DEFAULT NULL COMMENT '글쓰기유무',
  `REPLY_YN` char(1) DEFAULT NULL COMMENT '댓글사용여부',
  `COMMENT_YN` char(1) DEFAULT NULL COMMENT '한줄답변글사용여부',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`BOARD_CD`),
  UNIQUE KEY `PK_CM_BOARD_MANAGER` (`BOARD_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='통합게시판속성관리';



CREATE TABLE `TCM_BOARD_MST` (
  `BOARD_IDX` decimal(10,0) NOT NULL COMMENT '게시인덱스',
  `BOARD_CD` varchar(3) NOT NULL COMMENT '게시판코드',
  `PARENT_BOARD_IDX` decimal(10,0) DEFAULT NULL COMMENT '부모게시인덱스',
  `THREAD` bigint(20) DEFAULT NULL COMMENT '게시물출력순번',
  `DEPTH` bigint(20) DEFAULT NULL COMMENT '답변글깊이',
  `BOARD_KIND_CD` varchar(7) DEFAULT NULL COMMENT '게시판유형코드',
  `BOARD_TITLE` varchar(200) DEFAULT NULL COMMENT '게시제목',
  `BOARD_CONTENT` text DEFAULT NULL COMMENT '게시내용',
  `BOARD_COUNT` bigint(20) DEFAULT NULL COMMENT '조회카운트',
  `BOARD_NOTICE_YN` char(1) DEFAULT NULL COMMENT '상단공지여부',
  `IS_PORTAL_YN` char(1) DEFAULT NULL COMMENT '포탈메인공지여부',
  `ALERT_FR_DT` varchar(12) DEFAULT NULL COMMENT '팝업공지시작일시',
  `ALERT_TO_DT` varchar(12) DEFAULT NULL COMMENT '팝업공지종료일시',
  `IS_URGENCY_YN` char(1) DEFAULT NULL COMMENT '게시BORDER처리유무',
  `ETC_URL` varchar(200) DEFAULT NULL COMMENT '기타바로가기URL',
  `ATCH_FILE_ID` varchar(17) DEFAULT NULL COMMENT '첨부파일아이디',
  `MEMBER_CD` varchar(10) DEFAULT NULL COMMENT '등록자고유번호',
  `USER_NM` varchar(120) DEFAULT NULL COMMENT '등록자명',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '등록자이메일',
  `DEL_YN` char(1) DEFAULT NULL COMMENT '삭제여부',
  `BOARD_EXPOSURE_CD` char(1) DEFAULT NULL COMMENT '게시노출유형코드',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`BOARD_IDX`,`BOARD_CD`),
  UNIQUE KEY `PK_CM_BOARD_MST` (`BOARD_IDX`,`BOARD_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='통합게시판';



CREATE TABLE `TCM_CODE_DTL` (
  `COM_CD` varchar(6) NOT NULL COMMENT '마스터코드',
  `DTL_CD` varchar(10) NOT NULL COMMENT '세부코드',
  `DTL_NM` varchar(100) NOT NULL COMMENT '세부코드명',
  `DTL_SUB_NM` varchar(100) DEFAULT NULL COMMENT '세부코드확장명',
  `DTL_EXTEND_NM` varchar(100) DEFAULT NULL COMMENT '세부코드추가확장명',
  `EXTENT01` varchar(100) DEFAULT NULL COMMENT '확장코드01',
  `EXTENT02` varchar(100) DEFAULT NULL COMMENT '확장코드02',
  `EXTENT03` varchar(100) DEFAULT NULL COMMENT '확장코드03',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `SORT_NO` int(5) DEFAULT NULL COMMENT '정렬순번',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`COM_CD`,`DTL_CD`),
  UNIQUE KEY `PK_CM_CODE_DTL` (`COM_CD`,`DTL_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공통코드세부';


CREATE TABLE `TCM_CODE_MST` (
  `COM_CD` varchar(6) NOT NULL COMMENT '마스터코드',
  `COM_NM` varchar(100) NOT NULL COMMENT '마스터코드명',
  `COM_SUB_NM` varchar(100) DEFAULT NULL COMMENT '마스터코확장명',
  `COM_EXTEND_NM` varchar(100) DEFAULT NULL COMMENT '마스터코추가확장명',
  `COM_ANX_DESC` varchar(200) DEFAULT NULL COMMENT '마스터코드부가설명',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`COM_CD`),
  UNIQUE KEY `PK_CM_CODE_MST` (`COM_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공통코드마스터';


CREATE TABLE `TCM_COMPINFO` (
  `MASTER_ID` varchar(20) NOT NULL COMMENT '회사마스터아이디',
  `PARENT_COMP_CD` varchar(20) DEFAULT NULL COMMENT '부모사업장코드',
  `COMP_NM` varchar(100) DEFAULT NULL COMMENT '회사명',
  `BMAN_NO` varchar(30) NOT NULL COMMENT '사업자등록번호[Tax Code]',
  `CORPN_NO` varchar(20) DEFAULT NULL COMMENT '법인번호[Enterprise Reg/License No]',
  `BMAN_DIVN_CD` char(1) DEFAULT NULL COMMENT '사업자구분코드[GMD :BZM01]',
  `AFFILIATE_YN` char(1) DEFAULT NULL COMMENT '계열사유무',
  `CEO_NM` varchar(50) DEFAULT NULL COMMENT '대표자명',
  `BTYP_NM` varchar(60) DEFAULT NULL COMMENT '업태명칭',
  `BIZ_ITM_NM` varchar(60) DEFAULT NULL COMMENT '업종명칭',
  `REP_BRAND` varchar(200) DEFAULT NULL COMMENT '대표브랜드',
  `FOU_DY` varchar(8) DEFAULT NULL COMMENT '설립일자',
  `REP_TEL_NO` varchar(24) DEFAULT NULL COMMENT '대표전화번호',
  `FAX_TEL_NO` varchar(24) DEFAULT NULL COMMENT '팩스전화번호',
  `ZIP_NO` varchar(7) DEFAULT NULL COMMENT '우편번호',
  `ZIP_ADDR` varchar(100) DEFAULT NULL COMMENT '우편주소',
  `DTL_ADDR` varchar(100) DEFAULT NULL COMMENT '상세주소',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '이메일',
  `HMPG_URL` varchar(50) DEFAULT NULL COMMENT '홈페이지URL',
  `MAIN_BIZ_RSUT` varchar(200) DEFAULT NULL COMMENT '주요사업실적',
  `HEDO_YN` char(1) DEFAULT NULL COMMENT '본사여부',
  `COMP_CLOSE_CD` varchar(2) DEFAULT NULL COMMENT '회사휴폐업코드',
  `DEAL_ITEM_CONTENT` varchar(100) DEFAULT NULL COMMENT '취급품목내용',
  `COMP_INTRO` varchar(500) DEFAULT NULL COMMENT '회사소개',
  `APRV_DIVN_CD` char(2) DEFAULT NULL COMMENT '승인구분코드[9068]',
  `GEN_DIVN_CD` varchar(5) DEFAULT NULL COMMENT '생성구분코드',
  `KSIC_LAR_CAT_CD` varchar(5) DEFAULT NULL COMMENT '표준산업대분류코드[SECTION CODE]',
  `KSIC_MED_CAT_CD` varchar(5) DEFAULT NULL COMMENT '표준산업중분류코드[SUB_CLASS]',
  `BANK_ACC_NO` varchar(20) DEFAULT NULL COMMENT 'Bank account No',
  `BANK_ACC_NM` varchar(50) DEFAULT NULL COMMENT 'Bank Account Name',
  `BANK_BENE_NM` varchar(50) DEFAULT NULL COMMENT 'Bank Beneficiary Name',
  `CON_ISSUED_BY` varchar(100) DEFAULT NULL COMMENT 'Contract Issued by',
  `CON_ISSUED_ON` varchar(100) DEFAULT NULL COMMENT 'Contract Issued on',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`MASTER_ID`),
  UNIQUE KEY `PK_CM_COMPINFO` (`MASTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회사마스터정보';



CREATE TABLE `TCM_FILEDWLDINFO_LOG` (
  `LOG_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '로그순번',
  `FILE_KIND_CD` varchar(7) DEFAULT NULL COMMENT '파일종류코드[9040]',
  `DWLD_CMD` varchar(200) DEFAULT NULL COMMENT '다운로드내용',
  `FILE_INFO` varchar(400) DEFAULT NULL COMMENT '다운로드파일정보',
  `EXECUTE_TIME` varchar(200) DEFAULT NULL COMMENT '실행시간MS',
  `ACCESS_DY` varchar(8) DEFAULT NULL COMMENT '접근일자',
  `RES_IP` varchar(20) DEFAULT NULL COMMENT '접근아이피',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  PRIMARY KEY (`LOG_SEQ`),
  KEY `IX_CM_FILEDWLDINFO_LOG` (`ACCESS_DY`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='첨부파일다운로드로그';


CREATE TABLE `TCM_GROUP` (
  `GROUP_ID` varchar(6) NOT NULL COMMENT '그룹아이디',
  `GROUP_NM` varchar(50) NOT NULL COMMENT '그룹명칭',
  `GROUP_SUB_NM` varchar(50) DEFAULT NULL COMMENT '그룹추가명',
  `GROUP_DESC` varchar(400) DEFAULT NULL COMMENT '그룹설명',
  `GROUP_FG` varchar(1) DEFAULT NULL COMMENT '그룹구분코드[9202]',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `SYS_YN` char(1) DEFAULT NULL COMMENT '빌드인여부',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`GROUP_ID`),
  UNIQUE KEY `PK_CM_GROUP` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='그룹정보';



CREATE TABLE `TCM_GROUP_AUTH` (
  `GROUP_ID` varchar(6) NOT NULL COMMENT '그룹아이디',
  `MENU_ID` int(10) NOT NULL COMMENT '메뉴아이디',
  `PGM_QRY` char(1) DEFAULT NULL COMMENT '조회권한',
  `PGM_INT` char(1) DEFAULT NULL COMMENT '등록권한',
  `PGM_UPT` char(1) DEFAULT NULL COMMENT '수정권한',
  `PGM_DEL` char(1) DEFAULT NULL COMMENT '삭제권한',
  `PGM_PRT` char(1) DEFAULT NULL COMMENT '출력권한',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`GROUP_ID`,`MENU_ID`),
  UNIQUE KEY `PK_CM_GROUP_AUTH` (`GROUP_ID`,`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='그룹메뉴권한';



CREATE TABLE `TCM_MEMBER` (
  `MEMBER_CD` varchar(10) NOT NULL COMMENT '사용자고유번호',
  `USER_ID` varchar(60) DEFAULT NULL COMMENT '로그인아이디',
  `USER_NM` varchar(120) DEFAULT NULL COMMENT '사용자명',
  `USER_PW` varchar(100) DEFAULT NULL COMMENT '로그인비밀번호',
  `TEL_NO` varchar(24) DEFAULT NULL COMMENT '전화번호',
  `HP_NO` varchar(24) DEFAULT NULL COMMENT '핸드폰번호',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '이메일',
  `BRTH` varchar(8) DEFAULT NULL COMMENT '생년월일',
  `POSITION_CD` varchar(20) DEFAULT NULL COMMENT '직책코드',
  `POSITION_NM` varchar(100) DEFAULT NULL COMMENT '직책명',
  `CLASS_CD` varchar(20) DEFAULT NULL COMMENT '직급코드',
  `CLASS_NM` varchar(100) DEFAULT NULL COMMENT '직급명',
  `DEPT_CD` varchar(8) DEFAULT NULL COMMENT '담당자부서코드',
  `DEPT_NM` varchar(120) DEFAULT NULL COMMENT '담당자부서명',
  `POSTN_BRNCH_CD` varchar(8) DEFAULT NULL COMMENT '소속지점코드',
  `MASTER_ID` varchar(20) DEFAULT NULL COMMENT '회사마스터아이디',
  `EMP_NO` varchar(20) DEFAULT NULL COMMENT '사원번호',
  `USER_TYPE` char(2) DEFAULT NULL COMMENT '사용자타입[9001]',
  `USER_ROLES` varchar(100) DEFAULT NULL COMMENT 'ROAL',
  `PASS_CHG_YN` char(1) DEFAULT NULL COMMENT '비밀번호필수변경유무',
  `PASS_LAST_DY` varchar(8) DEFAULT NULL COMMENT '비밀번호최종변경일자',
  `PASS_FAIL_CNT` int(1) DEFAULT NULL COMMENT '비밀번호실패횟수',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `LOCK_YN` char(1) DEFAULT NULL COMMENT 'LOCK유무',
  `FORGOT_PWD_CODE` varchar(6) DEFAULT NULL COMMENT 'Forgot Password URL code',
  `FORGOT_PWD_LST_DY` varchar(8) DEFAULT NULL COMMENT 'Forgot Password Expiration Date',
  `ID_TYPE_CD` varchar(7) DEFAULT NULL COMMENT '아이디타입코드[C001]',
  `UI_TYPE` varchar(10) DEFAULT NULL COMMENT 'UI 타입(탭방식 : T , 일반 : F)',
  `STR_CD` varchar(5) DEFAULT NULL COMMENT '점포코드',
  `WORKNG_SE_CD` varchar(8) DEFAULT NULL COMMENT '재직구분코드[SAL123]',
  `FIND_PWD_CD` varchar(7) DEFAULT NULL COMMENT '패스워드찾기질문코드',
  `FIND_PWD_ANSWER` varchar(200) DEFAULT NULL COMMENT '패스워드찾기질문답변',
  `ZIP_NO` varchar(7) DEFAULT NULL COMMENT '우편번호',
  `ZIP_ADDR` varchar(100) DEFAULT NULL COMMENT '우편주소',
  `DTL_ADDR` varchar(100) DEFAULT NULL COMMENT '상세주소',
  `GEN_DIVN_CD` varchar(7) DEFAULT NULL COMMENT '생성구분코드[9007]',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`MEMBER_CD`),
  UNIQUE KEY `PK_CM_MEMBER` (`MEMBER_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자정보';



CREATE TABLE `TCM_MEMBER_AUTH` (
  `GROUP_ID` varchar(6) NOT NULL COMMENT '그룹아이디',
  `MEMBER_CD` varchar(10) NOT NULL COMMENT '사용자고유번호',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  PRIMARY KEY (`GROUP_ID`,`MEMBER_CD`),
  UNIQUE KEY `PK_CM_MEMBER_AUTH` (`GROUP_ID`,`MEMBER_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자별그룹';



CREATE TABLE `TCM_MEMBER_HIST` (
  `MEMBER_CD` varchar(10) NOT NULL COMMENT '사용자고유번호',
  `VERSION_ID` varchar(12) NOT NULL COMMENT '변경이력아이디',
  `USER_ID` varchar(60) DEFAULT NULL COMMENT '로그인아이디',
  `USER_NM` varchar(120) DEFAULT NULL COMMENT '사용자명',
  `USER_PW` varchar(100) DEFAULT NULL COMMENT '로그인비밀번호',
  `TEL_NO` varchar(24) DEFAULT NULL COMMENT '전화번호',
  `HP_NO` varchar(24) DEFAULT NULL COMMENT '핸드폰번호',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '이메일',
  `BRTH` varchar(8) DEFAULT NULL COMMENT '생년월일',
  `POSITION_CD` varchar(20) DEFAULT NULL COMMENT '직책코드',
  `POSITION_NM` varchar(100) DEFAULT NULL COMMENT '직책명',
  `CLASS_CD` varchar(20) DEFAULT NULL COMMENT '직급코드',
  `CLASS_NM` varchar(100) DEFAULT NULL COMMENT '직급명',
  `DEPT_CD` varchar(8) DEFAULT NULL COMMENT '담당자부서코드',
  `DEPT_NM` varchar(120) DEFAULT NULL COMMENT '담당자부서명',
  `POSTN_BRNCH_CD` varchar(8) DEFAULT NULL COMMENT '소속지점코드',
  `MASTER_ID` varchar(20) DEFAULT NULL COMMENT '회사마스터아이디',
  `EMP_NO` varchar(20) DEFAULT NULL COMMENT '사원번호',
  `USER_TYPE` char(2) DEFAULT NULL COMMENT '사용자타입[9001]',
  `USER_ROLES` varchar(100) DEFAULT NULL COMMENT 'ROAL',
  `PASS_CHG_YN` char(1) DEFAULT NULL COMMENT '비밀번호필수변경유무',
  `PASS_LAST_DY` varchar(8) DEFAULT NULL COMMENT '비밀번호최종변경일자',
  `PASS_FAIL_CNT` int(1) DEFAULT NULL COMMENT '비밀번호실패횟수',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `LOCK_YN` char(1) DEFAULT NULL COMMENT 'LOCK유무',
  `FORGOT_PWD_CODE` varchar(6) DEFAULT NULL COMMENT 'Forgot Password URL code',
  `FORGOT_PWD_LST_DY` varchar(8) DEFAULT NULL COMMENT 'Forgot Password Expiration Date',
  `ID_TYPE_CD` varchar(7) DEFAULT NULL COMMENT '아이디타입코드[C001]',
  `UI_TYPE` varchar(10) DEFAULT NULL COMMENT 'UI 타입(탭방식 : T , 일반 : F)',
  `STR_CD` varchar(5) DEFAULT NULL COMMENT '점포코드',
  `WORKNG_SE_CD` varchar(8) DEFAULT NULL COMMENT '재직구분코드[SAL123]',
  `FIND_PWD_CD` varchar(7) DEFAULT NULL COMMENT '패스워드찾기질문코드',
  `FIND_PWD_ANSWER` varchar(200) DEFAULT NULL COMMENT '패스워드찾기질문답변',
  `ZIP_NO` varchar(7) DEFAULT NULL COMMENT '우편번호',
  `ZIP_ADDR` varchar(100) DEFAULT NULL COMMENT '우편주소',
  `DTL_ADDR` varchar(100) DEFAULT NULL COMMENT '상세주소',
  `GEN_DIVN_CD` varchar(7) DEFAULT NULL COMMENT '생성구분코드[9007]',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`MEMBER_CD`,`VERSION_ID`),
  UNIQUE KEY `PK_CM_MEMBER_HIST` (`MEMBER_CD`,`VERSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자정보변경이력';


CREATE TABLE `TCM_MEMBER_TEAM_AUTH` (
  `TEAM_CD` varchar(10) NOT NULL COMMENT '관리팀코드',
  `MEMBER_CD` varchar(10) NOT NULL COMMENT '사용자고유번호',
  `WORK_DIVN_CD` varchar(7) DEFAULT NULL COMMENT '작업구분코드[5013]',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`TEAM_CD`,`MEMBER_CD`),
  UNIQUE KEY `PK_CM_MEMBER_TEAM_AUTH` (`TEAM_CD`,`MEMBER_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자팀권한';  



CREATE TABLE `TCM_MENU` (
  `MENU_ID` int(10) NOT NULL COMMENT '메뉴아이디',
  `PARENT_ID` int(10) NOT NULL COMMENT '부모아이디',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '항목 별칭',
  `TITLE_SUB` varchar(100) DEFAULT NULL COMMENT '항목추가별칭',
  `TITLE_EXTEND` varchar(100) DEFAULT NULL COMMENT '항목추가확정명',
  `MENU_FG` char(1) DEFAULT NULL COMMENT '메뉴타입',
  `COMMAND_FG` char(1) DEFAULT NULL COMMENT '메뉴실행타입',
  `HIDE_YN` char(1) DEFAULT NULL COMMENT '메뉴숨김',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `IS_EXPANDED` char(1) DEFAULT NULL COMMENT '메뉴확장여부',
  `WEB_PAGE` varchar(200) DEFAULT NULL COMMENT '페이지경로',
  `EXTRA_INFO` varchar(200) DEFAULT NULL COMMENT '메뉴확장정보',
  `SORT_IN_LEVEL` int(10) DEFAULT NULL COMMENT '메뉴소팅레벨',
  `MENU_LEVEL` int(10) DEFAULT NULL COMMENT '메뉴레벨',
  `DTL_LOG_YN` char(1) DEFAULT 'N' COMMENT '상세로그생성여부',
  `BUILDIN_YN` char(1) DEFAULT 'N' COMMENT '빌드인여부',
  `VDI_CD` char(1) DEFAULT NULL COMMENT '접근아이피정책코드[9050]',
  `EXC_TYPE_FG` varchar(1) DEFAULT NULL COMMENT 'DEVICE[W/M/R]',
  `MENU_ICON` varchar(50) DEFAULT NULL COMMENT '메뉴아이콘명',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`MENU_ID`),
  UNIQUE KEY `PK_CM_MENU` (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메뉴정보';


CREATE TABLE `TCM_NOTICE_EXCLUDE` (
  `USER_ID` varchar(60) NOT NULL COMMENT '로그인아이디',
  `TEMPLETE_GBN` int(3) NOT NULL COMMENT '발송업무구분',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  PRIMARY KEY (`USER_ID`,`TEMPLETE_GBN`),
  UNIQUE KEY `PK_CM_NOTICE_EXCLUDE` (`USER_ID`,`TEMPLETE_GBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `TCM_NOTICE_HIST_TEMP` (
  `NOTICE_SEQ` varchar(12) NOT NULL COMMENT '알림순번(YYMMDD+6자리)',
  `SEND_GBN` varchar(12) NOT NULL COMMENT '발송구분(SMS/LMS : 1 | 알림톡 : 2 | 이메일 : 3)',
  `TMPLETE_GBN` varchar(3) NOT NULL COMMENT '템플릿구분',
  `RCV_HP_NO` varchar(400) DEFAULT NULL COMMENT '수신자전화번호',
  `RCV_EMAIL` varchar(100) DEFAULT NULL COMMENT '수신자이메일',
  `RCV_USER_ID` varchar(60) DEFAULT NULL COMMENT '수신자 사용자 ID',
  `GRP_KEY` varchar(200) DEFAULT NULL COMMENT '계약번호(기타키값)',
  `PARAM1` varchar(2000) DEFAULT NULL COMMENT '변수1',
  `PARAM2` varchar(2000) DEFAULT NULL COMMENT '변수2',
  `PARAM3` varchar(2000) DEFAULT NULL COMMENT '변수3',
  `PARAM4` varchar(2000) DEFAULT NULL COMMENT '변수4',
  `PARAM5` varchar(2000) DEFAULT NULL COMMENT '변수5',
  `API_FLAG` char(1) DEFAULT NULL COMMENT 'API 호출 여부(대기:T,성공:S,실패:F)',
  `API_RTN_CD` varchar(3) DEFAULT NULL COMMENT 'API 호출 결과코드',
  `API_RTN_MSG` varchar(2000) DEFAULT NULL COMMENT 'API 호출 결과메세지',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`NOTICE_SEQ`),
   UNIQUE KEY `PK_CM_NOTICE_HIST_TEMP` (`NOTICE_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='알림톡 발송 이력';



CREATE TABLE `TCM_PERSINFO_LOG` (
  `LOG_SEQ` bigint(20) NOT NULL AUTO_INCREMENT,
  `REND_DIVN_CD` varchar(7) DEFAULT NULL COMMENT '개인정보열람사유코드[9016]',
  `REND_CMT` varchar(200) DEFAULT NULL COMMENT '열람사유내용',
  `PERS_INFO` varchar(400) DEFAULT NULL COMMENT '개인정보조회내용',
  `PERS_PCL_NO` varchar(20) DEFAULT NULL COMMENT '개인정보별번호[사용자고유번호]',
  `ACCESS_DY` varchar(8) DEFAULT NULL COMMENT '정보조회일시',
  `PERS_BIZS_CD` varchar(7) DEFAULT NULL COMMENT '정보조회업무구분코드[9017]',
  `RES_IP` varchar(20) DEFAULT NULL COMMENT '접근아이피',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  PRIMARY KEY (`LOG_SEQ`),
  KEY `IX_CM_PERSINFO_LOG` (`ACCESS_DY`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8 COMMENT='개인정보조회이력';



CREATE TABLE `TCM_PERSONALINFO_LOG` (
  `LOG_IDX` varchar(14) NOT NULL COMMENT '로그인덱스',
  `SESSION_ID` varchar(100) NOT NULL COMMENT '브라우저세션아이디',
  `RES_PGM_URL` varchar(200) DEFAULT NULL COMMENT '접근프로그램URL',
  `RES_FULL_URL` varchar(400) DEFAULT NULL COMMENT '접근URL전체경로',
  `RES_IP` varchar(20) DEFAULT NULL COMMENT '접근아이피',
  `RES_CNT` int(11) DEFAULT NULL COMMENT '접근횟수',
  `ACCESS_DY` varchar(8) DEFAULT NULL COMMENT '접근일자',
  `MENU_ID` int(10) DEFAULT NULL COMMENT '메뉴아이디',
  `EXECUTE_TIME` varchar(200) DEFAULT NULL COMMENT '실행시간MS',
  `EXECUTE_TIME_AVG` decimal(10,2) DEFAULT NULL COMMENT '실행시간평균MS',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  PRIMARY KEY (`LOG_IDX`),
  UNIQUE KEY `PK_CM_PERSONALINFO_LOG` (`LOG_IDX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='시스템접근로그';



CREATE TABLE `TCM_PERSONALINFO_LOG_DTL` (
  `LOG_IDX` varchar(14) NOT NULL COMMENT '로그인덱스',
  `SEQ` int(10) NOT NULL COMMENT '순번',
  `DTL_COMMAND` text DEFAULT NULL COMMENT '추가세부정보',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`LOG_IDX`,`SEQ`),
  UNIQUE KEY `PK_CM_PERSONALINFO_LOG_DTL` (`LOG_IDX`,`SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `TCM_SSO_INFO` (
  `TOKEN_ID` varchar(32) NOT NULL COMMENT '토큰아이디',
  `BIZS_DOC_NO` varchar(50) DEFAULT NULL COMMENT '연동대상데이터키',
  `USER_PCL_NO` varchar(20) DEFAULT NULL COMMENT '사용자식별번호',
  `BIZS_IDNT_CD` varchar(5) DEFAULT NULL COMMENT '업무구분코드',
  `SESSION_ID` varchar(100) DEFAULT NULL COMMENT '브라우저세션아이디',
  `VALID_TM` int(10) DEFAULT NULL COMMENT '유효신간(초)',
  `CONN_DT` varchar(14) DEFAULT NULL COMMENT '접속일시',
  `EXPRIE_YN` char(1) DEFAULT 'N' COMMENT '만료여부',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  PRIMARY KEY (`TOKEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SSO접근정보';



CREATE TABLE `TCM_TEAMINFO` (
  `TEAM_CD` varchar(10) NOT NULL COMMENT '관리팀코드',
  `MASTER_ID` varchar(20) NOT NULL COMMENT '회사마스터아이디',
  `TEAM_NM` varchar(20) DEFAULT NULL COMMENT '팀명',
  `TEAM_DIVN_CD` varchar(7) NOT NULL COMMENT '팀유형구분[5014]',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `BUILDIN_YN` char(1) DEFAULT NULL COMMENT '빌드인여부',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  `MOD_DT` datetime DEFAULT NULL COMMENT '수정일시',
  `MOD_ID` varchar(60) DEFAULT NULL COMMENT '수정자아이디',
  PRIMARY KEY (`TEAM_CD`),
   UNIQUE KEY `PK_CM_TEAMINFO` (`TEAM_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회사팀정보';



CREATE TABLE `TCM_TERMS` (
  `TERMS_DIVN_CD` varchar(7) NOT NULL COMMENT '정보동의구분코드[9012]',
  `SEQ` int(10) NOT NULL COMMENT '순번',
  `TERMS_VERSION_INFO` varchar(100) DEFAULT NULL COMMENT '정보동의버전정보',
  `TERMS_CONTENT` text DEFAULT NULL COMMENT '동의정보내용',
  `TERMS_ST_DY` varchar(8) DEFAULT NULL COMMENT '동의정보시행일자',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일시',
  `REG_ID` varchar(60) DEFAULT NULL COMMENT '등록자아이디',
  PRIMARY KEY (`TERMS_DIVN_CD`,`SEQ`),
  UNIQUE KEY `PK_CM_TERMS` (`TERMS_DIVN_CD`,`SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `TCM_TERMS_AGREE` (
  `AGREE_REG_NO` varchar(14) NOT NULL COMMENT '동의인증고유번호',
  `TERMS_DIVN_CD` varchar(7) DEFAULT NULL COMMENT '정보동의구분코드[9012]',
  `SEQ` int(10) DEFAULT NULL COMMENT '순번',
  `TERMS_PCL_NO` varchar(20) DEFAULT NULL COMMENT '사용자고유번호[동의자고유번호]',
  `TERMS_PCL_IDX` varchar(100) DEFAULT NULL COMMENT '정보동의자식별값',
  `AGREE_YN` char(1) DEFAULT NULL COMMENT '동의인증여부',
  `SYS_NM` varchar(50) DEFAULT NULL COMMENT '시스템명',
  `TERMS_AGREE_DT` datetime DEFAULT NULL COMMENT '정보동의변경일시',
  PRIMARY KEY (`AGREE_REG_NO`),
  UNIQUE KEY `PK_CM_TERMS_AGREE` (`AGREE_REG_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








/* ----------VIEW-----------------*/


CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `VCM_MENU` AS
select
    case
        when `M3`.`MENU_ID` is not null then `M3`.`MENU_ID`
        when `M2`.`MENU_ID` is not null then `M2`.`MENU_ID`
        when `M1`.`MENU_ID` is not null then `M1`.`MENU_ID`
    end AS `M_MENU_ID`,
    case
        when `M1`.`MENU_ID` is not null then `M1`.`MENU_ID`
        when `M2`.`MENU_ID` is not null then `M2`.`MENU_ID`
        when `M3`.`MENU_ID` is not null then `M3`.`MENU_ID`
    end AS `M_PARENT_ID`,
    cast(case when `M1`.`MENU_ID` is not null then '1' else '0' end as signed) + cast(case when `M2`.`MENU_ID` is not null then '1' else '0' end as signed) + cast(case when `M3`.`MENU_ID` is not null then '1' else '0' end as signed) AS `MENU_LEVEL`,
    concat(case when `M1`.`MENU_ID` is not null then concat(',', `M1`.`SORT_IN_LEVEL`, ',', `M1`.`MENU_ID`) else '' end, case when `M2`.`MENU_ID` is not null then concat(',', `M2`.`SORT_IN_LEVEL`, ',', `M2`.`MENU_ID`) else '' end, case when `M3`.`MENU_ID` is not null then concat(',', `M3`.`SORT_IN_LEVEL`, ',', `M3`.`MENU_ID`) else '' end) AS `SORT_PATH`,
    `M3`.`MENU_ID` AS `MENU_ID`,
    `M3`.`PARENT_ID` AS `PARENT_ID`,
    `M3`.`TITLE` AS `TITLE`,
    `M3`.`TITLE_SUB` AS `TITLE_SUB`,
    `M3`.`MENU_FG` AS `MENU_FG`,
    `M3`.`COMMAND_FG` AS `COMMAND_FG`,
    `M3`.`HIDE_YN` AS `HIDE_YN`,
    `M3`.`USE_YN` AS `USE_YN`,
    `M3`.`IS_EXPANDED` AS `IS_EXPANDED`,
    `M3`.`WEB_PAGE` AS `WEB_PAGE`,
    `M3`.`EXTRA_INFO` AS `EXTRA_INFO`,
    `M3`.`SORT_IN_LEVEL` AS `SORT_IN_LEVEL`,
    `M3`.`MENU_ICON` AS `MENU_ICON`
from
    ((`TCM_MENU` `M3`
left join `TCM_MENU` `M2` on
    (`M3`.`PARENT_ID` = `M2`.`MENU_ID`
        and `M2`.`MENU_ID` not in ('10000', '10001')))
left join `TCM_MENU` `M1` on
    (`M2`.`PARENT_ID` = `M1`.`MENU_ID`
        and `M1`.`MENU_ID` not in ('10000', '10001')))
where
    `M3`.`MENU_ID` not in ('10000', '10001')
order by
    concat(case when `M1`.`MENU_ID` is not null then concat(',', `M1`.`SORT_IN_LEVEL`, ',', `M1`.`MENU_ID`) else '' end, case when `M2`.`MENU_ID` is not null then concat(',', `M2`.`SORT_IN_LEVEL`, ',', `M2`.`MENU_ID`) else '' end, case when `M3`.`MENU_ID` is not null then concat(',', `M3`.`SORT_IN_LEVEL`, ',', `M3`.`MENU_ID`) else '' end);
    
   
   
   
   
   
   
   /*--------------FUNCTION------------------- */
   
   
   CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`CONNECT_BY_PARENT_MENU`(value INT) RETURNS int(11)
    READS SQL DATA
BEGIN   
         DECLARE _MENU_ID INT;
         DECLARE _PARENT_ID INT;
         DECLARE _NEXT INT;   
         DECLARE CONTINUE HANDLER FOR NOT FOUND SET @id = NULL;

         SET _PARENT_ID = @id;
         SET _MENU_ID = -1;

         IF @id IS NULL THEN
               RETURN NULL;
         END IF;

         LOOP
               SELECT  MIN(MENU_ID)
               INTO    @id
               FROM    TCM_MENU
               WHERE   PARENT_ID = _PARENT_ID
                     AND MENU_ID > _MENU_ID;
                     
               IF @id IS NOT NULL OR _PARENT_ID = @start_with THEN
                     SET @level = @level + 1;
                     RETURN @id;
               END IF;
               SET @level := @level - 1;
               SELECT  MENU_ID, PARENT_ID
               INTO    _MENU_ID, _PARENT_ID
               FROM    TCM_MENU
               WHERE   MENU_ID = _PARENT_ID;

         END LOOP;       
   END;
  
  
  
  CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_CODE_LANG_RTN`(
                                       as_com_cd    VARCHAR(20),   -- 코드종류
                                       as_dtl_cd    VARCHAR(20),   -- 코드
                                       as_lang_cd   VARCHAR(20)    -- 언어 코드
                               ) RETURNS text CHARSET utf8
BEGIN
     DECLARE v_CODE_NAME TEXT ;
     SELECT  
             CASE WHEN as_lang_cd = 'KO'
                  THEN a.dtl_nm
                  WHEN as_lang_cd = 'EN'
                  THEN a.dtl_sub_nm
                  ELSE a.dtl_extend_nm 
              END  
      INTO  v_CODE_NAME
      FROM  TCM_CODE_DTL a
      WHERE  a.com_cd  = as_com_cd
       AND  a.dtl_cd  = as_dtl_cd;
      RETURN v_CODE_NAME;
   END;

CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_CODE_RTN`(
                                       as_com_cd    varchar(20),   -- 코드종류
                                       as_dtl_cd    varchar(20)   -- 코드
                               ) RETURNS text CHARSET utf8
BEGIN
     DECLARE v_CODE_NAME TEXT ;

     SELECT  a.DTL_NM 
      INTO  v_CODE_NAME
      FROM  TCM_CODE_DTL a
      WHERE  a.COM_CD  = as_com_cd
       AND  a.DTL_CD  = as_dtl_cd;

      RETURN v_CODE_NAME;

   END;

CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_CODE_SUB_RTN`(

                                       as_com_cd    varchar(20),     -- 코드종류
                                       as_dtl_cd    varchar(20)      -- 코드
                                     ) RETURNS text CHARSET utf8
BEGIN
      
     DECLARE v_CODE_NAME TEXT ;
    
     SELECT  a.DTL_SUB_NM
      INTO  v_CODE_NAME
      FROM  TCM_CODE_DTL a
      WHERE  a.COM_CD  = as_com_cd
       AND  a.DTL_CD  = as_dtl_cd;

      RETURN v_CODE_NAME;

   END;

CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_DATE_LANG_RTN`(
                                       as_day     varchar(8),   -- 코드종류
                                       as_sign    varchar(4),   -- 코드
                                       as_lang_cd varchar(2)    -- 언어 코드
                               ) RETURNS text CHARSET utf8
BEGIN
    DECLARE v_CONV_DATE TEXT ;

    IF as_day IS NULL OR as_day = ''  THEN
       SET v_CONV_DATE = 'noFoundData';
       RETURN v_CONV_DATE;
    END IF; 
   
    SELECT
           CASE WHEN as_lang_cd = 'KO' THEN 
                  CONCAT(DATE_FORMAT(as_day,'%Y'), as_sign , DATE_FORMAT(as_day,'%m'), as_sign , DATE_FORMAT(as_day,'%d')) 
               ELSE
                      CONCAT(DATE_FORMAT(as_day,'%d'), as_sign , DATE_FORMAT(as_day,'%m'), as_sign , DATE_FORMAT(as_day,'%Y')) 
               END AS RTN_DAY 
        INTO  v_CONV_DATE
     FROM DUAL;
   
    RETURN v_CONV_DATE;

END;

CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_FULL_DATE_LANG_RTN`(
                                       as_day     varchar(14),   -- 코드종류
                                       as_sign    varchar(4),   -- 코드
                                       as_lang_cd varchar(2)    -- 언어 코드
                               ) RETURNS text CHARSET utf8
BEGIN
    DECLARE v_CONV_DATE TEXT ;

    IF as_day IS NULL OR as_day = ''  THEN
       SET v_CONV_DATE = 'noFoundData';
       RETURN v_CONV_DATE;
    END IF; 
   
    SELECT
           CASE WHEN as_lang_cd = 'KO' THEN 
                  CONCAT(DATE_FORMAT(as_day,'%Y'), as_sign , DATE_FORMAT(as_day,'%m'), as_sign , DATE_FORMAT(as_day,'%d'),' ', DATE_FORMAT(as_day, '%H'),':', DATE_FORMAT(as_day, '%i'), ':', DATE_FORMAT(as_day, '%s')) 
               ELSE
                      CONCAT(DATE_FORMAT(as_day,'%d'), as_sign , DATE_FORMAT(as_day,'%m'), as_sign , DATE_FORMAT(as_day,'%Y'),' ', DATE_FORMAT(as_day, '%H'),':', DATE_FORMAT(as_day, '%i'), ':', DATE_FORMAT(as_day, '%s')) 
               END AS RTN_DAY 
        INTO  v_CONV_DATE
     FROM DUAL;
   
    RETURN v_CONV_DATE;

END;

CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_MONTH_LANG_RTN`(
                                       as_day     varchar(6),   -- 코드종류
                                       as_sign    varchar(4),   -- 코드
                                       as_lang_cd varchar(2)    -- 언어 코드
                               ) RETURNS text CHARSET utf8
BEGIN
    DECLARE v_CONV_DATE TEXT ;

    IF as_day IS NULL OR as_day = ''  THEN
       SET v_CONV_DATE = 'noFoundData';
       RETURN v_CONV_DATE;
    END IF; 
   
    SELECT
           CASE WHEN as_lang_cd = 'KO' THEN 
                  CONCAT(SUBSTRING(as_day,1,4), as_sign , SUBSTRING(as_day,5)) 
               ELSE
                      CONCAT(SUBSTRING(as_day,5), as_sign , SUBSTRING(as_day,1,4)) 
               END AS RTN_DAY 
        INTO  v_CONV_DATE
     FROM DUAL;
   
    RETURN v_CONV_DATE;

END;

CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_WORK_DAY_RTN`(
	as_st_day	datetime		-- 시작일자
	,as_end_day datetime		-- 종료일자
) RETURNS int(11)
BEGIN
	DECLARE v_WORK_DAY TEXT;		-- 영업일
	DECLARE v_st_day varchar(8);	-- 시작일자 yyyymmdd
	DECLARE v_end_day varchar(8);	-- 종료일자 yyyymmdd

	-- 시작일자가 빈 값일 경우, NULL 셋팅
	IF
		as_st_day IS NULL OR as_st_day = ''
	THEN SET v_WORK_DAY = NULL;
       RETURN v_WORK_DAY;
    ELSE
    	SET v_st_day = DATE_FORMAT(as_st_day, '%Y%m%d');
	END IF;
	

	-- 종료일자가 빈 값일 경우, 오늘 날짜 셋팅 
	IF
		as_end_day IS NULL OR as_end_day = ''
	THEN
		SET v_end_day = DATE_FORMAT(NOW(), '%Y%m%d');
	ELSE
    	SET v_end_day = DATE_FORMAT(as_end_day, '%Y%m%d');
	END IF;

	SELECT
		ABS(DATEDIFF(v_st_day, v_end_day)) + 1
		- ABS(DATEDIFF(ADDDATE(v_end_day, INTERVAL 1 - DAYOFWEEK(v_end_day) DAY),
                       ADDDATE(v_st_day, INTERVAL 1 - DAYOFWEEK(v_st_day) DAY))) / 7 * 2
        - (DAYOFWEEK(IF(v_st_day < v_end_day, v_st_day, v_end_day)) = 1)
        - (DAYOFWEEK(IF(v_st_day > v_end_day, v_st_day, v_end_day)) = 7)
        INTO v_WORK_DAY
    FROM DUAL;
   
	RETURN v_WORK_DAY;
END;

CREATE DEFINER=`KRMC`@`%` FUNCTION `KRMC`.`FN_WORK_PROC_PERD_RTN`(
	as_st_day	datetime		-- 시작일자
   ,as_perd_day int		-- 적용 영업일 수
) RETURNS text CHARSET utf8
BEGIN
	DECLARE v_PERD_RTN TEXT;		-- 영업일
	DECLARE v_st_day datetime;	-- 시작일자 yyyymmdd
	DECLARE v_perd_day int;	-- 적용 영업일 수
	-- 시작일자가 빈 값일 경우, NULL 셋팅
	IF
		as_st_day IS NULL OR as_st_day = '' OR as_perd_day IS NULL OR as_perd_day =''
	THEN SET v_PERD_RTN = NULL;
       RETURN v_PERD_RTN;
    ELSE
    	SET v_st_day = as_st_day
		   ,v_perd_day= as_perd_day;
	END IF;

	SELECT
		CASE WHEN DAYOFWEEK(DATE_ADD(v_st_day, INTERVAL v_perd_day DAY)) ='1' THEN DATE_FORMAT(DATE_ADD(DATE_ADD(v_st_day ,INTERVAL v_perd_day DAY),INTERVAL 1 DAY),'%Y%m%d')
		WHEN DAYOFWEEK(DATE_ADD(v_st_day ,INTERVAL v_perd_day DAY))='7' THEN DATE_FORMAT(DATE_ADD(DATE_ADD(v_st_day ,INTERVAL v_perd_day DAY),INTERVAL 2 DAY),'%Y%m%d')
		ELSE DATE_FORMAT(DATE_ADD(v_st_day ,INTERVAL v_perd_day DAY),'%Y%m%d')
		END AS PROC_PERD
		INTO v_PERD_RTN
	FROM DUAL;
   
	RETURN v_PERD_RTN;
END;


/*----PROCEDURE ---------*/

CREATE DEFINER=`KRMC`@`%` PROCEDURE `KRMC`.`SP_ACCESS_LOG_CREATE`(
   IN 	P_SESSION_ID 			VARCHAR(50),      
   IN 	P_RES_PGM_URL 			VARCHAR(200),     
   IN 	P_RES_FULL_URL 			VARCHAR(400),    	
   IN 	P_RES_IP 				VARCHAR(20),      
   IN 	P_REG_ID 				VARCHAR(20),      
   IN 	P_MENU_ID 				INT(10),          
   IN 	P_EXECUTE_TIME 			VARCHAR(200),    	
   IN 	P_DTL_COMMAND 			VARCHAR(4000), 	
   OUT 	P_RSPNS_CD 				VARCHAR(10),      
   OUT 	P_MESSAGE 				VARCHAR(200)      




)
PROC_LABEL:BEGIN

   DECLARE V_LOG_IDX 		VARCHAR(14); 
   DECLARE V_CREATE_CNT		INT DEFAULT 0;
   
   
   
    
     DECLARE EXIT HANDLER FOR SQLEXCEPTION
	  BEGIN
			GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, 
 			@errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
 			
 			SET P_RSPNS_CD = @errno;
			SET P_MESSAGE  = @text;
 
			ROLLBACK;
	  END;
	 	
	  
	  
    
    
     IF (P_SESSION_ID   IS NULL or TRIM(P_SESSION_ID)   ='')  OR
        (P_RES_FULL_URL IS NULL or TRIM(P_RES_FULL_URL) ='')
     THEN
        SET P_RSPNS_CD = '-1';
        SET P_MESSAGE  = 'Necessary Input Parameter is null Error';
        LEAVE PROC_LABEL;
     END IF;
     
     
     
     	  
			  
    
     BEGIN
     
                                
     		
       	SET V_LOG_IDX = (SELECT MAX(LOG_IDX)  LOG_IDX
             FROM TCM_PERSONALINFO_LOG T1
            WHERE T1.ACCESS_DY  BETWEEN  DATE_FORMAT(NOW(),'%Y%m%d')  AND DATE_FORMAT(NOW(),'%Y%m%d')
              AND T1.SESSION_ID   = P_SESSION_ID 
              AND T1.RES_FULL_URL = P_RES_FULL_URL )
           ;
         
         
          START TRANSACTION;
         	
         IF V_LOG_IDX IS NULL THEN
         
	          	
	          	SET V_LOG_IDX = (SELECT CONCAT(DATE_FORMAT(NOW(),'%y%m%d'),LPAD(NEXT VALUE FOR SQ_CM_PERSONALINFO_LOG,8,'0')) );
				
				  	INSERT INTO TCM_PERSONALINFO_LOG (
	                     LOG_IDX          
							  ,SESSION_ID       
	                    ,RES_PGM_URL      
	                    ,RES_FULL_URL     
	                    ,RES_IP           
	                    ,RES_CNT          
	                    ,REG_DT           
	                    ,MOD_DT           
	                    ,REG_ID           
	                    ,ACCESS_DY        
							  ,MENU_ID          
	                    ,EXECUTE_TIME     
	                    ,EXECUTE_TIME_AVG 
	              )
	              VALUES(V_LOG_IDX
	                    ,P_SESSION_ID
	                    ,P_RES_PGM_URL
	                    ,P_RES_FULL_URL
	                    ,P_RES_IP
	                    ,1
	                    ,NOW()
	                    ,NOW()
	                    ,P_REG_ID
	                    ,DATE_FORMAT(NOW(),'%Y%m%d')
	                    ,P_MENU_ID
	                    ,P_EXECUTE_TIME
	                    ,P_EXECUTE_TIME
					 );
					  
         ELSE
					
					UPDATE TCM_PERSONALINFO_LOG SET
                  RES_CNT = RES_CNT+1
                 ,MOD_DT  = NOW()
                 ,EXECUTE_TIME	   = CASE WHEN LENGTH(EXECUTE_TIME)-LENGTH(REPLACE(EXECUTE_TIME,',',''))  <  9
                 					  		       THEN CONCAT(EXECUTE_TIME,',',P_EXECUTE_TIME)
                 					   			 ELSE P_EXECUTE_TIME
                 					   			 END
                 ,EXECUTE_TIME_AVG = 
                 (  SELECT  ROUND(SUM(TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(A.DATA, ',', B.RN), ',', -1))) /  COUNT(1),2)
							FROM   (SELECT    CASE WHEN CONVERT((LENGTH(A.DATA) - LENGTH(REPLACE(A.DATA, ',', ''))) / LENGTH(','), UNSIGNED) > 0 THEN
							                         CONVERT((LENGTH(A.DATA) - LENGTH(REPLACE(A.DATA, ',', ''))) / LENGTH(',') + 1, UNSIGNED)
							                       ELSE 0
							                   END AS DELIMITER_CNT
							                  ,A.DATA
							         FROM      (SELECT  CASE WHEN LENGTH(EXECUTE_TIME)-LENGTH(REPLACE(EXECUTE_TIME,',',''))  <  9
						                 				     THEN CONCAT(EXECUTE_TIME,',',P_EXECUTE_TIME)
						                 					  ELSE P_EXECUTE_TIME
						                 					END  AS DATA 
										             FROM TCM_PERSONALINFO_LOG   WHERE LOG_IDX = V_LOG_IDX ) A) A 
							        INNER JOIN (SELECT  CAST(seq AS INT) as RN FROM seq_1_to_1000) B ON (B.RN <= A.DELIMITER_CNT)
					  ) 
					  ,MOD_DT  = NOW()                    
              WHERE LOG_IDX = V_LOG_IDX;
              
		  END IF;
		  
		  
		  
		  
		   
		    
		    BEGIN
		
		       IF TRIM(P_DTL_COMMAND) IS NOT NULL THEN
		          INSERT INTO TCM_PERSONALINFO_LOG_DTL (
		           LOG_IDX      
		          ,SEQ          
		          ,DTL_COMMAND  
		          ,REG_DT        
		          ,REG_ID        
		          )
		          SELECT V_LOG_IDX, IFNULL(MAX(SEQ),0)+1 AS SEQ, P_DTL_COMMAND, NOW(), P_REG_ID
		          FROM TCM_PERSONALINFO_LOG_DTL WHERE LOG_IDX = V_LOG_IDX
		          ;
		      END IF;
		
		   
		    END;
		    
    
		  
		  COMMIT;
     		
     	  
   		SET P_RSPNS_CD  = '00';
   		SET P_MESSAGE   = 'SUCCESS';
     		
     END;
     
END;



/*----SEQUENCE----------*/
CREATE OR REPLACE SEQUENCE `SQ_APPRO_NO` start with 1 minvalue 1 maxvalue 99999999 increment by 1 cache 1000 cycle ENGINE=InnoDB;

CREATE OR REPLACE SEQUENCE `SQ_COMPCD` start with 1 minvalue 1 maxvalue 99999999 increment by 1 cache 1000 cycle ENGINE=InnoDB;

CREATE OR REPLACE SEQUENCE `SQ_NOTICE_SEQ` start with 1 minvalue 1 maxvalue 999999 increment by 1 cache 1000 cycle ENGINE=InnoDB COMMENT='Notice SEQ';

CREATE OR REPLACE SEQUENCE `SQ_CM_PERSONALINFO_LOG` start with 1 minvalue 1 maxvalue 99999999 increment by 1 cache 1000 cycle ENGINE=InnoDB;

CREATE OR REPLACE SEQUENCE `SQ_TERMS_REG_NO` start with 1 minvalue 1 maxvalue 99999999 increment by 1 cache 1000 cycle ENGINE=InnoDB;

CREATE OR REPLACE SEQUENCE `SQ_MEMBERCD` start with 1 minvalue 1 maxvalue 99999999 increment by 1 cache 1000 cycle ENGINE=InnoDB;

  
  
  
  
  
  
  /* 회사정보 view table */
  
  CREATE VIEW  VCM_COMPINFO  AS 
     SELECT  TSM.SALES_CD		AS MASTER_ID		-- 회사마스터아이디
     		,TSM.SALES_CD		AS PARENT_COMP_CD 	-- 부모사업장코드	
     		,TSAI.SALES_NM		AS COMP_NM			-- 회사명
     		,TSM.BSNS_NUM		AS BMAN_NO			-- 사업자번호 
     		,TSM.CORP_NUM		AS CORPN_NO			-- 법인번호
     		,'1'				AS TRADE_COMP_CD 	-- 거래유형(1:매출처)[M901]
     		,'1'				AS BMAN_DIVN_CD		-- 사업자구분코드[BZM01]
     		,'N'				AS AFFILIATE_YN 	-- 계열사유무
     		,TSM.BOSS_NM		AS CEO_NM			-- 대표자명 
     		,TSM.BUSI_CON		AS BTYP_NM			-- 업태명 
     		,TSM.BUSI_TYPE		AS BIZ_ITM_NM		-- 업종명 
     		,NULL 				AS REP_BRAND		-- 대표브랜드
     		,NULL 				AS FOU_DY			-- 설립일자
     		,TSM.TEL_NO     	AS REP_TEL_NO		-- 대표전화번호
     		,TSM.FAX_NO			AS FAX_TEL_NO		-- 팩스전화번호
     		,TSM.ZIP_CD   		AS ZIP_NO			-- 우편번호
     		,TSM.ADDR_1			AS ZIP_ADDR			-- 우편주소
     		,TSM.ADDR_2			AS DTL_ADDR			-- 상세주소 
     		,TSM.EMAIL			AS EMAIL			-- 이메일 
     		,NULL 				AS HMPG_URL			-- 홈페이지
     		,NULL 				AS MAIN_BIZ_RSUT	-- 주요사업실적
     		,'Y' 				AS HEDO_YN			-- 본사여부
     		,CASE WHEN TSM.USE_YN = 'Y' THEN  '2026000' ELSE '2026003' END	AS COMP_CLOSE_CD -- 휴폐업구분코드
     		,NULL 				AS DEAL_ITEM_CONTENT -- 취급품목내용
     		,TSAI.REMAKRS  		AS COMP_INTRO		 -- 회사소개
     		,CASE WHEN TSM.USE_YN = 'Y' THEN  '01' ELSE '03' END AS APRV_DIVN_CD	-- 승인구분코드[9068]
     		,'01' 				AS GEN_DIVN_CD		-- 생성구분코드[9066]
     		,NULL 				AS KSIC_LAR_CAT_CD	-- 표준산업대분류코드[SECTION CODE]
     		,NULL 				AS KSIC_MED_CAT_CD	-- 표준산업중분류코드[SUB_CLASS]
     		,NULL 				AS BANK_ACC_NO		-- Bank account No
     		,NULL 				AS BANK_ACC_NM		-- Bank Account Name
			,NULL 				AS BANK_BENE_NM		-- Bank Beneficiary Name
			,NULL 				AS CON_ISSUED_BY	-- Contract Issued by
			,NULL 				AS CON_ISSUED_ON 	-- Contract Issued on
			,TSM.REG_DT			AS REG_DT 			-- 등록일자		
			,TSM.REG_ID 		AS REG_ID 			-- 등록자아이디 
			,TSM.MOD_DT 		AS MOD_DT  			-- 수정일자 
			,TSM.MOD_ID 		AS MOD_ID  			-- 수정자아이디 
		FROM TFM_SALES_MST TSM INNER JOIN TFM_SALES_ADD_INFO TSAI ON TSM.SALES_CD = TSAI.SALES_CD
   		UNION
		SELECT TBM.BUY_CD 		AS MASTER_ID		-- 회사마스터아이디
     		,TBM.BUY_CD			AS PARENT_COMP_CD 	-- 부모사업장코드	
     		,TBM.BUY_NM 		AS COMP_NM			-- 회사명
     		,TBM.BSNS_NUM		AS BMAN_NO			-- 사업자번호 
     		,TBM.CORP_NUM		AS CORPN_NO			-- 법인번호
     		,'2'				AS TRADE_COMP_CD 	-- 거래유형(2:매입처)[M901]
     		,'1'				AS BMAN_DIVN_CD		-- 사업자구분코드[BZM01]
     		,'N'				AS AFFILIATE_YN 	-- 계열사유무
     		,TBM.BOSS_NM		AS CEO_NM			-- 대표자명 
     		,TBM.BUSI_CON		AS BTYP_NM			-- 업태명 
     		,TBM.BUSI_TYPE		AS BIZ_ITM_NM		-- 업종명 
     		,NULL 				AS REP_BRAND		-- 대표브랜드
     		,NULL 				AS FOU_DY			-- 설립일자
     		,TBM.TEL_NO     	AS REP_TEL_NO		-- 대표전화번호
     		,TBM.FAX_NO			AS FAX_TEL_NO		-- 팩스전화번호
     		,TBM.ZIP_CD   		AS ZIP_NO			-- 우편번호
     		,TBM.ADDR_1			AS ZIP_ADDR			-- 우편주소
     		,TBM.ADDR_2			AS DTL_ADDR			-- 상세주소 
     		,TBM.EMAIL			AS EMAIL			-- 이메일 
     		,NULL 				AS HMPG_URL			-- 홈페이지
     		,NULL 				AS MAIN_BIZ_RSUT	-- 주요사업실적
     		,'Y' 				AS HEDO_YN			-- 본사여부
     		,CASE WHEN TBM.USE_YN = 'Y' THEN  '2026000' ELSE '2026003' END	AS COMP_CLOSE_CD -- 휴폐업구분코드
     		,NULL 				AS DEAL_ITEM_CONTENT -- 취급품목내용
     		,NULL  				AS COMP_INTRO		 -- 회사소개
     		,CASE WHEN TBM.USE_YN = 'Y' THEN  '01' ELSE '03' END AS APRV_DIVN_CD	-- 승인구분코드[9068]
     		,'01' 				AS GEN_DIVN_CD		-- 생성구분코드[9066]
     		,NULL 				AS KSIC_LAR_CAT_CD	-- 표준산업대분류코드[SECTION CODE]
     		,NULL 				AS KSIC_MED_CAT_CD	-- 표준산업중분류코드[SUB_CLASS]
     		,TBM.BANK_ACC_NUM   AS BANK_ACC_NO		-- Bank account No
     		,TBM.BANK_NM  		AS BANK_ACC_NM		-- Bank Account Name
			,TBM.BANK_DEP  		AS BANK_BENE_NM		-- Bank Beneficiary Name
			,NULL 				AS CON_ISSUED_BY	-- Contract Issued by
			,NULL 				AS CON_ISSUED_ON 	-- Contract Issued on
			,TBM.REG_DT			AS REG_DT 			-- 등록일자		
			,TBM.REG_ID 		AS REG_ID 			-- 등록자아이디 
			,TBM.MOD_DT 		AS MOD_DT  			-- 수정일자 
			,TBM.MOD_ID 		AS MOD_ID  			-- 수정자아이디 
          FROM TFM_BUY_MST TBM
        UNION
          SELECT TC.MASTER_ID		-- 회사마스터아이디
     		,TC.PARENT_COMP_CD 		-- 부모사업장코드	
     		,TC.COMP_NM				-- 회사명
     		,TC.BMAN_NO				-- 사업자번호 
     		,TC.CORPN_NO			-- 법인번호
     		,'9' AS TRADE_COMP_CD 	-- 거래유형(9: KRMC본사) [M901]
     		,TC.BMAN_DIVN_CD		-- 사업자구분코드[BZM01]
     		,TC.AFFILIATE_YN 		-- 계열사유무
     		,TC.CEO_NM				-- 대표자명 
     		,TC.BTYP_NM				-- 업태명 
     		,TC.BIZ_ITM_NM			-- 업종명 
     		,TC.REP_BRAND			-- 대표브랜드
     		,TC.FOU_DY				-- 설립일자
     		,TC.REP_TEL_NO			-- 대표전화번호
     		,TC.FAX_TEL_NO			-- 팩스전화번호
     		,TC.ZIP_NO				-- 우편번호
     		,TC.ZIP_ADDR			-- 우편주소
     		,TC.DTL_ADDR			-- 상세주소 
     		,TC.EMAIL				-- 이메일 
     		,TC.HMPG_URL			-- 홈페이지
     		,TC.MAIN_BIZ_RSUT		-- 주요사업실적
     		,TC.HEDO_YN				-- 본사여부
     		,TC.COMP_CLOSE_CD 		-- 휴폐업구분코드
     		,TC.DEAL_ITEM_CONTENT 	-- 취급품목내용
     		,TC.COMP_INTRO		 	-- 회사소개
     		,TC.APRV_DIVN_CD		-- 승인구분코드[9068]
     		,TC.GEN_DIVN_CD			-- 생성구분코드[9066]
     		,TC.KSIC_LAR_CAT_CD		-- 표준산업대분류코드[SECTION CODE]
     		,TC.KSIC_MED_CAT_CD		-- 표준산업중분류코드[SUB_CLASS]
     		,TC.BANK_ACC_NO			-- Bank account No
     		,TC.BANK_ACC_NM			-- Bank Account Name
			,TC.BANK_BENE_NM		-- Bank Beneficiary Name
			,TC.CON_ISSUED_BY		-- Contract Issued by
			,TC.CON_ISSUED_ON 		-- Contract Issued on
			,TC.REG_DT 				-- 등록일자		
			,TC.REG_ID 				-- 등록자아이디 
			,TC.MOD_DT  			-- 수정일자 
			,TC.MOD_ID  			-- 수정자아이디 
          FROM TCM_COMPINFO TC 
  
