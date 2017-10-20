package com.example.life.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.life.Repository.UserRepository;
import com.example.life.domain.Result;
import com.example.life.domain.User;
import com.example.life.service.GetCity;
import com.example.life.service.GetLocation;
import com.example.life.service.GetWeather;
import com.example.life.service.LoginShow;
import com.example.life.utils.ResultUtils;
import com.example.life.validation.ValidateLogin;
import com.example.life.validation.ValidateSign;

@RestController
public class SignController {
	@Autowired
	private UserRepository uesrRepository;
	
	@Autowired
	private LoginShow loginShow;
	
	@Autowired
	private GetLocation getLocation;
	
	@Autowired
	private GetWeather getWeather;
	
	@Autowired
	private GetCity getCity;
	
	@PostMapping(value = "/sign")
	public Result<User> userAdd(@Validated(value = {ValidateSign.class}) User user, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return ResultUtils.errors(1, bindingResult.getFieldError().getDefaultMessage());
		}else {
			if(uesrRepository.findByName(user.getName()) != null) {
				return ResultUtils.errors(2, "该用户名已存在");
			}else {
				user.setName(user.getName());
				user.setPassword(user.getPassword());
				user.setBirthDay(user.getBirthDay());
				uesrRepository.save(user);
				
				return ResultUtils.success();
			}
		}
	}
	
	@PostMapping(value = "/login")
	public Result<User> userLogin(@Validated(value = {ValidateLogin.class}) User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return ResultUtils.errors(1, bindingResult.getFieldError().getDefaultMessage());
		}else {
			if(uesrRepository.findByName(user.getName()) == null) {
				return ResultUtils.errors(3, "不存在该用户");
			}else {
				User loginUser = uesrRepository.findByName(user.getName());
				if(loginUser.getPassword().equals(user.getPassword())){
					return ResultUtils.success(loginUser);
				}else {
					return ResultUtils.errors(4, "输入的密码有误");
				}
			}
		}
	}
	
	@GetMapping(value = "/checkName/{name}")
	public Result<Object> checkName(@PathVariable("name") String name) {
		if(uesrRepository.findByName(name) != null) {
			return ResultUtils.errors(2, "该用户名已存在");
		}else {
			return ResultUtils.success();
		}
	}
	
	@GetMapping(value = "/getUserById/{id}")
	public Result<Object> getUserById(@PathVariable("id") Integer id) throws ParseException {
		return loginShow.getDayBetween(id);
	}
	
	@GetMapping(value = "/getLocation")
	public Result<Object> getLocation() {
		return getLocation.getBaiduMap();
	}
	
	@GetMapping(value = "/getWeather")
	public Result<Object> GetWeather(@RequestParam("city") String city) {
		return getWeather.getWeather(city);
	}
	
	@GetMapping(value = "/getCity")
	public Result<Object> GetCity(@RequestParam("name") String name) {
		return getCity.getCity(name);
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder)
    {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch(Exception e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
            }        

        });
    }

}
