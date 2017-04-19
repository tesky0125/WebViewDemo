!function(){
	// js注册方法供java调用
	service.onalert(function(params){
		alert( '------\n' + JSON.stringify(params) + '\n------\n');
		return {'result': 'alert ok'};
	});

	var btn = document.createElement('button');
	btn.innerHTML = '调用native toast';
	btn.onclick=function(){
		// js调用java注册的messageboxt方法
		service.toast({'text': '你好, messagebox!'}, function(response){
			alert('调用toast回来啦\n' + JSON.stringify(response));
		});
	}
	document.body.appendChild(btn);

	var btn = document.createElement('button');
    	btn.innerHTML = '页面跳转push_view';
    	btn.onclick=function(){
    		service.pushView({'page_url': 'file:///android_asset/index.html'}, function(response){
    			console.log('调用push_view回来啦\n' + JSON.stringify(response));
    		});
    	}
    	document.body.appendChild(btn);
}();