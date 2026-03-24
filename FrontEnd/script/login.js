async function fazerLogin() {
    const email = document.getElementById('user').value.trim();
    const senha = document.getElementById('senha').value.trim();
    const erro = document.getElementById('mensagem-erro');

    if(email == '' || senha == '') {
            erro.textContent = "Preencha todos os campos.";
            return;
    }

    try {
        const response = await fetch('http://localhost:8081/usuario/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                senha: senha
            })
        });

        
        if (response.ok) {
            const dados = await response.json();
            
            // Se você usa JWT, salve o token aqui:
            // localStorage.setItem('token', dados.token);
            
            window.location.href = "../pages/dashboard.html"; // Redireciona o usuário
        } else {
            erro.textContent = "Email ou senha inválidos. Tente novamente.";
        }
    } catch (error) {
        console.error("Erro ao conectar com o servidor:", error);
    }
}