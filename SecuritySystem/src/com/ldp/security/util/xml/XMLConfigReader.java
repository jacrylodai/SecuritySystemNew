package com.ldp.security.util.xml;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ldp.security.report.domain.DangerousObjectItem;
import com.ldp.security.report.domain.SpecialCodeItem;
import com.ldp.security.util.business.excel.common.BasePosition;
import com.ldp.security.util.business.excel.common.FormVersion;
import com.ldp.security.util.business.excel.common.SubPosition;

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
		readSecurityFormVersionConfig();
		readSecurityFormVersionDetail();
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
	
	private void readSecurityFormVersionConfig(){

		SAXReader reader=new SAXReader();
		InputStream in=
			Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("com/ldp/security/config/security-form-excel-version-info.xml");
		Document document = null;
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}		
		
		List<Element> formVersionEltList = 
			(List<Element>) document.selectNodes("/version-list/version");
		Map<Integer, FormVersion> formVersionMap = 
			new HashMap<Integer, FormVersion>();
		
		for(int i=0;i<formVersionEltList.size();i++){
			
			Element formVersionElt = formVersionEltList.get(i);
			String versionName = formVersionElt.elementTextTrim("version-name");
			String versionCodeStr = formVersionElt.elementTextTrim("version-code");
			int versionCode = Integer.parseInt(versionCodeStr);
			String versionDateStr = formVersionElt.elementTextTrim("version-date");
			String versionConfigFilePath = 
				formVersionElt.elementTextTrim("version-config-file-path");
			String versionClassPath = formVersionElt.elementTextTrim("version-class-path");
			
			FormVersion formVersion = new FormVersion();
			formVersion.setVersionName(versionName);
			formVersion.setVersionCode(versionCode);
			formVersion.setVersionDateStr(versionDateStr);
			formVersion.setVersionConfigFilePath(versionConfigFilePath);
			formVersion.setVersionClassPath(versionClassPath);
			
			formVersionMap.put(versionCode, formVersion);
		}
		
		systemConfig.setFormVersionMap(formVersionMap);
		
		try {
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void readSecurityFormVersionDetail(){
		
		Map<Integer, FormVersion> formVersionMap = systemConfig.getFormVersionMap();
		Iterator<Integer> keyIterator = formVersionMap.keySet().iterator();
		while(keyIterator.hasNext()){
			Integer versionCode = keyIterator.next();
			FormVersion formVersion = formVersionMap.get(versionCode);
			
			SAXReader reader=new SAXReader();
			InputStream in=
				Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(formVersion.getVersionConfigFilePath());
			Document document = null;
			try {
				document = reader.read(in);
			} catch (DocumentException e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}		
			
			List<Element> basePositionEltList = 
				(List<Element>) document.selectNodes("/base-position-list/base-position");
			Map<String, BasePosition> basePositionMap = 
				new HashMap<String, BasePosition>();
			
			formVersion.setBasePositionMap(basePositionMap);
			
			int currentRow = 0;
			
			for(int i=0;i<basePositionEltList.size();i++){
				
				Element basePositionElt = basePositionEltList.get(i);
				String name = basePositionElt.attributeValue("name");
				String heightStr = basePositionElt.attributeValue("height");
				int height = Integer.parseInt(heightStr);
				
				BasePosition basePosition = new BasePosition();
				basePosition.setName(name);
				basePosition.setRow(currentRow);
				basePosition.setHeight(height);
				
				basePositionMap.put(name,basePosition);
				
				List<Element> subPositionEltList = 
					basePositionElt.elements("sub-position");
				Map<String,SubPosition> subPositionMap = 
					new HashMap<String, SubPosition>();
				
				for(Element subPositionElt:subPositionEltList){
					
					String subName = subPositionElt.attributeValue("name");
					String subColStr = subPositionElt.attributeValue("col");
					int subCol = Integer.parseInt(subColStr);
					String subRelativeRowStr = subPositionElt.attributeValue("relativeRow");
					int subRelativeRow = Integer.parseInt(subRelativeRowStr);
					
					SubPosition subPosition = new SubPosition();
					subPosition.setName(subName);
					subPosition.setCol(subCol);
					subPosition.setRelativeRow(subRelativeRow);
					
					subPositionMap.put(subName, subPosition);
				}
				
				basePosition.setSubPositionMap(subPositionMap);
				
				currentRow += height;
			}
			
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
