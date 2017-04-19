!function(){
	// js注册方法供java调用
	service.onalert(function(params){
		alert( '------\n' + JSON.stringify(params) + '\n------\n');
		return {'result': 'alert ok'};
	});

	var btn = document.createElement('button');
	btn.innerHTML = '点击我呀';
	btn.onclick=function(){
		// js调用java注册的messageboxt方法
		service.toast({'text': '你好, messagebox!'}, function(response){
			alert('调用toast回来啦\n' + JSON.stringify(response));
		});
	}
	document.body.appendChild(btn);
}();