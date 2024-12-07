parser grammar MyParser;


options { tokenVocab=MyLexer; } // Указание на использование созданного Lexer


// Точка входа
program : (functionDeclaration | statement | classDeclaration)* EOF ;


// Правило для объявления функции
functionDeclaration
    : (GLOBAL | LOCAL) FUNCTION type (ID | MAIN) LPAREN parameterList? RPAREN block
    ;


// Правило для типа данных
type
    : VOID
    | INT_TYPE
    | STRING_TYPE
    | FLOAT_TYPE
    | BOOLEAN_TYPE
    | ELEMENT_TYPE
    | ELEMENT_SET_TYPE
    ;


// Список параметров
parameterList
    : parameter (COMMA parameter)*
    ;


//Параметр
parameter
    : type ID
    ;


// Блок кода
block
    : LCURLY statement* RCURLY
    ;


// Оператор
statement
    : variableDeclaration
    | assignment
    | methodCall
    | returnStatement
    | voidReturnStatement
    | ifStatement
    | switchStatement
    | forStatement
    | whileStatement
    | breakStatement
    | continueStatement
    ;

//Оператор выхода
breakStatement
    : BREAK SEMI
    ;

//Оператор продолжения
continueStatement
    : CONTINUE SEMI
    ;

// Условный оператор if
ifStatement
    : IF LPAREN comparativeExpression RPAREN block (ELSE block)?
    ;


// Оператор switch
switchStatement
    : SWITCH LPAREN expression RPAREN LCURLY caseStatement* RCURLY
    ;


// Оператор case
caseStatement
    : CASE expression COLON statement
    ;


// Оператор for
forStatement
    : FOR LPAREN (variableDeclaration | expression)? comparativeExpression SEMI expression RPAREN block
    ;

// Оператор while
whileStatement
    : WHILE LPAREN comparativeExpression RPAREN block
    ;


// Объявление переменной
variableDeclaration
    : LOCAL? (type | ID) ID ASSIGN expression SEMI
    ;


// Присваивание
assignment
    : ID (ASSIGN | PLUS_ASSIGN | MINUS_ASSIGN | MULT_ASSIGN | DIV_ASSIGN | MOD_ASSIGN) expression SEMI
    ;


// Вызов метода
methodCall
    : (expression (DOT ID LPAREN argumentList? RPAREN)
      | ID LPAREN argumentList? RPAREN
    ) SEMI
    ;


// Список аргументов
argumentList
    : expression (COMMA expression)*
    ;

//Сравнительное выражение
comparativeExpression
    : expression GT expression
    | expression LT expression
    | expression NEQ expression
    | expression GTE expression
    | expression LTE expression
    | expression EQ expression
    | comparativeExpression AND comparativeExpression
    | comparativeExpression OR comparativeExpression
    | NOT comparativeExpression
 ;


// Выражение
expression
    : primary (DOT ID)?
    | expression PLUS expression
    | expression MINUS expression
    | expression MULT expression
    | expression DIV expression
    | expression MOD expression
    | expression PLUS_ASSIGN expression
    | expression MINUS_ASSIGN expression
    | expression MULT_ASSIGN expression
    | expression DIV_ASSIGN expression
    | expression MOD_ASSIGN expression
    | typeCast
    ;

// Приведение типов
typeCast
    : LPAREN type RPAREN expression
    ;


// Первичное выражение
primary
    : INT
    | FLOAT
    | BOOLEAN
    | STRING
    | ELEMENT_TYPE LPAREN argumentList? RPAREN
    | ELEMENT_SET_TYPE LPAREN argumentList? RPAREN
    | ID
    | ID LPAREN argumentList? RPAREN
    | LPAREN expression RPAREN
    ;

//Возврат со значением
returnStatement
    : RETURN expression SEMI
    ;




// Возврат без значения для функций типа void
voidReturnStatement
    : RETURN SEMI
    ;

//Декларация класса
classDeclaration
    : (GLOBAL | LOCAL) CLASS ID classBlock
    ;

//Внутренность класса
classBlock
    : LCURLY (constructor variableDeclaration* functionDeclaration*)+ RCURLY
    ;

//Конструктор
constructor
    : (GLOBAL | LOCAL) CONSTRUCTOR LPAREN parameterList? RPAREN block
    ;