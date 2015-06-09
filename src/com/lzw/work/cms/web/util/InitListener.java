package com.lzw.work.cms.web.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lzw.work.dwf.manager.SQLManagerImpl;

/**
 * Application Lifecycle Listener implementation class InitListener
 * 
 */
public class InitListener implements ServletContextListener {

	protected static Log log = LogFactory.getLog(InitListener.class);

	private SessionFactory sessionFactory;

	private SessionFactory trafficeSessionFactory;

	private WebApplicationContext wac;

	private ServletContext servletContext;

	private SQLManagerImpl sqlManager;

	/**
	 * Default constructor.
	 */
	public InitListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {

		try {
			servletContext = contextEvent.getServletContext();

			wac = WebApplicationContextUtils
					.getWebApplicationContext(contextEvent.getServletContext());

			sessionFactory = (SessionFactory) wac.getBean("sessionFactory");

			trafficeSessionFactory = (SessionFactory) wac
					.getBean("trafficeSessionFactory");

			sqlManager = (SQLManagerImpl) wac.getBean("sqlManager");

			initTomcatPath();

			sqlManager.setBaseSessionFactory(trafficeSessionFactory);
			initCode();
			sqlManager.setBaseSessionFactory(sessionFactory);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	private void initCode() {

		Map codeMap = new HashMap();
		// 制造国
		Map param = new HashMap();
		param.put("dmlb", "0031");
		param.put("xtlb", "00");
		codeMap.put("zzg", getCodeList(param));

		// 转向形式
		param.put("dmlb", "0016");
		param.put("xtlb", "01");
		getCodeList(param);
		codeMap.put("zxxs", getCodeList(param));

		// 燃油总类
		param.put("dmlb", "0009");
		param.put("xtlb", "01");
		getCodeList(param);
		codeMap.put("rlzl", getCodeList(param));

		// 车辆类型
		param.put("dmlb", "1004");
		param.put("xtlb", "00");
		getCodeList(param);
		codeMap.put("cllx", getCodeList(param));

		// 车身颜色
		param.put("dmlb", "1008");
		param.put("xtlb", "00");
		getCodeList(param);
		codeMap.put("csys", getCodeList(param));

		// 号牌种类
		param.put("dmlb", "1007");
		param.put("xtlb", "00");
		getCodeList(param);
		codeMap.put("hpzl", getCodeList(param));

		// 进口国产
		param.put("dmlb", "0012");
		param.put("xtlb", "01");
		getCodeList(param);
		codeMap.put("gcjk", getCodeList(param));

		// 使用性质
		param.put("dmlb", "1003");
		param.put("xtlb", "00");
		getCodeList(param);
		codeMap.put("syxz", getCodeList(param));

		// System.out.println(codeMap);

		servletContext.setAttribute("CardCodes", codeMap);
	}

	private List getCodeList(Map param) {

		final String sql = "SELECT * FROM TRFF_APP.FRM_CODE WHERE DMLB=:dmlb AND XTLB=:xtlb";
		return sqlManager.getBaseList(sql, param, null);

	}

	private void initTomcatPath() {

		String tomcatPath = this.getClass().getClassLoader().getResource("/")
				.getPath();
		String uploadCadre = tomcatPath.substring(0,
				tomcatPath.indexOf("/WEB-INF"));
		tomcatPath = tomcatPath.substring(0, tomcatPath.indexOf("/webapps"));

		String uploadPath = tomcatPath + "/upload";

		File uploadFile = new File(uploadPath);

		if (!uploadFile.exists()) {
			uploadFile.mkdir();
		}

		File uploadCadreFile = new File(uploadCadre + "/uploadCadre");
		if (!uploadCadreFile.exists()) {
			uploadCadreFile.mkdir();
		}

		File barcodeFile = new File(uploadCadre + "/barcode");
		if (!barcodeFile.exists()) {
			barcodeFile.mkdir();
		}
		File towCode = new File(tomcatPath + "/webapps/2code");
		if (!towCode.exists()) {
			towCode.mkdir();
		}

		File tempCode = new File(tomcatPath + "/webapps/temp2code");
		if (!tempCode.exists()) {
			tempCode.mkdir();
		}

		System.setProperty("tomcat.dir", tomcatPath);
		System.setProperty("uploadCadrePath", uploadCadre);
		System.setProperty("barcode", uploadCadre + "/barcode");
		System.setProperty("2code", tomcatPath + "/webapps/2code");
		System.setProperty("temp2code", tomcatPath + "/webapps/temp2code");
	}
}
