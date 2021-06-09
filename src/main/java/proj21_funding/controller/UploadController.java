package proj21_funding.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj21_funding.dto.PrjOption;
import proj21_funding.dto.Project;
import proj21_funding.dto.account.UserAuthInfo;
import proj21_funding.dto.account.UserInfo;
import proj21_funding.service.ProjectAndPrjOptionService;

//@RequestMapping("/upload")
@Controller
public class UploadController {
	@Autowired
	private ProjectAndPrjOptionService service;
	
	//home에서 프로젝트 올리기 광고페이지
//	@GetMapping("/upload_main")
	@RequestMapping(value = "/upload/upload_main", method = RequestMethod.GET)
	public String uploadMain() {
		return "upload/upload_main";
	}
	
	//광고페이지에서 등록 html
//	@GetMapping("/register")
	@RequestMapping(value = "/upload/register", method = RequestMethod.GET)
	public String uploadRegister() {
		return "upload/register";
	}
	
	@PostMapping("/ListSuccess")
	public String registerSuccess(Project project, PrjOption prjoption, HttpSession session) {
	try {
		UserAuthInfo userAuthInfo = (UserAuthInfo) session.getAttribute("authInfo");
		project.setUserNo(new UserInfo(userAuthInfo.getUserNo()));
			System.out.println("project >>>>"+project);
			System.out.println("prjproject >>>>"+prjoption);
			
		service.trJoinPrjAndPrjOpt(project, prjoption);
		System.out.println("success");
		return "upload/register_success";
		
	
		}catch (Exception e) { 
			System.out.println("errors2");
			e.printStackTrace();
		 
		 return "upload/register"; 
		 }
				
	}

	
}
