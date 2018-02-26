function deliverytwitter () {
    $.ajax({
        url:"/deliverytwitter",
        type:"get",
        data:$("#myform").serialize(),
        success:function(msg){
            location.href="/tomain";
            return false;
        }
    });
    return false;
}