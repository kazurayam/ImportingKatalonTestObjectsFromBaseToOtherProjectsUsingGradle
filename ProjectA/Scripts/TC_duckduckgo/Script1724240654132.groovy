import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://duckduckgo.com/')

WebUI.setText(findTestObject('Object Repository/Page_DuckDuckGo  Privacy, simplified/input_q'), 'katalon')

