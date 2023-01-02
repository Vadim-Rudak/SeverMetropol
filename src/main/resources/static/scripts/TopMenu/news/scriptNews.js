
$(document).ready(function() {

    $('#custom-file-upload[type="file"]').change(function(){

        var btn_clear_photo = document.getElementById("btn_clear_photo");
        btn_clear_photo.style.display = "block"

        var img = document.getElementById("img_news");
        var input_file = document.getElementById("custom-file-upload");
        var fil = input_file.files[0];
        if(fil){
            img.src = URL.createObjectURL(fil);
            localStorage.setItem('myImage', img.src);
        }

        img.src = localStorage.getItem('myImage')

    });
});

function clearPhoto() {
    var btn_clear_photo = document.getElementById("btn_clear_photo");
    btn_clear_photo.style.display = "none"

    var img = document.getElementById("img_news");
    img.src = "images/default_img_add_news.jpg";
}