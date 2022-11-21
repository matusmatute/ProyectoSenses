
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
                    <section class="container mx-auto p-6 font-mono">
                        <div class="w-full mb-8 overflow-hidden rounded-lg shadow-lg">
                          <div class="w-full overflow-x-auto">
                            <table class="w-full">
                              <thead>
                                <tr class="text-md font-semibold tracking-wide text-left text-gray-900 bg-gray-100 uppercase border-b border-gray-600">
                                  <th class="px-4 py-3">Dentista</th>
                                  <th class="px-4 py-3">Telefono</th>
                                  <th class="px-4 py-3">Especialidad</th>
                                  <th class="px-4 py-3">Acciones</th>
                                </tr>
                              </thead>
                              <tbody class="bg-white">
                                @foreach ($data as $showappoint)     
                                <tr class="text-gray-700">
                                  <td class="px-4 py-3 border">
                                    <div class="flex items-center text-sm">
                                      <div class="relative w-8 h-8 mr-3 rounded-full md:block">
                                        <img class="object-cover w-full h-full rounded-full" src="dentistimage/{{$showappoint->image}}" alt="" loading="lazy" />
                                        <div class="absolute inset-0 rounded-full shadow-inner" aria-hidden="true"></div>
                                      </div>
                                      <div>
                                        <p class="font-semibold text-black">{{$showappoint->name}} {{$showappoint->last_name}}</p>
                                      </div>
                                    </div>
                                  </td>
                                  <td class="px-4 py-3 text-ms font-semibold border">{{$showappoint->phone}}</td>
                                  <td class="px-4 py-3 text-ms font-semibold border">{{$showappoint->speciality}}</td>
                                  <td class="px-6 py-4 text-center"> 
                                    <div>
                                        <a href="{{url('updatedentist',$showappoint->id)}}">Editar</a>
                                    </div>
                                    <div>
                                        <form action="{{url('deletedentist',$showappoint->id)}}" method="Post" class="formEliminar">
                                            @csrf
                                            @method('DELETE')
                                            <button type="submit" style="color: red;">Eliminar</button>
                                        </form>
                                    </div>
                                </td>
                                </tr>
                              </tbody>
                              @endforeach
                            </table>
                          </div>
                        </div>
                      </section>
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