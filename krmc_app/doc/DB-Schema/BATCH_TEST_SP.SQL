
CREATE  PROCEDURE SP_BATCH_CLOSEJOB (
	IN 	P_MONTH 			VARCHAR(6),      	-- 발주전표번호 [필수]
	
   	OUT P_RSPNS_CD 				VARCHAR(10),      	-- 응답코드
   	OUT P_MESSAGE 				VARCHAR(200)      	-- MESSAGE

/******************************************************************************/
/*                       (c) Copyright  DADA                                  */
/*                       All Rights Reserved                                  */
/******************************************************************************/
/*                                                                            */
/* Program Name   : SP_BATCH_CLOSEJOB                                         */
/* Description    : Batch job                                                 */
/****[ EX ]********************************************************************/
/*
set @P_RSPNS_CD :=null;
set @P_MESSAGE  :=null;

call SP_BATCH_CLOSEJOB(
	'202405'     	  		        -- P_MONTH 기준년월(yyyyMM)  [필수]
   
   ,@P_RSPNS_CD 					-- RETURN CODE
   ,@P_MESSAGE 						-- RETURN MESSAGE
	);
	
COMMIT;
SELECT @P_RSPNS_CD, @P_MESSAGE;
*/
   	
/******************************************************************************/
/* Change Request Number :                                                    */ 
/*----------------------------------------------------------------------------*/
/* Date        In Charge       Description                                    */
/*----------------------------------------------------------------------------*/
/* 2024-04-02  DADA         Initial Release                                  */
/******************************************************************************/

)
BEGIN

    
    DECLARE V_EXECUTE_CNT		INT DEFAULT 0;	    -- UPDATE/INSERT 반영 건수 
    DECLARE V_RSPNS_CD 		    VARCHAR(20);	    -- LOCAL MESSAGE CODE
    DECLARE V_MESSAGE 			TEXT;		        -- LOCAL MESSAGE
    
    DECLARE V_SYSDATE           VARCHAR(12) ;        -- test return data
    
    /*-- SQL Exception  Stored Procedure 종료 =================*/
    
    DECLARE ERR_SQL	TEXT;
    DECLARE ERR_MESSAGE TEXT;
    DECLARE ERR_NUMBER INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    	GET DIAGNOSTICS CONDITION 1 ERR_SQL = MESSAGE_TEXT, ERR_NUMBER = RETURNED_SQLSTATE;
    
    	IF(ERR_NUMBER IS NULL) THEN SET ERR_NUMBER = 99999; END IF;
 
    	SET P_RSPNS_CD = CASE WHEN V_RSPNS_CD IS NULL or TRIM(V_RSPNS_CD) ='' THEN  ERR_NUMBER ELSE  V_RSPNS_CD END ; 
		SET P_MESSAGE  = ERR_SQL;
 
    END;
    /**========================================================*/

   
    
    /*-- 자료조회 조건 검증 =========================================*/
	 IF (P_MONTH   IS NULL or TRIM(P_MONTH)   ='')  
     THEN
     	#에러 처리
		SET V_RSPNS_CD = '-1'; -- 사용자 정의 에러코드 설정 
		SIGNAL SQLSTATE  '99998' SET MESSAGE_TEXT ='Necessary Input Parameter is null Error' ,MYSQL_ERRNO = 5000;
   	END IF;
    
	/*=========================================================*/

	
	 
      BEGIN
	     /* TRANSACTION 은 호출하는 application에서 정의 됨.  (테스트 수행시 프로시저 호출 후 COMMIT 실행 ) */
		-- START TRANSACTION; 
	      
        
        SELECT date_format(SYSDATE(),'%Y-%m-%d') INTO V_SYSDATE
        ;
        SET V_EXECUTE_CNT = (SELECT ROW_COUNT()); -- 실행카운트 
	
        /*=====================================================================*/
    
         /* TRANSACTION 은 호출하는 application에서 정의 됨. */
        -- COMMIT;
 
        SET P_RSPNS_CD  = '00';
        SET P_MESSAGE   = CONCAT('SUCCESS',' [ EXECUTE:',CAST(V_EXECUTE_CNT 	AS CHAR),']');
	
     END;
    
END  