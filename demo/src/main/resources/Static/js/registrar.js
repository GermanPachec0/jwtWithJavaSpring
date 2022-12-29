// Call the dataTables jQuery plugin
$(document).ready(function() {
  //ON READY

});

async function registrarUsuarios(){
      let datos ={};
      datos.nombre = document.getElementById('txtNombre').value
      datos.apellido = document.getElementById('txtApellido').value
      datos.email = document.getElementById('txtEmail').value
      datos.password = document.getElementById('txtPassword').value

      let repetirPassword =  document.getElementById('txtRepetirPassword').value

       if(repetirPassword != datos.password){
            alert('Las contraseñas son diferentes')
            return;
       }

      const request = await fetch('usuarios/register', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
            body: JSON.stringify(datos)
       });
        alert("La cuenta fue creadaa con exito");
        window.location.href = 'login.html';


       const usuarios = await request.json();

}

