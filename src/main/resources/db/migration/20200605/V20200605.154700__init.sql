USE [EnglishApp]
GO
IF NOT EXISTS(
        SELECT schema_name
        FROM information_schema.schemata
        WHERE schema_name = 'EnglishApp')
    BEGIN
        EXEC sp_executesql N'CREATE SCHEMA [EnglishApp]'
    END
GO
/****** Object:  Table [EnglishApp].[User]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[User]'))
CREATE TABLE [EnglishApp].[User]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [FirstName]    [nvarchar](255)      NOT NULL,
    [LastName]     [nvarchar](255)      NOT NULL,
    [Email]        [varchar](255)       NOT NULL,
    [Username]     [varchar](255)       NOT NULL,
    [Password]     [varchar](255)       NOT NULL,
    [RoleId]       [int]                NOT NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [EnglishApp].[Role]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[Role]'))
CREATE TABLE [EnglishApp].[Role]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [Name]         [varchar](255)       NOT NULL,
    [DisplayName]  [varchar](255)       NOT NULL,
    [Description]  [varchar](255)       NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY],
    CONSTRAINT [AK_Role_Name] UNIQUE NONCLUSTERED
        (
         [Name] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [EnglishApp].[Permission]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[Permission]'))
CREATE TABLE [EnglishApp].[Permission]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [Name]         [varchar](255)       NOT NULL,
    [Description]  [varchar](255)       NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY],
    CONSTRAINT [AK_Permission_Name] UNIQUE NONCLUSTERED
        (
         [Name] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [EnglishApp].[RolePermission]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[RolePermission]'))
CREATE TABLE [EnglishApp].[RolePermission]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [RoleId]       [int]                NOT NULL,
    [PermissionId] [int]                NOT NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY],
    CONSTRAINT [AK_RolePermission_RoleId_PermissionId] UNIQUE NONCLUSTERED
        (
         [RoleId] ASC,
         [PermissionId] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO

IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_RolePermission_PermissionId')
ALTER TABLE [EnglishApp].[RolePermission]
    WITH CHECK ADD CONSTRAINT [FK_RolePermission_PermissionId] FOREIGN KEY ([PermissionId])
        REFERENCES [EnglishApp].[Permission] ([Id])
GO
ALTER TABLE [EnglishApp].[RolePermission]
    CHECK CONSTRAINT [FK_RolePermission_PermissionId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_RolePermission_RoleId')
ALTER TABLE [EnglishApp].[RolePermission]
    WITH CHECK ADD CONSTRAINT [FK_RolePermission_RoleId] FOREIGN KEY ([RoleId])
        REFERENCES [EnglishApp].[Role] ([Id])
GO
ALTER TABLE [EnglishApp].[RolePermission]
    CHECK CONSTRAINT [FK_RolePermission_RoleId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_User_RoleId')
ALTER TABLE [EnglishApp].[User]
    WITH CHECK ADD CONSTRAINT [FK_User_RoleId] FOREIGN KEY ([RoleId])
        REFERENCES [EnglishApp].[Role] ([Id])
GO
ALTER TABLE [EnglishApp].[User]
    CHECK CONSTRAINT [FK_User_RoleId]
GO

/****** Object:  Table [EnglishApp].[Word]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[Word]'))
CREATE TABLE [EnglishApp].[Word]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [Vocabulary]   [nvarchar](255)       NOT NULL,
    [Spell]        [nvarchar](255)      NOT NULL,
    [Image]        [image],
    [TopicId]      [int]                NOT NULL,
    [LevelId]      [int]                NOT NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [EnglishApp].[Topic]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[Topic]'))
CREATE TABLE [EnglishApp].[Topic]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [Name]         [varchar](255)       NOT NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY],
    CONSTRAINT [AK_Topic_Name] UNIQUE NONCLUSTERED
        (
         [Name] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [EnglishApp].[Question]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[Question]'))
CREATE TABLE [EnglishApp].[Question]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [Question]     [varchar](255)       NOT NULL,
    [Image]        [image],
    [TopicId]      [int]                NOT NULL,
    [LevelId]      [int]                NOT NULL,
    [WordId]       [int]                NOT NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY],
    CONSTRAINT [AK_Question_Question] UNIQUE NONCLUSTERED
        (
         [Question] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [EnglishApp].[Level]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[Level]'))
CREATE TABLE [EnglishApp].[Level]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [Name]         [varchar](255)       NOT NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY],
    CONSTRAINT [AK_Level_Name] UNIQUE NONCLUSTERED
        (
         [Name] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [EnglishApp].[Progress]    Script Date: 6/05/2020 3:48:38 PM ******/
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'[EnglishApp].[Progress]'))
CREATE TABLE [EnglishApp].[Progress]
(
    [Id]           [int] IDENTITY (1,1) NOT NULL,
    [UserId]       [int]                NOT NULL,
    [WordId]       [int]                NOT NULL,
    [CreatedDate]  [datetime]           NOT NULL DEFAULT getdate(),
    [ModifiedDate] [datetime]           NULL,
    PRIMARY KEY CLUSTERED
        (
         [Id] ASC
            ) ON [PRIMARY],
    CONSTRAINT [AK_Progress_UserId_WordId] UNIQUE NONCLUSTERED
        (
         [UserId] ASC,
         [WordId] ASC
            ) ON [PRIMARY]
) ON [PRIMARY]
GO

IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_Progress_UserId')
ALTER TABLE [EnglishApp].[Progress]
    WITH CHECK ADD CONSTRAINT [FK_Progress_UserId] FOREIGN KEY ([UserId])
        REFERENCES [EnglishApp].[User] ([Id])
GO
ALTER TABLE [EnglishApp].[Progress]
    CHECK CONSTRAINT [FK_Progress_UserId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_Progress_WordId')
ALTER TABLE [EnglishApp].[Progress]
    WITH CHECK ADD CONSTRAINT [FK_Progress_WordId] FOREIGN KEY ([WordId])
        REFERENCES [EnglishApp].[Word] ([Id])
GO
ALTER TABLE [EnglishApp].[Progress]
    CHECK CONSTRAINT [FK_Progress_WordId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_Question_WordId')
ALTER TABLE [EnglishApp].[Question]
    WITH CHECK ADD CONSTRAINT [FK_Question_WordId] FOREIGN KEY ([WordId])
        REFERENCES [EnglishApp].[Word] ([Id])
GO
ALTER TABLE [EnglishApp].[Question]
    CHECK CONSTRAINT [FK_Question_WordId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_Word_TopicId')
ALTER TABLE [EnglishApp].[Word]
    WITH CHECK ADD CONSTRAINT [FK_Word_TopicId] FOREIGN KEY ([TopicId])
        REFERENCES [EnglishApp].[Topic] ([Id])
GO
ALTER TABLE [EnglishApp].[Word]
    CHECK CONSTRAINT [FK_Word_TopicId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_Word_LevelId')
ALTER TABLE [EnglishApp].[Word]
    WITH CHECK ADD CONSTRAINT [FK_Word_LevelId] FOREIGN KEY ([LevelId])
        REFERENCES [EnglishApp].[Level] ([Id])
GO
ALTER TABLE [EnglishApp].[Word]
    CHECK CONSTRAINT [FK_Word_LevelId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_Question_LevelId')
ALTER TABLE [EnglishApp].[Question]
    WITH CHECK ADD CONSTRAINT [FK_Question_LevelId] FOREIGN KEY ([LevelId])
        REFERENCES [EnglishApp].[Level] ([Id])
GO
ALTER TABLE [EnglishApp].[Question]
    CHECK CONSTRAINT [FK_Question_LevelId]
GO
IF NOT EXISTS(SELECT *
              FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
              WHERE CONSTRAINT_NAME = 'FK_Question_TopicId')
ALTER TABLE [EnglishApp].[Question]
    WITH CHECK ADD CONSTRAINT [FK_Question_TopicId] FOREIGN KEY ([TopicId])
        REFERENCES [EnglishApp].[Topic] ([Id])
GO
ALTER TABLE [EnglishApp].[Question]
    CHECK CONSTRAINT [FK_Question_TopicId]
GO
