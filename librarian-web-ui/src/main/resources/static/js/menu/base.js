
(function () {
    document.write('<meta name="viewport" content="width=device-width,initial-scale=1.0">');
    document.write('<meta http-equiv="X-UA-Compatible" content="ie=edge">');
    document.write('<link rel="stylesheet" type="text/css" href="libs/layui/css/layui.css">');
    document.write('<link rel="stylesheet" type="text/css" href="libs/common.css">');
    document.write('<link rel="stylesheet" type="text/css" href="libs/menu.css">');
    document.write('<script type="text/javascript" src="libs/jquery.min.js"></script>');
    document.write('<script type="text/javascript" src="libs/jquery-ui.min.js"></script>');
    document.write('<script type="text/javascript" src="libs/anime.js"></script>');
	document.write('<script type="text/javascript" src="libs/menu.js"></script>');
    document.write('<script type="text/javascript" src="libs/echarts.min.js"></script>');
    document.write('<script type="text/javascript" src="libs/map/world.js"></script>');
    document.write('<script type="text/javascript" src="libs/map/china.js"></script>');
    document.write('<script type="text/javascript" src="libs/map/henan.js"></script>');
    document.write('<script type="text/javascript" src="libs/layui/layui.all.js"></script>');
    document.write('<script type="text/javascript" src="libs/common.js"></script>');
    document.write('<script type="text/javascript" src="src/_public.js"></script>');
    // var name = (location.href.split('?')[0].split('/').pop() || 'index').split('.')[0];
    var name = document.querySelector('script').getAttribute('data-src');
    document.write('<link rel="stylesheet" type="text/css" href="src/' + name + '.css">');
    document.write('<script type="text/javascript" src="src/' + name + '.js"></script>');
})();
