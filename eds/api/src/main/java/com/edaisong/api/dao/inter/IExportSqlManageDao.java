package com.edaisong.api.dao.inter;

import com.edaisong.entity.ExportSqlManage;

public interface IExportSqlManageDao {
    int insert(ExportSqlManage record);

    int insertSelective(ExportSqlManage record);
}