SET IDENTITY_INSERT [EnglishApp1].[Level] ON;

BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Level]
              WHERE [Name] = 'Easy')
    BEGIN
        INSERT INTO [EnglishApp1].[Level]([Id], [Name])
        VALUES (1, 'Easy')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Level]
              WHERE [Name] = 'Medium')
    BEGIN
        INSERT INTO [EnglishApp1].[Level]([Id], [Name])
        VALUES (2, 'Medium')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Level]
              WHERE [Name] = 'High')
    BEGIN
        INSERT INTO [EnglishApp1].[Level]([Id], [Name])
        VALUES (3, 'High')
    END
GO

COMMIT TRANSACTION;

SET IDENTITY_INSERT [EnglishApp1].[Level] OFF;
