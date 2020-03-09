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
    $('#add_one').click(function (){
        if ($('#post_name').val() && $('#post_role').val()){
//            alert("Name: " + $('#post_name').val() + "\nRole: " + $('#post_role').val());
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
                    $('#result_add_one').append("Id: " + employee.id + ", Name: " + employee.name + ", Role: " + employee.role);
                },
                error: function(stat) {
                    alert(stat.responseText);
                }
            });
        } else {
            alert("Faltan campos!!!");
        }
    });
});