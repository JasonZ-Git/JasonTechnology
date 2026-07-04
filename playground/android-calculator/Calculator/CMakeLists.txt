cmake_minimum_required(VERSION 3.22.1)

project(calculator)

add_library(
    calculator
    SHARED
    src/main/jni/calculator.cpp
)
