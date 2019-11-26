package source;

import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;

public class XMLHandler extends DefaultHandler {
	private JumpIn model;
	private StringBuilder data;
	private ArrayList<GameObject> animals;
	private int level;
	
	private boolean rabbitState;
	private boolean x1;
	private boolean y1;
	private boolean x2;
	private boolean y2;
	private boolean nameState;
	private boolean foxState;
	private boolean directionState;
	private boolean levelState;
	
	
	public XMLHandler() {
		model = null;
		data = null;
		animals = new ArrayList<>();
		level = -1;
		rabbitState = false;
		nameState = false;
		foxState = false;
		directionState = false;
		levelState = false;
		x1 = false;
		x2 = false;
		y1 = false;
		y2 = false;	
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("name")) {
			nameState = true;
		} else if (qName.equals("x1")) {
			x1 = true;
		} else if (qName.equals("x2")) {
			x2 = true;
		} else if(qName.equals("y1")) {
			y1 = true;
		} else if(qName.equals("y2")) {
			y2 = true;
		} else if(qName.equals("Rabbit")) {
			rabbitState = true;
		} else if(qName.equals("Fox")) {
			foxState = true;
		} else if(qName.equals("level")) {
			levelState = true;
		} else if(qName.equals("direction")) {
			directionState = true;
		}
		data = new StringBuilder();
	}
	
//	@Override
//	public void endElement(String uri, String localName, String qName) throws SAXException {
//		if(nameState) {
//			name = new String(data.toString());
//			nameState = false;
//		} else if(ageState) {
//			this.age = new String(data.toString());
//			ageState = false;
//		} else if(phoneState) {
//			phone = new String(data.toString());
//			phoneState = false;
//		} else if(addyState) {
//			address = new String(data.toString());
//			addyState = false;
//		}
//		
//		if(age != "" && name != "" && phone != "" && address != "" ) {
//			addy.addBuddy(new BuddyInfo(name, address, phone, Integer.parseInt(age)));
//			this.name = "";
//			this.age = "";
//			this.address = ""; 
//			this.phone = "";
//		}
//	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch,start,length));
	} 
	
}
