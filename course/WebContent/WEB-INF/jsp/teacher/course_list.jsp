<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body style="margin: 10px;">
<fieldset class="layui-elem-field">
	<legend>课程</legend> 
  	<div class="layui-field-box">
		<form class="layui-form" action="" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label">课程名称</label>
		      	<div class="layui-input-inline" style="width: 160px;" >
		        	<input type="text" id="courseName" class="layui-input" placeholder="课程名称">
		      	</div>
			</div>
			
		    <div class="layui-inline">
				<label class="layui-form-label"><button type="button"  class="layui-btn" id="search">查询</button></label>
			</div>
		</form>
  	</div>
</fieldset>
<table id="tb" lay-filter="tb"></table>
<script type="text/html" id="bar">
	<div class="layui-btn-group">
		<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</a>
		<a class="layui-btn layui-btn-sm" lay-event="add" >导入学生</a>
		<a class="layui-btn layui-btn-sm" lay-event="chakan" >查看学生</a>
		<a class="layui-btn layui-btn-sm" lay-event="chakanfk" >查看反馈</a>
	</div>
</script>

<script charset="utf-8" type="text/javascript">
var id=window.parent.id;
function reload(){
	var table = layui.table;
	table.reload('tb',{
		where: {
			teacherId: id,
			courseName: $('#courseName').val(),
		}
	});
}
$(document).ready(function(){
	var form = layui.form;
	form.render();
	var table = layui.table;
	table.render({
		id: 'tb',
		url: '${base}/teacher/course_list',
		elem: '#tb',
		limits: [10,20,50,100],
		limit: 10,
		page: true,
		method: 'post',
		cellMinWidth: 150,
		where: {
			teacherId: id,
			courseName: $('#courseName').val(),
		},
		cols:  [[ //标题栏
			{field:'courseName',title: '课程名称'},
			{field:'kcdescribe',title: '课程描述'},
			{field:'startTime',title: '开始时间'},
			{field:'endTime',title: '结束时间'},
			{title:'操作',fixed: 'right',toolbar: '#bar',width:350}
		]]
	});
	//监听工具条
	table.on('tool(tb)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
	  	var layEvent = obj.event; //获得 lay-event 对应的值
	  	var tr = obj.tr; //获得当前行 tr 的DOM对象
	  	if(layEvent == 'del'){
	  		layer.confirm('确定删除该课程？', function(index){
	    		var url = '${base}/teacher/delacourse';
	    		var reqData = {
	    			id: data.id,
	    		};
	    		var index = layer.load(1);
	    		$.post(url, reqData, function(result) {
	    			if (result.code == 0) {
	    				layer.msg('操作成功');
	    				reload();
	    			} else {
	    				layer.alert(result.msg);
	    			}
	    		}, 'json').always(function(){
	    			//关闭弹层
	    			layer.close(index);
	    		});
	    	});
	  	}else if (layEvent == 'chakan') {
	  		window.location.href = '${base}/kcstudent_list?id='+data.id; 
		}else if (layEvent == 'chakancj') {
			window.location.href = '${base}/kcstudent_cjlist?id='+data.id; 	
		}else if (layEvent == 'chakanfk') {
			window.location.href = '${base}/fk_list?id='+data.id; 	
		}else {
	  		window.location.href = '${base}/add_student?id='+data.id; 
		}
	});
	$('#search').click(function(){
		reload();
	});
});
</script>
</body>
</html>