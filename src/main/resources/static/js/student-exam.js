const API_BASE = "http://localhost:8080";

function startExam() {
    const studentId = document.getElementById("studentId").value;
    const courseId = document.getElementById("courseId").value;

    if (!studentId || !courseId) {
        alert("Please enter Student ID and Course ID");
        return;
    }

    fetch(`${API_BASE}/api/exam/start?studentId=${studentId}&courseId=${courseId}`)
        .then(res => res.ok ? res.json() : res.text().then(t => { throw new Error(t); }))
        .then(data => {
            localStorage.setItem("studentId", studentId);
            localStorage.setItem("courseId", courseId);
            localStorage.setItem("examId", data.examId);
            localStorage.setItem("duration", data.durationMinutes);
            localStorage.setItem("questions", JSON.stringify(data.questions));

            window.location.href = "student-exam.html";
        })
        .catch(err => alert(err.message));
}

let timeLeft = 0;
let timerHandle = null;

window.addEventListener("load", () => {
    const container = document.getElementById("questionContainer");
    const timerEl = document.getElementById("timer");

    if (!container || !timerEl) return;

    const questions = JSON.parse(localStorage.getItem("questions"));
    const duration = Number(localStorage.getItem("duration"));

    if (!questions || !duration) {
        window.location.href = "exam.html";
        return;
    }

    timeLeft = duration * 60;

    questions.forEach((q, idx) => {
        container.insertAdjacentHTML("beforeend", `
            <div class="question-block">
                <p class="question-text">${idx + 1}. ${q.questionText}</p>

                <label class="option-row">
                    <input type="radio" name="q${q.questionId}" value="A">
                    <span>${q.optionA}</span>
                </label>

                <label class="option-row">
                    <input type="radio" name="q${q.questionId}" value="B">
                    <span>${q.optionB}</span>
                </label>

                <label class="option-row">
                    <input type="radio" name="q${q.questionId}" value="C">
                    <span>${q.optionC}</span>
                </label>

                <label class="option-row">
                    <input type="radio" name="q${q.questionId}" value="D">
                    <span>${q.optionD}</span>
                </label>
            </div>
        `);
    });

    document.getElementById("submitExamBtn").addEventListener("click", submitExam);
    startTimer(timerEl);
});

function startTimer(timerEl) {
    timerHandle = setInterval(() => {
        const m = Math.floor(timeLeft / 60);
        const s = timeLeft % 60;

        timerEl.textContent = `Time Left: ${m}:${s < 10 ? "0" : ""}${s}`;

        if (timeLeft <= 0) {
            clearInterval(timerHandle);
            submitExam();
        }
        timeLeft--;
    }, 1000);
}

function submitExam() {
    if (timerHandle) clearInterval(timerHandle);

    const questions = JSON.parse(localStorage.getItem("questions")) || [];
    const answers = [];

    questions.forEach(q => {
        const selected = document.querySelector(`input[name="q${q.questionId}"]:checked`);
        if (selected) {
            answers.push({
                questionId: q.questionId,
                selectedOption: selected.value
            });
        }
    });

    const payload = {
        studentId: Number(localStorage.getItem("studentId")),
        courseId: Number(localStorage.getItem("courseId")),
        answers
    };

    const examId = localStorage.getItem("examId");

    fetch(`${API_BASE}/api/exam/submit/${examId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
        .then(res => res.ok ? res.json() : res.text().then(t => { throw new Error(t); }))
        .then(result => {
            localStorage.setItem("examResult", JSON.stringify(result));
            window.location.href = "exam-result.html";
        })
        .catch(err => alert(err.message));
}
