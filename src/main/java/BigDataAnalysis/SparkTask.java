package BigDataAnalysis;
public class SparkTask {
	public static void main(String[] args) {
		FundCompany  myFundCompany = new FundCompany();
		myFundCompany.load2ODS();
		myFundCompany.close();
		
	}
}
