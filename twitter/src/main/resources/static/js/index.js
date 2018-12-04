function tomypage() {
    location.href="/tomypage";
}

function showMoreTwitter(page){
    $.ajax({
        url:"/getMainTwitter",
        type:"post",
        data:"page="+page,
        dataType:"json",
        success:function(msg){
            if(msg==null||msg.length==0){
                alert("没有更多推文了");
            }
            var ulDom=$("#twitterList");
            console.log(msg.length);
            for(var i=0;i<msg.length;i++){
                console.log(msg[i]);
                var liDom=document.createElement("li");
                // 显示了头像部分
                $(liDom).attr("class","media");
                $(liDom).attr("style","margin-bottom:20px;padding:10px;border:1px solid;border-radius:10px;");
                var divDom=document.createElement("div");
                $(divDom).attr("style","float: left");
                var aDom=document.createElement("a");
                $(aDom).attr("title","点击查看他的推文");
                $(aDom).attr("href","/tootherspage?id="+msg[i].twitter.user.id);
                var imgDom=document.createElement("img");
                $(imgDom).attr("class","media-object img-circle");
                $(imgDom).attr("src",msg[i].twitter.user.image);
                $(imgDom).attr("alt","此处为头像");
                $(imgDom).attr("style","width: 100px;height: 100px");

                $(aDom).append(imgDom);
                $(divDom).append(aDom);
                $(liDom).append(divDom);
                //显示内容标题
                divDom=document.createElement("div");
                $(divDom).attr("class","media-body");
                $(divDom).attr("style","padding-left:10px");
                var timgList=new Array();
                for(var k=0;k<msg[i].timageList.length;k++){
                    timgList.push(msg[i].timageList[k].image);
                }
                console.log("长度为"+timgList.length);
                $(divDom).attr("onclick","showmodal('"+msg[i].twitter.id+"')");
                var h4Dom=document.createElement("h4");
                $(h4Dom).attr("style","margin-left:5px");
                $(h4Dom).attr("class","media-heading");
                $(h4Dom).html(msg[i].twitter.title);

                var spanDom=document.createElement("span");
                $(spanDom).html(msg[i].twitter.user.name+"&nbsp;&nbsp;");
                $(spanDom).attr("style","margin-left:5px");
                var spanDom2=document.createElement("span");
                $(spanDom2).html(msg[i].twitter.date);
                $(spanDom2).attr("class","text-right");
                var divDom2=document.createElement("div");
                $(divDom2).attr("style","width: 200px;margin-left:5px;word-break:break-all;");
                var content;
                if(msg[i].twitter.content.length>100){
                    content=msg[i].twitter.content.substring(0,100)+"...";
                }else{
                    content=msg[i].twitter.content;
                }
                var contentDom=document.createElement("span");
                $(contentDom).attr("cpContent",msg[i].twitter.content);
                $(contentDom).html(content);
                var divDom3=document.createElement("div");
                $(divDom3).attr("style","position:relative;width:100%;cursor:pointer;");
                //console.log("length: "+msg[i].timageList.length);
                if(parseInt(msg[i].timageList.length)>0){
                    for(var k=0;k<msg[i].timageList.length;k++){
                        var timageDom=document.createElement("img");
                        $(timageDom).attr("style","width:100%;border-radius:10px;margin-top:15px;cursor:pointer;");
                        if(k>0){
                            $(timageDom).attr("hidden","hidden");
                        }
                        $(timageDom).attr("src",""+msg[i].timageList[k].image);
                        /* $(timageDom).attr("onclick","showPictureModal(this)");*/
                        $(divDom3).append(timageDom);
                    }

                    if(parseInt(msg[i].timageList.length)>1){
                        var floatArea=document.createElement("button");
                        $(floatArea).attr("style","display:inline-block;background-color:rgba(0,0,0,0.6);position:absolute;border-width:0px;width:50px;height:50px;right:0;top:0;transform:translateY(15px);border-top-right-radius:10px;color:white");
                        $(floatArea).html("<span class='glyphicon glyphicon-file'></span>&nbsp;"+msg[i].timageList.length);
                        $(divDom3).append(floatArea);
                    }
                }
                $(divDom2).append(contentDom);

                $(divDom).append(h4Dom,spanDom,spanDom2,divDom2,divDom3);
                $(liDom).append(divDom);
                $(ulDom).append(liDom);
            }
        }
    });
}