package com.example.demo.models;
// Generated Sep 28, 2020 10:32:02 PM by Hibernate Tools 5.1.10.Final

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category")
public class Category implements java.io.Serializable {

	private Integer id;
	private Category category;
	private String name;
	private boolean status;
	private List<Category> categories = new ArrayList<Category>(0);
	private List<Product> products = new ArrayList<Product>(0);

	public Category() {
	}

	public Category(String name, boolean status) {
		this.name = name;
		this.status = status;
	}

	public Category(Category category, String name, boolean status, List<Category> categories, List<Product> products) {
		this.category = category;
		this.name = name;
		this.status = status;
		this.categories = categories;
		this.products = products;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
