<map version="0.8.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1284534239656" ID="Freemind_Link_1479577983" MODIFIED="1284535144500" TEXT="CAS 3.4.2.1">
<node CREATED="1284534260296" ID="_" MODIFIED="1284534272859" POSITION="right" TEXT="Server Configuration">
<node CREATED="1284534367468" ID="Freemind_Link_932614833" MODIFIED="1284534371562" TEXT="Required">
<node CREATED="1284534372296" ID="Freemind_Link_105746858" MODIFIED="1284534411718" TEXT="SSL (CAS Login Web and Browser)"/>
<node CREATED="1284534414250" ID="Freemind_Link_518058110" MODIFIED="1284534442093" TEXT="SSL between CAS and service provider (&#x8d85;&#x503c;&#x6848;&#x9700;&#x6c42;)">
<node CREATED="1284534446328" ID="Freemind_Link_1628240563" MODIFIED="1284534467265" TEXT="ImportCert.java to trust CAS Server key"/>
</node>
</node>
<node CREATED="1284535853828" ID="Freemind_Link_233899814" MODIFIED="1284535868500" TEXT="&#x5efa;&#x7acb;CAS Server WAR">
<node CREATED="1284535869531" ID="Freemind_Link_1911424189" MODIFIED="1284536069500" TEXT="&#x4f7f;&#x7528;&#x5b98;&#x65b9;wiki&#x4e0a;&#x7684;&#x65b9;&#x6cd5;"/>
<node CREATED="1284535884843" ID="Freemind_Link_1908766264" MODIFIED="1284535923546" TEXT="&#x5f9e;cas server source&#x81ea;&#x5df1;&#x505a;">
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1284689428093" ID="Freemind_Link_269317286" MODIFIED="1284689439406" TEXT="deployerConfigContext.xml">
<node CREATED="1284689235140" ID="Freemind_Link_1763156405" MODIFIED="1284689237437" TEXT="location">
<node CREATED="1284689204593" ID="Freemind_Link_853281583" MODIFIED="1284689228078" TEXT="cas-server-webapp module"/>
<node CREATED="1284689241593" ID="Freemind_Link_1806093798" MODIFIED="1284689271828" TEXT="/WEB-INF/deployerConfigContext.xml"/>
</node>
<node CREATED="1284690660843" ID="Freemind_Link_809385011" MODIFIED="1284690695093" TEXT="&#x6700;&#x5e38;&#x6539;&#x4e5f;&#x4e00;&#x5b9a;&#x6703;&#x6539;&#x5230;&#x7684;&#x7684;&#x8a2d;&#x5b9a;&#x6a94;"/>
<node CREATED="1284689892937" ID="Freemind_Link_1680011456" MODIFIED="1284689898468" TEXT="Authentication Handler">
<node CREATED="1284689353625" ID="Freemind_Link_1802835427" MODIFIED="1284689999828" TEXT="bean: authenticationManager, property: authenticationHandlers"/>
<node CREATED="1284691192281" ID="Freemind_Link_1082460735" MODIFIED="1284691214687" TEXT="&#x4f7f;&#x7528;LDAP server&#x9a57;&#x8b49;&#x7684;&#x8a2d;&#x5b9a;">
<node CREATED="1284691218875" ID="Freemind_Link_1653243242" MODIFIED="1284691240390" TEXT="&#x8a2d;&#x5b9a;LDAP Context Source">
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1284691242562" ID="Freemind_Link_174499256" MODIFIED="1284691291437" TEXT="authentication handlers&#x52a0;org.jasig.cas.adaptors.ldap.BindLdapAuthenticationHandler">
<icon BUILTIN="full-2"/>
<node CREATED="1284691295875" ID="Freemind_Link_646591562" MODIFIED="1284691352046" TEXT="filter: &#x8a2d;&#x5b9a;&#x548c;&#x4f7f;&#x7528;&#x8005;&#x8f38;&#x5165;&#x5e33;&#x865f;&#x5224;&#x65b7;&#x7684;ldap attribute">
<node CREATED="1285233684087" ID="ID_1743057703" MODIFIED="1285233698654" TEXT="%u&#x6703;&#x7528;&#x8f38;&#x5165;username&#x53d6;&#x4ee3;"/>
<node CREATED="1285233699657" ID="ID_810819122" MODIFIED="1285233801308" TEXT="&#x5982;&#x679c;&#x8981;&#x4f7f;&#x7528;compound ldap filter&#xff0c;&#x6bd4;&#x5982;&#x8aaa;cn=%u&#x548c;employeeNumber=0&#x6642;&#x4f5c;&#x6cd5;&#x5982;&#x4e0b;&#xa;(&amp;amp;(cn=%u)(employeeNumber=0))"/>
</node>
<node CREATED="1284691360484" ID="Freemind_Link_386997455" MODIFIED="1284691377234" TEXT="searchBase: &#x8a2d;&#x5b9a;&#x5728;LDAP tree&#x4e0a;&#x641c;&#x5c0b;&#x7684;&#x57fa;&#x6e96;&#x9ede;"/>
<node CREATED="1284691380125" ID="Freemind_Link_1128941295" MODIFIED="1284691407531" TEXT="contextSource: &#x8a2d;&#x5b9a;&#x5728;&#x7b2c;&#x4e00;&#x6b65;&#x5efa;&#x7acb;LDAP context source"/>
</node>
</node>
</node>
<node CREATED="1284689849656" ID="Freemind_Link_1266264252" MODIFIED="1284689861359" TEXT="LDAP Context Source">
<node CREATED="1284691106312" ID="Freemind_Link_1728527562" MODIFIED="1284691130687" TEXT="&#x8a2d;&#x5b9a;LDAP server&#x9023;&#x7dda;&#x4f7f;&#x7528;&#x7684;context source"/>
<node CREATED="1284691131265" ID="Freemind_Link_453755181" MODIFIED="1284691132765" TEXT="org.springframework.ldap.core.support.LdapContextSource"/>
<node CREATED="1284691429078" ID="Freemind_Link_1486470479" MODIFIED="1284691611703" TEXT="&#x591a;&#x53f0;LDAP server">
<node CREATED="1284691563343" ID="Freemind_Link_515729185" MODIFIED="1284691601968" TEXT="property: urls&#x53ef;&#x4ee5;&#x4f7f;&#x7528;list&#x8a2d;&#x5b9a;&#x591a;&#x500b;LDAP server"/>
</node>
<node CREATED="1284691455140" ID="Freemind_Link_1050253091" MODIFIED="1284691685375" TEXT="&#x5176;&#x4ed6;&#x53c3;&#x6578;">
<node CREATED="1284691630921" ID="Freemind_Link_1188609597" MODIFIED="1284903627953" TEXT="property: baseEnvironmentProperties" VSHIFT="-3">
<node CREATED="1284691653031" ID="Freemind_Link_126331164" MODIFIED="1284691671343" TEXT="key: com.sun.jndi.ldap.connect.timeout"/>
<node CREATED="1284691713390" ID="Freemind_Link_795719983" MODIFIED="1284691717468" TEXT="key: com.sun.jndi.ldap.read.timeout"/>
</node>
</node>
</node>
<node CREATED="1284689880015" ID="Freemind_Link_272135671" MODIFIED="1285569942531" TEXT="Attribute Repository">
<node CREATED="1284689542187" ID="Freemind_Link_650644859" MODIFIED="1284690016203" TEXT="bean: authenticationManager, property: credentialsToPrincipalResolvers"/>
<node CREATED="1284691810296" ID="Freemind_Link_855044427" MODIFIED="1284691890796" TEXT="&#x76ee;&#x7684;&#x662f;&#x901a;&#x904e;&#x4f7f;&#x7528;&#x8005;&#x9a57;&#x8b49;&#x4e4b;&#x5f8c;&#xff0c;&#x5982;&#x679c;&#x9084;&#x8981;&#x53d6;&#x5f97;&#x5176;&#x4ed6;&#x8cc7;&#x8a0a;&#x5c31;&#x8981;&#x900f;&#x904e;&#x9019;&#x500b;"/>
<node CREATED="1284691896000" ID="Freemind_Link_766107672" MODIFIED="1284691991515" TEXT="&#x5728;&#x8d85;&#x503c;&#x6848;&#x8981;&#x900f;&#x904e;&#x9019;&#x500b;&#x53bb;LDAP&#x4e0a;&#x62ff;user uuid&#x56de;&#x4f86;"/>
</node>
</node>
<node CREATED="1284562613930" ID="ID_793479766" MODIFIED="1284562618174" TEXT="misc">
<node CREATED="1284562638529" ID="ID_1078376076" MODIFIED="1284562731591" TEXT="&#x767b;&#x51fa;&#x5f8c;&#x81ea;&#x52d5;&#x8f49;&#x5740;">
<node CREATED="1285243785746" ID="Freemind_Link_1103941741" MODIFIED="1285243792902" TEXT="cas-server-webapp">
<node CREATED="1285243793934" FOLDED="true" ID="Freemind_Link_505710785" MODIFIED="1285243827512" TEXT="WEB-INF/cas-servlet.xml">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>    &lt;bean id=&quot;logoutController&quot; class=&quot;org.jasig.cas.web.LogoutController&quot;&#xa;&#x9;&#x9;p:centralAuthenticationService-ref=&quot;centralAuthenticationService&quot;&#xa;&#x9;&#x9;p:logoutView=&quot;casLogoutView&quot;&#xa;&#x9;&#x9;p:warnCookieGenerator-ref=&quot;warnCookieGenerator&quot;&#xa;&#x9;&#x9;p:ticketGrantingTicketCookieGenerator-ref=&quot;ticketGrantingTicketCookieGenerator&quot; &gt;&#xa;        &lt;property name=&quot;followServiceRedirects&quot; value=&quot;true&quot; /&gt;&#xa;    &lt;/bean&gt;</text>
</hook>
<node CREATED="1286960924734" ID="Freemind_Link_578704260" MODIFIED="1286960931156" TEXT="    &lt;bean id=&quot;logoutController&quot; class=&quot;org.jasig.cas.web.LogoutController&quot;&#xa;&#x9;&#x9;p:centralAuthenticationService-ref=&quot;centralAuthenticationService&quot;&#xa;&#x9;&#x9;p:logoutView=&quot;casLogoutView&quot;&#xa;&#x9;&#x9;p:warnCookieGenerator-ref=&quot;warnCookieGenerator&quot;&#xa;&#x9;&#x9;p:ticketGrantingTicketCookieGenerator-ref=&quot;ticketGrantingTicketCookieGenerator&quot; &gt;&#xa;        &lt;property name=&quot;followServiceRedirects&quot; value=&quot;true&quot; /&gt;&#xa;    &lt;/bean&gt;"/>
</node>
</node>
<node CREATED="1285243838184" FOLDED="true" ID="Freemind_Link_1473174900" MODIFIED="1285243922684" TEXT="service provider">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>Logout Link:&#xa;&lt;a href=&quot;https://localhost:8443/cas/logout?service=&lt;%=request.getRequestURL()%&gt;&quot;&gt;Logout&lt;/a&gt;</text>
</hook>
<node CREATED="1286960966078" ID="Freemind_Link_926210923" MODIFIED="1286960972171" TEXT="Logout Link:&#xa;&lt;a href=&quot;https://localhost:8443/cas/logout?service=&lt;%=request.getRequestURL()%&gt;&quot;&gt;Logout&lt;/a&gt;"/>
</node>
</node>
</node>
</node>
<node CREATED="1284534299921" ID="Freemind_Link_1723305737" MODIFIED="1284535201421" POSITION="right" TEXT="Server Source Structure">
<node CREATED="1284535067968" FOLDED="true" ID="Freemind_Link_757773161" MODIFIED="1285568647078" TEXT="cas-server-core">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>&#x6838;&#x5fc3;&#x90e8;&#x5206;&#x7684;&#x7a0b;&#x5f0f;&#x548c;webapp&#x7684;&#x7a0b;&#x5f0f;&#x3002;CAS&#x898f;&#x5283;&#x88e1;&#x9762;cas-server-webapp&#x53ea;&#x6709;web page&#x3001;&#x975c;&#x614b;&#x8cc7;&#x6e90;&#x548c;&#x8a2d;&#x5b9a;&#x6a94;</text>
</hook>
<node CREATED="1286960985828" ID="Freemind_Link_356093062" MODIFIED="1286960991968" TEXT="&#x6838;&#x5fc3;&#x90e8;&#x5206;&#x7684;&#x7a0b;&#x5f0f;&#x548c;webapp&#x7684;&#x7a0b;&#x5f0f;&#x3002;CAS&#x898f;&#x5283;&#x88e1;&#x9762;cas-server-webapp&#x53ea;&#x6709;web page&#x3001;&#x975c;&#x614b;&#x8cc7;&#x6e90;&#x548c;&#x8a2d;&#x5b9a;&#x6a94;"/>
</node>
<node CREATED="1284535807578" ID="Freemind_Link_1734469282" MODIFIED="1285568647078" TEXT="cas-server-webapp">
<arrowlink DESTINATION="Freemind_Link_757773161" ENDARROW="Default" ENDINCLINATION="25;0;" ID="Freemind_Arrow_Link_1539415389" STARTARROW="None" STARTINCLINATION="25;0;"/>
</node>
<node CREATED="1284535813125" FOLDED="true" ID="Freemind_Link_1054071530" MODIFIED="1285568678578" TEXT="cas-server-support-ldap">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>LDAP authentication handler&#x7684;&#x7a0b;&#x5f0f;</text>
</hook>
<node CREATED="1286960999031" ID="Freemind_Link_874140372" MODIFIED="1286961001031" TEXT="LDAP authentication handler&#x7684;&#x7a0b;&#x5f0f;"/>
</node>
</node>
<node CREATED="1284534329609" ID="Freemind_Link_1772981018" MODIFIED="1284534345359" POSITION="right" TEXT="&#x60e1;&#x641e;CAS Server">
<node CREATED="1284534613093" ID="Freemind_Link_1675211131" MODIFIED="1284534619281" TEXT="Captcha">
<node CREATED="1284534691156" ID="Freemind_Link_1024629259" MODIFIED="1284534739843" TEXT="captcha&#x5b98;&#x65b9;&#x5728;CAS4&#x624d;&#x652f;&#x63f4;&#xff0c;&#x9019;&#x500b;&#x7248;&#x672c;&#x4e0d;&#x652f;&#x63f4;&#xff0c;&#x6240;&#x4ee5;&#x8981;&#x81ea;&#x5df1;&#x4f86;"/>
<node CREATED="1285568690109" ID="Freemind_Link_714205606" MODIFIED="1285568707812" TEXT="servlet&#x5beb;&#x5728;cas-server-core&#x88e1;&#x9762;"/>
<node CREATED="1285569178484" ID="Freemind_Link_1568653867" MODIFIED="1285569194296" TEXT="new pom.xml dependency">
<node CREATED="1285569306546" ID="Freemind_Link_1678944129" MODIFIED="1285569317828" TEXT="jcaptcha">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>        &lt;!-- jCaptcha --&gt;&#xa;        &lt;dependency&gt;&#xa;            &lt;groupId&gt;com.octo.captcha&lt;/groupId&gt;&#xa;            &lt;artifactId&gt;jcaptcha&lt;/artifactId&gt;&#xa;            &lt;version&gt;1.0&lt;/version&gt;&#xa;        &lt;/dependency&gt;</text>
</hook>
<node CREATED="1285569380265" ID="Freemind_Link_973887664" MODIFIED="1285571860468" TEXT="&#x63a1;&#x7528;jcaptcha&#x7db2;&#x7ad9;&#x4e0a;&#x4e94;&#x5206;&#x9418;&#x6b61;&#x6a02;&#x7248;&#x6587;&#x4ef6;&#x7684;&#x65b9;&#x6cd5;"/>
<node CREATED="1286961020500" ID="Freemind_Link_675188913" MODIFIED="1286961025203" TEXT="        &lt;!-- jCaptcha --&gt;&#xa;        &lt;dependency&gt;&#xa;            &lt;groupId&gt;com.octo.captcha&lt;/groupId&gt;&#xa;            &lt;artifactId&gt;jcaptcha&lt;/artifactId&gt;&#xa;            &lt;version&gt;1.0&lt;/version&gt;&#xa;        &lt;/dependency&gt;"/>
</node>
</node>
<node CREATED="1285569607234" ID="Freemind_Link_559655740" MODIFIED="1285569611468" TEXT="cas-server-core">
<node CREATED="1285569326687" ID="Freemind_Link_245253545" MODIFIED="1285569360125" TEXT="org.jasig.cas.captcha">
<node CREATED="1285569361171" FOLDED="true" ID="Freemind_Link_1316324564" MODIFIED="1285569568531" TEXT="CaptchaServiceSingleton.java">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>package org.jasig.cas.captcha;&#xa;&#xa;import com.octo.captcha.service.image.ImageCaptchaService;&#xa;import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;&#xa;&#xa;public class CaptchaServiceSingleton {&#xa;    private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService();&#xa;&#xa;    public static ImageCaptchaService getInstance() {&#xa;        return instance;&#xa;    }&#xa;}&#xa;&#xa;</text>
</hook>
<node CREATED="1286961034703" ID="Freemind_Link_937485818" MODIFIED="1286961039828" TEXT="package org.jasig.cas.captcha;&#xa;&#xa;import com.octo.captcha.service.image.ImageCaptchaService;&#xa;import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;&#xa;&#xa;public class CaptchaServiceSingleton {&#xa;    private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService();&#xa;&#xa;    public static ImageCaptchaService getInstance() {&#xa;        return instance;&#xa;    }&#xa;}&#xa;&#xa;"/>
</node>
<node CREATED="1285569370218" FOLDED="true" ID="Freemind_Link_1117449107" MODIFIED="1285569580953" TEXT="ImageCaptchaServlet.java">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>package org.jasig.cas.captcha;&#xa;&#xa;import com.octo.captcha.service.CaptchaServiceException;&#xa;import com.sun.image.codec.jpeg.JPEGCodec;&#xa;import com.sun.image.codec.jpeg.JPEGImageEncoder;&#xa;&#xa;import javax.servlet.ServletConfig;&#xa;import javax.servlet.ServletException;&#xa;import javax.servlet.ServletOutputStream;&#xa;import javax.servlet.http.HttpServlet;&#xa;import javax.servlet.http.HttpServletRequest;&#xa;import javax.servlet.http.HttpServletResponse;&#xa;import java.awt.image.BufferedImage;&#xa;import java.io.ByteArrayOutputStream;&#xa;import java.io.IOException;&#xa;&#xa;&#xa;public class ImageCaptchaServlet extends HttpServlet {&#xa;&#xa;&#xa;    public void init(ServletConfig servletConfig) throws ServletException {&#xa;        super.init(servletConfig);&#xa;    }&#xa;&#xa;&#xa;    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {&#xa;&#xa;       byte[] captchaChallengeAsJpeg = null;&#xa;       // the output stream to render the captcha image as jpeg into&#xa;        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();&#xa;        try {&#xa;        // get the session id that will identify the generated captcha.&#xa;        //the same id must be used to validate the response, the session id is a good candidate!&#xa;        String captchaId = httpServletRequest.getSession().getId();&#xa;        // call the ImageCaptchaService getChallenge method&#xa;            BufferedImage challenge =&#xa;                    CaptchaServiceSingleton.getInstance().getImageChallengeForID(captchaId,&#xa;                            httpServletRequest.getLocale());&#xa;&#xa;            // a jpeg encoder&#xa;            JPEGImageEncoder jpegEncoder =&#xa;                    JPEGCodec.createJPEGEncoder(jpegOutputStream);&#xa;            jpegEncoder.encode(challenge);&#xa;        } catch (IllegalArgumentException e) {&#xa;            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);&#xa;            return;&#xa;        } catch (CaptchaServiceException e) {&#xa;            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);&#xa;            return;&#xa;        }&#xa;&#xa;        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();&#xa;&#xa;        // flush it in the response&#xa;        httpServletResponse.setHeader(&quot;Cache-Control&quot;, &quot;no-store&quot;);&#xa;        httpServletResponse.setHeader(&quot;Pragma&quot;, &quot;no-cache&quot;);&#xa;        httpServletResponse.setDateHeader(&quot;Expires&quot;, 0);&#xa;        httpServletResponse.setContentType(&quot;image/jpeg&quot;);&#xa;        ServletOutputStream responseOutputStream =&#xa;                httpServletResponse.getOutputStream();&#xa;        responseOutputStream.write(captchaChallengeAsJpeg);&#xa;        responseOutputStream.flush();&#xa;        responseOutputStream.close();&#xa;    }&#xa;}&#xa;&#xa;</text>
</hook>
<node CREATED="1286961054421" ID="Freemind_Link_1054128363" MODIFIED="1286961059765" TEXT="package org.jasig.cas.captcha;&#xa;&#xa;import com.octo.captcha.service.CaptchaServiceException;&#xa;import com.sun.image.codec.jpeg.JPEGCodec;&#xa;import com.sun.image.codec.jpeg.JPEGImageEncoder;&#xa;&#xa;import javax.servlet.ServletConfig;&#xa;import javax.servlet.ServletException;&#xa;import javax.servlet.ServletOutputStream;&#xa;import javax.servlet.http.HttpServlet;&#xa;import javax.servlet.http.HttpServletRequest;&#xa;import javax.servlet.http.HttpServletResponse;&#xa;import java.awt.image.BufferedImage;&#xa;import java.io.ByteArrayOutputStream;&#xa;import java.io.IOException;&#xa;&#xa;&#xa;public class ImageCaptchaServlet extends HttpServlet {&#xa;&#xa;&#xa;    public void init(ServletConfig servletConfig) throws ServletException {&#xa;        super.init(servletConfig);&#xa;    }&#xa;&#xa;&#xa;    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {&#xa;&#xa;       byte[] captchaChallengeAsJpeg = null;&#xa;       // the output stream to render the captcha image as jpeg into&#xa;        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();&#xa;        try {&#xa;        // get the session id that will identify the generated captcha.&#xa;        //the same id must be used to validate the response, the session id is a good candidate!&#xa;        String captchaId = httpServletRequest.getSession().getId();&#xa;        // call the ImageCaptchaService getChallenge method&#xa;            BufferedImage challenge =&#xa;                    CaptchaServiceSingleton.getInstance().getImageChallengeForID(captchaId,&#xa;                            httpServletRequest.getLocale());&#xa;&#xa;            // a jpeg encoder&#xa;            JPEGImageEncoder jpegEncoder =&#xa;                    JPEGCodec.createJPEGEncoder(jpegOutputStream);&#xa;            jpegEncoder.encode(challenge);&#xa;        } catch (IllegalArgumentException e) {&#xa;            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);&#xa;            return;&#xa;        } catch (CaptchaServiceException e) {&#xa;            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);&#xa;            return;&#xa;        }&#xa;&#xa;        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();&#xa;&#xa;        // flush it in the response&#xa;        httpServletResponse.setHeader(&quot;Cache-Control&quot;, &quot;no-store&quot;);&#xa;        httpServletResponse.setHeader(&quot;Pragma&quot;, &quot;no-cache&quot;);&#xa;        httpServletResponse.setDateHeader(&quot;Expires&quot;, 0);&#xa;        httpServletResponse.setContentType(&quot;image/jpeg&quot;);&#xa;        ServletOutputStream responseOutputStream =&#xa;                httpServletResponse.getOutputStream();&#xa;        responseOutputStream.write(captchaChallengeAsJpeg);&#xa;        responseOutputStream.flush();&#xa;        responseOutputStream.close();&#xa;    }&#xa;}&#xa;&#xa;"/>
</node>
</node>
</node>
<node CREATED="1285569591890" ID="Freemind_Link_846760148" MODIFIED="1285569604093" TEXT="cas-server-webapp">
<node CREATED="1285569616500" ID="Freemind_Link_475831097" MODIFIED="1285569726000" TEXT="web.xml">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>        &lt;!-- jCaptcha Servlet --&gt;&#xa;        &lt;servlet&gt;&#xa;                &lt;servlet-name&gt;jcaptcha&lt;/servlet-name&gt;&#xa;                &lt;servlet-class&gt;org.jasig.cas.captcha.ImageCaptchaServlet&lt;/servlet-class&gt;&#xa;                &lt;load-on-startup&gt;0&lt;/load-on-startup&gt;&#xa;        &lt;/servlet&gt;&#xa;&#xa;        &lt;servlet-mapping&gt;&#xa;                &lt;servlet-name&gt;jcaptcha&lt;/servlet-name&gt;&#xa;                &lt;url-pattern&gt;/jcaptcha&lt;/url-pattern&gt;&#xa;        &lt;/servlet-mapping&gt;</text>
</hook>
<node CREATED="1286961069578" ID="Freemind_Link_1656233770" MODIFIED="1286961074281" TEXT="        &lt;!-- jCaptcha Servlet --&gt;&#xa;        &lt;servlet&gt;&#xa;                &lt;servlet-name&gt;jcaptcha&lt;/servlet-name&gt;&#xa;                &lt;servlet-class&gt;org.jasig.cas.captcha.ImageCaptchaServlet&lt;/servlet-class&gt;&#xa;                &lt;load-on-startup&gt;0&lt;/load-on-startup&gt;&#xa;        &lt;/servlet&gt;&#xa;&#xa;        &lt;servlet-mapping&gt;&#xa;                &lt;servlet-name&gt;jcaptcha&lt;/servlet-name&gt;&#xa;                &lt;url-pattern&gt;/jcaptcha&lt;/url-pattern&gt;&#xa;        &lt;/servlet-mapping&gt;"/>
</node>
</node>
</node>
<node CREATED="1284534621265" ID="Freemind_Link_213131198" MODIFIED="1285569942546" TEXT="return other attribute">
<arrowlink DESTINATION="Freemind_Link_272135671" ENDARROW="Default" ENDINCLINATION="406;0;" ID="Freemind_Arrow_Link_681431144" STARTARROW="None" STARTINCLINATION="406;0;"/>
<node CREATED="1285568712875" ID="Freemind_Link_835481779" MODIFIED="1285568725828" TEXT="&#x4fee;&#x6539;cas-server-core&#x88e1;&#x7684;&#x7a0b;&#x5f0f;"/>
<node CREATED="1285569879125" ID="Freemind_Link_1759792433" MODIFIED="1285569880125" TEXT="http://210.68.27.210/confluence/display/aptgportal/Jasig+CAS+SSO"/>
<node CREATED="1285569881171" ID="Freemind_Link_1007898921" MODIFIED="1285570222515" TEXT="&#x9700;&#x8981;&#x8a2d;&#x5b9a;attribute&#xff0c;&#x8b93;CAS ticket validation &#x4e4b;&#x5f8c;&#x53bb;&#x7522;&#x751f;&#x81ea;&#x5df1;&#x8981;&#x7684;principal&#x3002;&#xa;&#x7136;&#x5f8c;&#x5728;CentralAuthenticationServiceImpl&#x88e1;&#x9762;&#x5728;ticket validation&#x5f8c;&#x7684;&#x6d41;&#x7a0b;&#x5f8c;&#xff0c;&#xa;&#x5c07;principal&#x88e1;&#x7684;uid&#x53d6;&#x51fa;&#x4f86;&#xff0c;&#x628a;&#x539f;&#x672c;&#x7a0b;&#x5f0f;&#x78bc;&#x6703;&#x6703;&#x50b3;&#x7684;username&#x63db;&#x6210;uid&#x3002;&#xa;&#x4e4b;&#x5f8c;service provider&#x900f;&#x904e;request.getRemoteUser()&#x53d6;&#x5230;&#x7684;&#x5c31;&#x662f;uid&#x800c;&#x975e;username..."/>
</node>
</node>
<node CREATED="1284534274968" ID="Freemind_Link_1990048539" MODIFIED="1284534281578" POSITION="left" TEXT="Client Configuration">
<node CREATED="1284534283906" ID="Freemind_Link_1575623397" MODIFIED="1284536316875" TEXT="CAS Client for Java 3.1">
<node CREATED="1284534561921" ID="Freemind_Link_593108889" MODIFIED="1284690626031" TEXT="web.xml way">
<icon BUILTIN="button_ok"/>
<node CREATED="1284534484984" FOLDED="true" ID="Freemind_Link_827638700" MODIFIED="1285243178652" TEXT="Single-Sign On">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>    &lt;!-- Authentication Filter --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS Authentication Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.authentication.AuthenticationFilter&lt;/filter-class&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;casServerLoginUrl&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443/cas/login&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;service&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443/SimpleWeb2/index.jsp&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;serverName&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;    &lt;/filter&gt;&#xa;    &#xa;    &lt;!-- ticket Validation Filter --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS Validation Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.validation.Saml11TicketValidationFilter&lt;/filter-class&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;casServerUrlPrefix&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443/cas&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;serverName&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;redirectAfterValidation&lt;/param-name&gt;&#xa;            &lt;param-value&gt;true&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;    &lt;/filter&gt;&#xa;&#xa;    &lt;!-- HttpServletRequrst Wrapper Filter --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS HttpServletRequest Wrapper Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.util.HttpServletRequestWrapperFilter&lt;/filter-class&gt;&#xa;    &lt;/filter&gt;&#xa;&#xa;    &lt;!-- --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS Assertion Thread Local Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.util.AssertionThreadLocalFilter&lt;/filter-class&gt;&#xa;    &lt;/filter&gt;&#xa;&#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS Authentication Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;&#xa;    &#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS Validation Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;&#xa;    &#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS HttpServletRequest Wrapper Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;&#xa;    &#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS Assertion Thread Local Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;</text>
</hook>
<node CREATED="1286960833843" ID="Freemind_Link_641961172" MODIFIED="1286960854359" TEXT="    &lt;!-- Authentication Filter --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS Authentication Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.authentication.AuthenticationFilter&lt;/filter-class&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;casServerLoginUrl&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443/cas/login&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;service&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443/SimpleWeb2/index.jsp&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;serverName&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;    &lt;/filter&gt;&#xa;    &#xa;    &lt;!-- ticket Validation Filter --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS Validation Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.validation.Saml11TicketValidationFilter&lt;/filter-class&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;casServerUrlPrefix&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443/cas&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;serverName&lt;/param-name&gt;&#xa;            &lt;param-value&gt;https://localhost:8443&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;        &lt;init-param&gt;&#xa;            &lt;param-name&gt;redirectAfterValidation&lt;/param-name&gt;&#xa;            &lt;param-value&gt;true&lt;/param-value&gt;&#xa;        &lt;/init-param&gt;&#xa;    &lt;/filter&gt;&#xa;&#xa;    &lt;!-- HttpServletRequrst Wrapper Filter --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS HttpServletRequest Wrapper Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.util.HttpServletRequestWrapperFilter&lt;/filter-class&gt;&#xa;    &lt;/filter&gt;&#xa;&#xa;    &lt;!-- --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS Assertion Thread Local Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.util.AssertionThreadLocalFilter&lt;/filter-class&gt;&#xa;    &lt;/filter&gt;&#xa;&#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS Authentication Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;&#xa;    &#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS Validation Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;&#xa;    &#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS HttpServletRequest Wrapper Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;&#xa;    &#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS Assertion Thread Local Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;"/>
</node>
<node CREATED="1284534492484" FOLDED="true" ID="Freemind_Link_1554239036" MODIFIED="1285243196355" TEXT="Single-Sign Out">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>Filter&#x8a2d;&#x5b9a;:&#xa;    &lt;!-- Single-Sign Out  --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS SingleSignOut Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.session.SingleSignOutFilter&lt;/filter-class&gt;&#xa;    &lt;/filter&gt;&#xa;&#xa;    &lt;filter-mapping&gt;&#xa;        &lt;filter-name&gt;CAS SingleSignOut Filter&lt;/filter-name&gt;&#xa;        &lt;url-pattern&gt;/*&lt;/url-pattern&gt;&#xa;    &lt;/filter-mapping&gt;&#xa;&#xa;Listener&#x8a2d;&#x5b9a;:&#xa;    &lt;listener&gt;&#xa;        &lt;listener-class&gt;org.jasig.cas.client.session.SingleSignOutHttpSessionListener&lt;/listener-class&gt;&#xa;    &lt;/listener&gt;</text>
</hook>
<node CREATED="1286960875703" ID="Freemind_Link_1110146746" MODIFIED="1286960887328" TEXT="Filter&#x8a2d;&#x5b9a;:&#xa;    &lt;!-- Single-Sign Out  --&gt;&#xa;    &lt;filter&gt;&#xa;        &lt;filter-name&gt;CAS SingleSignOut Filter&lt;/filter-name&gt;&#xa;        &lt;filter-class&gt;org.jasig.cas.client.session.SingleSignOutFilter&lt;/filter-class&gt;&#xa;    &lt;/filter&gt;"/>
</node>
</node>
<node CREATED="1284534501640" FOLDED="true" ID="Freemind_Link_763016555" MODIFIED="1285243211918" TEXT="get login username from request">
<hook NAME="accessories/plugins/NodeNote.properties">
<text>&#x50b3;&#x56de;&#x7684;&#x767b;&#x5165;&#xa;&lt;%=request.getRemoteUser()%&gt;</text>
</hook>
<node CREATED="1286960893765" ID="Freemind_Link_1856172154" MODIFIED="1286960903125" TEXT="&#x50b3;&#x56de;&#x7684;&#x767b;&#x5165;&#xa;&lt;%=request.getRemoteUser()%&gt;"/>
</node>
</node>
<node CREATED="1284534545234" ID="Freemind_Link_1817735759" MODIFIED="1284534548171" TEXT="phpClient">
<node CREATED="1284691547921" ID="Freemind_Link_1404033784" MODIFIED="1284691554031" TEXT="&#x6c92;&#x73a9;&#x904e;"/>
</node>
</node>
<node CREATED="1284536154906" ID="Freemind_Link_585134161" MODIFIED="1284690262031" POSITION="left" TEXT="references">
<node CREATED="1284536320062" ID="Freemind_Link_786205103" MODIFIED="1284536340109" TEXT="https://wiki.jasig.org/dashboard.action">
<node CREATED="1284690944140" ID="Freemind_Link_1616249982" MODIFIED="1284690949656" TEXT="&#x5b98;&#x65b9;Wiki"/>
</node>
<node CREATED="1284690264328" ID="Freemind_Link_1601810274" MODIFIED="1284690291812" TEXT="http://home.so-net.net.tw/tzuyichao">
<node CREATED="1284690292984" ID="Freemind_Link_225992855" MODIFIED="1284690299953" TEXT="&#x672c;&#x4eba;&#x7b46;&#x8a18;&#x7db2;&#x7ad9;"/>
<node CREATED="1284690300359" ID="Freemind_Link_331259064" MODIFIED="1284690316671" TEXT="&#x9060;&#x50b3;&#x5c01;&#x9396;so-net&#x9019;&#x500b;&#x7ad9;"/>
</node>
<node CREATED="1284690590109" ID="Freemind_Link_1607749793" MODIFIED="1284690591468" TEXT="http://210.68.27.210/confluence/dashboard.action">
<node CREATED="1284690592500" ID="Freemind_Link_710188387" MODIFIED="1284690607375" TEXT="&#x80e4;&#x8d85;&#x7684;confluence"/>
</node>
</node>
</node>
</map>
