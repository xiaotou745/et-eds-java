<%@page import="com.edaisong.admin.common.LoginUtil"%>
<%@page import="com.edaisong.api.common.LoginHelper"%>
<%@ page contentType="image/jpeg" language="java" import="java.util.*,java.awt.*,java.awt.image.*,javax.imageio.*" pageEncoding="utf-8"%>  
<%@ page import="com.edaisong.core.util.RandomCodeImgHelper" %>
<%@ page import="com.edaisong.core.util.RandomCodeStrGenerator" %>
<%
	//设置页面不缓存  
    response.setHeader("Pragma","no-cache");  
    response.setHeader("Cache-Control","no-catch");  
    response.setDateHeader("Expires",0);  
      
    String sRand = RandomCodeStrGenerator.generateCode(4);
    BufferedImage image = RandomCodeImgHelper.getCodeImg(sRand);
    LoginHelper.storeAuthCode2Redis("admin",sRand,LoginUtil.ADMIN_JSESSIONID, request, response);

    //输出图像到页面  
    ImageIO.write(image,"JPEG",response.getOutputStream());  
    out.clear();  
    out = pageContext.pushBody();
%>