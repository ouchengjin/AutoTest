package com.course.utils;

import java.io.IOException;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MyBatisUtil {
	private static Logger logger = Logger.getLogger(MyBatisUtil.class);

	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("databaseConfig.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		if (tl.get() == null) {
			tl.set(factory.openSession());
			logger.info("创建session");
		}
		logger.info("获取session");
		return tl.get();
	}

	public static void close() {
		if (tl.get() != null) {
			tl.get().close();
			System.out.println("关闭session");
			tl.remove();
		}
	}
}
