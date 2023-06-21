# ETreeCodingTest

Create a random generator class which takes as input a seed of numbers and their
associated probabilities. Implement the method nextNum() and a minimal but effective set of
unit tests. The method nextNum() should only ever return one of the seeded numbers and
given enough calls the probability of the output should converge on the seed probability.
Implement the solution in the language of your choice, Java is preferred, but Kotlin and other
languages are completely fine. Make sure your code is exemplary, as if it was going to be
shipped as part of a production system. You should leverage comments whenever you see fit
to
Setup
1. Setup a simple java ( or kotlin) project in an IDE of your choice
2. You can use maven or gradle (gradle with kotlin is preferred)
3. Java version should be 17 as minimum
4. Kotlin version ( if you choose to use it) should be 1.8.0
Algorithms
As a quick check, given Random Numbers are [-1, 0, 1, 2, 3] and Probabilities are
[0.01, 0.3, 0.58, 0.1, 0.01] if we call nextNum() 100 times we may get the following results.
As the results are random, these particular results are unlikely.
-1: 1 times
0: 22 times
1: 57 times
2: 20 times
3: 0 times
You may use Random.nextFloat() which returns a pseudo random number between 0 and 1.
