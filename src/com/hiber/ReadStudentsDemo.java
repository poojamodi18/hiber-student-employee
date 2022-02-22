package com.hiber;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Student;

public class ReadStudentsDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			List<Student> students = session.createQuery("from Student").getResultList();
			displayStudents(students);

			students = session.createQuery("from Student s where s.lastName='Kim'").getResultList();
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.lastName='Kim' OR s.firstName='HoSeok'").getResultList();
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.email LIKE '%n@gmail.com'").getResultList();
			displayStudents(students);
			
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}
}
