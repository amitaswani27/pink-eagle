package xmlReader;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* 
 * Use Dom parser which has following benefits:

 * DOM presents the xml document as a the following tree-structure in memory.
 * DOM is W3C standard.
 * DOM parser works on Document Object Model.
 * DOM occupies more memory, preferred for small XML documents
 * DOM is Easy to navigate either forward or backward.
*/
public class XMLFileRead {

	public static void main(String[] args) throws Exception {
		
		File fXmlFile = new File("./XML/AB356.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		HashMap<Float, String> hm = new HashMap<>();
		
		NodeList nList = doc.getElementsByTagName("PostcodeStationWithCoord");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				System.out.println("Crs : " + eElement.getElementsByTagName("Crs").item(0).getTextContent());
				String crs = eElement.getElementsByTagName("Crs").item(0).getTextContent();
				System.out.println("Distance : " + eElement.getElementsByTagName("Distance").item(0).getTextContent());
				String distance = eElement.getElementsByTagName("Distance").item(0).getTextContent();
				hm.put(Float.valueOf(distance),crs);
			}

		}
		float temp1=Collections.min(hm.keySet());
		float temp2=Collections.max(hm.keySet());
		
		for(float secondMin : hm.keySet())
		{
			if(temp1<secondMin && temp2>secondMin)
			{ 	
				temp2 = secondMin;
			}
		}
		
		System.out.println("The second closest Crs code is : " + hm.get(temp2));
		
	}
}
