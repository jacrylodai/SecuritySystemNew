package com.ldp.security.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.ldp.security.basedata.domain.User;
import com.ldp.security.basedata.manager.UserManager;
import com.ldp.security.system.actionform.LoginActionForm;
import com.ldp.security.util.encrypt.EncryptUtils;

public class SiteIndexAction extends DispatchAction{
	
	public ActionForward departmentSiteIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("departmentSiteIndex");
	}

	public ActionForward stationSiteIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("stationSiteIndex");
	}

	public ActionForward railwaySiteIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("railwaySiteIndex");
	}

	public ActionForward countrySiteIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("countrySiteIndex");
	}
	
}
