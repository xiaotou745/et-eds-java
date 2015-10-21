package com.edaisong.mqservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	writePID();
		System.out.println( "开始启动activemq监听器" );
    	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println( "启动完成" );
		shutDown(applicationContext);
    }
	private static void writePID() throws IOException {
		File f = new File("device.pid");
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(f));
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		String pid = processName.substring(0, processName.indexOf("@"));
		writer.write(String.valueOf(pid));
		writer.flush();
		writer.close();
	}
    private static void shutDown(ApplicationContext applicationContext){
    	System.out.println("输入任意字符即可关闭监听器：");  
        BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
        try {
			String str2 = strin.readLine();
			if (str2!=null&&!str2.isEmpty()) {
				System.out.println( "开始关闭activemq监听器" );
	    		DefaultMessageListenerContainer orderService =applicationContext.getBean(DefaultMessageListenerContainer.class);
	    		orderService.shutdown();
	    		System.out.println( "已经关闭" );
			}
		} catch (IOException e) {
			System.out.println( "关闭监听器时出错:"+e.getMessage() );
		}  
        try {
			String str2 = strin.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
