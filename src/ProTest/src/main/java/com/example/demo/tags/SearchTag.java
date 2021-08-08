package com.example.demo.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

public class SearchTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@Override
	public void doFinally() {
		JspWriter writer = pageContext.getOut();
		try {
			HttpServletRequest request = (HttpServletRequest) (pageContext.getRequest());
			String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
			List<Category> categories = categoryService.findParentCategoriesWithStatus(true);
			writer.write("<form class='form-inline navbar-search' method='post' action='" + pageContext.getServletContext().getContextPath() + "/product/search'>");
			writer.write("<button type='submit' id='submitButton' class='btn btn-primary rounded-circle btnpro'>" + "<span class='fa fa-search'></span>" +"</button>");
			writer.write("<input id='srchFld' class='srchTxt' type='text' name='keyword' value='" + keyword + "'>");
			writer.write("<select class='srchTxt' name='category'>");
			writer.write("<option value='-1'>All</option>");
			if (categories != null) {
				for(Category category : categories) {
					writer.write("<optgroup label='" + category.getName() + "'>");
					if(!category.getCategories().isEmpty()) {
						for (Category subCategory : category.getCategories()) {
							writer.write("<option value='" + subCategory.getId() + "'>");
							writer.write(subCategory.getName());
							writer.write("</option>");
						}
					}
					
				}
			}
			writer.write("</select>");
			writer.write("</form>");
		} catch (Exception e) {
			try {
				writer.write(e.getMessage());
			} catch (IOException e1) {
			}
		}
	}




	@Override
	protected int doStartTagInternal() throws Exception {
		if(productService == null || categoryService == null) {
			WebApplicationContext webApplicationContext = getRequestContext().getWebApplicationContext();
			AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
			autowireCapableBeanFactory.autowireBean(this);
		}
		return SKIP_BODY;
	}

}
