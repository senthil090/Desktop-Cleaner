import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.*;
public class DesktopCleaner {

	public static void main(String[] args) throws IOException {
		
		String desktopLocation = System.getProperty("user.home") + "/Desktop";
		String downloadLocation = System.getProperty("user.home") + "/Downloads";
		String donwloadMoveLocation;
		
		File fileDirectory = new File(desktopLocation);
		File DownloadDirectory = new File(downloadLocation);
		File downloadDestDirectory;
		System.out.println("File Directory "+fileDirectory.toString());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String folderName = dateFormat.format(date);
		File destDirectory = new File("D:\\DesktopBackups\\"+folderName);
		if(!destDirectory.exists()){
			if(destDirectory.mkdirs()){
				System.out.println("DirectoryCreated "+destDirectory.getName());
			}
		}
		System.out.println("DestDirectory "+destDirectory.toString());
		if(fileDirectory.isDirectory()){
			File[] content = fileDirectory.listFiles();
			for(int i=0;i<content.length;i++){
				if(content[i].isFile()){
					System.out.println("Content"+content[i].getName());
					FileUtils.moveFileToDirectory(content[i], destDirectory, false);
				}else if(content[i].isDirectory()){
					System.out.println("Content"+content[i].getName());
					FileUtils.moveDirectoryToDirectory(content[i], destDirectory, false);
				}
			}
		}
		
		if(DownloadDirectory.isDirectory()){
			File[] downloadContent = DownloadDirectory.listFiles();
			for(int i=0;i<downloadContent.length;i++){
				if(downloadContent[i].isFile()){
					donwloadMoveLocation = FilenameUtils.getExtension(downloadContent[i].getAbsolutePath());
					System.out.println("Download Extension"+donwloadMoveLocation);
					if(donwloadMoveLocation != "")
					{
						downloadDestDirectory = new File(downloadLocation+"/"+donwloadMoveLocation);
						FileUtils.moveFileToDirectory(downloadContent[i], downloadDestDirectory, true);
					}else{
						System.out.println("Unextensive file found"+downloadContent[i].getName());
					}
				}
			}
		}
		
		
	}

}
