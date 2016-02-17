<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
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
            <div class="step-item" data-type="1">
                <div class="logo"></div>
                <div class="title">商家中心</div>
            </div>
            <div class="step-item" data-type="2">
                <div class="logo"></div>
                <div class="title">智能调度</div>
            </div>
            <div class="step-item" data-type="3">
                <div class="logo"></div>
                <div class="title">管理后台</div>
            </div>
        </div>
    </div>
    <div class="timeline">
        <div class="timeline_split"></div>
        <div class="year">
            <div class="point"></div>
            2016年
        </div>
        <div class="time_point t1">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2016/01/08
                    <img src="<%=basePath%>/img/iapple.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>商家中心</div>
                        <ul>
                            <li>1、UPDATE dbo.LineHistory SET IsEnable=1</li>
                            <li>2、UPDATE dbo.LineHistory SET IsEnable=1；</li>
                            <li>3、UPDATE dbo.LineHistory SET IsEnable=1</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point -->
        <div class="time_point t2 odd">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2016/01/05
                    <img src="<%=basePath%>/img/iandroid.jpg" alt="">
                </div>
                <div class="des">
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>智能调度</div>
                        <ul>
                            <li>1、智能调度</li>
                            <li>2、智能调度</li>
                            <li>3、智能调度</li>
                        </ul>
                    </div>
                    <div class="arrow"></div>
                </div>
            </div>
        </div>
        <!-- point end-->
        <div class="year">
            <div class="point"></div>
            2015年
        </div>
        <!-- point -->
        <div class="time_point t3 ">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/12/26
                    <img src="<%=basePath%>/img/isetting.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>管理后台</span></div>
                        <ul>
                            <li>1、管理后台</li>
                            <li>2、管理后台；</li>
                            <li>3、管理后台；</li>
                            <li>4、管理后台。</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
        <!-- point -->
        <div class="time_point t1 odd">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/12/18
                    <img src="<%=basePath%>/img/iandroid.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>商家中心</div>
                        <ul>
                            <li>1、商家中心；</li>
                            <li>2、商家中心；</li>
                            <li>3、商家中心；</li>
                            <li>4、商家中心。</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
        <!-- point -->
        <div class="time_point t1">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/12/08
                    <img src="<%=basePath%>/img/iapple.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>商家中心</div>
                        <ul>
                            <li>1、商家中心；</li>
                            <li>2、商家中心；</li>
                            <li>3、商家中心；</li>
                            <li>4、商家中心。</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
        <!-- point -->
        <div class="time_point t2">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/11/18
                    <img src="<%=basePath%>/img/iandroid.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>智能调度</div>
                        <ul>
                            <li>智能调度 </li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
		<!-- point -->
        <div class="time_point t2 odd">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/11/18
                    <img src="<%=basePath%>/img/iandroid.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>智能调度</div>
                        <ul>
                            <li>智能调度 </li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
		<!-- point -->
        <div class="time_point t2">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/11/18
                    <img src="<%=basePath%>/img/iandroid.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>智能调度</div>
                        <ul>
                            <li>智能智能 </li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
		<!-- point -->
        <div class="time_point t2 odd">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/11/18
                    <img src="<%=basePath%>/img/iandroid.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>智能调度</div>
                        <ul>
                            <li>智能调度 </li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
		<!-- point -->
        <div class="time_point t2">
            <div class="time_point_wrap">
                <img src="<%=basePath%>/img/point.jpg" alt="">
                <div class="time">
                    2015/11/18
                    <img src="<%=basePath%>/img/iandroid.jpg" alt="">
                </div>
                <div class="des">
                    <div class="arrow"></div>
                    <div class="des_wrap">
                        <div class="title"><span>E代送</span>智能调度</div>
                        <ul>
                            <li>智能调度 </li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                            <li>智能调度</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- point end-->
        <div class="year start" style="margin-top:20px;margin-bottom:0;">
            <div class="point"></div>
            START
        </div>
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