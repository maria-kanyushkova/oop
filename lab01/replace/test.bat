rem @echo off
SET program="%1"
if %program% == "" goto err

%program% input1.txt output1.txt qwe 123
IF ERRORLEVEL 1 GOTO testFailed
fc.exe output1.txt check/out1.txt
IF ERRORLEVEL 1 GOTO testFailed

%program% input2.txt output2.txt ma mama
IF ERRORLEVEL 1 GOTO testFailed
fc.exe output2.txt check/out2.txt
if ERRORLEVEL 1 goto testFailed

%program% input3.txt output3.txt cat dog
IF ERRORLEVEL 1 GOTO testFailed
fc.exe output3.txt check/out3.txt
if ERRORLEVEL 1 goto testFailed

%program% input4.txt output4.txt 1231234 true
IF ERRORLEVEL 1 GOTO testFailed
fc.exe output4.txt check/out4.txt
if ERRORLEVEL 1 goto testFailed


echo OK
EXIT /B

:testFailed
ECHO Testing failed
exit /b

:err
echo Usage: test.bat <Path to program>