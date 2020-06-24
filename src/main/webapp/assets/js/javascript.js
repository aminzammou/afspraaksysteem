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
        var fetchOptions = {
            method: 'POST',
            body: encData,
            headers : {
                'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
            }
        }
        let modT = document.getElementById("infoTekst");
        let modH = document.getElementById("modelHeader");



        fetch("/restservices/afspraken", fetchOptions)
            .then(response => response.json())
            .then(function (myJson) {
                if (myJson === true){
                    modT.textContent = "De afspraak is toe gevoegd!";
                    modH.textContent = "Succesvol";
                    $("#infoModal").modal('show');
                    // setTimeout(function () { window.location.reload(); }, 10)
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
})



function getKlante(event) {

    fetch("/restservices/klanten", {method: 'GET'})
        .then(response => response.json())
        .then(myJson => {
            const selc = document.getElementById("klant");
            console.log(myJson);
            myJson.forEach(klant=>{
                const opti = document.createElement("option");
                opti.text = (JSON.stringify(klant.naam)).replace(/"/g, "");
                opti.value = (JSON.stringify(klant.email)).replace(/"/g, "");
                selc.add(opti, null);
            })

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
$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

function getListsVanVandaag(event) {
    var fetchOptions = {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }

    fetch("restservices/afspraken/AfsprakenVandaag",fetchOptions)
        .then(response => response.json())
        .then(myJson => {
            tabelVanVandaag(myJson);
        })
        // .then(myJson => console.log(myJson))
        // .then(myJson => tabelVanVandaag(myJson))
        .catch(error => console.log(error));
}
function tabelVanVandaag(data) {
    let tabelBody = document.getElementById("afsprakenTabelVandaag");
    let tableRowTemplate = document.getElementById("tableRowTemplate");
    console.log(data)
    data.forEach(afspraak => {
        console.log(afspraak);
        let tableRow = tableRowTemplate.content.cloneNode(true);
        tableRow.querySelector(".meer-info").addEventListener("click", () => {
            /* Voeg tekst van modal toe */
            let naam = document.getElementById("klantNaam");
            let achternaam = document.getElementById("klantAchternaam");
            let straat = document.getElementById("straat");
            let huisnummer = document.getElementById("huisnummer");
            let stad = document.getElementById("stad");

            naam.textContent = afspraak['klant'].naam;
            achternaam.textContent = afspraak['klant'].achternaam;
            straat.textContent = afspraak['klant'].straat;
            huisnummer.textContent = afspraak['klant'].huisnummer;
            stad.textContent = afspraak['klant'].stad;
        });
        tableRow.querySelector(".klantNaamTable").textContent = afspraak['klant'].naam;
        tableRow.querySelector(".werknemerNaamTable").textContent = afspraak['werknemer'].naam;
        tableRow.querySelector(".datumTable").textContent = `${afspraak.datum.year} - ${afspraak.datum.monthValue} - ${afspraak.datum.dayOfMonth}`;
        tableRow.querySelector(".tijdTable").textContent = `${("00"+afspraak.tijd.hour).slice(-2)}:${("00"+afspraak.tijd.minute).slice(-2)}`;
        tableRow.querySelector(".beschrijvingTable").textContent = afspraak.beschrijving;
        tabelBody.appendChild(tableRow);
    });
}
var van = new Date();
var date = van.getFullYear()+'-'+(van.getMonth()+1)+'-'+van.getDate();
document.getElementById('datum-header').innerHTML = date;

// function login(event){
//     var formData = new FormData(document.querySelector("#loginform"));
//     var encData = new URLSearchParams(formData.entries());
//
//     fetch("/restservices/authentication", {method: 'POST', body: encData})
//         .then(function(response){
//             if (response.ok) return response.json();
//             else throw alert("WRONG username/password");
//         })
//         .then(myJson => {
//             window.sessionStorage.setItem("myJWT",myJson.JWT);
//             window.location.replace('index.html')
//             window.location.href = "hoofdpagina.html";
//
//         })
//         .catch(error => console.log(error));
//
// }
// document.querySelector("#login").addEventListener("click",login);

function logout(event) {
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "index.html";
}
document.querySelector("#loguit").addEventListener("click",logout);

let gebruiker = document.getElementById("gebruiker");
let myJson = window.sessionStorage.getItem("myJWT");

if (jwt_decode(myJson).role === "werknemer"){
    gebruiker.textContent = "Werknemer";
}else {
    gebruiker.textContent = "Klant";
};