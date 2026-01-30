document.addEventListener("DOMContentLoaded", loadIntakes);

function loadIntakes() {
    fetch("/api/intakes")
        .then(r => r.json())
        .then(data => {
            const table = document.getElementById("intakeTable");
            table.innerHTML = "";

            data.forEach(i => {
                table.innerHTML += `
                    <tr>
                        <td>${i.intakeId}</td>
                        <td>${i.courseId}</td>
                        <td>${i.intakeName}</td>
                        <td>${i.startDate}</td>
                        <td>${i.endDate}</td>
                        <td>${i.totalSeats}</td>
                        <td>${i.availableSeats}</td>
                        <td>${i.status}</td>
                    </tr>
                `;
            });
        });
}
