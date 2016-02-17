package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Everyday;
import com.edaisong.entity.domain.LineHistoryModel;

public interface IBiService {
	List<Everyday> queryEveryDay();
	List<LineHistoryModel> getLineHistoryModel();
}
