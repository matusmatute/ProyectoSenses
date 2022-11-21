<script src="../assets/js/jquery-3.5.1.min.js"></script>

<script src="../assets/js/bootstrap.bundle.min.js"></script>

<script src="../assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="../assets/vendor/wow/wow.min.js"></script>

<script src="../assets/js/theme.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
(function () {
    'use strict'
    var forms = document.querySelectorAll('.formEliminar')
    Array.prototype.slice.call(forms)
    .forEach(function (form) {
        form.addEventListener('submit', function (event) {
        event.preventDefault()
        event.stopPropagation()
        Swal.fire({
            title: 'Confirmar eliminacion?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#20c997',
            cancelButtonColor: '6c757d',
            ConfirmButtonText: 'Confirmar'
        }).then((result)=> {
            if (result.isConfirmed) {
                this.submit();
                Swal.fire('!Eliminado!','El registro se ha eliminado exitosamente','success');
                }
        })
    }, false)
    })
})()
</script>