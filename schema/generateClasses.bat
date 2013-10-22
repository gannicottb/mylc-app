@echo off
REM Brandon Gannicott
REM 12/5/2012
REM Senior Project, Lynchburg College
REM This is a batch file to speed up schema-based class generation for the MyLC App

REM cd to the folder with the .xsd file and run this .bat from there
REM this .bat also assumes that xsd.exe, xjc, and sed are in the PATH.
REM C:\Program Files (x86)\Java\jre6\jaxb-ri-2.2.6\bin;
REM C:\Program Files (x86)\GnuWin32\bin;
REM C:\Program Files (x86)\Microsoft SDKs\Windows\v7.0A\Bin

REM Generate C# class:
xsd.exe messageSchema.xsd /c /o:generated
REM Generate Java classes:
xjc messageSchema.xsd
REM Convert Java classes into Simple:
sed -f jaxb2simple.sed generated\Message.java > generated\SimpleMessage.java
