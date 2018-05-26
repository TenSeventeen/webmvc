package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.User;

@Controller
public class UserController {

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String  save(User user,Model model){
		System.out.println(user);
		if(user!=null) {
			model.addAttribute("status", "添加成功");
		}else {
			model.addAttribute("status", "添加失败");
		}
		return "status";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String  del(User user) {
		System.out.println(user.getId());
		return "userdetail";
	}
	
	@RequestMapping(value="/map",method=RequestMethod.GET)
	public String  userMap(@RequestParam Map<String,Object> params) {
		Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Object> en =  iterator.next();
			System.out.println(en.getKey());
			System.out.println(en.getValue());
		}
		return "userdetail";
	}
	
	@RequestMapping("/redirect")
	public String redirectTest(Model model,@RequestBody User user,HttpServletRequest req,final HttpServletResponse resp) {
		user.setAge(10);
		user.setName("杜迪");
		user.setMobile("15822532570");
		model.addAttribute(user);
		req.setAttribute("email", "5172681@qq.com");
		System.out.println(user+"!!!");
		return "success";
	}
	@RequestMapping("/json")
	public User jsonTest() {
		User user = new User();
		user.setId(1);
		user.setName("22");
		return user;
	}
	@RequestMapping("/upload")
	public String upload(@RequestParam(value="file",required=false) MultipartFile file,Model model) {
		String path  = "D:/file/";
		String fileName = file.getOriginalFilename();
		File targetFile = new File(path);
		if(!targetFile.exists()) {
			System.out.println("创建"+targetFile.isDirectory());
			System.out.println(targetFile.mkdirs());
		}
		try {
			file.transferTo(new File("D:/file/"+fileName));
			System.out.println(file.getSize());
			model.addAttribute("msg","success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "result";
		
	}
	
	@RequestMapping("/add")  
	public String index(User user)  
	{  
		   return "add";  
	}  
}
