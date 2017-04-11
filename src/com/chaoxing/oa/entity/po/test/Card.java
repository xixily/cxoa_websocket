package com.chaoxing.oa.entity.po.test;
//package com.chaoxing.oa.entity.po.websocket;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//
//@Entity
//public class Card {
//	private Integer id;
//	private String name;
//	private Person person;
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	@Column
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	@OneToOne(mappedBy = "card",cascade = CascadeType.ALL)
//	public Person getPerson() {
//		return person;
//	}
//	public void setPerson(Person person) {
//		this.person = person;
//	}
//	
//	
//}
