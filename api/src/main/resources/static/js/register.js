$(document).ready(function() {

});

async function registrarUsuario() {

    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirPassword = datos.password = document.getElementById('txtRepeatPassword').value;

    if(repetirPassword != datos.password) {
        alert('Password is incorrect')
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("Account created successfully");
    window.location.href = 'login.html'
}


