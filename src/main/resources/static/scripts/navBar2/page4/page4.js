
var list_add_users = new Map();

function clickBox3(){
    var status_checkbox_user_work = document.getElementById("checkbox_user_work")
    var block_user_select = document.getElementById("block_user_select")

    if (status_checkbox_user_work.checked){
        block_user_select.style.display = "block"

    }else{
        block_user_select.style.display = "none"
    }

}

function addUserInTask(user_id) {
    var btn_select_user = document.getElementById("btn_select_user_" + user_id)
    var btn_cansel_user = document.getElementById("btn_cansel_user_" + user_id)

    btn_select_user.style.display = "none"
    btn_cansel_user.style.display = "block"

    list_add_users.set(user_id,user_id)
    console.log(list_add_users.size)
}

function deleteUserInTask(user_id) {
    var btn_select_user = document.getElementById("btn_select_user_" + user_id)
    var btn_cansel_user = document.getElementById("btn_cansel_user_" + user_id)

    btn_select_user.style.display = "block"
    btn_cansel_user.style.display = "none"

    list_add_users.delete(user_id)
    console.log(list_add_users.size)
}

