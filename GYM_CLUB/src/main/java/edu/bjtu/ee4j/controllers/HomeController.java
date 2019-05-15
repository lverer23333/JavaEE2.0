package edu.bjtu.ee4j.controllers;

import java.io.Serializable;
import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import edu.bjtu.ee4j.domain_second.Coach;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController

public class HomeController  {
	
	
    @RequestMapping(value = { "", "/", "/home" })
    public ModelAndView index(WebRequest request,Coach coach, Model model) {
        model.addAttribute("activePage", "home");
    
        long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        	    System.out.println("这是没有缓存的界面1");
        return new ModelAndView("/index");
    }
   
}
