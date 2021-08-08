package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("categories", categoryService.findParentCatagories());
		return "admin.category.index";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		Category category = new Category();
		category.setStatus(true);
		modelMap.put("category", category);
		return "admin.category.add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("category") Category category) {
		category.setCategory(null);
		categoryService.save(category);
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id")int id, RedirectAttributes redirectAttributes) {
		try {
			categoryService.delete(id);
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", "Deleted Failed");
		}
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")int id, ModelMap modelMap) {
		modelMap.put("category", categoryService.findById(id));
		return "admin.category.edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("category") Category category) {
		categoryService.save(category);
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "subcategories/{id}", method = RequestMethod.GET)
	public String subcategories(@PathVariable("id")int id, ModelMap modelMap) {
		modelMap.put("category", categoryService.findById(id));
		return "admin.category.subcategories";
	}
	
	@RequestMapping(value = "addsubcategory/{id}", method = RequestMethod.GET)
	public String addsubcategory(@PathVariable("id")int id, ModelMap modelMap) {
		Category category = new Category();
		category.setStatus(true);
		category.setCategory(categoryService.findById(id));
		modelMap.put("category", category);
		return "admin.category.addsubcategory";
	}
	
	@RequestMapping(value = "addsubcategory", method = RequestMethod.POST)
	public String addsubcategory(@ModelAttribute("category") Category category) {
		categoryService.save(category);
		return "redirect:/admin/category/subcategories/" + category.getCategory().getId();
	}
	
	@RequestMapping(value = "deletesubcategory/{id}", method = RequestMethod.GET)
	public String deletesubcategory(@PathVariable("id")int id, RedirectAttributes redirectAttributes) {
		Category category = categoryService.findById(id);
		try {
			categoryService.delete(id);
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", "Deleted Failed");
		}
		return "redirect:/admin/category/subcategories/" + category.getCategory().getId();
	}
	
	@RequestMapping(value = "editsubcategory/{id}", method = RequestMethod.GET)
	public String editsubcategory(@PathVariable("id")int id, ModelMap modelMap) {
		modelMap.put("category", categoryService.findById(id));
		return "admin.category.editsubcategory";
	}
	
	@RequestMapping(value = "editsubcategory", method = RequestMethod.POST)
	public String editsubcategory(@ModelAttribute("category") Category category) {
		categoryService.save(category);
		return "redirect:/admin/category/subcategories/" + category.getCategory().getId();
	}
	
}
