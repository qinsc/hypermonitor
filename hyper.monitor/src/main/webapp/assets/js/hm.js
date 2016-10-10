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


