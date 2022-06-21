package com.antran.bookclub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {

//	======================== ID =========================
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
//	======================== Variables ===================
	
	@NotEmpty(message="Name is required!")
	private String name;
	
	@NotEmpty(message="Email is required!")
	@Email(message="Need a valid email!")
	private String email;
	
	@NotEmpty(message="Password is required!")
	@Size(min=8, message="Password must have at least 8 characters long.")
	private String password;
	
	@NotEmpty(message="Confirm Password is required!")
	@Size(min=8, message="Confirm Password must have at least 8 characters long.")
	private String confirmPassword;

//  ====================== Relationship ==========================
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Book> books;
	
	@OneToMany(mappedBy="borrower", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Book> borrowBooks;
	
//	====================== CreatedAt/UpdatedAt ===================
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
//	=========================== Constructor ========================
	
	public User() {}
	
//	===================== Auto CreatedAt/UpdatedAt ==================
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

//	======================= Getter/Setter =============================
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBorrowBooks() {
		return borrowBooks;
	}

	public void setBorrowBooks(List<Book> borrowBooks) {
		this.borrowBooks = borrowBooks;
	}
}
