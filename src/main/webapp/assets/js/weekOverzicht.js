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

var clicks = 0;
document.querySelector("#opTellen").addEventListener("click",function (event) {
    clicks += 1;
    cleanTable();
    getLists(event)
    document.getElementById("clicks").innerHTML = clicks;
})
document.querySelector("#afTellen").addEventListener("click",function (event) {
    clicks -= 1;
    cleanTable();
    getLists(event)
    document.getElementById("clicks").innerHTML = clicks;
})
function getLists(event) {
    var id = clicks;
    var fetchOptions = {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }
    fetch(`/restservices/afspraken/perWeek/${id}`, fetchOptions)
        .then(response => response.json())
        .then(myJson => tabel(myJson))
        .catch(error => console.log(error));
}
function cleanTable() {
    let tabelBody = document.getElementById("afsprakenTabel");
    tabelBody.innerHTML = " ";
}

function tabel(data) {
    let tabelBody = document.getElementById("afsprakenTabel");
    let tableRowTemplate = document.getElementById("tableRowTemplate");

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
        // tableRow.querySelector(".beschrijvingTable").textContent = afspraak.beschrijving;
        tableRow.querySelector("#delAfspraak").addEventListener("click", () => {
            // let formData = new FormData();
            var maand = afspraak.datum.monthValue.toString().length > 1 ? afspraak.datum.monthValue.toString() : "0" + afspraak.datum.monthValue.toString();
            var id = afspraak['klant'].email;
            var datum = `${afspraak.datum.year}-${maand}-${afspraak.datum.dayOfMonth}`;
            var tijd = `${("00"+afspraak.tijd.hour).slice(-2)}:${("00"+afspraak.tijd.minute).slice(-2)}:00`;
            var fetchOptions = {
                method: 'DELETE',
                headers : {
                    'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
                }
            }

            fetch(`/restservices/afspraken/${id}/${datum}/${tijd}`, fetchOptions)
                .then(function (response) {
                    if(response.ok) alert("afspraak deleted")
                    else if (response.status == 404) alert("afspraak not found")
                    else alert("Somting else happend")
                });
        });
        tableRow.querySelector("#updateAfspraak").addEventListener("click", () => {
            $("#updateFormModal").modal('show');
            // let klant = document.getElementById("klant");
            // let werknemer = document.getElementById("werknemer");
            // let datum = document.getElementById("datum");
            // let tijd = document.getElementById("tijd");
            // let beschrijving = document.getElementById("beschrijving");
            //
            // klant.textContent = afspraak['klant'].naam;
            // werknemer.textContent = afspraak['werknemer'].naam;
            // datum.textContent = `${afspraak.datum.year} - ${afspraak.datum.monthValue} - ${afspraak.datum.dayOfMonth}`;
            // tijd.textContent = `${("00"+afspraak.tijd.hour).slice(-2)}:${("00"+afspraak.tijd.minute).slice(-2)}`;
            // beschrijving.textContent = afspraak.beschrijving;

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
                    var formData = new FormData(document.querySelector("#putAfspraak"));
                    var encData = new URLSearchParams(formData.entries());
                    var maand = afspraak.datum.monthValue.toString().length > 1 ? afspraak.datum.monthValue.toString() : "0" + afspraak.datum.monthValue.toString();
                    var id = afspraak['klant'].email;
                    var datum = `${afspraak.datum.year}-${maand}-${afspraak.datum.dayOfMonth}`;
                    var tijd = `${("00"+afspraak.tijd.hour).slice(-2)}:${("00"+afspraak.tijd.minute).slice(-2)}:00`;
                    var fetchOptions = {
                        method: 'PUT',
                        body: encData,
                        headers : {
                            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
                        }
                    }
                    let modT = document.getElementById("infoTekst");
                    let modH = document.getElementById("modelHeader");



                    fetch(`/restservices/afspraken/${id}/${datum}/${tijd}`, fetchOptions)
                        .then(response => response.json())
                        .then(function (myJson) {
                            if (myJson === true){
                                modT.textContent = "De afspraak is aangepast!";
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
            });
        })
        // tableRow.appendChild(createDelete(afspraak['klant'].email,afspraak.datum,afspraak.tijd));

        tabelBody.appendChild(tableRow);
    });
}

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



// einde log uit
// begin update afspraak

// document.querySelector("#updateAfspraak").addEventListener("click",)
//
// const formCheck = document.querySelector('.needs-validation');
// let today = new Date();
// let dd = today.getDate();
// let mm = today.getMonth() + 1; //januari is 0!
// const yyyy = today.getFullYear();
// if(dd<10){
//     dd='0'+dd
// }
// if(mm<10){
//     mm='0'+mm
// }
// today = yyyy+'-'+mm+'-'+dd;
// document.getElementById("datum").setAttribute("min", today);
//
// document.querySelector("#submitAfspraak").addEventListener("click",function (event) {
//     if (formCheck.checkValidity() === false){
//         event.preventDefault();
//         event.stopPropagation();
//     }
//     if (formCheck.checkValidity() === true){
//         var formData = new FormData(document.querySelector("#putAfspraak"));
//         var encData = new URLSearchParams(formData.entries());
//         var fetchOptions = {
//             method: 'PUT',
//             body: encData,
//             headers : {
//                 'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
//             }
//         }
//         let modT = document.getElementById("infoTekst");
//         let modH = document.getElementById("modelHeader");
//
//
//
//         fetch("/restservices/afspraken", fetchOptions)
//             .then(response => response.json())
//             .then(function (myJson) {
//                 if (myJson === true){
//                     modT.textContent = "De afspraak is toe gevoegd!";
//                     modH.textContent = "Succesvol";
//                     $("#infoModal").modal('show');
//                     // setTimeout(function () { window.location.reload(); }, 10)
//                     // alert("mooi man");
//                 }else{
//                     modT.textContent = (JSON.stringify(myJson)).replace(/"/g, "").replace('{', "").replace('}', "");
//                     modH.textContent = "Unsuccessful";
//                     $("#infoModal").modal('show');
//                     // alert(JSON.stringify(myJson));
//                 }
//                 // console.log(myJson)
//             });
//     }
//
//     formCheck.classList.add('was-validated');
// });
