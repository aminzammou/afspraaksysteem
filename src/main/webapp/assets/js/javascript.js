// (function() {
//     'use strict';
//     window.addEventListener('load', function() {
//         // Fetch all the forms we want to apply custom Bootstrap validation styles to
//         var forms = document.getElementsByClassName('needs-validation');
//         // Loop over them and prevent submission
//         var validation = Array.prototype.filter.call(forms, function(form) {
//             form.addEventListener('submit', function(event) {
//                 if (form.checkValidity() === false) {
//                     event.preventDefault();
//                     event.stopPropagation();
//                 }
//                 form.classList.add('was-validated');
//             }, false);
//         });
//     }, false);
// })();

document.querySelector("#submitAfspraak").addEventListener("click",function () {
    var formData = new FormData(document.querySelector("#postAfspraak"));
    var encData = new URLSearchParams(formData.entries());

    fetch("/restservices/afspraken", {method: 'POST', body: encData})
        .then(response => response.json())
        .then(function (myJson) {
            console.log(myJson)
        });
})



function getKlante(event) {

    fetch("/restservices/klanten", {method: 'GET'})
        .then(response => response.json())
        .then(myJson => {
            const selc = document.getElementById("klant");
            const opti = document.createElement("option");
            opti.text = (JSON.stringify(myJson[0].naam)).replace(/"/g, "");
            opti.value = (JSON.stringify(myJson[0].email)).replace(/"/g, "");
            selc.add(opti, null);
        } )
        .catch(error => console.log(error));

}
function getWerknemer(event) {

    fetch("/restservices/werknemer", {method: 'GET'})
        .then(response => response.json())
        .then(myJson => {
            const selc = document.getElementById("werknemer");
            const opti = document.createElement("option");
            opti.text = (JSON.stringify(myJson[0].naam)).replace(/"/g, "");
            opti.value = (JSON.stringify(myJson[0].email)).replace(/"/g, "");
            selc.add(opti, null);
        } )
        .catch(error => console.log(error));

}
// document.getElementById("tester").textContent = JSON.stringify(myJson[0].naam)
// .then(myJson =>{
//     const selc = document.getElementById("klant");
//     const opti = document.createElement("option");
//     opti.textContent = JSON.stringify(myJson[0].naam);
//     selc.appendChild(opi);
//
// })



