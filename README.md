# Software-Design-And-Development

Coursework from a university Software Design and Development subject, collected in one repository. The material progresses from console Java and OOP fundamentals through JavaFX desktop UIs, JDBC database access, and REST/JSON consumption.

## Contents

| Folder | What it covers |
| --- | --- |
| `Classwork/` | In-class exercises — pass-by-value vs. pass-by-reference (`classwork`, `point`), a dice-roll simulation, and a `student` class |
| `Assignment/` | Console engineering-economics calculator: computes Uniform Annual Cost from an initial investment, interest rate, and term |
| `Assignment3/` | OOP and inheritance — an abstract `Pizza` base with `Small`, `Medium`, and `Large` subclasses, ordered and priced through a console menu |
| `Midterm/` | JavaFX `Calculator` — a `GridPane` form that adds two numbers |
| `GameDev/` | JavaFX game using `PathTransition`, `Timeline`, keyboard input, scoring, and a modal game-over dialog with a blur effect |
| `JavaViaMySQL/` | Plain JDBC — connect, run a `SELECT` against a `Student` table, iterate the `ResultSet` |
| `JavaViaMySQL_Server/` | The same, against a remote database server and wrapped in exception handling |
| `FinalExam/` | `FinalQ1` — a JavaFX CRUD screen over JDBC (view / insert / update / clear). `FinalQ2` — a JavaFX app that calls a web API with OkHttp and parses the response with json-simple |
| `Project/` | The capstone: a JavaFX application combining a JDBC-backed data-entry form with charting (`XYChart`, `CategoryAxis`), date pickers, and combo boxes. See `Course Project User Guide.pdf` |

`TextIO.java`, which appears in several folders, is the course-supplied console input helper.

## Building and running

The projects use the VS Code Java layout — sources in `src/`, compiled classes in `bin/`, and any third-party JARs in `lib/`. There is no build tool; open a folder in VS Code with the Java extension pack, or compile by hand:

```bash
cd Assignment3
javac -d bin src/Assignment/*.java
java -cp bin Assignment.Main
```

**JavaFX projects** (`Midterm`, `GameDev`, `FinalExam`, `Project`) were written against a JDK that still bundled JavaFX. On JDK 11+ you need the JavaFX SDK on the module path:

```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp bin Midterm.Calculator
```

**Database projects** (`JavaViaMySQL`, `JavaViaMySQL_Server`, `FinalExam/FinalQ1`, `Project`) need a MySQL Connector/J JAR on the classpath, and the connection URL, username, and password filled into the blank `DriverManager.getConnection("", "", "")` arguments.

`FinalExam/lib/` already contains the OkHttp, Okio, Kotlin stdlib, and json-simple JARs that `FinalQ2` requires.

## Note

This is archived coursework kept for reference — the credentials are intentionally left blank, and the code reflects course requirements rather than production practice.
