function getunread() {
    console.log("-----")
    $.ajax({
        url:"/getunread",
        type:"post",
        success:function (count) {
            console.log(count);
            if(count==0){
                $(".badge").hide();
            }else{
                $(".badge").text(count);
                $(".badge").show();
            }
        }
    })
}