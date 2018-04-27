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

function showMoreAttented(page){
    $.ajax({
        url:"/getAttented",
        type:"post",
        data:"page="+page,
        dataType:"json",
        success:function(msg){
            console.log(msg);
            if(msg==null||msg.length==0){
                alert("没有更多用户了");
            }
            var mainDom=$("#attentedList");
            for(var i=0;i<msg.length;i++){
                var divDom=document.createElement("div");
                $(divDom).css("margin-bottom","10px");
                var aDom=document.createElement("a");
                var imgDom=document.createElement("img");
                $(aDom).attr("href","/tootherspage?id="+msg[i].id);
                $(aDom).attr("style","margin-right:10px");
                $(imgDom).attr("class","img-circle");
                $(imgDom).attr("src",msg[i].image);
                $(imgDom).attr("alt","...此处为被关注者的头像");
                $(imgDom).attr("style","width: 100px;height: 100px");
                $(aDom).append(imgDom);
                var buttonDom=document.createElement("button");
                $(buttonDom).attr("class","btn btn-primary");
                $(buttonDom).attr("onclick","deleteattented('"+msg[i].id+"',this)");
                $(buttonDom).attr("style","margin-right:10px");
                $(buttonDom).html("关注中");
                var spanDom=document.createElement("span");
                $(spanDom).html(msg[i].name);
                $(divDom).append(aDom,buttonDom,spanDom);
                $(mainDom).append(divDom);
            }
        }
    });
}