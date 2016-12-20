
package com.frameworkset.platform.dict.tag;

import java.util.List;

import javax.servlet.jsp.JspException;

import com.frameworkset.common.ecs.Input;
import com.frameworkset.platform.dict.core.Param;


/**
 * @author biaoping.yin
 * 2004-8-2
 */
public class CheckboxTag extends DictBaseTag
{
	protected String vertical;
	/* (non-Javadoc)
	 * @see com.westerasoft.common.tag.BaseTag#generateContent()
	 */
	public String generateContent()
	{

		if(data != null)
		{
			StringBuffer ret = new StringBuffer();
			List<String> defaultValues = super.selected();
//			在父类中初始化
//			AccessControl control = AccessControl.getInstance();
//			control.checkAccess(request,response);
			

			for(int i = 0; i < data.size(); i ++)
			{
				Input input = new Input();
				Param item = data.getParam(i);
//				if(item.getDataValidate() == 0) continue;
				//设置了权限过滤
//			    if(this.isCheckPermission() && !super.accesscontroler.checkPermission(ids,this.getOpcode(),"orgTaxcode")){
//			    	continue;
//			    }
				
				input.setType(Input.CHECKBOX)
					 .setName(getName())
					 .setValue(item.getValue())
					 .setChecked(selected(defaultValues,item.getValue())).setExtend(this.getExtend());
				input.setDisabled(this.isDisabled());
	
				if(getStyle() != null)
								input.setStyle(getStyle());
			    input.setTagText(item.getName());
			    ret.append(input.toString());
			    if(getVertical().trim().equalsIgnoreCase("true"))
			    {
			    	ret.append("<br>");
			    }
			}
			return ret.toString();
		}
		else
		{
			return "字典[" + this.type + "]不存在";
		}
	}
	
//	private boolean isChecked(String[] defaultValues,String value)
//	{
//	    
//	    for(int i = 0; defaultValues != null && i < defaultValues.length; i ++)
//	    {
//	        if(value.equals(defaultValues[i]))
//	            return true;
//	    }
//	    return false;
//	}
	/**
	 * @return
	 */
	public String getVertical()
	{
		return vertical == null? "false":vertical;
	}

	/**
	 * @param string
	 */
	public void setVertical(String string)
	{
		vertical = string;
	}

	@Override
	public int doEndTag() throws JspException {
		
		return super.doEndTag();
	}

	@Override
	public void doFinally() {
		// TODO Auto-generated method stub
				this.vertical = null;
		super.doFinally();
	}
	
	

}
