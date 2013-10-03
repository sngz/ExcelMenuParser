package com.foodrun.excelparser.export;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.foodrun.excelparser.vo.MenuItemVo;
import com.foodrun.excelparser.vo.MenuPageVo;
import com.foodrun.excelparser.vo.RestaurantVo;
import com.foodrun.excelparser.vo.VariationVo;

public class RestaurantXmlExporter {
	
	private RestaurantVo restaurant;
	
	public RestaurantXmlExporter(RestaurantVo restaurant) {
		this.restaurant = restaurant;
	}
	
	public void generateXml() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			//Root element Restaurant
			Element rootElement = doc.createElement("restaurant");
			rootElement.setAttribute("id", restaurant.getId());
			rootElement.setAttribute("name", restaurant.getName());
			doc.appendChild(rootElement);
				
			//Menu Page		
			List<MenuPageVo> menuPageList = restaurant.getMenuPages();
			Element menuPagesListNode = doc.createElement("menu-pages");
			rootElement.appendChild(menuPagesListNode);
			for( MenuPageVo menuPage :  menuPageList) {
				Element menuPageNode = doc.createElement("menu-page");
				menuPagesListNode.appendChild(menuPageNode);
				Element menuPageNameNode = doc.createElement("name");
				menuPageNameNode.appendChild(doc.createTextNode(menuPage.getPageName()));
				menuPageNode.appendChild(menuPageNameNode);
				
				//Menu Items
				Element menuItemsListNode = doc.createElement("menu-items");
				menuPageNode.appendChild(menuItemsListNode);
				List<MenuItemVo> menuItemList = menuPage.getMenuItems();
				for( MenuItemVo menuItem : menuItemList ) {
					Element menuItemNode = doc.createElement("menu-item");
					Element menuItemNameNode = doc.createElement("name");
					menuItemNameNode.appendChild(doc.createTextNode(menuItem.getName()));
					menuItemNode.appendChild(menuItemNameNode);
					Element menuItemDescriptionNode = doc.createElement("description");
					menuItemDescriptionNode.appendChild(doc.createTextNode(menuItem.getDescription()));
					menuItemNode.appendChild(menuItemDescriptionNode);
					Element menuItemServingNode = doc.createElement("serving");
					menuItemServingNode.appendChild(doc.createTextNode(menuItem.getServing()));
					menuItemNode.appendChild(menuItemServingNode);
					
					//Variations
					Element variationsListNode = doc.createElement("variations");
					menuItemNode.appendChild(variationsListNode);
					List<VariationVo> variationList = menuItem.getVariations();	
					for( VariationVo variation : variationList) {
						if(variation == null) {	}
						else {
							Element variationNode = doc.createElement("variation");
							Element variationNameNode = doc.createElement("name");
							variationNameNode.appendChild(doc.createTextNode(variation.getName()));
							variationNode.appendChild(variationNameNode);
							Element variationPriceNode = doc.createElement("price");
							variationPriceNode.appendChild(doc.createTextNode(variation.getPrice().toPlainString()));
							variationNode.appendChild(variationPriceNode);
							
							variationsListNode.appendChild(variationNode);
						}				
					}
					
					menuItemsListNode.appendChild(menuItemNode);
				}
							
			}
			
			//Write into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\Projects\\ExcelMySqlParser\\Export.xml"));
			
			transformer.transform(source, result);
			
			System.out.println("XML Export Complete");
			
		}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	
}
