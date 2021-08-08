package com.example.demo.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.example.demo.models.Photo;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

public class NewProductsTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductService productService;
	
	@Override
	protected int doStartTagInternal() throws Exception{
		if(productService == null) {
			WebApplicationContext webApplicationContext = getRequestContext().getWebApplicationContext();
			AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
			autowireCapableBeanFactory.autowireBean(this);
		}
		return SKIP_BODY;
	}

	@Override
	public void doFinally() {
		JspWriter writer = pageContext.getOut();
		try {
			
			List<Product> products = productService.newProducts(true, 2);
			if (products != null) {
				for (Product product : products) {
					Photo photo = product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get();
					writer.write("<div class=\"item\">");
					writer.write("<ul class=\"thumbnails listing-products\">");
					writer.write("<li class=\"span3\">");
					writer.write("<div class=\"product-box\">");
					writer.write("<a href='" + pageContext.getServletContext().getContextPath() + "/product/details/" + product.getId() + "'>");
					writer.write("<img src=\"" + getRequestContext().getContextPath() + "/uploads/images/" + photo.getName() + "\" />");
					writer.write("</a><br />");
					writer.write("<a href='" + pageContext.getServletContext().getContextPath() + "/product/details/" + product.getId() + "' class=''>" + product.getName() + "\r\n" + 
							"										</a><br />");
					writer.write("<a href=\"#\" class=\"category\">" + product.getDescription() + "</a>");
					writer.write("<p class=\"price\">$" + product.getPrice() + "</p>");
					writer.write("<a class=\"btn btn-primary\" href='" + pageContext.getServletContext().getContextPath() + "/cart/buy/" + product.getId() + "'>Add to Cart | <i\r\n" + 
							"							class=\"fas fa-shopping-cart\"></i></a>");
					writer.write("</div>");
					writer.write("</li>");
					writer.write("</ul>");
					writer.write("</div>");
				}
			}
			
		} catch (Exception e) {
			{
				try {
					writer.write(e.getMessage());
				} catch (IOException e1) {
				}
			}
		}
	}


}
