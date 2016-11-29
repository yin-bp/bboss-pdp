/**
 *  Copyright 2008-2010 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.frameworkset.platform.admin.entity;

import java.sql.Timestamp;
/**
 * <p>Title: SmOrganization</p> <p>Description: 机构管理管理服务实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-28 16:33:16 @author yinbp @version
 * v1.0
 */
public class SmOrganization implements java.io.Serializable {
	/**
	 * 流水号
	 */
	private String orgId;
	/**
	 * 主管部门
	 */
	private String chargeorgid;
	/**
	 * 下级部门
	 */
	private String children;
	/**
	 * 机构编码
	 */
	private String code;
	/**
	 * 创建时间
	 */
	private Timestamp creatingtime;
	/**
	 * 创建人
	 */
	private String creator;
	private long isdirectguanhu;
	private long isdirectlyparty;
	private long isforeignparty;
	private long isjichaparty;
	private long ispartybussiness;
	/**
	 * 简拼
	 */
	private String jp;
	/**
	 * 机构层级
	 */
	private String layer;
	/**
	 * 机构等级
	 */
	private String orgLevel;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 机制排序号
	 */
	private long orgSn;
	/**
	 * 机构树层次码
	 */
	private String orgTreeLevel;
	/**
	 * 行政区码
	 */
	private String orgXzqm;
	/**
	 * 机构描述
	 */
	private String orgdesc;
	/**
	 * 部门领导
	 */
	private String orgleader;
	/**
	 * 机构编号
	 */
	private String orgnumber;
	/**
	 * 父ID
	 */
	private String parentId;
	/**
	 * 机构路径
	 */
	private String path;
	/**
	 * 全拼
	 */
	private String qp;
	/**
	 * 备注1
	 */
	private String remark1;
	/**
	 * 备注2
	 */
	private String remark2;
	/**
	 * 备注3,标注部门是否启用：0-禁用 1-启用
	 */
	private String remark3;
	/**
	 * 备注4
	 */
	private String remark4;
	/**
	 * 备注5
	 */
	private String remark5;
	private String satrapjobid;
	public SmOrganization() {
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setChargeorgid(String chargeorgid) {
		this.chargeorgid = chargeorgid;
	}

	public String getChargeorgid() {
		return chargeorgid;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getChildren() {
		return children;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCreatingtime(Timestamp creatingtime) {
		this.creatingtime = creatingtime;
	}

	public Timestamp getCreatingtime() {
		return creatingtime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return creator;
	}

	public void setIsdirectguanhu(long isdirectguanhu) {
		this.isdirectguanhu = isdirectguanhu;
	}

	public long getIsdirectguanhu() {
		return isdirectguanhu;
	}

	public void setIsdirectlyparty(long isdirectlyparty) {
		this.isdirectlyparty = isdirectlyparty;
	}

	public long getIsdirectlyparty() {
		return isdirectlyparty;
	}

	public void setIsforeignparty(long isforeignparty) {
		this.isforeignparty = isforeignparty;
	}

	public long getIsforeignparty() {
		return isforeignparty;
	}

	public void setIsjichaparty(long isjichaparty) {
		this.isjichaparty = isjichaparty;
	}

	public long getIsjichaparty() {
		return isjichaparty;
	}

	public void setIspartybussiness(long ispartybussiness) {
		this.ispartybussiness = ispartybussiness;
	}

	public long getIspartybussiness() {
		return ispartybussiness;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getJp() {
		return jp;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getLayer() {
		return layer;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgSn(long orgSn) {
		this.orgSn = orgSn;
	}

	public long getOrgSn() {
		return orgSn;
	}

	public void setOrgTreeLevel(String orgTreeLevel) {
		this.orgTreeLevel = orgTreeLevel;
	}

	public String getOrgTreeLevel() {
		return orgTreeLevel;
	}

	public void setOrgXzqm(String orgXzqm) {
		this.orgXzqm = orgXzqm;
	}

	public String getOrgXzqm() {
		return orgXzqm;
	}

	public void setOrgdesc(String orgdesc) {
		this.orgdesc = orgdesc;
	}

	public String getOrgdesc() {
		return orgdesc;
	}

	public void setOrgleader(String orgleader) {
		this.orgleader = orgleader;
	}

	public String getOrgleader() {
		return orgleader;
	}

	public void setOrgnumber(String orgnumber) {
		this.orgnumber = orgnumber;
	}

	public String getOrgnumber() {
		return orgnumber;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setQp(String qp) {
		this.qp = qp;
	}

	public String getQp() {
		return qp;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setSatrapjobid(String satrapjobid) {
		this.satrapjobid = satrapjobid;
	}

	public String getSatrapjobid() {
		return satrapjobid;
	}

}