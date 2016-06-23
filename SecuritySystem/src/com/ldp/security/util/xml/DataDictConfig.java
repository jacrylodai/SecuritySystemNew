package com.ldp.security.util.xml;

import java.util.List;

import com.ldp.security.report.domain.DangerousObjectItem;
import com.ldp.security.report.domain.SpecialCodeItem;

/**
 * 数据字典类
 * @author Administrator
 *
 */
public class DataDictConfig {

	//危险品项目类列表
	private List<DangerousObjectItem> dangerousObjectItemList;

	//危险品项目数量
	private int dangerousObjectItemCount;

	//查缉一体化项目列表
	private List<SpecialCodeItem> specialCodeItemList;

	//查缉一体化项目数量
	private int specialCodeItemCount;

	public List<DangerousObjectItem> getDangerousObjectItemList() {
		return dangerousObjectItemList;
	}

	public void setDangerousObjectItemList(
			List<DangerousObjectItem> dangerousObjectItemList) {
		this.dangerousObjectItemList = dangerousObjectItemList;
	}

	public int getDangerousObjectItemCount() {
		return dangerousObjectItemCount;
	}

	public void setDangerousObjectItemCount(int dangerousObjectItemCount) {
		this.dangerousObjectItemCount = dangerousObjectItemCount;
	}

	public List<SpecialCodeItem> getSpecialCodeItemList() {
		return specialCodeItemList;
	}

	public void setSpecialCodeItemList(List<SpecialCodeItem> specialCodeItemList) {
		this.specialCodeItemList = specialCodeItemList;
	}

	public int getSpecialCodeItemCount() {
		return specialCodeItemCount;
	}

	public void setSpecialCodeItemCount(int specialCodeItemCount) {
		this.specialCodeItemCount = specialCodeItemCount;
	}
	
}
