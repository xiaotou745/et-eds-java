$(function() {
	initCheckBox();
	$("#frmKey").validate({
		rules : {
			key : {
				required : true
			},
			toolName : {
				required : true
			},
			desc : {
				required : true
			}
		},
		messages : {
			key : "请输入快捷键",
			toolName : "请输入工具",
			desc : "请输入快捷键功能描述"
		},
		submitHandler : function(form) {
			save();
		}
	});

	$("#btnSave").bind("click", function() {
		$("#frmKey").trigger("submit");
	});

	$("#contents").delegate(".J_Remove", "click", function() {
		if (confirm("确定要删除吗？不可恢复之操作.")) {
			var id = $(this).parents("tr").data("id");
			remove(id);
		}
	})

	$('#modalKey').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the
		var modal = $(this);
		initModal(modal, button);
	});

	$("#selToolsNameQ").bind("change", function() {
		refresh();
	})
})

function initModal(modal, button) {
	var operateType = button.data("type");

	if (operateType == 1) { // 新增
		modal.find("[name=id]").val(0);
		modal.find("[name=desc]").val("");
		modal.find(":text").val("");
	} else if (operateType == 2) { // 编辑
		var id = button.parents("tr").data("id");
		modal.find("[name=id]").val(id);
		$.get(window.basePath + "/shortcuts/get/" + id, null, function(resp) {
			if (!resp.iserror) {
				modal.find("[name=key]").val(resp.data.key);
				modal.find("[name=toolName]").val(resp.data.toolsName);
				modal.find("[name=desc]").val(resp.data.desc);
				modal.find("[name=commonUse]").each(function(i, item) {
					if ($(item).val() == resp.data.isCommonUse) {
						$(item).iCheck("check");
					}
				})
			}
		})
	}
}

function save() {
	var $form = $("#frmKey");
	var key = {
		id : $form.find("[name=id]").val(),
		key : $form.find("[name=key]").val(),
		desc : $form.find("[name=desc]").val(),
		toolsName : $form.find("[name=toolName]").val()
	};
	$form.find("[name=commonUse]").each(function(i, item) {
		if ($(item).is(":checked")) {
			if ($(item).val() == 1) {
				key.isCommonUse = true;
			} else {
				key.isCommonUse = false;
			}
		}
	});
	$.ajax({
		url : window.basePath + "/shortcuts/save",
		type : "post",
		dataType : "json",
		data : key,
		success : function(resp) {
			console.log(resp);
			if (!resp.iserror) {
				refresh();
				$("#modalKey").modal("hide");
			}
		}
	});
}

function refresh() {
	var queryReqs = {
		toolsName : $("#selToolsNameQ").val(),
		keyword : ""
	};
	$.ajax({
		url : window.basePath + "/shortcuts/refresh",
		type : "get",
		data : queryReqs,
		success : function(resp) {
			$("#contents").html(resp);
		}
	});
}

function remove(id) {
	$.ajax({
		url : window.basePath + "/shortcuts/remove",
		type : "get",
		dataType : "json",
		data : {
			id : id
		},
		success : function(resp) {
			if (!resp.iserror) {
				refresh();
			}
		}
	});
}