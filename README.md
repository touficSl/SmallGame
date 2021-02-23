# Introduction

## Objective

- Speed up user calculation skills.
- Optimize children's intelligence.
- Show the time (in second).
- Challenge and Fun.
 

## About Project

- This game progress two modes ‘Easy’ or ‘Hard’.
- It Allows user to select the number of rows and the operator (‘+’, ‘-’, ‘/’).


# Algorithm

-  I take random numbers between 2 small numbers,  and i calculate all the table then, I show only these cells:

## Easy

- I show only the top row row[0] and left column col[0]

## Hard

- from the top row i show the even index, example: row[0]col[2], row[0]col[4], …, row[0]col[i%2==0]
- from the left column i show the odd index, example: col[0]row[1], col[0]row[3], …, col[0]row[i%2!=0]
- from the rest of cols-rows i show the diagonal cells, 
example: if rows = 3 and cols = 5 
show:  col[1]row[1], col[2]row[2], …,  col[i]row[j]  
  

# Screen Description  

- You can find screen description here https://docs.google.com/presentation/d/1lUOy0P11es6eXD2Aof0QakuonYwOPfRMwehCrBcOxvY/edit?usp=sharing 
