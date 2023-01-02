
function PopUpHideDeleteUser(){
    var popUpAddUser = document.getElementById("popUpDeleteUser");
    popUpAddUser.style.display = "none"
}

function PopUpShowDeleteUser(user_id){
    var popUpAddUser = document.getElementById("popUpDeleteUser");
    popUpAddUser.style.display = "flex"

    var btn_delete_in_popUp = document.getElementById("btn_delete_in_popUp");
    btn_delete_in_popUp.href = "/deleteUser?user_id=" + user_id;
}