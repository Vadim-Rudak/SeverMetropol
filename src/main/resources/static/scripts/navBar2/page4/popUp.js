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

function PopUpShowHistory(id_task){
    $("#popUpHistory").show();
    getListHistory(id_task)
}

function getListHistory(task_id) {
    let xhr = new XMLHttpRequest();
    var url = "/taskHistory?task_id=" + task_id;

    xhr.open('GET', url, false);
    xhr.send();
    if (xhr.status !== 200){
        alert(`Ошибка ${xhr.status}: ${xhr.statusText}`);
    }else {
        var list_items = document.getElementById("history_list")
        var json = JSON.parse(xhr.response);
        if(json.length === 0){
            //None history list
            console.log("None list")
            list_items.innerHTML = ""
        }else {
            //Have history list
            var all_item_to_add = "";
            for (var i=0;i<json.length;i++){
                var item = json[i];
                all_item_to_add += '<div class="text_hist">' + item.date_add + " " + item.time_add + " >> " + item.info + '</div>';
            }
            list_items.innerHTML = all_item_to_add;
        }
    }
}

function openPageAddTask(classificationTask) {

    var form_send_task = document.getElementById("form_to_post_task");
    var list_my_all_tasks = document.getElementById("list_my_all_tasks");
    var calendar = document.getElementById("id_full_calendar");
    var btn_or_in_tasks = document.getElementById("id_btn_or_in_tasks");
    var add_new_task = document.getElementById("add_new_task");
    var search = document.getElementById("id_search");
    var filters = document.getElementById("id_filters");
    var block_small_task = document.getElementById("block_small_task")
    var add_users_in_task = document.getElementById("block_add_users")


    list_my_all_tasks.style.display = "none"
    calendar.style.display = "none"
    btn_or_in_tasks.style.display = "none"
    add_new_task.style.display = "flex"
    search.style.display = "none"
    filters.style.display = "none"

    switch (classificationTask){
        case "TaskIT":{
            form_send_task.action = "/addNewItTask"
            block_small_task.style.display = "block"
            add_users_in_task.style.display = "none"
            break;
        }
        case "TaskRepair":{
            form_send_task.action = "/addNewRepairTask"
            block_small_task.style.display = "block"
            add_users_in_task.style.display = "none"
            break;
        }
        case "TaskOrder":{
            form_send_task.action = "/addNewOrderTask"
            block_small_task.style.display = "none"
            add_users_in_task.style.display = "block"
            break;
        }
        case "TaskProduct":{
            form_send_task.action = "/addNewProductTask"
            block_small_task.style.display = "none"
            add_users_in_task.style.display = "block"
            break;
        }
        case "TaskTransport":{
            form_send_task.action = "/addNewTransportTask"
            block_small_task.style.display = "none"
            add_users_in_task.style.display = "block"
            break;
        }
    }
}

function PopUpHideAddSmallTask(){
    $("#popUpAddSmallTask").hide();
}

function PopUpShowAddSmallTask(){
    $("#popUpAddSmallTask").show();
}

function openWindowAddUsersToSendShow(){
    var popUpAddUsers = document.getElementById("popUpUsers")
    popUpAddUsers.style.display = "flex"
}

function openWindowAddUsersToSendHide(){
    var popUpAddUsers = document.getElementById("popUpUsers")
    popUpAddUsers.style.display = "none"
}