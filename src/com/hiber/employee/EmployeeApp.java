package com.hiber.employee;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Employee;

public class EmployeeApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		Scanner sc = new Scanner(System.in);
		int ch = 0;
		Boolean exit = true;

		try {
			do {

				System.out.println("\n\nEmployee App");
				System.out.println("1 - Get All Employees");
				System.out.println("2 - Get Employee by id");
				System.out.println("3 - Add new Employee");
				System.out.println("4 - Update Employee");
				System.out.println("5 - Delete Employee");
				System.out.println("6 - Exit");
				ch = sc.nextInt();
				switch (ch) {
				case 1:
					session = factory.getCurrentSession();
					session.beginTransaction();
					List<Employee> employees = session.createQuery("from Employee").getResultList();
					session.getTransaction().commit();
					if (employees.size() > 0) {
						for (Employee employee : employees) {
							System.out.println(employee);
						}
					}else {
						System.out.println("No record found of employees");
					}
					break;
				case 2:
					session = factory.getCurrentSession();
					session.beginTransaction();
					Scanner s = new Scanner(System.in);
					System.out.print("Employee Id: ");
					int id = s.nextInt();
					Employee employee = session.get(Employee.class,id);
					session.getTransaction().commit();
					if(employee==null) {
						System.out.println("No record found with id "+id);
					}else {
						System.out.println(employee);
					}
					break;
				case 3:
					session = factory.getCurrentSession();
					session.beginTransaction();
					Scanner s1 = new Scanner(System.in);
					Employee employee2 = new Employee();
					System.out.println("Enter Employee details");
					System.out.print("First Name: ");
					employee2.setFirstName(s1.next());
					System.out.print("Last Name: ");
					employee2.setLastName(s1.next());
					System.out.print("Company: ");
					employee2.setCompany(s1.next());
					session.save(employee2);
					session.getTransaction().commit();
					break;
				case 4:
					session = factory.getCurrentSession();
					session.beginTransaction();
					Scanner s3 = new Scanner(System.in);
					System.out.print("Name of the employee you want to update: ");
					String uname = s3.next();
					Employee employee3 = (Employee) (session.createQuery("from Employee WHERE firstName ='"+uname+"'").getResultList()).get(0);
					if(employee3==null) {
						System.out.println("No record found with name "+uname);
					}else {
						System.out.println("Enter Updated Details");
						System.out.print("First Name: ");
//						Employee updateEmp = new Employee();
						employee3.setFirstName(s3.next());
						System.out.print("Last Name: ");
						employee3.setLastName(s3.next());
						System.out.print("Company: ");
						employee3.setCompany(s3.next());
						
						session.getTransaction().commit();
					}
					break;
				case 5:
					session = factory.getCurrentSession();
					session.beginTransaction();
					Scanner s2 = new Scanner(System.in);
					System.out.print("First Name of the employee you want to delete: ");
					String name = s2.next();
					session.createQuery("delete Employee WHERE firstName='"+name+"'").executeUpdate();
					session.getTransaction().commit();
					break;
				case 6:
					session.close();
					exit = false;
					break;
				default:
					System.out.println("Invalid Choice");
				}

			} while (exit);
		} finally {
			session.close();
		}
	}
}
