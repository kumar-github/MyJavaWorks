import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class MainClass
{
	public static void main(String[] args) throws Exception
	{
		File fXmlFile = new File("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\config\\app.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
				
		doc.getDocumentElement().normalize();
		
		Element root = doc.getDocumentElement();
		System.out.print(root.getElementsByTagName("LoadCustomLookAndFeel").item(0).getTextContent());
		
		NodeList nodeList = root.getElementsByTagName("*");
		for(int i=0;i<nodeList.getLength();i++)
		{
			System.out.println(nodeList.item(i).getNodeName() + " -> " + nodeList.item(i).getTextContent());
			System.out.println(nodeList.item(i).hasAttributes());
		}
		
		NodeList nList = doc.getElementsByTagName("dbConnection");
		Node node = nList.item(0);
		System.out.println("\nCurrent Element :" + node.getNodeName());
		if (node.getNodeType() == Node.ELEMENT_NODE)
		{
			Element eElement = (Element)node;
			System.out.println("First Name : " + eElement.getElementsByTagName("databaseDriverClassName").item(0).getTextContent());
		}
	}
	
	private void fetchConfigurationFromXMLFile()
	{
	}
}