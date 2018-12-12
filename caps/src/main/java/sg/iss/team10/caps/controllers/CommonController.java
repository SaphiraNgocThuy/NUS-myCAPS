package sg.iss.team10.caps.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.iss.team10.caps.model.Admin;
import sg.iss.team10.caps.model.Lecturer;
import sg.iss.team10.caps.model.Student;
import sg.iss.team10.caps.services.AdminService;
import sg.iss.team10.caps.services.LecturerService;
import sg.iss.team10.caps.services.StudentService;

@Controller
public class CommonController {

	@Autowired
	private AdminService aService;

	@Autowired
	private LecturerService lService;

	@Autowired
	private StudentService sService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "Home";
	}

	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminLogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "AdminLogin";
	}

	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public ModelAndView adminAuthenticate(@ModelAttribute Admin admin, HttpSession session, BindingResult result) {
		ModelAndView mav = new ModelAndView("AdminLogin");
		if (result.hasErrors())
			return mav;
		UserSession us = new UserSession();
		if (admin.getUsername() != null && admin.getPassword() != null) {
			Admin ad = aService.authenticate(admin.getUsername(), admin.getPassword());
			if (ad == null) {
				return mav;
			} else {
				us.setSessionId(session.getId());
				us.setAdmin(ad);
				// to change the uri after check with the relevant team
				mav = new ModelAndView("redirect:/testadmin");
			}
		} else {
			return mav;
		}
		session.setAttribute("USERSESSION", us);
		return mav;
	}

	@RequestMapping(value = "/lecturerlogin", method = RequestMethod.GET)
	public String lecturerLogin(Model model) {
		model.addAttribute("lecturer", new Lecturer());
		return "LecturerLogin";
	}

	@RequestMapping(value = "/lecturerlogin", method = RequestMethod.POST)
	public ModelAndView lecturerAuthenticate(@ModelAttribute Lecturer lecturer, HttpSession session,
			BindingResult result) {
		ModelAndView mav = new ModelAndView("LecturerLogin");
		if (result.hasErrors())
			return mav;
		UserSession us = new UserSession();
		if (lecturer.getUsername() != null && lecturer.getPassword() != null) {
			Lecturer lc = lService.authenticate(lecturer.getUsername(), lecturer.getPassword());
			if (lc == null) {
				return mav;
			} else {
				us.setSessionId(session.getId());
				us.setLecturer(lc);
				// to change after check with the relevant team
				mav = new ModelAndView("redirect:/home");
			}
		} else {
			return mav;
		}
		session.setAttribute("USERSESSION", us);
		return mav;
	}

	@RequestMapping(value = "/studentlogin", method = RequestMethod.GET)
	public String studentLogin(Model model) {
		model.addAttribute("student", new Student());
		return "StudentLogin";
	}

	@RequestMapping(value = "/studentlogin", method = RequestMethod.POST)
	public ModelAndView studentAuthenticate(@ModelAttribute Student student, HttpSession session,
			BindingResult result) {
		ModelAndView mav = new ModelAndView("StudentLogin");
		if (result.hasErrors())
			return mav;
		UserSession us = new UserSession();
		if (student.getUsername() != null && student.getPassword() != null) {
			Student st = sService.authenticate(student.getUsername(), student.getPassword());
			if (st == null) {
				return mav;
			} else {
				us.setSessionId(session.getId());
				us.setStudent(st);
				// to change after check with the relevant team
				mav = new ModelAndView("redirect:/home");
			}
		} else {
			return mav;
		}
		session.setAttribute("USERSESSION", us);
		return mav;
	}

	// For testing after login, must delete after communicate with team

	@RequestMapping(value = "/testadmin", method = RequestMethod.GET)
	public String adminTest() {
		return "TestingAfterLoginAdmin";
	}
}