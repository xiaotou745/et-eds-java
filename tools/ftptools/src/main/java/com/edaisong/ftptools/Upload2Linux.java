package com.edaisong.ftptools;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Upload2Linux {

	private static final Properties prop = new Properties();
    public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();
    	initConfig();
        System.out.println("开始复制文件");
        copyFiles(prop.getProperty("host"), prop.getProperty("port"), prop.getProperty("username"), 
        		prop.getProperty("password"), prop.getProperty("src"),prop.getProperty("dst"));
        //copyFiles("10.8.8.64", 22, "root", "abcd@1234", "D:/test/war","/usr/local/tomcat/test");
        System.out.println("复制完成");
        
		long end = System.currentTimeMillis();
		System.out.println("共耗时:" + ((end - begin) / 1000) + "s");
    }
    
    public static void initConfig() throws Exception{
		InputStream inputStream = Upload2Linux.class.getClassLoader().getResourceAsStream("conf.properties");
		prop.load(inputStream);
		inputStream.close();
    }
    private static List<String> copyFiles(String host, String port, String username, 
    		final String password, String src,String dst) {
        List<String> list = new ArrayList<String>();
        ChannelSftp sftp = null;
        Channel channel = null;
        Session sshSession = null;
        try {
            JSch jsch = new JSch();
            int realPort=Integer.parseInt(port);
            jsch.getSession(username, host,realPort);
            sshSession = jsch.getSession(username, host, realPort);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();

            channel = sshSession.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
            File file = new File(src);    
            upload(sftp,file,dst);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(sshSession);
        }
        return list;
    }
    private static void upload(ChannelSftp sftp,File file,String dst) throws Exception{      
        if(file.isDirectory()){             
            String[] files = file.list();             
            for (int i = 0; i < files.length; i++) {      
                File file1 = new File(file.getPath()+"\\"+files[i] );      
                if(file1.isDirectory()){
                	createDir(sftp,dst+"/"+file1.getName());
                    upload(sftp,file1,dst+"/"+file1.getName());        
                }else if (isNeedUpload(file1.getPath())) { 
                	 System.out.println("复制"+file1.getPath()+"到"+dst);
                	 sftp.put(file1.getPath(), dst, ChannelSftp.OVERWRITE); // 代码段2       
                }                 
            }      
        }else if (isNeedUpload(file.getPath())) { 
        	 System.out.println("复制"+file.getPath()+"到"+dst);
             sftp.put(file.getPath(), dst, ChannelSftp.OVERWRITE); // 代码段2       
        }      
    }  
    private static boolean isNeedUpload(String src) throws Exception{
		String warPath = prop.getProperty("warsrc").replace("/", "\\");
		if (src.startsWith(warPath)) {
			return true;
		}
		String srcPath = prop.getProperty("src").replace("/", "\\");
		String lastpath = prop.getProperty("lastpath").replace("/", "\\");

		File srcFile = new File(src);
		String srcMd5 = MD5.getFileMD5String(srcFile);

		String oldFilePath = src.replace(srcPath, lastpath);
		File oldFile = new File(oldFilePath);
		if (!oldFile.exists()) {// 新增的文件
			return true;
		}
		String oldmd5 = MD5.getFileMD5String(oldFile);
		if (srcMd5.equals(oldmd5)) {
			return false;
		}
		return true;
    }
    private static void createDir(ChannelSftp channelSftp,String dir) throws Exception{
        try{ 
            Vector content = channelSftp.ls(dir); 
            if(content == null) { 
               channelSftp.mkdir(dir); 
            } 
        } catch (SftpException e) { 
            channelSftp.mkdir(dir); 
        } 
    }
    private static void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private static void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
