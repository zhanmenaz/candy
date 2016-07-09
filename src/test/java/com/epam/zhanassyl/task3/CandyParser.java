package com.epam.zhanassyl.task3;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandyParser {
    public static boolean validateXML(String xsdPath, String xmlPath){
                 try{
                         SchemaFactory factory =
                                         SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                         Schema schema = factory.newSchema(new File(xsdPath));
                         Validator validator = schema.newValidator();
                         validator.validate(new StreamSource(new File(xmlPath)));
                     }
                 catch (IOException e){
                         System.out.println("Exception: " + e.getMessage());
                         return false;
                     } catch (SAXException e) {
             			// TODO Auto-generated catch block
                     	System.out.println("Exception: " + e.getMessage());
             		}
                 return true;
             }

                 public static List<CandyInstance> DOMParse(String xmlPath){



                 DocumentBuilderFactory builderFactory =
                         DocumentBuilderFactory.newInstance();

                 DocumentBuilder builder = null;
            	try {
                 		builder = builderFactory.newDocumentBuilder();
                 	} catch (ParserConfigurationException e) {
                 			e.printStackTrace();
                 	}

             	try {

                 	    org.w3c.dom.Document document = builder.parse(
                         	            new FileInputStream(xmlPath));

                 	    Element rootElement = document.getDocumentElement();
                 	    //System.out.println(rootElement.toString());

                 	    NodeList nodes = rootElement.getChildNodes();


                 	    CandyInstance currentCandyInstance;

                	    List<CandyInstance> candyInstanceList=new ArrayList<CandyInstance>();



                 	    for(int i=0; i<nodes.getLength(); i++){
                     	      Node node = nodes.item(i);


                     	      if(node instanceof Element){
                         	        //a child element to process
                         	        Element child = (Element) node;
                         	        //System.out.println(child);

                         	        currentCandyInstance=new CandyInstance();


                         	        ChocolateType chocolate =ChocolateType.getChocolateType(child.getAttribute("chocolate"));
                         	        currentCandyInstance.setChocolate(chocolate);

                        	        currentCandyInstance.setEnergy(new Integer(child.getAttribute("energy")));

                         	        currentCandyInstance.setWater(new Boolean(child.getAttribute("hasWater")));

                         	        currentCandyInstance.setId(new Integer(child.getAttribute("id")));

                         	        currentCandyInstance.setName(child.getAttribute("name"));


                         	        currentCandyInstance.setType(CandyType.getCandyType(child.getAttribute("type")));

                         	        candyInstanceList.add(currentCandyInstance);

                         	        List<Element> ingridientsValuesList=retreiveIngridientsValues(child);

                         	        Element sugar = ingridientsValuesList.get(0);
                         	        Element fructose = ingridientsValuesList.get(1);
                         	        Element vanilin = ingridientsValuesList.get(2);

                         	        Element protein = ingridientsValuesList.get(3);
                         	        Element fat = ingridientsValuesList.get(4);
                         	        Element carbohydrate = ingridientsValuesList.get(5);

                         	        Element production = ingridientsValuesList.get(6);



                         	        Ingredients ingSugar= new Ingredients(new Integer(sugar.getAttribute("amount")), sugar.getAttribute("unit"));
                         	        Ingredients ingFructose= new Ingredients(new Integer(fructose.getAttribute("amount")), sugar.getAttribute("unit"));
                         	        Ingredients ingVanilin= new Ingredients(new Integer(vanilin.getAttribute("amount")), sugar.getAttribute("unit"));

                        	        Ingredients ingridients = new Ingredients(ingSugar, ingFructose, ingVanilin);



                         	        Values.Value valProtein=new Values.Value(new Integer(sugar.getAttribute("amount")), sugar.getAttribute("unit"));
                         	        Values.Value valFat=new Values.Value(new Integer(fructose.getAttribute("amount")), sugar.getAttribute("unit"));
                         	        Values.Value valCarbohydrate=new Values.Value(new Integer(vanilin.getAttribute("amount")), sugar.getAttribute("unit"));

                         	        Values values = new Values(valProtein, valFat, valCarbohydrate);




                         	        currentCandyInstance.setIngredients(ingridients);

                         	        currentCandyInstance.setValues(values);

                        	        currentCandyInstance.setProduction(production.getTextContent());



                         	       // String attribute = child.getAttribute("");
                         	      }

                     	    }
                	    return candyInstanceList;
                 	} catch (SAXException e) {
                 	    e.printStackTrace();
                 	} catch (IOException e) {
                 	    e.printStackTrace();
                 	}
        		return null;


             }

     	private static List<Element> retreiveIngridientsValues(Element child) {
     		NodeList nodeList=child.getChildNodes();
     		List<Element> elementList=new ArrayList<Element>();
     		for (Integer j = 0; j < nodeList.getLength(); j++) {
     			Node node=nodeList.item(j);
             	if ( node instanceof Element)
             		elementList.add((Element)node);
    		}
     		return elementList;
     	}


     	public static List<CandyInstance> SAXParse(String xmlPath)
                throws Exception{
           class SAXHandler extends DefaultHandler {
                 List<CandyInstance> candies = new ArrayList<CandyInstance>();
                 CandyInstance candyInstance;
                 String content = null;
              Ingredients currentIngridients;
                Values currentValues;
                 Ingredients currentIngridient;
               Values.Value currentValue;
                             @Override
                         public void startElement(String uri, String localName, String qName, Attributes attributes)
                                 throws SAXException {
                                 if (qName.equals("CandyInstance")){

                                     	currentIngridients=new Ingredients();

                                     	currentValues=new Values();
                                                             candyInstance = new CandyInstance();

                                         candyInstance.setId(Integer.parseInt(attributes.getValue("id")));

                                         ChocolateType chocolate =ChocolateType.getChocolateType(attributes.getValue("chocolate"));
                             	        candyInstance.setChocolate(chocolate);

                             	        candyInstance.setEnergy(new Integer(attributes.getValue("energy")));

                             	        candyInstance.setWater(new Boolean(attributes.getValue("hasWater")));
                                                 	        candyInstance.setName(attributes.getValue("name"));


                             	        candyInstance.setType(CandyType.getCandyType(attributes.getValue("type")));
                                     } else
                                 	if (qName.equals("Ingridient")){
                                 		currentIngridient= new Ingridient(new Integer(attributes.getValue("amount")), attributes.getValue("unit"));
                                 	} else
                             		if (qName.equals("Value")){
                             			currentValue=new Value(new Integer(attributes.getValue("amount")),attributes.getValue("unit"));
                             		}
                             }


                                 @Override
                         public void endElement(String uri, String localName,
                                 String qName) throws SAXException{
                                 if (qName.equals("CandyInstance")){
                                     	candyInstance.setIngredients(currentIngridients);
                                     	candyInstance.setValues(currentValues);

                                     	candies.add(candyInstance);
                                 	} else
                                     if(qName.equals("Ingridient")){
                                     	if (content.equals("Sugar")){
                                         		currentIngridients.setSugar(currentIngridient);
                                         	} else
                                     		if (content.equals("Fructose")){
                                         		currentIngridients.setFructose(currentIngridient);
                                         	} else
                                     		if (content.equals("Vanilin")){
                                         		currentIngridients.setVanilla(currentIngridient);
                                         	}
                                     } else
                                 	if (qName.equals("Value")){
                                 		if (content.equals("Protein")){
                                         		currentValues.setProtein(currentValue);
                                         	} else
                                     		if (content.equals("Fat")){
                                     			currentValues.setFat(currentValue);
                                         	} else
                                     		if (content.equals("Carbohydrate")){
                                     			currentValues.setCarbohydrate(currentValue);
                                         	}
                                 	} else
                             		if (qName.equals("Production")){
                             			candyInstance.setProduction(content);
                             		}
                             }


                                 @Override
                         public void characters(char[] ch, int start, int length)
                                throws SAXException{
                                 content = String.copyValueOf(ch, start, length).trim();
                             }
                     }


                 SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                 SAXParser parser = parserFactory.newSAXParser();
                 SAXHandler handler = new SAXHandler();


                 parser.parse(new File(xmlPath), handler);
                return handler.candies;
             }



                 /**
           * StAX
           */
                 public static List<CandyInstance> StAXParse(String xmlPath)
                 throws Exception{
                 List<CandyInstance> candyInstances = new ArrayList<CandyInstance>();
                 CandyInstance candyInstance = new CandyInstance();

                 String tagContext = null;
                 XMLInputFactory factory = XMLInputFactory.newFactory();
                 XMLStreamReader reader =
                                 factory.createXMLStreamReader(new FileInputStream(new File(xmlPath)));

                 String localName;

                 Ingredients currentIngridients =new Ingredients();
                 Values currentValues=new Values();

                 Ingredient currentIngridient=new Ingredient();
                 Values.Value currentValue=new Values.Value();


                 while(reader.hasNext()){
                         int event = reader.next();

                        switch (event){
                                 case XMLStreamConstants.START_ELEMENT:
                                     	localName=reader.getLocalName();
                                         if("CandyInstance".equals(localName)){
                                                 candyInstance = new CandyInstance();

                                                 currentIngridients=new Ingredients();

                                             	currentValues=new Values();
                                                                         candyInstance = new CandyInstance();

                                                 candyInstance.setId(Integer.parseInt(reader.getAttributeValue(null,"id")));

                                                 ChocolateType chocolate =ChocolateType.getChocolateType(reader.getAttributeValue(null,"chocolate"));
                                     	        candyInstance.setChocolate(chocolate);

                                     	        candyInstance.setEnergy(new Integer(reader.getAttributeValue(null,"energy")));

                                     	        candyInstance.setWater(new Boolean(reader.getAttributeValue(null,"hasWater")));

                                     	        candyInstance.setName(reader.getAttributeValue(null,"name"));


                                     	        candyInstance.setType(CandyType.getCandyType(reader.getAttributeValue(null,"type")));

                                             } else
                                         	if ("Ingridient".equals(localName)){
                                         		currentIngridient= new Ingredient(new Integer(reader.getAttributeValue(null,"amount")), reader.getAttributeValue(null,"unit"));
                                         	} else
                                     		if ("Value".equals(localName)){
                                     			currentValue=new Values.Value(new Integer(reader.getAttributeValue(null,"amount")),reader.getAttributeValue(null,"unit"));
                                     		}

                                         break;


                                 case XMLStreamConstants.CHARACTERS:
                                         tagContext = reader.getText().trim();
                                         break;


                                 case XMLStreamConstants.END_ELEMENT:
                                     	localName=reader.getLocalName();

                                         if (localName.equals("CandyInstance")){
                                             	candyInstance.setIngredients(currentIngridients);
                                             	candyInstance.setValues(currentValues);

                                             	candyInstances.add(candyInstance);
                                         	} else
                                             if(localName.equals("Ingridient")){
                                             	if (tagContext.equals("Sugar")){
                                                 		currentIngridients.setSugar(currentIngridient);
                                                	} else
                                             		if (tagContext.equals("Fructose")){
                                                 		currentIngridients.setFructose(currentIngridient);
                                                 	} else
                                             		if (tagContext.equals("Vanilin")){
                                                 		currentIngridients.setVanilla(currentIngridient);
                                                 	}
                                             } else
                                         	if (localName.equals("Value")){
                                         		if (tagContext.equals("Protein")){
                                                 		currentValues.setProtein(currentValue);
                                                 	} else
                                             		if (tagContext.equals("Fat")){
                                             			currentValues.setFat(currentValue);
                                                 	} else
                                             		if (tagContext.equals("Carbohydrate")){
                                             			currentValues.setCarbohydrate(currentValue);
                                                 	}
                                         	} else
                                     		if (localName.equals("Production")){
                                     			candyInstance.setProduction(tagContext);
                                     		}

                                         break;
                                 case XMLStreamConstants.START_DOCUMENT:
                                         candyInstances = new ArrayList<CandyInstance>();
                                         break;

                             }

                     }
                 return candyInstances;
             }

                 /**
           * Writing to file
         */
                 public static void toXML(List<CandyInstance> candyInstanceObjects, String filePath){
                 try{
                         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                         DocumentBuilder builder = factory.newDocumentBuilder();

                         org.w3c.dom.Document document = builder.newDocument();
                         Element rootElement = document.createElement("Сandy");
                         document.appendChild(rootElement);

                         for(CandyInstance currCandyInstanceObject : candyInstanceObjects){
                                 Element candyInstance = document.createElement("candyInstance");
                                 rootElement.appendChild(candyInstance);


                                 Attr attrId = document.createAttribute("id");
                                 attrId.setValue(Integer.toString(currCandyInstanceObject.getId()));
                                 candyInstance.setAttributeNode(attrId);

                                 Attr attrChocolate= document.createAttribute("chocolate");
                                 attrChocolate.setValue(currCandyInstanceObject.getChocolate().toString());
                                 candyInstance.setAttributeNode(attrChocolate);

                                 Attr attrEnergy= document.createAttribute("energy");
                                 attrEnergy.setValue(currCandyInstanceObject.getEnergy().toString());
                                 candyInstance.setAttributeNode(attrEnergy);

                                 Attr attrHasWater= document.createAttribute("hasWater");
                                 attrHasWater.setValue(new Boolean(currCandyInstanceObject.isWater()).toString());
                                 candyInstance.setAttributeNode(attrHasWater);

                                 Attr attrName= document.createAttribute("name");
                                 attrName.setValue(currCandyInstanceObject.getName().toString());
                                 candyInstance.setAttributeNode(attrName);

                                 Attr attrType= document.createAttribute("type");
                                 attrType.setValue(currCandyInstanceObject.getType().toString());
                                 candyInstance.setAttributeNode(attrType);

                                 Attr attrAmount;
                                 Attr attrUnit;

                                 // Ingridients
                                 Element sugar = document.createElement("Ingridient");

                                 sugar.appendChild(document.createTextNode("Sugar"));

                 	                attrAmount= document.createAttribute("amount");
                 	                attrAmount.setValue(currCandyInstanceObject.getIngredients().getSugar().getAmount().toString());
                 	                sugar.setAttributeNode(attrAmount);

                 	                attrUnit= document.createAttribute("unit");
                 	                attrUnit.setValue(currCandyInstanceObject.getIngredients().getSugar().getUnit());
                 	                sugar.setAttributeNode(attrUnit);

                                 candyInstance.appendChild(sugar);

                                 Element fructose = document.createElement("Ingridient");

                                 fructose.appendChild(document.createTextNode("fructose"));

                 	                attrAmount= document.createAttribute("amount");
                 	                attrAmount.setValue(currCandyInstanceObject.getIngredients().getFructose().getAmount().toString());
                 	                fructose.setAttributeNode(attrAmount);

                 	                attrUnit= document.createAttribute("unit");
                 	                attrUnit.setValue(currCandyInstanceObject.getIngredients().getFructose().getUnit());
                 	                fructose.setAttributeNode(attrUnit);

                                 candyInstance.appendChild(fructose);

                                 Element vanilin = document.createElement("Ingridient");

                                 vanilin.appendChild(document.createTextNode("vanilin"));

                 	                attrAmount= document.createAttribute("amount");
                 	                attrAmount.setValue(currCandyInstanceObject.getIngredients().getVanilla().getAmount().toString());
                 	                vanilin.setAttributeNode(attrAmount);

                 	                attrUnit= document.createAttribute("unit");
                 	                attrUnit.setValue(currCandyInstanceObject.getIngredients().getVanilla().getUnit());
                 	                vanilin.setAttributeNode(attrUnit);

                                 candyInstance.appendChild(vanilin);
                                 // end Ingridients
                                 //Values

                                 Element protein = document.createElement("Value");

                                 protein.appendChild(document.createTextNode("Sugar"));

                 	                attrAmount= document.createAttribute("amount");
                 	                attrAmount.setValue(currCandyInstanceObject.getValues().getProtein().getAmount().toString());
                 	                protein.setAttributeNode(attrAmount);

                 	                attrUnit= document.createAttribute("unit");
                 	                attrUnit.setValue(currCandyInstanceObject.getValues().getProtein().getUnit());
                 	                protein.setAttributeNode(attrUnit);

                                 candyInstance.appendChild(protein);

                                 Element fat = document.createElement("Value");

                                 fat.appendChild(document.createTextNode("fat"));

                 	                attrAmount= document.createAttribute("amount");
                 	                attrAmount.setValue(currCandyInstanceObject.getValues().getFat().getAmount().toString());
                 	                fat.setAttributeNode(attrAmount);

                 	                attrUnit= document.createAttribute("unit");
                 	                attrUnit.setValue(currCandyInstanceObject.getValues().getFat().getUnit());
                 	                fat.setAttributeNode(attrUnit);

                                 candyInstance.appendChild(fat);

                                 Element carbohydrate = document.createElement("Value");

                                 carbohydrate.appendChild(document.createTextNode("carbohydrate"));

                 	                attrAmount= document.createAttribute("amount");
                 	                attrAmount.setValue(currCandyInstanceObject.getValues().getCarbohydrate().getAmount().toString());
                 	                carbohydrate.setAttributeNode(attrAmount);
                                 	                attrUnit= document.createAttribute("unit");
                 	                attrUnit.setValue(currCandyInstanceObject.getValues().getCarbohydrate().getUnit());
                 	                carbohydrate.setAttributeNode(attrUnit);

                                 candyInstance.appendChild(carbohydrate);

                                 // end Values

                                             }


                         TransformerFactory transformerFactory = TransformerFactory.newInstance();
                         Transformer transformer = transformerFactory.newTransformer();
                         DOMSource source = new DOMSource(document);
                         StreamResult result = new StreamResult(new File(filePath));


                         transformer.transform(source, result);
                     } catch (ParserConfigurationException e) {
                         e.printStackTrace();
                     }
                 catch (TransformerException e){
                     	e.printStackTrace();
                     }
             }
         }



