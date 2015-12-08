$(function() {
	initCheckBox();
	dateformat($(".datetimepicker"));
	$('.input-daterange').datepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		language : "zh-CN"
	});
	$("#frmTask").validate({
		rules : {
			title : {
				required : true
			},
			content : {
				required : true
			}
		},
		messages : {
			title : "请输入任务标题",
			content : "请输入任务内容"
		},
		submitHandler : function(form) {
			save();
		}
	});

	$("#frmTaskStatus").validate({
		rules : {
			startTime : {
				required : true
			},
			taskTime : {
				required : true,
				digits : true
			}
		},
		messages : {
			startTime : "请输入任务开始时间",
			taskTime : {
				required : "请输入任务工时",
				digits : "任务工时应该为整数"
			}
		},
		submitHandler : function(form) {
			saveStatus();
		}
	});

	$(document).find("[name=typeq]").on("ifChecked", function() {
		refresh();
	})
	$("#btnQuery").bind("click", refresh);

	$('#modalTask').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the
		var modal = $(this);
		initModal(modal, button);
	});
	$('#modalTaskStatus').on('hide.bs.modal', function(event) {
		refresh();
	});

	$("#btnSave").bind("click", function() {
		$("#frmTask").trigger("submit");
	});

	$("#btnSaveStatus").bind("click", function() {
		$("#frmTaskStatus").trigger("submit");
	})

	initSortable();

	$("#jobs").delegate(".J_Hide", "click", function() {
		var id = $(this).parents("li").data("id");
		if (confirm("确定要隐藏此任务吗？")) {
			Hide(id);
		}
	});

	$("#jobs").delegate(".J_Remove", "click", function() {
		var id = $(this).parents("li").data("id");
		if (confirm("确定要删除此任务吗？")) {
			Remove(id);
		}
	});
});

function initModal(modal, button) {
	var recipient = button.data('whatever');
	modal.find('.modal-title').text(recipient);

	var operateType = button.data("type");

	if (operateType == 1) { // 新增
		modal.find("[name=id]").val(0);
		modal.find("[name=title]").val("");
		modal.find("[name=content]").val("");
		modal.find("[name=selPriorityLevel]").val(
				modal.find("[name=selPriorityLevel]").find("option:first")
						.val());
	} else if (operateType == 2) { // 编辑
		var $li = button.parents("li");
		var id = $li.data("id");
		modal.find("[name=id]").val(id);
		$.get(window.basePath + "/tools/tasks/getbyid", {
			id : id
		}, function(resp) {
			if (!resp.iserror) {
				modal.find("[name=title]").val(resp.data.title);
				modal.find("[name=content]").val(resp.data.content);
				modal.find("[name=selPriorityLevel]").val(
						resp.data.priorityLevel);
				modal.find("[name=type]").each(function(i, item) {
					if ($(item).val() == resp.data.type) {
						$(item).iCheck("check");
					}
				})
			}
		})
	}
}

function initSortable() {
	$(".sortable-list").sortable({
		connectWith : ".connectList",
		stop : function(event, ui) {
			// changeStatus(event, ui);
		},
		receive : function(event, ui) {
			changeStatus(event, ui);
		}
	}).disableSelection();
}

function changeStatus(event, ui) {
	var targetStatus = $(ui.item[0]).parent().data("status");
	var currentStatus = $(ui.item[0]).data("status");
	var currentId = $(ui.item[0]).data("id");
	var currentType = $(ui.item[0]).data("type");
	if (currentStatus == targetStatus) {
		return;
	}
	if (currentStatus == 1 && targetStatus == 2) {// 如果目标状态为进行中
		$("#modalTaskStatus").find("[name=id]").val(currentId);
		$("#modalTaskStatus").find("[name=status]").val(targetStatus);
		var $selWho = $("#modalTaskStatus").find("[name=selWho]");
		if (currentType == 1) {
			$selWho.val($("#hidUserId").val());
			$selWho.attr("disabled", "disabled");
		} else {
			$selWho.val($selWho.find("option:first").val());
			$selWho.removeAttr("disabled");
		}
		$("#modalTaskStatus").modal("show");
		return;
	} else {
		saveStatus(currentId, targetStatus);
	}
}

function save() {
	var $form = $("#frmTask");
	var task = {
		id : $form.find("[name=id]").val(),
		title : $form.find("[name=title]").val(),
		priorityLevel : $form.find("[name=selPriorityLevel]").val(),
		content : $form.find("[name=content]").val()
	};
	$form.find("[name=type]").each(function(i, item) {
		if ($(item).is(":checked")) {
			task.type = $(item).val();
		}
	});
	$.ajax({
		url : window.basePath + "/tools/tasks/save",
		type : "post",
		dataType : "json",
		data : task,
		success : function(resp) {
			if (!resp.iserror) {
				refresh();
				$("#modalTask").modal("hide");
			}
		}
	});
}

function saveStatus(id, status) {
	var $form = $("#frmTaskStatus");
	if (!id) {
		id = $form.find("[name=id]").val();
	}
	if (!status) {
		status = $form.find("[name=status]").val();
	}
	var params = {
		taskId : id,
		whoId : $form.find("[name=selWho]").val(),
		targetStatus : status,
		startTime : $form.find("[name=startTime]").val(),
		taskTime : $form.find("[name=taskTime]").val()
	};
	$.ajax({
		url : window.basePath + "/tools/tasks/changeStatus",
		type : "post",
		dataType : "json",
		data : params,
		success : function(resp) {
			if (resp.iserror) {
				alert(resp.message);
				$(".sortable-list").sortable("cancel");
				return;
			}
			$("#modalTaskStatus").modal("hide");
		}
	});
}

function refresh() {
	var type = "";
	$(document).find("[name=typeq]").each(function(i, item) {
		if ($(item).is(":checked")) {
			type = $(item).val();
		}
	})
	var queryReqs = {
		type : type,
		timeType : $("#selTimeType").val(),
		startTime : $("#start").val(),
		endTime : $("#end").val()
	};
	$.ajax({
		url : window.basePath + "/tools/tasks/refresh",
		type : "get",
		data : queryReqs,
		success : function(resp) {
			$("#jobs").html(resp);
			initSortable();
		}
	});
}

function Remove(id) {
	$.ajax({
		url : window.basePath + "/tools/tasks/remove",
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

function dateformat(sender) {
	var curDate = new Date();
	var currYear = curDate.getFullYear();
	var thisDate = sender.val();
	var object = new Date(thisDate);

	var opt = {
		preset : 'datetime',
		// theme: 'android-ics light', //皮肤样式
		// display: 'modal', //显示方式
		// mode: 'scroller', //日期选择模式
		showNow : true,
		lang : 'zh',
		startYear : currYear - 2, // 开始年份
		endYear : currYear + 2
	// 结束年份
	};
	sender.mobiscroll(opt).datetime(opt);
	;
}