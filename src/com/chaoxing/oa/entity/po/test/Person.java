package com.chaoxing.oa.entity.po.test;
//package com.chaoxing.oa.entity.po.websocket;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//@Entity
//public class Person {
//	private Integer id;
//	private String name;
//	private Card card;
//	
//	@Column
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
////	@OneToOne(cascade=CascadeType.ALL,optional=true)
////	@JoinColumn(name="c_id")
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,targetEntity = Card.class)
//	@JoinColumn(name = "card_id")
//	public Card getCard() {
//		return card;
//	}
//	public void setCard(Card card) {
//		this.card = card;
//	}
//	
//	
//}
