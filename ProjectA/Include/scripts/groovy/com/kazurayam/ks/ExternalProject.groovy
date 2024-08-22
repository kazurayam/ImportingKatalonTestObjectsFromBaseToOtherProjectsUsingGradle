package com.kazurayam.ks

public enum ExternalProject {

	Base("../BaseProject"),
	ProjectA("../ProjectA"),
	ProjectB("../ProjectB");

	private File projectDir

	private ExternalProject(String relativePath) {
		projectDir = new File(relativePath)
	}
	File getProjectDir() {
		return projectDir
	}
}
