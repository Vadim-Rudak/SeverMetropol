
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

function openWindowAddUsersToSendShow(){
    var popUpAddUsers = document.getElementById("popUpUsers")
    popUpAddUsers.style.display = "flex"

    var list_users = document.getElementById("list_users")

    for(var m = 0; m<list_add_users.size;m++){
        var l = Array.from(list_add_users.keys())[m]

        var btn_select_user = document.getElementById("btn_select_user_" + l)
        var btn_cansel_user = document.getElementById("btn_cansel_user_" + l)

        btn_select_user.style.display = "block"
        btn_cansel_user.style.display = "none"
    }



    var old_map = new Map();
    var x = 0;
    for(var j = 0; j<list_users.value.toString().length;j++){
        if (list_users.value.toString().charAt(j)===","){
            old_map.set(list_users.value.toString().substring(x,j),list_users.value.toString().substring(x,j))
            x = j + 1;
        }
    }
    if (x<list_users.value.toString().length){
        old_map.set(list_users.value.toString().substring(x),list_users.value.toString().substring(x))
    }

    for (var i = 0; i<old_map.size;i++){
        console.log("p" + i)
        var k = Array.from(old_map.keys())[i]
        console.log('M' + k)
        var btn_select_user = document.getElementById("btn_select_user_" + k)
        var btn_cansel_user = document.getElementById("btn_cansel_user_" + k)

        btn_select_user.style.display = "none"
        btn_cansel_user.style.display = "block"

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

function addUsersInArray(){
    var list_users = document.getElementById("list_users")
    var popUpAddUsers = document.getElementById("popUpUsers")

    list_users.value = [...list_add_users.keys()]
    popUpAddUsers.style.display = "none"

    var text_select_users = document.getElementById("text_users_to_send")
    if(list_add_users.size === 1){
        text_select_users.textContent = "Выбран 1 сотрудник"
    }else{
        text_select_users.textContent = "Выбрано " + list_add_users.size + " сотрудника(-ов)"
    }

    var id_btn_add_users = document.getElementById("id_btn_add_users")
    var id_info_add_users = document.getElementById("id_info_add_users")


    if(list_add_users.size>0){
        id_btn_add_users.style.display = "none"
        id_info_add_users.style.display = "flex"
    }else{
        id_btn_add_users.style.display = "flex"
        id_info_add_users.style.display = "none"
    }

}