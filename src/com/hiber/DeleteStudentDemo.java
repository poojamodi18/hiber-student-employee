package com.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Student;

public class DeleteStudentDemo {
	public static void main(String[] args) {
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			 Student student = new Student();
			 session.beginTransaction();
			 student = session.get(Student.class, 1);
			 System.out.println(student);
			 session.delete(student);
			 session.getTransaction().commit();
			 
		}finally {
			factory.close();
		}
	}
}
