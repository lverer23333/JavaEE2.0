package edu.bjtu.ee4j.controllers;


import edu.bjtu.ee4j.APIVersion.ApiVersion;
import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.domain_second.Course;
import edu.bjtu.ee4j.services.CoachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Collection;
 

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.String;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;


@RestController("CoachController-v2")
@ApiVersion(2)
@RequestMapping("/{api_version}")
@Api(value="GYM_CLUB_Coach", description="Operations about coach querying")

public class CoachController<R> {
    private CoachService CoachService;

    @Autowired
    public void setCoachService(CoachService CoachService) {
        this.CoachService = CoachService;
    }
    
   
 
    @RequestMapping(value = "/query/{page}",produces = "text/html")
    @ApiOperation(value = "Search a coach in many pages",response = Coach.class)
    public ModelAndView Coach_query(@Valid Coach coach, BindingResult bindingResult,Model model,@PathVariable("page") int page) {
  
    	Collection<Coach> a = new ArrayList<Coach>();
    	Collection<String> hrefs = new ArrayList<String>();
    	PageRequest pageRequest = PageRequest.of(page-1, 3);
    	
		Page<Coach> coachPage = this.CoachService.getAllCoaches(pageRequest);
		
		if(coach.getCoach_name()==null||coach.getCoach_name().equals("")){
    		
    		for (int i = 0; i < coachPage.getContent().size(); i++) {
    			 Coach c=coachPage.getContent().get(i);
    			 this.updatePollResourceWithLinks(c,model);
	        	  a.add(c);
	        	  hrefs.add(c.getLinks().get(0).getHref());

    		}

    	}
		else{
		for (int i = 0; i < coachPage.getContent().size(); i++) {
			 Coach c=coachPage.getContent().get(i);
			 if(c.getCoach_name().indexOf(coach.getCoach_name())!=-1){
				 this.updatePollResourceWithLinks(c,model);
	        	  a.add(c);
	        	  hrefs.add(c.getLinks().get(0).getHref());
	           }
		}
		}
		model.addAttribute("hrefs",hrefs);
        model.addAttribute("coaches",a);
        model.addAttribute("judge","yes");
    	
      return new ModelAndView("/index");
        
       
    }
    
  
    @RequestMapping(value = "/quer/Coach", method = RequestMethod.POST,produces = "text/html")
    @ApiOperation(value = "View a list of coaches in the gym",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved coach list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ModelAndView Coach_query1(@Valid Coach coach, BindingResult bindingResult,Model model) {
  
    	
    	Collection<Coach> a = new ArrayList<Coach>();
    	Collection<String> hrefs = new ArrayList<String>();
    	PageRequest pageRequest = PageRequest.of(0, 3);
    	if(coach.getCoach_name()==null||coach.getCoach_name().equals("")){
    		Page<Coach> coachPage = this.CoachService.getAllCoaches(pageRequest);
    		for (int i = 0; i < coachPage.getContent().size(); i++) {
    			 Coach c=coachPage.getContent().get(i);
    			 this.updatePollResourceWithLinks(c,model);
	        	  a.add(c);
	        	  hrefs.add(c.getLinks().get(0).getHref());
    		}
    	}
    	
    	else{
		Page<Coach> coachPage = this.CoachService.getAllCoaches(pageRequest);
		for (int i = 0; i < coachPage.getContent().size(); i++) {
			 Coach c=coachPage.getContent().get(i);
			 
			 if(c.getCoach_name().indexOf(coach.getCoach_name())!=-1){
				 this.updatePollResourceWithLinks(c,model);
	        	  a.add(c);
	        	  hrefs.add(c.getLinks().get(0).getHref());
	           }
		}
    	}
    	 model.addAttribute("hrefs",hrefs);
        model.addAttribute("coaches",a);
        model.addAttribute("judge","yes");
    	
       return new ModelAndView("/index");
        
       
    }
    
    @ApiOperation(value = "Search a coach in gym",response = Coach.class)
    @RequestMapping(value = "/coach_detail/{coach_name}",produces = "text/html")
    public ModelAndView index1(@PathVariable String coach_name,Coach coach,Course course, Model model) {
        model.addAttribute("activePage", "contacts");
        System.out.println(coach_name);
    	Coach co=this.CoachService.getCoach(coach_name);
        model.addAttribute("co", co);
        
        return new ModelAndView("/index1");
        
    }
    
    private void updatePollResourceWithLinks(Coach coach,Model model) {
    	Course cc=new Course();
    	Link link = linkTo(methodOn(CoachController.class).index1(coach.getCoach_name(), coach, cc, model)).withRel("coach_detail/"+coach.getCoach_name());
    	coach.add(link);
    
    }
   
}
