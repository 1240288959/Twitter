function deleteattented(id,button) {
    console.log(id);
    $.ajax({
        url: "/deleteattented",
        type:"get",
        data:"id="+id,
        success:function (msg) {
            if(msg==true){
                console.log("成功");
                $(button).parent().hide();
            }else{
                console.log("失败");
            }
            return false;
        }
    });
    return false;
}