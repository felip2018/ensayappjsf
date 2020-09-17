function verModal() {
    console.log("Esta funcion mostrara la modal de Bootstrap!")
    jQuery('#myModal').modal('show')
    jQuery(".modal-title").html('Register Form');
    jQuery(".modal-body").html('<form class="row">' +
            '<div class="col-12">' +
            '<p><b>Name</b></p>' +
            '<input class="form-control" type="text" name="nombre" id="nombre">' +
            '</div>' +
            '<div class="col-12">' +
            '<p><b>Last Name</b></p>' +
            '<input class="form-control" type="text" name="apellido" id="apellido">' +
            '</div>' +
            '<div class="col-12">' +
            '<p><b>Email</b></p>' +
            '<input class="form-control" type="text" name="correo" id="correo">' +
            '</div>' +
            '</form>');

    jQuery(".modal-footer").html('<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button><button type="button" class="btn btn-primary">Sign up</button>')
}

