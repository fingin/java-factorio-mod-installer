package fingin;

import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.lingala.zip4j.core.ZipFile;


public class Main {
	private static String workingDirectory;
	//here, we assign the name of the OS, according to Java, to a variable...
	private static String OS = (System.getProperty("os.name")).toUpperCase();
	public static void main(String args[]){
		//to determine what the workingDirectory is.
		//if it is some version of Windows
		if (OS.contains("WIN"))
		{
		    //it is simply the location of the "AppData" folder
		    workingDirectory = System.getenv("AppData");
		}
		//Otherwise, we assume Linux or Mac
		else
		{
		    //in either case, we would start in the user's home directory
		    workingDirectory = System.getProperty("user.home");
		    //if we are on a Mac, we are not done, we look for "Application Support"
		    workingDirectory += "/Library/Application Support";
		}
		workingDirectory += "/factorio";
		
			File d = new File(workingDirectory + "/mods");
			String Date = dateFormat.format(date);
			if (d.exists() && d.isDirectory()) {
				System.out.print("backing up current mods folder...");
				Path D = Paths.get(workingDirectory + "/mods");
				Path H = Paths.get(workingDirectory + "/oldmods" + "/" + Date);
				File uu = new File(workingDirectory + "/oldmods");
				
				if(!uu.exists()){
					uu.mkdir();
				}
				try {
					Files.move(D,H);
					System.out.print("done");
				} catch (IOException e) {
					System.out.print("backup failed error:");
					e.printStackTrace();
					System.out.print("skiping backup...");
				}
			}
				
				
				
				
			
			
			if(args[1] == "1"){
			unzip(args[0],workingDirectory);
			System.out.print("zip root directory placed in /factorio folder");
			}else{
				if(args[1] == "0"){
					unzip(args[0],workingDirectory + "/mods");
					System.out.print("zip root directory placed in /factorio/mods folder");
				}else{
					System.out.print("mods folder removed and backed up in:" + " " + workingDirectory + "/oldmods" + Date );
				}
			}
			System.out.print("finished, please launch game");
			
			
		}
		
	
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-ss");
	static Date date = new Date();
	
	
	public static String unzip(String source,String destination){
		   

	    try {
	         ZipFile zipFile = new ZipFile(source);
	         if (zipFile.isEncrypted()) {
	            return "error";
	         }
	         zipFile.extractAll(destination);
	         return "done";
	    } catch (ZipException e) {
	        e.printStackTrace();
	        return "error";
	        }
	    }
	
	public static String unzip(String source,String destination ,String password){
	   

	    try {
	         ZipFile zipFile = new ZipFile(source);
	         if (zipFile.isEncrypted()) {
	            zipFile.setPassword(password);
	         }
	         zipFile.extractAll(destination);
	         return "done";
	    } catch (ZipException e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
}
