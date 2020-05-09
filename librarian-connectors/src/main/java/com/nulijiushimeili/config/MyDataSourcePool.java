package com.nulijiushimeili.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyDataSourcePool {

	// 使用容器——使用环境查询条件不多主要做增删改操作
	private LinkedList<Connection> pool = new LinkedList<>();

    // 这里的代码应该写在配置文件中
	private static final int INIT_CONNECTIONS = 10;
	private static final String DRIVER_CLASS = "";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	private static final String URL = "";
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	static {
		// 注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 通过构造方法初始化连接
	public MyDataSourcePool() {
		for (int i = 0; i < INIT_CONNECTIONS; i++) {
			try {
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				pool.addLast(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 获取数据库连接
	public Connection getConnection() {
		Connection conn = null;
		lock.lock();
		try {
			while (pool.size() == 0) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (!pool.isEmpty()) {
				conn = pool.removeFirst();
			}
			return conn;
		} finally {
			lock.unlock();
		}
	}

	// 释放数据库连接
	public void releaseConnection(Connection conn) {
		if (conn != null) {
			lock.lock();
			try {
				// 释放连接过程就是把连接放回连接池过程
				pool.addLast(conn);
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
