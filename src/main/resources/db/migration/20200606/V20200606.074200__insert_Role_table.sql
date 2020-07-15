SET IDENTITY_INSERT [EnglishApp1].[Role] ON;

BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Role]
              WHERE [Name] = 'ADMIN')
    BEGIN
        INSERT INTO [EnglishApp1].[Role]([Id], [Name], [DisplayName], [Description])
        VALUES (1, 'ADMIN', 'Administrator', 'System administrator')
        INSERT INTO [EnglishApp1].[Role]([Id], [Name], [DisplayName], [Description])
        VALUES (2, 'USER', 'User', 'Application user')
    END
GO


COMMIT TRANSACTION;

SET IDENTITY_INSERT [EnglishApp1].[Role] OFF;