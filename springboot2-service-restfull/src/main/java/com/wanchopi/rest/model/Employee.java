package com.wanchopi.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Employee entity
 * @author Wanchopi
 *
 */
@Entity
@Table(name = "employees")
@ToString
@NoArgsConstructor
public class Employee {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Column(name = "first_name", nullable = false, length = 50)
	@Getter @Setter
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 50)
	@Getter @Setter
	private String lastName;
	
	@Column(name = "email", nullable = false, length = 50)
	@Getter @Setter
	private String email;
	
	/**
	 * Overloaded constructor
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

}
