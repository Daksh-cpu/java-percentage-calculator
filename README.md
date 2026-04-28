# 📊 Student Percentage Calculator — Java Mini Project

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![AWT](https://img.shields.io/badge/AWT-GUI-blue?style=for-the-badge&logo=java&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen?style=for-the-badge)

> A Java-based Student Result Calculator that computes **total marks**, **percentage**, **grade**, and **pass/fail status** across multiple subjects — available as both a **command-line application** and an **AWT GUI desktop application**.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [How It Works](#-how-it-works)
- [Grading System](#-grading-system)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the CLI Version](#running-the-cli-version)
  - [Running the GUI (Applet) Version](#running-the-gui-applet-version)
- [Sample Output](#-sample-output)
- [Technologies Used](#-technologies-used)
- [Author](#-author)

---

## 🧾 Overview

This mini project was developed as part of a Java programming coursework. It demonstrates core Java concepts including:

- **Input Validation** with loops and conditional checks
- **Arrays** for parallel data storage (subject names & marks)
- **String Manipulation** (splitting comma-separated input)
- **AWT GUI** using `Panel`, `TextField`, `Button`, `TextArea`, and `Label`
- **Event Handling** via `ActionListener`
- **Formatted Output** using `printf` / `String.format`

---

## ✨ Features

| Feature | CLI Version | GUI Version |
|---|:---:|:---:|
| Dynamic number of subjects | ✅ | ✅ |
| Custom subject names | ✅ | ✅ |
| Custom max marks per subject | ✅ | ✅ |
| Input validation (type & range) | ✅ | ✅ |
| Percentage calculation | ✅ | ✅ |
| Grade assignment (A–F) | ✅ | ✅ |
| Pass / Fail status | ✅ | ✅ |
| Subject-wise breakdown | ✅ | ✅ |
| Interactive desktop UI | ❌ | ✅ |
| Error display in UI | ❌ | ✅ |

---

## 📁 Project Structure

```
JAVA-MINI-PROJECT/
│
├── PercentageCalculator.java          # Console / CLI-based application
├── PercentageCalculatorApplet.java    # AWT GUI desktop application
└── README.md
```

---

## ⚙️ How It Works

### CLI Version (`PercentageCalculator.java`)

1. Prompts the user for the **number of subjects** (validated as a positive integer).
2. Prompts for **maximum marks per subject**.
3. Accepts **comma-separated subject names** and validates the count matches.
4. Iterates through each subject to accept **individual marks** (validated within `[0, maxMarks]`).
5. Calculates **total marks**, **percentage**, **grade**, and **pass/fail**.
6. Prints a **formatted subject-wise breakdown**.

### GUI Version (`PercentageCalculatorApplet.java`)

1. Opens an AWT desktop window with labeled input fields.
2. User fills in subjects count, max marks, subject names, and obtained marks.
3. Clicking **"Calculate Result"** triggers full validation and calculation.
4. Results and subject-wise breakdown are displayed in a scrollable `TextArea`.
5. Errors are shown in **red** inside the result area for immediate feedback.

---

## 🎓 Grading System

| Percentage Range | Grade | Status |
|:---:|:---:|:---:|
| ≥ 90% | **A** | PASS |
| ≥ 75% | **B** | PASS |
| ≥ 60% | **C** | PASS |
| ≥ 40% | **D** | PASS |
| < 40% | **F** | FAIL |

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK 8 or above** installed on your system.
- A terminal / command prompt or any Java IDE (IntelliJ IDEA, Eclipse, VS Code).

Verify your Java installation:
```bash
java -version
javac -version
```

---

### Running the CLI Version

**Step 1: Compile**
```bash
javac PercentageCalculator.java
```

**Step 2: Run**
```bash
java PercentageCalculator
```

**Step 3: Follow the prompts**
```
=========================================
       STUDENT RESULT CALCULATOR
=========================================
Enter the number of subjects: 3
Enter maximum possible marks per subject: 100
Enter Subject Names (comma separated): Maths, Physics, Chemistry

--- Entering Subject Marks ---
Enter marks obtained in Maths: 88
Enter marks obtained in Physics: 76
Enter marks obtained in Chemistry: 91
```

---

### Running the GUI (Applet) Version

**Step 1: Compile**
```bash
javac PercentageCalculatorApplet.java
```

**Step 2: Run as a standalone desktop application**
```bash
java PercentageCalculatorApplet
```

> 💡 The `main()` method wraps the AWT Panel inside a `java.awt.Frame`, so it runs as a fully functional desktop window without needing a browser or applet viewer.

---

## 📸 Sample Output

### CLI Output
```
==== RESULT ====

Total Max Marks : 300
Total Obtained  : 255
Percentage      : 85.0%
Grade           : B
Status          : PASS

=============================

Subject-wise Breakdown:
 - Maths      : 88 / 100
 - Physics    : 76 / 100
 - Chemistry  : 91 / 100
```

### GUI Output
The result is displayed in the scrollable text area with the same structured format.

---

## 🛠️ Technologies Used

- **Language**: Java (JDK 8+)
- **GUI Library**: Java AWT (`java.awt`, `java.awt.event`)
- **Input Handling**: `java.util.Scanner` (CLI), `TextField` (GUI)
- **IDE Recommended**: IntelliJ IDEA / Eclipse / VS Code with Java Extension

---

## 👤 Author

Developed as a **Java Mini Project** for academic coursework.

- Feel free to fork, star ⭐, and contribute!
- Pull requests for enhancements (e.g., Swing UI, file export) are welcome.

---

## 📄 License

This project is licensed under the **MIT License** — feel free to use and modify it for educational purposes.

---

<p align="center">Made with ☕ Java</p>
