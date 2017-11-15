package com.hojin.core;

import java.util.List;

import org.hibernate.Session;

import com.hojin.model.Student;
import com.hojin.util.HibernateUtil;

public class HibernateStandAloneDemo {
	public static void main(String[] args) {
		HibernateStandAloneDemo application = new HibernateStandAloneDemo();
		
		//삽입
		int studentId1 = application.saveStudent("Sam", "Disilva", "Maths");
        int studentId2 = application.saveStudent("Joshua", "Brill", "Science");
        int studentId3 = application.saveStudent("Peter", "Pan", "Physics");
        int studentId4 = application.saveStudent("Bill", "Laurent", "Maths");
        
        //리스트
        List<Student> students = application.getAllStudents();
        System.out.println("List of all persisted students >>>");
        for (Student student : students) {
            System.out.println("Persisted Student :" + student);
        }
        
        //업데이트
        application.updateStudent(studentId4, "ARTS");
        
        //삭제
        application.deleteStudent(studentId2);
	}

	 public void deleteStudent(int id) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
	 
	        Student student = (Student) session.get(Student.class, id);
	        session.delete(student);
	        session.getTransaction().commit();
	        session.close();
	    }


	private void updateStudent(int id, String section) {
		Session session = openSession();
		session.beginTransaction();
		
		Student student = (Student) session.get(Student.class, id);//get 해올 때 객체와 주키로 맵핑
		student.setSection(section);
		//session.update(student);//트랜잭션이 끝나면 자동적으로 값 변경되기 때문에, 수동적으로 update 필요 x 
		session.getTransaction().commit();
		session.close();
	}

	private List<Student> getAllStudents() {
		Session session = openSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Student> employess =  (List<Student>) session.createQuery(
                "FROM Student s ORDER BY s.firstName ASC").list();
		session.getTransaction().commit();
		session.close();

		return employess;
	}

	private int saveStudent(String firstName, String lastName, String section) {
		  Student student = new Student();
	        student.setFirstName(firstName);
	        student.setLastName(lastName);
	        student.setSection(section);
	        
	        Session session = openSession();
	        session.beginTransaction();
	        
	        int id = (Integer) session.save(student);//generated identifier값이 리턴
	        session.getTransaction().commit();
	        session.close();
	        return id;
	}
	
	public static Session openSession(){
		return HibernateUtil.getSessionFactory().openSession();
	}
}
