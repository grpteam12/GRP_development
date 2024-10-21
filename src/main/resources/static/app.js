document.getElementById("ranking-form").addEventListener("submit", async function (e) {
    e.preventDefault(); // 阻止表单默认提交行为

    // 收集表单数据
    const formData = new FormData(e.target);
    const scores = {};

    // 将表单数据转换为对象格式
    for (let [key, value] of formData.entries()) {
        scores[key] = parseFloat(value);
    }

    try {
        // 发送请求到后端 API
        const response = await fetch("http://localhost:8080/api/optimize", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(scores)
        });

        if (response.ok) {
            const result = await response.json();

            // 展示结果
            let resultDiv = document.getElementById("result");
            resultDiv.innerHTML = `
                <p>Employee 1 score: ${result.x_0}</p>
                <p>Employee 2 score: ${result.x_1}</p>
                <p>Employee 3 score: ${result.x_2}</p>
                <p>Employee 4 score: ${result.x_3}</p>
                <p>Manager A bias: ${result.b_0}</p>
                <p>Manager B bias: ${result.b_1}</p>
                <p>Manager C bias: ${result.b_2}</p>
            `;
        } else {
            console.error("Error:", response.statusText);
        }
    } catch (error) {
        console.error("Error:", error);
    }
});

