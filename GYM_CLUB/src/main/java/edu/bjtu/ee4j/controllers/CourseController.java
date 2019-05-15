package edu.bjtu.ee4j.controllers;

import edu.bjtu.ee4j.APIVersion.ApiVersion;
import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.domain_second.Course;
import edu.bjtu.ee4j.services.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ModelAndView;



@RestController("CourseController-v2")
@ApiVersion(2)
@RequestMapping("/{api_version}")

@Api(value="GYM_CLUB_Course", description="Operations about course querying")
public class CourseController<R> {
    private CourseService CourseService;

    @Autowired
    public void setCourseService(CourseService CourseService) {
        this.CourseService = CourseService;
    }
    
    /*
  
    @RequestMapping(value = "/query1/Course", method = RequestMethod.POST, produces = "text/html")
    @ApiOperation(value = "View a list of courses in the gym",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved course list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
   
    public ModelAndView course_query(@Valid Course Course,BindingResult bindingResult,Model model) {

    	Collection<Course> courses = new ArrayList<Course>();

         Iterable<Course> iterable=this.CourseService.getAllCourses();
         Iterator<Course> iterator1=iterable.iterator();
         while (iterator1.hasNext()) {
        	
           Course c= iterator1.next();
           if(c.getCourse_name().indexOf(Course.getCourse_name())!=-1){
        	  courses.add(c);
           }
         } 
        model.addAttribute("course",courses);
    	
  	  return new ModelAndView("/index");
       
    }
  */
    /*
    @ApiOperation(value = "Search a course's detail information",response = Course.class)
    @RequestMapping(value = { "", "/course_detail/{course_name}"},produces = "text/html")
    public ModelAndView index1(@PathVariable String course_name ,Coach coach,Course course, Model model) {
        model.addAttribute("activePage", "contacts");
        
    	Course cour=this.CourseService.getCourse(course_name);
        model.addAttribute("cour", cour);
        
  	  return new ModelAndView("/index2");
    }
    */
  
    @Autowired private EntityLinks links;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <String> AllProducts(Pageable pageable, PagedResourcesAssembler assembler) {
     Page <Course> products = this.CourseService.findAll(pageable);
     PagedResources <Course> pr = assembler.toResource(products, linkTo(CourseController.class).slash("/products").withSelfRel());
     HttpHeaders responseHeaders = new HttpHeaders();
     responseHeaders.add("Link", createLinkHeader(pr));
     return new ResponseEntity <String> (createLinkHeader(pr), responseHeaders, HttpStatus.OK);
    }

    private String createLinkHeader(PagedResources <Course> pr) {
     final StringBuilder linkHeader = new StringBuilder();
     linkHeader.append(buildLinkHeader(pr.getLinks().get(0).getHref(), "first"));
     linkHeader.append(", ");
     for(int i=1;i<pr.getLinks().size();i++){
         linkHeader.append(buildLinkHeader(pr.getLinks().get(i).getHref(), "next"));
         linkHeader.append(", ");
     }
    
 
     return linkHeader.toString();
    }

    public static String buildLinkHeader(final String uri, final String rel) {
     return "<" + uri + ">; rel=\"" + rel + "\"";
    }

}
