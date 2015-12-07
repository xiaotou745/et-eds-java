<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Set" %>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.domain.Menu" %>
<%@page import="com.edaisong.toolsentity.domain.Role" %>
<%
	Set<String> keys = (Set<String>) request.getAttribute("dataOfKeys");
	String suffxKey = request.getAttribute("dataOfSuffxKey").toString();
%>
<div class="row">
    <div class="col-lg-12">
    	<div class="ibox">
    		<div class="ibox-title">
    			<h3>缓存列表</h3>
    		</div>
    		<div class="ibox-content">
    			<div class="row m-b-sm m-t-sm">
                    <div class="col-md-1">
                        <button type="button" id="btnRefresh" class="btn btn-white btn-sm"><i class="fa fa-refresh"></i> Refresh</button>
                    </div>
                    <div class="col-md-11">
                        <div class="input-group">
                        	<input type="text" id="txtQuery" placeholder="Search" class="input-sm form-control">
                        	<span class="input-group-btn"><button id="btnSearch" type="button" class="btn btn-sm btn-primary"> Go!</button> 
                        	</span>
                        </div>
                    </div>
                </div>
                <div id="contents">
                	<jsp:include page="./_redislist.jsp"></jsp:include>
                </div>
    		</div>
    	</div>
    </div>
</div>
<div id="modalRedisValue" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="lblRedisValue">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="lblRedisValue">缓存值</h4>
            </div>
            <div class="modal-body">
                <p id="lblValue"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>