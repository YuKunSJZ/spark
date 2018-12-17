import java.sql.ResultSet;
import java.sql.SQLException;

import config.BigDataTask;
import jdbc.JDBCHelper;

public class Test {

	public static void main(String[] args) {

	BigDataTask myTask = new BigDataTask("1");
	System.out.println(myTask.TaskParameter.get("idcode"));
	

	}

}
