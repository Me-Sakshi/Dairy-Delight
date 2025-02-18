package com.dairyproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Administrator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;

	@NotEmpty(message = "Email address is required for registration")
	@Column(unique = true)
	@Email(regexp = "^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$", message = "Please enter your valid email address")
	private String emailId;

	@NotEmpty(message = "Username must be required for registration and login")
	@Length(min = 5, max = 15, message = "Username must be in between 5 to 15 characters")
	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9_-]{5,15}$", message = "Please enter correct username")
	private String username;

	@NotEmpty(message = "Password is required")
	@Length(min = 6, message = "Password must be in between 6 to 12 characters")
	//@Pattern(regexp = "(?=^.{6,15}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$", message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;


	/**
	 *  // Composition
	 *  OneToOne ->  uni - bidirectional
	 *  OneToMany  ->  uni - bidirectional
	 *  ManyToOne  -> uni - bidirectional
	 *  ManyToMany  ->  uni - bidirectional
	 *
	 *  OneToOne -> uni
	 *       Owner                 Inverse
	 *     Column exist            column not exist
	 *
	 *     Student 				Address(pk - address_id)
	 * 		@OneToOne(Cascade =Cascade.Type=
	 * 		@JoinColumn(name="address_id" , referenceColumnName="address_id")
	 * 		private Address address;
	 *
	 *      @OneToOne
	 * 		@JoinColumn(mappedBy = "address")
	 * 		private Student student;
	 *
	 * 	ManyToMany
	 *
	 * 		DB -> allowed -> hibernate JPA provide
	 * 		Student 		            Address
	 * 				Student_address
	 * 				student_id	address_id
	 *
	 * 		@ManyToMany
	 * 		@JoinTable(name="student_address", joinsColumns = @JoinColumn(name="student_id)
	 * 	    inverseJoinColumn = @JoinColumn(name="address_id))
	 * 	    private List<Address> addresses;
	 *
	 *
	 * @ManyToMany(mappedBy="addresses")
	 * private List<Student> students;
	 *
	 *
	 *
	 *   //Inheritance
	 *    @Emblabled @Embedded
	 *    @SuperClass
	 *
	 *  // connection pooling in hibernate -> provider - hikari
	 *
	 *
	 *  // globalExceptionHandler  @ControllerAdvice
	 * @ExceptionHandler(InternalServerError.class)
	 *
	 */

}
