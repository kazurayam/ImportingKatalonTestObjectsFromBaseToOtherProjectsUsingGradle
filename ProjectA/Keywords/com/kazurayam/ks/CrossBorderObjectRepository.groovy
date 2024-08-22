package com.kazurayam.ks

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.testobject.ObjectRepository
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.constants.StringConstants
import java.text.MessageFormat

public class CrossBorderObjectRepository {
	
	private static final KeywordLogger logger = KeywordLogger.getInstance(CrossBorderObjectRepository.class);
	
	private static final String WEBELEMENT_FILE_EXTENSION = ".rs";
	
	public CrossBorderObjectRepository() {
		
	}
	
	@Keyword
	public static TestObject findTestObject(String testObjectRelativeId) {
		return ObjectRepository.findTestObject(testObjectRelativeId)
	}
	
	@Keyword
	public static TestObject findTestObject(String testObjectRelativeId, Map<String, Object> variables) {
		return ObjectRepository.findTestObject(testObjectRelativeId, variables)
	}
	
	@Keyword 
	public static TestObject findTestObject(File alternativeProjectDir) {
		return findTestObject(alternativeProjectDir, new HashMap<String, Object>())
	}
	
	@Keyword
	public static TestObject findTestObject(File alternativeProjectDir, String testObjectRelativeId, Map<String, Object> variables) {
		if (!Files.exists(alternativeProjectDir.toPath())) {
			logger.logWarning(alternativeProjectDir.toString() + " does not exist")
			return null
		}
		if (!alternativeProjectDir.isDirectory()) {
			logger.logWarning(alternativeProjectDir.toString() + " is not a directory")
			return null
		}
		
		if (testObjectRelativeId == null) {
			logger.logWarning(StringConstants.TO_LOG_WARNING_TEST_OBJ_NULL);
			return null;
		}
		
		String testObjectId = ObjectRepository.getTestObjectId(testObjectRelativeId)
		logger.logDebug(MessageFormat.format(StringConstants.TO_LOG_INFO_FINDING_TEST_OBJ_W_ID, testObjectId));
		
		File objectFile = new File(alternativeProjectDir, testObjectId + WEBELEMENT_FILE_EXTENSION);
		if (!objectFile.exists()) {
			logger.logWarning(
					MessageFormat.format(StringConstants.TO_LOG_WARNING_TEST_OBJ_DOES_NOT_EXIST, testObjectId));
			return null;
		}
		return ObjectRepository.readTestObjectFile(testObjectId, objectFile, alternativeProjectDir, variables);
	}
}
