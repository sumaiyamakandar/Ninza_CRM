package com.ninza.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseclass.baseclass;

//import com.ninza.crm.leadtest.TakeScreenshot;

public class ListenerImplementation implements ITestListener,ISuiteListener{
	ExtentReports report;
	ExtentTest test;
	

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		Date date=new Date();
		String d = date.toString().replace(" ","_").replace(":","_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReports/Report_"+d+".html");
		spark.config().setDocumentTitle("CRM Report");
		spark.config().setReportName("Ninza CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Browser","Edge");
		report.setSystemInfo("OS","Windows 10");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName =result.getMethod().getMethodName();
		System.out.println(methodName+"Execution started");
		 test=report.createTest(methodName);
		 test.log(Status.INFO, methodName+"Execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName =result.getMethod().getMethodName();
		System.out.println(methodName+"Execution success");
		 test.log(Status.PASS, methodName+"Execution success");

	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName =result.getMethod().getMethodName();
		System.out.println(methodName+"Execution Failed");
		 test.log(Status.FAIL, methodName+"Execution Failed");
		 Date date=new Date();
			String d = date.toString().replace(" ","_").replace(":","_");
		 TakesScreenshot ts=(TakesScreenshot)baseclass.sdriver;
			String src=ts.getScreenshotAs(OutputType.BASE64);
			test.addScreenCaptureFromBase64String(src,methodName+"_"+date);

		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName =result.getMethod().getMethodName();
		System.out.println(methodName+"Execution skipped");
	}

}
