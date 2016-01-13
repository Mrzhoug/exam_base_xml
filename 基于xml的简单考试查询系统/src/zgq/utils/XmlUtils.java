package zgq.utils;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlUtils {
	
	//�ڹ������У��������ֶκͷ�������Ϊ��̬��
	private static String filename="src/exam.xml";
	
	//��ȡDocument�ĵ�
	public static Document getDocument() throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder= factory.newDocumentBuilder();
		Document document=builder.parse(filename);
		return document;
	}
	
	//д��Document����
	public static void writeToXml(Document document) throws Exception{
		TransformerFactory factory=TransformerFactory.newInstance();
		Transformer former= factory.newTransformer();
		former.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filename)));
	}
}
