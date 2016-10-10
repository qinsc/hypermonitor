var hostGrid = new Datatable();
var contextPath;
function initEventDatatables(ctxPath, tableId){
	contextPath = ctxPath;
	hostGrid.init({
        src: $("#"+tableId),
        idField:"eventId",
        dataTable: {
        	dom: 'rtip',           
             "processing": true,
             "pageLength": 10,
             "deferLoading": 0
        }
    });
}

function loadNumbers(){
	$.ajax({
		url: contextPath+"/rest/dashboard/numbers",
		type: "get",
		contentType: "application/json",
		success: function(info){
			$("#numbers #hosts").html(info.hosts);
			$("#numbers #onlineHosts").html(info.onlineHosts);
			$("#numbers #hostGroups").html(info.hostGroups);
			$("#numbers #hostTags").html(info.hostTags);
		}
	});
}

function loadEvents(){
	
}