const formCheck = document.querySelector('.needs-validation');
let today = new Date();
let dd = today.getDate();
let mm = today.getMonth() + 1; //januari is 0!
const yyyy = today.getFullYear();
if(dd<10){
    dd='0'+dd
}
if(mm<10){
    mm='0'+mm
}
today = yyyy+'-'+mm+'-'+dd;
document.getElementById("datum").setAttribute("min", today);


document.querySelector("#submitAfspraak").addEventListener("click",function (event) {
    if (formCheck.checkValidity() === false){
        event.preventDefault();
        event.stopPropagation();
    }
    if (formCheck.checkValidity() === true){
        var formData = new FormData(document.querySelector("#postAfspraak"));
        var encData = new URLSearchParams(formData.entries());
        let modT = document.getElementById("infoTekst");
        let modH = document.getElementById("modelHeader");



        fetch("/restservices/afspraken", {method: 'POST', body: encData})
            .then(response => response.json())
            .then(function (myJson) {
                if (myJson === true){
                    modT.textContent = "De afspraak is toe gevoegd!";
                    modH.textContent = "Succesvol";
                    $("#infoModal").modal('show');
                    // alert("mooi man");
                }else{
                    modT.textContent = (JSON.stringify(myJson)).replace(/"/g, "").replace('{', "").replace('}', "");
                    modH.textContent = "Unsuccessful";
                    $("#infoModal").modal('show');
                    // alert(JSON.stringify(myJson));
                }
                // console.log(myJson)
            });
    }

    formCheck.classList.add('was-validated');

    // const newTaskInput = document.querySelector('.tijd_input');
    // const text = newTaskInput.value;
    // var hours = text.split(":")[0];
    // var minutes = text.split(":")[1];
    // const uren = parseInt(hours);
    // const minuten = parseInt(minutes);
    // if (uren < 9 || uren > 17){
    //     alert("Niet goed kies iets anders man")
    // }else{}



    // var formData = new FormData(document.querySelector("#postAfspraak"));
    // var encData = new URLSearchParams(formData.entries());
    //
    // fetch("/restservices/afspraken", {method: 'POST', body: encData})
    //     .then(response => response.json())
    //     .then(function (myJson) {
    //         console.log(myJson)
    //     });
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



