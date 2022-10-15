<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<meta name="renderer" content="webkit">
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body style="margin: 10px;">
<fieldset class="layui-elem-field">
	<legend>课程学生列表</legend>
	<div class="layui-field-box" style="padding-top: 50px">
		<div class="layui-inline">
			
		</div>
	</div> 
</fieldset>
<table class="layui-hide" id="tb" lay-filter="tb"></table>

<script charset="utf-8" type="text/javascript">
var id;
var url = location.search; //获取url中"?"符后的字串 ('?modFlag=business&role=1')
var theRequest = new Object();
if ( url.indexOf( "?" ) != -1 ) {
  var str = url.substr( 1 ); //substr()方法返回从参数值开始到结束的字符串；
  var strs = str.split( "&" );
  for ( var i = 0; i < strs.length; i++ ) {
    theRequest[ strs[ i ].split( "=" )[ 0 ] ] = ( strs[ i ].split( "=" )[ 1 ] );
  }
  id = theRequest.id;
};
function reload(){
	var table = layui.table;
	table.reload('test',{
		where: {
			courseId: id,
		}
	});
}

layui.use('excel',function(){
	var layer = layui.layer;
	var excel = layui.excel;
});
$(document).ready(function(){
	var form = layui.form;
	form.render();
	var table = layui.table;
	var ins1 = table.render({
		id: 'tb',
		url: '${base}/teacher/course_stlist',
		elem: '#tb',
		method: 'post',
		limits: [10,20,50,100],
		limit: 10,
		page: true,
		toolbar: false ,
		toolbar: '<div></div>',
		cellMinWidth: 150,
		where: {
			courseId: id,
		},
		cols:  [[ //标题栏
			{field:'studentId',title: '学号'},
			{field:'name',title: '姓名'},
			{field:'sex',title: '性别'},
			{field:'phone',title: '手机号'},
			{field:'state',title: '签到状态'},
		]]
	});
});
</script>
</body>
</html>