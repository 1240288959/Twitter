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
                $(divDom).attr("style","word-break:break-all;margin:3px 0 5px 0;");
                var spanDom=document.createElement("span");
                $(spanDom).attr("class","text-right");
                $(spanDom).attr("style","text-decoration:underline;float:right;");
                $(spanDom).attr("onmouseover","javascript:this.style.color='blue';");
                $(spanDom).attr("onmouseout","javascript:this.style.color='black';");
                $(spanDom).attr("onclick","showmodal('"+msg[i].twitter.id+"')");
                $(spanDom).html("点击此处查看详情");

                var spanDom2=document.createElement("span");
                $(spanDom2).attr("style","text-left");
                $(spanDom2).html(new Date(msg[i].date).Format("yyyy-MM-dd hh:mm:ss"));

                $(divDom).append(spanDom2,spanDom);
                $(liBodyDom).append(bDom,divDom);
                $(liDom).append(liBodyDom);
                $(ulDom).append(liDom);
            }
            getunread();
        }
    });
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

