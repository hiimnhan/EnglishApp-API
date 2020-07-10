SET IDENTITY_INSERT [EnglishApp].[Role] ON;

BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Role]
              WHERE [Name] = 'ADMIN')
    BEGIN
        INSERT INTO [EnglishApp].[Role]([Id], [Name], [DisplayName], [Description])
        VALUES (1, 'ADMIN', 'Administrator', 'System administrator')
        INSERT INTO [EnglishApp].[Role]([Id], [Name], [DisplayName], [Description])
        VALUES (2, 'USER', 'User', 'Application user')
    END
GO


COMMIT TRANSACTION;

SET IDENTITY_INSERT [EnglishApp].[Role] OFF;