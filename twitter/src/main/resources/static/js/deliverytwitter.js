function deliverytwitter () {
    var flag=false;
    var imageArray=[];
    $("#picture_list div img").each(function () {
        var base64coder=$(this).attr("src");
        base64coder=base64coder.replace(",","&comma;");
        imageArray.push(base64coder);
    });

    console.log("进入到方法  ");

    var jsondata={};
    jsondata.title=$("#title").val();
    jsondata.content=$("#content").val();

    if(imageArray.length>0){
        jsondata.imageArray=imageArray;
    }else{
        jsondata.imageArray=["null"];
    }

    $.ajax({
        url:"/deliverytwitter",
        type:"post",
        async:false,
        cache:false,
        data:jsondata,
        success:function(msg){
            flag=msg;
            /*console.log("成功的进行了ajax异步");*/
            if(flag==false||flag=="false"){
                alert("发布推特失败");
            }
            return flag;
        }
    });

    return flag;
}


function showPictureList() {
    $("#picture_list").show();
}

function convertBase64() {
    $.ajaxFileUpload({
        url:"/convertBase64",
        secureuri:false,
        dataType:'text',
        fileElementId:'fileupload',
        success:function (data) {
            console.log("上传图片成功，有信息传入");
            var containerDom=document.createElement("div");
            $(containerDom).attr("style","display:inline-block;position:relative");

            var spanDom =document.createElement("span");
            $(spanDom).attr("style","position:absolute;right:25px;top:10px;font-size:x-large;cursor:pointer;");
            $(spanDom).attr("class","glyphicon glyphicon-remove-circle");
            $(spanDom).attr("title","点击删除图片");
            $(spanDom).attr("onclick","removePicture(this)");

            var imgDom=document.createElement("img");
            $(imgDom).attr("style","display: inline-block;width:200px;height: 200px;border-width: 0px;border-radius: 10px;margin-right: 20px;margin-bottom:10px;");
            $(imgDom).attr("src",data);

            $(containerDom).append(imgDom,spanDom);

            $("#addPicture").before(containerDom);

            console.log("执行完毕");
        }
    });
}

function removePicture(spanDom) {
    $(spanDom).parent().remove();
}

function addPicture(){
    $("#fileupload").click();
}
