<!DOCTYPE html>
<html lang="en">
@include('user.estilos.head')
<header>
    @include('user.estilos.header')
</header>
<body>
    <div class="container-scroller">
        <!-- partial:../../partials/_navbar.html -->
        <!-- partial -->
        <div class="container-fluid page-body-wrapper">
          <!-- partial:../../partials/_sidebar.html -->
          <!-- partial -->
          <div class="main-panel">
              <div class="row">
                <div class="col-lg-12 grid-margin stretch-card">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="card-title">Citas <code>Registradas</code></h4>
                      <table class="table table-bordered">
                        <thead>
                          <tr>
                            <th> Dentista seleccionado </th>
                            <th> Horario </th>
                            <th>Cita</th>
                            <th>Cancelar Cita</th>
                          </tr>
                        </thead>
                        <tbody>
                          @foreach ($appoint as $appo)
                          <tr>
                            <td> {{$appo->dentist}} </td>
                            <td> {{$appo->date}} </td>
                            <td> {{$appo->status}} </td>
                            <td class="px-6 py-4 text-center"> 
                              <form action="{{url('cancel_appoint',$appo->id)}}" method="get" class="formEliminar">
                                  @csrf
                                  @method('DELETE')
                                  <button type="submit" style="color: red;">Cancelar</button>
                              </form>
                            </td>
                          </tr>
                        </tbody>
                        @endforeach
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <footer class="footer">
              <div class="container-fluid d-flex justify-content-between">
                <span class="text-muted d-block text-center text-sm-start d-sm-inline-block">Copyright © bootstrapdash.com 2021</span>
                <span class="float-none float-sm-end mt-1 mt-sm-0 text-end"> Free <a href="https://www.bootstrapdash.com/bootstrap-admin-template/" target="_blank">Bootstrap admin template</a> from Bootstrapdash.com</span>
              </div>
            </footer>
            <!-- partial -->
          </div>
          <!-- main-panel ends -->
        </div>
        <!-- page-body-wrapper ends -->
      </div>
      @include('user.estilos.scripts')
</body>
</html>