package com.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Student;

public class CreateStudentsDemo {
	public static void main(String[] args) {
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			 Student student1 = new Student("Namjoon", "Kim", "joon@gmail.com");
			 Student student2 = new Student("Seokjin", "Kim", "jin@gmail.com");
			 Student student3 = new Student("Taehyung", "Kim", "taehyung@gmail.com");

			 session.beginTransaction();
			 session.save(student1);
			 session.save(student2);
			 session.save(student3);
			 session.getTransaction().commit();
			 
		}finally {
			factory.close();
		}
	}

}
