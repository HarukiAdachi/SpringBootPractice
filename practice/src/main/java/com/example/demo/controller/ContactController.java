package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.data.ContactData;

@Controller
public class ContactController {
	@PostMapping("/contact")
	//	public ModelAndView contact(
	//			@RequestParam("lastName") String lastName,
	//			@RequestParam("firstName") String firstName,
	//			@RequestParam("email") String email,
	//			@RequestParam("phone") String phone,
	//			@RequestParam("zipCode") String zipCode,
	//			@RequestParam("address") String address,
	//			@RequestParam("buildingName") String buildingName,
	//			@RequestParam("contactType") String contactType,
	//			@RequestParam("body") String body,
	//			ModelAndView mv) {
	//	mv.setViewName("confirmation");
	//	
	//	mv.addObject("lastName",lastName);
	//	mv.addObject("firstName",firstName);
	//	mv.addObject("email",email);
	//	mv.addObject("phone",phone);
	//	mv.addObject("zipCode",zipCode);
	//	mv.addObject("address",address);
	//	mv.addObject("buildingName",buildingName);
	//	mv.addObject("contactType",contactType);
	//	mv.addObject("body",body);
	//	
	//	return mv;
	//}

	public ModelAndView contact(@ModelAttribute ContactData contactData, ModelAndView mv) {

		mv.setViewName("confirmation");

		mv.addObject("lastName", contactData.getLastName());
		mv.addObject("firstName", contactData.getFirstName());
		mv.addObject("email", contactData.getEmail());
		mv.addObject("phone", contactData.getPhone());
		mv.addObject("zipCode", contactData.getZipCode());
		mv.addObject("address", contactData.getAddress());
		mv.addObject("buildingName", contactData.getBuildingName());
		mv.addObject("contactType", contactData.getContactType());
		mv.addObject("body", contactData.getBody());

		return mv;
	}

}
