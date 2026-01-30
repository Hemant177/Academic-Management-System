function isValidEmail(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}


function addStaff() {
    const email = document.getElementById("email").value.trim();

    if (!isValidEmail(email)) {
        alert(
            "Invalid Email Credential!\n\n"
        );
        return;
    }

    fetch("http://localhost:8080/api/staff", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            staffCode: document.getElementById("code").value,
            staffName: document.getElementById("name").value,
            email: email,
            role: "STAFF",
            maxCoursesAllowed: document.getElementById("limit").value
        })
    })
        .then(res => res.json())
        .then(() => alert("Staff Added Successfully"));
}

function deleteStaff(id) {
    if (!confirm("Are you sure you want to delete this staff member?")) return;

    fetch(`http://localhost:8080/api/staff/${id}`, {
        method: "DELETE"
    })
        .then(() => {
            alert("Staff deleted");
            location.reload();
        })
        .catch(err => console.error(err));
}

let currentStaffId = null;

function openStaffModal(id, name, email, limit) {
    currentStaffId = id;

    document.getElementById("modalTitle").innerText = "Edit Staff";
    document.getElementById("modalName").value = name;
    document.getElementById("modalEmail").value = email;
    document.getElementById("modalLimit").value = limit;

    document.getElementById("modalBackdrop").classList.remove("hidden");
    document.getElementById("editModal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("modalBackdrop").classList.add("hidden");
    document.getElementById("editModal").classList.add("hidden");
}

function saveChanges() {
    fetch(`http://localhost:8080/api/staff/${currentStaffId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            staffName: document.getElementById("modalName").value,
            email: document.getElementById("modalEmail").value,
            maxCoursesAllowed: document.getElementById("modalLimit").value
        })
    })
        .then(() => {
            alert("Staff updated");
            location.reload();
        })
        .catch(err => console.error(err));
}

/* LOAD STAFF DATA */
fetch("http://localhost:8080/api/staff")
    .then(res => res.json())
    .then(data => {
        let rows = "";

        data.forEach(s => {
            rows += `
                <tr>
                    <td>${s.staffId}</td>
                    <td>${s.staffCode}</td>
                    <td>${s.staffName}</td>
                    <td>${s.email}</td>
                    <td>${s.maxCoursesAllowed}</td>
                    <td>
                        <button class="btn edit"
                            onclick="openStaffModal(${s.staffId}, '${s.staffName}', '${s.email}', ${s.maxCoursesAllowed})">
                            <i class="fa fa-pen"></i>
                        </button>
                        <button class="btn delete"
                            onclick="deleteStaff(${s.staffId})">
                            <i class="fa fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });

        document.getElementById("staffTable").innerHTML = rows;
    })
    .catch(err => console.error(err));
