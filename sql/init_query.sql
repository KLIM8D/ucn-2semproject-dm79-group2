CREATE TABLE "Cities"  ( 
	"cityId"  	int IDENTITY(1,1) NOT NULL,
	"cityName"	varchar(256) NOT NULL,
	"zipCode" 	int NOT NULL,
	CONSTRAINT "pkCities" PRIMARY KEY CLUSTERED("cityId")
)
GO
CREATE TABLE "Clients"  ( 
	"clientId"   	int IDENTITY(1,1) NOT NULL,
	"cityId"     	int NOT NULL,
	"name"       	varchar(256) NOT NULL,
	"address"    	varchar(256) NOT NULL,
	"phoneNo"    	int NOT NULL,
	"eMail"      	varchar(256) NULL,
	"createdDate"	datetime NOT NULL,
	"editedDate" 	datetime NOT NULL,
	CONSTRAINT "pkClients" PRIMARY KEY CLUSTERED("clientId")
)
GO
CREATE TABLE "DataEntries"  ( 
	"entryId"     	int IDENTITY(1,1) NOT NULL,
	"taskId"      	int NOT NULL,
	"userId"      	int NOT NULL,
	"startDate"   	datetime NOT NULL,
	"endDate"     	datetime NOT NULL,
	"entryRemark" 	varchar(1024) NULL,
	"creationDate"	datetime NOT NULL,
	"editedDate"  	datetime NOT NULL,
	CONSTRAINT "pkDataEntry" PRIMARY KEY CLUSTERED("entryId")
)
GO
CREATE TABLE "EntryCollection"  ( 
	"collectionId"	int IDENTITY(1,1) NOT NULL,
	"sheetId"     	int NOT NULL,
	"entryId"     	int NOT NULL,
	CONSTRAINT "pkEntryCollection" PRIMARY KEY CLUSTERED("collectionId")
)
GO
CREATE TABLE "Logs"  ( 
	"logId"            	int IDENTITY(1,1) NOT NULL,
	"userId"           	int NOT NULL,
	"userDetails"      	varchar(512) NOT NULL,
	"exception"        	varchar(2048) NOT NULL,
	"exceptionLocation"	varchar(512) NOT NULL,
	"createdDate"      	datetime NOT NULL,
	CONSTRAINT "pkLogs" PRIMARY KEY CLUSTERED("logId")
)
GO
CREATE TABLE "PermissionWrapper"  ( 
	"wrapperId"      	int IDENTITY(1,1) NOT NULL,
	"sheetId"        	int NOT NULL,
	"permissionType" 	int NOT NULL,
	"permissionValue"	int NOT NULL,
	CONSTRAINT "pkPermissionWrapper" PRIMARY KEY CLUSTERED("wrapperId")
)
GO
CREATE TABLE "Tasks"  ( 
	"taskId"     	int IDENTITY(1,1) NOT NULL,
	"title"      	varchar(256) NOT NULL,
	"description"	varchar(2048) NOT NULL,
	CONSTRAINT "pkTasks" PRIMARY KEY CLUSTERED("taskId")
)
GO
CREATE TABLE "TimeSheets"  ( 
	"sheetId"    	int IDENTITY(1,1) NOT NULL,
	"userId"     	int NOT NULL,
	"clientId"   	int NOT NULL,
	"note"       	varchar(256) NULL,
	"createdDate"	datetime NOT NULL,
	"editedDate" 	datetime NOT NULL,
	CONSTRAINT "pkTimeSheet" PRIMARY KEY CLUSTERED("sheetId")
)
GO
CREATE TABLE "UserPermissions"  ( 
	"permissionId"	int IDENTITY(1,1) NOT NULL,
	"userRole"    	varchar(256) NOT NULL,
	"creationDate"	datetime NOT NULL,
	"editedDate"  	datetime NOT NULL,
	CONSTRAINT "pkUserPermissions" PRIMARY KEY CLUSTERED("permissionId")
)
GO
CREATE TABLE "Users"  ( 
	"userId"      	int IDENTITY(1,1) NOT NULL,
	"permissionId"	int NOT NULL,
	"firstName"   	varchar(256) NOT NULL,
	"lastName"    	varchar(256) NOT NULL,
	"userName"    	varchar(256) NOT NULL,
	"userPwd"     	varchar(256) NOT NULL,
	"creationDate"	datetime NOT NULL,
	"editedDate"  	datetime NOT NULL,
	CONSTRAINT "pkUsers" PRIMARY KEY CLUSTERED("userId")
)
GO
ALTER TABLE "Cities"
	ADD CONSTRAINT "Clients_Cities"
	FOREIGN KEY("cityId")
	REFERENCES "Clients"("cityId")
GO
ALTER TABLE "Tasks"
	ADD CONSTRAINT "DataEntries_Tasks"
	FOREIGN KEY("taskId")
	REFERENCES "DataEntries"("taskId")
GO
ALTER TABLE "Users"
	ADD CONSTRAINT "DataEntries_Users"
	FOREIGN KEY("userId")
	REFERENCES "DataEntries"("userId")
GO
ALTER TABLE "DataEntries"
	ADD CONSTRAINT "EntryCollection_DataEntries"
	FOREIGN KEY("entryId")
	REFERENCES "EntryCollection"("entryId")
GO
ALTER TABLE "EntryCollection"
	ADD CONSTRAINT "TimeSheets_EntryCollection"
	FOREIGN KEY("sheetId")
	REFERENCES "TimeSheets"("sheetId")
GO
ALTER TABLE "PermissionWrapper"
	ADD CONSTRAINT "TimeSheets_PermissionWrapper"
	FOREIGN KEY("sheetId")
	REFERENCES "TimeSheets"("sheetId")
GO
ALTER TABLE "Users"
	ADD CONSTRAINT "TimeSheets_Users"
	FOREIGN KEY("userId")
	REFERENCES "TimeSheets"("userId")
GO
ALTER TABLE "Clients"
	ADD CONSTRAINT "TimeSheets_Clients"
	FOREIGN KEY("clientId")
	REFERENCES "TimeSheets"("clientId")
GO
ALTER TABLE "Logs"
	ADD CONSTRAINT "Users_Logs"
	FOREIGN KEY("userId")
	REFERENCES "Users"("userId")
GO
ALTER TABLE "UserPermissions"
	ADD CONSTRAINT "Users_UserPermissions"
	FOREIGN KEY("permissionId")
	REFERENCES "Users"("permissionId")
GO