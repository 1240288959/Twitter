function deliverytwitter () {
    var flag=false;
    $.ajax({
        url:"/deliverytwitter",
        type:"post",
        async:false,
        cache:false,
        data:$("#myform").serialize(),
        success:function(msg){
            flag=true;
        }
    });
    return flag;
}