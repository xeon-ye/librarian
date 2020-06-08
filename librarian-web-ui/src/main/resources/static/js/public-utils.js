//添加平板样式文件
function loadStyles(href) {
    var link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = href;  //引入的文件名
    document.getElementsByTagName('head')[0].appendChild(link);
}

// 动态加载js文件
function loadJavaScript(src) {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = src;  //引入的文件名
    document.getElementsByTagName('head')[0].appendChild(script);
}

//移除平板样式文件
function removeStyles(href) {
    // var filename = 'css/mpad.css';  //移除引入的文件名
    var filename = href;  //移除引入的文件名
    var targetelement = "link";
    var targetattr = "href";
    var allsuspects = document.getElementsByTagName(targetelement);
    for (var i = allsuspects.length; i >= 0; i--) {
        if (allsuspects[i] && allsuspects[i].getAttribute(targetattr) != null && allsuspects[i].getAttribute(targetattr).indexOf(filename) != -1) {
            allsuspects[i].parentNode.removeChild(allsuspects[i])
        }
    }
}


//日期转化为固定格式函数（参数为时间戳，或者直接new Date()）
function formatTime2DateTime(time) {
    var date = new Date(time);
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + (date.getDate()) : date.getDate();
    var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

//日期转化为固定格式函数（参数为时间戳，或者直接new Date()）   只显示年月日数据
function formatTime2Date(time) {
    var date = new Date(time);
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + (date.getDate()) : date.getDate();
    var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

    return year + "-" + month + "-" + day //+ " " + hours + ":" + minutes + ":" + seconds;
}
