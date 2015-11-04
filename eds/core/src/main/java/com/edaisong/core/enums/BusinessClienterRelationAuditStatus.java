package com.edaisong.core.enums;

/**
 * 门店骑士绑定关系表 审核状态
 * @author CaoHeYang
 *
 */
public enum BusinessClienterRelationAuditStatus {
	   /**
		 * 待审核
		 */
	    Wait(0,"待审核"),
	    /**
		 *审核通过
		 */
	    Pass (1,"审核通过"),
	    /**
	     * 审核拒绝
	     */
	    Refuse (2,"审核拒绝");
		private int value = 0;
		private String desc;
		private BusinessClienterRelationAuditStatus(int value, String desc) { // 必须是private的，否则编译错误
			this.value = value;
			this.desc = desc;
		}
		public int value() {
			return this.value;
		}
		public String desc() {
			return this.desc;
		}

		public static BusinessClienterRelationAuditStatus getEnum(int index) {
			for (BusinessClienterRelationAuditStatus c : BusinessClienterRelationAuditStatus.values()) {
				if (c.value() == index) {
					return c;
				}
			}
			return null;
		}
}
