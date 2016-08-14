(function ($) {
    $(document).ready(function () {
		$("#navbar").click(function(){
			if($(this).hasClass('closed')){
				$('.navbar-side').animate({left: '0px'});
				$(this).removeClass('closed');
				$('#page-wrapper').animate({'margin-left' : '220px'});
			}
			else{
			    $(this).addClass('closed');
				$('.navbar-side').animate({left: '-220px'});
				$('#page-wrapper').animate({'margin-left' : '0px'}); 
			}
		});
	});
}(jQuery));

var language = {
		"sProcessing": "处理中...",
        "sLengthMenu": "显示&nbsp; _MENU_  &nbsp;项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第&nbsp; 0 &nbsp;至&nbsp; 0 &nbsp;项结果，共&nbsp; 0 &nbsp;项",
        "sInfoFiltered": "(由&nbsp; _MAX_ &nbsp;项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索&nbsp;&nbsp;",
        "sUrl": "",
        "sEmptyTable": "无数据",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    };

function initDatatables(tableId, option){
	option = $.extend(true, {"language": language}, option);
	$('#'+ tableId).DataTable(option);
}
