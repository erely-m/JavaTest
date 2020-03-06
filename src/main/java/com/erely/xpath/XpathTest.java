package com.erely.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XpathTest {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//建造者工程
        DocumentBuilder bulider = dbf.newDocumentBuilder(); //初始化建造者
        Document doc = bulider.parse("E:\\QHD\\JavaTest\\src\\main\\resources\\xpath.xml"); //获取doc

        XPathFactory factory = XPathFactory.newInstance();//实例化XPathFactory对象，帮助创建XPath对象
        XPath xpath = factory.newXPath();

        XPathExpression compile = xpath.compile("//book[author='NealStephenson']/title/text()");
        NodeList result = (NodeList) compile.evaluate(doc, XPathConstants.NODESET);
        for(int i = 0 ;i<result.getLength();i++){
            System.out.println(result.item(i).getNodeValue());
        }

    }
}
