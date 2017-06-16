(function($, window, document, undefined) {
	
	/**
	 * 点击下拉显示
	 */
	$('.wrap1000').on("click", ".selectBox", function() {
		var ul = $(this).find("ul"),
			display = ul.css("display");
			
		display = display == "block" ? 0 : 1;
		
		$(".selectBox ul").css("display", "none");
		
		if (display) {
			ul.css("display", "block");
			display = 0;
			ul.find("li").each(function() {
				display += $(this).height();
			});
			ul.css("display", "none");
			
			if (display > 200) {
				ul.css("height",200);
				ul.css("overflow", "auto");
			}
			
			ul.slideDown(100);
		} else {
			ul.slideUp();
		}
		
		return false;
	});
	   
	/**
	 * 点击列表 文字和 value 上去
	 */
	$('.wrap1000').on("click", ".selectBox ul li", function() {
		var p = $(this).parent().parent().find("p");
		
		p.text($(this).find("a").text());
		p.attr("value", $(this).attr("value"));
	});
	
	$('.wrap1000').on("click", function() {
		$(".selectBox ul").slideUp();
	});
	
	
	
	
	
	
	
	$('.addJg').on("click", ".selectBox", function() {
		var ul = $(this).find("ul"),
			display = ul.css("display");
			
		display = display == "block" ? 0 : 1;
		
		$(".selectBox ul").css("display", "none");
		
		if (display) {
			ul.css("display", "block");
			display = 0;
			ul.find("li").each(function() {
				display += $(this).height();
			});
			ul.css("display", "none");
			
			if (display > 200) {
				ul.css("height",200);
				ul.css("overflow", "auto");
			}
			
			ul.slideDown(100);
		} else {
			ul.slideUp();
		}
		
		return false;
	});
	   
	/**
	 * 点击列表 文字和 value 上去
	 */
	$('.addJg').on("click", ".selectBox ul li", function() {
		var p = $(this).parent().parent().find("p");
		
		p.text($(this).find("a").text());
		p.attr("value", $(this).attr("value"));
	});
	
	$('.addJg').on("click", function() {
		$(".selectBox ul").slideUp();
	});
})(jQuery, window, document);
