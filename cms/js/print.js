(function($) {
	$.fn.printMe = function(text_only) {
		// 获取元素内容
		var content = text_only ? $(this).text() : $(this).html();
		// 在页面创建 iframe
		$("body").append(
				'<iframe id="iframe-print" style="display: none;"></iframe>');
		// 获取 iframe window
		var ifrm = $("#iframe-print")[0].contentWindow;
		// 将内容写入 iframe
		ifrm.document.write(content);

		// IE
		if (navigator.userAgent.match(/MSIE/)) {
			ifrm.document.execCommand("print", false, null);
		}
		// Opera
		else if (navigator.userAgent.match(/Opera/)) {
			// Opera 需要打开新窗口
			var printWin = window.open(""), printDoc = printWin.document;
			printDoc.open();
			printDoc
					.write('<!DOCTYPE html><html><head></head><body onload="window.print(); window.close();">'
							+ content + '</body></html>');
			printDoc.close();
		}
		// Firefox/Chrome/Safari/其它浏览器
		else {
			ifrm.print();
		}
		// 释放 cache
		ifrm = null;
		// 移除 iframe
		$("#iframe-print").remove();
	};
})(jQuery);