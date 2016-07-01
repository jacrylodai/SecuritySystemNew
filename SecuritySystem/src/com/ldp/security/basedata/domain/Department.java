package com.ldp.security.basedata.domain;

/**
 * 部门类
 * @author Administrator
 *
 */
public class Department {
	
	/**
	 * 根节点的id
	 */
	public static final long ID_ROOT = 0;

	/**
	 * 根节点
	 */
	public static final int LEVEL_ROOT = 0;
	
	/**
	 * 全国
	 */
	public static final int LEVEL_COUNTRY = 1;
	
	/**
	 * 级别，路局
	 */
	public static final int LEVEL_RAILWAY = 2;
	
	/**
	 * 级别，车站
	 */
	public static final int LEVEL_STATION = 3;
	
	/**
	 * 级别，车间
	 */
	public static final int LEVEL_DEPARTMENT = 4;

	//id
	private long departmentId;

	//上级部门
	private Department parentDepartment;

	//部门级别，分别是：路局，车站，车间
	private int level;

	//是否删除
	private int isDelete;
	
	//部门名称
	private String departmentName;

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public boolean equals(Object obj) {
		
		Department target = null;
		if(obj instanceof Department){
			target = (Department)obj;
		}else{
			return false;
		}
		
		return departmentId == target.getDepartmentId();
	}

	/**
	 * 比较当前部门是否比目标部门级别更高，如车站比车间的级别高
	 * @param targetDepartment
	 * @return
	 */
	public boolean higherLevelThan(Department targetDepartment) {
		
		if(level<targetDepartment.getLevel()){
			return true;
		}else{
			return false;
		}
	}
		
}
