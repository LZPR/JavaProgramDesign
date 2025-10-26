package org.example.jpd.common.solver.impl;

import org.example.jpd.common.solver.MaxNumberSolver;

public class MaxNumberSolverImpl implements MaxNumberSolver {

    @Override
    public double solve(double a, double b) {
        if (a > b) return a;
        return b;
    }
}
