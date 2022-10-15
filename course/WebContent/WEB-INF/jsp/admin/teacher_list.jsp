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
	<legend>老师管理</legend>
  	<div class="layui-field-box">
		<form class="layui-form" action="" style="margin-top: 20px;">
		    <div class="layui-inline">
				<label class="layui-form-label">工号</label>
		      	<div class="layui-input-inline" style="width: 160px;" >
		        	<input type="text" id="jobNumber" class="layui-input" placeholder="工号">
		      	</div>
			</div> 
			<div class="layui-inline">
				<label class="layui-form-label">姓名</label>
		      	<div class="layui-input-inline" style="width: 160px;" >
		        	<input type="text" id="name" class="layui-input" placeholder="姓名">
		      	</div>
			</div>
		    <div class="layui-inline">
				<label class="layui-form-label">性别</label>
		      	<div class="layui-input-inline" style="width: 160px;" >
		        	<select id="sex">
        				<option value="">全部</option>
				        <option value="男">男</option>
				        <option value="女">女</option>
      				</select>
		      	</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">职称</label>
		      	<div class="layui-input-inline" style="width: 160px;" >
		        	<select id="rank">
        				<option value="">全部</option>
				        <option value="教授">教授</option>
				        <option value="副教授">副教授</option>
				        <option value="讲师">讲师</option>
				        <option value="助教">助教</option>
				        <option value="其他">其他</option>
      				</select>
		      	</div>
			</div>
			
		    <div class="layui-inline">
				<label class="layui-form-label"><button type="button" class="layui-btn" id="search">查询</button></label>
				<label class="layui-form-label"><button type="button" class="layui-btn" id="tianjia">添加</button></label>
			</div>
		</form>
  	</div>
</fieldset>
<table id="tb" lay-filter="tb"></table>
<script type="text/html" id="bar">
	<div class="layui-btn-group">
		<a class="layui-btn layui-btn-sm" lay-event="del">删除</a>
	</div>
</script>

<script charset="utf-8" type="text/javascript">
function reload(){
	var table = layui.table;
	table.reload('tb',{
		where: {
			studentId: $('#jobNumber').val(),
			name: $('#name').val(),
			sex: $('#sex').val(),
			rank: $('#rank').val(),
		}
	});
}
$(document).ready(function(){
	var form = layui.form;
	form.render();
	var table = layui.table;
	table.render({
		id: 'tb',
		url: '${base}/admin/teacher_list',
		elem: '#tb',
		limits: [10,20,50,100],
		limit: 10,
		page: true,
		method: 'post',
		cellMinWidth: 150,
		where: {
			studentId: $('#jobNumber').val(),
			name: $('#name').val(),
			sex: $('#sex').val(),
			rank: $('#rank').val(),
		},
		cols:  [[ //标题栏
			{field:'jobNumber',title: '工号'},
			{field:'name',title: '姓名'},
			{field:'sex',title: '性别'},
			{field:'rank',title: '职称'},
			{field:'phone',title: '手机号'},
			{title:'操作',fixed: 'right',toolbar: '#bar',width:200}
		]]
	});
	//监听工具条
	table.on('tool(tb)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
	  	var layEvent = obj.event; //获得 lay-event 对应的值
	  	var tr = obj.tr; //获得当前行 tr 的DOM对象
	  	if(layEvent == 'del'){
	  		layer.confirm('确定删除该老师？', function(index){
	    		var url = '${base}/admin/delateacher';
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
	  	}
	});
	$('#search').click(function(){
		reload();
	});
	$('#tianjia').click(function(){
		window.location.href = '${base}/addteacher'; 
	});
});
</script>
</body>
</html>