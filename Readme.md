# **Project Documentation**

## **1. Project Overview**
This project is a console-based calculator that supports basic arithmetic operations such as addition (`+`), subtraction (`-`), multiplication (`*`), division (`/`), and modulus (`%`). Additionally, it includes mathematical functions such as power (`^`), square root (`sqrt()`), absolute value (`abs()`), and rounding (`round()`). The program also maintains a history of past calculations and allows the user to recall them.

## **2. Design Choices**
- **User Input Handling**: The calculator takes mathematical expressions as input in string format.
- **Mathematical Function Parsing**: The program preprocesses functions (`sqrt()`, `abs()`, `round()`) before evaluating expressions.
- **Expression Evaluation**: The implementation converts infix expressions to postfix notation using the **Shunting-Yard Algorithm** and evaluates them using a stack-based approach.
- **User Experience**: After each calculation, the program asks the user whether they want to continue (`y/n`). It also supports a `history` command to recall previous calculations.

## **3. Algorithms and Data Structures Used**
- **Shunting-Yard Algorithm**: Converts infix notation to postfix notation to handle operator precedence correctly.
- **Stack Data Structure**: Used for evaluating postfix expressions efficiently.
- **Regular Expressions (Regex)**: Used for parsing mathematical functions like `sqrt()`, `abs()`, and `round()`.
- **ArrayList**: Stores history of calculations.

## **4. Challenges Encountered**
- Handling function calls like `abs(-5)` correctly without breaking the parsing process.
- Ensuring correct precedence and associativity of operators.
- Preventing division by zero and handling invalid inputs gracefully.

## **5. Improvements Made**
- The initial implementation relied on JavaScript's `ScriptEngine`, which was removed in newer Java versions. This was replaced with a custom evaluation algorithm.
- Error handling was improved to prevent crashes on invalid input.
- Function parsing was refined to support nested expressions.

## **6. Input/Output Handling**
- **User Input**: The calculator reads expressions from the console.
- **Program Output**: The results of calculations are displayed in the console.
- **History Storage**: The history of calculations is stored in an `ArrayList` and displayed upon request.

## **7. Acknowledgments**
This project was completed with the assistance of ChatGPT for debugging and implementation guidance, as well as insights from various YouTube tutorials on infix-to-postfix conversion and Java programming best practices.

