package BigDataAnalysis;

public interface LoadDataToMysql {

	void getDataFromOuter();
	void insertDataToMysql();
	void addTable(String tableName);
}
