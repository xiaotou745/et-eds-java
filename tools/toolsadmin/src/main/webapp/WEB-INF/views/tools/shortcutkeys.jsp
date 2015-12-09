<%@page import="com.edaisong.toolsentity.domain.ShortcutKeys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="com.edaisong.toolsadmin.common.ViewModel"%>
<%@page import="com.edaisong.toolsentity.view.ShortcutKeysModel"%>
<%@page import="com.edaisong.toolsentity.domain.ShortcutKeys"%>
<%
	ShortcutKeysModel model = (ShortcutKeysModel)request.getAttribute(ViewModel.KEY_VIEW_DATA);
%>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>记忆优化器</h5>
				<div class="ibox-tools">
                    <a href="#modalKey" data-toggle="modal" data-type="1" class="btn btn-primary btn-xs"><i class="fa fa-check"></i>&nbsp;添加记忆</a>
                </div>
			</div>
			<div class="ibox-content">
				<div class="row">
					<div class="col-sm-3 m-b-xs">
						<select class="input-sm form-control input-s-sm inline" id="selToolsNameQ">
							<%for(String toolName : model.getToolNames()){%>
								<option value="<%=toolName%>"><%=toolName%></option>
							<%} %>
						</select>
					</div>
				</div>
				<hr/>
				<div class="table-responsive" id="contents">
					<jsp:include page="./_shortcutkeyslist.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>

<!--账户对话框-->
<div class="modal fade" id="modalKey" tabindex="-1" role="dialog" aria-labelledby="modalKeyLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalKeyLabel">记忆详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="frmKey">
                    <input type="hidden" value="0" name="id" />
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="toolName">类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="toolName" placeholder="请输入工具"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="title">Key</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="key" placeholder="请输入快捷键"  />
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="control-label col-sm-2">常用</label>
                    	<div class="col-sm-10">
                    	 	<div class="radio i-checks">
                                <label><input type="radio" value="1" name="commonUse" checked="checked"> <i></i> 常用</label>
                                <label><input type="radio" value="0" name="commonUse"> <i></i> 非常用</label>
                            </div>
                    	</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="content">Value</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" name="desc" placeholder="请填写快捷键功能" required></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSave" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
