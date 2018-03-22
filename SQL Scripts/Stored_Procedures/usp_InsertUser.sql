-------------------------------------------------------------------------------------------------
--
-- Project - OverHaul
-- Author - Brandon Royal
-- Date - 3/22/2018
-- Description - Add a new user.
-- Script Type - Data Insertion
--
--
-------------------------------------------------------------------------------------------------

USE OverHaul_Main
Go

SET NOCOUNT ON
GO

CREATE PROCEDURE dbo.usp_InsertUser
	@UserName varchar(50),
	@PassWord varchar(50),
	@FirstName varchar(50),
	@LastName varchar(50)
AS
	
	IF EXISTS (SELECT 1 FROM UserLogin WHERE UserName = @UserName)
	BEGIN
		RAISERROR (100,-1,-1, 'Error: The user name already exists in the system.');
	END

	INSERT INTO UserInfo(UserName, PassWord, FirstName, LastName, isActive)
	VALUES (@UserName, @PassWord, @FirstName, @LastName, 1)


GO
