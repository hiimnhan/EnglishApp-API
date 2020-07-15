SET IDENTITY_INSERT [EnglishApp1].[Topic] ON;

BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Food')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (1, 'Food')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Sport')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (2, 'Sport')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Music')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (3, 'Music')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Animal')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (4, 'Animal')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Job')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (5, 'Job')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Flower')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (6, 'Flower')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Criminals')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (7, 'Criminals')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Business')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (8, 'Business')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Topic]
              WHERE [Name] = 'Space')
    BEGIN
        INSERT INTO [EnglishApp1].[Topic]([Id], [Name])
        VALUES (9, 'Space')
    END
GO

COMMIT TRANSACTION;

SET IDENTITY_INSERT [EnglishApp1].[Topic] OFF;
