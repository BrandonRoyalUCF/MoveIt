-------------------------------------------------------------------------------------------------
--
-- Project - OverHaul
-- Author - Brandon Royal
-- Date - 3/22/2018
-- Description - Insert a drive profile for a user
-- Script Type - Data Insertion
--
-------------------------------------------------------------------------------------------------

USE OverHaul_Main
GO

SET NOCOUNT ON
GO

CREATE PROCEDURE dbo.usp_InsertDriver
	@idUser int,
	@DriverLiscenseNumber varchar(50),
	@CarMake varchar(50),
	@CarModel varchar(50),
	@LiscensePlateNumber varchar(50)
AS
	IF EXISTS (SELECT 1 FROM DriverInfo WHERE id_User = @idUser)
	BEGIN
		RAISERROR (100,-1,-1, 'Error: The user already has a driver profile.');
	END

	DECLARE @firstName varchar(50) = (SELECT FirstName FROM UserInfo where id = @idUser)
	DECLARE @lastName varchar(50) = (SELECT LastName FROM UserInfo where id = @idUser)

	INSERT INTO DriverInfo(FirstName, LastName, DriverLiscenseNumber, CarMake, CarModel, LiscensePlateNumber, isActive)
	VALUES(@firstName, @lastName, @DriverLiscenseNumber, @CarMake, @CarModel, @LiscensePlateNumber, 1)

GO