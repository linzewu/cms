package com.lzw.work.dwf.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lzw.work.dwf.criteria.CriteriaAbstract;
import com.lzw.work.dwf.entity.BaseEntity;
import com.lzw.work.dwf.manager.IBaseManager;
import com.lzw.work.dwf.manager.ISQLManager;
import com.lzw.work.multiple.manager.MultipleManagerAbstract;
import com.lzw.work.sqls.SQLAbstract;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Controller("baseManagerAction")
public class BaseManagerAction extends BaseAction implements
		ModelDriven<BaseEntity> {

	protected static Log log = LogFactory.getLog(BaseManagerAction.class);

	public static final String BEAN_TYPE = "bType";

	public static final String MULTIPLE_TYPE = "mType";

	public static final String QUERY_TYPE = "qType";

	public static final String METHOD = "method";

	public static final String MULTIPLE_TYPE_ERROR = "mType is not find";

	public static final String BEAN_TYPE_ERROR = "beanType is not find";

	public static final String STATE_SUCCESS = "200";

	public static final String STATE_ERROR = "500";

	public static final String STATE = "state";

	public static final String SID = "sid";

	@Resource
	private IBaseManager baseManager;

	@Resource
	private ISQLManager sqlManager;

	// spring ioc
	private WebApplicationContext wac = WebApplicationContextUtils
			.getWebApplicationContext(ServletActionContext.getServletContext());

	PrintWriter pw;

	JSONObject respondData;

	private BaseEntity bean;

	@Override
	public BaseEntity getModel() {
		String beanType = ServletActionContext.getRequest().getParameter(
				BEAN_TYPE);
		if (beanType != null && !"".equals(beanType.trim())) {
			bean = (BaseEntity) wac.getBean(beanType);
		}
		return bean;
	}

	@PostConstruct
	private void initAction() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		pw = response.getWriter();
		respondData = new JSONObject();
	}

	private void destroyAction() {
		if (pw != null) {
			pw.close();
			pw = null;
		}
		respondData = null;
	}

	private DetachedCriteria queryTypeOfCriteriaInti() {
		String queryType = ServletActionContext.getRequest().getParameter(
				QUERY_TYPE);
		CriteriaAbstract ca = null;
		if (queryType != null && !"".equals(queryType)) {
			ca = (CriteriaAbstract) wac.getBean(queryType);
		}
		if (ca != null) {
			return ca.getCriteria(bean);
		} else {
			return DetachedCriteria.forClass(bean.getClass());
		}
	}

	private SQLAbstract queryTypeOfSQLInti() {
		String queryType = ServletActionContext.getRequest().getParameter(
				QUERY_TYPE);
		SQLAbstract sa = null;
		if (queryType != null && !"".equals(queryType)) {
			sa = (SQLAbstract) wac.getBean(queryType);
		}
		return sa;
	}

	public void saveBean() {
		try {
			if (bean == null) {
				throw new Exception(BEAN_TYPE_ERROR);
			} else {
				String sid = null;
				if (bean.getId() == null || "".equals(bean.getId().trim())) {
					sid = this.baseManager.addBaseEntity(bean);
				} else {
					this.baseManager.updateBaseEntity(bean);
					sid = bean.getId();
				}

				respondData.put(SID, sid);
				respondData.put(STATE, STATE_SUCCESS);
				pw.print(respondData);
			}
		} catch (Exception e) {
			respondData.put(STATE, STATE_ERROR);
			log.error("addBean 异常", e);
		} finally {
			destroyAction();
		}

	}

	public void addBean() {
		try {
			if (bean == null) {
				throw new Exception(BEAN_TYPE_ERROR);
			} else {


				String sid = this.baseManager.addBaseEntity(bean);
				respondData.put(SID, sid);
				respondData.put(STATE, STATE_SUCCESS);
				pw.print(respondData);
			}
		} catch (Exception e) {
			respondData.put(STATE, STATE_ERROR);
			log.error("addBean 异常", e);
		} finally {
			destroyAction();
		}
	}

	public void updateBean() {
		try {
			if (bean == null) {
				throw new Exception(BEAN_TYPE_ERROR);
			} else {
				this.baseManager.updateBaseEntity(bean);
				respondData.put(SID, bean.getId());
				respondData.put(STATE, STATE_SUCCESS);
			}
		} catch (Exception e) {
			log.error("addBean 异常", e);
			respondData.put(STATE, STATE_ERROR);
			pw.print(respondData);
		} finally {
			destroyAction();
		}
	}

	public void deleteBean() {
		try {
			this.baseManager.deleteBaseEntity(bean);
			respondData.put(STATE, STATE_SUCCESS);
			pw.print(respondData);
		} catch (Exception e) {
			log.error("数据删除失败", e);
			respondData.put(STATE, STATE_ERROR);
			pw.print(respondData);
		} finally {
			destroyAction();
		}

	}

	public void getBeanList() {
		try {
			Map pageMap = null;
			if (this.getRows() != 0 && this.getPage() != 0) {
				pageMap = new HashMap();
				pageMap.put("pageSize", this.rows);
				pageMap.put("pageNumber", this.page);
			}
			List list = this.baseManager.getBaseEntityList(
					queryTypeOfCriteriaInti(), pageMap);

			if (this.getRows() != 0 && this.getPage() != 0) {
				Long count = this.baseManager
						.getBaseEntityCount(queryTypeOfCriteriaInti());
				respondData.put("total", count);
				System.out.println(respondData);
			}
			JSONArray array = JSONArray.fromObject(list);

			// respondData.put(STATE,STATE_SUCCESS);
			respondData.put("rows", array);
			// System.out.println(respondData.toString());
			pw.print(respondData);
		} catch (Exception e) {
			log.error("查询列表错误", e);
			if (pw != null) {
				respondData.put(STATE, STATE_ERROR);
				pw.print(respondData);
			}
		} finally {
			destroyAction();
		}
	}

	public void getUniqueResult() {
		try {

			DetachedCriteria dc = queryTypeOfCriteriaInti();
			Object o = this.baseManager.getBaseUniqueResult(dc);
			pw.print(o.toString());
		} catch (Exception e) {
			log.error("查询列表错误", e);
			if (pw != null) {
				respondData.put(STATE, STATE_ERROR);
				pw.print(respondData);
			}
		} finally {
			destroyAction();
		}
	}

	public void getMapList() {
		try {
			SQLAbstract sa = queryTypeOfSQLInti();
			if (sa != null) {
				Map param = JSONObject.fromObject(bean);
				String sql = sa.getSQL(param);
				Map pageMap = null;
				if (this.getRows() != 0 && this.getPage() != 0) {
					pageMap = new HashMap();
					pageMap.put("pageSize", this.rows);
					pageMap.put("pageNumber", this.page);
				}

				List<Object> list = this.sqlManager.getBaseList(sql, param,
						pageMap);

				JSONArray ja = JSONArray.fromObject(list);

				respondData.put("rows", ja);

				String countSQL = sa.getCountSQL(param);
				if (countSQL != null) {
					Integer count = (Integer) this.sqlManager.getBaseCount(
							countSQL, param);
					respondData.put("total", count);
				}
				respondData.put(STATE, STATE_SUCCESS);
				pw.print(respondData);
			} else {
				throw new Exception("Query Type error");
			}
		} catch (Exception e) {
			log.error("getMapList 异常", e);
			respondData.put(STATE, STATE_ERROR);
			pw.print(respondData);
		} finally {
			destroyAction();
		}
	}

	public void getMap() {
		try {
			SQLAbstract sa = queryTypeOfSQLInti();

			if (sa != null) {
				Map param = JSONObject.fromObject(bean);
				String sql = sa.getSQL(param);

				Object object = this.sqlManager.getUniqueResult(sql, param);

				pw.print(object.toString());
			}
		} catch (Exception e) {
			JSONObject respondData = new JSONObject();
			log.error("getBaseList 异常", e);
			respondData.put("state", 500);
			pw.print(respondData);
		} finally {
			destroyAction();
		}
	}

	/**
	 * 复杂功能接口
	 */
	public void multipleManager() {

		try {
			String mType = ServletActionContext.getRequest().getParameter(
					MULTIPLE_TYPE);
			String bType = ServletActionContext.getRequest().getParameter(
					BEAN_TYPE);
			String method = ServletActionContext.getRequest().getParameter(
					METHOD);

			if (mType == null && bType != null) {
				mType = bType + "Manager";
			}

			if (mType == null || "".equals(mType)) {
				throw new Exception(MULTIPLE_TYPE_ERROR);
			} else {
			
				MultipleManagerAbstract mma = (MultipleManagerAbstract) wac
						.getBean(mType);
				mma.setBean(bean);
				mma.getClass().getMethod(method, null).invoke(mma, null);
			}
		} catch (Exception e) {
			JSONObject respondData = new JSONObject();
			log.info("multipleManager error", e);
			respondData.put("state", 500);
			pw.print(respondData);
		} finally {
			destroyAction();
		}
	}
	
	public void checkLogin(){
		
		Map userMap = (Map)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		if(userMap==null){
			pw.print("false"); 
		}else{
			pw.print("true");
		} 
	}

}
