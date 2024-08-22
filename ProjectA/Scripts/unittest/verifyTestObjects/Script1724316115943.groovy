import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
println "projectDir=" + projectDir.toString()
assert Files.exists(projectDir)

TestObject tObj = findTestObject("Object Repository/Page_DuckDuckGo  Privacy, simplified/input_q")
assert tObj != null