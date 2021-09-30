# smart-calculator

## Console Calculator in Java

This is a console based calculator that respects the order of operations. It currently supports addition, subtraction, multiplication, division, negative numbers, and parentheses. Decimal numbers are not supported. A more feature complete version of this calculator can be found in my swing-calculator repository.

You can assign numbers to variables for later use. Variables are not persisted and so will be reset when app is closed. Assigning another number to a variable will replace the previous value. Variables must contain only Latin letter and are case sensitive. Variables can also be assigned to variables. Inputting a variable name will print its value.

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
`/help` <br>
`/exit`


<br>

### Examples of inputs/outputs:

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
    
    
    
### BigIntegers
    112234567890 + 112234567890 * (10000000999 - 999)
    1122345679012234567890
    a = 800000000000000000000000
    b = 100000000000000000000000
    a + b
    900000000000000000000000
    /exit
    Bye!
    
    
### Variable assignment
The syntax for assigning variables is fairly forgiving. Variable names can be capital letters and more than one letter in length.
    n = 3
    m=4
    a  =   5
    b = a
    v=   7
    n =9
    count = 10
