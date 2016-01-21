package com.edaisong.toolsadmin.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserIndentity;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.ITasksService;
import com.edaisong.toolsapi.service.inter.IUserService;
import com.edaisong.toolscore.util.AjaxResult;
import com.edaisong.toolscore.util.DateTime;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.ParseHelper;
import com.edaisong.toolsentity.common.RequestBase;
import com.edaisong.toolsentity.domain.Tasks;
import com.edaisong.toolsentity.domain.User;
import com.edaisong.toolsentity.req.TaskChangeStatusReq;
import com.edaisong.toolsentity.req.TasksQueryReq;
import com.edaisong.toolsentity.req.TasksStatus;
import com.edaisong.toolsentity.view.TasksViewModel;

@Controller
@RequestMapping("tools")
public class ToolsController {
	@Autowired
	private RedisService redisService;

	@Autowired
	private ITasksService tasksService;

	@Autowired
	private IUserService userService;

	@RequestMapping("redis")
	public ModelAndView redis() {
		ModelAndView model = new ModelAndView("adminView");

		model.addObject("subtitle", "常用工具");
		model.addObject("currenttitle", "Redis管理");
		model.addObject("viewPath", "tools/redis");
		model.addObject("footerJs", "tools/_redisJs");

		Set<String> allKeys = redisService.allKeys();
		String suffxKey = redisService.getSuffxKey();
		model.addObject("dataOfKeys", allKeys);
		model.addObject("dataOfSuffxKey", suffxKey);
		return model;
	}

	@RequestMapping(value = "redis/remove", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult redisRemove(String key) {
		redisService.remove(key);
		return AjaxResult.Success();
	}

	@RequestMapping(value = "redis/get", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult redisGet(String key) {
		Object value = redisService.get(key, Object.class);
		return AjaxResult.Success(JsonUtil.obj2string(value));
	}

	@RequestMapping("redis/query")
	public ModelAndView redisQuery(String key) {
		ModelAndView model = new ModelAndView("tools/_redislist");
		String suffxKey = redisService.getSuffxKey();
		Set<String> allKeys = new HashSet<String>();
		if (StringUtils.isEmpty(key)) {
			allKeys = redisService.allKeys();
		} else {
			allKeys = redisService.keys(suffxKey + key + "*");
		}
		model.addObject("dataOfKeys", allKeys);
		model.addObject("dataOfSuffxKey", suffxKey);
		return model;
	}

	@RequestMapping("git")
	public ModelAndView git() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("currenttitle", "常用工具");
		model.addObject("subtitle", "Git地址库");
		model.addObject("viewPath", "home/welcome");
		return model;
	}

	@RequestMapping("tasks")
	public ModelAndView tasks(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("currenttitle", "常用工具");
		model.addObject("subtitle", "任务管理");
		model.addObject("viewPath", "tools/tasks");
		model.addObject("footerJs", "tools/_tasksJs");

		TasksViewModel viewModel = new TasksViewModel();

		List<User> allUsers = userService.getAll();
		viewModel.setUsers(allUsers);

		Integer userId = UserIndentity.getIndentity(request).getUserId();
		TasksQueryReq queryReq = new TasksQueryReq();
		queryReq.setUserId(userId);
		queryReq.setStartTime(DateTime.getInstance().getDate().addDays(-30).toString());
		queryReq.setEndTime(DateTime.getInstance().getDate().addDays(1).toString());
		List<Tasks> lstTasks = tasksService.query(queryReq);

		viewModel.setTasks(lstTasks);
		viewModel.setCurUserId(userId);

		model.addObject("dataOfModel", viewModel);

		return model;
	}

	@RequestMapping("tasks/refresh")
	public ModelAndView taskRefresh(HttpServletRequest request, TasksQueryReq queryReq) {
		ModelAndView model = new ModelAndView("tools/_taskslist");

		TasksViewModel viewModel = new TasksViewModel();

		Integer userId = UserIndentity.getIndentity(request).getUserId();
		queryReq.setUserId(userId);
		queryReq.setStartTime(DateTime.parse(queryReq.getStartTime(), "yyyy-MM-dd").getDate().toString());
		queryReq.setEndTime(DateTime.parse(queryReq.getEndTime(), "yyyy-MM-dd").addDays(1).getDate().toString());
		List<Tasks> lstTasks = tasksService.query(queryReq);

		viewModel.setTasks(lstTasks);
		model.addObject("dataOfModel", viewModel);
		return model;
	}

	@RequestMapping(value = "tasks/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult taskSave(HttpServletRequest request, Tasks task) {
		if (task == null) {
			return AjaxResult.Error();
		}

		if (task.getId() == 0) {
			UserIndentity currentUser = UserIndentity.getIndentity(request);

			task.setUserId(currentUser.getUserId());
			if (StringUtils.isEmpty(currentUser.getUserName())) {
				task.setUser(currentUser.getLoginName());
			} else {
				task.setUser(currentUser.getUserName());
			}

			tasksService.create(task);
		} else {
			tasksService.modify(task);
		}
		return AjaxResult.Success();
	}

	@RequestMapping("tasks/getbyid")
	@ResponseBody
	public AjaxResult taskGet(Integer id) {
		Tasks tasks = tasksService.getById(id);
		return AjaxResult.Success(tasks);
	}

	@RequestMapping("tasks/remove")
	@ResponseBody
	public AjaxResult taskRemove(Integer id) {
		tasksService.remove(id);
		return AjaxResult.Success();
	}

	@RequestMapping(value = "tasks/changeStatus", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult taskStatusModify(TaskChangeStatusReq statusParams) {
		if (statusParams == null || statusParams.getTaskId() == 0) {
			return AjaxResult.Error("参数错误");
		}
		Tasks task = tasksService.getById(statusParams.getTaskId());
		if (task.getStatus().equals(statusParams.getTargetStatus())) {
			return AjaxResult.Error("状态不用更改");
		}
		if (task.getStatus().equals(TasksStatus.toDo.value())
				&& statusParams.getTargetStatus().equals(TasksStatus.Completed.value())) {
			return AjaxResult.Error("任务还没有被领取，不能直接完成.");
		}
		// 如果是完成变成进行中状态，则进行中的相关事宜不动
		if (task.getStatus().equals(TasksStatus.Completed.value())
				&& statusParams.getTargetStatus().equals(TasksStatus.InProcess.value())) {
			statusParams.setStartTime(ParseHelper.ToDateString(task.getStartTime()));
			statusParams.setTaskTime(task.getTaskTime());
			statusParams.setWhoId(task.getWhoId());
			statusParams.setWho(task.getWho());
		} else if (statusParams.getTargetStatus().equals(TasksStatus.InProcess.value())) {
			User who = userService.getById(statusParams.getWhoId());
			if (who != null) {
				statusParams.setWho(who.getLoginName());
			}
		}
		tasksService.modifyStatus(statusParams);

		return AjaxResult.Success();
	}
}
