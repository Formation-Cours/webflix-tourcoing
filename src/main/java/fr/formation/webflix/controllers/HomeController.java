package fr.formation.webflix.controllers;

import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index() {
		// ModelAndView
		System.out.println("Je suis à l'index de mon site Webflix !!");
		return "index.html";
	}

	@RequestMapping("/admin")
	public String admin(){
		return "admin/index.html";
	}

	@RequestMapping("/admin/users")
	public String adminUser(Model model){
		model.addAttribute("users", userService.findAll());
		return "admin/user/index.html";
	}

	@RequestMapping("/admin/users/{id}")
	public String adminUserDetail(@PathVariable("id") Long userId, Model model){
		Optional<UserEntity> user = userService.findById(userId);
		if (user.isPresent()){
			model.addAttribute("u", user.get());
			return "admin/user/detail.html";
		}
		return "error_404.html";
	}
}
