package com.hetty.util;

import java.io.File;

public class FileUtil {

	public static File getFile(String path) {
		String applicationPath = FileUtil.class.getClassLoader().getResource("").getPath();
		return new File(applicationPath, path);
	}
}
