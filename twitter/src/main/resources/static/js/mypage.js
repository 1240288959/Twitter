function upload_click() {
    $("#fileupload").click();
}

function fileupload_change() {
    $.ajaxFileUpload({
        url:"/setImage",
        secureuri:false,
        fileElementId:'fileupload',
        success:function (msg) {
            if(msg==false){
                alert("false");
            }else{
                alert("success");
            }
            return false;
        },
        error:function (msg) {
            if(msg==false){
                alert("false");
            }else{
                alert("success");
            }
            return false;
        }
    });
    return false;
}