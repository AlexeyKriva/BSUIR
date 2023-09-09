@echo off
@echo Test %2
@echo Test %2 >> result.txt
copy %2 spbmetro.in > nul
timer %1 2000 65536 >> result.txt
check %2 %2.a spbmetro.out >> result.txt
if exist spbmetro.in del spbmetro.in
if exist spbmetro.out del spbmetro.out