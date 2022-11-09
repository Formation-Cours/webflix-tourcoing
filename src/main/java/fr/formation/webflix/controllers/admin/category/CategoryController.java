package fr.formation.webflix.controllers.admin.category;

import fr.formation.webflix.entities.CategoryEntity;
import fr.formation.webflix.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String index(CategoryEntity categoryEntity, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("page", "category/index");
        return "admin/index";
    }

    @PostMapping("")
    public String formPost(@Valid CategoryEntity categoryEntity, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("page", "category/index");
            return "admin/index";
        }

        categoryService.save(categoryEntity);
        return "redirect:/admin/categories";
    }

    @GetMapping("{id}/delete")
    public String formPost(@PathVariable Long id, Model model) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }
}
