$(document).ready(function () {
    $('#get_all').click(function (){
        $.get("/employees",
        function(employees) {
            $('#result').empty();
            $.each(employees, function(i, employee) {
                $('#result').append("Id: " + employee.id + ", Name: " + employee.name + ", Role: " + employee.role + ", Salary: " + employee.salary + "k €<br>");
            });
        });
    });
    $('#get_one').click(function (){
        if ($('#id_one').val()) {
            var url="/employees/" + $('#id_one').val();
            $.get(url,
            function(employee) {
                $('#result_get_one').empty();
                $('#result_get_one').append("Id: " + employee.id + ", Name: " + employee.name + ", Role: " + employee.role + ", Salary: " + employee.salary + "k €<br>");
            }).fail(function(stat){
                $('#result_get_one').empty();
                $('#result_get_one').append(stat.responseText);
            });
        }
    });
    $('#add_one').click(function (){
        if ($('#post_name').val() && $('#post_role').val()){
            $.ajax({
                url: "/employees",
                dataType: "json",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({"name": $('#post_name').val(),"role": $('#post_role').val()}),
                processData: false,
                success: function(employee){
                    $('#result_add_one').empty();
                    $('#result_add_one').append("Successfully added employee!!!<br>");
                    $('#result_add_one').append("Id: " + employee.id + ", Name: " + employee.name + ", Role: " + employee.role + ", Salary: " + employee.salary + "k €<br>");
                },
                error: function(stat) {
                    alert(stat.responseText);
                }
            });
        } else {
            alert("Please fill in name and role to create a new employee.");
        }
    });
    $('#update').click(function (){
        if ($('#id_put').val() && ($('#post_name').val() || $('#post_role').val())){
            $.ajax({
                url: "/employees/" + $('#id_put').val(),
                dataType: "json",
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify({"name": $('#post_name').val(),"role": $('#post_role').val()}),
                processData: false,
                success: function(employee){
                    $('#result_add_one').empty();
                    $('#result_add_one').append("Successfully updated employee!!!<br>");
                    $('#result_add_one').append("Id: " + employee.id + ", Name: " + employee.name + ", Role: " + employee.role + ", Salary: " + employee.salary + "k €<br>");
                },
                error: function(stat) {
                    $('#result_add_one').empty();
                    $('#result_add_one').append(stat.responseText);
                    // alert(stat.responseText);
                }
            });
        } else {
            alert("Please fill in employee id and at least name or salary to update an employee.");
        }
    });
    $('#delete').click(function (){
        if ($('#id_put').val()){
            $.ajax({
                url: "/employees/" + $('#id_put').val(),
                type: "DELETE",
                success: function(){
                    $('#result_add_one').empty();
                    $('#result_add_one').append("Successfully deleted employee!!!<br>");
                },
                error: function(stat) {
                    $('#result_add_one').empty();
                    $('#result_add_one').append(stat.responseText);
                }
            });
        }
    });
});