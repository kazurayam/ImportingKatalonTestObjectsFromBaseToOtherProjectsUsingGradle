package com.kazurayam.ks

import static org.junit.Assert.*

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.ks.CrossBorderObjectRepository as CBOR
import com.kms.katalon.core.testobject.TestObject


@RunWith(JUnit4.class)
public class CrossBorderObjectRepositoryTest {

	@BeforeClass
	static void beforeClass() {
		com.kazurayam.ks.testobject.TestObjectExtension.apply()
	}

	@Test
	void test_findTestObject_local() {
		TestObject tObj = CBOR.findTestObject("Object Repository/Page_DuckDuckGo  Privacy, simplified/input_q")
		//println tObj.prettyPrint()
		assertNotNull("tObj is null", tObj)
		assertEquals("//input[@id='searchbox_input']", tObj.getActiveXpaths().get(0).getValue())
	}
	
	@Test
	void test_findTestObject_siblingProject() {
		TestObject tObj = 
			CBOR.findTestObject(ExternalProject.Base.getProjectDir(),
				"Object Repository/Page_CURA Healthcare Service/a_Make Appointment")
		println tObj.prettyPrint()
		assertNotNull("tObj isnull", tObj)
		assertEquals("//a[@id='btn-make-appointment']", tObj.getActiveXpaths().get(0).getValue())
		
	}
}