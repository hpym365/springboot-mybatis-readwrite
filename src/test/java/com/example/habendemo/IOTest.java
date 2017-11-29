package com.example.habendemo;

import java.io.*;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-15 11:58
 * @Version: 1.0
 **/
public class IOTest {

	public static void main(String[] args) throws IOException {

		String path1 = "/Users/hpym365/ftp/test/fir.txt";
		String path2 = "/Users/hpym365/ftp/test/sec.txt";

//		write(path1,false);
//		write(path2,true);


		FileInputStream fileInputStream = new FileInputStream(new File(path2));
		int read = fileInputStream.read();
		byte[] bytes = new byte[100];

		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line;
		LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);

		lineNumberReader.setLineNumber(3);


		while((line=br.readLine())!=null){
			System.out.println(line);
		}
//		int read1 = fileInputStream.read(bytes);
//		System.out.println(bytes.toString());
//		System.out.println(new String(bytes));
		System.out.println(read);
	}

	public static void write(String path, Boolean random) {
		File file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str = "哇哈哈啊1";

		for (int i = 0; i < 10000; i++) {
			String w = i + ",张三,23\n";
			if (random && Math.random() > 0.001) {
				continue;
			}
			try {
				outputStream.write(w.getBytes());
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		outputStream.flush();
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
