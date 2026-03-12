-- 1. Habilita o modo de autenticação mista (SQL + Windows) no servidor
-- Nota: Isso altera uma configuração do Registro via SQL
EXEC xp_instance_regwrite N'HKEY_LOCAL_MACHINE', 
    N'Software\Microsoft\MSSQLServer\MSSQLServer', 
    N'LoginMode', REG_DWORD, 2;
GO

-- 2. Ativa o usuário 'sa' e define a senha
ALTER LOGIN sa ENABLE;
ALTER LOGIN sa WITH PASSWORD = '12345678';
GO