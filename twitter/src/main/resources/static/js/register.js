function register() {
    $.ajax({
        url:"/register",
        type:"get",
        dataType:"json",
        async:true,
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
                location.href="/tologin";
            }
            else
                window.alert("注册失败");
            return false;
        }
    });
    return false;
}