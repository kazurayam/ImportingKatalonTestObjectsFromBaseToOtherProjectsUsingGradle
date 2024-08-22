package com.kazurayam.ks

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Files
import java.text.MessageFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.constants.StringConstants
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject

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
	public static TestObject findTestObject(File alternativeProjectDir, 
											String testObjectRelativeId) {
		return findTestObject(alternativeProjectDir, testObjectRelativeId, new HashMap<String, Object>())
	}

	@Keyword
	public static TestObject findTestObject(File alternativeProjectDir, 
											String testObjectRelativeId, 
											Map<String, Object> variables) {
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
		return ObjectRepository.readTestObjectFile(testObjectId, objectFile, alternativeProjectDir.toString(), variables);
	}
}
