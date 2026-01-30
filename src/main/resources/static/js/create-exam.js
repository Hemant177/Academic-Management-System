document.addEventListener("DOMContentLoaded", () => {
    generateQuestionBlocks();
    document.getElementById("createExamBtn").addEventListener("click", submitCreateExam);
});

function generateQuestionBlocks() {
    const container = document.getElementById("questionsContainer");
    container.innerHTML = "";

    for (let i = 1; i <= 20; i++) {
        container.insertAdjacentHTML("beforeend", `
            <div class="question-block">
                <h4>Question ${i}</h4>

                <input
                    type="text"
                    class="form-input question-text"
                    placeholder="Enter question text"
                    required
                >

                <div class="option-grid">
                    <input type="text" class="form-input option-a" placeholder="Option A" required>
                    <input type="text" class="form-input option-b" placeholder="Option B" required>
                    <input type="text" class="form-input option-c" placeholder="Option C" required>
                    <input type="text" class="form-input option-d" placeholder="Option D" required>
                </div>

                <input
                    type="text"
                    class="form-input correct-option"
                    placeholder="Correct Option (A / B / C / D)"
                    maxlength="1"
                    required
                >
            </div>
        `);
    }
}

function submitCreateExam() {
    const courseIdInput = document.getElementById("courseId");
    const courseId = courseIdInput.value.trim();

    if (!courseId) {
        alert("Course ID is required");
        courseIdInput.focus();
        return;
    }

    const questionBlocks = document.querySelectorAll(".question-block");
    const questions = [];

    for (let i = 0; i < questionBlocks.length; i++) {
        const block = questionBlocks[i];

        const questionText = block.querySelector(".question-text").value.trim();
        const optionA = block.querySelector(".option-a").value.trim();
        const optionB = block.querySelector(".option-b").value.trim();
        const optionC = block.querySelector(".option-c").value.trim();
        const optionD = block.querySelector(".option-d").value.trim();
        const correctOption = block.querySelector(".correct-option").value.trim().toUpperCase();

        if (
            !questionText ||
            !optionA ||
            !optionB ||
            !optionC ||
            !optionD ||
            !["A", "B", "C", "D"].includes(correctOption)
        ) {
            alert(`Please fill all fields correctly for Question ${i + 1}`);
            return;
        }

        questions.push({
            questionText: questionText,
            optionA: optionA,
            optionB: optionB,
            optionC: optionC,
            optionD: optionD,
            correctOption: correctOption
        });
    }

    if (questions.length !== 20) {
        alert("Exactly 20 questions are required");
        return;
    }

    const payload = {
        courseId: Number(courseId),
        questions: questions
    };

    fetch("http://localhost:8080/api/admin/exam/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(text || "Failed to create exam");
                });
            }
            return response.text();
        })
        .then(message => {
            alert("Exam created successfully!");
            window.location.href = "dashboard.html";
        })
        .catch(error => {
            alert(error.message);
        });
}
