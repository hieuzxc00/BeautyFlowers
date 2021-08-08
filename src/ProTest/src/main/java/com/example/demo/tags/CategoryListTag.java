package com.example.demo.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;

public class CategoryListTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	protected int doStartTagInternal() throws Exception{
		if(categoryService == null) {
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
			writer.write("<div  id=\"menu-filter\">");
			List<Category> categories = categoryService.findParentCategoriesWithStatus(true);
			if (categories != null) {
				for (Category category : categories) {
					writer.write("<h3><a href='#'> " + category.getName() + " </a></h3>");
					if (!category.getCategories().isEmpty()) {
						writer.write("<div>");
						for (Category subCategory : category.getCategories()) {
							writer.write("<div class=\"checkbox checkbox-primary\">\r\n" + "				\r\n"
									+ "						<a href='" + pageContext.getServletContext().getContextPath() + "/product/category/" + subCategory.getId() + "'>" + subCategory.getName()
									+ "</a>			\r\n" + "				</div>");
						}
						writer.write("</div>");
					}
				}
			}
			writer.write("</div>");
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
