package zgq.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import zgq.beans.Student;
import zgq.dao.StudentDao;
import zgq.exception.StudentNotExistException;

public class Main {
	public static void main(String[] args) {
		try {
			System.out.println("添加用户：（a）      删除用户：（b）    查询成绩（c");
			System.out.print("请输入操作类型：");

			// 获取用户输入
			BufferedReader bufr = new BufferedReader(new InputStreamReader(
					System.in));
			String in = bufr.readLine();

			if ("a".equals(in)) {
				System.out.print("请输入学生姓名：");
				String name = bufr.readLine();

				System.out.print("请输入学生准考证号：");
				String examid = bufr.readLine();

				System.out.print("请输入学生身份证号：");
				String idcard = bufr.readLine();

				System.out.print("请输入学生所在地：");
				String location = bufr.readLine();

				System.out.print("请输入学生成绩：");
				String grade = bufr.readLine();

				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);

				StudentDao dao = new StudentDao();
				dao.add(s);

				System.out.println("添加成功！！");
			}

			else if ("b".equals(in)) {
				System.out.print("请输入要删除的学生的姓名：");
				String name  = bufr.readLine();
				try{
					StudentDao dao = new StudentDao();
					dao.delete(name);
					System.out.println("删除成功！！");
				}catch (StudentNotExistException e) {
					System.out.println("您要删除的用户不存在！！");
				}
			}

			else if ("c".equals(in)) {
				System.out.print("请输入要删除的学生的准考证号：");
				String examid  = bufr.readLine();
				
				StudentDao studentDao=new StudentDao();
				Student student= studentDao.find(examid);
				if(student!=null){
					System.out.println("Examid:"+student.getExamid());
					System.out.println("Idcard:"+student.getIdcard());
					System.out.println("Name:"+student.getName());
					System.out.println("Location:"+student.getLocation());
					System.out.println("Grade:"+student.getGrade());
				}
				else {
					System.out.println("您输入的准考证号有误，未能查到相关信息");
				}
			}
			else {
				System.out.println("您输入的有误！运行截止。");
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("对不起，出错了！！");
		}

	}
}
