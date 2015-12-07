package com.edaisong.toolsentity.view;

public enum LoginResult {
	success(0), userNameIsNull(1), passwordIsNull(2), userNotExists(3), passwordError(4), userIsDisable(5);
	private int value;

	LoginResult(int value) {
		this.value = value;
	}

	public static LoginResult valueOf(int value) {
		switch (value) {
		case 0:
			return LoginResult.success;
		case 1:
			return LoginResult.userNameIsNull;
		case 2:
			return LoginResult.passwordIsNull;
		case 3:
			return LoginResult.userNotExists;
		case 4:
			return LoginResult.passwordError;
		case 5:
			return LoginResult.userIsDisable;
		default:
			return LoginResult.success;
		}
	}

	public int value() {
		return this.value;
	}
	
	public String getDesc(){
		switch(value){
		case 0:
			return "登录成功";
		case 1:
			return "用户名不允许为空";
		case 2:
			return "密码不允许为空";
		case 3:
			return "用户名不存在";
		case 4:
			return "密码错误";
		case 5:
			return "用户被禁用";
		default:
			return "登录成功";
		}
	}
}
