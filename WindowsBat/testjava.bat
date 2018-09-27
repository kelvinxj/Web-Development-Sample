@echo off
set JAVACMD=v

set _JAVACMD=%JAVACMD%

if not defined _JAVACMD (
  if not "%JAVA_HOME%"=="" (
    if exist "%JAVA_HOME%\bin\java.exe" set "_JAVACMD=%JAVA_HOME%\bin\java.exe"
  )
)

echo %_JAVACMD%