-------------------------------------------------------------------------------------------------
--
-- Project - OverHaul
-- Author - Brandon Royal
-- Date - 3/26/2018
-- Description - Insert a driver rating
-- Script Type - Stored Procedure
--
-------------------------------------------------------------------------------------------------

USE [OverHaul_Main]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[usp_InsertDriverRating]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[usp_InsertDriverRating]
END
GO

CREATE PROCEDURE [dbo].[usp_InsertDriverRating]
	@idDriver int,
	@idUserWhoRated int,
	@idTransaction int,
	@rating int
AS

	INSERT INTO [DriverRating](id_Driver, id_UserWhoRated, id_Transaction, rating)
	VALUES (@idDriver, @idUserWhoRated, @idTransaction, @rating)

GO


