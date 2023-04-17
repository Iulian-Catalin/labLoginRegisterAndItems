function newToDo() {
    var name = document.getElementById('name').value;
    var urlEnc = encodeURI('addfood?foodname=' + name)
    $.ajax({
        url: urlEnc
    }).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function loadToDo() {
    $.ajax({
        url: 'listfood'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display(response.listFromBackend);
    });
}

// function deleteAll() {
//     $.ajax({
//         url: 'manageMyToList?action=DELETE'
//     }).done(function (response) {
//         printOnDiv(response.listFromBackend); // ne vom asigura ca din backend ne vine listFromBackend goala
//     });
// }

function display(lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.foodName + "</td>" +
            "<td>" + obiect.foodDate + "</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect").html(randuri);
}

function search(myText) {
    $.ajax("listfood", {
        cache: false,
        dataType: "json",
        data: {
            // order: ordinea,
            search: myText
        }
    }).done(function (response) {
        display(response.listFromBackend);
    });
}


function printOnDiv(listFromBackend) {
    var listHtml = '';

    var list = document.getElementById('listOfToDo');

    for (var i = 0; i < listFromBackend.length; i++) {
        var elemC = listFromBackend[i];
        var el = '<li>' + elemC.foodName + ' ' + elemC.foodDate + '</li>';
        listHtml = listHtml + el;
    }
    list.innerHTML = '<ol>' + listHtml + '</ol>';
}


