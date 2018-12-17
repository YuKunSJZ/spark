package config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import jdbc.JDBCHelper;

public class BigDataTask {
	String taskID = "1";
	public HashMap<String, String> TaskParameter = new HashMap<String, String>();

	public BigDataTask(String _taskid) {
		taskID = _taskid;
		String sql = "Select parametername,parametervalue from test_db.taskparameters where taskid='" + _taskid + "'";
		JDBCHelper mydb = JDBCHelper.getInstance();
		System.out.println(sql.toString());
		ResultSet myRs = mydb.getQueryRs(sql);

		try {
			while (myRs.next()) {
				TaskParameter.put(myRs.getString("parametername"), myRs.getString("parametervalue"));
			}

			myRs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
