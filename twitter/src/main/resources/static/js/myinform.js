function setform() {
    $.ajax({
        url: "/getcurrentuser",
        type: "post",
        success: function (user) {
            console.log(user);
            var name = user.name;
            var password = user.password;
            var realname = user.realname;
            var gender = user.gender;
            var email = user.email;
            var mobile = user.mobile;
            var birthday = user.birthday;

            $("#name").val(name);
            $("#password").val(password);
            $("#realname").val(realname);
            $("#gender").val(gender);
            if (gender == 1) {
                $("#male").attr("checked", "checked");
            } else {
                $("#female").attr("checked", "checked");
            }
            $("#email").val(email);
            $("#mobile").val(mobile);
            $("#birthday").val(birthday);

            $("#name").attr("disabled",true);
            $("#realname").attr("disabled",true);
            $("#email").attr("disabled",true);
            $("#mobile").attr("disabled",true);
            $("#birthday").attr("disabled",true);
            $("#male").attr("disabled",true);
            $("#female").attr("disabled",true);
            $("#edit").show();
            $("#unedit").hide();
            $("#submit").hide();
        }
    })
}

function edit_click() {
    $("#unedit").show();
    $("#submit").show();
    $("#edit").hide();

    $("#name").attr("disabled",false);
    $("#realname").attr("disabled",false);
    $("#email").attr("disabled",false);
    $("#mobile").attr("disabled",false);
    $("#birthday").attr("disabled",false);
    $("#male").attr("disabled",false);
    $("#female").attr("disabled",false);
}

function update_myinform() {
    $.ajax({
        url:"updateuserinform",
        type:"post",
        data:$("#myform").serialize(),
        success:function (isSuccess) {
            if(isSuccess==false){
                alert("更改信息失败");
            }else
                alert("更改成功");
        }
    })
}

