// Generated from /Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab2-4/AntlrParser/src/main/antlr/org/example/MyParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyParser}.
 */
public interface MyParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MyParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MyParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(MyParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(MyParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MyParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MyParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MyParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MyParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MyParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MyParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MyParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MyParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MyParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MyParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MyParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MyParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MyParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MyParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MyParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MyParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(MyParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(MyParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(MyParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(MyParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MyParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MyParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MyParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MyParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(MyParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(MyParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MyParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MyParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(MyParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(MyParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(MyParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(MyParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#comparativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparativeExpression(MyParser.ComparativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#comparativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparativeExpression(MyParser.ComparativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MyParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MyParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#typeCast}.
	 * @param ctx the parse tree
	 */
	void enterTypeCast(MyParser.TypeCastContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#typeCast}.
	 * @param ctx the parse tree
	 */
	void exitTypeCast(MyParser.TypeCastContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MyParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MyParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MyParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MyParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#voidReturnStatement}.
	 * @param ctx the parse tree
	 */
	void enterVoidReturnStatement(MyParser.VoidReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#voidReturnStatement}.
	 * @param ctx the parse tree
	 */
	void exitVoidReturnStatement(MyParser.VoidReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MyParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MyParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#classBlock}.
	 * @param ctx the parse tree
	 */
	void enterClassBlock(MyParser.ClassBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#classBlock}.
	 * @param ctx the parse tree
	 */
	void exitClassBlock(MyParser.ClassBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(MyParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(MyParser.ConstructorContext ctx);
}