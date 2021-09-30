# smart-calculator
Console calculator in Java

This is a console based calculator that respects the order of operations. It currently supports addition, subtraction, multiplication, division, and parentheses. 
The Big Integer class is used when the length of the input requires it. 

The program's flow is as such:
1) Read input
2) Tokenize input - put all numbers and operators in a List, in order of input
3) Convert infix to postfix notation (Reverse Polish Notation) - Postfix is more computer friendly than infix notation
4) Do operations

