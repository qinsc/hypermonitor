var hostGroupEditGrid = new Datatable();
function initHostGroupEditDatatables(){
	hostGroupEditGrid.init({
        src: $("#hostGroupEditTable"),
        idField:"groupId",
        dataTable: {
        	 dom: 'rtip',
        	 "processing": true,
             "pageLength": 5,
             showCheckBox: false,
             "deferLoading": 0,
             "ajax": {
            	 url:contextPath+"/rest/hostgroups/",
            	 type:"GET"
             }, 
             "columns":[
                 {"data" : "groupName","orderable": true},  
                 {"data" : "groupDesc", "orderable": false}  
             ],
        }
    });
	
	$('#hostGroupEditTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
        	hostGroupEditGrid.getDataTable().$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
}

var hostGroupMgrGrid = new Datatable();
function initHostGroupMgrDatatables(){
	hostGroupMgrGrid.init({
        src: $("#hostGroupMgrTable"),
        idField:"groupId",
        dataTable: {
        	 dom: 'fBrtip',
        	 "processing": true,
             "pageLength": 5,
             "deferLoading": 0,
             "ajax": {
            	 url:contextPath+"/rest/hostgroups/",
            	 type:"GET"
             }, 
             buttons: [ 
                      {
     		              text: '添加',
     		              action: function ( e, dt, node, config ) {
     		            	  showAddHostGroup();
     		              }
     		          },
     		          {
     		              text: '删除',
     		              action: function ( e, dt, node, config ) {
     		            	 doDeleteHostGroup();
     		              }
     		          }
     		      ],
             "columns":[
                 {"data":"check"},
                 {"data" : "groupName","orderable": true},  
                 {"data" : "groupDesc", "orderable": false},
                 { render: render_host_group_operations}
             ],
        }
    });
	
//	$('#hostGroupMgrTable tbody').on( 'click', 'tr', function () {
//        if ( $(this).hasClass('selected') ) {
//            $(this).removeClass('selected');
//        } else {
//        	hostGroupMgrGrid.getDataTable().$('tr.selected').removeClass('selected');
//            $(this).addClass('selected');
//        }
//    });
}

function render_host_group_operations(data, type, row){
	var OperationHtml = 
		'<div class="btn-group">' +    
		'	<button data-toggle="dropdown"' + 
		'		class="btn btn-info dropdown-toggle" aria-expanded="false">' +
		'		操作 <span class="caret"></span>' +
		'	</button>' +
		'	<ul class="dropdown-menu dropdown-menu-right" style="min-width:90px;">' +
		'		<li><a id="'+row.groupId+'" onclick="showEditHostGroup(this)"">修改</a></li>' +   
		'		<li><a id="'+row.groupId+'" onclick="doDeleteHostGroup(this)">删除</a></li>' +
		'	</ul>' + 
		'</div>'; 
	return OperationHtml;                                                          
}

function editGroup(){
	var hostIds = getselectHostIds();
	if (hostIds.length == 0){
		$.messager.alert("提示", "请选择要操作的主机");
		return;
	}
	
	hostGroupEditGrid.setAjaxFinishCallback(function(){
		hostGroupEditGrid.setSelectedSingleRow(0);
	});
	
	hostGroupEditGrid.reload();
    $('#hostGroupEditModal').modal({keyboard:false,show:true});
}

$(document).delegate("#hostGroupEditModal  #btn_edit_hosts_group","click",function(){
	var selectedGroup = hostGroupEditGrid.getSelectedSingleRow();
	if (selectedGroup == null){
		$.messager.alert("提示", "请选择一个主机组");
	} else {
		var hostIds = getselectHostIds();
		if (hostIds.length == 0){
			$.messager.alert("提示", "请选择要操作的主机");
			return;
		}
		
		$.messager.confirm("确认", "确定要将主机组修改为" + selectedGroup.groupName + "吗?", function() { 
			var data = {};
			data["hostIds"] = getselectHostIds();
			data["groupId"] = selectedGroup.groupId;
			
			$.ajax({
				url: contextPath+"/rest/hosts/group/edit",
				type: "post",
				contentType: "application/json",
				data:JSON.stringify(data),
				success: function(ret){
					$('#hostGroupEditModal').modal("hide");
					hostGrid.reload();
				},
				error: function(ret){
					$.messager.alert("消息", "主机组修改失败");
				}
			});
		});
	}
});

function mgrGroup(){
	hostGroupEditGrid.reload();
	showMgrHostGroup();
    $('#hostGroupMgrModal').modal({keyboard:false,show:true});
}

function showMgrHostGroup(){
	$('#hostGroupMgrModal #hostGroupMgrModalLabel').html("主机组管理");
	
	$('#hostGroupMgrModal #tableDiv').css("display","block");
	$('#hostGroupMgrModal #formDiv').css("display","none");
	
	$('#hostGroupMgrModal #btn_action').css("display","none");
	$('#hostGroupMgrModal #btn_cancel').css("display","none");
	$('#hostGroupMgrModal #btn_dismiss').css("display","inline-block");
}

function showAddHostGroup(){
	$('#hostGroupMgrModal #hostGroupMgrModalLabel').html("添加主机组");
	
	$('#hostGroupMgrModal #groupId').val("");
	$('#hostGroupMgrModal #groupName').val("");
	$('#hostGroupMgrModal #groupDesc').val("");
	$('#hostGroupMgrModal #groupName').prop("disabled", false);
	
	$('#hostGroupMgrModal #tableDiv').css("display","none");
	$('#hostGroupMgrModal #formDiv').css("display","block");
	
	$('#hostGroupMgrModal #btn_action').html("添加");
	$('#hostGroupMgrModal #btn_action').css("display","inline-block");
	$('#hostGroupMgrModal #btn_cancel').css("display","inline-block");
	$('#hostGroupMgrModal #btn_dismiss').css("display","none");
}

function showEditHostGroup(row){
	$('#hostGroupMgrModal #hostGroupMgrModalLabel').html("修改主机组");
	
	var hostGroup = hostGroupMgrGrid.getRowData(row.id);
	$('#hostGroupMgrModal #groupId').val(hostGroup.groupId);
	$('#hostGroupMgrModal #groupName').val(hostGroup.groupName);
	$('#hostGroupMgrModal #groupDesc').val(hostGroup.groupDesc);
	$('#hostGroupMgrModal #groupName').prop("disabled", true);
	
	$('#hostGroupMgrModal #tableDiv').css("display","none");
	$('#hostGroupMgrModal #formDiv').css("display","block");
	
	$('#hostGroupMgrModal #btn_action').html("修改");
	$('#hostGroupMgrModal #btn_action').css("display","inline-block");
	$('#hostGroupMgrModal #btn_cancel').css("display","inline-block");
	$('#hostGroupMgrModal #btn_dismiss').css("display","none");
}

$(document).delegate("#hostGroupMgrModal  #btn_cancel","click",function(){
	showMgrHostGroup();
});

$(document).delegate("#hostGroupMgrModal  #btn_action","click",function(){
	var id = $('#hostGroupMgrModal #groupId').val();
	if (id == null || id == ""){
		doAddHostGroup();
	} else {
		doEditHostGroup();
	}
});

function getselectHostGroupIds(row){
	var hostGroupIds = [];
	if(row){
		hostGroupIds.push(row.id);
	} else {
		var selectdHosts = hostGroupMgrGrid.getSelectedRows();
		$.each(selectdHosts, function(i,val){
			hostGroupIds.push(val.groupId);
		})
	}
	return hostGroupIds;
}

function doDeleteHostGroup(row){
	var groupIds = getselectHostGroupIds(row);
	if (groupIds.length == 0){
		$.messager.alert("提示", "请选择要删除的主机组");
		return;
	}
	
	$.messager.confirm("确认", "确定要删除这些主机组吗?" , function() { 
		$.ajax({
			url: contextPath+"/rest/hostgroups/delete",
			type: "post",
			contentType: "application/json",
			data:JSON.stringify(groupIds),
			success: function(ret){
				hostGroupMgrGrid.reload();
				hostGrid.reload();
			},
			error: function(ret){
				$.messager.alert("消息", "主机组删除失败");
			}
		});
	});
}

function doEditHostGroup(){
	var group = {};
	group["groupId"] = $('#hostGroupMgrModal #groupId').val();
	group["groupName"] = $('#hostGroupMgrModal #groupName').val();
	group["groupDesc"] = $('#hostGroupMgrModal #groupDesc').val();
	
	$.messager.confirm("确认", "确定要修改主机组吗?" , function() { 
		$.ajax({
			url: contextPath+"/rest/hostgroups/update",
			type: "post",
			contentType: "application/json",
			data:JSON.stringify(group),
			success: function(ret){
				showMgrHostGroup();
				hostGroupMgrGrid.reload();
			},
			error: function(ret){
				$.messager.alert("消息", "主机组修改失败");
			}
		});
	});
}

function doAddHostGroup(){
	var group = {};
	group["groupId"] = $('#hostGroupMgrModal #groupId').val();
	group["groupName"] = $('#hostGroupMgrModal #groupName').val();
	group["groupDesc"] = $('#hostGroupMgrModal #groupDesc').val();
	
	if (group["groupName"] == null || group["groupName"]==""){
		$.messager.alert("提示", "主机组的名称不能为空");
		return;
	}
	
	if (group["groupDesc"] != null && group["groupDesc"].length>500){
		$.messager.alert("提示", "主机组的描述不能多于500字符");
		return;
	}
	
	$.ajax({
		url: contextPath+"/rest/hostgroups/add",
		type: "post",
		contentType: "application/json",
		data:JSON.stringify(group),
		success: function(ret){
			showMgrHostGroup();
			hostGroupMgrGrid.reload();
		},
		error: function(ret){
			$.messager.alert("消息", "主机组添加失败");
		}
	});
}