Implement in java a predictive text input system (as used by
cellphones using a numeric keypad). Your program should take a
training dataset file (the text of Alice in Wonderland in this case)
and a numeric input, e.g. "227", and then output the set of words
corresponding to the input, e.g. "car", "cap", "bar", in order of word
popularity in the corpus. No additional language
dictionaries/datasets should be used.

To get you started with the basics, a file containing the main()
method and a few utility functions is provided.

Extra credit: Also output the five most popular prefix matches, e.g.
i.e. longer words that have the input as a prefix, e.g. for "227" we
might have "carp", "bartender", etc.

Here's an example of how your program would run:

> java Suggest alice_in_wonderland.txt 729
Exact matches for 729:
say
saw
paw
raw
Prefix matches for 729:
saying

The number to letter mapping to use is the standard phone keypad
number -> alphabet mapping, reproduced here for convenience:

2 abc
3 def
4 ghi
5 jkh
6 mno
7 pqrs
8 tuv
9 wxyz

abc
abc
pgrs


415 832 2133
findallmatches( 