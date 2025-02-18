package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/admin/signup")
	public String admin(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "admin/signup";
	}

	@PostMapping("/admin/signup")
	public String admfirming(@Validated @ModelAttribute("adminForm") AdminForm adminForm, BindingResult errorResult,
			HttpServletRequest request) {
		if (errorResult.hasErrors()) {
			return "admin/signup";
		}
		HttpSession session = request.getSession();
		session.setAttribute("adminForm", adminForm);
		
		return "redirect:/admin/admfirm";
	}

	@GetMapping("/admin/admfirm")
	public String admfirm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
		
		if (adminForm == null) {
	        return "redirect:/admin/signup";
	    }

		model.addAttribute("adminForm", adminForm);
		return "admin/admfirmation";
	}

	@PostMapping("/admin/register")
	public String register(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
		
		
		adminService.saveAdmin(adminForm);
		
		session.setAttribute("adminForm", adminForm);

		return "redirect:/admin/contacts";
	}

	@GetMapping("/admin/signin")
	public String loginPage(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "admin/signin";
	}
	


	@GetMapping("/admin/signout")
	public String logoutTask(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/admin/signin";
	}

	

}
