function getLists(event) {
    var fetchOptions = {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }

    fetch("restservices/afspraken/afsprakenKlant", fetchOptions)
        .then(response => response.json())
        // .then(myJson => document.getElementById("demo").textContent = JSON.stringify(myJson))
        // .then(myJson => console.log(myJson))
        .then(myJson => tabel(myJson))
        .catch(error => console.log(error));
}
// <td>${data[i].naam}</td>
// <td>${data[i].naam}</td>
// <td>${JSON.stringify(data[i].datum.month)}</td>
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
        tableRow.querySelector(".tijdTable").textContent = `${JSON.stringify(afspraak.tijd.hour)}:${JSON.stringify(afspraak.tijd.minute)}`;
        tableRow.querySelector(".beschrijvingTable").textContent = afspraak.beschrijving;
        tabelBody.appendChild(tableRow);
    });
}
function logout(event) {
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "index.html";
}
document.querySelector("#loguit").addEventListener("click",logout);