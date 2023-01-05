
$(document).ready(function(){

    PopUpHideHistory();
    PopUpHideAddSmallTask();

    $('#document[type="file"]').change(function(){

        var btn_add_document = document.getElementById("btn_add_document")
        btn_add_document.style.display = "none"

        var block_add_file = document.getElementById("block_add_file")
        block_add_file.style.display = "flex"

        var name_file = document.getElementById("name_document")
        var input_file = document.getElementById("document");
        name_file.textContent = input_file.files[0].name

    });

});


function delete_file() {
    var btn_add_document = document.getElementById("btn_add_document")
    btn_add_document.style.display = "flex"

    var block_add_file = document.getElementById("block_add_file")
    block_add_file.style.display = "none"

    var input_file = document.getElementById("document");
    input_file.value = ""
}


function useCalendar() {
    var calendar = document.getElementById("id_img_calendar");
    var list = document.getElementById("id_img_list");
    var list_my_all_tasks = document.getElementById("list_my_all_tasks");
    var search = document.getElementById("id_search");
    var filter = document.getElementById("id_filters");

    var full_calendar = document.getElementById("id_full_calendar");


    if (calendar.style.display === "block"){
        calendar.style.display = "none"
        list.style.display = "block"

        full_calendar.style.display = "block"
        list_my_all_tasks.style.display = "none"
        search.style.display = "none"
        filter.style.display = "none"
    }else{
        calendar.style.display = "block"
        list.style.display = "none"

        full_calendar.style.display = "none"
        list_my_all_tasks.style.display = "block"
        search.style.display = "flex"
        filter.style.display = "block"
    }
}


function PopUpHideHistory(){
    $("#popUpHistory").hide();
}

function PopUpShowHistory(){
    $("#popUpHistory").show();
}

function addItTask() {
    var form_send_task = document.getElementById("form_to_post_task");
    form_send_task.action = "/addNewItTask"

    var list_my_all_tasks = document.getElementById("list_my_all_tasks");
    list_my_all_tasks.style.display = "none"

    var calendar = document.getElementById("id_full_calendar");
    calendar.style.display = "none"

    var btn_or_in_tasks = document.getElementById("id_btn_or_in_tasks");
    btn_or_in_tasks.style.display = "none"

    var add_new_task = document.getElementById("add_new_task");
    add_new_task.style.display = "flex"

    var block_small_task = document.getElementById("block_small_task")
    block_small_task.style.display = "block"

    var add_users_in_task = document.getElementById("block_add_users")
    add_users_in_task.style.display = "none"

    var search = document.getElementById("id_search");
    search.style.display = "none"

    var filters = document.getElementById("id_filters");
    filters.style.display = "none"
}

function addOrderTask() {
    var form_send_task = document.getElementById("form_to_post_task");
    form_send_task.action = "/addNewOrderTask"

    var list_my_all_tasks = document.getElementById("list_my_all_tasks");
    list_my_all_tasks.style.display = "none"

    var calendar = document.getElementById("id_full_calendar");
    calendar.style.display = "none"

    var btn_or_in_tasks = document.getElementById("id_btn_or_in_tasks");
    btn_or_in_tasks.style.display = "none"

    var add_new_task = document.getElementById("add_new_task");
    add_new_task.style.display = "flex"

    var block_small_task = document.getElementById("block_small_task")
    block_small_task.style.display = "none"

    var add_users_in_task = document.getElementById("block_add_users")
    add_users_in_task.style.display = "block"

    var search = document.getElementById("id_search");
    search.style.display = "none"

    var filters = document.getElementById("id_filters");
    filters.style.display = "none"
}

function PopUpHideAddSmallTask(){
    $("#popUpAddSmallTask").hide();
}

function PopUpShowAddSmallTask(){
    $("#popUpAddSmallTask").show();
}

function add() {
    
}