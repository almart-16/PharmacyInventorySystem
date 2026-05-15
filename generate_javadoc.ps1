param (
    [string]$OutputDir = "doc"
)

Write-Host "Generating Javadoc documentation..."

# Define classpath to include all jars in lib directory and subdirectories
$ClassPath = "lib\*;lib\MaterialJPA\*;lib\libXML\*"

# Execute Javadoc
javadoc -d $OutputDir -cp `"$ClassPath`" -sourcepath src -subpackages interfaces:jdbc:jpa:main:pojos:xml

if ($LASTEXITCODE -eq 0) {
    Write-Host "Javadoc generated successfully in the '$OutputDir' directory." -ForegroundColor Green
} else {
    Write-Host "Javadoc generation completed with exit code $LASTEXITCODE. Check for any warnings." -ForegroundColor Yellow
}
