window.addEventListener("load", function(){

    (async () => {
        const response = await 
            fetch('http://localhost:8080/api/v1/index')
        const data = await response.json();

        let thead = document.createElement("thead");
        let row = document.createElement("tr");
        document.getElementById("tabular")
            .appendChild(thead)
            .appendChild(row);
        let header;

        for (const [key, value] of Object.entries(data[0])) {
            header = document.createElement("th");
            header.innerHTML = key;
            row.appendChild(header);
        }

        tbody = document.createElement("tbody");
        let cell;

        data.forEach(element => {
            row = document.createElement("tr");
            document.getElementById("tabular")
                .appendChild(tbody)
                .appendChild(row);

            for (const [key, value] of Object.entries(element)) {
                cell = document.createElement("td");
                cell.innerHTML = value;
                row.appendChild(cell);
            }
        });
    })();

});