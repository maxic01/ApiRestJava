// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
  actEmail();
});

function actEmail() {
  document.getElementById('txt-email').outerHTML = localStorage.email;
}

async function cargarUsuarios() {

  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();



  let listadoHtml = '';
  for(let usuario of usuarios) {
    let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let telefonoVar = usuario.telefono == null ? '-':usuario.telefono;

    let usuarioHtml  = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'
    +telefonoVar+'</td><td>' + botonEliminar + '</td></tr>';

    listadoHtml += usuarioHtml;
  }
  console.log(usuarios);



  document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

function getHeaders() {
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  };
}

async function eliminarUsuario(id) {

  if(!confirm('Do you want to delete this user?')) {
    return;
  }

  const request = await fetch('api/usuarios/' + id, {
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload();
}
