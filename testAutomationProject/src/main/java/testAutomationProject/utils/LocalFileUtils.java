package testAutomationProject.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalFileUtils {

	
	public static List<String> readCsvFile(String filePath) {
		List<String> csvData = new ArrayList<String>();
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				if(line != null && !line.isEmpty()) {
					csvData.add(line);
				}
			}
		
			br.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return csvData;
	}
	
	
	public static Path copyFile(String srcFile, String destinationFile) throws IOException {
		
        Path srcFilePath = Paths.get(srcFile);              
        Path destFilePath = Paths.get(destinationFile);

        //Copy file at destination
        
        // System.out.println(destFilePath.toString());

        return Files.copy(srcFilePath, destFilePath);
	}
	
}
