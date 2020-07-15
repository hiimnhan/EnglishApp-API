BEGIN TRANSACTION;

IF NOT EXISTS(SELECT *
              FROM [EnglishApp1].[User]
              WHERE [Username] = 'admin')
    BEGIN
        INSERT INTO [EnglishApp1].[User]([Username], [Password], [FirstName], [LastName], [Email], [RoleId])
        VALUES ('admin', '$2a$10$HvPbMCNs/U1eezqKUBi4S.VGiIgVwr9m6s2mfmxtJ.mGUMye46fre', 'Hai', 'Dang', 'dangthse63124@fpt.edu.vn', 1)
    END
GO

COMMIT TRANSACTION;