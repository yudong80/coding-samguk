# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is **coding-samguk**, a Java-based Three Kingdoms simulation game written in Korean. The project simulates ancient Chinese provinces, generals, armies, treasures, and military operations during the Three Kingdoms period.

## Commands

Since this is a basic Java project without build automation tools, all commands are manual:

### Compilation
```bash
cd V0
javac -d bin -cp src src/coding/samguk/*.java
```

### Running the Application
```bash
cd V0
java -cp bin coding.samguk.App
```

### Running Tests
The project uses JUnit 5 for testing:
```bash
cd V0
# Compile tests (requires JUnit 5 on classpath)
javac -d bin -cp "src:lib/*" src/coding/samguk/*.java

# Run specific test
java -cp "bin:lib/*" org.junit.platform.console.ConsoleLauncher --select-class coding.samguk.AppTest
```

## Code Architecture

### Core Domain Models

- **General** (`General.java`): Represents historical figures with attributes like war ability (무력), intelligence (지력), charisma (매력), politics (정치력), army command abilities, and personal army units
- **Province** (`Province.java`): Represents territorial regions with population, resources (gold/food), infrastructure development, assigned generals, and neighboring provinces
- **Treasure** (`Treasure.java`): Represents artifacts that boost general abilities

### Singleton Management Systems

- **GeneralsMap** (`GeneralsMap.java`): Singleton registry containing all historical generals with their pre-configured stats
- **ProvinceNet** (`ProvinceNet.java`): Singleton network managing all provinces and their geographical relationships
- **TreasuresMap** (`TreasuresMap.java`): Singleton registry of available treasures

### Military Operations

The `Province` class implements complex military operations:
- **transferTo()**: Move generals and armies between provinces
- **transport()**: Send resources (gold/food) with escort generals
- **motivateSoldiers()**: Boost army morale using general abilities
- **draftArmy()** / **raiseArmy()**: Recruit new soldiers using different methods

### Testing Architecture

- **AppTest.java**: Comprehensive JUnit 5 test suite covering all major functionality
- Uses `@BeforeEach` with `ProvinceNet.initForTesting()` to ensure clean test state
- Tests simulate historical scenarios like Luoyang (낙양) with Cao Cao's (조조) forces

### File Structure

```
V0/
├── src/coding/samguk/     # Main source package
├── .vscode/settings.json  # VS Code Java configuration
├── lib/                   # External dependencies (JUnit)
└── bin/                   # Compiled output (auto-generated)
```

## Development Notes

- All code and comments are in Korean, reflecting Three Kingdoms historical context
- The project uses Korean variable names and method names for domain concepts
- VS Code is configured with source path `src/` and output path `bin/`
- No external build tools (Maven/Gradle) - uses basic javac compilation
- JUnit 5 is used for testing but must be manually added to classpath