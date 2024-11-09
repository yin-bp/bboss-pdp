package com.frameworkset.platform.admin.action;

import com.frameworkset.platform.admin.entity.AuthResponse;
import com.frameworkset.util.StringUtil;
import org.frameworkset.platform.action.SysInfo;
import org.frameworkset.platform.config.ConfigManager;
import org.frameworkset.platform.framework.Framework;
import org.frameworkset.platform.framework.Item;
import org.frameworkset.platform.framework.MenuHelper;
import org.frameworkset.platform.framework.MenuItem;
import org.frameworkset.platform.framework.Module;
import org.frameworkset.platform.framework.SubSystem;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.platform.security.PermissionModule;
import org.frameworkset.platform.security.authorization.AccessException;
import org.frameworkset.platform.security.authorization.AuthUser;
import org.frameworkset.platform.util.AdminUtil;
import org.frameworkset.util.FileCopyUtils;
import org.frameworkset.util.I18NUtil;
import org.frameworkset.util.annotations.AssertDToken;
import org.frameworkset.util.annotations.AssertTicket;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.interceptor.AuthenticateFilter;
import org.frameworkset.web.servlet.ModelMap;
import org.frameworkset.web.servlet.support.RequestContextUtils;
import org.frameworkset.web.token.TokenStore;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class SSOControler {

	private static org.slf4j.Logger log = LoggerFactory.getLogger(SSOControler.class);
	private boolean enableuseraccountsso = false;
	//    public @ResponseBody String recive(ModelMap model, HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		log.info("消息接收接口");
//
//		String app = request.getParameter("app");
//		log.info("微信系统app名称=" + app);
//
//		// 微信加密签名
//		String sVerifyMsgSig = request.getParameter("msg_signature");
//		// System.out.println("微信加密签名msg_signature=" + sVerifyMsgSig);
//		log.info("微信加密签名msg_signature=" + sVerifyMsgSig);
//
//		// 时间戳
//		String sVerifyTimeStamp = request.getParameter("timestamp");
//		// System.out.println("时间戳sVerifyTimeStamp=" + sVerifyTimeStamp);
//		log.info("时间戳sVerifyTimeStamp=" + sVerifyTimeStamp);
//
//		// 随机数
//		String sVerifyNonce = request.getParameter("nonce");
//		// System.out.println("随机数sVerifyNonce=" + sVerifyNonce);
//		log.info("随机数sVerifyNonce=" + sVerifyNonce);
//
//		// 随机字符串
//		String sVerifyEchoStr = request.getParameter("echostr");
//		// System.out.println("随机字符串sVerifyEchoStr=" + sVerifyEchoStr);
//		log.info("随机字符串sVerifyEchoStr=" + sVerifyEchoStr);
//
//		String sEchoStr = ""; // 需要返回的明文
//		if (StringUtils.isNotEmpty(sVerifyEchoStr)) {
//
//			WXBizMsgCrypt wxcpt;
//			try {
//				String weixin_token = WXHelper.getEnterpriseToken(app);
//				String weixin_aeskey = WXHelper.getEnterpriseAeskey(app);
//				String weixin_corpid = WXHelper.getEnterpriseCorpid(app);
//
//				wxcpt = new WXBizMsgCrypt(weixin_token, weixin_aeskey, weixin_corpid);
//				sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
//				System.out.println("需要返回的明文sEchoStr=" + sEchoStr);
//
//				// 验证URL成功，将sEchoStr返回
//			} catch (AesException e1) {
//				e1.printStackTrace();
//			}
//		}
//
//		return sEchoStr;
//
//	}
	//设置字母的大小,大小
	private Font mFont = new Font("Times New Roman", Font.PLAIN, 17);

	//	/**
//	 * 配置微信菜单 //redirect_uri=http://domain/contextpath/sso/wxsso.page?loginMenu=
//	 * appbommanager
//	 *
//	 * redirect_uri=http://domain/contextpath/sso/wxsso.page?successRedirect=<%=
//	 * URLEncoder.encode("/appbom/aaa.page")
//	 *
//	 * 微信跳转过来的地址
//	 * redirect_uri=http://domain/contextpath/sso/wxsso.page?loginMenu=
//	 * appbommanager&app=zqztpy&code=CODE&state=STATE
//	 *
//	 * redirect_uri=http://domain/contextpath/sso/wxsso.page?successRedirect=/
//	 * appbom/aaa.page&code=CODE&state=STATE
//	 *
//	 * @param request
//	 * @param response
//	 */
//	public void wxsso(HttpServletRequest request, HttpServletResponse response) {
//
//		String code = request.getParameter("code");
//		log.info("微信code=" + code);
//
//		String app = request.getParameter("state");
//		log.info("微信state=" + app);
//
//		String successRedirect = request.getParameter("successRedirect");
//
//		// 解析successRedirect参数中含有多个参数
//		if (StringUtil.isNotEmpty(app)) {
//			int num = app.indexOf(";");
//			if (num > -1) {
//				successRedirect = successRedirect + app.substring(num).replace(";", "?").replace(",", "&");
//				app = app.substring(0, num);
//			}
//		}
//		log.info("微信successRedirect=" + successRedirect);
//
//		String corpid = WXHelper.getEnterpriseCorpid(app), corpsecret = WXHelper.getEnterpriseCorpsecret(app);
//		log.info("微信corpid=" + corpid);
//		log.info("微信corpsecret=" + corpsecret);
//
//		String userName = null;
//		String loginMenu = request.getParameter("loginMenu");
//		String contextpath = request.getContextPath();
//		String menuid = null;
//		if (loginMenu != null) {
//
//			menuid = loginMenu;
//
//		}
//		HttpSession session = request.getSession();
//
//		try {
//			AccessControl control = AccessControl.getInstance();
//			control.checkAccess(request, response, false);
//			String user = control.getUserAccount();
//			if (!control.isGuest()) {
//				if (!WXHelper.uselocalsession()) {
//
//					WxAccessToken accesstoken = WXHelper.getEnterpriseWXSecurityService().getWxAccessToken(corpid,
//							corpsecret);
//					WxUserToken userToken = WXHelper.getEnterpriseWXSecurityService()
//							.getWxUserToken(accesstoken.getAccess_token(), code);
//
//					userName = userToken.getUserId();
//
//				} else {
//					userName = user;
//				}
//			} else {
//				user = null;
//				WxAccessToken accesstoken = WXHelper.getEnterpriseWXSecurityService().getWxAccessToken(corpid,
//						corpsecret);
//				WxUserToken userToken = WXHelper.getEnterpriseWXSecurityService()
//						.getWxUserToken(accesstoken.getAccess_token(), code);
//
//				userName = userToken.getUserId();
//			}
//
//			log.info("微信userName=" + userName);
//
//			boolean issameuser = false;
//
//			if (user != null && !user.equals(""))
//				issameuser = userName.equals(user);
//
//			if (user == null || "".equals(user) || !issameuser) {
//
//				if (!issameuser) {
//					control.resetSession(session);
//				}
//
//				try {
//					// 1-域账号登录 2-工号登录
//					String password = null;
//
//					password = SSOUserMapping.getUserPassword(userName);
//					if (password == null)
//						throw new AccessException("用户" + userName + "不存在。");
//
//					control = AccessControl.getInstance();
//					request.setAttribute("fromsso", "true");
//					// System.out.println("-----------userName="+userName+",password="+password);
//					control.login(request, response, userName, password);
//					if (StringUtil.isEmpty(successRedirect)) {
//						Framework framework = Framework.getInstance(control.getCurrentSystemID());
//						MenuItem menuitem = framework.getMenuByID(menuid);
//						if (menuitem instanceof Item) {
//
//							Item menu = (Item) menuitem;
//							successRedirect = MenuHelper.getRealUrl(contextpath,
//									Framework.getWorkspaceContent(menu, control), MenuHelper.sanymenupath_menuid,
//									menu.getId());
//						} else {
//
//							Module menu = (Module) menuitem;
//							StringBuilder framepath = new StringBuilder();
//							framepath.append(contextpath).append("/sanydesktop/singleframe.page?")
//									.append(MenuHelper.sanymenupath).append("=").append(menu.getPath());
//							successRedirect = framepath.toString();
//						}
//						AccessControl.recordIndexPage(request, successRedirect);
//					} else {
//						successRedirect = URLDecoder.decode(successRedirect);
//					}
//					response.sendRedirect(successRedirect);
//					return;
//				} catch (Exception e) {
//					log.info("", e);
//					String msg = e.getMessage();
//					if (msg == null)
//						msg = "";
//					response.sendRedirect(new StringBuilder().append(contextpath)
//							.append("/webseal/websealloginfail.jsp?userName=").append(userName).append("&errormsg=")
//							.append(java.net.URLEncoder.encode(java.net.URLEncoder.encode(msg, "UTF-8"), "UTF-8"))
//							.toString());
//					return;
//				}
//
//			} else {
//				control.resetUserAttributes();
//				if (StringUtil.isEmpty(successRedirect)) {
//					Framework framework = Framework.getInstance(control.getCurrentSystemID());
//					MenuItem menuitem = framework.getMenuByID(menuid);
//					if (menuitem instanceof Item) {
//
//						Item menu = (Item) menuitem;
//						successRedirect = MenuHelper.getRealUrl(contextpath,
//								Framework.getWorkspaceContent(menu, control), MenuHelper.sanymenupath_menuid,
//								menu.getId());
//					} else {
//
//						Module menu = (Module) menuitem;
//						StringBuilder framepath = new StringBuilder();
//						framepath.append(contextpath).append("/sanydesktop/singleframe.page?")
//								.append(MenuHelper.sanymenupath).append("=").append(menu.getPath());
//						successRedirect = framepath.toString();
//					}
//					AccessControl.recordIndexPage(request, successRedirect);
//				} else {
//					successRedirect = URLDecoder.decode(successRedirect);
//				}
//				response.sendRedirect(successRedirect);
//				return;
//			}
//
//		} catch (Throwable ex) {
//			log.info("", ex);
//			String errorMessage = ex.getMessage();
//			if (errorMessage == null)
//				errorMessage = "";
//
//			try {
//				FileCopyUtils.copy(
//						new StringBuilder().append(errorMessage).append(",").append(userName)
//								.append("登陆失败，请确保输入的用户名和口令是否正确！").toString(),
//						new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
//			} catch (IOException e) {
//				log.info("", e);
//			}
//
//		}
//
//	}
	public String sso(ModelMap model) {
		model.addAttribute("enableuseraccountsso", new Boolean(enableuseraccountsso));
		if (enableuseraccountsso)
			return "path:sso";
		else
			return "path:ssofailed";
	}

	public void generateVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到会话信息
		HttpSession session = request.getSession();

		createVerifyCode(request, response, session);
	}

	/**
	 * 生成验证码
	 *
	 * @param
	 * @return
	 * @create 2016/10/31
	 * @version V1.0.0
	 */
	private void createVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//表明生成的响应是图片
		response.setContentType("image/jpeg");

		int width = 100, height = 18;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(mFont);

		g.setColor(getRandColor(160, 200));

		//画随机线
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		//从另一方向画随机线
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}

		//生成随机数,并将随机数字转换为字母
		String sRand = "";
		for (int i = 0; i < 6; i++) {
			int itmp = random.nextInt(26) + 65;
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(String.valueOf(ctmp), 15 * i + 10, 16);
		}

		session.setAttribute("verifyCode", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	public String cookieLocale(String language, HttpServletResponse response, HttpServletRequest request) {


//		StringUtil.addCookieValue(request, response, "cookie.localkey", language, 3600 * 24);
//		
//		
//		if(language.equals("en_US"))
//		{
//			request.getSession().setAttribute("session.localkey",java.util.Locale.US);
//		}
//		else
//		{
//			request.getSession().setAttribute("session.localkey",java.util.Locale.CHINA);
//		}

		try {
			I18NUtil.setLocale(request, response, language);
		} catch (Exception e) {
			log.error("", e);
		}
//			loginPathCookie.setPath(request.getContextPath());

		return AccessControl.redirectpathloginPage;
	}

	private String getSuccessRedirect(String loginStyle, String subsystem) {
		StringBuilder ret = new StringBuilder();
		if (StringUtil.isEmpty(subsystem)) {
			subsystem = AccessControl.getDefaultSUBSystemID();
		}
		if (StringUtil.isEmpty(subsystem)) {

			if (loginStyle == null || loginStyle.equals("5") || loginStyle.equals("6")) {
				ret.append("sanydesktop/indexcommon.page");
			} else if (loginStyle.equals("1")) {
				ret.append("index.jsp?subsystem_id=").append(subsystem);
			} else if (loginStyle.equals("3")) {
				ret.append("sanydesktop/index.page");
			} else if (loginStyle.equals("2")) {
				ret.append("desktop/desktop1.page");
			} else if (loginStyle.equals("4")) {
				ret.append("sanydesktop/webindex.page");
			} else {
				ret.append("sanydesktop/indexcommon.page");
			}
		} else {
			if (subsystem.equals("cms")) {
				ret.append("index.jsp?subsystem_id=").append(subsystem);
				return ret.toString();
			}
			SubSystem sys = Framework.getSubSystem(subsystem);
			if (sys != null && !StringUtil.isEmpty(sys.getSuccessRedirect()))
				ret.append(sys.getSuccessRedirect());
			else {
				if (loginStyle == null || loginStyle.equals("5") || loginStyle.equals("6")) {
					ret.append("sanydesktop/indexcommon.page");
				} else if (loginStyle.equals("1")) {
					ret.append("index.jsp?subsystem_id=").append(subsystem);
				} else if (loginStyle.equals("3")) {
					ret.append("sanydesktop/index.page");
				} else if (loginStyle.equals("2")) {
					ret.append("desktop/desktop1.page");
				} else if (loginStyle.equals("4")) {
					ret.append("sanydesktop/webindex.page");
				} else {
					ret.append("sanydesktop/indexcommon.page");
				}
			}
		}
		return ret.toString();
	}

	public @ResponseBody(datatype = "json")
	AuthResponse applogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception,
			Exception {
		HttpSession session = request.getSession(false);
		AuthResponse authResponse = new AuthResponse();

		String u = "", p = "", ck = "";


		String language = request.getParameter("language");
		authResponse.setLanguage(language);
		PermissionModule permissionModule = ConfigManager.getInstance().getPermissionModule();

		String errorMessage = null;

		String userName = request.getParameter("userName");

		/*
		 * if(language.equals("zh_CN")){
		 * request.getSession().setAttribute("languageKey",
		 * java.util.Locale.CHINA); } else if(language.equals("en_US")){
		 * request.getSession().setAttribute("languageKey",
		 * java.util.Locale.US); }
		 */
		String subsystem = request.getParameter("subsystem_id");

		if (subsystem == null)
			subsystem = StringUtil.getCookieValue(request, "subsystem_id");
		if (subsystem == null)
			subsystem = AccessControl.getDefaultSUBSystemID();
		authResponse.setSystemid(subsystem);
		authResponse.setSystemName(Framework.getSystemName(authResponse.getSystemid()));

		String machineIP = AdminUtil.getClientIP(request);
		authResponse.setClientIP(machineIP);
		String specialuser = permissionModule.isSpesialUser(machineIP);
		if ((specialuser != null) && userName == null) {

			try// uim检测
			{
				userName = specialuser;
				AccessControl control = AccessControl.getInstance();
				control.checkAccess(request, response, false);
				String user = control.getUserAccount();
				authResponse.setFromsso(true);
				if (user == null || "".equals(user) || !userName.equals(user)) {

					try {
						if (!userName.equals(user))
							control.resetSession(session);
						AuthUser authUser = permissionModule.getUser(userName);
//						String password = permissionModule.getUserPassword(userName);
						control = AccessControl.getInstance();
						control.login(request, response, userName, authUser.getPasswordText());
						if (session == null)
							session = request.getSession(false);
						authResponse.setSessionId(session.getId());
						authResponse.setCode("200");
						authResponse.setMessage("success");
						authResponse.setUser(control.getUserAttributes());
					} catch (Exception e) {
						authResponse.setCode("500");
						authResponse.setMessage(StringUtil.exceptionToString(e));
					}

				} else {

					authResponse.setCode("200");
					authResponse.setMessage("success");
				}


			} catch (Exception e)// 检测失败,继续平台登录
			{
				authResponse.setCode("500");
				authResponse.setMessage(StringUtil.exceptionToString(e));
			}
			return authResponse;

		} else {
			AuthUser authUser = permissionModule.getUser(userName);
			if(authUser == null){
				authResponse.setCode("500");
				authResponse.setMessage("用户["+userName+"]不存在!");
				return authResponse;
			}


			String password = request.getParameter("password");
			if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password))
			{
				authResponse.setCode("500");
				authResponse.setMessage("请输入正确的账号或者口令!");
			}
			else{

				//判断口令是否过期
				boolean enablePasswordExpiredDays = ConfigManager.getInstance().getConfigBooleanValue("enablePasswordExpiredDays", false);
				if(enablePasswordExpiredDays) {
					int expiredays = userName != null ? authUser.getPasswordDualtime() : 0;
					String expriedtime_ = "";
					Date expiretime = expiredays > 0 ? permissionModule.getPasswordExpiredTimeByUserAccount(authUser) : null;
					if (expiretime != null) {
						SimpleDateFormat dateformt = new SimpleDateFormat("yyyy-MM-dd");
						expriedtime_ = dateformt.format(expiretime);
						authResponse.setCode("500");
						authResponse.setMessage(new StringBuilder().append("口令过期，有效期:").append(expiredays).append(",过期时间：").append(expriedtime_).toString());
						return authResponse;
					}
				}
				//判断校验码是否正确
				boolean enable_login_validatecode = ConfigManager.getInstance().getConfigBooleanValue(
						"enable_login_validatecode", false);
				try {
					if (enable_login_validatecode) {
						permissionModule.validatecode(request);
					}
					AccessControl accessControl = AccessControl.getInstance();
					accessControl.login(request, response, userName, password);
					if (session == null)
						session = request.getSession(false);
					authResponse.setSessionId(session.getId());
					authResponse.setUser(accessControl.getUserAttributes());
					authResponse.setCode("200");
					authResponse.setMessage("success");
					// response.sendRedirect("sysmanager/refactorwindow.jsp?subsystem_id="
					// + subsystem);
				} catch (AccessException ex) {

					errorMessage = ex.getMessage();
					if (errorMessage != null) {

					} else {
						errorMessage = org.frameworkset.web.servlet.support.RequestContextUtils.getI18nMessage(
								"sany.pdp.login.failed", request);
					}
					authResponse.setCode("500");
					authResponse.setMessage(errorMessage);

					// if(errorMessage==null){
					// out.print("登陆失败，请确保输入的用户名和口令是否正确！");
					// }
					// else{
					// out.print(errorMessage);
					// }

				} catch (Exception ex) {
					errorMessage = ex.getMessage();

					if (errorMessage != null) {
						// errorMessage = errorMessage.replaceAll("\\n",
						// "\\\\n");
						// errorMessage = errorMessage.replaceAll("\\r",
						// "\\\\r");
					} else {
						errorMessage = org.frameworkset.web.servlet.support.RequestContextUtils.getI18nMessage(
								"sany.pdp.login.failed", request);
					}
					authResponse.setCode("500");
					authResponse.setMessage(errorMessage);
				}
			}

		}
		return authResponse;
	}

	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception,
			Exception {
		HttpSession session = request.getSession(false);


		boolean fromredirect = false;

		String successRedirect = request.getParameter(AuthenticateFilter.referpath_parametername);
		if (successRedirect != null) {
			if ((successRedirect.equals(request.getContextPath())
					|| successRedirect.equals(request.getContextPath() + "/jsp") || successRedirect.equals("login.jsp") || successRedirect
					.equals("login.page"))) {
				successRedirect = null;
			} else {
				fromredirect = true;
				model.addAttribute("successRedirect", successRedirect);
			}

		}
		String language = request.getParameter("language");
		PermissionModule permissionModule = ConfigManager.getInstance().getPermissionModule();
		boolean enable_login_validatecode = ConfigManager.getInstance().getConfigBooleanValue(
				"enable_login_validatecode", true);

		model.addAttribute("enable_login_validatecode", enable_login_validatecode);
		String errorMessage = null;

		String userName = request.getParameter("userName");

		String loginStyle = null;
		String system_id = null;
		if (language == null) {
			language = RequestContextUtils.getLocaleResolver(request).resolveLocaleCode(request);

		} else {
			RequestContextUtils.getLocaleResolver(request).setLocale(request, response, language);
		}
		model.addAttribute("language", language);

		/*
		 * if(language.equals("zh_CN")){
		 * request.getSession().setAttribute("languageKey",
		 * java.util.Locale.CHINA); } else if(language.equals("en_US")){
		 * request.getSession().setAttribute("languageKey",
		 * java.util.Locale.US); }
		 */
		String loginPath = request.getParameter("loginPath");//登陆界面风格
		String subsystem_id = request.getParameter("subsystem_id");


		loginStyle = StringUtil.getCookieValue(request, "loginStyle");
		system_id = StringUtil.getCookieValue(request, "subsystem_id");

		if (loginPath != null) {
			StringUtil.addCookieValue(request, response, "loginStyle", loginPath);
			loginStyle = loginPath;

		}
		if (loginStyle == null)
			loginStyle = "5";
		if (subsystem_id != null) {
			StringUtil.addCookieValue(request, response, "subsystem_id", subsystem_id);

		}
		model.addAttribute("system_id", system_id);
		model.addAttribute("loginStyle", loginStyle);
		model.addAttribute("defaultmodulename", Framework.getSystemName("module", request));


		String machineIP = StringUtil.getClientIP(request);
		String specialuser = permissionModule.isSpesialUser(machineIP);
		if ((specialuser != null) && userName == null) {

			String subsystem = request.getParameter("subsystem_id");
			try// uim检测
			{

				userName = specialuser;

				AccessControl control = AccessControl.getInstance();
				control.checkAccess(request, response, false);
				String user = control.getUserAccount();
				request.setAttribute("fromsso", "true");
				if (user == null || "".equals(user) || !userName.equals(user)) {

					try {
						if (!userName.equals(user))
							control.resetSession(session);
						String password = permissionModule.getUserPassword(userName);
						control = AccessControl.getInstance();
						control.login(request, response, userName, password);

						if (subsystem == null)
							subsystem = AccessControl.getDefaultSUBSystemID();
						if (successRedirect == null) {
							successRedirect = getSuccessRedirect(loginPath, subsystem);
						}
						if (!fromredirect) {
							AccessControl.recordIndexPage(request, successRedirect);
							AccessControl.recordeSystemLoginPage(request, response);
						}
						response.sendRedirect(successRedirect);
						return null;
					} catch (Exception e) {

						response.sendRedirect(new StringBuilder().append(request.getContextPath()).append("/jsp/common/ssofail.jsp?userName=").append(userName).append("&ip=").append(machineIP).toString());
						return null;
					}

				} else {

					if (subsystem == null)
						subsystem = AccessControl.getDefaultSUBSystemID();
					if (successRedirect == null) {
						successRedirect = getSuccessRedirect(loginPath, subsystem);

					}
					if (!fromredirect) {
						AccessControl.recordIndexPage(request, successRedirect);
						AccessControl.recordeSystemLoginPage(request, response);
					}
					response.sendRedirect(successRedirect);
					return null;
				}

			} catch (Exception e)// 检测失败,继续平台登录
			{

			}

		} else {
			String flag = request.getParameter("flag"); // 是否触发提交


			if (flag == null) {
			} else {
				// String successRedirect =
				// request.getParameter("successRedirect");

				String password = request.getParameter("password");

				 if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password))
				{
					errorMessage = ("请输入正确的账号或者口令!");
				}
				else {


					 try {
						 AuthUser authUser = permissionModule.getUser(userName);
						 if(authUser == null){
							 errorMessage = ("用户["+userName+"]不存在!");
							 throw new AccessException(errorMessage);
						 }

//判断口令是否过期
						 boolean enablePasswordExpiredDays = ConfigManager.getInstance().getConfigBooleanValue("enablePasswordExpiredDays", false);
						 if (enablePasswordExpiredDays) {
							 int expiredays = userName != null ? authUser.getPasswordDualtime() : 0;
							 String expriedtime_ = "";
							 Date expiretime = expiredays > 0 ? permissionModule.getPasswordExpiredTimeByUserAccount(authUser) : null;
							 if (expiretime != null && expiretime.before(new Date())) {

								 SimpleDateFormat dateformt = new SimpleDateFormat("yyyy-MM-dd");
								 expriedtime_ = dateformt.format(expiretime);
								 model.addAttribute("expriedtime_", expriedtime_);
								 model.addAttribute("userName", userName);
								 model.addAttribute("expiredays", expiredays);
								 errorMessage = new StringBuilder().append("口令过期，有效期:").append(expiredays).append(",过期时间：").append(expriedtime_).toString();
								 throw new AccessException(errorMessage);

							 }
						 }
						 if (enable_login_validatecode) {
							 permissionModule.validatecode(request);
						 }

						 AccessControl.getInstance().login(request, response, userName, password);
						 String subsystem = request.getParameter("subsystem_id");

						 if (subsystem == null)
							 subsystem = AccessControl.getDefaultSUBSystemID();
						 /**
						  * 需要全屏时，将response.sendRedirect("index.jsp");注释掉，
						  * 将response.sendRedirect(
						  * "sysmanager/refactorwindow.jsp");打开
						  */
						 if (successRedirect == null) {
							 successRedirect = getSuccessRedirect(loginPath, subsystem);

						 }
						 if (!fromredirect) {
							 AccessControl.recordIndexPage(request, successRedirect);
							 AccessControl.recordeSystemLoginPage(request, response);
						 }
						 response.sendRedirect(successRedirect);
						 return null;
						 // response.sendRedirect("sysmanager/refactorwindow.jsp?subsystem_id="
						 // + subsystem);
					 } catch (AccessException ex) {

						 errorMessage = ex.getMessage();
						 if (errorMessage != null) {

						 } else {
							 errorMessage = org.frameworkset.web.servlet.support.RequestContextUtils.getI18nMessage(
									 "sany.pdp.login.failed", request);
						 }

						 // if(errorMessage==null){
						 // out.print("登陆失败，请确保输入的用户名和口令是否正确！");
						 // }
						 // else{
						 // out.print(errorMessage);
						 // }

					 } catch (Exception ex) {
						 log.error("login failed:",ex);
						 errorMessage = ex.getMessage();

						 if (errorMessage != null) {
							 // errorMessage = errorMessage.replaceAll("\\n",
							 // "\\\\n");
							 // errorMessage = errorMessage.replaceAll("\\r",
							 // "\\\\r");
						 } else {
							 errorMessage = org.frameworkset.web.servlet.support.RequestContextUtils.getI18nMessage(
									 "sany.pdp.login.failed", request);
						 }
						 // out.print(errorMessage+ "登陆失败，请确保输入的用户名和口令是否正确！");

					 }

				}

			}

		}
		if (errorMessage != null)
			model.addAttribute("errorMessage", errorMessage);
		List<SubSystem> subsystemList = Framework.getInstance().getSubsystemList();
		List<SysInfo> syses = new ArrayList<SysInfo>();
		SysInfo sys = new SysInfo();
		sys.setName(Framework.getSystemName("module", request));
		sys.setSysid("module");
		syses.add(sys);
		if (subsystemList != null && subsystemList.size() > 0) {
			for (SubSystem sub : subsystemList) {
				sys = new SysInfo();
				sys.setName(Framework.getSystemName(sub.getId(), request));
				sys.setSysid(sub.getId());
				if (system_id != null && system_id.equals(sys.getSysid()))
					sys.setSelected(true);
				syses.add(sys);
			}

		}
		model.addAttribute("systemList", syses);
		return "path:login";
	}

	@AssertTicket
	public void ssowithticket(HttpServletRequest request, HttpServletResponse response) {
		_ssowithtoken(request, response);
	}

	@AssertDToken
	public void ssowithtoken(HttpServletRequest request, HttpServletResponse response) {
		_ssowithtoken(request, response);
	}


	/**
	 * 强制要求系统必须携带令牌
	 *
	 * @return
	 */

	public void _ssowithtoken(HttpServletRequest request, HttpServletResponse response) {
		// return "path:sso";

		String u = "", p = "", ck = "";

		String successRedirect = request.getParameter("successRedirect");

		if (!StringUtil.isEmpty(successRedirect)) {
			successRedirect = StringUtil.getRealPath(request, successRedirect, true);
		}
		String userName = (String) request.getAttribute(TokenStore.token_request_account_key);
		String worknumber = (String) request.getAttribute(TokenStore.token_request_worknumber_key);
		String loginType = "1";
		if (StringUtil.isEmpty(userName)) {
			userName = worknumber;
			loginType = "2";
		}
		PermissionModule permissionModule = ConfigManager.getInstance().getPermissionModule();
		String loginMenu = request.getParameter("loginMenu");
		String contextpath = request.getContextPath();
		String menuid = "newGetDoc";
		if (loginMenu != null) {

			menuid = loginMenu;

		}
		HttpSession session = request.getSession();

		try {
			AccessControl control = AccessControl.getInstance();
			control.checkAccess(request, response, false);
			String user = control.getUserAccount();

			worknumber = control.getUserAttribute("userWorknumber");
			boolean issameuser = false;
			if (loginType.equals("2")) {
				if (worknumber != null && !worknumber.equals(""))
					issameuser = userName.equals(worknumber);
			} else {
				if (user != null && !user.equals(""))
					issameuser = userName.equals(user);
			}

			if (user == null || "".equals(user) || !issameuser) {

				if (!issameuser) {
					control.resetSession(session);
				}

				try {
					// 1-域账号登录 2-工号登录
					String password = null;
					if (loginType.equals("1")) {

						password = permissionModule.getUserPassword(userName);
						if (password == null)
							throw new AccessException("用户" + userName + "不存在。");
					} else {
						java.util.Map data = permissionModule.getUserNameAndPasswordByWorknumber(userName);
						if (data == null)
							throw new AccessException("工号为" + userName + "的用户不存在。");
						userName = (String) data.get("USER_NAME");
						password = (String) data.get("USER_PASSWORD");
					}
					control = AccessControl.getInstance();
					request.setAttribute("fromsso", "true");
					// System.out.println("-----------userName="+userName+",password="+password);
					control.login(request, response, userName, password);
					if (StringUtil.isEmpty(successRedirect)) {
						Framework framework = Framework.getInstance(control.getCurrentSystemID());
						MenuItem menuitem = framework.getMenuByID(menuid);
						if (menuitem instanceof Item) {

							Item menu = (Item) menuitem;
							successRedirect = MenuHelper.getRealUrl(contextpath,
									Framework.getWorkspaceContent(menu, control), MenuHelper.menupath_menuid,
									menu.getId());
						} else {

							Module menu = (Module) menuitem;
							StringBuilder framepath = new StringBuilder();
							framepath.append(contextpath).append("/sanydesktop/singleframe.page?").append(MenuHelper.menupath).append("=").append(menu.getPath());
							successRedirect = framepath.toString();
						}
						AccessControl.recordIndexPage(request, successRedirect);
					} else {
						successRedirect = URLDecoder.decode(successRedirect);
					}
					response.sendRedirect(successRedirect);
					return;
				} catch (Exception e) {
					log.info("", e);
					String msg = e.getMessage();
					if (msg == null)
						msg = "";
					StringBuilder builder = new StringBuilder();
					builder.append(contextpath).append("/webseal/websealloginfail.jsp?userName=").append(userName)
							.append("&ip=").append(AdminUtil.getClientIP(request)).append("&errormsg=").append(java.net.URLEncoder.encode(java.net.URLEncoder.encode(msg, "UTF-8"), "UTF-8"));
					response.sendRedirect(builder.toString());
					return;
				}

			} else {
				control.resetUserAttributes();
				if (StringUtil.isEmpty(successRedirect)) {
					Framework framework = Framework.getInstance(control.getCurrentSystemID());
					MenuItem menuitem = framework.getMenuByID(menuid);
					if (menuitem instanceof Item) {

						Item menu = (Item) menuitem;
						successRedirect = MenuHelper.getRealUrl(contextpath,
								Framework.getWorkspaceContent(menu, control), MenuHelper.menupath_menuid,
								menu.getId());
					} else {

						Module menu = (Module) menuitem;
						StringBuilder framepath = new StringBuilder();
						framepath.append(contextpath).append("/sanydesktop/singleframe.page?").append(MenuHelper.menupath
						).append("=").append(menu.getPath());
						successRedirect = framepath.toString();
					}
					AccessControl.recordIndexPage(request, successRedirect);
				} else {
					successRedirect = URLDecoder.decode(successRedirect);
				}
				response.sendRedirect(successRedirect);
				return;
			}

		} catch (Throwable ex) {
			log.info("", ex);
			String errorMessage = ex.getMessage();
			if (errorMessage == null)
				errorMessage = "";

			try {
				FileCopyUtils.copy(errorMessage + "," + userName + "登陆失败，请确保输入的用户名和口令是否正确！",
						new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
			} catch (IOException e) {
				log.info("", e);
			}

		}


	}

	/**
	 * 随机取颜色
	 *
	 * @return
	 * @version V1.0.0
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) fc = 255;
		if (bc > 255) bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 在系统首页切换平台
	 *
	 * @param request
	 * @return
	 */
	public String switchSystem(HttpServletRequest request, ModelMap model) {
		MenuHelper menuHelper = MenuHelper.getMenuHelper(request, true);

		String indexpage = AccessControl.getIndexPage(request);
		if (!indexpage.startsWith("/"))
			indexpage = "/" + indexpage;
		model.addAttribute("selected", AccessControl.getAccessControl().getCurrentSystemID());
		return indexpage;

	}

	public @ResponseBody String logout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(false);
		String _redirectPath = request.getParameter("_redirectPath");
		AccessControl accesscontroler = AccessControl.getInstance();
		try
		{
			boolean success = accesscontroler.checkAccess(request, response,false);
			if(success) {
				String account = accesscontroler.getUserAccount();
				accesscontroler.logout(false, false);
				return new StringBuilder().append(account).append(" logout success.").toString();
			}
			return "not login.";

		}
		catch(Exception e)
		{
			return "logout failed:"+e.getMessage();
		}
	}

}
