# Employee Rating Optimization with Z3 Solver

This Java program implements an optimization solution using the Z3 solver to adjust managerial ratings and eliminate bias. The goal is to generate fair and unbiased employee ratings. Below is a detailed explanation of the key components and steps of the program.

## Key Steps

### 1. Initialize Z3 Context
The Z3 solver requires a context object to manage all operations related to the solver. This context ensures that the solver can handle multiple variables, constraints, and optimization goals.

### 2. Variable Definitions
The program defines two key sets of variables:
- **`b[i]` (Manager Bias)**: These variables represent the bias of each manager. Manager bias is the systematic error that a manager might introduce while rating employees. For each manager, a bias variable `b[i]` is defined.
- **`x[j]` (Unbiased Employee Score)**: These variables represent the unbiased scores of employees, which are the values we aim to compute. The unbiased score reflects the actual performance of each employee without the influence of managerial bias.

### 3. Objective Function
The primary goal is to minimize the squared error between the ratings provided by the managers (`S_{ij}`) and the unbiased employee scores (`x_j`) adjusted for managerial bias (`b_i`). This is formulated as a **least-squares minimization** problem:
- Minimize the sum of squared differences: `minimize ∑ (S_{ij} - (x_j + b_i))^2`
  
This approach ensures that the adjusted ratings are as close as possible to the original ratings, while accounting for and eliminating managerial bias.

### 4. Bias Normalization
To prevent the system from drifting due to the collective bias of all managers, the program introduces a normalization constraint:
- The sum of all managerial biases must be zero: `∑b_i = 0`

This constraint guarantees that the system as a whole does not shift in one direction due to biased managers.

### 5. Employee Score Bounds
To ensure realistic ratings, constraints are added to restrict employee scores to a specific range. In this case, employee scores are constrained between 0 and 5:
- `0 ≤ x_j ≤ 5`

This reflects a typical rating scale that might be used in performance evaluations.

### 6. Optimization Setup
The objective function and constraints are added to the Z3 optimization problem. The solver is instructed to minimize the squared error using Z3's `opt.MkMinimize()` method.

### 7. Solving and Output
Finally, the program calls the Z3 solver's `opt.Check()` method to find a solution. If a solution exists, it outputs the unbiased employee scores (`x_j`) and the managerial biases (`b_i`). This allows for the adjustment of employee ratings to achieve a fair and unbiased ranking.

## Example Output

Here is an example of the program's output, where:
- `x_j` represents the unbiased score for each employee
- `b_i` represents the systematic bias for each manager

```
Employee Unbiased Scores:
x_1 = 4.2
x_2 = 3.7
x_3 = 5.0
x_4 = 2.8

Manager Biases:
b_1 = -0.3
b_2 = 0.2
b_3 = 0.1
```

In this example:
- Employee 1 has an unbiased score of 4.2
- Employee 2 has an unbiased score of 3.7
- Manager 1 has a negative bias of -0.3, meaning they tend to underrate employees
- Manager 2 has a positive bias of 0.2, meaning they tend to overrate employees

By adjusting the scores based on these biases, the program provides fair, unbiased ratings that better reflect the true performance of employees.

###For detailed installation and configuration steps, see [Installation Instructions (Install.md)](./Install.md).
