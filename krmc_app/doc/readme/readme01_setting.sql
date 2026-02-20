#### \uC785\uC810\uC0C1\uB2F4 \uC2DC\uC2A4\uD15C \uD504\uB85C\uC81D\uD2B8 \uC14B\uD305 readme
#### \uD504\uB85C\uC81D\uD2B8 \uC14B\uD305 \uC2DC, \uD558\uC704 \uB0B4\uC6A9\uC744 \uCC38\uACE0\uD558\uC2DC\uAE30 \uBC14\uB78D\uB2C8\uB2E4.

########################## \uAC1C\uBC1C \uD658\uACBD ###############################
- openJdk17 / tomcat9.0 (tomcat 8.5 \uC774\uC0C1)
##################################################################

####################### \uD504\uB85C\uC81D\uD2B8 \uAE30\uBCF8\uC14B\uD305 #############################
[LOCAL DB]
1. tomcat context.xml JNDI setting
- doc > context.xml \uCC38\uACE0

[\uC124\uC815\uD30C\uC77C]
1. config.properties
2. tradesign3280.properties
- \uD504\uB85C\uC81D\uD2B8 \uACBD\uB85C/nas \uACBD\uB85C \uD655\uC778\uD558\uC5EC setting
##################################################################

####################### code Template ############################
- \uC8FC\uC11D \uC790\uB3D9 \uC0DD\uC131\uC744 \uC704\uD55C \uD15C\uD50C\uB9BF \uC14B\uD305
- \uC124\uC815\uACBD\uB85C :: window > preferences > Java > Code Style > Code Templates
- \uD574\uB2F9 \uACBD\uB85C\uC5D0\uC11C \uC124\uC815\uB300\uC0C1 \uC120\uD0DD \uD6C4, \uC6B0\uCE21 Edit \uBC84\uD2BC \uD074\uB9AD\uD558\uC5EC \uC2E4\uD589\uB418\uB294 \uD31D\uC5C5\uC5D0\uC11C
  \uC77C\uCE58\uD558\uB294 \uD328\uD134\uC744 \uADF8\uB300\uB85C \uBCF5\uC0AC/\uC218\uC815 \uD6C4 \uBD99\uC5EC\uB123\uAE30

1-1. \uC790\uBC14 \uD30C\uC77C
1) \uC124\uC815\uB300\uC0C1
- Comments > Types
2) \uD328\uD134 
/**
 * @Class : ${file_name}
 * @Description :
 * @author : [\uC791\uC131\uC790\uBA85]
 * <pre>
 *  << \uAC1C\uC815\uC774\uB825(Modification Information) >>
 *
 *          \uC218\uC815\uC77C          \uC218\uC815\uC790           \uC218\uC815\uB0B4\uC6A9
 *  ----------------    ------------    ---------------------------
 *   ${date}            [\uC791\uC131\uC790\uBA85]        	\uCD5C\uCD08 \uC0DD\uC131
 *
 * </pre>
 */
 
1-2. \uC790\uBC14 \uBA54\uC18C\uB4DC
1) \uC124\uC815\uB300\uC0C1
- Comments > Method 
2) \uD328\uD134
/**
 * @Method : ${enclosing_method}
 * @Description : 
 * ${tags}
 */
##################################################################