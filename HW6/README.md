# Sorting Comparison 

## Data Initialization: 
The program starts by initializing an ArrayList named allA to hold integer arrays. It creates four integer arrays, each with three different initial arrangements:

Random data set.
Almost sorted data set.
Almost reversed data set.
Data Generation: For each of these arrays, the code populates them with random integer values. The makeRandom, sortedRandom, and reverseRandom methods are used to generate these data sets, simulating random, almost sorted, and almost reversed data, respectively.

## Sorting Algorithms: 
The program then performs sorting on each of these data sets using three different sorting algorithms: bubble sort, quick sort, and merge sort. The goal is to compare the efficiency of these algorithms for different data arrangements.

### Bubble Sort: 
This is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. The number of comparisons made during the sorting process is counted.

### Quick Sort: 
Quick sort is a divide-and-conquer sorting algorithm. The code uses the quick sort algorithm to sort the arrays and counts the number of comparisons made.

### Merge Sort: 
Merge sort is another divide-and-conquer sorting algorithm. The code uses the merge sort algorithm to sort the arrays and counts the number of comparisons.

## Comparison Count: 
After each sorting operation, the code prints the original state of the array, the type of data set, and the number of comparisons made during the sorting process.

## Data Sets Comparison: 
The program repeats this process for all four different data lengths (10, 30, 50, and 100) and the three types of data arrangements.

## Output: 
The code prints the results of the sorting algorithms, allowing you to compare the number of comparisons made by each algorithm for different data sets and lengths.

The code is primarily designed to serve as an educational tool for comparing the efficiency of different sorting algorithms when dealing with various types of input data. The number of comparisons serves as a metric to assess the performance of these sorting algorithms under different conditions.
