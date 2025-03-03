package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {
	List<Contact> findAllContacts();
	void saveContact(ContactForm contactForm);

	void deleteContact(Long contactId);


	Contact findById(Long id);


	void updateContact(Long id, ContactForm contactForm);
}
