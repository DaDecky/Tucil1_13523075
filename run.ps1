param (
    [string]$File = "./test/test_1.txt"
)

# Compile Java files
javac -d bin src/*.java src/game/*.java src/IO/*.java

# Check if compilation was successful
if ($LASTEXITCODE -eq 0) {
    # Run the Java program
    java -cp bin Main $File
} else {
    Write-Host "Compilation failed. Please check your Java code."
}
