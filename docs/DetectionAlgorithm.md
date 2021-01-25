# CHALLENGE MERCADO LIBRE
## MUTANT DETECTION ALGORITHM

As mentioned above, the objective of this API was related to carrying out the process of detecting a mutant given an array of String that represented each row of a size table (NxN) with the DNA sequence.

Below you can see, as a graph, the considerations to detect if a DNA sequence corresponds to that of a mutant or that of a human mentioned above:

![Alt Text](/docs/img/Img-1._Representation_of_the_DNA_sequence_table.png)


### STEP 1

Initially, and having very clear the considerations that must be taken into account to carry out a correct detection of the DNA sequence in relation to whether it corresponded to a mutant or a human, a first validation was carried out that consisted of evaluating the size of the string array representing the DNA sequence that was received. This in order to ensure that said received DNA sequence satisfies the consideration when constructing the sequence table with it, since by business rule it had been established that the size of said sequence table should be (NxN).

In addition, previously validation is also carried out in relation to the fact that the DNA sequence only contains the letters allowed by business rule (A, T, C, G).

![Alt Text](/docs/img/Img-5._NxN_table.png)





### STEP 2

It is analyzed what should be the logical behavior that the algorithm should use to move horizontally, vertically and diagonally through the table of DNA sequences.

To do this, the table of DNA sequences is displayed making the analogy with respect to the fact that each row and column represents a coordinate on the X and Y axis.

According to the above, it was reached the point of analyzing that the detection of a sequence of 4 equal letters in whatever direction (horizontal, vertical or diagonal) would be carried out through advanced behavior. That is to say, from any coordinate at a point (X, Y) or (Row, Column) in order not to generate checks at certain points that as we progressed in a movement on the table of DNA sequences we were leaving behind. We would proceed to check from each point (X, Y) or (Row, Column) only in 3 possible movements always in advance.

The 3 advanced movements established to go through the table of DNA sequences were:

1 position forward on the X axis
1 position forward on the Y axis
1 position forward on the possible diagonal from my current point

In this way we would be ensuring that with each possible forward movement on the table of DNA sequences in the possible ways (horizontal, vertical and diagonal) to find the different sequences of 4 letters in a row, they would be fully taken into account, without generating reprocessing. rechecking from each position at the point (X, Y) or (Row, Column) the positions for which when we made the forward movement we previously checked.

![Alt Text](/docs/img/Img-4._Advanced_movement_representation.png)





### STEP 3

Let us consider each point as a starting point from where we will start looking for a sequence of 4 equal letters.

Let's find the sequence of 4 equal letters in the 3 previously defined directions starting from the position (X, Y) or (row, column).

Take as starting point the point (X, Y) or (Row, Column) where I am currently.

We are validating if the first letter in which I start to move is already marked, and I continue validating if the remaining characters match.

And in this way I continue moving in a particular direction, until I reach the point where all the letters coincide, and the length value of the letters that coincide corresponds to 4.

With this I assume that I have found a DNA sequence, now I advance to the next position in the table and iterate again in the same way that I mentioned before, until I find another sequence of 4 letters in a row and the same. Using a valid counter, the number of sequences I have found, until I comply with the business rule that tells me that if I find more than one sequence of the same letters, in whatever form (horizontal, vertical, diagonal) I can consider that the DNA sequence corresponds to that of a mutant, therefore I stop the process of my detection algorithm and return the answer, otherwise it is a human and could not be recruited by Magneto.


![Alt Text](/docs/img/Img-3._NxN_table_representation.png "Img-3.NxN table representation")
