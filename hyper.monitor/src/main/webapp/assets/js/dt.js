var DT = function() {
    var tableOptions; // main options
    var dataTable; // datatable object
    var table; // actual table jquery object
    var tableContainer; // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var the;

    var countSelectedRecords = function() {
        var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        if (selected > 0 && text) {
            $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
        } else {
            $('.table-group-actions > span', tableWrapper).text("");
        }
    };

    return {

        //main function to initiate the module
        init: function(options) {

            if (!$().dataTable) {
                return;
            }

            the = this;

            // default settings
            options = $.extend(true, {
                src: "", // actual table  
                showCheckBox:true,
                filterApplyAction: "filter",
                filterCancelAction: "filter_cancel",
                resetGroupActionInputOnSuccess: true,
                idField:"id",//用来取值时获得
                loadingMessage: 'Loading...',
                dataTable: {
                	"dom": "<'row'<'col-md-8 col-sm-12 '<'table-group-actions '>><'col-md-4 col-sm-12 pull-right'f>r><'table-scrollable't><'row'<'col-md-8 col-sm-12'i><'col-md-4 col-sm-12'p>>",
                    "pageLength": 10, // default records per page
                    "language":{
                    	"sProcessing":   "处理中...",
                    	"sLengthMenu":   "显示 _MENU_ 项结果",
                    	"sZeroRecords":  "没有匹配结果",
                    	"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                    	"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
                    	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                    	"sInfoPostFix":  "",
                    	"sSearch":       "搜索:",
                    	"sUrl":          "",
                    	"sEmptyTable":     "表中数据为空",
                    	"sLoadingRecords": "载入中...",
                    	"sInfoThousands":  ",",
                    	"oPaginate": {
                    		"sFirst":    "首页",
                    		"sPrevious": "上页",
                    		"sNext":     "下页",
                    		"sLast":     "末页"
                    	},
                    	"oAria": {
                    		"sSortAscending":  ": 以升序排列此列",
                    		"sSortDescending": ": 以降序排列此列"
                    	}
                    },
                    "orderCellsTop": false,
                    "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                        'orderable': false,
                        'targets': [0]
                    }],
                    "order": [],
                    "pagingType": "full_numbers", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                    "autoWidth": false, // disable fixed width and enable fluid table
                    "processing": false, // enable/disable display message box on record load
                    "serverSide": false, // enable/disable server side ajax loading

                    "ajax": { // define ajax settings
                        "url": "", // ajax URL
                        "type": "POST", // request type
                        "timeout": 20000,
                        "data": function(data) { // add request parameters before submit
                            $.each(ajaxParams, function(key, value) {
                                data[key] = value;
                            });
                        },
                        "dataSrc": function(res) { // Manipulate the data returned from the server
                        	var data = res.data;
                        	if(data && data.length > 0){
                        		for(var i = 0 ; i < data.length; i++){
                        			var obj = data[i];
                        			if(obj){
                        				if(tableOptions.showCheckBox){
                        					obj.check = "<input type=\"checkbox\" name=\"id[]\" value="+obj[tableOptions.idField]+">";
                        				}
                        				obj.DT_RowId = obj[tableOptions.idField];
                        			}
                        		}
                        	}

                            if (res.customActionStatus) {
                                if (tableOptions.resetGroupActionInputOnSuccess) {
                                    $('.table-group-action-input', tableWrapper).val("");
                                }
                            }

                            if ($('.group-checkable', table).size() === 1) {
                                $('.group-checkable', table).attr("checked", false);
                                $.uniform.update($('.group-checkable', table));
                            }

                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, the, res);
                            }

                            return res.data;
                        },
                        "error": function() { // handle general connection errors
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, the);
                            }
                        }
                    },

                    "drawCallback": function(oSettings) { // run some code on table redraw
                        if (tableInitialized === false) { // check if table has been initialized
                            tableInitialized = true; // set table initialized
                            table.show(); // display table
                        }
                        countSelectedRecords(); // reset selected records indicator

                        // callback for ajax data load
                        if (tableOptions.onDataLoad) {
                            tableOptions.onDataLoad.call(undefined, the);
                        }
                    }
                }
            }, options);

            tableOptions = options;

            // create table's jquery object
            table = $(options.src);
            tableContainer = table.parents(".table-container");

            // apply the special class that used to restyle the default datatable
            var tmp = $.fn.dataTableExt.oStdClasses;

            //$.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper + " dataTables_extended_wrapper";
            $.fn.dataTableExt.oStdClasses.sFilterInput = "form-control ";
            $.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";

            // initialize a datatable
            dataTable = table.DataTable(options.dataTable);

            // revert back to default
            $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
            $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
            $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

            // get table wrapper
            tableWrapper = table.parents('.dataTables_wrapper');

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
                $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
            }
            // handle group checkboxes check/uncheck
            $('.group-checkable', table).change(function() {
                var set = $('tbody > tr > td:nth-child(1) input[type="checkbox"]', table);
                var checked = $(this).is(":checked");
                $(set).each(function() {
                    $(this).attr("checked", checked);
                });
                $.uniform.update(set);
                countSelectedRecords();
            });

            // handle row's checkbox click
            table.on('change', 'tbody > tr > td:nth-child(1) input[type="checkbox"]', function() {
            	var checked = $(this).is(":checked");
            	if(!checked){
            		$('.group-checkable', table).attr("checked", checked)
            	}
            	$.uniform.update($('.group-checkable', table));
                countSelectedRecords();
            });

            // handle filter submit button click
            table.on('click', '.filter-submit', function(e) {
                e.preventDefault();
                the.submitFilter();
            });

            // handle filter cancel button click
            table.on('click', '.filter-cancel', function(e) {
                e.preventDefault();
                the.resetFilter();
            });
            
            
            var _html = $(".dataTables_filter",tableWrapper);
            var searchBox = $(".form-control",_html);
            searchBox.attr("placeholder","筛选");
            var searchImg = '<span class="input-group-addon"><i class="fa fa-search"></i></span>';
            _html.addClass("input-group");
            _html.append(searchBox);
            _html.append(searchImg);
            $("label",_html).remove();
            
        },

        submitFilter: function() {
            the.setAjaxParam("action", tableOptions.filterApplyAction);

            // get all typeable inputs
            $('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', table).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all checkboxes
            $('input.form-filter[type="checkbox"]:checked', table).each(function() {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input.form-filter[type="radio"]:checked', table).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            dataTable.ajax.reload();
        },

        resetFilter: function() {
            $('textarea.form-filter, select.form-filter, input.form-filter', table).each(function() {
                $(this).val("");
            });
            $('input.form-filter[type="checkbox"]', table).each(function() {
                $(this).attr("checked", false);
            });
            the.clearAjaxParams();
            the.addAjaxParam("action", tableOptions.filterCancelAction);
            dataTable.ajax.reload();
        },

        getSelectedRowsCount: function() {
            return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        },

        getSelectedRows: function() {
            var rows = [];
            var data = dataTable.rows().data();
            var rowDataMap = {};
            if(data){
                if(data && data.length > 0){
                	for(var i = 0 ; i < data.length; i++){
                		var key = data[i][tableOptions.idField];
                		if(key){
                			rowDataMap[key] = data[i];
                		}
                	}
                }

            }
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function() {
            	if($(this).val() && rowDataMap[$(this).val()]){
            		rows.push(rowDataMap[$(this).val()]);
            	}
            });

            return rows;
        },

        setAjaxParam: function(name, value) {
            ajaxParams[name] = value;
        },

        addAjaxParam: function(name, value) {
            if (!ajaxParams[name]) {
                ajaxParams[name] = [];
            }

            skip = false;
            for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
                if (ajaxParams[name][i] === value) {
                    skip = true;
                }
            }

            if (skip === false) {
                ajaxParams[name].push(value);
            }
        },

        clearAjaxParams: function(name, value) {
            ajaxParams = {};
        },

        getDataTable: function() {
            return dataTable;
        },

        getTableWrapper: function() {
            return tableWrapper;
        },

        gettableContainer: function() {
            return tableContainer;
        },

        getTable: function() {
            return table;
        },
        
        reload:function(url){
        	if(url){
        		dataTable.ajax.url(url).load();
        	}else{
        		dataTable.ajax.reload();
        	}
        	
        },
        
        getRowData:function(rowId){
        	var data = dataTable.row("#"+rowId).data();
        	return data;
        }

    };

};