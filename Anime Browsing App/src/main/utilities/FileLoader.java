package main.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * 
 * @author Mohann Scarlett mohannscarlett3@gmail.com
 * @version 1/25/2023
 */
public class FileLoader {
	public ArrayList<String[]> csvToArrayList(String path) {
		ArrayList<String[]> list = new ArrayList<>();
		InputStream in = getClass().getResourceAsStream(path);
		int count = 0; //keep track of where in the list new line is added
	    try{
	    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String line = "";
	        //add line from CSV file to specified list index
	        while ((line = br.readLine()) != null) {
	            list.add(count,line.split(",")); 
	            count++;
	        }
	    } catch (IOException e) {
	    	JOptionPane.showMessageDialog(null, e);
	    	}
	    return list;
	}
	public String txtToString(String path) {
		String text = null;
		InputStream in = getClass().getResourceAsStream(path);
		try {
			Scanner scanner;
			scanner = new Scanner(in).useDelimiter("\\A");
			text = scanner.hasNext() ? scanner.next() : "";
			scanner.close();
		}catch(Exception e) {
			
		}
	    return text;
	}
}
 