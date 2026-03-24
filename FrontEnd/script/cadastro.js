async function realizarCadastro(event) {
    event.preventDefault(); // Impede a página de recarregar

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const cpf = document.getElementById('cpf').value;
    const senha = document.getElementById('senha').value;
    const confirmacaoSenha = document.getElementById('confirmar-senha').value;
    const erro = document.getElementById('erro');

    if (senha !== confirmacaoSenha) {
        erro.textContent = "As senhas não coincidem.";
        return;
    }

    const dados = {
        nome: nome,
        email: email,
        cpf: cpf,
        senha: senha,
        confirmacaoSenha: confirmacaoSenha
    };


    if (!nome || !email || !cpf || !senha || !confirmacaoSenha) {
        erro.textContent = "Preencha todos os campos.";
        return;
    }

    try {
        const response = await fetch('http://localhost:8081/usuario/cadastrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        });

        const mensagem = await response.text();

        if (response.ok) {
            window.location.href = "../pages/sucessoCadastro.html"; // Redireciona para a página de sucesso
        } else {
            alert("Erro no cadastro: " + mensagem);
        }
    } catch (error) {
        console.error("Erro ao conectar:", error);
        alert("Não foi possível falar com o servidor.");
    }
}