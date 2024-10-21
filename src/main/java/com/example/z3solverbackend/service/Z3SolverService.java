package com.example.z3solverbackend.service;

import com.microsoft.z3.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Z3SolverService {

    public Map<String, Object> optimizeScores(Map<String, Double> scores) {
        // 模拟一个3个管理者对4个员工的评分矩阵
        double[][] S = new double[3][4];
        S[0][0] = scores.get("score_A1");
        S[0][1] = scores.get("score_A2");
        S[0][2] = scores.get("score_A3");
        S[0][3] = scores.get("score_A4");

        S[1][0] = scores.get("score_B1");
        S[1][1] = scores.get("score_B2");
        S[1][2] = scores.get("score_B3");
        S[1][3] = scores.get("score_B4");

        S[2][0] = scores.get("score_C1");
        S[2][1] = scores.get("score_C2");
        S[2][2] = scores.get("score_C3");
        S[2][3] = scores.get("score_C4");

        // 调用Z3求解器
        return solveWithZ3(S);
    }

    private Map<String, Object> solveWithZ3(double[][] S) {
        Map<String, Object> result = new HashMap<>();
        HashMap<String, String> cfg = new HashMap<>();
        cfg.put("model", "true");
        Context ctx = new Context(cfg);

        try {
            int m = S.length; // 管理者数量
            int n = S[0].length; // 员工数量

            // 定义管理者偏差变量
            RealExpr[] b = new RealExpr[m];
            for (int i = 0; i < m; i++) {
                b[i] = ctx.mkRealConst("b_" + i);
            }

            // 定义员工无偏评分变量
            RealExpr[] x = new RealExpr[n];
            for (int j = 0; j < n; j++) {
                x[j] = ctx.mkRealConst("x_" + j);
            }

            // 创建最小二乘目标函数
            ArithExpr<?> objective = ctx.mkReal(0);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (S[i][j] != -1) {  // 跳过缺失评分
                        ArithExpr<?> diff = ctx.mkSub(ctx.mkReal(String.valueOf(S[i][j])), x[j], b[i]);
                        ArithExpr<?> squaredDiff = ctx.mkMul(diff, diff);
                        objective = ctx.mkAdd(objective, squaredDiff);
                    }
                }
            }




            // 偏差归一化（偏差和应为0）
            ArithExpr<?> sumBiases = ctx.mkReal(0);
            for (int i = 0; i < m; i++) {
                sumBiases = ctx.mkAdd(sumBiases, b[i]);
            }
            BoolExpr normalizeConstraint = ctx.mkEq(sumBiases, ctx.mkReal(0));


            // 约束员工评分范围为[0, 5]
            BoolExpr[] scoreConstraints = new BoolExpr[n];
            for (int j = 0; j < n; j++) {
                scoreConstraints[j] = ctx.mkAnd(ctx.mkLe(ctx.mkReal(0), x[j]), ctx.mkLe(x[j], ctx.mkReal(5)));
            }

            // 使用优化器
            Optimize opt = ctx.mkOptimize();
            opt.Add(normalizeConstraint); // 添加偏差归一化约束
            for (BoolExpr scoreConstraint : scoreConstraints) {
                opt.Add(scoreConstraint);  // 添加评分范围约束
            }
            opt.MkMinimize(objective);  // 最小化目标函数

            // 求解
            if (opt.Check() == Status.SATISFIABLE) {
                Model model = opt.getModel();

                // 获取无偏评分结果
                for (int j = 0; j < n; j++) {
                    result.put("x_" + j, model.evaluate(x[j], true).toString());
                }

                // 获取管理者偏差结果
                for (int i = 0; i < m; i++) {
                    result.put("b_" + i, model.evaluate(b[i], true).toString());
                }
            } else {
                System.out.println("没有找到可行解。");
            }

        } catch (Z3Exception e) {
            e.printStackTrace();
        } finally {
            // 释放Z3资源
            ctx.close();
        }

        return result;
    }
}
