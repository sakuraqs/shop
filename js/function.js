function change(img){
	img.src="getcode?"+new Date().getTime();
}
function FocusItem(obj){
	
	if($(obj).attr('name') == 'code'){
		$(obj).next().next().html('').removeClass('error');
	}else{
		$(obj).next('span').html('').removeClass('error');
	}
}
function CheckItem(obj){
	var msgBox = $(obj).next('span');
	switch($(obj).attr('name')){
	case "userName":
	    if(obj.value == ""){
	    	msgBox.html('用户名不能为空');
	    	msgBox.addClass('error');
	    }
	    break;
	case "passWord":
		if(obj.value == ""){
	    	msgBox.html('密码不能为空');
	    	msgBox.addClass('error');
		}
	    break;
	case "rePassWord":
		if(obj.value == ""){
	    	msgBox.html('确认密码不能为空');
	    	msgBox.addClass('error');
		}else if($(obj).val() !=$('input [name="passWord"]').val()){
			msgBox.html('密码不一致');
	    	msgBox.addClass('error');
		}
	    break;
	    
	case "code":
		var numshow  = $(obj).next().next();
		if(obj.value == ""){
	    	numshow.html('验证码不能为空');
	    	numshow.addClass('error');
	    }
		break;
	}
}