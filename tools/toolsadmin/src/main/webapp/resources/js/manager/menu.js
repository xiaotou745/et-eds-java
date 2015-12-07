$(function() {
	$(".J_Second").hide();
	initCheckBox();
	initFormValidate();

	$("#form1").find("[name=selParentId]").bind("change", function() {
		var parentId = $(this).val();
		if (parentId == 0) {
			$(".J_Second").hide();
			$(".J_First").show();
		} else {
			$(".J_Second").show();
			$(".J_First").hide();
		}
	})
	$("#btnSave").bind("click", function() {
		$("#form1").trigger("submit");
	});
	$('#modalMenus').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the
												// modal
		var modal = $(this);
		initModal(modal, button);
	});
	$("#tblContainer").delegate(".J_Toggle", "click", function() {
		var id = $(this).parents("tr").data("id");
		var currentShow = $(this).data("show");
		if (currentShow) {
			$("[parentid=" + id + "]").hide();
			$(this).data("show", false);
			$(this).find("i").removeClass("fa-minus").addClass("fa-plus");
		} else {
			$("[parentid=" + id + "]").show();
			$(this).data("show", true);
			$(this).find("i").removeClass("fa-plus").addClass("fa-minus");
		}
	});

	$("#tblContainer").delegate(".J_Remove", "click", function() {
		var id = $(this).parents("tr").data("id");
		$.ajax({
			url : window.basePath + "/menu/remove",
			type : "post",
			data : {
				id : id
			},
			dataType : "json",
			success : function(resp) {
				if (!resp.iserror) {
					refresh();
				}
			}
		});
	});
});

function initFormValidate() {
	var rules = {
		txtName : {
			required : true
		},
		txtOrderBy : {
			required : true,
			number : true
		},
		txtIcon : {
			required : true
		},
		txtUrl : {
			required : true
		},
		txtViewPath:{
			required:true
		}
	};
	var messages = {
		txtName : "请输入菜单名称",
		txtOrderBy : "请输入排序数字",
		txtIcon : "父菜单必须指定一个icon",
		txtUrl : "子菜单必须有Url",
		txtViewPath:"viewpath必须填啊，跟选中项有关"
	}
	$("#form1").validate({
		rules : rules,
		messages : messages,
		submitHandler : function(form) {
			save();
		}
	});
}

function refresh() {
	$.get(window.basePath + "/menu/refresh", null, function(resp) {
		$("#tblContainer").html(resp);
	});
}

function save() {
	var menu = getModel();
	$.ajax({
		url : window.basePath + "/menu/save",
		type : "post",
		data : menu,
		dataType : "text",
		success : function(resp) {
			if (!resp.iserror) {
				refresh();
				$("#modalMenus").modal("hide");
			}
		}
	});
}

function getModel() {

	var form = $("#modalMenus").find("form");
	var menu = {
		id : form.find("[name=Id]").val(),
		name : form.find("[name=txtName]").val(),
		parentId : form.find("[name=selParentId]").val(),
		url : form.find("[name=txtUrl]").val(),
		icon : form.find("[name=txtIcon]").val(),
		orderBy : form.find("[name=txtOrderBy]").val(),
		viewPath:form.find("[name=txtViewPath]").val(),
		openNewWindow : form.find("[name=chkOpenNewWindow]").is(":checked")
	};
	if (menu.parentId == 0) {// 一级菜单
		menu.url = "";
		menu.viewPath="";
		menu.openNewWindow = false;
	} else {
		menu.icon = "";
	}
	return menu;
}

function initModal(modal, button) {
	var operateType = button.data("type");

	if (operateType == 1) { // 新增
		modal.find("[name=Id]").val(0);
		modal.find(":text").val("");
		modal.find("[name=selParentId]").val(
		modal.find("[name=selParentId] option:first").val());
	} else if (operateType == 2) { // 编辑
		var $tr = button.parents("tr");
		modal.find("[name=Id]").val($tr.data("id"));
		modal.find("[name=txtName]").val($tr.find("[name=name]").text());
		modal.find("[name=selParentId]").val($tr.data("parentid"));
		modal.find("[name=selParentId]").trigger("change");
		modal.find("[name=txtOrderBy]").val($tr.data("orderby"));
		modal.find("[name=txtUrl]").val($tr.find("[name=url]").text());
		modal.find("[name=txtViewPath]").val($tr.data("viewpath"));
		modal.find("[name=txtIcon]").val($tr.find("[name=icon]").text());

		var openNewWindow = $tr.data("opennewwindow");
		if (openNewWindow) {
			modal.find("[name=chkOpenNewWindow]").iCheck("check");
		} else {
			modal.find("[name=chkOpenNewWindow]").iCheck("uncheck");
		}
	}
}