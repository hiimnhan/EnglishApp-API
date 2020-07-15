BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 1)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 1)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 2)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 2)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 3)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 3)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 4)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 4)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 5)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 5)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 6)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 6)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 7)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 7)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 1
                AND [PermissionId] = 8)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (1, 8)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 2
                AND [PermissionId] = 1)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (2, 1)
    END
GO

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[RolePermission]
              WHERE [RoleId] = 2
                AND [PermissionId] = 5)
    BEGIN
        INSERT INTO [EnglishApp1].[RolePermission]([RoleId], [PermissionId])
        VALUES (2, 5)
    END
GO
COMMIT TRANSACTION;