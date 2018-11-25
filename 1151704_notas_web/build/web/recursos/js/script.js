(function ($) {
    $(document).ready(function () {
        // Cache the elements we'll need
        var menu = $('.main-menu');
        var menuList = menu.find('ul:first');
        var listItems = menu.find('li').not('#responsive-tab');

        // Create responsive trigger
        menuList.prepend('<li id="responsive-tab"><a href="#">Menu</a></li>');

        // Toggle menu visibility
        menu.on('click', '#responsive-tab', function () {
            listItems.slideToggle('fast');
            listItems.addClass('collapsed');
        });

        modales();
    });
})(jQuery);

function modales() {

    var cargarEventos = function (elementos) {
        for (var item in elementos) {

            $(elementos[item]).on('show.bs.modal', function (e) {
                var related = $(e.relatedTarget);
                $.post(related.data('url'), {id: related.data('id')}).done(function (data) {
                    $(e.currentTarget).find(".modal-body").html(data);
                });
            });

        }
    };
    var cajaListado = $("#cajaListadoFiltro");

    var cargarListado = function (url, filtro, elementos) {
        $.post(url, filtro).done(function (data) {
            cajaListado.html(data);
            cargarEventos(elementos);
        });
    };

    var modal_rellenar = [
        '#modal-carreraEditar', '#modal-carreraEliminar',
        '#modal-asignaturaEditar', '#modal-asignaturaEliminar',
        '#modal-requisitoEditar', '#modal-requisitoEliminar',
        '#modal-alumnoEditar', '#modal-alumnoEliminar',
        '#modal-tutorEditar', '#modal-tutorEliminar'
    ];


    if (cajaListado.length > 0) {

        var selectFiltro = $("#selectListadoFiltro");

        selectFiltro.on('change', function () {
            var item = $(this);
            cargarListado(item.data("url"), {id: this.value}, ['#modal-notaEditar', '#modal-notaEliminar']);
        });

    } else {
        cargarEventos(modal_rellenar);
    }
}


