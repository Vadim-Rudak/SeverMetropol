
$(document).ready(function() {

    $('#custom-file-upload[type="file"]').change(function(){

        var btn_clear_photo = document.getElementById("btn_clear_photo");
        btn_clear_photo.style.display = "block"

        var img = document.getElementById("img_user_in_form_add");
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

    var img = document.getElementById("img_user_in_form_add");
    img.src = "images/user_photo.jpg";
}


function editUserStatus(user_id){
    var checkbox_in_top_menu = document.getElementById("act_user_in_site");

    var xhr = new XMLHttpRequest();

    var body = 'user_id=' + user_id +
        '&active_status=' + checkbox_in_top_menu.checked;

    xhr.open("POST", '/upDateActiveProfile', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            //var json = JSON.parse(xhr.responseText);
            console.log(xhr.status);
        }
    };

    xhr.send(body);


}