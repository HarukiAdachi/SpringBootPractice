package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	//	一覧画面がこれ。
	@GetMapping("/admin/contacts")
	public String contactList(Model model) {
		List<Contact> contacts = contactService.findAllContacts();
		model.addAttribute("contacts", contacts);
		return "admin/contacts";
	}

	//	詳細画面がこれ
	@GetMapping("/admin/contacts/{id}")
	public String contactDetail(@PathVariable Long id, Model model) {
		Contact contact = contactService.findById(id);
		model.addAttribute("contact", contact);
		return "admin/contactsDetail";
	}

	//	編集画面がこれ
	@GetMapping("/admin/contacts/{id}/edit")
	public String editContact(@PathVariable Long id, Model model) {
		Contact contact = contactService.findById(id);
		model.addAttribute("contact", contact);
		return "admin/contactsEdit";
	}

	//	更新と
	@PostMapping("/admin/contacts/{id}/update")
	public String updateContact(@PathVariable Long id, @ModelAttribute ContactForm contactForm) {
		contactService.updateContact(id, contactForm);
		return "redirect:/admin/contacts";
	}

	//	削除
	@PostMapping("/admin/contacts/{id}/delete")
	public String deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
		return "redirect:/admin/contacts";
	}

	//	ここまでがadmin関係。
	//	以下はgeekationのアドバンス通りのもの
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("contactForm", new ContactForm());

		return "contact/contact";
	}

	@PostMapping("/contact")
	public String contact(@Validated @ModelAttribute("contactForm") ContactForm contactForm, BindingResult errorResult,
			HttpServletRequest request) {

		if (errorResult.hasErrors()) {
			return "contact/contact";
		}

		HttpSession session = request.getSession();
		session.setAttribute("contactForm", contactForm);

		return "redirect:/contact/confirm";
	}

	@GetMapping("/contact/confirm")
	public String confirm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
		model.addAttribute("contactForm", contactForm);
		return "contact/confirmation";
	}

	@PostMapping("/contact/register")
	public String register(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");

		contactService.saveContact(contactForm);

		return "redirect:/contact/complete";

	}

	@GetMapping("/contact/complete")
	public String complete(Model model, HttpServletRequest request) {

		if (request.getSession(false) == null) {
			return "redirect:/contact";
		}

		HttpSession session = request.getSession();
		ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
		model.addAttribute("contactForm", contactForm);

		session.invalidate();

		return "contact/completion";
	}

}
