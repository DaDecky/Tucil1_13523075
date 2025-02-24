# Compile Java files
javac -d bin src/*.java src/game/*.java src/IO/*.java

# Check if compilation was successful
if ($LASTEXITCODE -eq 0) {
    # Run the Java program
    java -cp bin Main
} else {
    Write-Host "Compilation failed. Please check your Java code."
}
