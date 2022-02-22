package com.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Student;

public class DeleteStudentsDemo {
	public static void main(String[] args) {
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			 session.beginTransaction();
			 session.createQuery("delete Student WHERE lastName='Min'").executeUpdate();
			 session.getTransaction().commit();
			 
		}finally {
			factory.close();
		}
	}
}
