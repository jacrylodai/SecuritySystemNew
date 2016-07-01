package com.ldp.security.basedata.domain;

/**
 * 权限类
 * @author jacrylodai
 *
 */
public class RoleResourceAuthority {

	//权限id
	private long roleResourceAuthorityId;

	//对应角色
	private Role role;

	//对应资源
	private Resource resource;
	
	//权限代码，用于保存create,retrieve,update,delete,showMenu,other
	//等是否可以访问的状态
	private int authorityCode;
	
	public RoleResourceAuthority(){
		//初始化值为0，默认所有权限都为false;
		this.authorityCode = 0;
	}

	public long getRoleResourceAuthorityId() {
		return roleResourceAuthorityId;
	}

	public void setRoleResourceAuthorityId(long roleResourceAuthorityId) {
		this.roleResourceAuthorityId = roleResourceAuthorityId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	private void setPermission(int permission,boolean allowed){
		int temp=1;
		temp=temp<<permission;
		if(allowed){
			authorityCode|=temp;
		}else{
			temp=~temp;
			authorityCode&=temp;
		}
	}
	
	public boolean isPermission(int permission){
		int temp=1;
		//check it`s tri state
		temp=temp<<permission;
		int testLogical=temp&authorityCode;
		if(testLogical==0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isShowMenu(){
		return isPermission(Permission.SHOW_MENU);
	}
	
	public boolean isCreate(){
		return isPermission(Permission.CREATE);
	}
	
	public boolean isRetrieve(){
		return isPermission(Permission.RETRIEVE);
	}
	
	public boolean isUpdate(){
		return isPermission(Permission.UPDATE);
	}
	
	public boolean isDelete(){
		return isPermission(Permission.DELETE);
	}
	
	public boolean isOther(){
		return isPermission(Permission.OTHER);
	}
	
	public void setShowMenu(boolean allowed){
		setPermission(Permission.SHOW_MENU, allowed);
	}
	
	public void setCreate(boolean allowed){
		setPermission(Permission.CREATE, allowed);
	}
	
	public void setRetrieve(boolean allowed){
		setPermission(Permission.RETRIEVE, allowed);
	}
	
	public void setUpdate(boolean allowed){
		setPermission(Permission.UPDATE, allowed);
	}
	
	public void setDelete(boolean allowed){
		setPermission(Permission.DELETE, allowed);
	}
	
	public void setOther(boolean allowed){
		setPermission(Permission.OTHER, allowed);
	}
	
}
