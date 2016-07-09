package com.epam.zhanassyl.task3;

import com.sun.corba.se.impl.orb.ParserActionBase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import sun.org.mozzila.javascript.ast.ForInLoop;
public class Main {
public static void main(String[] args){
    String xsdPath = System.getProperty("Users.dir")+"/"+"Candy.xsd";
    String xmlPath = System.getProperty("Users.dir")+"/"+"Candy.xml";

    Parser.validateXml(xsdPath,xmlPath);
    List<CandyInstance> candyInstanceListDOM = new ArrayList<CandyInstance>();
    candyInstanceListDOM = Parser.DOMParse(xmlPath);
    List<CandyInstance> candyInstanceListSAX = new ArrayList<CandyInstance>();
    try{
        candyInstanceListSAX= Parser.SAXParse(xmlPath);

    }catch (Exception e){
        e.printStackTrace();
    }
    List<CandyInstance> candyInstanceListStAX = new ArrayList<CandyInstance>();
    try{
        candyInstanceListStAX =Parser.StAXParse(xmlPath);
    }catch (Exception e){
        e.printStackTrace();
    }
    for(CandyInstance candyInstance:candyInstanceListStAX){
        candyInstance.printEverything();
    }
    Parser.toXML(candyInstanceListStAX,"OutputXML.xml");
    System.out.println("good");
}
}
