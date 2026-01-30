function loadStudents() {
    fetch("/api/students")
        .then(r => r.json())
        .then(data => {
            const table = document.getElementById("studentTable");
            table.innerHTML = "";

            data.forEach(s => {
                table.innerHTML += `
                <tr>
                    <td>${s.studentId}</td>
                    <td>${s.studentCode}</td>
                    <td>${s.fullName}</td>
                    <td>${s.email}</td>
                    <td>${s.gender}</td>
                    <td>${s.status}</td>

                    <td class="actions">
                        <button class="edit-btn" onclick="editStudent(${s.studentId})">
                            <i class="fas fa-pencil-alt"></i>
                        </button>

                        <button class="delete-btn" onclick="deleteStudent(${s.studentId})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>`;
            });
        });
}

function editStudent(id) {
    window.location.href = "edit-student.html?id=" + id;
}

function deleteStudent(id) {
    if (!confirm("Delete student?")) return;

    fetch(`/api/students/${id}`, { method: "DELETE" })
        .then(() => {
            loadStudents();
            if (typeof updateCounts === "function") updateCounts();
        });
}

loadStudents();