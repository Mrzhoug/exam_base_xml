package zgq.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import zgq.beans.Student;
import zgq.exception.StudentNotExistException;
import zgq.utils.XmlUtils;

public class StudentDao {

	//添加信息
	public void add(Student s){
		try {
			//获取exam.xml文档dom树
			Document document=XmlUtils.getDocument();
			
			//创建出封装学生信息的标签<student examid="222" idcard="111"></student>
			Element student_tag=document.createElement("student");
			student_tag.setAttribute("examid",s.getExamid());
			student_tag.setAttribute("idcard", s.getIdcard());
			
			//创建用于封装学生姓名的标签
			Element student_name=document.createElement("name");
			student_name.setTextContent(s.getName());
			
			//创建用于封装学生所在地的标签
			Element student_location=document.createElement("location");
			student_location.setTextContent(s.getLocation());
			
			//创建用于封装学生成绩的标签
			Element student_grade=document.createElement("grade");
			student_grade.setTextContent(s.getGrade()+"");
			
			//将姓名所在地和成绩挂载到student_tag标签上
			student_tag.appendChild(student_name);
			student_tag.appendChild(student_location);
			student_tag.appendChild(student_grade);
			
			//将新得到的文件内容挂载到exam节点上
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//更新xml文件
			XmlUtils.writeToXml(document);
			
		} catch (Exception e) {
			throw new RuntimeException(e);   //unchecked exception(运行时异常)
		                                     //checked exception(编译时异常)
		}
	}
	
	//查找信息
	public Student find(String examid){
		try {
			Document document=XmlUtils.getDocument();
			
			//获取student标签集合
			NodeList students=document.getElementsByTagName("student");
			
			//循环获取 查看student的属性examid
			for(int i=0;i<students.getLength();i++){
				Element student_tag=(Element) students.item(i);
				if(student_tag.getAttribute("examid").equals(examid)){ //找到匹配学生
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
	
	
	public void delete(String name) throws StudentNotExistException{  //根据姓名进行删除操作
		Document document;
		try {
			document = XmlUtils.getDocument();
			NodeList students=document.getElementsByTagName("name");  //通过name获取学生集合
			for(int i=0;i<students.getLength();i++){
				if(students.item(i).getTextContent().equals(name)){
					//得到指定节点 进行删除操作
					students.item(i).getParentNode().getParentNode().removeChild(students.item(i).getParentNode());
					
					//重新写入xml文件
					XmlUtils.writeToXml(document);
					return;
				}
			}
			throw new StudentNotExistException("删除节点不存在");
			
		}catch(StudentNotExistException e){
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
