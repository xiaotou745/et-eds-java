package com.edaisong.toolsadmin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserIndentity;
import com.edaisong.toolsadmin.common.ViewModel;
import com.edaisong.toolsapi.service.inter.IShortcutKeysService;
import com.edaisong.toolscore.util.AjaxResult;
import com.edaisong.toolsentity.domain.ShortcutKeys;
import com.edaisong.toolsentity.view.ShortcutKeysModel;
import com.edaisong.toolsentity.view.ShortcutKeysQueryParams;

/**
 * 快捷键控件器
 * 
 * @author wangyuchuan
 *
 */
@Controller
@RequestMapping("shortcuts")
public class ShortcutKeysController {

	@Autowired
	private IShortcutKeysService keysService;

	@RequestMapping("")
	public ModelAndView index() {
		ViewModel model = ViewModel.createDefault().setCurrentTitle("快捷键").setViewPath("tools/shortcutkeys")
				.setSubTitle("常用工具").setFooterJs("tools/_shortcutKeysJs");

		List<String> toolNames = keysService.getToolNames();
		String toolName = toolNames.size() > 0 ? toolNames.get(0) : "";
		List<ShortcutKeys> lstKeys = new ArrayList<ShortcutKeys>();
		if (!StringUtils.isEmpty(toolName)) {
			lstKeys = keysService.getByToolName(toolName);
		}
		ShortcutKeysModel modelData = ShortcutKeysModel.getInstance(lstKeys);
		modelData.setToolNames(toolNames);
		
		model.setViewData(modelData);

		return model;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(HttpServletRequest request, ShortcutKeys key) {
		if (key == null) {
			return AjaxResult.Error();
		}
		if (key.getId() == 0) {
			key.setCreateBy(UserIndentity.getIndentity(request).getLoginName());
			keysService.create(key);
		} else {
			keysService.modify(key);
		}
		return AjaxResult.Success();
	}

	@RequestMapping("refresh")
	public ModelAndView refresh(ShortcutKeysQueryParams queryParams) {
		List<ShortcutKeys> keys = keysService.query(queryParams);
		ShortcutKeysModel modelData = ShortcutKeysModel.getInstance(keys);

		ViewModel model = ViewModel.create("tools/_shortcutkeyslist").setViewData(modelData);
		return model;
	}

	@RequestMapping("remove")
	@ResponseBody
	public AjaxResult remove(Integer id) {
		keysService.remove(id);
		return AjaxResult.Success();
	}

	@RequestMapping("get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable Integer id) {
		ShortcutKeys keys = keysService.getById(id);
		return AjaxResult.Success(keys);
	}
}
