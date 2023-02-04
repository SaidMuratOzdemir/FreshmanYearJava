# Number Guessing Game by Digits

This game first takes a number from the first user (suppose that this is your opponent in the game), and then takes guesses from the second user (suppose that this is you) one by one until the second user makes the correct guess.

  The rules are as follows:
  > The number and all guesses should be three-digit positive integers. In other words, the number and guesses should be in between 100 and 999. If a user enters an invalid input, the program should continue asking to enter a valid number or a guess. Please see the example run in the first box below.
  
  > When a guess is entered, the program first identifiesthe digits of the guess found in the correct place,and the digits of the guess found in an incorrect place. Then, it displays the numbers of correct and incorrect places. 
  
    In identifying correct and incorrect places, each digit in the guess can be matched at most one digit in the number or vice versa. 
    
Please examine the following examples together with the explanations to understand how your
program should determine the number of correct and incorrect places (digits with the correct place
are typed in green and those with an incorrect place are typed in red). More examples can be found
in the second, third, and fourth boxes given below.

***Suppose that the number is 345***  

- If the guess is 182 --> zero correct, zero incorrect

      No digits matched.

- If the guess is 1***4***2 --> one correct, zero incorrect

      4 locates at the tens place of the number, which is the same in the guess.

- If the guess is 1***4***_3_ --> one correct, one incorrect(3)

      4 locates at the tens place of the number, which is the same in the guess.

      3 locates at the hundreds place of the number, but it locates at the ones place of the guess.

- If the guess is _5_***4***_3_ --> one correct, two incorrect(5 and 3)

      4 locates at the tens place of the number, which is the same in the guess.

      3 locates at the hundreds place of the number, but it locates at the ones place of the guess.

      5 locates at the ones place of the number, but it locates at the hundreds place of the guess.

- If the guess is 4***4***1 --> one correct, zero incorrect

      4 locates at the tens place of the number and is matched with 4 also located atthe tens place of the guess.

      Since each digit in the number can be matched with only one digit in the guess (and vice versa), the other 4, which is located at the hundreds place of the guess, is not matched with any digit. Thus, it is counted as neither correct not incorrect.

- If the guess is 4***4***4 --> one correct, zero incorrect
