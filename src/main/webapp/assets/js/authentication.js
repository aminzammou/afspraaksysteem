const formCheck = document.querySelector('.needs-validation');
function login(event){
    if (formCheck.checkValidity() === false){
        event.preventDefault();
        event.stopPropagation();
    }
    if (formCheck.checkValidity() === true){
        var formData = new FormData(document.querySelector("#loginform"));
        var encData = new URLSearchParams(formData.entries());

        fetch("/restservices/authentication", {method: 'POST', body: encData})
            .then(function(response){
                if (response.ok) return response.json();
                alert("VERKEERDE Email/Wachtwoord");
            })
            .then(myJson => {
                window.sessionStorage.setItem("myJWT",myJson.JWT);
                if (jwt_decode(myJson.JWT).role === "werknemer"){
                    window.location.href = "hoofdpagina.html";
                }else {
                    window.location.href = "klantpagina.html";
                };

            })
            .catch(error => console.log(error));
    }
    formCheck.classList.add('was-validated');

}
document.getElementById("login").addEventListener("click",login);

// function logout(event) {
//     window.sessionStorage.removeItem("myJWT");
//
// }
// document.querySelector("#loguit").addEventListener("click",logout);