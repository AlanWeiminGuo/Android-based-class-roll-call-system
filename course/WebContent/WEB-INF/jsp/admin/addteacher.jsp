<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加老师</title>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>
    <form class="layui-form" action="">
    	<div class="layui-form-item" style="margin-top: 20px;margin-right: 1200px;">
            <label class="layui-form-label">工号</label>
            <div class="layui-input-block">
              <input type="text" placeholder="请输入工号" name="jobNumber" id="jobNumber"  class="layui-input">
            </div>
        </div> 
        
        <div class="layui-form-item" style="margin-top: 20px;margin-right: 1200px;">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
              <input type="text" placeholder="请输入姓名" name="name" id="name"  class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item" style="margin-top: 20px;margin-right: 1200px;">
				<label class="layui-form-label">性别</label>
		      	<div class="layui-input-inline" style="width: 400px; " >
		        	<select id="sex">
        				<option value="男">男</option>
				        <option value="女">女</option>
      				</select>
		      	</div>
			</div>
        
        <div class="layui-form-item" style="margin-top: 20px;margin-right: 1200px;">
				<label class="layui-form-label">职称</label>
		      	<div class="layui-input-inline" style="width: 400px; " >
		        	<select id="rank">
        				<option value="教授">教授</option>
				        <option value="副教授">副教授</option>
				        <option value="讲师">讲师</option>
				        <option value="助教">助教</option>
				        <option value="其他">其他</option>
      				</select>
		      	</div>
			</div>
			
		<div class="layui-form-item" style="margin-top: 20px;margin-right: 1200px;">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
              <input type="text" placeholder="请输入手机号" name="phone" id="phone"  class="layui-input">
            </div>
        </div>
          
       <button class="layui-btn layui-btn-normal" type="button" style="width:200px;margin-left: 60px;margin-top: 20px;" id="btn">添加</button>
   </form>
   
   <script charset="utf-8" type="text/javascript">
	$(document).ready(function(){
		var form = layui.form;
		form.render();
	$('#btn').click(function(){
		var jobNumber = $('#jobNumber').val();
		var name = $('#name').val();
		var sex = $('#sex').val();
		var rank = $('#rank').val();
		var phone = $('#phone').val();
		if (!jobNumber) {
			layer.alert('工号不能为空');
			return;
		}
		if (!name) {
			layer.alert('姓名不能为空');
			return;
		}
		if (!sex) {
			layer.alert('性别不能为空');
			return;
		}
		if (!rank) {
			layer.alert('职称不能为空');
			return;
		}
		if (!phone) {
			layer.alert('手机号不能为空');
			return;
		}
		var data = {
				jobNumber:jobNumber,
				name :name,
				sex:sex,
				rank :rank,
				phone :phone,
		};
		var url = '${base}/admin/addteacher';
		var index = layer.load(1);
		$.post(url, data, function(result) {
			if (result.code == 0) {
				window.location.href = '${base}/teacher_list'; 
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