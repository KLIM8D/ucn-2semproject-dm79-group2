/* Insert sample users */
INSERT INTO USERS (permissionId, firstName, lastName, username, userPassword, saltValue, creationDate, editedDate) 
VALUES  
        (2, 'Hans', 'Jensen', 'iuy43', 'e7db4ae9dd112ae48da0bdafe830fa4eef7d9a9f333dddea8e2779d8e9d5cf504929d863c37e46a9a86db38292228d93d85e7c5d725534ab23435c7f6b117', '68A3EF250660FC4CF499670DE1A7BE8544EAB50C5DE4AB7AC3842AB711022BB8D7568F3F4838B05E2AA519D8DEEA5E946A26DD58F0793ACAD2251F7CFE805C07', '2013/01/02', '2013/01/02'),
        (1, 'Johnny', 'Jensen', 'jhd72', '8a4821144cfde1692aeef6839a39ec8835da565a97f9a3e7e13e2f41ebabdf9c3f17b564882073d9f11b539e0f6eb9a3e3325d7adb396c2a74d7d2510ad1f', '1DFE4453F93D9E4317A92B7AE249236630A793D2A6D20344390F3CA07183B47BE7238C62C2E3C1221E10CFEA2F531FCA1EC8C0013F46220908E1C759AF8EE931', '2013/01/02', '2013/01/02'),
		(1, 'Peder', 'Jensen', 'hgf23', 'f6cda9da1dddf8bfbeca2c9a151ccab93fe3aaf963f8b13641dad88f528782fe83c9ea92ac936ee977a5925e8c35fe45a4d2191d754bb6fec849e655a2d', '9D13A898403EF1CFD3D5A81DFED4911A109C29BB5190AE89BED97A3E5A9CD912C9B05754B7BAC29BD6292C827521189BE5B634EAF86ABFA9969070D27E13092A', '2013/01/02', '2013/01/02'),
		(1, 'Mogens', 'Jensen', 'ovf37', '317b122a68f4db71b8f8ed206d9be7e87a4bab2258fac4e3d4b71380123b733c13ee74763d84cdfcb7117e5b71e753733d7ddadb7b235097e87a8b14497', 'BB1A8633A4339083AFDFB64E0241A0FCB3DE77A8553E20A79802EAAFFF667894D99BD99BB69643950F1E9E171097CAA5E3597A6047AAAFBA03ADF1D926337FD1', '2013/01/02', '2013/01/02'),
		(3, 'Bjarne', 'Jensen', 'iou85', '49186a7d2fa705944d2a5c81634fe91cecb2f8386ecd8caf744d5771ba24f39d3d468ebb131689b61cb29f2dbe863132884619a53aba56661d4c8d7755f', 'A995C5F322DA4E3C50BF24622BEEA2E3E66C3250808EAA6C9E888FBCB5F261A99DBD1342A74D291EE359DAACD4A66329FB9D98C6097CEF8F6FD7515F93272958', '2013/01/02', '2013/01/02'),
		(2, 'Ib', 'Jensen', 'cxd72', '143a5b57d729b388957bcee9db5f70e831d07f2852eea89af29383ec4ebdfa16fb1789924e89c46f7d53ccf728b773928a9bccc9e81923882b13a6b5f07e8d', '74DC08495E9D6897EBE40D960811336B55F3D2A887056D841C4D718278A9F70F161BFD42B6EA485F8773690ABA8C327BBE648C8F5E91CA434B01225B9717EE3D', '2013/01/02', '2013/01/02'),               
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
VALUES (1, 1, 'Opgaven er netop oprettet', '2013/01/02', '2013/01/03', 'L-455U90'),
       (2, 2, 'Sagen er igang', '2013/01/02', '2013/01/03', 'L-455U91'),
	   (3, 3, 'Ingen note', '2013/01/02', '2013/01/03', 'L-455U92'),
	   (4, 4, 'Sagen er oprettet', '2013/01/02', '2013/01/03', 'L-455U93'),
	   (5, 5, 'Sagen er oprettet', '2013/01/02', '2013/01/03', 'L-455U94')
	   

/* Insert sample Data Entries*/
INSERT INTO DATAENTRIES (sheetId, taskId, userId, startDate, endDate, entryRemark, creationDate, editedDate)
VALUES (1, 1, 1, '2013/01/02 10:02:00:000', '2013/01/02 16:04:00:000', 'Her skrives bemærkning om sagen', '2013/01/02 10:04:34:353', '2013/01/02 15:17:13:597'),
(1, 3, 1, '2013/01/03 11:05:00:000', '2013/01/03 16:04:00:000', 'Her skrives bemærkning om sagen', '2013/01/02 10:04:34:353', '2013/01/02 15:17:13:597'),
(1, 2, 1, '2013/01/04 14:02:00:000', '2013/01/04 16:04:00:000', 'Her skrives bemærkning om sagen', '2013/01/02 10:04:34:353', '2013/01/02 15:17:13:597'),
       (3, 3, 2, '2013/01/02 10:08:00:000', '2013/01/03 11:05:00:000', 'Her skrives bemærkning om sagen', '2013/01/02 10:12:54:149', '2013/01/02 14:05:43:218'),
	   (4, 2, 3, '2013/01/03 10:08:00:000', '2013/01/04 11:05:00:000', 'Her skrives bemærkning om sagen', '2013/01/03 10:12:54:149', '2013/01/04 14:05:43:218')


