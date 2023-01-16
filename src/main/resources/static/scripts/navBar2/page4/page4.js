function clickBox3(){
    var status_checkbox_user_work = document.getElementById("checkbox_user_work")
    var block_user_select = document.getElementById("block_user_select")

    if (status_checkbox_user_work.checked){
        block_user_select.style.display = "block"

    }else{
        block_user_select.style.display = "none"
    }

}