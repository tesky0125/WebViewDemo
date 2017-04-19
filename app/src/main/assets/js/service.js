!function(){
    var service = {
       // register js method
       onalert: function(callback){
            bridge.addJavascriptMethod('alert', callback);
       },
       // call native method
       toast: function(params, callback){
            bridge.require('toast', params, function(response){
       			callback(response);
       		});
       },
       pushView: function(params, callback){
            bridge.require('push_view', params, function(response){
                callback(response);
            });
       }
    };
    window.service = service;
}();