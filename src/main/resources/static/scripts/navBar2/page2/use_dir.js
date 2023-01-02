
function openSmallDir() {

    var all_top_dirs = document.getElementById("id_all_top_dirs");
    all_top_dirs.style.display = "none"

    var see_small_dir = document.getElementById("files_in_small_dir");
    see_small_dir.style.display = "block"

}

function clouseSmallDir(){
    var all_top_dirs = document.getElementById("id_all_top_dirs");
    all_top_dirs.style.display = "flex"

    var see_small_dir = document.getElementById("files_in_small_dir");
    see_small_dir.style.display = "none"
}