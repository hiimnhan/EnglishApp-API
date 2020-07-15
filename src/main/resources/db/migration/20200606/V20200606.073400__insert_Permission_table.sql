SET IDENTITY_INSERT [EnglishApp1].[Permission] ON;

BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'READ_QUESTION')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission]([Id], [Name], [Description])
        VALUES (1, 'READ_QUESTION', 'Allow user to read quiz question')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'CREATE_QUESTION')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission]([Id], [Name], [Description])
        VALUES (2, 'CREATE_QUESTION', 'Allow user to create quiz question')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'EDIT_QUESTION')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission] ([Id], [Name], [Description])
        VALUES (3, 'EDIT_QUESTION', 'Allow user to edit quiz question')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'DELETE_QUESTION')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission] ([Id], [Name], [Description])
        VALUES (4, 'DELETE_QUESTION', 'Allow user to delete quiz question')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'READ_WORD')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission] ([Id], [Name], [Description])
        VALUES (5, 'READ_WORD', 'Allow user to read and study word')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'CREATE_WORD')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission] ([Id], [Name], [Description])
        VALUES (6, 'CREATE_WORD', 'Allow user to create word')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'EDIT_WORD')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission] ([Id], [Name], [Description])
        VALUES (7, 'EDIT_WORD', 'Allow user to edit word')
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[Permission]
              WHERE [Name] = 'DELETE_WORD')
    BEGIN
        INSERT INTO [EnglishApp1].[Permission] ([Id], [Name], [Description])
        VALUES (8, 'DELETE_WORD', 'Allow user to delete word')
    END
GO

COMMIT TRANSACTION;

SET IDENTITY_INSERT [EnglishApp1].[Permission] OFF;