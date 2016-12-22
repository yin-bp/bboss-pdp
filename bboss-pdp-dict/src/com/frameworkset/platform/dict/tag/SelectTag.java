
package com.frameworkset.platform.dict.tag;


import java.util.List;

import javax.servlet.jsp.JspException;

import org.frameworkset.util.I18NUtil;

import com.frameworkset.common.ecs.Option;
import com.frameworkset.common.ecs.Select;
import com.frameworkset.platform.dict.core.Param;

/**
 * @author biaoping.yin
 * 2004-8-2
 */
public class SelectTag extends DictBaseTag
{
	protected String textValue;
	/**
	 * 默认项的国际化值
	 */
	protected String textValueCode;
	
	/**
	 * 指定默认项的默认的值
	 */
	protected String textNAN = "NaN";
	protected boolean multiple;
	
	/* (non-Javadoc)
	 * @see com.westerasoft.common.tag.BaseTag#generateContent()
	 */
	public String generateContent()
	{
		Select select = new Select();
		select.setName(getName());
		select.setID(getName());
		select.setDisabled(this.isDisabled());
		select.setExtend(this.getExtend());
		select.setMultiple(multiple);
		
		if(this.data != null)
		{
			List<String> selected = null;
			if(this.multiple && this.t_value != null)
			{
				selected = selected();
			}
//			Option[] options = null;
//			int temp = 0;
			if(this.getTextValue() != null)
			{
				Option o = new Option();
				o = new Option().setValue(textNAN);
				o.setTagText(getTextValue());
				select.addElement(o);
			}
			else
			{
//				options = new Option[data.size()];
			}
			
			for(int i = 0 ; i < data.size() ; i ++)
			{				
				Param item = data.getParam(i );
//				if(item.getDataValidate() == 0) continue;
				//设置了权限过滤
//				if(this.isCheckPermission() && !super.accesscontroler.checkPermission(ids,this.getOpcode(),"orgTaxcode")){
				 
				Option option = new Option().setValue(item.getValue());
				if(this.multiple)
				{
					if(selected(selected,item.getValue()))
						option.setSelected(true);
				}
				else
				{
					if(this.t_value != null && String.valueOf(t_value).equals(item.getValue()))
					{
						option.setSelected(true);
					}
				}
				option.setTagText(item.getName());
//				options[i] = option;
				select.addElement(option);
				
			}
			
//			select.addElement(options);
			if(getStyle() != null)
				select.setStyle(getStyle());
			//buffer.append(data.getName());
			return select.toString();
		}
		else
		{
			Option option = new com.frameworkset.common.ecs.Option();
			option  = new Option().setValue("NaN");
			option.setTagText("字典[" + this.type + "]不存在");
			select.addElement(option);
			return select.toString();
		}
	}

	/**
	 * @return
	 */
	public String getTextValue()
	{
		if(textValue != null)
			return textValue;
		if(this.textValueCode != null)
			return textValue = I18NUtil.getI18nMessage(textValueCode, this.textValue,request);
		else
			return textValue;
	}

	/**
	 * @param string
	 */
	public void setTextValue(String string)
	{
		textValue = string;
	}
	
	public int doEndTag() throws JspException
	{
		int ret = super.doEndTag();
		
		return ret;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public String getTextValueCode() {
		return textValueCode;
	}

	public void setTextValueCode(String textValueCode) {
		this.textValueCode = textValueCode;
	}

	public String getTextNAN() {
		return textNAN;
	}

	public void setTextNAN(String textNAN) {
		this.textNAN = textNAN;
	}

	@Override
	public void doFinally() {
		this.textValue = null;
		this.multiple = false;
		textValueCode = null;
		textNAN = "NaN";
		super.doFinally();
	}
}
