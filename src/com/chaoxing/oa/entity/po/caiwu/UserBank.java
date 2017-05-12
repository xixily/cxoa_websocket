package com.chaoxing.oa.entity.po.caiwu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chaoxing.oa.entity.po.employee.UserName;

/**
 * 员工报销银行卡号
 * @author dengxf
 */
@Entity
@Table(name="userbank")
@DynamicInsert(value=true)
@DynamicUpdate(value=false)
public class UserBank {
	private Integer id;
	private String bank;
	private String account;
//	private Integer uid;
	private UserName user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	@ManyToOne()
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	/**
	 * 设置我们的关联列，在hibernate中，对象关系映射的关联体现在数据库表间就是主键的关联，
	 * 它在Article表中保存了对应user对象的主键来建立映射关系，而且名称即为"属性名_id"，
	 * 比如这里我们不指定name=“user_id"属性，hibernate默认在数据库中建立的映射主键属性名也为”user_id"
	 */
	@JoinColumn(name = "uid",unique = false,updatable = true)
	public UserName getUser() {
		return user;
	}
	public void setUser(UserName user) {
		this.user = user;
	}
	@Column
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	
	
	
}
