/*     */ package org.frameworkset.platform.desktop.action;
/*     */ 
/*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;



/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;


/*     */ 
/*     */ 
/*     */
/*     */public class MenuController
/*     */ {
/*  33 */   private static final Logger log = Logger.getLogger(MenuController.class);

/*     */ 		
/*     */   public String initMenu(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*     */    
/*  69 */     return null;
/*     */   }
/*     */   public String initMenuMain(HttpServletRequest request) throws Exception {
/*  72 */     return "path:initMenuMain";
/*     */   }
/*     */   public String initMenuAdd(HttpServletRequest request) throws Exception {
/*  75 
/*  87 */     return "path:initMenuAdd";
/*     */   }
/*     */   public void initMenuTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  90 */    
/*     */   }
/*     */    
/*     */ 
/*     */  
/*     */ 
/*     */   public void saveModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 129 */    
/* 130 */     response.getWriter().println("success");
/*     */   }
/*     */ 
/*     */   public void saveAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 134 */     
/* 135 */     response.getWriter().println("success");
/*     */   }
/*     */ 
/*     */   public String loadDesk(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 163 */     return "path:loadDesk";
/*     */   }
/*     */ 
/*     */   public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 167 */     String id = request.getParameter("id");
/* 168 */     if ((id != null) && (!"".equals(id))) {
/* 169 */     
/* 170 */       response.getWriter().println("success");
/*     */     } else {
/* 172 */       response.getWriter().println("error");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void loadLeftTreeMenu(HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 252 */     response.setCharacterEncoding("UTF-8");
/* 253 */     
/* 255 */     String parentId = request.getParameter("parentId");
/* 256 */     if ((parentId != null) && (!"".equals(parentId))) {
/*     */       
/* 263 */           response.getWriter().println("");
/*     */       
/*     */     }
/*     */     else
/* 270 */       response.getWriter().println("error");
/*     */   }
/*     */ }

/* Location:           D:\workspace3\radar\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.system.sm.menu.web.MenuController
 * JD-Core Version:    0.6.2
 */