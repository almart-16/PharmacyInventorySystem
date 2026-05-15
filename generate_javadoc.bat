@echo off
echo Generating Javadoc documentation...

set CLASSPATH="lib\*;lib\MaterialJPA\*;lib\libXML\*"

javadoc -d doc -cp %CLASSPATH% -sourcepath src -subpackages interfaces:jdbc:jpa:main:pojos:xml

if %ERRORLEVEL% EQU 0 (
    echo [SUCCESS] Javadoc generated successfully in the 'doc' directory.
) else (
    echo [WARNING/ERROR] Javadoc generation completed with exit code %ERRORLEVEL%. Check for any warnings.
)
pause
