package edu.bjtu.ee4j.controllers;

import edu.bjtu.ee4j.APIVersion.ApiVersion;
import edu.bjtu.ee4j.domain.Person;
import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController("PersonController-v2")
@ApiVersion(2)
@RequestMapping("/{api_version}")
@Api(value="GYM_CLUB_Person", description="Operations about user register and login")
public class PersonController<R> {
    private PersonService PersonService;

    @Autowired
    public void setPersonService(PersonService PersonService) {
        this.PersonService = PersonService;
    }
    
 
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "text/html")
    public  ModelAndView index(WebRequest request,Coach coach, Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
       // model.addAttribute("contacts", this.PersonService.getAllPersons());
        System.out.println("这是没有缓存的界面2");
        return new ModelAndView("/index");
    }
    
   
    @RequestMapping(value ="/about" ,produces = "text/html")
    public ModelAndView index1(WebRequest request,Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return new ModelAndView("/about");
    }
    

    @RequestMapping(value = "/map" ,produces = "text/html")
    public ModelAndView index0(WebRequest request,Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return new ModelAndView("/map");
    }

  
    @RequestMapping(value ="/portfolio" ,produces = "text/html")
    public ModelAndView index2(WebRequest request,Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
       // model.addAttribute("Persons", this.PersonService.getAllPersons());
        return new ModelAndView("/portfolio");
    }
    
    
    @RequestMapping(value = "/shortcodes" ,produces = "text/html")
    public ModelAndView index3(WebRequest request,Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
      //  model.addAttribute("Persons", this.PersonService.getAllPersons());
        return new ModelAndView("/shortcodes");
    }
    
   
    @RequestMapping(value =  "/single" ,produces = "text/html")
    public ModelAndView index4(WebRequest request,Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return new ModelAndView("/single");
    }
    
   
    @RequestMapping(value = "/person", method = RequestMethod.GET,produces = "text/html")
    public ModelAndView index5(WebRequest request,Person person, Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
       // model.addAttribute("contacts", this.PersonService.getAllPersons());
        return new ModelAndView("/contact");
    }
    
   
    @RequestMapping(value ="/services" ,produces = "text/html")
    public ModelAndView index6(WebRequest request,Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return new ModelAndView("/services");
    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET,produces = "text/html")
    public ModelAndView index7(WebRequest request,Person person,Model model) {
    	long lastModified =6L; // 1. 应用相关的方式计算得到(application-specific calculation)
        if (request.checkNotModified(lastModified)) {
        	        // 2. 快速退出 — 不需要更多处理了
        	        return null;
         }
        model.addAttribute("activePage", "contacts1");
        model.addAttribute("hhh", "Register");
        return new ModelAndView("/login");
    }
 
    @ApiOperation(value = "user register")
    @RequestMapping(value = "/register", method = RequestMethod.POST,produces = "text/html")
    //@ResponseBody
    public ModelAndView Register(@Valid Person person, BindingResult bindingResult, Model model,HttpServletResponse response,HttpServletRequest request) {
    	boolean judge=true;
        if (bindingResult.hasErrors()) {
        	 judge=false;
        	  return new ModelAndView("/contact");
           
        }
      
      if(this.PersonService.getUser(person.getEmail())!=null){
    	  model.addAttribute("err", "The email has been registered!");
    	  judge=false;
    	  return new ModelAndView("/contact");
      }
      else if(this.PersonService.getUser1(person.getPhone_no())!=null){
    	  model.addAttribute("err1", "The phone has been registered!");
    	  judge=false;
    	  return new ModelAndView("/contact");
      }
        
        this.PersonService.savePerson(person);
        try {
			this.addCookie(person.getPhone_no(),"", response, request);
			this.addCookie(person.getEmail().replace("@",""),"", response, request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         model.addAttribute("hhh", "Login");
         return new ModelAndView("/login");
    }
    
    @ApiOperation(value = "user login and verify")
    @RequestMapping(value = "/login2",method = RequestMethod.POST,produces = "text/html")
    public ModelAndView viewPerson(@Valid Person person, BindingResult bindingResult, Model model,HttpServletResponse response,HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	boolean judge=true;
    	if(session.getValue("name")==null){
    		if(person.getEmail()!=null){
    	
    		 
    			  String mailRegex,mailName,mailDomain;
    		        mailName="^[0-9a-z]+\\w*";       //^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
    		        mailDomain="([0-9a-z]+\\.)+[0-9a-z]+$";       //***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
    		        mailRegex=mailName+"@"+mailDomain;          //邮箱正则表达式      ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
    		        Pattern pattern=Pattern.compile(mailRegex);
    		         Matcher matcher=pattern.matcher(person.getEmail());
    		         if(!matcher.matches()){
    			  model.addAttribute("err1", "Wrong Email.");
    			  judge=false;
    			  return new ModelAndView("/login");
    			 
    		         }
    			
    	      
    	  if(this.PersonService.getUser(person.getEmail())!=null&&this.PersonService.getUser(person.getEmail()).equals(person.getPassword())){
    		  session.putValue("name", person.getEmail());
    		String str[]=request.getParameterValues("pass");
    		if(str!=null && str[0].equals("on")){
    			System.out.println("保存了密码:");
    			try {
    				this.addCookie(person.getEmail().replace("@",""),person.getPassword(), response, request);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else{
    			try {
    				this.addCookie(person.getEmail().replace("@",""),"", response, request);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	
    			 
    		
    		return new ModelAndView("/services");
         }
    	  else{
    		  model.addAttribute("err", "Wrong Password!");
    		  judge=false;
    		  return new ModelAndView("/login");
    	  }
    		}
    	
    	
    		 else{
        		 session.putValue("name", person.getPhone_no());
        			
        			
           	  if (person.getPhone_no().length()!=11) {
       			  model.addAttribute("err1", "Mobile no. must be 11 digits.");
       			 judge=false;
       			return new ModelAndView("/login");
       	        }
           	  else if(this.PersonService.getUser1(person.getPhone_no()).equals(person.getPassword())){
            
        	      
            	  if(this.PersonService.getUser(person.getPhone_no())!=null&&this.PersonService.getUser(person.getPhone_no()).equals(person.getPassword())){
            		  String str[]=request.getParameterValues("pass");
            		  if(str!=null && str[0].equals("on")){
                			System.out.println("保存了密码:");
                			try {
            					this.addCookie(person.getPhone_no(), person.getPassword(), response, request);
            				} catch (UnsupportedEncodingException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                		}
                		else{
                			try {
            					this.addCookie(person.getPhone_no(),"", response, request);
            				} catch (UnsupportedEncodingException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                		}
            		  return new ModelAndView("/services");
                 }
            	  else{
            		  model.addAttribute("err", "Wrong Password!");
            		  judge=false;
            		  return new ModelAndView("/login");
            	  }
        	 }
        	 }
    	 }
 
    		
    	 else{
    	
    		 return new ModelAndView("/services");
    	 }
    
    	return new ModelAndView("/services");
    }
    
 
 
 
 
    public  void addCookie(String userName,String password,HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{
        //创建cookie
        Cookie nameCookie = new Cookie(userName, password);
        nameCookie.setPath(request.getContextPath()+"/");//设置cookie路径
        //设置cookie保存的时间 单位：秒
        nameCookie.setMaxAge(7*24*60*60);
        //将cookie添加到响应
        response.addCookie(nameCookie);            
    }
    @ResponseBody
    @RequestMapping(value="/getCookie",method=RequestMethod.POST)
    public Map<String, String> initCookie(String email, HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        Map<String, String> map = new HashMap<>();
        for(Cookie c : cookie) {
            if(c.getName().equals(email.replace("@", ""))) {
                String password = c.getValue();
                map.put("email",email);
                map.put("password", password);
                return map;
            }
        }
        return null;
    }
}
