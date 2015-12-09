package com.edaisong.toolsentity.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.edaisong.toolsentity.domain.ShortcutKeys;

public class ShortcutKeysModel {
	private List<String> toolNames;
	private List<ShortcutKeys> keys;

	public static ShortcutKeysModel getInstance(List<ShortcutKeys> keys) {
		ShortcutKeysModel result = new ShortcutKeysModel();

		result.setKeys(keys);

		return result;
	}

	public List<ShortcutKeys> getKeys() {
		return keys;
	}

	public void setKeys(List<ShortcutKeys> keys) {
		this.keys = keys;
	}

	public List<String> getToolNames() {
		if (toolNames == null) {
			List<ShortcutKeys> allKeys = getKeys();
			if (allKeys == null || allKeys.size() == 0) {
				toolNames = new ArrayList<String>();
			}
			toolNames = allKeys.stream().map(ShortcutKeys::getToolsName).distinct().collect(Collectors.toList());
		}
		return toolNames;
	}

	public void setToolNames(List<String> toolNames) {
		this.toolNames = toolNames;
	}
}
