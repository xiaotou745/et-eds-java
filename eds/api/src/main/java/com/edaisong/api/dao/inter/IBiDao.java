package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.Everyday;
import com.edaisong.entity.domain.LineHistoryModel;

public interface IBiDao {
	List<Everyday> queryEveryDayDao();
	List<LineHistoryModel> getLineHistoryModel();
}
