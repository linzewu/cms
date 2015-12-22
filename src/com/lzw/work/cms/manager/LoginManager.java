package com.lzw.work.cms.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lzw.work.dwf.entity.BaseEntity;
import com.lzw.work.multiple.manager.MultipleManagerAbstract;

@Scope("prototype")
@Service("loginManager")
public class LoginManager  extends MultipleManagerAbstract {

	@Override
	public void setBean(BaseEntity bean) throws Exception {
		this.bean = bean;
	}
	
	public void login(){
		
		String userName=ServletActionContext.getRequest().getParameter("userName");
		
		String password=ServletActionContext.getRequest().getParameter("password");
		
		JSONObject rjo=new JSONObject();
		
		if(userName==null||"".equals(userName.trim())||password==null||"".equals(password.trim())){
			rjo.put("state","408");
			rjo.put("message","用户名密码不能为空！");
			pw.print(rjo);
			return;
		}
		
		String sql="select * from t_user where userName=:userName and password=:password";
		Map param=new HashMap();
		param.put("password", password);
		param.put("userName", userName);
		
		List users = this.getBaseList(sql, param, null);
		if(users.size()>0){
			rjo.put("state","200");
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user", users.get(0));
			System.out.println(rjo.toString());
			pw.print(rjo);
		}else{
			rjo.put("state","405");
			rjo.put("message","用户名密码错误！");
			pw.print(rjo);
		}
	}
	
	public void logout(){
		JSONObject rjo=new JSONObject();
		rjo.put("state","200");
		ServletActionContext.getRequest().getSession().invalidate();
		pw.print(rjo);
		
		
	}

}
