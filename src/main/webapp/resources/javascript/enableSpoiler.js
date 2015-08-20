$( document ).ready(function() {
	$(".spoiler-trigger").click(function() {
		$(this).parent().next().collapse('toggle');
	});
});
// Spoiler 2
$.fn.ready(function() {
   
    $(document).on('click', '.spoiler-btn', function (e) {
        e.preventDefault()
        $(this).parent().children('.spoiler-body').collapse('toggle')
    });
});