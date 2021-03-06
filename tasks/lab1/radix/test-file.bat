rem @echo off
SET program="%1"
if %program% == "" goto err

echo translate from 16 to 10 value 0
%program% 0_16_10.txt 16 10 0 
if errorlevel 1 goto testFailed
fc.exe 0_16_10.txt check/0_16_10.txt
if errorlevel 1 goto testFailed

echo translate from 16 to 10 value 1F
%program% 1F_16_10.txt 16 10 1F 
if errorlevel 1 goto testFailed
fc.exe 1F_16_10.txt check/1F_16_10.txt
if errorlevel 1 goto testFailed

echo translate from 15 to 3 value 34AB3
%program% 34AB3_15_3.txt 15 3 34AB3
if errorlevel 1 goto testFailed
fc.exe 34AB3_15_3.txt check/34AB3_15_3.txt
if errorlevel 1 goto testFailed

echo translate from 25 to 5 value -456
%program% -456_25_5.txt 25 5 -456
if errorlevel 1 goto testFailed
fc.exe -456_25_5.txt check/-456_25_5.txt
if errorlevel 1 go

echo translate from 16 to 10 value 0
%program% 0_16_10.txt 16 10 0
if errorlevel 1 goto testFailed
fc.exe 0_16_10.txt check/0_16_10.txt
if errorlevel 1 goto testFailed

echo translate from 10 to 16 value 2147483647
%program% 2147483647_10_16.txt 10 16 2147483647
if errorlevel 1 goto testFailed
fc.exe 2147483647_10_16.txt check/2147483647_10_16.txt
if errorlevel 1 goto testFailed

echo translate from 36 to 36 value 123456
%program% 123456_36_36.txt 36 36 123456
if not errorlevel 1 goto testFailed
fc.exe 123456_36_36.txt check/coincidence_number_system.txt
if errorlevel 1 goto testFailed

echo translate from 36 to 1 value 435345353
%program% 435345353_36_1.txt 36 1 435345353
if not errorlevel 1 goto testFailed
fc.exe 435345353_36_1.txt check/incorrect_number_systems.txt
if errorlevel 1 goto testFailed

echo translate from 37 to 10 value RRR
%program% RRR_37_10.txt 37 10 RRR
if not errorlevel 1 goto testFailed
fc.exe RRR_37_10.txt check/incorrect_number_systems.txt
if errorlevel 1 goto testFailed

echo translate from 10 to 16 value FF
%program% FF_10_16.txt 10 16 FF
if not errorlevel 1 goto testFailed
fc.exe FF_10_16.txt check/incorrect_value.txt
if errorlevel 1 goto testFailed



echo OK
exit /b

:testFailed
ECHO Testing failed
exit /b

:err
echo Usage: test.bat <Path to program>