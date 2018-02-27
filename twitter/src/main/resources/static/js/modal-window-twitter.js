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
            /*console.log(msg);*/
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
                $(duser).html(msg[i].user.name);

                $(div1).append(dcontent);
                $(div2).append(date,duser);
                $(div).append(div1,div2);
                $("#mydiv").append(div);
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
        type:"get",
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