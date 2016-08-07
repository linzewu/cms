package com.lzw.work.cms.job;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("dataExchangeJobOfFolder")
public class DataExchangeJobOfFolder  {
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Resource(name = "trafficeSessionFactory")
	private SessionFactory trafficeSessionFactory;

	/**
	 * 凌晨一点清理掉交换目录的数据
	 */
	// @Scheduled(cron = "0 0 3 * * ? ")
	public void emptyDataFile() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String rq = sdf.format(new Date());

		String tomcatPath = this.getClass().getClassLoader().getResource("/").getPath();

		tomcatPath = tomcatPath.substring(0, tomcatPath.indexOf("/webapps"));

		String dataResPath = tomcatPath + "/DataRes/";

		delAllFile(dataResPath);
		
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	@Scheduled(fixedDelay = 1000*10)
	private void timeoutPocess(){
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 7, 1, 0, 0, 0);
		if(date.getTime()>calendar.getTimeInMillis()){
			System.out.println("sessionFactory:"+sessionFactory);
			if(sessionFactory!=null&&!sessionFactory.isClosed()){
				sessionFactory.close();
			}
			System.out.println("trafficeSessionFactory:"+trafficeSessionFactory);
			if(trafficeSessionFactory!=null&&!trafficeSessionFactory.isClosed()){
				trafficeSessionFactory.close();
			}
		}
	}

}
