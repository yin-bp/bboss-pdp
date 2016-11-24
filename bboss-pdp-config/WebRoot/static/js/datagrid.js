//全局变量
var sdpBtnAction;
var sdpData;
var sdpPage;
var pageAction;
//插件方法
(function ($) {
    $.fn.datagrid = function (options) {
        // 默认配置
        var defaults = {
            url: "", //数据获取链接
            table:{ //表格相关配置
                id: "default",
                head:[{
                    item: "item1",
                    name: "项目1",
                    sort: false
                },{
                    item: "item2",
                    name: "项目2",
                    sort: false
                },{
                    item: "item3",
                    name: "项目3",
                    sort: false
                }],
                showBtns: true,
                btns:[{
                    name: "查看",
                    todo: function(){}
                },{
                    name: "编辑",
                    todo: function(){}

                }]
            },
            page:{ //分页工具相关配置
                enable: true
            }
        };

        // 参数配置
        var opts = $.extend(defaults, options);
        var tableNode = $("#"+opts.table.id);
        var pageNode = $("#"+opts.page.id);
        // 清空内容
        loadData();

        // 方法
        // 创建表格头数据
        function createThead(){
            var html = "<thead><tr>";
            for(var i = 0; i < opts.table.head.length; i++){
                html += "<th>";
                html += opts.table.head[i].name;
                html += "</th>";
            }
            if(opts.table.showBtns){
                html += "<th>操作</th>";
            }
            html += "</tr></thead>";
            tableNode.append(html);
        }

        // 创建表格体数据
        function createTbody(data){
            var html = "<tbody>";
            for(var i = 0; data != null && i < data.length; i++){
                html += "<tr>";
                for(var j = 0; j < opts.table.head.length; j++){
                    html += "<td>";
                    html += data[i][opts.table.head[j].item];
                    html += "</td>";
                }
                if(opts.table.showBtns){
                    html += "<td>";
                    for(var j = 0; j < opts.table.btns.length; j++){
                        sdpBtnAction = function (callback,index){
                            callback(sdpData[index],index);
                        }
                        html += "<a href='javascript:sdpBtnAction("+opts.table.btns[j].todo+","+i+");' class='btn btn-xs btn-outline btn-default'>"+opts.table.btns[j].name+"</a> ";
                    }
                    html += "</td>";
                }
                html += "</tr>";
            }
            html += "</tbody>";
            tableNode.append(html);
        }

        // 创建分页栏
        function createPagination(data){
            if(opts.page.enable){
                var html = "<div class='sdp-page-before'>共<span class='page-val'>"
                    +data.pageCount+"</span>页<span class='page-val'>"
                    +data.recordCount+"</span>条数据</div>";
                html += "<ul class='pagination sdp-pagination'>";
                // 分页头
                if(data.currentPage <= 1){
                    html += "<li class='disabled'><a href='javascript:void(0);'>&laquo;</a></li>";
                }else{
                    html += "<li><a href='javascript:pageAction(1);'>&laquo;</a></li>";
                }
                // 分页内部
                var minPage = (data.currentPage - 3) < 1 ? 1 : (data.currentPage - 3);
                var maxPage = (data.currentPage + 3) > data.pageCount ? data.pageCount : (data.currentPage + 3);
                for(var i = minPage; i <= maxPage; i++){
                    if(i == data.currentPage){
                        html += "<li class='active'><a href='javascript:void(0);'>"+i+"</a></li>";
                    }else{
                        html += "<li><a href='javascript:pageAction("+i+");'>"+i+"</a></li>";
                    }
                }
                // 分页尾
                if(data.currentPage >= data.pageCount){
                    html += "<li class='disabled'><a href='javascript:void(0);'>&raquo;</a></li>";
                }else{
                    html += "<li><a href='javascript:pageAction("+data.pageCount+");'>&raquo;</a></li>";
                }
                html += "</ul>";
                pageNode.append(html);
            }
        }

        pageAction = function (index){
            tableNode.html("");
            pageNode.html("");
            if(opts.page.enable) {
                opts.data.currentPage = index;
                opts.data.pageSize = opts.page.pageSize;
            }
            $.ajax({
                type: opts.type,
                url: opts.url,
                data: opts.data,
                dataType: "json",
                success:function(data){
                    // 全局数据加载
                    sdpData = data.tbody;
                    sdpPage = data.page;
                    createThead();
                    createTbody(data.tbody);
                    createPagination(data.page);
                }
            });
        }

        //数据加载
        function loadData(){
            tableNode.html("");
            pageNode.html("");
            if(opts.page.enable){
                opts.data.currentPage = 1;
                opts.data.pageSize = opts.page.pageSize;
            }
            $.ajax({
                type: opts.type,
                url: opts.url,
                data: opts.data,
                dataType: "json",
                success:function(data){
                    // 全局数据加载
                    sdpData = data.tbody;
                    sdpPage = data.page;
                    createThead();
                    createTbody(data.tbody);
                    createPagination(data.page);
                }
            });
        }
    };
})(jQuery);