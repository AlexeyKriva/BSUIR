% Перемещение зелёной лягушки на свободный камень
move([green, X, empty | Rest], [empty, X, green | Rest]).

% Перемещение зелёной лягушки через занятый камень на свободный камень
move([green, X, brown | Rest1], [empty, X, green | Rest2]) :-
    move(Rest1, Rest2).

% Перемещение коричневой лягушки на свободный камень
move([brown, empty, X | Rest], [X, empty, brown | Rest]).

% Перемещение коричневой лягушки через занятый камень на свободный камень
move([brown, X, green | Rest1], [X, empty, brown | Rest2]) :-
    move(Rest1, Rest2).

% Правило для проверки, что состояние камней является допустимым решением задачи
solution([brown, brown, brown, empty, green, green, green]).

% Предикат для решения задачи
solve(State, Steps) :-
    solve(State, [], Steps).

% Рекурсивный предикат для решения задачи
solve(State, _, []) :-
    solution(State). % Если текущее состояние является решением, завершаем поиск.

solve(State, Visited, [Move | Steps]) :-
    move(State, NextState), % Перемещаем лягушку в следующее состояние
    \+ member(NextState, Visited), % Проверяем, что мы не посещали это состояние ранее
    solve(NextState, [NextState | Visited], Steps), % Рекурсивно ищем решение из следующего состояния
    Move = NextState. % Запоминаем текущий шаг
