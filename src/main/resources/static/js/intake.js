// ===============================================
// LOAD ALL COURSES INTO DROPDOWN
// ===============================================
window.onload = function () {
    fetch("/api/courses")
        .then(res => {
            if (!res.ok) throw new Error("Failed to fetch courses");
            return res.json();
        })
        .then(data => {
            const dropdown = document.getElementById("courseId");

            dropdown.innerHTML = "<option value=''>Select Course</option>";

            data.forEach(course => {

                const option = document.createElement("option");
                option.value = course.courseId;

                // ⬇⬇ SHOW COURSE + LOCATION NAME HERE ⬇⬇
                option.textContent =
                    `${course.courseCode} - ${course.courseName} - ${course.location.locationName}`;

                dropdown.appendChild(option);
            });
        })
        .catch(() => {
            alert("Unable to load courses. Please check server.");
        });
};




// ===============================================
// ADD INTAKE FUNCTION
// ===============================================
function addIntake() {

    const courseId = document.getElementById("courseId").value.trim();
    const intakeName = document.getElementById("name").value.trim();
    const startDate = document.getElementById("start").value.trim();
    const endDate = document.getElementById("end").value.trim();
    const totalSeats = document.getElementById("seats").value.trim();

    // ------------------- VALIDATION -------------------

    if (!courseId || !intakeName || !startDate || !endDate || !totalSeats) {
        alert("Please fill all fields before submitting!");
        return;
    }

    if (Number(totalSeats) <= 0) {
        alert("Total seats must be a positive number.");
        return;
    }

    if (new Date(startDate) > new Date(endDate)) {
        alert("Start date cannot be after end date.");
        return;
    }

    // ------------------- REQUEST BODY -------------------

    const intakeData = {
        courseId: courseId,
        intakeName: intakeName,
        startDate: startDate,
        endDate: endDate,
        totalSeats: totalSeats
    };

    // ------------------- API CALL -------------------

    fetch("/api/intakes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(intakeData)
    })
        .then(res => {

            // If backend sends error → show popup with backend message
            if (!res.ok) {
                return res.text().then(msg => {
                    throw new Error(msg);
                });
            }

            return res.text(); // backend returns plain string
        })
        .then(() => {
            alert("Intake added successfully!");

            // Clear fields
            document.getElementById("courseId").value = "";
            document.getElementById("name").value = "";
            document.getElementById("start").value = "";
            document.getElementById("end").value = "";
            document.getElementById("seats").value = "";
        })
        .catch(err => {
            // POPUP WITH BACKEND ERROR MESSAGE
            alert(err.message);
        });
}