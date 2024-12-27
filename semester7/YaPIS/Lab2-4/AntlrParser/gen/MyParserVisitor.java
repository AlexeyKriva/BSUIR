// Generated from /Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab2-4/AntlrParser/src/main/antlr/org/example/MyParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MyParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MyParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(MyParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MyParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MyParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(MyParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MyParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MyParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(MyParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(MyParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MyParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(MyParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(MyParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MyParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MyParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(MyParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(MyParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(MyParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(MyParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#comparativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparativeExpression(MyParser.ComparativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MyParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#typeCast}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeCast(MyParser.TypeCastContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(MyParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MyParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#voidReturnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidReturnStatement(MyParser.VoidReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MyParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#classBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBlock(MyParser.ClassBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(MyParser.ConstructorContext ctx);
}