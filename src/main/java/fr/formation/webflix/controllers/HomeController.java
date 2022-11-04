package fr.formation.webflix.controllers;

import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.enums.Gender;
import fr.formation.webflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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

	@GetMapping("/admin/users/add")
	public String adminUserAdd(UserEntity userEntity){
		System.out.println("Je suis dans mon GET");
		return "admin/user/add.html";
	}

//	@RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)
	@PostMapping("/admin/users/add")
	public String adminUserAddPost(UserEntity userEntity){
		System.out.println("Je suis dans mon POST");
		userEntity.setCountry("France");
		userEntity.setGender(Gender.MR);
		userEntity.setDateCreated(Calendar.getInstance());

		userService.save(userEntity);
		return "admin/user/add.html";
	}
}
