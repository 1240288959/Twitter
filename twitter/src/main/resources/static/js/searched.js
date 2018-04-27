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

function showMoreUser(name,page){
    $.ajax({
        url:"/getSearched",
        type:"post",
        data:{
            "name":name,
            "page":page
        },
        dataType:"json",
        success:function(msg){
            console.log(msg);
            if(msg==null||msg.length==0){
                alert("没有更多用户了");
            }
            var mainDom=$("#searchedList");
            for(var i=0;i<msg.length;i++){
                var divDom=document.createElement("div");
                $(divDom).css("margin-bottom","10px");
                var aDom=document.createElement("a");
                var imgDom=document.createElement("img");
                $(aDom).attr("href","/tootherspage?id="+msg[i].user.id);
                $(aDom).attr("style","margin-right:10px");
                $(imgDom).attr("class","img-circle");
                $(imgDom).attr("src",msg[i].user.image);
                $(imgDom).attr("alt","...此处为被查找到的用户的头像");
                $(imgDom).attr("style","width: 100px;height: 100px");
                $(aDom).append(imgDom);
                var buttonDom=document.createElement("button");
                $(buttonDom).attr("class","btn btn-primary");
                if(msg[i].attented==false){
                    $(buttonDom).attr("onclick","addattented('"+msg[i].user.id+"',this)");
                    $(buttonDom).html("关注");
                }else{
                    $(buttonDom).attr("onclick","deleteattented('"+msg[i].user.id+"',this)");
                    $(buttonDom).html("关注中");
                }

                $(buttonDom).attr("style","margin-right:10px");

                var spanDom=document.createElement("span");
                $(spanDom).html(msg[i].user.name);
                $(divDom).append(aDom,buttonDom,spanDom);
                $(mainDom).append(divDom);
            }
        }
    });
}