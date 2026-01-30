function updateCounts() {

    fetch("/api/students")
        .then(r => r.json())
        .then(data => {
            document.getElementById("studentCount").innerText = data.length;
        });

    fetch("/api/enrollments")
        .then(r => r.json())
        .then(data => {
            document.getElementById("enrollCount").innerText = data.length;
        });

    fetch("/api/locations")
        .then(r => r.json())
        .then(data => {
            document.getElementById("locationCount").innerText = data.length;
        });

    fetch("http://localhost:8080/api/intakes/count")
        .then(res => res.json())
        .then(count => {
            document.getElementById("intakeCount").innerText = count;
        });
}

document.addEventListener("DOMContentLoaded", updateCounts);
