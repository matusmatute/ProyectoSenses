<script src="admin/assets/vendors/js/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page -->
<script src="admin/assets/vendors/chart.js/Chart.min.js"></script>
<script src="admin/assets/js/jquery.cookie.js" type="text/javascript"></script>
<!-- End plugin js for this page -->
<!-- inject:js -->
<script src="admin/assets/js/off-canvas.js"></script>
<script src="admin/assets/js/hoverable-collapse.js"></script>
<script src="admin/assets/js/misc.js"></script>
<!-- endinject -->
<!-- Custom js for this page -->
<script src="admin/assets/js/dashboard.js"></script>
<script src="admin/assets/js/todolist.js"></script>
<script src="https://cdn.tailwindcss.com"></script>
<script>
    $(document).ready(function (e) {
        $('#imagen').change(function() {
            let reader = new FileReader();
            reader.onload= (e) => {
                $('#imagenSeleccionada').attr('src', e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        });
    });
</script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.formAprobar')
        Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
            event.preventDefault()
            event.stopPropagation()
            Swal.fire({
                title: 'Confirmar',
                icon: 'info',
                showCancelButton: true,
                confirmButtonColor: '#20c997',
                cancelButtonColor: '6c757d',
                ConfirmButtonText: 'Confirmar'
            }).then((result)=> {
                if (result.isConfirmed) {
                    this.submit();
                    Swal.fire('!Confirmado!','Registado en la tabla','success');
                    }
            })
        }, false)
        })
    })()
</script>
<script>
(function () {
    'use strict'
    var forms = document.querySelectorAll('.formDenegar')
    Array.prototype.slice.call(forms)
    .forEach(function (form) {
        form.addEventListener('submit', function (event) {
        event.preventDefault()
        event.stopPropagation()
        Swal.fire({
            title: 'Desea cancelar la cita?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#20c997',
            cancelButtonColor: '6c757d',
            ConfirmButtonText: 'Confirmar'
        }).then((result)=> {
            if (result.isConfirmed) {
                this.submit();
                Swal.fire('!Denegado!','Registado en la tabla','success');
                }
        })
    }, false)
    })
})()
</script>
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