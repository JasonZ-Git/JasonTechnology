package org.json.dom4j;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

/**
 * @TODO To be deleted
 * Dom4j实验类,用于 进行xml解析
 * 
 * @author Jason.zhang
 * 
 */
public class Dom4jMain {
	private static Logger log = Logger.getLogger("dom4jLogger");

	public static void main(String[] args) throws DocumentException {
		PropertyConfigurator.configure(Dom4jMain.class.getClassLoader()
				.getResource("log4j.properties"));

		log.info("started");
		Dom4jMain main = new Dom4jMain();
		main.parseXML();
	}

	/**
	 * 测试saxReader
	 * 
	 * @throws DocumentException
	 */
	public Document getDoc() throws DocumentException {
		InputStream in = Dom4jMain.class.getClassLoader().getResourceAsStream(
				"hello.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		return doc;
	}

	public void parseXML() throws DocumentException {
		MyVisitor vistor = new MyVisitor();
		this.getDoc().getRootElement().accept(vistor);
	}
}

/**
 * Vistor模式，遍历XML文件
 * 
 * @author chengsen
 * 
 */
class MyVisitor extends VisitorSupport {
	@Override
	public void visit(Element element) {
		System.out.println(element.getName());
	}

	@Override
	public void visit(Attribute attr) {
		System.out.println(attr.getName());
	}
}