/* Insert sample Permission Wrappers*/
INSERT INTO PERMISSIONWRAPPER (sheetId, permissionType, permissionValue)
VALUES (1, 1, 7),
       (3, 2, 2),
	   (4, 1, 7),
       (2, 2, 3),
       (5, 2, 3)


/* Insert sample Logs */
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Windows 7 amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_05<br/>User home dir: C:\Users\RasmusLp<br/>', '[Ljava.lang.StackTraceElement;@43b63017', 'net.sf.jasperreports.engine.JRException', '20121228 13:39:40.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Linux amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_10<br/>User home dir: /home/klim<br/>', '[Ljava.lang.StackTraceElement;@38ee1abe', 'java.lang.ArrayIndexOutOfBoundsException', '20121228 14:14:00.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Linux amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_10<br/>User home dir: /home/klim<br/>', '[Ljava.lang.StackTraceElement;@f752ff', 'java.lang.ArrayIndexOutOfBoundsException', '20121228 14:17:55.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Linux amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_10<br/>User home dir: /home/klim<br/>', '[Ljava.lang.StackTraceElement;@6575cfac', 'java.lang.NullPointerException', '20121228 15:15:20.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Linux amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_10<br/>User home dir: /home/klim<br/>', '[Ljava.lang.StackTraceElement;@4d98d61e', 'java.lang.NullPointerException', '20121228 15:15:24.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Linux amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_10<br/>User home dir: /home/klim<br/>', '[Ljava.lang.StackTraceElement;@6695c13b', 'java.lang.NullPointerException', '20121228 15:34:27.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Linux amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_10<br/>User home dir: /home/klim<br/>', 'java.lang.NullPointerException
	at views.SearchUI$5.actionPerformed(SearchUI.java:228)
	at utils.ButtonColumn.actionPerformed(ButtonColumn.java:203)
	at javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:2018)
	at javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2341)
	at javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:402)
	at javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:259)
	at javax.swing.plaf.basic.BasicButtonListener.mouseReleased(BasicButtonListener.java:252)
	at java.awt.Component.processMouseEvent(Component.java:6505)
	at javax.swing.JComponent.processMouseEvent(JComponent.java:3321)
	at java.awt.Component.processEvent(Component.java:6270)
	at java.awt.Container.processEvent(Container.java:2229)
	at java.awt.Component.dispatchEventImpl(Component.java:4861)
	at java.awt.Container.dispatchEventImpl(Container.java:2287)
	at java.awt.Component.dispatchEvent(Component.java:4687)
	at javax.swing.plaf.basic.BasicTableUI$Handler.repostEvent(BasicTableUI.java:948)
	at javax.swing.plaf.basic.BasicTableUI$Handler.mouseReleased(BasicTableUI.java:1164)
	at java.awt.AWTEventMulticaster.mouseReleased(AWTEventMulticaster.java:290)
	at java.awt.AWTEventMulticaster.mouseReleased(AWTEventMulticaster.java:289)
	at java.awt.Component.processMouseEvent(Component.java:6505)
	at javax.swing.JComponent.processMouseEvent(JComponent.java:3321)
	at java.awt.Component.processEvent(Component.java:6270)
	at java.awt.Container.processEvent(Container.java:2229)
	at java.awt.Component.dispatchEventImpl(Component.java:4861)
	at java.awt.Container.dispatchEventImpl(Container.java:2287)
	at java.awt.Component.dispatchEvent(Component.java:4687)
	at java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:4832)
	at java.awt.LightweightDispatcher.processMouseEvent(Container.java:4492)
	at java.awt.LightweightDispatcher.dispatchEvent(Container.java:4422)
	at java.awt.Container.dispatchEventImpl(Container.java:2273)
	at java.awt.Window.dispatchEventImpl(Window.java:2719)
	at java.awt.Component.dispatchEvent(Component.java:4687)
	at java.awt.EventQueue.dispatchEventImpl(EventQueue.java:723)
	at java.awt.EventQueue.access$200(EventQueue.java:103)
	at java.awt.EventQueue$3.run(EventQueue.java:682)
	at java.awt.EventQueue$3.run(EventQueue.java:680)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:76)
	at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:87)
	at java.awt.EventQueue$4.run(EventQueue.java:696)
	at java.awt.EventQueue$4.run(EventQueue.java:694)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:76)
	at java.awt.EventQueue.dispatchEvent(EventQueue.java:693)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:242)
	at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:161)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:150)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:146)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:138)
	at java.awt.EventDispatchThread.run(EventDispatchThread.java:91)', 'java.lang.NullPointerException', '20121228 16:16:03.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Windows 7 amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_05<br/>User home dir: C:\Users\RasmusLp<br/>', '[Ljava.lang.StackTraceElement;@75d911c0', 'java.lang.NullPointerException', '20121228 19:11:15.0')
GO
INSERT INTO [dbo].[Logs]([userId], [userDetails], [exception], [exceptionLocation], [creationDate])
  VALUES(7, 'OS: Windows 7 amd64<br/>Java vendor: Oracle Corporation<br/>Java version: 1.7.0_05<br/>User home dir: C:\Users\RasmusLp<br/>', '[Ljava.lang.StackTraceElement;@354ac4d3', 'java.lang.NullPointerException', '20121228 19:12:50.0')
GO
