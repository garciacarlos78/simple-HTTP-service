$(document).ready(function () {
    $('#get_all').click(function (){
        $.get("/employees",
        function(employees) {
            $('#result').empty();
            $.each(employees, function(i, employee) {
                $('#result').append("Id: " + employee.id + ", Name: " + employee.name + ", Role: " + employee.role + "<br>");
            });
        });
    });
    $('#get_one').click(function (){
        if ($('#id_one').val()) {
            var url="/employees/" + $('#id_one').val();
            $.get(url,
            function(employee) {
                $('#result_get_one').empty();
                $('#result_get_one').append("Id: " + employee.id + ", Name: " + employee.name + ", Role: " + employee.role);
            }).fail(function(stat){
                $('#result_get_one').empty();
                $('#result_get_one').append(stat.responseText);
            });
        }
    });
});