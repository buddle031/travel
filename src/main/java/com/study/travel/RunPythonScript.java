package com.study.travel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunPythonScript {
    public static void main(String[] args) {
        try {
            // Python 실행 경로와 스크립트 파일 경로를 설정합니다.
            String pythonScriptPath = "scy.py";  // Python 스크립트 경로
            String pythonExecutable = "python2.7";  // Python 2.7 실행 경로 (환경에 따라 다를 수 있음)

            // 프로세스 빌더를 사용해 Python 스크립트를 실행합니다.
            ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutable, pythonScriptPath);

            // 프로세스를 시작하고 결과를 기다립니다.
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Python 스크립트의 출력을 읽어옵니다.
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 프로세스 종료 코드를 확인합니다.
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

