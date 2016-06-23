package com.ldp.security.util.xml;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ldp.security.report.domain.DangerousObjectItem;
import com.ldp.security.report.domain.SpecialCodeItem;

/**
 * 配置文件读取类
 * @author Administrator
 *
 */
public class XMLConfigReader {

	private SystemConfig systemConfig = new SystemConfig();
	
	private DataDictConfig dataDictConfig = new DataDictConfig();
	
	private static XMLConfigReader instance=null;

	private static Logger logger = 
		Logger.getLogger(XMLConfigReader.class);
	
	private XMLConfigReader(){

		logger.info("start reading config files--");
		readSystemConfig();
		readDangerousObjectItem();
		readSpecialCodeItem();
	}
	
	private void readSystemConfig(){
		
		SAXReader reader=new SAXReader();
		InputStream in=
			Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("com/ldp/security/config/sys-config.xml");
		Document document = null;
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}		
		
		//读取每页显示数量
		Element pageSizeElt = (Element)document.selectObject("/config/system-config/page-size");
		String pageSizeStr=pageSizeElt.getStringValue();
		
		int pageSize = Integer.parseInt(pageSizeStr);
		systemConfig.setPageSize(pageSize);
		
		Element systemDataFolderElt = 
			(Element)document.selectObject("/config/system-config/system-data-folder");
		String systemDataFolder = systemDataFolderElt.getStringValue();
		
		systemConfig.setSystemDataFolder(systemDataFolder);
		
		Element securityMachineMaxCountElt = 
			(Element) document.selectObject("/config/model-config/security-machine-max-count");
		String maxCountString = securityMachineMaxCountElt.getStringValue();
		
		int maxCount = Integer.parseInt(maxCountString);
		systemConfig.setSecurityMachineMaxCount(maxCount);
		
		
		try {
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void readDangerousObjectItem(){
		
		SAXReader reader=new SAXReader();
		InputStream in=
			Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("com/ldp/security/config/dangerous-object-item-config.xml");
		Document document = null;
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}		
		
		List<Element> dangerousObjectItemEltList = 
			(List<Element>) document.selectObject("/dangerous-object-item-list/dangerous-object-item");
		List<DangerousObjectItem> dangerousObjectItemList = 
			new ArrayList<DangerousObjectItem>();
		
		for(int i=0;i<dangerousObjectItemEltList.size();i++){
			
			Element dangerousObjectItemElt = dangerousObjectItemEltList.get(i);
			String itemName = dangerousObjectItemElt.elementText("item-name");
			String itemDescription = dangerousObjectItemElt.elementText("item-description");
			
			DangerousObjectItem dangerousObjectItem = new DangerousObjectItem();
			dangerousObjectItem.setItemName(itemName);
			dangerousObjectItem.setItemDescription(itemDescription);
			dangerousObjectItemList.add(dangerousObjectItem);
		}
		
		dataDictConfig.setDangerousObjectItemList(dangerousObjectItemList);
		dataDictConfig.setDangerousObjectItemCount(dangerousObjectItemList.size());
		
		try {
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void readSpecialCodeItem(){
		
		SAXReader reader=new SAXReader();
		InputStream in=
			Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("com/ldp/security/config/special-code-item-config.xml");
		Document document = null;
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}		
		
		List<Element> specialCodeItemEltList = 
			(List<Element>) document.selectObject("/special-code-item-list/special-code-item");
		List<SpecialCodeItem> specialCodeItemList = 
			new ArrayList<SpecialCodeItem>();
		
		for(int i=0;i<specialCodeItemEltList.size();i++){
			
			Element specialCodeItemElt = specialCodeItemEltList.get(i);
			String itemName = specialCodeItemElt.elementText("item-name");
			String itemDescription = specialCodeItemElt.elementText("item-description");
			
			SpecialCodeItem specialCodeItem = new SpecialCodeItem();
			specialCodeItem.setItemName(itemName);
			specialCodeItem.setItemDescription(itemDescription);
			specialCodeItemList.add(specialCodeItem);
		}
		
		dataDictConfig.setSpecialCodeItemList(specialCodeItemList);
		dataDictConfig.setSpecialCodeItemCount(specialCodeItemList.size());
		
		try {
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public synchronized static XMLConfigReader getInstance(){
		if(instance==null){
			instance=new XMLConfigReader();
		}
		return instance;
	}

	
	public SystemConfig getSystemConfig(){
		return systemConfig;
	}

	public DataDictConfig getDataDictConfig() {
		return dataDictConfig;
	}
}
