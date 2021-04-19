package com.yvonne.zoom.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/1920:53
 *
 * dom4j使Java生成和解析XML灵活性变高，并且代码易于维护
 * API操作类：
 *  Document：表示xml文档信息，是一个树形结构
 *  Eelment：表示xml的元素结点，提供一些操作其子元素方法的，如文本、属性、名称空间等
 *  Attribute：表示元素结点中的属性
 */
public class Dom4jWithXml {
    public static void main(String[] args) throws Exception {
        //读XML文件内容
        Dom4jWithXml dom4jWithXml = new Dom4jWithXml();
        List<Student> students = dom4jWithXml.readXmlFromIo("students.xml");
        for (Student student : students) {
            System.out.println(student);
        }

        //写入到新的XML文件
        dom4jWithXml.writeToXML(students);
    }

    public List<Student> readXmlFromIo(String nameXML) throws Exception {
        List<Student> students = new ArrayList<Student>();
        SAXReader reader = new SAXReader();
        String packagePath = this.getClass().getResource(".").getPath();
        //获得系统资源文件
        InputStream is = new FileInputStream(new File(packagePath + nameXML));
        Document document = reader.read(is);
        Element root = document.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> elements = (List<Element>)root.elements();

        for (Element e : elements) {
            String sid = e.attribute("sid").getText();
            String sname = e.elementTextTrim("sname");
            String sex = e.elementTextTrim("sex");
            String age = e.elementTextTrim("age");
            String addr = e.elementTextTrim("addr");
            Student student = new Student(new Integer(sid), sname, sex, new Integer(age), addr);
            students.add(student);
        }

        return students;
    }

    public void writeToXML(List<Student> students) throws Exception {
        DocumentFactory factory = DocumentFactory.getInstance();
        Document document = factory.createDocument();
        Element root = document.addElement("students");

        for (Student student : students) {
            Element studentE = root.addElement("student");
            studentE.addAttribute("sid", student.getSid() + "");
            studentE.addElement("sname", student.getSname());
            studentE.addElement("sex", student.getSex());
            studentE.addElement("age", student.getAge() + "");
            studentE.addElement("addr", student.getAddr());
        }

        String path = System.getProperty("user.dir") + "/schematic/";
        Writer out = new FileWriter(new File(path + "studnts_bak.xml"));
        //整齐排列XML元素，类似SimpleDateFormat
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(out, format);
        writer.write(document);
        writer.close();
        System.out.println("\nXML文件写入完毕！！");
    }

}
