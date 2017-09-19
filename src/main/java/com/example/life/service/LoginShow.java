package com.example.life.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.life.Repository.UserRepository;
import com.example.life.domain.Result;
import com.example.life.domain.User;
import com.example.life.utils.BetweenDays;
import com.example.life.utils.ResultUtils;

@Service
public class LoginShow {
	@Autowired
	private UserRepository uesrRepository;
	
	public Result<Object> getDayBetween(@PathVariable("id") Integer id) throws ParseException {
		User longinUser = uesrRepository.findOne(id);
		RestTemplate restTemplate = new RestTemplate();
		if(longinUser == null) {
			return ResultUtils.errors(3, "不存在该用户，请重新登录");
		}else {
			Date now = new Date();
			Date birthDay = longinUser.getBirthDay();
			Integer dayNumber = BetweenDays.daysBetween(birthDay, now);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("dayNumber", dayNumber);
			map.put("UserInfo", longinUser);
			return ResultUtils.success(map);
		}
	}
}
