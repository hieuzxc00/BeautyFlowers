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

import com.example.demo.models.Photo;
import com.example.demo.models.Product;
import com.example.demo.services.PhotoService;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/admin/photo")
public class PhotoAdminController implements ServletContextAware {

	private ServletContext servletContext;

	@Autowired
	private ProductService productService;

	@Autowired
	private PhotoService photoService;

	@RequestMapping(value = "product/{id}", method = RequestMethod.GET)
	public String photosofProduct(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("product", productService.find(id));
		return "admin.photo.photos_of_product";
	}

	@RequestMapping(value = "add/{id}", method = RequestMethod.GET)
	public String add(@PathVariable("id") int id, ModelMap modelMap) {
		Product product = productService.find(id);
		Photo photo = new Photo();
		photo.setStatus(true);
		photo.setProduct(product);

		modelMap.put("photo", photo);
		modelMap.put("product", product);
		return "admin.photo.add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("photo") Photo photo, @RequestParam(value = "file") MultipartFile file) {
		photo.setName(uploadFile(file));

		// update main of product
		if (photo.isMain()) {
			Product product = productService.find(photo.getProduct().getId());
			if (product.getPhotos() != null && !product.getPhotos().isEmpty()) {
				for (Photo p : product.getPhotos()) {
					p.setMain(false);
					photoService.save(p);
				}
			}
			photo.setMain(true);
		}

		photoService.save(photo);
		return "redirect:/admin/photo/product/" + photo.getProduct().getId();
	}

	@RequestMapping(value = "delete/{id}/{productId}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, @PathVariable("productId") int productId) {
		photoService.delete(id);
		return "redirect:/admin/photo/product/" + productId;
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("photo", photoService.find(id));
		return "admin.photo.edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("photo") Photo photo, @RequestParam(value = "file") MultipartFile file) {
		Photo currentPhoto = photoService.find(photo.getId());
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			currentPhoto.setName(uploadFile(file));
		}
		currentPhoto.setStatus(photo.isStatus());
		// update main of product
		if (photo.isMain()) {
			Product product = productService.find(photo.getProduct().getId());
			for (Photo p : product.getPhotos()) {
				p.setMain(false);
				photoService.save(p);
			}
			currentPhoto.setMain(true);
		}

		photoService.save(currentPhoto);
		return "redirect:/admin/photo/product/" + photo.getProduct().getId();
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
