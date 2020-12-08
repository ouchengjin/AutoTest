package com.course.utils;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

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
		}
		System.out.println("sucess");
		return tl.get();
	}

	public static void close() {
		if (tl.get() != null) {
			tl.get().close();
			tl.remove();
		}
	}
}
