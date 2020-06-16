// function getUser() {
//     var fetchOptions = {
//         method: 'GET',
//         headers: {
//             'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
//         }
//     }
//
//     fetch("restservices/afspraken/users", fetchOptions)
//         .then(response => response.json())
//         .then(myJson => console.log(myJson))
//         .catch(error => console.log(error));
// }
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};



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
                alert("WRONG username/password");
            })
            .then(myJson => {
                window.sessionStorage.setItem("myJWT",myJson.JWT);
                parseJwt("myJWT");
                // window.location.href = "hoofdpagina.html";

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