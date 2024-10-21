package com.example.z3solverbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Z3SolverBackendApplication {
	static {
		System.out.println("尝试加载 z3java 库...");
		try {
			System.loadLibrary("z3java"); // 不需要前缀 lib 和后缀 .so
			System.out.println("成功加载 z3java 库");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("加载 z3java 库失败:");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Z3SolverBackendApplication.class, args);
	}
}
