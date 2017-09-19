package com.example.life.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.life.domain.Result;
import com.example.life.utils.ResultUtils;

@Service
public class GetLocation {
	private String ak = "nc7Q1agjAoTBM6u804rrBnNAikci0L06";
	private String coor = "bd09ll";
	public Result<Object> getBaiduMap() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.map.baidu.com/location/ip?ak={ak}&coor={coor}";
        Map<String, Object> map = new HashMap<>();
        map.put("ak", ak);
        map.put("coor", coor);
        Object info = restTemplate.getForObject(url, Object.class, map);
        return ResultUtils.success(info);
	}
}
