<%-- 
    Document   : index
    Created on : Apr 25, 2018, 4:28:02 PM
    Author     : vergo_000
--%>


<%@page import="com.sv.udb.modelo.Prestamos"%>
<%@page import="com.sv.udb.controlador.Usuarios_Ctrl"%>
<%@page import="com.sv.udb.modelo.Usuarios"%>
<%@page import="com.sv.udb.controlador.Libros_Ctrl"%>
<%@page import="com.sv.udb.modelo.Libros"%>
<%@page import="com.sv.udb.controlador.Prestamos_Ctrl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Corto 03</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
          <div class="container">
            <div class="alert alert-success">
                <strong>Indicaciones:</strong> Corto 03
            </div>
            <div class="row">
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Agregar Prestamo</div>
                        <div class="panel-body">
                            <div class="alert alert-success">
                                ${mensAler}
                            </div>
                            <form method="POST" action="PrestamosSrv" name="Demo">
                             
                                 <div class="form-group">
                                  <label for="selLibros">Libros:</label>
                                  <select class="form-control" id="selLibros" name="selLibros">
                                    <%
                                        for(Libros temp1 : new Libros_Ctrl().consTodoNoPrestado())
                                        {
                                    %>

                                      <option value="<%=temp1.getCodi_libr()%>"><%= temp1%></option>
                                    <%
                                        }
                                    %>

                                  </select>
                                </div>
                                <div class="form-group">
                                  <label for="selUsuarios">Usuarios:</label>
                                  <select class="form-control" id="selUsuarios" name="selUsuarios">
                                    <%
                                        for(Usuarios temp : new Usuarios_Ctrl().consTodo())
                                        {
                                    %>

                                      <option value="<%=temp.getCodi_usua()%>"><%= temp%></option>
                                    <%
                                        }
                                    %>

                                  </select>
                                </div>
                                    <div class="form-group">
                                            <label for="cant">Fecha Prestamo:</label>
                                            <input type="date" class="form-control" name="fechaP" id="fechaP" />
                                        </div>
                                      
                                <input type="submit" class="btn btn-default" name="btonPrestamo" value="Agregar"/>
                                <input type="submit" class="btn btn-primary"  name="btonPrestamo" value="Cancelar"/>

                            </form>
                        </div>
                    </div>
           
                    <div class="panel panel-primary">
                        <div class="panel-heading">Modificar Prestamo</div>
                        <div class="panel-body">
                            <div class="alert alert-success">
                                ${mensAlerta}
                            </div>
                            <form method="POST" action="PrestamosSrv" name="Demo">
                                <input type="hidden" name="codigoP" id="codigoP" value="${codigoP}"/>
                                 <div class="form-group">
                                  <label for="selLibrosPrestados">Libros:</label>
                                  <select class="form-control" id="selLibrosPrestados" name="selLibrosPrestados">
                                    <%
                                        for(Libros temp12 : new Libros_Ctrl().consTodoPrestado())
                                        {
                                    %>

                                      <option value="<%=temp12.getCodi_libr()%>"><%= temp12%></option>
                                    <%
                                        }
                                    %>

                                  </select>
                                </div>
                                <div class="form-group">
                                  <label for="selUsuarios">Usuarios:</label>
                                  <select class="form-control" id="selUsuariosPrestados" name="selUsuario">
                                    <%
                                        for(Usuarios temp2 : new Usuarios_Ctrl().consTodo())
                                        {
                                    %>

                                      <option value="<%=temp2.getCodi_usua()%>"><%= temp2%></option>
                                    <%
                                        }
                                    %>

                                  </select>
                                </div>
                                  
                                        <div class="form-group">
                                            <label for="fecha">Fecha Devolucion:</label>
                                            <input type="date" class="form-control" name="fechaD" id="fechaD" value="${fechaD}"/>
                                        </div>
                                <input type="submit" class="btn btn-default" name="btonPrestamo" value="Modificar"/>
                                <input type="submit" class="btn btn-primary"  name="btonPrestamo" value="Cancelar"/>

                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="PrestamosSrv" name="Tabl">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Cons</th>
                                        <th>Pieza</th>
                                        <th>Proveedor</th>
                                        <th>Fecha Prestamo</th>
                                        <th>Fecha Devolucion</th>
                                    </tr>
                                    <%
                                        for(Prestamos temp5 : new Prestamos_Ctrl().consTodo())
                                        {
                                    %>
                                        <tr>
                                            <td><input type="radio" name="codiBRadi" value="<%= temp5.getCodigo()%>"/></td>
                                            <td><%= temp5.getCodigoL().getNomb_libr()%></td>
                                            <td><%= temp5.getCodigoU().getNomb_usua()%></td>
                                            <td><%= temp5.getFechaP()%></td>
                                            <td><%= temp5.getFechaD()%></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                </table>
                                  <input type="submit" class="btn btn-success" name="btonPrestamo" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
                 <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Reportes</div>
                        <div class="panel-body">
                            <form method="POST" action="ReporteSrv" name="Reportes">
                                
                                  <input type="submit" class="btn btn-success" name="btonReporte" value="Libros"/>
                                  <input type="submit" class="btn btn-success" name="btonReporte" value="Prestamos"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
