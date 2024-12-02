lexer grammar MyLexer;


// Logical Operators
AND : '&&' ;
OR : '||' ;
NOT : '!' ;


// Relational Operators
GT : '>' ;
LT : '<' ;
NEQ : '!=' ;
GTE : '>=' ;
LTE : '<=' ;
EQ : '==' ;


// Assignment Operators
ASSIGN : '=' ;
PLUS_ASSIGN : '+=' ;
MINUS_ASSIGN : '-=' ;
MULT_ASSIGN : '*=' ;
DIV_ASSIGN : '/=' ;
MOD_ASSIGN : '%=' ;


// Delimiters
COMMA : ',' ;
SEMI : ';' ;
LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;
COLON : ':';
DOT : '.';


// Keywords
CLASS : 'class' ;
LOCAL : 'local' ;
GLOBAL : 'global' ;
CONSTRUCTOR : 'constructor' ;
FUNCTION : 'function' ;
FINAL : 'final' ;
VOID : 'void' ;
RETURN : 'return' ;
MAIN : 'main' ;
IF : 'if' ;
ELSE : 'else' ;
SWITCH : 'switch' ;
CASE : 'case' ;
FOR : 'for' ;
WHILE : 'while' ;
BREAK : 'break' ;
CONTINUE : 'continue' ;


// Arithmetic Operators
PLUS : '+' ;
MINUS : '-' ;
MULT : '*' ;
DIV : '/' ;
MOD : '%' ;


// Data Types
STRING_TYPE : 'string' ;
INT_TYPE : 'int' ;
FLOAT_TYPE : 'float' ;
BOOLEAN_TYPE : 'boolean' ;
ELEMENT_TYPE : 'element' ;
ELEMENT_SET_TYPE : 'elementSet' ;


// Literals
BOOLEAN : 'true' | 'false' ;
INT : [0-9]+ ;
FLOAT : [0-9]+ '.' [0-9]* | '.' [0-9]+ ;
STRING : '"' ( ~["\\] | '\\' . )* '"' ;


// Identifier
ID : [a-zA-Z_][a-zA-Z_0-9]* ;


// Whitespace and Comments
WS : [ \t\n\r\f]+ -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;