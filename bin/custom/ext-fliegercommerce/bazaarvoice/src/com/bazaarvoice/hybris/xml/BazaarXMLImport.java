package com.bazaarvoice.hybris.xml;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class BazaarXMLImport {

	/**
	 * root element
	 */
	private Element rootElement;
	/**
	 * xml document
	 */
	private Document document;
	/**
	 * xml file
	 */
	private File file;
	/**
	 * root tag of file, inside this will be defined all xml structure
	 */
	private String initialTag = "<Feed>\n</Feed>";
	/**
	 * @param file 
	 * @param create - if file doesn't exists, it will create a new file
	 * 
	 */
	public BazaarXMLImport(File file, boolean create){
		defineDocument(file, create);
		rootElement = document.getRootElement();
		this.file = file;
	}
	/**
	 * Will search in all xml file if element exists
	 * @param elementName
	 */
	public Element findElementByName(String elementName){
		return findElementByName(rootElement, elementName);
	}
	/**
	 * Will search in all xml file if element exists
	 * @param parent
	 * @param elementName
	 */
	@SuppressWarnings("unchecked")
	public Element findElementByName(Element parent, String elementName){
		
		List<Element> children = parent.getChildren();
		
		for(Element child : children) {
			if(elementName.equalsIgnoreCase(child.getName())) {
				return child;
			}
		}
		
		for(Element child : children) {
			Element findElementByName = findElementByName(child, elementName);
			if(findElementByName != null) {
				return findElementByName;
			}
		}		
		
		return null;
		
	}
	/**
	 * Will search in all xml file if element exists
	 * @param elementName
	 */
	public List<Element> findElementsByName(String elementName){
		return findElementsByName(rootElement, elementName);
	}
	
	/**
	 * Will search in all xml file if element exists
	 * @param parent
	 * @param elementName
	 */
	public List<Element> findElementsByName(Element parent, String elementName){
		@SuppressWarnings("unchecked")
		List<Element> children = parent.getChildren();
		List<Element> toReturn = new ArrayList<Element>();
		
		for(Element child : children) {
			if(elementName.equals(child.getName())) {
				toReturn.add(child);
			} else {
				List<Element> findElementsByName = findElementsByName(child, elementName);
				if(CollectionUtils.isNotEmpty(findElementsByName)) {
					toReturn.addAll(findElementsByName);
				}
			}
		}
		
		return toReturn;
//		if(toReturn != null && !toReturn.isEmpty()) return toReturn;
//		for(Object childValue : parent.getChildren()){
//			children = findElementsByName((Element)childValue, elementName);
//		}
//		return children;
	}
	/**
	 * 
	 * Define document value, if parameter create is true it will try to create a new if
	 * it doesn't exists, if first try fail doesn't try anymore
	 * 
	 * @param file
	 * @param create
	 */
	private void defineDocument(File file, boolean create){
		try{
			document = new SAXBuilder().build(file);
		}catch (FileNotFoundException e){
			if(create){
   			makeXMLFile(file);
   			defineDocument(file, !create);
			}else{
				e.printStackTrace();
			}
		}catch (JDOMException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * makes a new xml file with default configuration, initial tag can be customized
	 * @param file
	 */
	private void makeXMLFile(File file){
		try{
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			osw.append(initialTag);
			osw.flush();
			osw.close();
			fos.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * @param element
	 */
	public Map<String, String> getElement(Element element){
		Map<String, String> object = new TreeMap<String, String>();
		Iterator iterator = element.getChildren().iterator();
		for(;iterator.hasNext();){
			Element node = (Element) iterator.next();
			object.put(node.getName(), node.getText());
		}
		return object;
	}
	
	/**
	 * @param element
	 */
	public Map<String, Object> getElementWithInnerElements(Element element){
		Map<String, Object> object = new TreeMap<String, Object>();
		List<Element> elements = element.getChildren();
		for(Element node : elements){
			object.put(node.getName(), node.getContent());
		}
		return object;
	}
	
	/**
	 * @return the file
	 */
	public final File getFile(){
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public final void setFile(File file){
		this.file = file;
	}
	/**
	 * @return the rootElement
	 */
	public final Element getRootElement(){
		return rootElement;
	}
	/**
	 * @param rootElement the rootElement to set
	 */
	public final void setRootElement(Element rootElement){
		this.rootElement = rootElement;
	}
	/**
	 * @return the document
	 */
	public final Document getDocument(){
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public final void setDocument(Document document){
		this.document = document;
	}
	/**
	 * @return the initialTag
	 */
	public final String getInitialTag(){
		return initialTag;
	}
	/**
	 * @param initialTag the initialTag to set
	 */
	public final void setInitialTag(String initialTag){
		this.initialTag = initialTag;
	}
}
