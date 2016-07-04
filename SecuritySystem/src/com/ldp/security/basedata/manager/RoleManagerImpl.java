package com.ldp.security.basedata.manager;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.basedata.dao.RoleDao;
import com.ldp.security.basedata.domain.DataDict;
import com.ldp.security.basedata.domain.Role;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.database.ParameterObject;

public class RoleManagerImpl extends AbstractManager<Role>
	implements RoleManager {
	
	private RoleDao roleDao;
	
	private DataDictManager dataDictManager;

	public void setDataDictManager(DataDictManager dataDictManager) {
		this.dataDictManager = dataDictManager;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Role loadRoleById(long roleId) {

		return roleDao.loadRoleById(roleId);
	}

	public void saveRole(Role role) {

		roleDao.saveRole(role);
	}

	public void updateRole(Role role) {

		roleDao.updateRole(role);
	}
	
	public List<DataDict> getRoleTypeDataDictList(){
		
		return dataDictManager.getDataDictListByCategory(Role.ROLE_TYPE_DATA_DICT_CATEGORY);
	}

	public DataDict getRoleTypeDataDictById(String roleTypeDataDictId) {

		return dataDictManager.loadDataDictById(roleTypeDataDictId);
	}

	public PageModel<Role> listRoleInPage() {

		String hql = "from Role role" +
				" order by role.roleType.dataDictId asc,role.roleName asc";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		return findDataByHqlParameterListInPage(hql, paraObjList);
	}

	public List<Role> getRoleListByRoleTypeId(String roleTypeId) {

		String hql = 
			"from Role role" +
			" where role.roleType.dataDictId=:roleTypeId" +
			" order by role.roleName asc";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("roleTypeId",roleTypeId));
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

	public boolean checkIsRoleUsedByUser(long roleId) {

		String hql = "from User user where user.role.roleId=:roleId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("roleId",roleId));
		
		long count = getCountByHqlParameterListInPage(hql, paraObjList);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	public void deleteRole(Role role) {

		roleDao.deleteRole(role);
	}

}
