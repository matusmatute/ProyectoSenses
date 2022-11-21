<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="admin/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="admin/assets/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="admin/assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="admin/assets/images/favicon.ico" />
</head>
<body>
    <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
        <div class="row flex-grow">
            <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left p-5">
                <div class="brand-logo">
                <img src="admin/assets/images/logo.svg">
                </div>
                <h4>Hola! Inicia sesion </h4>
                <h6 class="font-weight-light">Ingresa tus datos</h6>
                @if (session('status'))
                <div class="mb-4 font-medium text-sm text-green-600">
                    {{ session('status') }}
                </div>
                @endif
                <form class="pt-3" method="POST" action="{{ route('login') }}">
                @csrf
                <div class="form-group">
                    <x-jet-input id="email" class="form-control form-control-lg" type="email" name="email" :value="old('email')" placeholder="Usuario" required autofocus />
                </div>
                <div class="form-group">
                    <x-jet-input id="password" class="form-control form-control-lg" type="password" name="password" placeholder="Contraseña" required autocomplete="current-password" />
                </div>
                <div class="mt-3">
                    <x-jet-button class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
                        {{ __('Iniciar sesion') }}
                    </x-jet-button>
                    
                </div>
                <div class="my-2 d-flex justify-content-between align-items-center">
                    <div class="form-check">
                    <label class="form-check-label text-muted">
                        <input type="checkbox" class="form-check-input"> Recordarme </label>
                    </div>
                    
                </div>
                <div class="text-center mt-4 font-weight-light"> Deseas registrarte? <a href="{{route('register')}}" class="text-primary">Crear</a>
                </div>
                <div class="text-center mt-4 font-weight-light">

                    <p><a href="{{  url('/')   }}">Salir</a></p>
                </div>
                <div class="flex items-center justify-end mt-4">
                    @if (Route::has('password.request'))
                        <a class="underline text-sm text-gray-600 hover:text-gray-900" href="{{ route('password.request') }}">
                            {{ __('Forgot your password?') }}
                        </a>
                    @endif
                </form>
            </div>
            </div>
        </div>
        </div>
        <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="admin/assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="admin/assets/js/off-canvas.js"></script>
    <script src="admin/assets/js/hoverable-collapse.js"></script>
    <script src="admin/assets/js/misc.js"></script>
    <!-- endinject -->
</body>
</html>
            