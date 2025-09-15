package com.ninza.crm.leadtest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class demo {

	@Test 
	public void test() {
		System.out.println("Hi");
		String s=null;
		Assert.assertNotNull(s);
	}
}
