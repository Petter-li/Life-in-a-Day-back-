package com.example.life.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.life.Repository.DirectRepository;
import com.example.life.domain.Location;
import com.example.life.domain.Result;
import com.example.life.utils.ResultUtils;


@Service
public class GetCity {
	
	@Autowired
	private DirectRepository directRepository;
	
	public Result<Object> getCity(String name) {
		// TODO Auto-generated method stub
		List resultList = new ArrayList<>();
		List<Location> location = directRepository.findByNameLike(name);
		for(Location lc: location){
			if(lc.getParent_id() != 0){
				Map<String, Object> map = new HashMap<>();
				Integer parentId = lc.getParent_id();
				Location fatherLocation = directRepository.findOne(parentId);
				map.put("childCity", lc.getName());
				map.put("fatherCity", fatherLocation.getName());
				resultList.add(map);
			}
		}
		return ResultUtils.success(resultList);
	}

}
