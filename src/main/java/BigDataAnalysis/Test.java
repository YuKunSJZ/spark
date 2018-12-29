package BigDataAnalysis;


import com.google.common.base.Ascii;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CapitalCost myCaptalCost = new CapitalCost();
//		myCaptalCost.run();
////		
//		FlowIn myFlowIn = new FlowIn();
//		myFlowIn.run();
		
		FundCompany  myFundCompany = new FundCompany();
		myFundCompany.run();
		myFundCompany.close();

		FlowIn myFlowIn = new FlowIn();
		myFlowIn.run();

		CapitalCost myCaptalCost = new CapitalCost();
		myCaptalCost.run();

		


	}

}
