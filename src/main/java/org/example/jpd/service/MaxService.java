package org.example.jpd.service;

import org.example.jpd.common.solver.MaxNumberSolver;
import org.example.jpd.common.solver.impl.MaxNumberSolverImpl;
import org.example.jpd.entity.MaxEntity;

public class MaxService {

    public MaxEntity solve(MaxEntity maxEntity) {
        MaxNumberSolver solver = new MaxNumberSolverImpl();
        maxEntity.setResult(solver.solve(maxEntity.getInputA(), maxEntity.getInputB()));
        return maxEntity;
    }
}
