
function clickCheckBox(num_checkbox,task_id) {
    var user_role = document.getElementById("user_role");
    var status_check = document.getElementById("check" + task_id)
    console.log(status_check.checked)
    status_check.disabled = true

    sendToUser(task_id,user_role.textContent,status_check.checked)
    
}

function sendToUser(task_id,user_role,check_status) {

    var xhr = new XMLHttpRequest();

    var body = 'task_id=' + task_id + '&user_role=' + user_role + '&check_status=' + check_status;

    xhr.open("POST", '/upDateOrderTaskStatus', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            //var json = JSON.parse(xhr.responseText);
            console.log(xhr.status);
        }
    };

    xhr.send(body);
    
}