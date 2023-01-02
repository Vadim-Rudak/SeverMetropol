
$(document).ready(function(){

    PopUpHideAddNews();
    var dashboard = document.getElementById("dashboard_1");
    var dashboard2 = document.getElementById("dashboard_2");
    var list_news = document.getElementById("id_list_news");
    var check_box = document.getElementById("bool_chek_news");
    var btn_dath_and_news = document.getElementById("d");
    if (check_box.checked){
        dashboard.style.display = "none";
        dashboard2.style.display = "none";
        list_news.style.display = "block";
        btn_dath_and_news.checked = true;
    }else{
        dashboard.style.display = "flex";
        dashboard2.style.display = "flex";
        list_news.style.display = "none";
        btn_dath_and_news.checked = false;
    }

});


function PopUpHideAddNews(){
    var checkbox_in_top_menu = document.getElementById("checkbox_in_top_menu");
    checkbox_in_top_menu.style.display = "block"

    var popUpAddNews = document.getElementById("popUpAddNews");
    popUpAddNews.style.display = "none"
}

function PopUpShowAddNews(){
    var checkbox_in_top_menu = document.getElementById("checkbox_in_top_menu");
    checkbox_in_top_menu.style.display = "none"

    var popUpAddNews = document.getElementById("popUpAddNews");
    popUpAddNews.style.display = "flex"
}


function click_checkbox() {
    var check_dash_or_lenta = document.getElementById("d");

    var dashboard = document.getElementById("dashboard_1");
    var dashboard2 = document.getElementById("dashboard_2");

    var list_news = document.getElementById("id_list_news");


    if(check_dash_or_lenta.checked){
        //Dashboard
        dashboard.style.display = "flex";
        dashboard2.style.display = "flex";
        list_news.style.display = "none";

    }else {
        //Lenta
        dashboard.style.display = "none";
        dashboard2.style.display = "none";
        list_news.style.display = "block";

    }
}