# %%
import numpy as np
import csv 
import random
x=[]
y=[]
with open('sudoku.csv', 'r') as sudoku:   
 
  csv_reader = csv.reader(sudoku)
  
  random_sudoku=random.randrange(90)+1

  i=0
  for row in csv_reader:
        i+=1
        if i==random_sudoku:
          x.append(row[0])
          y.append(row[1])
          break

empty_space=[]
m=[[],[],[],[],[],[],[],[],[]]
matrix=[[],[],[],[],[],[],[],[],[]]
solved_matrix=[[],[],[],[],[],[],[],[],[]]

col_missing_numbers=[[],[],[],[],[],[],[],[],[]]
row_missing_numbers=[[],[],[],[],[],[],[],[],[]]
square_missing_numbers=[[],[],[],[],[],[],[],[],[]]

for i in range(len(m)):
    a=9*i
    matrix[i]=x[0][a:a+9]
    solved_matrix[i]=y[0][a:a+9]
   

for i in range(len(m)):
  matrix[i]=list(matrix[i])
  solved_matrix[i]=list(solved_matrix[i])

m=np.array(matrix)
solved_nparray=np.array(solved_matrix)


m_transpose=m.transpose()
# print(m)
# print(solved_nparray)

square=[[],[],[],[],[],[],[],[],[]]



counter=0


# %%
def create_3x3_squares(m):
 
        for i in range(3):
                a=i*3
                   
                square[a]=np.concatenate([m[a][0:3],m[a+1][0:3],m[a+2][0:3]])
                square[a+1]=np.concatenate([m[a][3:6],m[a+1][3:6],m[a+2][3:6]])
                square[a+2]=np.concatenate([m[a][6:9],m[a+1][6:9],m[a+2][6:9]])


# %%
def find_spaces(m):
    s_numbers=[1,2,3,4,5,6,7,8,9]
    for i in range (len(m)):
        for x in s_numbers:
            if str(x) not in m[i]:
                    row_missing_numbers[i].append(x)
          
            if str(x) not in square[i]:
                    square_missing_numbers[i].append(x)
    for i in range (len(m_transpose)):

        for x in s_numbers:
           
            if str(x) not in m_transpose[i]:
            
                col_missing_numbers[i].append(x)

# %%

def squareof(row,col):
    if row in range(0,3) and col in range(0,3):
        return 0
    if row in range(0,3) and col in range(3,6):
        return 1
    if row in range(0,3) and col in range(6,9):
        return 2

    if row in range(3,6) and col in range(0,3):
        return 3
    if row in range(3,6) and col in range(3,6):
        return 4
    if row in range(3,6) and col in range(6,9):
        return 5
    
    if row in range(6,9) and col in range(0,3):
        return 6
    if row in range(6,9) and col in range(3,6):
        return 7
    if row in range(6,9) and col in range(6,9):
        return 8


def fill(x):
    array1=row_missing_numbers
    array2=col_missing_numbers
    mtx=m


    possible_numbers=[]
 
    for i in range (len(mtx[x])):
        if mtx[x][i]=="0":
            for number in array1[x]:
                for number2 in array2[i]:
                    for number3 in square_missing_numbers[squareof(x,i)]:
                            if number==number2 and number==number3:
                                possible_numbers.append(number)
           
            if len(possible_numbers)==1:
               
                    m[x][i]=str(possible_numbers[0])
                   
                    possible_numbers.clear()
                    break

                    
                
            else:
                possible_numbers.clear()

   



# %%

counter=0
print("\nInitial Sudoku ( number:",random_sudoku,"):\n",m)
while(True):
    counter+=1
    
    for i in range (9):
        create_3x3_squares(m)
        find_spaces(m)
        fill(i)
       

        # reset_missing_numbers()
        col_missing_numbers=[[],[],[],[],[],[],[],[],[]]
        row_missing_numbers=[[],[],[],[],[],[],[],[],[]]
        square_missing_numbers=[[],[],[],[],[],[],[],[],[]]
        
    if (m==solved_nparray).all():
        print("Iterations:",counter)
        print("\nResolved Sudoku by algorithm:\n",m)
        print("\nSudoku Solution:\n",solved_nparray)

        break


