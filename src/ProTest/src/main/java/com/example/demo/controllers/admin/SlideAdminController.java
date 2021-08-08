package com.example.demo.controllers.admin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Slide;
import com.example.demo.services.SlideService;

@Controller
@RequestMapping("/admin/slide")
public class SlideAdminController implements ServletContextAware{

	private ServletContext servletContext;
	
	@Autowired
	private SlideService slideService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("slides", slideService.findAll());
		return "admin.slide.index";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		modelMap.put("slide", new Slide());
		return "admin.slide.add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("slide") Slide slide, @RequestParam(value = "file") MultipartFile file) {
		slide.setName(uploadFile(file));
		slideService.save(slide);
		return "redirect:/admin/slide";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		slideService.delete(id);
		return "redirect:/admin/slide";
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("slide", slideService.find(id));
		return "admin.slide.edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("slide") Slide slide, @RequestParam(value = "file") MultipartFile file) {
		Slide currentSlide = slideService.find(slide.getId());
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			currentSlide.setName(uploadFile(file));
		}
		currentSlide.setDescription(slide.getDescription());
		currentSlide.setStatus(slide.isStatus());
		slideService.save(currentSlide);
		return "redirect:/admin/slide";
	}
	
	private String uploadFile(MultipartFile multipartFile) {
		try {
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(servletContext.getRealPath("/uploads/images/" + multipartFile.getOriginalFilename()));
			Files.write(path, bytes);
			return multipartFile.getOriginalFilename();
		} catch (Exception e) {
			return "no-image.png";
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
}
