<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.domain.LineHistoryModel"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.enums.DevPlatformType"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
	List<LineHistoryModel> list= (List<LineHistoryModel>) request.getAttribute("list");
	List<String> yearlist= (List<String>) request.getAttribute("yearlist");
	List<DevPlatformType> typeList= (List<DevPlatformType>) request.getAttribute("typeList");
%>
<!DOCTYPE html>
<html>

<head>
    <title></title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <script type="text/javascript" src="<%=basePath%>/js/sylvester.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/cssSandpaper.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/index.css" />

</head>

<body>
    <div class="topbar">
        <div class="title">
            <img src="<%=basePath%>/img/mtitle.png" alt="">
        </div>
    </div>
    <div class="step">
        <div class="setp-wrap">
            <div class="setp-line"></div>
            <div class="step-item active" data-type="all">
                <div class="logo"></div>
                <div class="title">全部</div>
            </div>
            <% for(int i=0;i<typeList.size();i++) 
            {
            	%>
            	<div class="step-item" data-type="<%=typeList.get(i).value()%>">
               		<div class="logo"></div>
                	<div class="title"><%=typeList.get(i).desc()%></div>
            	</div>
            	<% 
            }%>
            
        </div>
    </div>
    <div class="timeline">
        <div class="timeline_split"></div>
        <%
        for(int i=0;i<yearlist.size();i++)
        {
        	%>
        	  <div class="year">
            	<div class="point"></div>
            	<%=yearlist.get(i)%>年
        	  </div>
        	  
        	<%
        	for(int j=0;j<list.size();j++)
        	{
        		if(list.get(j).getOnLineTime().contains(yearlist.get(i)))
        		{
        			%>
        			 <div class="time_point t<%=list.get(j).getDevPlatformCode()%> <%=j%2==0?"odd":""%>">
            		<div class="time_point_wrap">
                	<img src="<%=basePath%>/img/point.jpg" alt="">
               		<div class="time">
                    <%=list.get(j).getOnLineTime()%>
                    <img src="<%=basePath%>/img/iapple.jpg" alt="">
                	</div>
                	<div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span><%=list.get(j).getDevPlatform()%></span><%=list.get(j).getOnLineProduct()%></div>
                        <ul>
                        <% 
                        String[] stra=list.get(j).getOnLineContent().split("@");
                        for(String s:stra)
                        {%>
                        <li><%=s%></li>
                        <%}%>
                        </ul>
                    </div>
                	</div>
            		</div>
        			</div>
        			<%
        		}
        	}
        }
        
        if(list.size()>0)
        {
        	%>
        	  <div class="year start" style="margin-top:20px;margin-bottom:0;">
            <div class="point"></div>
            START
        </div>
        	<% 
        }
        %>
        
      
        <div style="height:40px;background:#fff;"></div>
    </div>
    <script src="<%=basePath%>/js/jquery.1.2.6.js"></script>
    <script>
	    
        function odd(list) {
		    $('.year:not(.start)').hide()//隐藏年标记
			var yearnode=$('.year');//年标记
            list.show().each(function(idx, item) {
            $(item).show().toggleClass("odd", idx % 2 == 1);
			   //该节点的年份
			   var year=$.trim($(item).find('.time').html().split('/')[0]);
			    for(var i=0;i<yearnode.length;i++)
				{  
					if($(yearnode[i]).html().indexOf(year)>0)
					{
						$(yearnode[i]).show();
					}
				}
            });
        }
        $('.step-item').bind('click', function() {
            $('.active').removeClass('active');
            var t = $(this).addClass('active').attr('data-type');
            if (t == 'all') {
                odd($(".time_point"));
                return;
            }
            $('.time_point:not(.t' + t + ')').hide();
            odd($(".t" + t));
        });
    </script>
</body>

</html>