function deleteattented(id,button) {
    console.log(id);
    $.ajax({
        url: "/deleteattented",
        type:"get",
        data:"id="+id,
        success:function (msg) {
            if(msg==true){
                console.log("成功");
                $(button).text("关注");
                $(button).removeAttr("onclick");
                $(button).attr("onclick","addattented(\'"+id+"\',this)");

            }else{
                console.log("失败");
            }
            return false;
        }
    });
    return false;
}

function addattented(id,button) {
    console.log(id);
    $.ajax({
        url: "/addattented",
        type:"get",
        data:"id="+id,
        success:function (msg) {
            var btn="<button>关注中</button>";
            if(msg==true){
                console.log("成功");
                $(button).text("关注中");
                $(button).removeAttr("onclick");
                $(button).attr("onclick","deleteattented(\'"+id+"\',this)");

            }else{
                console.log("失败");
            }
            return false;
        }
    });
    return false;
}