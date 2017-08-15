/*
 * @(#)OrgTreeLevel.java
 * 
 * Copyright @ 2001-2012 bbossgroups Co.,Ltd.
 * All right reserved.
 * 
  
 */
package com.frameworkset.platform.admin.service;

import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.RollbackException;

import org.apache.log4j.Logger;

import com.frameworkset.common.poolman.PreparedDBUtil;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.SmOrganization;

/**
 * 构建组织机构树层级
 * @author yinbiaoping 
 * @version  
 */
public class OrgTreeLevel {

    public static final String TREE_BASE = "0";
    
    public static final String CUT_UP = "|";
    
    private static Logger logger = Logger.getLogger(OrgTreeLevel.class);
    
    /**
     * 构建组织机构树层级
     */
    public static void run(SmOrganizationService organizationService) {
        
        //获取组织机构数据并转换为map
     
        List<SmOrganization> orgList = organizationService.getAllOrgs(); 
        LinkedHashMap<String, SmOrganization> orgMap = new LinkedHashMap<String, SmOrganization>();
        for (SmOrganization temp : orgList) {
            orgMap.put(temp.getOrgId(), temp);
        }

        //构建层级关系
        for (String temp : orgMap.keySet()) {
            try {
                getTreeLevel(orgMap, temp);
            } catch(Exception e) {
                logger.error("find [" + orgMap.get(temp).getOrgId() + "] tree level", e);
            }
        }
        
        //回写数据库
        TransactionManager tm = new TransactionManager();
        PreparedDBUtil pre = new PreparedDBUtil();
        try {
            tm.begin(TransactionManager.RW_TRANSACTION);
            pre.setBatchOptimize(true);
            pre.preparedUpdate("update td_sm_organization set org_tree_level=? where org_id=?");
            
            for (String temp : orgMap.keySet()) {
                pre.setString(1, orgMap.get(temp).getOrgTreeLevel());
                pre.setString(2, orgMap.get(temp).getOrgId());
                pre.addPreparedBatch();
            }
            
            pre.executePreparedBatch();
            tm.commit();
            
        } catch (Throwable e) {
            try {
                tm.rollback();
            } catch (RollbackException e1) {
                logger.error("transaction manager roll back error", e1);
            }
            logger.error("update organization tree level info error", e);
        }
        
    }

    /**
     * 递归求出父结构层级，并保存
     * @param temp
     */
    private static void getTreeLevel(LinkedHashMap<String, SmOrganization> orgMap, String key) {
        
        SmOrganization temp = orgMap.get(key);
        if(temp == null)
        {
        	return;
        }
        if (temp.getOrgTreeLevel() != null && temp.getOrgTreeLevel().equals("")) {
            return;
        } else if (temp.getParentId().equals(TREE_BASE)) {
            temp.setOrgTreeLevel(TREE_BASE + CUT_UP + temp.getOrgId());
        } else {
            getTreeLevel(orgMap, temp.getParentId());
            if(orgMap.get(temp.getParentId()) != null)
            {
            	temp.setOrgTreeLevel(orgMap.get(temp.getParentId()).getOrgTreeLevel() + CUT_UP + temp.getOrgId());
            }
            else
            {
            	temp.setOrgTreeLevel(TREE_BASE + CUT_UP + temp.getOrgId());
            }
        }
        
    }
   
    
    public static void main(String[] args) {
//        OrgTreeLevel.run();
    }
    
    
}
