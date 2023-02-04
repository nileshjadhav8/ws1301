package com.example.ws1301.ws1301.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ws1301.ws1301.model.Contact;
import com.example.ws1301.ws1301.service.DataDirServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class DataDirController {

    DataDirServiceImpl dataservice;

    DataDirController(DataDirServiceImpl dataservice) {
        this.dataservice = dataservice;
    }

    @PostMapping("/save")
    public String saveData(Model model, @Valid @ModelAttribute("contact") Contact contact) {
        Contact savedcontact = dataservice.saveContact(contact);

        System.out.println(contact.getId());
        if (savedcontact != null) {
            model.addAttribute("contact", savedcontact);
            model.addAttribute("statucode", HttpStatus.CREATED);
            return "success";
        } else
            return "error";
    }

    @GetMapping("/search")
    public String searchContact() {
        return "search";
    }

    @GetMapping("/searchContact")
    public String searchContctById(Model model, HttpServletRequest request){

		String id = request.getParameter("id");

		Contact contact = dataservice.searchContactById(id);
		System.out.println("before if: " + contact.getId());
		if (contact.getId() == null) {
			System.out.println("contact is null");
			model.addAttribute("statuscode", HttpStatus.NOT_FOUND);
			return "recordNotFound";
		}

		model.addAttribute("contact", contact);

		return "showContact";
	}

    @GetMapping("/fetchAllContacts")
	public String fetchAllContacts(Model model) {

		model.addAttribute("contactsDirMap", dataservice.fetchAllcontacts());
		

		return "showAllContacts";

	}
}
