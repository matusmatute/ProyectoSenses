
<!DOCTYPE html>
<html lang="en">
  @include('admin.estilo.head')
  <body>
    <div class="container-scroller">
      <div class="row p-0 m-0 proBanner" id="proBanner">
        <div class="col-md-12 p-0 m-0">
          <div class="card-body card-body-padding d-flex align-items-center justify-content-between">
            <div class="ps-lg-1">
              <div class="d-flex align-items-center justify-content-between">
                <p class="mb-0 font-weight-medium me-3 buy-now-text">Free 24/7 customer support, updates, and more with this template!</p>
                <a href="https://www.bootstrapdash.com/product/purple-bootstrap-admin-template/?utm_source=organic&utm_medium=banner&utm_campaign=buynow_demo" target="_blank" class="btn me-2 buy-now-btn border-0">Get Pro</a>
              </div>
            </div>
            <div class="d-flex align-items-center justify-content-between">
              <a href="https://www.bootstrapdash.com/product/purple-bootstrap-admin-template/"><i class="mdi mdi-home me-3 text-white"></i></a>
              <button id="bannerClose" class="btn border-0 p-0">
                <i class="mdi mdi-close text-white me-0"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- partial:partials/_navbar.html -->
      @include('admin.estilo.navbar')
      <!-- partial -->
      <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_sidebar.html -->
        @include('admin.estilo.menu')
        <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Lista de <code>Citas</code></h4>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th> Paciente </th>
                                            <th> Correo </th>
                                            <th> Telefono </th>
                                            <th> Nombre del Dentista </th>
                                            <th>Horario</th>
                                            <th>Estatus de Cita</th>
                                            <th> Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        @foreach ($data as $appoint)
                                        <tr>
                                            <td> {{$appoint->name}} </td>
                                            <td>{{$appoint->email}} </td>
                                            <td> {{$appoint->phone}} </td>
                                            <td> {{$appoint->dentist}} </td>
                                            <td> {{$appoint->date}} </td>
                                            <td> {{$appoint->status}} </td>
                                            <td class="px-6 py-4 text-center"> 
                                                <div>
                                                    <form action="{{url('approved',$appoint->id)}}" method="get" class="formAprobar">
                                                        @csrf
                                                        <button type="submit" style="color: blue">Aprovar</button>
                                                    </form>
                                                </div>
                                                <div>
                                                    <form action="{{url('canceled',$appoint->id)}}" method="get" class="formDenegar">
                                                        @csrf
                                                        @method('DELETE')
                                                        <button type="submit" style="color: red;">Denegar</button>
                                                    </form>
                                                </div>
                                                <div>
                                                  <a href="{{url('email',$appoint->id)}}">email</a>
                                              </div>
                                                
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
          <!-- partial:partials/_footer.html -->
          @include('admin.estilo.footer')
          <!-- partial -->
        </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
  @include('admin.estilo.scrip')
    <!-- End custom js for this page -->
  </body>
</html>