// Generated from /Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab3/AntlrParser/src/main/antlr/org/example/MySimpleParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MySimpleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MySimpleParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MySimpleParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(MySimpleParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(MySimpleParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MySimpleParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(MySimpleParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(MySimpleParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#if_st}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_st(MySimpleParser.If_stContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#else_st}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_st(MySimpleParser.Else_stContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(MySimpleParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(MySimpleParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#return_st}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_st(MySimpleParser.Return_stContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#letDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetDeclaration(MySimpleParser.LetDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#stringExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExp(MySimpleParser.StringExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#funcInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncInvocation(MySimpleParser.FuncInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySimpleParser#methodInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodInvocation(MySimpleParser.MethodInvocationContext ctx);
}