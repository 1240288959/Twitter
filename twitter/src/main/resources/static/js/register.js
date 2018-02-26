function register() {
    var flag;
    $.ajax({
        url:"/register",
        type:"post",
        dataType:"json",
        async:false,
        cache:false,
        data:{
            name:$("#name").val(),
            password:$("#password").val(),
            realname:$("#realname").val(),
            gender:$("[name='gender']").val(),
            email:$("#email").val(),
            mobile:$("#mobile").val(),
            birthday:$("#birthday").val()
        },
        success:function (msg) {
            if(msg==true){
                window.alert("注册成功");
                flag=true;
            }
            else{
                window.alert("注册失败");
                flag=false;
            }
        }
    });
    return flag;
}

