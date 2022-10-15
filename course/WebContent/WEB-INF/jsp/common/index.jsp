<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>首页</title>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin"> 
	<div class="layui-header">
		<div class="layui-logo">课程APP后台管理</div>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
        		<a href="javascript:;">管理员</a>
      		</li>
      		<li class="layui-nav-item"><a href="${base}/logout">退出</a></li>
    	</ul>
  	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
	      	<ul class="layui-nav layui-nav-tree"  lay-filter="test">
	      		<li class="layui-nav-item" data-biao-shi="item" data-item="${base}/student_list"><a href="javascript:;">学生管理</a></li>
	      		<li class="layui-nav-item" data-biao-shi="item" data-item="${base}/teacher_list"><a href="javascript:;">老师管理</a></li>
	      		<li class="layui-nav-item" data-biao-shi="item" data-item="${base}/tjcourse_list"><a href="javascript:;">考勤统计</a></li>
	      	</ul>
    	</div>
	</div>
</div>

<!-- 内容主体区域 -->
<iframe id="iframe" scrolling="auto" frameborder="0" src="" style="margin-left: 200px;"></iframe>
<script>
function resize(){
	var width = document.documentElement.clientWidth - 200;
	var height = document.documentElement.clientHeight - 60;
	$('#iframe').attr('height',height);
	$('#iframe').attr('width',width);
}
$(document).ready(function(){
    layui.element.init();
    resize();
	$(window).resize(function() {
		resize();
	});
	$('[data-biao-shi=item]').click(function(){
		var url = $(this).attr('data-item');
		var iframe = $('#iframe')[0];
		iframe.contentWindow.location.replace(url);
	});
});
</script>
</body>
</html>