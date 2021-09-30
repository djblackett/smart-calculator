# smart-calculator

## Console Calculator in Java

This is a console based calculator that respects the order of operations. It currently supports addition, subtraction, multiplication, division, negative numbers, and parentheses. 
You can assign numbers to variables for later use. Variables are not persisted and so will be reset when app is closed. Assigning another number to a variable will replace the previous value.

The Big Integer class is used when the length of the input requires it. 

The program's flow is as such:
1) Read input
2) Tokenize input - put all numbers and operators in a List, in order of input
3) Convert infix to postfix notation (Reverse Polish Notation) - Postfix is more computer friendly than infix notation
4) Do operations
<br>


### Example of expression containing all valid operations: <br>
`3 + 8 * ((4 + 3) * 2 + 1) - 6 / (2 + 1)`

<br>

### Valid commands: <br>
`/help`
`/exit`


<br>

### Example of various inputs and their resuting outputs:

    8 * 3 + 12 * (4 - 2)
    48
    2 - 2 + 3
    3
    4 * (2 + 3
    Invalid expression
    -10
    -10
    a=4
    b=5
    c=6
    a*2+b*3+c*(2+3)
    53
    1 +++ 2 * 3 -- 4
    11
    3 *** 5
    Invalid expression
    4+3)
    Invalid expression
