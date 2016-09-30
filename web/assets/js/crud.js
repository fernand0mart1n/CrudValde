function eliminar(cliente_id){
    
    // id del cliente a dar de baja
    var info = 'id=' + cliente_id;

    // onclick eliminar abre el modal
    $("#myModal").modal("show");
    
    $("#btnDel").click(function () {
        $.ajax({
            type: "POST",
            url: "eliminarcontroller.php",
            data: info, // pasamos el id por post al controller que hace la baja
            success: function(){
                // redirigimos al index y desde el controller enviamos un mensaje de éxito o error
                window.location.href = 'homecontroller.php';
            }
        });
    });

    return false;

}

$(document).ready(function(){

    // animación al cerrar alertas de errores de validación
    $(".alert-danger button.close").click(function (e) {
        $(this).parent().hide('slow');
    });

    // animación para que los mensajes informativos se vayan automáticamente
    setTimeout(function() {
        $('.alert-info').hide('slow');
    }, 6000);

    // si la tabla queda vacía, muestra un mensaje de que no hay registros
    $("table tbody").not(":has(td)").html("<tr><td colspan=60%>No se han cargado clientes</td></tr>");

    $(function() {
        var dateSupported = (function() {
            var el = document.createElement('input'),
            invalidVal = 'foo'; // Any value that is not a date
            el.setAttribute('type','date');
            el.setAttribute('value', invalidVal);
            // A supported browser will modify this if it is a true date field
            return el.value !== invalidVal;
        }());
        if (!dateSupported) {

            $( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd'}); 

        }
        
    });
});
