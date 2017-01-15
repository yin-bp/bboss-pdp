package org.frameworkset.platform.security;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.frameworkset.common.tag.BaseTag;

public class CheckPermissionTag extends BaseTag {
	private String resource;
	private String opcode;
	private String resourceType;
	@Override
	public void doFinally() {
		// TODO Auto-generated method stub
		super.doFinally();
		resource = null;
		opcode = null;
		resourceType = null;
	}

	@Override
	public int doStartTag() throws JspException {
		
		AccessControl control = AccessControl.getAccessControl();
		if(control == null || resource == null || opcode == null || resourceType == null || resource.equals("") || opcode .equals("")|| resourceType .equals(""))
		{
			return SKIP_BODY;
		}
		else
		{
			boolean haspermission = control.checkPermission(resource, opcode, resourceType);
			if(haspermission)
				return EVAL_BODY_INCLUDE;
			else 
				return SKIP_BODY;
				
		}
		 
	}

	 

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	

}
