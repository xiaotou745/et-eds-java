<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="zpManage zpSh">
    <div class="SearchMd">
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <span>骑士有待完成任务经纬度上传时间间隔:</span>
                    <input type="text" name="uploadTimeInterval" value="10" id="hasUnFinishedOrderUploadTimeInterval" style="width: 100px" />秒
                    <input type="hidden" name="oldUploadTimeInterval" value="10" id="oldHasUnFinishedOrderUploadTimeInterval" />
                </td>
                <td>
                    <span>骑士没有待完成任务经纬度上传时间间隔:</span>
                    <input type="text" name="uploadTimeInterval" value="60" id="allFinishedOrderUploadTimeInterval" style="width: 100px" />秒
                    <input type="hidden" name="oldUploadTimeInterval" value="60" id="oldAllFinishedOrderUploadTimeInterval" />
                </td>
            </tr>
            <tr>
                <td>
                    <span>订单推动区域半径:</span>
                    <input type="text" name="pushRadius" value="5" id="pushRadius" style="width: 100px" />千米
                    <input type="hidden" name="oldPushRadius" value="5" id="oldPushRadius" />
                </td>
                <td>
                    <span>骑士订单列表每页显示条数:</span>
                    <input type="text" name="clienterOrderPageSize" value="20" id="clienterOrderPageSize" style="width: 100px" />条
                    <input type="hidden" name="oldClienterOrderPageSize" value="20" id="oldClienterOrderPageSize" />
                </td>
            </tr>
            <tr>
                <td>
                    <span>商家专属骑士接单响应时间:</span>
                    <input type="text" name="exclusiveOrderTime" value="5" id="exclusiveOrderTime" style="width: 100px" />分
                    <input type="hidden" name="oldExclusiveOrderTime" value="5" id="oldExclusiveOrderTime" />
                </td>
                <td>
                    <span>骑士完成任务时间限制:</span>
                    <input type="text" name="completeTimeSet" value="5" id="completeTimeSet" style="width: 100px" />分
                    <input type="hidden" name="oldCompleteTimeSet" value="5" id="oldCompleteTimeSet" />
                </td>
            </tr>
            <tr>
                <td>
                    <span>雇主任务时间限制:</span>
                    <input type="text" name="employerTaskTimeSet" value="0:01-23:59" id="employerTaskTimeSet" style="width: 100px" />分
                    <input type="hidden" name="oldEmployerTaskTimeSet" value="0:01-23:59" id="oldEmployerTaskTimeSet" />
                </td>
                <td>
                    <span>骑士提现小于等于X元支付手续费:</span>
                    <input type="text" name="employerTaskTimeSet" value="100" id="clienterWithdrawCommissionAccordingMoney" style="width: 100px" />元
                    <input type="hidden" name="oldEmployerTaskTimeSet" value="100" id="oldClienterWithdrawCommissionAccordingMoney" />
                </td>
            </tr>
            <tr>
                <td>
                    <span>无效订单判定时累计完成订单数量:</span>
                    <input type="text" name="orderCountSetting" value="50" id="orderCountSetting" style="width: 100px" />个
                    <input type="hidden" name="oldOrderCountSetting" value="50" id="oldOrderCountSetting" />
                </td>
                <td>
                    <span>无效订单判定时取货点和完成点的距离:</span>
                    <input type="text" name="takeCompleteDistance" value="0" id="takeCompleteDistance" style="width: 100px" />米
                    <input type="hidden" name="oldTakeCompleteDistance" value="0" id="oldTakeCompleteDistance" />
                </td>
            </tr>
            <tr>
                <td>
                    <span>易宝支付手续费金额:</span>
                    <input type="text" name="withdrawCommission" value="1" id="withdrawCommission" style="width: 100px" />元
                    <input type="hidden" name="oldWithdrawCommission" value="1" id="oldWithdrawCommission" />
                </td>
                <td>
                    <input type="button" value="提交修改" class="searchBtn" id="btnCommit" />
                </td>
            </tr>
        </table>
    </div>
</div>
