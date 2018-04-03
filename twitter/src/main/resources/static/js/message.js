function getunread() {
    $.ajax({
        url:"/getunread",
        type:"post",
        success:function (count) {
            if(count==0){
                $(".badge").hide();
            }else{
                $(".badge").text(count);
                $(".badge").show();
            }
        }
    })
}