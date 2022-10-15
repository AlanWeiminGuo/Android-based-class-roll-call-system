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

<script type="text/html" id="bar">
	<div class="layui-btn-group">
		<a class="layui-btn layui-btn-sm" lay-event="tianjia">添加成绩</a>
	</div>
</script>

<script charset="utf-8" type="text/javascript">
var id;
var studentId;
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

function tianjia(){
	var studentcj = $('#studentcj').val();
	if (!studentcj) {
		layer.alert('成绩不能为空');
		return;
	}
	var data = {
		courseId:id,
		studentId:studentId,
		studentcj:studentcj,
		studentcj:studentcj,
	};
		var url = '${base}/teacher/addcj';
		var index = layer.load(1);
		$.post(url, data, function(result) {
			if (result.code == 0) {
				layer.alert('添加成功');
			} else {
				layer.alert(result.msg);
			}
		}, 'json').always(function(){
			//关闭弹层
			layer.close(index);
		});
		layer.closeAll();
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
	//监听工具条
	table.on('tool(tb)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
	  	var layEvent = obj.event; //获得 lay-event 对应的值
	  	var tr = obj.tr; //获得当前行 tr 的DOM对象
	  	if(layEvent == 'tianjia'){
	  		studentId = data.id;
	  		layer.open({
	  	        type: 1,
	  	        closeBtn: false,
	  	        shift: 7,
	  	        shadeClose: true,
	  	        content: "<div style='width:350px;'><div style='width:300px;margin-left: 3%;' class='form-group has-feedback'><p>请输入该学生的成绩</p><input id='studentcj' class='form-control' type='number' name='studentcj'/></div>" +
	  	        "<button style='margin-left:140px;margin-bottom: 10px;' type='button' class='layui-btn' onclick='tianjia()'>提交</button></div>"
	  	    });
	  		/* layer.confirm('确定删除该学生？', function(index){
	    		var url = '${base}/admin/delastudent';
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
	    	}); */
	  	}
	});
});


</script>
</body>
</html>