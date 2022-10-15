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
	<legend>添加学生到课程</legend>
  	<div class="layui-field-box">
		<form class="layui-form" action="" style="margin-top: 20px;">
		    <div class="layui-inline">
				<label class="layui-form-label">学号</label>
		      	<div class="layui-input-inline" style="width: 193px;" >
		        	<input type="text" id="studentId" class="layui-input" placeholder="学号">
		      	</div> 
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">姓名</label>
		      	<div class="layui-input-inline" style="width: 193px;" >
		        	<input type="text" id="name" class="layui-input" placeholder="姓名">
		      	</div>
			</div>
		    <div class="layui-inline">
				<label class="layui-form-label">性别</label>
		      	<div class="layui-input-inline" style="width: 193px;" >
		        	<select id="sex">
        				<option value="">全部</option>
				        <option value="男">男</option>
				        <option value="女">女</option>
      				</select>
		      	</div>
			</div>
		    <div class="layui-inline">
				<label class="layui-form-label"><button type="button" class="layui-btn" id="search">查询</button></label>
			</div>
		</form>
  	</div>
</fieldset>
<table class="layui-hide" id="tb" lay-filter="tb"></table>
<div class="layui-inline">
	<label class="layui-form-label"><button type="button" class="layui-btn" id="add">添加</button></label>
</div>

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
			studentId: $('#studentId').val(),
			name: $('#name').val(),
			sex: $('#sex').val(),
		}
	});
}
$(document).ready(function(){
	var form = layui.form;
	form.render();
	var table = layui.table;
	table.render({
		id: 'tb',
		url: '${base}/admin/student_list',
		elem: '#tb',
		method: 'post',
		cellMinWidth: 150,
		where: {
			studentId: $('#studentId').val(),
			name: $('#name').val(),
			sex: $('#sex').val(),
		},
		cols:  [[ //标题栏
			{type:'checkbox'},
			{field:'studentId',title: '学号'},
			{field:'name',title: '姓名'},
			{field:'sex',title: '性别'},
			{field:'phone',title: '手机号'},
		]]
	});
	$('#search').click(function(){
		reload();
	});
	$('#add').click(function(){
		var data = layui.table.checkStatus('tb').data; 
		if (data.length>0) {
			var url = '${base}/teacher/addstudent';
			var data = {
				student: JSON.stringify(data),
				courseId: id,
			};
			var index = layer.load(1);
			$.post(url, data, function(result) {
				if (result.code == 0) {
					window.location.href = '${base}/course_list';
				} else {
					layer.alert(result.msg);
				}
			}, 'json').always(function(){
				//关闭弹层
				layer.close(index);
			});
		}else {
			layer.alert('请选择学生');
		}
	});
});
</script>
</body>
</html>