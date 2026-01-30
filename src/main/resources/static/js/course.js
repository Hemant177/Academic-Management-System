
function addCourse() {

    const courseCode = document.getElementById("code").value.trim();
    const courseName = document.getElementById("name").value.trim();
    const courseDescription = document.getElementById("description").value.trim();
    const locationId= document.getElementById("location").value.trim();
    const durationMonths = document.getElementById("duration").value;
    const courseLevel = document.getElementById("level").value;


    if (
        courseCode === "" ||
        courseName === "" ||
        courseDescription === "" ||
        location === "" ||
        durationMonths === "" ||
        courseLevel === ""
    ) {
        alert("Please fill all course details");
        return; // STOP API CALL
    }

    const data = {
        courseCode,
        courseName,
        courseDescription,
        location:{locationId: Number(locationId)},
        durationMonths: Number(durationMonths),
        courseLevel
    };

    fetch("http://localhost:8080/api/courses", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (!res.ok) throw new Error("Failed");
            return res.json();
        })
        .then(() => alert("Course saved successfully!"))
        .catch(console.error);
}

function deleteCourse(id) {
    if (!confirm("Are you sure?")) return;

    fetch(`http://localhost:8080/api/courses/${id}`, {
        method: "DELETE"
    })
        .then(() => {
            alert("Course Deleted");
            location.reload();
        });
}

/* ===== MODAL UPDATE LOGIC ===== */

let currentCourseId = null;

function openCourseModal(id, name, level, duration) {
    currentCourseId = id;

    document.getElementById("modalCourseName").value = name;
    document.getElementById("modalCourseLevel").value = level;
    document.getElementById("modalCourseDuration").value = duration;

    document.getElementById("modalBackdrop").classList.remove("hidden");
    document.getElementById("editModal").classList.remove("hidden");
}

function closeCourseModal() {
    document.getElementById("modalBackdrop").classList.add("hidden");
    document.getElementById("editModal").classList.add("hidden");
}

function saveCourseChanges() {
    fetch(`http://localhost:8080/api/courses/${currentCourseId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            courseName: document.getElementById("modalCourseName").value,
            courseLevel: document.getElementById("modalCourseLevel").value,
            durationMonths: document.getElementById("modalCourseDuration").value
        })
    })
        .then(() => {
            alert("Course Updated");
            location.reload();
        })
        .catch(console.error);
}

let assignCourseId = null;

function openAssignStaffModal(courseId) {
    assignCourseId = courseId;

    fetch("http://localhost:8080/api/staff")
        .then(res => res.json())
        .then(data => {
            document.getElementById("staffSelect").innerHTML =
                data.map(s =>
                    `<option value="${s.staffId}">
                        ${s.staffName}
                     </option>`
                ).join("");
        });

    document.getElementById("modalBackdrop").classList.remove("hidden");
    document.getElementById("assignStaffModal").classList.remove("hidden");
}

function closeAssignStaffModal() {
    document.getElementById("modalBackdrop").classList.add("hidden");
    document.getElementById("assignStaffModal").classList.add("hidden");
}



function assignStaff() {
    const staffId = document.getElementById("staffSelect").value;

    fetch(`http://localhost:8080/api/courses/${assignCourseId}/assign-staff/${staffId}`, {
        method: "PUT"
    })
    .then(async res => {

        if (!res.ok) {
            // Read backend error message
            const msg = await res.text();

            // Capacity exceeded message
            if (msg.toLowerCase().includes("maximum")) {
                alert(" Can't add staff: maximum course capacity exceeded");
            } else {
                alert("Failed to assign staff, max limit reached!!");
            }
            throw new Error(msg);
        }

        return res.json();
    })
    .then(() => {
        alert("Staff assigned successfully");
        location.reload();
    })
    .catch(err => console.error("Assign staff error:", err));
}

function loadLocations() {
    const select = document.getElementById("location");
    if (!select) return;

    fetch("http://localhost:8080/api/locations")
        .then(res => res.json())
        .then(data => {
            select.innerHTML =
                `<option value="">Select Location</option>` +
                data.map(l =>
                    `<option value="${l.locationId}">
                        ${l.locationName} - ${l.city}
                    </option>`
                ).join("");
        });
}

/* ===== LOAD COURSES ===== */

fetch("/api/courses")
    .then(res => res.json())
    .then(data => {

        let rows = "";

        data.forEach(c => {
            rows += `
                <tr>
                    <td>${c.courseId}</td>
                    <td>${c.courseCode}</td>
                    <td>${c.courseName}</td>
                    <td>${c.courseDescription}</td>
                    <td>${c.location ? `${c.location.locationName} - ${c.location.city}` : 'N/A'}</td>
                    <td>${c.courseLevel}</td>

                    <td>
                        ${
                            c.staff
                                ? `<span class="staff-badge">${c.staff.staffName}</span>`
                                : `<button class="btn assign"
                                    onclick="openAssignStaffModal(${c.courseId})">
                                    Assign Staff
                                  </button>`
                        }
                    </td>

                    <td>${c.durationMonths}</td>

                    <td>
                        <button class="btn edit"
                            onclick="openCourseModal(
                                ${c.courseId},
                                '${c.courseName}',
                                '${c.courseLevel}',
                                ${c.durationMonths}
                            )">
                            <i class="fa fa-pen"></i>
                        </button>

                        <button class="btn delete"
                            onclick="deleteCourse(${c.courseId})">
                            <i class="fa fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });

        document.getElementById("courseTable").innerHTML = rows;
    })
    .catch(console.error);


document.addEventListener("DOMContentLoaded", loadLocations);



