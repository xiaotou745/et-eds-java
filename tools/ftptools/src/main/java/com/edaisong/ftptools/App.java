package com.edaisong.ftptools;
import org.apache.commons.io.IOUtils;  
import org.apache.commons.net.ftp.FTPClient;  
  


import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.FileOutputStream;  

import org.apache.commons.net.ftp.FTPReply;    


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	App t = new App();    
        t.connect("/usr/local/tomcat/test", "10.8.8.64", 22, "root", "abcd@1234");    
        File file = new File("D:/test/war");    
        t.upload(file); 
    }
    private  FTPClient ftp;   
    private   boolean connect(String path,String addr,int port,String username,String password) throws Exception {      
        boolean result = false;      
        ftp = new FTPClient();      
        int reply;   
        ftp.connect(addr,port);      
        ftp.login(username,password);      
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);      
        reply = ftp.getReplyCode();      
        if (!FTPReply.isPositiveCompletion(reply)) {      
            ftp.disconnect();      
            return result;      
        }      
        ftp.changeWorkingDirectory(path);      
        result = true;      
        return result;      
    }   
    private void upload(File file) throws Exception{      
        if(file.isDirectory()){           
            ftp.makeDirectory(file.getName());                
            ftp.changeWorkingDirectory(file.getName());      
            String[] files = file.list();             
            for (int i = 0; i < files.length; i++) {      
                File file1 = new File(file.getPath()+"\\"+files[i] );      
                if(file1.isDirectory()){      
                    upload(file1);      
                    ftp.changeToParentDirectory();      
                }else{                    
                    File file2 = new File(file.getPath()+"\\"+files[i]);      
                    FileInputStream input = new FileInputStream(file2);      
                    ftp.storeFile(file2.getName(), input);      
                    input.close();                            
                }                 
            }      
        }else{      
            File file2 = new File(file.getPath());      
            FileInputStream input = new FileInputStream(file2);      
            ftp.storeFile(file2.getName(), input);      
            input.close();        
        }      
    }      
}
