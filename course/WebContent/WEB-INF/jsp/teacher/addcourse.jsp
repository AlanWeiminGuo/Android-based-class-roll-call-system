<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加课程</title>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>
    <form class="layui-form" action="">
    	<div class="layui-form-item" style="margin-top: 20px;margin-right: 1200px;">
            <label class="layui-form-label">课程名称</label>
            <div class="layui-input-block">
              <input type="text" placeholder="请输入课程名称" name="courseName" id="courseName"  class="layui-input">
            </div> 
        </div>
    	<div class="layui-form-item" style="margin-top: 20px;margin-right: 1200px;">
            <label class="layui-form-label">课程描述</label>
            <div class="layui-input-block">
              <input type="text" placeholder="请输入课程描述" name="kcdescribe" id="kcdescribe"  class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
			  <label class="layui-form-label">开始时间</label>
			  <div class="layui-input-inline">
				 <input type="text" class="layui-input datetime" id="startTime" placeholder="开始时间" >				
			  </div>
		</div>
          <div class="layui-form-item">
			  <label class="layui-form-label">结束时间</label>
			  <div class="layui-input-inline">
				 <input type="text" class="layui-input datetime" id="endTime" placeholder="结束时间" >				
			  </div>
		</div>
          
       <button class="layui-btn layui-btn-normal" type="button" style="width:200px;margin-left: 60px;margin-top: 20px;" id="btn">添加</button>
   </form>
   
   <script charset="utf-8" type="text/javascript">
   var id=window.parent.id;
   layui.use( 'laydate', function () {
	   var laydate = layui.laydate;
	   lay('.datetime').each(function() {
           laydate.render({
               elem : this,
               type: 'datetime',
               trigger : 'click'
           });
       });
   });
	$(document).ready(function(){
		var form = layui.form;
		form.render();
	$('#btn').click(function(){
		var courseName = $('#courseName').val();
		var kcdescribe = $('#kcdescribe').val();
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		if (!courseName) {
			layer.alert('课程名称不能为空');
			return;
		}
		if (!kcdescribe) {
			layer.alert('课程描述不能为空');
			return;
		}
		if (!startTime) {
			layer.alert('课程开始时间不能为空');
			return;
		}
		if (!endTime) {
			layer.alert('课程结束时间不能为空');
			return;
		}
		var data = {
				courseName:courseName,
				kcdescribe :kcdescribe,
				startTime :startTime,
				endTime :endTime,
				teacherId :id,
		};
		var url = '${base}/teacher/addcourse';
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
	})
});
</script>
</body>
</html>