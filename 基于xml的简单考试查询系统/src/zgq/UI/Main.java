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
			System.out.println("����û�����a��      ɾ���û�����b��    ��ѯ�ɼ���c");
			System.out.print("������������ͣ�");

			// ��ȡ�û�����
			BufferedReader bufr = new BufferedReader(new InputStreamReader(
					System.in));
			String in = bufr.readLine();

			if ("a".equals(in)) {
				System.out.print("������ѧ��������");
				String name = bufr.readLine();

				System.out.print("������ѧ��׼��֤�ţ�");
				String examid = bufr.readLine();

				System.out.print("������ѧ�����֤�ţ�");
				String idcard = bufr.readLine();

				System.out.print("������ѧ�����ڵأ�");
				String location = bufr.readLine();

				System.out.print("������ѧ���ɼ���");
				String grade = bufr.readLine();

				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);

				StudentDao dao = new StudentDao();
				dao.add(s);

				System.out.println("��ӳɹ�����");
			}

			else if ("b".equals(in)) {
				System.out.print("������Ҫɾ����ѧ����������");
				String name  = bufr.readLine();
				try{
					StudentDao dao = new StudentDao();
					dao.delete(name);
					System.out.println("ɾ���ɹ�����");
				}catch (StudentNotExistException e) {
					System.out.println("��Ҫɾ�����û������ڣ���");
				}
			}

			else if ("c".equals(in)) {
				System.out.print("������Ҫɾ����ѧ����׼��֤�ţ�");
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
					System.out.println("�������׼��֤������δ�ܲ鵽�����Ϣ");
				}
			}
			else {
				System.out.println("��������������н�ֹ��");
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("�Բ��𣬳����ˣ���");
		}

	}
}
