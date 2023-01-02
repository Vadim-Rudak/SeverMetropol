
$(document).ready(function(){

    PopUpHideHistory();
    PopUpHideAddSmallTask();

});


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
    var list_my_all_tasks = document.getElementById("list_my_all_tasks");
    list_my_all_tasks.style.display = "none"

    var calendar = document.getElementById("id_full_calendar");
    calendar.style.display = "none"

    var btn_or_in_tasks = document.getElementById("id_btn_or_in_tasks");
    btn_or_in_tasks.style.display = "none"

    var add_new_task = document.getElementById("add_new_task");
    add_new_task.style.display = "flex"

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