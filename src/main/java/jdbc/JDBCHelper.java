package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import config.ConfigrationManager;

public class JDBCHelper {
	static {
		try {
			Class.forName(ConfigrationManager.getProperty(Constants.Constants.JDBC_DRIVER));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static JDBCHelper instance = null;
	private LinkedList<Connection> datasource = new LinkedList<Connection>();
	
	private JDBCHelper() {
		int datasourcePoolSize=ConfigrationManager.getPropertyInteger(
				Constants.Constants.JDBC_DATASOURCE_POOL_SIZE);
		String JDBCUrl = ConfigrationManager.getProperty(Constants.Constants.JDBC_URL);
		String JDBCUser = ConfigrationManager.getProperty(Constants.Constants.JDBC_USER);
		String JDBCPass = ConfigrationManager.getProperty(Constants.Constants.JDBC_PASS);

		
		for(int i=0; i<=datasourcePoolSize; i++) {
			try {
				Connection conn = DriverManager.getConnection(JDBCUrl,JDBCUser, JDBCPass);
				datasource.push(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static JDBCHelper getInstance() {
		if(instance==null) {
			synchronized (JDBCHelper.class) {
				if(instance==null) {
					instance = new JDBCHelper();
				}
			}
		}
		return instance;
	}
	
	public synchronized Connection getConnection() {
		while(datasource.size() == 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return datasource.poll();
	}
	
	public int executeSql(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rtn=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rtn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (conn != null) {
				datasource.push(conn);
			}
		}
		
		return rtn;
		
	}
	
	public ResultSet getQueryRs(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);		
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			datasource.push(conn);
		}
		
		return rs;
		
	}
	
	public int[] executeBatch(String sql,List<Object[]> paramsList ) {
		int[] rtn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (Object[] params: paramsList) {
				for (int i=0; i<=params.length; i++) {
					pstmt.setObject(i+1,params[i]);
				}
				pstmt.addBatch();
			}
			
			rtn = pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(conn != null) {
				datasource.push(conn);
			}
		}
		
		return rtn;
		
	}
	
	public static interface QueryCallBack {
		void process(ResultSet rs) throws Exception;

	}

}
