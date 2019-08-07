# MyriadProgram ![Travis Build status](https://travis-ci.org/ValerioEmanuele/MyriadProgram.svg?branch=master)

This project solves the following problem:

Write a program that takes as input from the command line an absolute path, and prints out a list of subdirectories and files contained within that path; sorting by size from largest to smallest.  The size of a subdirectory is the size of all of that subdirectories contents.  The program should also display the size of the files, and subdirectories in kilobytes.

For example, if we have a directory on our computer, c:\test_du that contains the following
directories and files:

- C:\test_du\foo\a.dat (100kb)
- C:\test_du\foo\b.dat (200kb)
- C:\test_du\foo\another_dir\jim.dat (500kb)
- C:\test_du\bar\ball.jpg (5kb)
- C:\test_du\bar\sam\sam1.jpg (100kb)
- C:\test_du\bar\sam\sam2.jpg (300kb)
- C:\test_du\somefile.dat (700kb)

Running the command java DU c:\test_du should produce the following output:

- DIR C:\TEST_DU\FOO 800KB
- FILE C:\TEST_DU\SOMEFILE.DAT 700KB
- DIR C:\TEST_DU\BAR 405KB

## Requirements
In order to run this web application you'll need:
 - Java >= 11
 - Maven 3
 - Lombok

## How to run the application with Maven
 1. Run `mvn clean install`
 2. Run `mvn exec:java -Dexec.args="<path_to_folder>"` (e.g. `mvn exec:java -Dexec.args="C:\test_du"`)
 