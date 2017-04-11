//package com.chaoxing.oa.entity.po.test;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.JoinColumn;
//
//@Entity
////@Table(name = "t_teacher", schema="")
//@DynamicInsert(true)
//@DynamicUpdate(true)
//public class Teacher {
//	 private int id;  
//	    private String name;  
//	    private Set<Student> students = new HashSet<Student>();  
//	      
//	    @ManyToMany(cascade=CascadeType.ALL)  
//	    @JoinTable(name="t_s",  
//	            joinColumns={@JoinColumn(name="teacher_id")},  
//	            inverseJoinColumns={@JoinColumn(name="student_id")}  
//	    )  
//	    public Set<Student> getStudents() {  
//	        return students;  
//	    }  
//	    public void setStudents(Set<Student> students) {  
//	        this.students = students;  
//	    }  
//	    @Id  
//	    @GeneratedValue  
//	    public int getId() {  
//	        return id;  
//	    }  
//	    public void setId(int id) {  
//	        this.id = id;  
//	    }  
//	    public String getName() {  
//	        return name;  
//	    }  
//	    public void setName(String name) {  
//	        this.name = name;  
//	    }  
//}
