package com.lzw.work.cms.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lzw.work.common.CommonUtil;
import com.lzw.work.dwf.entity.BaseEntity;
import com.lzw.work.multiple.manager.MultipleManagerAbstract;

@Scope("prototype")
@Service("trafficDBManager")
public class TrafficDBManager extends MultipleManagerAbstract {

	@Resource(name = "trafficeSessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void setBean(BaseEntity bean) throws Exception {
		this.bean = bean;
	}

	public void getGongGaoListbyCLXH() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String clxh = request.getParameter("clxh");

		if (clxh != null) {
			final String sql = "SELECT PSV.BH,PSV.CLXH,TO_CHAR(PSV.GGRQ,'YYYY-MM-DD') ||' '|| PSV.CLXH  as GGRQ  FROM trff_app.PCB_ST_VEHICLE PSV"
					+ " WHERE PSV.CLXH LIKE :clxh order by PSV.GGRQ desc";
			Map param = new HashMap();
			param.put("clxh", clxh + "%");

			List list = this.getBaseList(sql, param, null);

			JSONArray ja = JSONArray.fromObject(list);

			pw.print(ja);

		}
	}

	public String getFirstGGBH(String clxh) {

		final String sql = "SELECT PSV.BH,PSV.CLXH,TO_CHAR(PSV.GGRQ,'YYYY-MM-DD') ||' '|| PSV.CLXH  as GGRQ  FROM trff_app.PCB_ST_VEHICLE PSV"
				+ " WHERE PSV.CLXH = :clxh order by PSV.GGRQ desc";
		Map param = new HashMap();
		param.put("clxh", clxh);
		List list = this.getBaseList(sql, param, null);

		if (list == null || list.size() == 0) {
			return "";
		} else {
			Map map = (Map) list.get(0);
			return (String) map.get("BH");
		}

	}

	public void getGongGaoInfoByGgbh() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ggbh = request.getParameter("ggbh");
		Map map = new HashMap();
		if (ggbh == null || "".equals(ggbh.trim())) {
			map.put("state", "200");
			JSONObject jo = JSONObject.fromObject(map);
			pw.print(jo);
			return;
		}
		final String sql = "SELECT * from trff_app.PCB_ST_VEHICLE where bh=:bh";

		Map param = new HashMap();
		param.put("bh", ggbh);

		List<Object> list = this.getBaseList(sql, param, null);

		Map<String, Object> rMap = (Map) list.get(0);

		Map returnMap = new HashMap();

		for (String key : rMap.keySet()) {
			returnMap.put(key.toLowerCase(), rMap.get(key));
		}

		map.put("data", returnMap);
		map.put("state", "200");

		JSONObject jo = JSONObject.fromObject(map);
		System.out.println(jo);
		pw.print(jo);

	}

	// 获取地盘公告
	public void getListOfDPGG() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String dpid = request.getParameter("dpid");

		final String sql = "select * from trff_app.PCB_ST_CHASSIS where dpid=:dpid";
		Map param = new HashMap();

		param.put("dpid", dpid);

		List<Object> list = this.getBaseList(sql, param, null);
		convertData(list);

		Map map = new HashMap();
		map.put("data", list);
		map.put("state", "200");

		JSONObject jo = JSONObject.fromObject(map);
		System.out.println(jo);
		pw.print(jo);

	}

	// 获取地盘公告
	public void getDPGG() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String bh = request.getParameter("bh");

		final String sql = "select * from trff_app.PCB_ST_CHASSIS where bh=:bh";
		Map param = new HashMap();

		param.put("bh", bh);

		List<Object> list = this.getBaseList(sql, param, null);
		convertData(list);
		// Map<String, Object> rMap = (Map) list.get(0);

		Map returnMap = new HashMap();

		Map map = new HashMap();
		map.put("data", list);
		map.put("state", "200");

		JSONObject jo = JSONObject.fromObject(map);
		System.out.println(jo);
		pw.print(jo);

	}

	public void getImplCarParam() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String clxh = request.getParameter("clxh");

		final String sql = "select * from trff_app.PCB_FINAL_PARA where clxh like :clxh";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map param = new HashMap();
		param.put("clxh", clxh + "%");
		
		Map pageMap=new HashMap();
		
		pageMap.put("pageNumber", 1);
		pageMap.put("pageSize", 100);
		
		List<Object> list = this.getBaseList(sql, param, pageMap);
		
		List<Map> returnList=new ArrayList<Map>();
		//key 转小写
		for(Object o:list){
			Map<String,Object> m=(Map)o;
			Map returnMap=new HashMap();
			for(String key: m.keySet()){
				if("GGSXRQ".equals(key)||"TZSCRQ".equals(key)){
					Date date =  (Date)m.get(key);
					if(date!=null){
						returnMap.put(key.toLowerCase(),sdf.format((date)));
					}else{
						returnMap.put(key.toLowerCase(),null);
					}
				}else{
					returnMap.put(key.toLowerCase(),m.get(key));
				}
			}
			returnList.add(returnMap);
		}
		
		Map map = new HashMap();
		
		map.put("rows", returnList);

//		JsonConfig jc = new JsonConfig();
//
//		jc.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
//
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//			@Override
//			public Object processArrayValue(Object value, JsonConfig jconfig) {
//				String[] obj = {};
//				System.out.println(value instanceof Date);
//				if (value instanceof Date[]) {
//					Date[] dates = (Date[]) value;
//					obj = new String[dates.length];
//					for (int i = 0; i < dates.length; i++) {
//						obj[i] = sdf.format(dates[i]);
//					}
//				}
//				return obj;
//			}
//
//			@Override
//			public Object processObjectValue(String key, Object value,
//					JsonConfig jconfig) {
//				System.out.println(value instanceof Date);
//				if (value instanceof Date) {
//					String str = sdf.format((Date) value);
//					return str;
//				}
//				return value;
//			}
//		});
		JSONObject jo = JSONObject.fromObject(map);
		pw.print(jo);

	}

	private void convertData(List list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (Object o : list) {
			Map map = (Map) o;
			Date date1 = (Date) map.get("GGRQ");
			Date date2 = (Date) map.get("GGSXRQ");
			Date date3 = (Date) map.get("TZSCRQ");
			if (date1 != null) {
				map.put("GGRQ", sdf.format(date1));
			}
			if (date2 != null) {
				map.put("GGSXRQ", sdf.format(date2));
			}
			if (date3 != null) {
				map.put("TZSCRQ", sdf.format(date3));
			}
			if (map.get("ZXXS") != null) {
				map.put("ZXXS", CommonUtil.convertCode("zxxs", map.get("ZXXS")
						.toString()));
			}
			if (map.get("RLZL") != null) {
				map.put("RLZL", CommonUtil.convertCode("rlzl", map.get("RLZL")
						.toString()));
			}
		}
	}

}
