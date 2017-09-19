package com.example.life.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.life.domain.Result;
import com.example.life.utils.ResultUtils;

@Service
public class GetWeather {
	
	private String key = "d15dc3e2ceec45279bdaf77c50399a89";
	
	public Result<Object> getWeather(String city) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://free-api.heweather.com/v5/weather?city={city}&key={key}";
        Map<String, Object> map = new HashMap<>();
        map.put("city", city);
        map.put("key", key);
        Object info = restTemplate.getForObject(url, Object.class, map);
        return ResultUtils.success(info);
	};
}
