package com.example.cubomv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "foods")
//@EntityListeners(AuditingEntityListener.class)
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne
    @JoinColumn(name="user_id")
    private User user;



	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}
	
	/**
	 * Sets first name.
	 *
	 * @param firstName the first name
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * Gets email.
	 *
	 * @return the email
	 */

}
