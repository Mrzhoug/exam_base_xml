package junit.test;

import org.junit.Test;

import zgq.beans.Student;
import zgq.dao.StudentDao;
import zgq.exception.StudentNotExistException;

public class StudentDaoTest {

	@Test
	public void testAdd(){
		StudentDao studentDao=new StudentDao();
		Student student=new Student();
		student.setExamid("123");
		student.setIdcard("321");
		student.setName("王五");
		student.setLocation("北京");
		student.setGrade(97);
		studentDao.add(student);
	}
	
	@Test
	public void testFind(){
		StudentDao studentDao=new StudentDao();
		Student student= studentDao.find("123");
		System.out.println("Examid:"+student.getExamid());
		System.out.println("Idcard:"+student.getIdcard());
		System.out.println("Name:"+student.getName());
		System.out.println("Location:"+student.getLocation());
		System.out.println("Grade:"+student.getGrade());
	}
	
	@Test
	public void testDelete() throws StudentNotExistException {
		StudentDao studentDao=new StudentDao();
		studentDao.delete("王五");
	}
}
