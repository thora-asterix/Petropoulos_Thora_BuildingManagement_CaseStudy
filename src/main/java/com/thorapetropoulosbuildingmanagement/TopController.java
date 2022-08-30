package com.thorapetropoulosbuildingmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TopController {
	

	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		return "index";
				
	}
	

}
