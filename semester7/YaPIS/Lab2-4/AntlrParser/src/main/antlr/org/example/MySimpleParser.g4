parser grammar MySimpleParser;
options { tokenVocab=MySimpleLexer; }

program
    : stat EOF
    | def EOF
    ;

stat: ID EQ expr
    | expr
    | letDeclaration
    | funcInvocation
    | switchStatement
    | caseStatement
    ;

def : stat*;

expr: func
    | op
    | if_st
    | return_st
    | stringExp
    ;

op: ID EQ EQ ID
    | ID MINUS EQ ID
    | ID PLUS EQ ID
    | ID (MINUS ID)+
    | ID (PLUS ID)+
    | ID EQ ID
    ;

func : FUNCTION ID LPAREN ID (COMMA ID)* RPAREN LCURLY expr* RCURLY;

if_st: IF LPAREN expr RPAREN LCURLY expr RCURLY
    | IF LPAREN expr RPAREN LCURLY expr RCURLY else_st
    ;

else_st: ELSE LCURLY expr RCURLY;

switchStatement
    : SWITCH LPAREN ID RPAREN LCURLY caseStatement* RCURLY
    ;


caseStatement
    : CASE expr COLON stat
    ;


return_st: RETURN expr;

letDeclaration: LET? ID
    | LET? ID COMMA ID EQ ID COMMA ID
    | LET? ID EQ ID
    | LET? ID EQ funcInvocation
    | LET? ID EQ stringExp
    | LET? ID EQ INT
    | LET? ID EQ STRING
    | LET? ID EQ ID PLUS ID
    | LET? ID EQ ID MINUS ID
    | LET? ID EQ ID MULT INT
    ;

stringExp: DOUBLE_QUOTES (ID)* DOUBLE_QUOTES
    | STRING LPAREN ID RPAREN
    | LSQUARE stringExp (COMMA stringExp)* RSQUARE
    | LSQUARE ID (COMMA ID)* RSQUARE
    ;

funcInvocation: ID LPAREN ID (COMMA ID)* RPAREN
    | ID DOT methodInvocation
    ;

methodInvocation: ID LPAREN INT (COMMA INT) RPAREN
    | ID LPAREN DOUBLE_QUOTES (ID)* DOUBLE_QUOTES RPAREN
    | ID LPAREN DOUBLE_QUOTES (ID)* DOUBLE_QUOTES (COMMA DOUBLE_QUOTES (ID)* DOUBLE_QUOTES)+ RPAREN
    ;