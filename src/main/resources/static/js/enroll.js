const studentId = document.getElementById("studentId");
const courseId = document.getElementById("courseId");
const intakeId = document.getElementById("intakeId");
const locationId = document.getElementById("locationId");

// Load Students
fetch("/api/students")
    .then(r => r.json())
    .then(data => {
        studentId.innerHTML = `<option value="">Select Student</option>`;
        data.forEach(s => {
            studentId.innerHTML += `<option value="${s.studentId}">${s.fullName}</option>`;
        });
    });

// Load Courses
fetch("/api/courses")
    .then(r => r.json())
    .then(data => {
        courseId.innerHTML = `<option value="">Select Course</option>`;
        data.forEach(c => {
            courseId.innerHTML += `<option value="${c.courseId}">${c.courseName}</option>`;
        });
    });

// Load Intake based on Course change
courseId.addEventListener("change", () => {
    fetch(`/api/intakes/course/${courseId.value}`)
        .then(r => r.json())
        .then(data => {
            intakeId.innerHTML = `<option value="">Select Intake</option>`;
            data.forEach(i => {
                intakeId.innerHTML += `<option value="${i.intakeId}">${i.intakeName}</option>`;
            });
        });
});

// Load Locations
fetch("/api/locations")
    .then(r => r.json())
    .then(data => {
        locationId.innerHTML = `<option value="">Select Location</option>`;
        data.forEach(l => {
            locationId.innerHTML += `<option value="${l.locationId}">${l.locationName}</option>`;
        });
    });

// ✅ Submit Enrollment (UPDATED PART)
document.getElementById("enrollForm").addEventListener("submit", e => {
    e.preventDefault();

    const url = `/api/enrollments?studentId=${studentId.value}&courseId=${courseId.value}&intakeId=${intakeId.value}`;

    fetch(url, { method: "POST" })
        .then(response => {
            if (!response.ok) {
                // 🔴 read backend error message
                return response.text().then(msg => {
                    throw new Error(msg);
                });
            }
            return response.json();
        })
        .then(() => {
            alert("Enrollment successful!");
            window.location.href = "view-enrollments.html";
        })
        .catch(err => {
            // 🔥 Shows:
            // "Student is already present in this intake"
            // OR
            // "Seats are filled for this intake"
            alert(err.message);
        });
});
