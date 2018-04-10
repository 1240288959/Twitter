function showMoreTwitter(page){
    $.ajax({
        url:"/getMyTwitter",
        type:"post",
        data:"page="+page,
        dataType:"json",
        success:function(msg){
            console.log(msg);
            if(msg==null||msg.length==0){
                alert("没有更多推文了");
            }
            var ulDom=$("#twitterList");
            for(var i=0;i<msg.length;i++){
                var liDom=document.createElement("li");
                // 显示了头像部分
                $(liDom).attr("class","media");
                var divDom=document.createElement("div");
                $(divDom).attr("style","float: left");
                var aDom=document.createElement("a");
                $(aDom).attr("title","点击查看他的推文");
                $(aDom).attr("href","/tootherspage?id="+msg[i].user.id);
                var imgDom=document.createElement("img");
                $(imgDom).attr("class","media-object img-circle");
                $(imgDom).attr("src",msg[i].user.image);
                $(imgDom).attr("alt","此处为头像");
                $(imgDom).attr("style","width: 100px;height: 100px");

                $(aDom).append(imgDom);
                $(divDom).append(aDom);
                $(liDom).append(divDom);
                //显示内容标题
                divDom=document.createElement("div");
                $(divDom).attr("class","media-body");
                $(divDom).attr("onclick","showmodal('"+msg[i].id+"','"+msg[i].title+"','"+msg[i].user.name+"','"+msg[i].date+"','"+msg[i].content+"')");
                var h4Dom=document.createElement("h4");
                $(h4Dom).attr("style","margin-left:5px");
                $(h4Dom).attr("class","media-heading");
                $(h4Dom).html(msg[i].title);

                var spanDom=document.createElement("span");
                $(spanDom).html(msg[i].user.name+"&nbsp;&nbsp;");
                $(spanDom).attr("style","margin-left:5px");
                var spanDom2=document.createElement("span");
                $(spanDom2).html(msg[i].date);
                $(spanDom2).attr("class","text-right");
                var divDom2=document.createElement("div");
                $(divDom2).attr("style","width: 200px;height:80px;margin-left:5px;word-break:break-all;");
                var content;
                if(msg[i].content.length>100){
                    content=msg[i].content.substring(0,100)+"...";
                }else{
                    content=msg[i].content;
                }
                $(divDom2).html(content);

                $(divDom).append(h4Dom,spanDom,spanDom2,divDom2);
                $(liDom).append(divDom);
                $(ulDom).append(liDom);


            }
        }
    });
}