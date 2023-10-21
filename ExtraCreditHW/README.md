# Multi-Dimensional Array Traversal

This Java program is designed to read input from a text file, process the input, and recursively print the coordinates of elements in a multi-dimensional array. It serves as a tool to visualize the traversal of multi-dimensional arrays, simulating the exploration of array elements.

## How It Works

The program reads input from a specified text file and interprets the input as coordinates in a multi-dimensional array. It uses a recursive function to print these coordinates in a structured manner, following the dimensions provided in the input. Here's an overview of how it works:

- It opens the input file using a `Scanner`.
- Reads integers from the file to determine the dimension and coordinates of the array.
- Calls a recursive function, `rec`, to print the array coordinates.
- The `rec` function simulates array traversal, printing coordinates as it goes.
- Once the entire array has been traversed, it resets and moves on to the next set of input.

## Getting Started

To use this program, you will need Java installed on your system. You can follow these steps to get started:

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/your-username/multi-dimensional-array-traversal.git

## Usage

Sample input:

3

1 2 3

-----
1

6

-------------

Output of the sample:

0 0 0

0 0 1

0 0 2

0 1 0

0 1 1

0 1 2


-------------
0

1

2

3

4

5

-------------

