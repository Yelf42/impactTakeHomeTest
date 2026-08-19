# impact.com TakeHomeTest

## Assumptions I made:
### General:
- Java version can be >= 8
  - Implied by "Minimum Java version 8"
  - I assume this is just an older test from when JAVA8 was newer
- Interface cannot be modified, to be treated like a dependency or part of the codebase beyond the scope
- Using the methods should be done via the interface rather than the implementing class 
  - ie only `collect()` and `summarizeCollection()` should be public in the implementing class (for this task at least)
- There is a similar problem to this one where an unordered input of numbers should be returned as a sorted output of ranges. 
This problem is mildly more complex as there is more room to optimise by combining the sorting and range-merging. 
I do not believe the problem description given matches this alternative problem, as seen in my comments below. 
That being said, `SortedRangeSummarizer` would produce the same results as this alternative problem with an insignificantly higher performance cost and increased readability.

### Input:
- Input numbers are all integers (as expected by the output)
- Negative integers (and 0) are permitted
  - This does produce one mild pain point with ranges like "-5--3" looking weird, but it is easy to reformat the output if necessary
    - [5, -3] would be my suggestion
- Duplicate numbers are allowed, and adjacent duplicates are not a valid range
- Invalid inputs should throw exceptions rather than return a valid output
- Input can be delimited in a human manner, as if given from a text field, and aren't limited to commas
    - ie "1, 2, 3,4, 5" (spaces, inconsistency, etc)
    - Very easy to change to be stricter if that was desired
    - There is a restriction on using '-' as a delimiter though, as it would introduce ambiguity
- String input is not necessarily sorted, and the output does not require sorting. 
No part of the instructions indicates sorting is intended, and the sole example does not need/use sorting.
Grouping sequential numbers is just as valid a problem without also expecting the input to be ascending/descending
  - As an extension of this, decreasing ranges are permitted, such as "1, 5-3, -4"
  - If a range can be built in both directions, it will default to whichever comes first
    - ie "1,0,1" will become "1-0, 1" instead of "1, 0-1"
    - This could easily be changed, but this is the most natural interpretation in my opinion
  - I've included `SortedRangeSummarizer` as an example for the case where an ascending sorted output is expected from unsorted inputs

### Output:
- Output is actually delimited by ", " rather than just commas (as seen in the example result)


## Choices / Optimizations I made:
The problem is O(n), so there wasn't much room for optimization... </br>

I chose to use available array functions rather than build them from scratch. This improves readability and is more realistic for normal development. </br>

`String.split()` can add an empty string if there is a leading delimiter in the input, thus requiring filtering.
I considered directly using Pattern/Matcher, but I felt the readability and simplicity of the current approach outweighed the insignificant performance gain



## Instructions:
Implement code which has the ability to produce a comma delimited list of numbers, grouping the numbers into a range when they are sequential. Please submit your finished task via a pull request in GitHub (your code should be in GitHub, only code in GitHub will be reviewed. Please do not send us your files). Provided for you is an interface that you should implement as a solution to the exercise.

### Requirements:
- Must be Java code. Minimum Java version 8. If you are not familiar with the changes in Java 8, you will need to learn them for this exercise.
- Must be in GitHub.
- Must implement provided interface
- Must have valid unit tests

### The review will be based on:
- your ability to understand / interpret a requirement.
- your ability to learn quickly (learning changes in Java 8)
- your coding ability.
- optimisation of your code.
- your ability to compile a structured solution.
- present your solution with valid unit tests.
