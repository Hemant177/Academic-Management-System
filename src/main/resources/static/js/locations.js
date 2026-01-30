document.addEventListener("DOMContentLoaded", () => {
    loadLocations();
    document.getElementById("locationForm").addEventListener("submit", saveLocation);
});


function saveLocation(e) {
    e.preventDefault();

    const data = {
        locationName: document.getElementById("locationName").value,
        address: document.getElementById("address").value,
        city: document.getElementById("city").value,
        state: document.getElementById("state").value
    };

    fetch("/api/locations", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(r => {
            if (!r.ok) throw new Error();
            return r.text();
        })
        .then(() => {
            document.getElementById("msg").innerText = "Location saved successfully!";
            document.getElementById("msg").style.color = "green";
            loadLocations();
            updateCounts();
        })

}



function loadLocations() {
    fetch("/api/locations")
        .then(r => r.json())
        .then(data => {
            const table = document.getElementById("locationTable");
            table.innerHTML = "";

            data.forEach(loc => {
                table.innerHTML += `
                <tr>
                    <td>${loc.locationId}</td>
                    <td>${loc.locationName}</td>
                    <td>${loc.address}</td>
                    <td>${loc.city}</td>
                    <td>${loc.state}</td>
                    <td class="actions">
                        <button class="delete-btn" onclick="deleteLocation(${loc.locationId})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>`;
            });
        });
}



function deleteLocation(id) {
    if (!confirm("Delete this location?")) return;

    fetch(`/api/locations/${id}`, { method: "DELETE" })
        .then(r => {
            if (r.status === 200 || r.status === 204) {
                // SUCCESS — NO ERROR NOW
                loadLocations();
                updateCounts();
                return;
            }
            throw new Error();
        })

}
