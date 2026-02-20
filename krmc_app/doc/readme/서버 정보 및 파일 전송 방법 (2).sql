116.124.133.233

root
Krmc12!

korearmcadmin
7e5C570c -> Krmc12! 변경

os 계정 : krmcwas / krmc5755

db 계정 : krmc / krmc121
db root / Krmc121

pscp -P 22 C:\send\* krmcwas@116.124.133.233:/home/krmcwas/src


*  WAS ( Tomcat ) 환경설정 정보

  1. Application Server 개정  (해당개정으로  Start / Shutdown 실행)

       id : krmcwas  pw : krmc5755

  2. 설치경로

      /usr/local/src/was/apache-tomcat-9.0.76

 3. Catalina  설정 ( 운영 / 개발 )

     - 운영 ( krmc-pro-server )   포트 : 80
       경로 :  /usr/local/src/was/apache-tomcat-9.0.76/krmc-pro-server/
       소스 :  /usr/local/src/was/apache-tomcat-9.0.76/krmc-pro-server/webapps/ROOT/
       로그 :  /usr/local/src/was/Logs/krmc-pro-server/

     - 개발 ( krmc-dev-server )  포트 : 8081
       경로 :   /usr/local/src/was/apache-tomcat-9.0.76/krmc-dev-server/
       소스 :  /usr/local/src/was/apache-tomcat-9.0.76/krmc-dev-server/webapps/ROOT/
       로그 :  /usr/local/src/was/Logs/krmc-dev-server/

4. Startup / Shutdown

   - 운영 명령어( .sh)
      sudo  /usr/local/src/was/apache-tomcat-9.0.76/bin/krmc-pro-startup.sh
      sudo  /usr/local/src/was/apache-tomcat-9.0.76/bin/krmc-pro-shutdown.sh

   - 개발 명령어 (.sh)
      sudo  /usr/local/src/was/apache-tomcat-9.0.76/bin/krmc-dev-startup.sh
      sudo  /usr/local/src/was/apache-tomcat-9.0.76/bin/krmc-dev-shutdown.sh


기타
파일 소유주 변경
chown -R krmcwas:krmcwas /usr/local/src/was/apache-tomcat-9.0.76/krmc-pro-server/webapps/ROOT

소스 파일 이동
cp /home/krmcwas/src/ROOT.war /usr/local/src/was/apache-tomcat-9.0.76/krmc-pro-server/webapps/

cp /home/krmcwas/jasper/*.*  /usr/local/src/was/apache-tomcat-9.0.76/krmc-pro-server/webapps/ROOT/WEB-INF/classes/jasperReport

cp /home/krmcwas/backup-config/pro/config.properties /usr/local/src/was/apache-tomcat-9.0.76/krmc-pro-server/webapps/ROOT/WEB-INF/classes/property
cp /home/krmcwas/backup-config/pro/logback.xml /usr/local/src/was/apache-tomcat-9.0.76/krmc-pro-server/webapps/ROOT/WEB-INF/classes


카페 24 접속 계정
korearmc / dong4337


톰캣 재 기동
/usr/local/src/was/apache-tomcat-9.0.76/bin/startup.sh
/usr/local/src/was/apache-tomcat-9.0.76/bin/shutdown.sh

