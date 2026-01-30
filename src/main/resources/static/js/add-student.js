// document.getElementById("studentForm").addEventListener("submit", function (e) {
//     e.preventDefault();
//
//     const student = {
//         studentCode: document.getElementById("studentCode").value,
//         fullName: document.getElementById("fullName").value,
//         email: document.getElementById("email").value,
//         phone: document.getElementById("phone").value,
//         gender: document.getElementById("gender").value,
//         dob: document.getElementById("dob").value,
//         password: document.getElementById("password").value,
//         status: "ACTIVE"
//     };
//
//     fetch("/api/students", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify(student)
//     })
//         .then(res => res.json())
//         .then(() => {
//             document.getElementById("msg").style.color = "green";
//             document.getElementById("msg").innerText = "Student saved successfully!";
//             document.getElementById("studentForm").reset();
//         })
//         .catch(() => {
//             document.getElementById("msg").style.color = "red";
//             document.getElementById("msg").innerText = "Error while saving";
//         });
// });
document.getElementById("studentForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const msg = document.getElementById("msg");
    const studentForm = document.getElementById("studentForm");

    const student = {
        studentCode: document.getElementById("studentCode").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        gender: document.getElementById("gender").value,
        dob: document.getElementById("dob").value,
        password: document.getElementById("password").value,
        status: "ACTIVE"
    };

    fetch("/api/students", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student)
    })
        .then(async res => {
            const data = await res.json();

            if (!res.ok) {
                throw data;   // validation errors
            }

            return data;
        })
        .then(() => {
            msg.style.color = "green";
            msg.innerText = "Student saved successfully!";
            studentForm.reset();
        })
        .catch(err => {
            msg.style.color = "red";

            if (typeof err === "object") {
                msg.innerText = Object.values(err).join(", ");
            } else {
                msg.innerText = "Something went wrong";
            }
        });
});

