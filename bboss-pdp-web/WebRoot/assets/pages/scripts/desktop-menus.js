var DesktopMenus = function() {

    var gotoworkspace = function(divid,url,menuid) {
    	// var reg=new RegExp("^#");     
    	//if(!reg.test(divid)) divid = '#'+divid;
    	//App.startPageLoading({message: '努力加载中...'});
    	App.blockUI({message: '努力加载中...'});
        $(".page-content").load(url,function(responseTxt,statusTxt,xhr){
        	//App.stopPageLoading();
        	//App.unblockUI();
        	window.setTimeout(function() {
               // App.stopPageLoading();
        		App.unblockUI();
            }, 10);
             
              if(statusTxt=="error" )
              {
            	  if(xhr.status == 403)
	    		  {
            		  if(menuid != '')
            			  $(".page-content").load('../jsp/common/403.jsp?menupath_menuid='+menuid);
            		  else
            			  $(".page-content").load('../jsp/common/403.jsp');
	    		  }
            	  else if(xhr.status > 400  &&  xhr.status < 500)
	    		  {
            		  if(menuid != '')
            			  $(".page-content").load('../jsp/common/404.jsp?menupath_menuid='+menuid);
            		  else
            			  $(".page-content").load('../jsp/common/404.jsp');
	    		  }
            	  else if(xhr.status >= 500 && xhr.status < 600)
	    		  {
            		  if(menuid != '')
            			  $(".page-content").load('../jsp/common/500.jsp?menupath_menuid='+menuid);
            		  else
            			  $(".page-content").load('../jsp/common/500.jsp');
	    		  }
            	  
              }
            	  
            });
        
    }
    
    var initTheme = function()
    {
    	Demo.init(); // init metronic core componets
    }

 
  

    return {
        //main function to initiate the module
    	gotoworkspace: function(divid,url,menuid) {

    		gotoworkspace(divid,url,menuid);

        },
    
        initTheme: function() {
	
        	initTheme();
	
	    },
	    changeFrameHeight:function(id){
	    	//alert($(".page-content").height());
	    	//alert($(".page-content").width());
	    	//alert($(".page-content").outerHeight());
	    	//alert($(".page-content").outerWidth());
	    //	var height = App.getViewPort().height - $('.page-header').outerHeight(true);
//$('#product-source').css('height', height);
	    	$("#"+id).height($(".page-content").height());
	    	// $("#"+id).height(height);
	    	 $("#"+id).width($(".page-content").width()+15);
	    	 
	    	//parent.height=id.document.body.scrollHeight+10;
	    	//id.document.body.scrollWidth = parent.width;
	    }

    };

}();

