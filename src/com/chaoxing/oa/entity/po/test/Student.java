//package com.chaoxing.oa.entity.po.test;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//@Entity
////@Table(name = "t_student", schema="")
//@DynamicInsert(true)
//@DynamicUpdate(true)
//public class Student {
//	private int id;  
//    private String name;  
//    private Set<Teacher> teachers = new HashSet<Teacher>();
//      
//    @ManyToMany(mappedBy="students",cascade=CascadeType.ALL)  
//    public Set<Teacher> getTeachers() {  
//        return teachers;  
//    }  
//    public void setTeachers(Set<Teacher> teachers) {  
//        this.teachers = teachers;  
//    }  
//    @Id  
//    @GeneratedValue  
//    public int getId() {  
//        return id;  
//    }  
//    public void setId(int id) {  
//        this.id = id;  
//    }  
//    public String getName() {  
//        return name;  
//    }  
//    public void setName(String name) {  
//        this.name = name;  
//    }  
//}
