lexer grammar MySimpleLexer;

CASE: 'case';
SWITCH: 'switch';
DOT: '.' ;
EQ : '=' ;
COMMA : ',' ;
SEMI : ';' ;
LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;
LSQUARE: '[' ;
RSQUARE: ']' ;
MINUS: '-';
COLON: ':';
PLUS: '+';
MULT: '*';
FUNCTION: 'function';
DOUBLE_QUOTES: '"' ;
IF: 'if' ;
ELSE: 'else' ;
RETURN: 'return' ;
LET: 'let' ;
STRING: 'string' ;

INT : [0-9]+ ;
ID : [a-zA-Z_][a-zA-Z_0-9]* ;
WS : [ \t\n\r\f]+ -> skip ;