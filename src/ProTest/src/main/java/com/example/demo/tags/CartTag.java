package com.example.demo.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.example.demo.models.Item;

public class CartTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	@Override
	protected int doStartTagInternal() throws Exception {

		return SKIP_BODY;
	}

	@Override
	public void doFinally() {
		JspWriter writer = pageContext.getOut();
		try {
			HttpSession session = pageContext.getSession();
			int countItems = 0;
			if(session.getAttribute("cart") != null) {
				List<Item> cart = (List<Item>) session.getAttribute("cart");
				countItems = cart.size();
			}
			writer.write("<a href='" + pageContext.getServletContext().getContextPath() + "/cart/index'>");
			writer.write("Cart <i\r\n" + "class=\"fas fa-shopping-cart\"></i> ");
			writer.write("<span class='label label-success' style='position: absolute; text-align: center; font-size: 9px; padding: 2px 3px; line-height: .9;'>" + countItems + "</span>");
			writer.write("</a>");

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
