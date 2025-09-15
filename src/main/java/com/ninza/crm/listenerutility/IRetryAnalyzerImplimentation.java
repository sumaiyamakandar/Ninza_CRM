package com.ninza.crm.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyzerImplimentation implements IRetryAnalyzer{

	int count=0;
	int upparlimitcount=5;
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<upparlimitcount) {
			count++;
			return true;
		}
		return false;
	}

}
