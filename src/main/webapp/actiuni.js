function newToDo() {
    var name = document.getElementById('name').value;
    var action = "NEW";
    $.ajax({
        url: 'addfood?foodname='+name
    }).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function loadToDo() {
    $.ajax({
        url: 'listfood'
    }).done(function (response) {
        printOnDiv(response.listFromBackend);
    });
}

// function deleteAll() {
//     $.ajax({
//         url: 'manageMyToList?action=DELETE'
//     }).done(function (response) {
//         printOnDiv(response.listFromBackend); // ne vom asigura ca din backend ne vine listFromBackend goala
//     });
// }




function printOnDiv(listFromBackend) {
    var listHtml = '';

    var list = document.getElementById('listOfToDo');

    for (var i = 0; i < listFromBackend.length; i++) {
        var elemC = listFromBackend[i];
        var el = '<li>'+elemC+'</li>';
        listHtml=listHtml+el;
    }
    list.innerHTML = '<ol>'+listHtml+'</ol>';
}


