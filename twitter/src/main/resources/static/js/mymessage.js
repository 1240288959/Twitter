function showMoreMessage(page){
    $.ajax({
        url:"/getMyMessage",
        type:"post",
        data:"page="+page,
        dataType:"json",
        success:function(msg){
            console.log(msg);
            if(msg==null||msg.length==0){
                alert("没有更多消息了");
            }
            var ulDom=$("#messageList");
            for(var i=0;i<msg.length;i++){
                var liDom=document.createElement("li");
                var liBodyDom=document.createElement("div");
                $(liBodyDom).attr("class","media-body");
                var bDom=document.createElement("b");
                $(bDom).html(msg[i].content);
                var divDom=document.createElement("div");
                $(divDom).attr("style","word-break:break-all;");
                var pDom=document.createElement("p");
                $(pDom).attr("class","text-right");
                $(pDom).attr("style","text-decoration:underline;");
                $(pDom).attr("onmouseover","javascript:this.style.color='blue';");
                $(pDom).attr("onmouseout","javascript:this.style.color='black';");
                $(pDom).attr("onclick","showmodal('"+msg[i].twitter.id+"','"+msg[i].twitter.title+"','"+msg[i].twitter.user.name+"','"+msg[i].twitter.date+"','"+msg[i].twitter.content+"')");
                $(pDom).html("点击此处查看详情");

                $(divDom).append(pDom);
                $(liBodyDom).append(bDom,divDom);
                $(liDom).append(liBodyDom);
                $(ulDom).append(liDom);
            }
        }
    });
}