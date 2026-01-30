const params = new URLSearchParams(window.location.search);
const studentId = params.get("id");

document.addEventListener("DOMContentLoaded", () => {
    fetch(`/api/students/${studentId}`)
        .then(res => res.json())
        .then(s => {
            document.getElementById("studentCode").value = s.studentCode;
            document.getElementById("fullName").value = s.fullName;
            document.getElementById("email").value = s.email;
            document.getElementById("phone").value = s.phone;
            document.getElementById("gender").value = s.gender;
            document.getElementById("dob").value = s.dob;
            document.getElementById("status").value = s.status;
        });
});

document.getElementById("editStudentForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const student = {
        studentCode: document.getElementById("studentCode").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        gender: document.getElementById("gender").value,
        dob: document.getElementById("dob").value,
        password: document.getElementById("password").value,
        status: document.getElementById("status").value
    };

    fetch(`/api/students/${studentId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student)
    })
        .then(res => {
            if (res.ok) {
                document.getElementById("msg").innerText = "Updated Successfully!";
                setTimeout(() => window.location = "view-students.html", 1000);
            } else {
                document.getElementById("msg").innerText = "Error updating!";
            }
        });
});
