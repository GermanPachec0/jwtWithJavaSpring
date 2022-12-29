// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
});

async function cargarUsuarios(){

      const request = await fetch('usuarios', {
        method: 'GET',
        headers: getHeaders()
      });

       const usuarios = await request.json();
       let listadoHtml = '';
        for(let usuario of usuarios){
        let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>'
        let telefono= usuario.telefono == null ? '-' : usuario.telefono;

        let usuariohtml = '<tr>'+usuario.id+'<td></td><td>'+ usuario.nombre +' ' +usuario.apellido +'</td><td>'
        +usuario.email+'</td><td>'+telefono
        +'</td><td>'+botonEliminar+'</td></tr>';
        listadoHtml += usuariohtml;
        }
      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

function getHeaders(){
    return {
     'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    };
}

async function eliminarUsuario(id){

    if(confirm('¿Desdea elimnar este usuario?'))
    {
     const request = await fetch('usuarios/'+id, {
             method: 'DELETE',
             headers: getHeaders()
           });

           const usuario = await request.json();
           console.log(usuario);
           location.reload();
    }

    return ;


}
