/**
 * 
 */
package org.frameworkset.platform.common;

/**
 * @author yinbp
 *
 * @Date:2016-11-28 20:21:23
 */
public class TreeNodeStage {
	private boolean	opened   ; // is the node open
	private boolean	disabled  ;  // is the node disabled
	private boolean	selected ; // is the node selected
	/**
	 * 
	 */
	public TreeNodeStage() {
		// TODO Auto-generated constructor stub
	}
	public boolean isOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
