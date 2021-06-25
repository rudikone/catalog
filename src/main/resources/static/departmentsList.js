//функция для отображения всех отделов
function loadDepartments() {
    fetch("http://localhost:8080/admin/departments")
        .then(response => response.json())
        .then(data => {
            $("#home").empty();
            let table = ""
            for (let i = 0; i < data.length; i++) {

                table += ('<div>' + data[i].id + '</div>')
                table += ('<div>' + data[i].name + '</div>')

                // table += ('<td><button id="' + data[i].id + '" type="button" class="edit btn btn-primary" data-toggle="modal" data-target="#editModal" data-id="' + data[i].id + '">Edit</button></td>')
                // table += ('<td><button type="button" class="delete btn btn-danger" data-toggle="modal" data-target="#deleteModal" data-id="' + data[i].id + '">Delete</button></td>')
                //
            }
            $('#home').append(table)
        });
}

loadDepartments()

$('#home-tab').on('click', loadDepartments());