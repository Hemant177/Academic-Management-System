fetch("/api/enrollments")
    .then(r => r.json())
    .then(data => {
        const table = document.getElementById("enrollTable");
        table.innerHTML = "";

        data.forEach(e => {
            table.innerHTML += `
                <tr>
                    <td>${e.enrollmentId}</td>

                    <td>${e.student.studentId}</td>
                    <td>${e.student.fullName}</td>

                    <td>${e.course.courseId}</td>
                    <td>${e.course.courseName}</td>

                    <td>${e.intake.intakeName}</td>
               
                    <td>${e.enrollmentDate}</td>
                </tr>
            `;
        });
    })
    .catch(err => console.error("Error loading enrollments:", err));
