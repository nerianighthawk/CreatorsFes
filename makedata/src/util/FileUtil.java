package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileUtil {
	public static BufferedReader newReader(String path) throws IOException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
	}

	public static PrintWriter newWriter(String path) throws IOException {
		return new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
	}

	public static void mkdir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
}
