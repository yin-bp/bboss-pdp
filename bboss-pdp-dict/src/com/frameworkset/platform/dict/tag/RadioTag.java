
package com.frameworkset.platform.dict.tag;

import javax.servlet.jsp.JspException;

import com.frameworkset.common.ecs.Input;
import com.frameworkset.platform.dict.core.Param; 

/**
 * @author biaoping.yin
 * 2004-8-2
 */
public class RadioTag extends DictBaseTag
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

			 
			for(int i = 0; i < data.size(); i ++)
			{
				Input input = new Input();
				Param item = data.getParam(i);
				//设置了权限过滤
//				if(this.isCheckPermission() && !super.accesscontroler.checkPermission(ids,this.getOpcode(),"orgTaxcode")){
//			    	continue;
//			    }
				 
				input.setType(Input.RADIO)
					 .setName(getName())
					 .setValue(item.getValue())
					 .setChecked(t_value != null && t_value.equals(item.getValue())).setExtend(this.getExtend());
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
		// TODO Auto-generated method stub
		
		return super.doEndTag();
	}
	@Override
	public void doFinally() {
		this.vertical = null;
		super.doFinally();
	}
}
