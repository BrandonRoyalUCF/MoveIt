-------------------------------------------------------------------------------------------------
--
-- Project - OverHaul
-- Author - Brandon Royal
-- Date - 3/26/2018
-- Description - Insert a transaction
-- Script Type - Stored Procedure
--
-------------------------------------------------------------------------------------------------

USE [OverHaul_Main]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[usp_InsertTransaction]
	@idUser int,
	@transactionTitle varchar(50),
	@transactionDescription varchar(500),
	@datePosted datetime2(7),
	@startLocation varchar(50),
	@endLocation varchar(50),
	@idTransaction int OUTPUT
AS

	INSERT INTO [Transaction](id_User, Title, Description, DatePosted, isCompleted)
	VALUES (@idUser, @transactionTitle, @transactionDescription, @datePosted, 0)

	SET @idTransaction = (SELECT SCOPE_IDENTITY())

	INSERT INTO [Location](id_Transaction, StartLocation, EndLocation)
	VALUES (@idTransaction, @startLocation, @endLocation)

	RETURN @idTransaction
GO


