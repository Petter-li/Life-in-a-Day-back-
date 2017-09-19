package com.example.life.utils;

import com.example.life.domain.Result;

public class ResultUtils {
	
	public static Result success(Object object){
		Result result = new Result();
		result.setCode(0);
		result.setMessage("请求成功");
		result.setData(object);
		return result;
	};
	
	public static Result success(){
		return success(null);
	};
	
	public static Result errors(Integer code, String message) {
		Result result = new Result();
		result.setCode(code);
		result.setMessage(message);
		return result;
	}
}
