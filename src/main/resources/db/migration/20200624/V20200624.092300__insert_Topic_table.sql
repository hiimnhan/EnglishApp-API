SET IDENTITY_INSERT [EnglishApp].[Topic] ON;

BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Food')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (1, 'Food')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Sport')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (2, 'Sport')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Music')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (3, 'Music')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Animal')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (4, 'Animal')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Job')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (5, 'Job')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Flower')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (6, 'Flower')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Criminals')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (7, 'Criminals')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Business')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (8, 'Business')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp].[Topic]
              WHERE [Name] = 'Space')
    BEGIN
        INSERT INTO [EnglishApp].[Topic]([Id], [Name])
        VALUES (9, 'Space')
    END
GO

COMMIT TRANSACTION;

SET IDENTITY_INSERT [EnglishApp].[Topic] OFF;
