<%@page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>登录</title>
	<!-- Bootstrap 3.3.6 -->
	<link rel="stylesheet" href="${ctx}/dist/bootstrap/css/bootstrap.min.css">
	<!-- Font Awesome -->
 	<link rel="stylesheet" href="${ctx}/dist/font-awesome-4.7.0/css/font-awesome.min.css"> 
	<!-- Theme style -->
	<link rel="stylesheet" href="${ctx}/dist/adminlte/css/AdminLTE.min.css">
	<!-- toastr -->
<!-- 	<link rel="stylesheet" href="${ctx}/dist/toastr/toastr.min.css" /> -->
	
	<!-- 皮肤 -->
	<link rel="stylesheet" href="${ctx}/dist/adminlte/css/skins/skin-black.css">
	<link rel="stylesheet" href="${ctx}/dist/adminlte/css/skins/allskins.css">
	
	<link rel="stylesheet" href="${ctx}/dist/animate/animate.css" />
	
	<!-- select2 -->
<!-- 	<link rel="stylesheet" href="${ctx}/dist/select2/css/select2.css" /> -->
	
	<!--layer -->
	<link rel="stylesheet" href="${ctx}/dist/layer/layer.css" />
	
	<!--fullcalendar -->
	<!-- <link rel="stylesheet" href="${ctx}/dist/fullcalendar/css/fullcalendar.css" /> -->
	
	<!--datepicker-->
	<link rel="stylesheet" href="${ctx}/dist/bootstrap-datepicker-1.6.4-dist/css/bootstrap-datepicker.css" />
	
	<!--slider-->
	<link rel="stylesheet" href="${ctx}/dist/bootstrap-slider/slider.css" />
	
	<!-- jstree -->
	<link rel="stylesheet" href="${ctx}/dist/jstree/dist/themes/default/style.min.css" />
	
	<!-- 自定义css -->
	<link rel="stylesheet" href="${ctx}/css/index.css">
  <style>
  	 .login-box-body{
 		 animation-duration: 0.6s;
 		 animation-iteration-count: 0.5;
		}
		.login-box{
			margin-top:-3px;
		}
  </style>
</head>
<body class="hold-transition" style="background:url('${ctx}/img/login.jpg');background-size:100%;overflow:hidden">
<div class="login-box animated">
   	 <img src="${ctx}/img/logo.png" style="display:block;margin-bottom:20px;margin-left:-130px"/>
  <!-- /.login-logo -->
  <div class="login-box-body animated #(animate)"  style="background-color: rgba(0, 0, 0, 0.2);color:#fff">

    <form action="${ctx}/login" method="post">
    	
   	   <div class="form-group pull-right">
   	   		<div style="margin-bottom:5px" class="" id="role">选择角色</div>
   	   		<div class="clearfix"></div>
  	   		<div class="btn-group btn-group-sm">
                 <div class="btn btn-default">admin</div>
                 <div class="btn btn-default">teacher</div>
                 <div class="btn btn-default">student</div>
            </div>
      </div>
      <div class="clearfix"></div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="用户名" name="username" autofocus="autofocus">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="密码" name="password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
       <div class="form-group">
        <input type="button" class="form-control" value="登录" style="background-color:rgba(80,80,80,0.8);color:#fff">
        <span style="color:red;font-weight:bold;margin-top:2px" class="pull-right">${err }</span>
      </div>
<!--       <div class="form-group col-md-offset-8 col-md-4">
      	 <a href="javascript:;" style="font-weight:bold;margin-left:18px" id="getpwd"></a>
      </div>
      <div class="clearfix"></div> -->
    </form>
  </div>
  <!-- /.login-box-body -->
</div>
	<!-- jQuery 2.2.3 -->
	<script src="${ctx}/dist/jquery/jquery-2.2.3.min.js"></script>
	<!-- toastr -->
<!-- 	<script src="${ctx}/dist/toastr/toastr.min.js"></script> -->
	<!-- Bootstrap 3.3.6 -->
	<script src="${ctx}/dist/bootstrap/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${ctx}/dist/adminlte/js/app.min.js"></script>
	<script src="${ctx}/dist/adminlte/js/demo.js"></script>
	<!-- bootstrap-pagination -->
	<script src="${ctx}/dist/jquery-bootstrap-pagination/jquery-bootstrap-pagination.js"></script>
	<!-- select2 -->
<!-- 	<script src="${ctx}/dist/select2/js/select2.js"></script>
	<script src="${ctx}/dist/select2/js/i18n/zh-CN.js"></script> -->
	
	<!--layer -->
	<script src="${ctx}/dist/layer/layer.js"></script>
	
	<!--fullcalendar -->
<!-- 	<script src="${ctx}/dist/fullcalendar/js/jquery-ui-1.10.2.custom.min.js"></script>
	<script src="${ctx}/dist/fullcalendar/js/fullcalendar.min.js"></script>
	<script src="${ctx}/dist/fullcalendar/js/moment.min.js"></script> -->
	
	<!--datepicker -->
	<script src="${ctx}/dist/bootstrap-datepicker-1.6.4-dist/js/bootstrap-datepicker.js"></script>
	<script src="${ctx}/dist/bootstrap-datepicker-1.6.4-dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	
	<!--echars -->
<!-- 	<script src="${ctx}/dist/echars/echarts.common.min.js"></script> -->
	
	<!--slider -->
	<script src="${ctx}/dist/bootstrap-slider/bootstrap-slider.js"></script>
	
	<!-- neditor -->
	<script  src="${ctx}/dist/neditor/utf8-jsp/neditor.config.js"></script>
	<script  src="${ctx}/dist/neditor/utf8-jsp/neditor.all.min.js"> </script>
	<script  src="${ctx}/dist/neditor/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
	
	<!--select2  -->
<!-- 	<script  src="${ctx}/dist/select2/js/select2.js"></script> -->
	
	<!-- jstree -->
	<script  src="${ctx}/dist/jstree/dist/jstree.min.js"></script>
<script>
	s='';
	groupname = localStorage.getItem('groupname');
	$('.btn-group-sm div').each(function(){
		if($(this).text()==groupname){
			$(this).addClass('active').siblings().removeClass('active');
			s=groupname;
		}
	});
	$('.btn-group-sm div').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		s = $(this).text();
		localStorage.setItem('groupname',$(this).text());
	});
	$('[type=button]').click(function(){
		flag = false;
		$('.btn-group-sm div').each(function(){
			if($(this).hasClass('active')){
				flag = true;
			}
		});
		if(!flag){
			$('#role').css({'color':'red'});
		}
		
		if(s!=''){
			$('form').append('<input type="hidden" name="groupname" value="'+s+'">');
			$('form').submit();
		}
	});
	$(window).keydown(function(e){
		if(e.keyCode==13){
			$('form').append('<input type="hidden" name="groupname" value="'+s+'">');
			$('form').submit();
		}
	});
</script>
</body>
</html>
