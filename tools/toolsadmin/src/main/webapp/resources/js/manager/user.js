$(function(){
	initCheckBox();
	$("#tblContainer").delegate(".J_Remove", "click", function() {
        if (!confirm("确定要删除此用户吗?此操作不可恢复.")) {
            return;
        }
        var id = $(this).parents("tr").data("id");
        var $this = $(this);
        $.ajax({
            url: window.basePath + "/user/remove",
            type: "post",
            data: { userId: id },
            dataType: "json",
            success: function(resp) {
                if (!resp.iserror) {
                    $this.parents("tr").remove();
                }
            }
        });
    });
    $("#tblContainer").delegate(".J_Disable", "click", function() {
    	var $this = $(this);
        if (!confirm("确定要" + $this.text() + "此用户吗?此操作不可恢复.")) {
            return;
        }
        var id = $this.parents("tr").data("id");
        var currentDisable = $this.data("disable");
        var targetDisable = currentDisable ? false : true;
        $.ajax({
            url: window.basePath + "/user/disable",
            type: "post",
            data: { userId: id, isDisable: targetDisable },
            dataType: "json",
            success: function(resp) {
                if (!resp.iserror) {
                    $this.data("disable", targetDisable);
                    $this.html(targetDisable ? "解禁" : "禁用");
        			$this.parents("tr").find("[name=disable]").text(targetDisable?"√":"");
                }
            }
        });
    });
    $('#modalRoles').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var modal = $(this);
        setRoles(modal, button);
    });
    
	$('#modalUsers').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var modal = $(this);
		setUserModal(modal, button);
	});
	
	$("#btnSaveUser").bind("click", function(){
		saveUser();
	});
	$("#btnSaveRole").bind("click", saveRoles);
});
function saveRoles() {
    var arrRoleIds = [];
    $("#modalRoles").find(":checkbox").each(function(i, item) {
        if ($(item).is(':checked')) {
            arrRoleIds.push($(item).val());
        }
    });
    var params= {
        userId: $("#modalRoles").find("[name=Id]").val(),
        roleIds:arrRoleIds.join(",")
    };
    $.ajax({
        url: window.basePath + "/user/setrole",
        type: "post",
        data: params,
        dataType: "json",
        success: function(resp) {
            if (!resp.iserror) {
                refresh();
                $("#modalRoles").modal("hide");
            }
        }
    });
}
function setRoles(modal, button) {
    var $tr = button.parents("tr");
    modal.find("[name=Id]").val($tr.data("id"));
    var roleIds = $tr.data("roles").toString();
    if (roleIds == undefined) {
        roleIds = "";
    }
    var arrRoleIds = roleIds.split(",").delete("");
    modal.find(":checkbox").each(function(i, item) {
        if (arrRoleIds.contain($(item).val())) {
            $(item).iCheck("check");
        }else{
        	$(item).iCheck("uncheck");
        }
    });
}

function saveUser() {
    var user = getUserModel();
    $.ajax({
        url: window.basePath+"/user/save",
        type: "post",
        data: user,
        dataType: "json",
        success: function(resp) {
            if (!resp.iserror) {
                refresh();
                $("#modalUsers").modal("hide");
            }
        }
    });
}
function getUserModel() {
    var form = $("#modalUsers").find("form");
    var user = {
        id: form.find("[name=Id]").val(),
        loginName: form.find("[name=txtLoginName]").val(),
        loginPwd: form.find("[name=txtLoginPwd]").val(),
        userName: form.find("[name=txtUserName]").val()
    };
    return user;
}
function setUserModal(modal, button) {
    var operateType = button.data("type");

    if (operateType == 1) { //新增
        modal.find("[name=Id]").val(0);
        modal.find(":text").val("");
    } else if (operateType == 2) { //编辑
        var $tr = button.parents("tr");
        modal.find("[name=Id]").val($tr.data("id"));
        modal.find("[name=txtLoginName]").val($tr.find("[name=loginname]").text());
        modal.find("[name=txtUserName]").val($tr.find("[name=username]").text());
        modal.find("[name=txtLoginPwd]").val($tr.data("password"));
    }
}
function refresh() {
    $.ajax({
        url: window.basePath+"/user/refresh",
        type: "get",
        success: function(resp) {
            $("#tblContainer").html(resp);
        }
    });
}