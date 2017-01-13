package examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV { // be careful: this is not object oriented

	public static void main(String[] args) {
		String fileName = "office.csv";
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tokens = line.split(",");
				for(String token: tokens)
					System.out.print(token + ", ");
				System.out.println();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done");
	}

}
