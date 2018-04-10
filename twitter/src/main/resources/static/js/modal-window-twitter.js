function showmodal(id,title,username,date,content) {
    console.log(id);

    $("#myModal h4").html(title);
    $("#modal-id").html(id);
    $("#modal-username").html(username);
    $("#modal-date").html(date);
    $("#modal-content p").html(content);
    $("#praise").hide();
    $("#unpraise").hide();

    $.ajax({
        url:"/isPraise",
        type:"get",
        data:"twitterId="+ $("#modal-id").html(),
        success:function (msg) {
            /*console.log("*******"+msg);*/
            if(msg==true){
                $("#unpraise").show();
            }else{
                $("#praise").show();
            }
        }
    });
    updatacomment(id);
    $("#myModal").modal("show");
}

function updatacomment(id){
    $("#mydiv").empty();
    $.ajax({
        url:"/getComment",
        type:"get",
        data:"twitterid="+id,
        dataType:"json",
        success:function (msg) {
            console.log(msg);
            var h4Dom=document.createElement("h4");
            $(h4Dom).attr("class","text-left");
            $(h4Dom).html("评论区");
            if(msg.length!=0) {
                $("#mydiv").append(h4Dom);
            }
            for(var i in msg){
                /*console.log(msg[i]);*/
                var div=document.createElement("div");
                /* $(div).attr("class","div");*/
                var div1=document.createElement("div");
                /*$(div1).attr("class","col-sm-8");*/
                $(div1).attr("style","word-break:break-all; word-wrap:break-word ;");

                var div2=document.createElement("div");
                /*$(div2).attr("class","col-sm-4");*/

                var dcontent=document.createElement("p");
                $(dcontent).html(msg[i].content);
                $(dcontent).attr("class","text-left");

                var date=document.createElement("span");
                $(date).html(msg[i].date+"&nbsp;");

                var duser=document.createElement("span");
                $(duser).html(msg[i].user.name+"&nbsp;");

                var dfloor=document.createElement("span");
                $(dfloor).html(msg[i].floor+"楼&nbsp;");

                var commenticon=document.createElement("span");
                $(commenticon).attr("class","glyphicon glyphicon-comment");
                $(commenticon).attr("onclick","commentCommentToggle(this)");
                $(commenticon).attr("title","点击弹出或关闭输入栏");

                var cform=document.createElement("form");
                $(cform).attr("class","col-sm-12");
                $(cform).attr("hidden","hidden");
                var cinput=document.createElement("input");
                $(cinput).attr("name","parent");
                $(cinput).attr("value",msg[i].id);
                $(cinput).attr("hidden","hidden");
                var ctextarea=document.createElement("textarea");
                $(ctextarea).attr("name","content");
                $(ctextarea).attr("class","form-control");
                $(ctextarea).attr("style","resize: none;margin-bottom: 5px");
                $(ctextarea).attr("placeholder","请输入评论");
                $(ctextarea).attr("cols",20);
                $(ctextarea).attr("rows",1);

                var cbutton=document.createElement("span");
                $(cbutton).html("回复");
                $(cbutton).attr("class","btn btn-primary");
                $(cbutton).attr("onclick","addCommentComment(this)");

                $(cform).append(cinput,ctextarea,cbutton);

                $(div1).append(dcontent);
                $(div2).append(date,duser,dfloor,commenticon,cform);
                $(div).append(div1,div2);



                $("#mydiv").append(div);
            }
            return false;
        }
    });
    return false;
}

function commentCommentToggle(spanicon) {
    $(spanicon).next().toggle();
}

function addCommentComment(button) {
    var id=$("#modal-id").html();
    $.ajax({
        url:"/addComment",
        type:"post",
        data:{
            content:$(button).prev().val(),
            twitter:$("#modal-id").html(),
            parent:$(button).prev().prev().val()
        },
        success:function (msg) {
            if(msg==true){
                updatacomment($("#modal-id").html());
                $("#comment").val("");
            }
            return false;
        }
    });
    return false;
}

function addcomment() {
    var id=$("#modal-id").html();
    $.ajax({
        url:"/addComment",
        type:"post",
        data:{
            content:$("#comment").val(),
            twitter:$("#modal-id").html()
        },
        success:function (msg) {
            if(msg==true){
                updatacomment($("#modal-id").html());
                $("#comment").val("");
            }
            return false;
        }
    });
    return false;
}

function praise_click() {
    $.ajax({
        url:"/addPraise",
        type:"get",
        data:"twitterId="+$("#modal-id").html(),
        success:function (msg) {
            /*console.log("+++++++++++");*/
            if(msg==true){
                /*console.log("点赞成功");*/
                $("#unpraise").show();
                $("#praise").hide();
            }
        }
    });
}

function unpraise_click() {
    $.ajax({
        url:"/deletePraise",
        type:"get",
        data:"twitterId="+$("#modal-id").html(),
        success:function (msg) {
            /*console.log("----------");*/
            if(msg==true){
                $("#unpraise").hide();
                $("#praise").show();
                /*console.log("取消点赞成功");*/
            }
        }
    });
}

function commentToggle() {
    $("#commentTFrom").toggle();
}