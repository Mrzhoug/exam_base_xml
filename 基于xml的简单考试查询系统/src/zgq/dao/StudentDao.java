package zgq.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import zgq.beans.Student;
import zgq.exception.StudentNotExistException;
import zgq.utils.XmlUtils;

public class StudentDao {

	//�����Ϣ
	public void add(Student s){
		try {
			//��ȡexam.xml�ĵ�dom��
			Document document=XmlUtils.getDocument();
			
			//��������װѧ����Ϣ�ı�ǩ<student examid="222" idcard="111"></student>
			Element student_tag=document.createElement("student");
			student_tag.setAttribute("examid",s.getExamid());
			student_tag.setAttribute("idcard", s.getIdcard());
			
			//�������ڷ�װѧ�������ı�ǩ
			Element student_name=document.createElement("name");
			student_name.setTextContent(s.getName());
			
			//�������ڷ�װѧ�����ڵصı�ǩ
			Element student_location=document.createElement("location");
			student_location.setTextContent(s.getLocation());
			
			//�������ڷ�װѧ���ɼ��ı�ǩ
			Element student_grade=document.createElement("grade");
			student_grade.setTextContent(s.getGrade()+"");
			
			//���������ڵغͳɼ����ص�student_tag��ǩ��
			student_tag.appendChild(student_name);
			student_tag.appendChild(student_location);
			student_tag.appendChild(student_grade);
			
			//���µõ����ļ����ݹ��ص�exam�ڵ���
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//����xml�ļ�
			XmlUtils.writeToXml(document);
			
		} catch (Exception e) {
			throw new RuntimeException(e);   //unchecked exception(����ʱ�쳣)
		                                     //checked exception(����ʱ�쳣)
		}
	}
	
	//������Ϣ
	public Student find(String examid){
		try {
			Document document=XmlUtils.getDocument();
			
			//��ȡstudent��ǩ����
			NodeList students=document.getElementsByTagName("student");
			
			//ѭ����ȡ �鿴student������examid
			for(int i=0;i<students.getLength();i++){
				Element student_tag=(Element) students.item(i);
				if(student_tag.getAttribute("examid").equals(examid)){ //�ҵ�ƥ��ѧ��
					Student s=new Student();
					s.setExamid(examid);
					s.setIdcard(student_tag.getAttribute("idcard"));
					s.setName(student_tag.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void delete(String name) throws StudentNotExistException{  //������������ɾ������
		Document document;
		try {
			document = XmlUtils.getDocument();
			NodeList students=document.getElementsByTagName("name");  //ͨ��name��ȡѧ������
			for(int i=0;i<students.getLength();i++){
				if(students.item(i).getTextContent().equals(name)){
					//�õ�ָ���ڵ� ����ɾ������
					students.item(i).getParentNode().getParentNode().removeChild(students.item(i).getParentNode());
					
					//����д��xml�ļ�
					XmlUtils.writeToXml(document);
					return;
				}
			}
			throw new StudentNotExistException("ɾ���ڵ㲻����");
			
		}catch(StudentNotExistException e){
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
