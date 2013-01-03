/* Insert sample users */
INSERT INTO USERS (permissionId, firstName, lastName, username, userPassword, saltValue, creationDate, editedDate) 
VALUES  
        (2, 'Hans', 'Jensen', 'iuy43', 'e7db4ae9dd112ae48da0bdafe830fa4eef7d9a9f333dddea8e2779d8e9d5cf504929d863c37e46a9a86db38292228d93d85e7c5d725534ab23435c7f6b117', '68A3EF250660FC4CF499670DE1A7BE8544EAB50C5DE4AB7AC3842AB711022BB8D7568F3F4838B05E2AA519D8DEEA5E946A26DD58F0793ACAD2251F7CFE805C07', '2013/01/02', '2013/01/02'),
        (1, 'Johnny', 'Jensen', 'jhd72', '8a4821144cfde1692aeef6839a39ec8835da565a97f9a3e7e13e2f41ebabdf9c3f17b564882073d9f11b539e0f6eb9a3e3325d7adb396c2a74d7d2510ad1f', '1DFE4453F93D9E4317A92B7AE249236630A793D2A6D20344390F3CA07183B47BE7238C62C2E3C1221E10CFEA2F531FCA1EC8C0013F46220908E1C759AF8EE931', '2013/01/02', '2013/01/02'),
		(1, 'Peder', 'Jensen', 'hgf23', 'f6cda9da1dddf8bfbeca2c9a151ccab93fe3aaf963f8b13641dad88f528782fe83c9ea92ac936ee977a5925e8c35fe45a4d2191d754bb6fec849e655a2d', '9D13A898403EF1CFD3D5A81DFED4911A109C29BB5190AE89BED97A3E5A9CD912C9B05754B7BAC29BD6292C827521189BE5B634EAF86ABFA9969070D27E13092A', '2013/01/02', '2013/01/02'),
		(1, 'Mogens', 'Jensen', 'ovf37', '317b122a68f4db71b8f8ed206d9be7e87a4bab2258fac4e3d4b71380123b733c13ee74763d84cdfcb7117e5b71e753733d7ddadb7b235097e87a8b14497', 'BB1A8633A4339083AFDFB64E0241A0FCB3DE77A8553E20A79802EAAFFF667894D99BD99BB69643950F1E9E171097CAA5E3597A6047AAAFBA03ADF1D926337FD1', '2013/01/02', '2013/01/02'),
		(3, 'Bjarne', 'Jensen', 'iou85', '49186a7d2fa705944d2a5c81634fe91cecb2f8386ecd8caf744d5771ba24f39d3d468ebb131689b61cb29f2dbe863132884619a53aba56661d4c8d7755f', 'A995C5F322DA4E3C50BF24622BEEA2E3E66C3250808EAA6C9E888FBCB5F261A99DBD1342A74D291EE359DAACD4A66329FB9D98C6097CEF8F6FD7515F93272958', '2013/01/02', '2013/01/02'),
		(2, 'Ib', 'Jensen', 'cxd72', '143a5b57d729b388957bcee9db5f70e831d07f2852eea89af29383ec4ebdfa16fb1789924e89c46f7d53ccf728b773928a9bccc9e81923882b13a6b5f07e8d', '74DC08495E9D6897EBE40D960811336B55F3D2A887056D841C4D718278A9F70F161BFD42B6EA485F8773690ABA8C327BBE648C8F5E91CA434B01225B9717EE3D', '2013/01/02', '2013/01/02')               
		(3, 'Test', 'User', 'test', 'e8cd6bb54d1a57d0896dae19b9e7aa9e4b5f152336e56b48655ea53cd8b1c804a7eb81c1c93bdc211d2c0302b7d91f9a0893dbeea5ee51cb85c185eb1723e', '1289hjusbv7f123', '2013/01/02', '2013/01/02')


/* Insert sample Clients */
INSERT INTO CLIENTS (cityId, name, address, phoneNo, eMail, creationdate, editedDate) 
VALUES (1080, 'Mogens Johnny', 'Bondevej 456', 23568712, 'mojohn@mail.dk','2013/01/02', '2013/01/02'),
       (1080, 'Axel Jensen', 'Byvej 12', 239112355, 'axjen@mail.dk','2013/01/02', '2013/01/02'),
	   (1080, 'Christian Nielsen', 'Landevej 200', 56908712, 'chrini@mail.dk','2013/01/02', '2013/01/02'),
	   (1080, 'Bo Jensen', 'Jordbærvænget 34', 23908712, 'bojen@mail.dk','2013/01/02', '2013/01/02'),
	   (1080, 'Rene Jensen', 'Hedebovej 4', 23458712, 'rejen@mail.dk','2013/01/02', '2013/01/02'),
	   (1080, 'Jørgen Jensen', 'Gammelvej 26', 27808712, 'jøjen@mail.dk','2013/01/02', '2013/01/02'),
	   (1080, 'Peder Sørensen', 'Torvegade 12', 76908712, 'pesør@mail.dk','2013/01/02', '2013/01/02')


/* Insert sample Tasks */
INSERT INTO TASKS (title, description) 
VALUES ('Løn', 'Beregning af løn for klienten'),
       ('Årsregnskab', 'Udførelse af årsregnskab for klienten'),
	   ('Momsregnskab', 'Udførelse af Momsregnskab for klienten'),
	   ('Budgettering', 'Estimering af Budget for klienten')


/* Insert sample Timesheets */
INSERT INTO TIMESHEETS (userId, clientId, note, creationDate, editedDate, caseId)
VALUES (3, 54, 'Opgaven er netop oprettet', '2013/01/02', '2013/01/03', 'L-455U90'),
       (46, 55, 'Sagen er igang', '2013/01/02', '2013/01/03', 'L-455U90'),
	   (49, 59, 'Ingen note', '2013/01/02', '2013/01/03', 'L-455U90'),
	   (47, 56, 'Sagen er oprettet', '2013/01/02', '2013/01/03', 'L-455U90'),
	   (3, 55, 'Sagen er oprettet', '2013/01/02', '2013/01/03', 'L-455U90')
	   

/* Insert sample Data Entries*/
INSERT INTO DATAENTRIES (sheetId, taskId, userId, startDate, endDate, entryRemark, creationDate, editedDate)
VALUES (32, 14, 3, '2013/01/02 10:02:00:000', '2013/01/03 16:04:00:000', 'Her skrives bemærkning om sagen', '2013/01/02 10:04:34:353', '2013/01/02 15:17:13:597'),
       (35, 17, 51, '2013/01/02 10:08:00:000', '2013/01/03 11:05:00:000', 'Her skrives bemærkning om sagen', '2013/01/02 10:12:54:149', '2013/01/02 14:05:43:218'),
	   (36, 16, 47, '2013/01/03 10:08:00:000', '2013/01/04 11:05:00:000', 'Her skrives bemærkning om sagen', '2013/01/03 10:12:54:149', '2013/01/04 14:05:43:218')


/* Insert sample Permission Wrappers*/
INSERT INTO PERMISSIONWRAPPER (sheetId, permissionType, permissionValue)
VALUES (32, 1, 49),
       (33, 2, 2),
	   (34, 1, 51)