<%@ page contentType="image/jpeg" language="java" import="java.util.*,java.awt.*,java.awt.image.*,javax.imageio.*" pageEncoding="utf-8"%>  
  
<%!  
    Color getRandColor(int fc,int bc){  
        Random random = new Random();  
        if(fc > 255){  
            fc = 255;  
        }  
        if(bc < 255){  
            bc = 255;  
        }  
        int r = fc +random.nextInt(bc-fc);  
        int g = fc +random.nextInt(bc-fc);  
        int b = fc +random.nextInt(bc-fc);  
          
          
        return new Color(r,g,b);  
    }  
%>  
  
<%   
    //设置页面不缓存  
    response.setHeader("Pragma","no-cache");  
    response.setHeader("Cache-Control","no-catch");  
    response.setDateHeader("Expires",0);  
      
    //在内存中创建图象  
    int width = 106;  
    int height = 43;  
    int lineCount = 5;
    int codeCount = 4;
    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
    int fontWidth = width / (codeCount +2);
    int fontHeight = height - 10;
    //创建图象  
    Graphics g = image.getGraphics();  
    //生成随机对象  
    Random random = new Random();  
    //设置背景色  
    g.setColor(getRandColor(200,250));  
    g.fillRect(0,0,width,height);  
    //设置字体  
    g.setFont(new Font("Tines Nev Roman",Font.PLAIN,35));  
    //随机产生干扰线  
    g.setColor(getRandColor(160,200));  
    for(int i = 0; i < lineCount; i++){  
/*         int x = random.nextInt(width);  
        int y = random.nextInt(height);  
        int xl = random.nextInt(12);  
        int yl = random.nextInt(12);  
        g.drawLine(x, y, xl, yl); */
    	int xs = random.nextInt(width);  
        int ys = random.nextInt(height);  
        int xe = xs+random.nextInt(width/8);  
        int ye = ys+random.nextInt(height/8);  
        int red = random.nextInt(255);  
        int green = random.nextInt(255);  
        int blue = random.nextInt(255);  
        g.setColor(new Color(red, green, blue));  
        g.drawLine(xs, ys, xe, ye); 
    }  
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',  
            'K', 'L', 'M', 'N',  'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' }; 
    //随机产生认证码,4位数字  
    String sRand = "";  
    for(int i = 0; i < codeCount; i++){  
        String rand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);//String.valueOf(random.nextInt(10));  
        sRand  += rand;  
        //将认证码显示到图象中  
        g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));  
        g.drawString(rand,(i + 1) * fontWidth,fontHeight);  
    }  
    System.out.println(sRand);
    session.setAttribute("code",sRand);  
    //图像生效  
    g.dispose();  
    //输出图像到页面  
    ImageIO.write(image,"JPEG",response.getOutputStream());  
    out.clear();  
    out = pageContext.pushBody();  
%>  