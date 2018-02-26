function login() {
    var flag=false;
    $.ajax({
        url:"/login",
        type:"post",
        async:false,
        cache:false,
        dataType:"json",
        data:$("#myform").serialize(),
        success:function (msg) {
            console.log(msg);
            if(msg==true) {
                flag=true;
                $("#alert_success").show();
                var count=1;
                setInterval(function () {
                    if(count==0){
                        location.href="/tomain";
                    }
                    count--;
                },500)
            }
            else {
                /*alert("登陆失败");*/
                $("#alert_fail").show();
            }
        }
    });
    return flag;
}

function close_alert_fail_click() {
    $("#alert_fail").hide();
}

function close_alert_success() {
    $("#alert_success").hide();
}