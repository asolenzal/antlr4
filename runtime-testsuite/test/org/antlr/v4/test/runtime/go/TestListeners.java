/* This file is generated by TestGenerator, any edits will be overwritten by the next generation. */
package org.antlr.v4.test.runtime.go;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestListeners extends BaseTest {

	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testBasic() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(546);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@parser::header {\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("@parser::members {\n");
		grammarBuilder.append("type LeafListener struct {\n");
		grammarBuilder.append("	*BaseTListener\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func NewLeafListener() *LeafListener {\n");
		grammarBuilder.append("	return &LeafListener{BaseTListener: &BaseTListener{}}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) VisitTerminal(node antlr.TerminalNode) {\n");
		grammarBuilder.append("	fmt.Println(node.GetSymbol().GetText())\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("fmt.Println($ctx.r.ToStringTree(nil, p))\n");
		grammarBuilder.append("var walker = antlr.NewParseTreeWalker()\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("walker.Walk(NewLeafListener(), $ctx.r)\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("  : r=a ;\n");
		grammarBuilder.append("a : INT INT\n");
		grammarBuilder.append("  | ID\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("MULT: '*' ;\n");
		grammarBuilder.append("ADD : '+' ;\n");
		grammarBuilder.append("INT : [0-9]+ ;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="1 2";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "s", input, false);
		assertEquals(
			"(a 1 2)\n" +
			"1\n" +
			"2\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testLR() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(723);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@parser::header {\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("@parser::members {\n");
		grammarBuilder.append("type LeafListener struct {\n");
		grammarBuilder.append("	*BaseTListener\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func NewLeafListener() *LeafListener {\n");
		grammarBuilder.append("	return &LeafListener{BaseTListener: &BaseTListener{}}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) ExitE(ctx *EContext) {\n");
		grammarBuilder.append("	if ctx.GetChildCount() == 3 {\n");
		grammarBuilder.append("		fmt.Printf(\"%s %s %s\\n\", ctx.E(0)[0].GetStart().GetText(), ctx.E(1)[0].GetStart().GetText(), ctx.E(-1)[0].GetStart().GetText())\n");
		grammarBuilder.append("	} else {\n");
		grammarBuilder.append("		fmt.Println(ctx.INT().GetSymbol().GetText())\n");
		grammarBuilder.append("	}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("fmt.Println($ctx.r.ToStringTree(nil, p))\n");
		grammarBuilder.append("var walker = antlr.NewParseTreeWalker()\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("walker.Walk(NewLeafListener(), $ctx.r)\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("	: r=e ;\n");
		grammarBuilder.append("e : e op='*' e\n");
		grammarBuilder.append("	| e op='+' e\n");
		grammarBuilder.append("	| INT\n");
		grammarBuilder.append("	;\n");
		grammarBuilder.append("MULT: '*' ;\n");
		grammarBuilder.append("ADD : '+' ;\n");
		grammarBuilder.append("INT : [0-9]+ ;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="1+2*3";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "s", input, false);
		assertEquals(
			"(e (e 1) + (e (e 2) * (e 3)))\n" +
			"1\n" +
			"2\n" +
			"3\n" +
			"2 3 2\n" +
			"1 2 1\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testLRWithLabels() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(709);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@parser::header {\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("@parser::members {\n");
		grammarBuilder.append("type LeafListener struct {\n");
		grammarBuilder.append("	*BaseTListener\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func NewLeafListener() *LeafListener {\n");
		grammarBuilder.append("	return &LeafListener{BaseTListener: &BaseTListener{}}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) ExitCall(ctx *CallContext) {\n");
		grammarBuilder.append("	fmt.Printf(\"%s %s\", ctx.E().GetStart().GetText(), ctx.EList())\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) ExitInt(ctx *IntContext) {\n");
		grammarBuilder.append("	fmt.Println(ctx.INT().GetSymbol().GetText())\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("fmt.Println($ctx.r.ToStringTree(nil, p))\n");
		grammarBuilder.append("var walker = antlr.NewParseTreeWalker()\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("walker.Walk(NewLeafListener(), $ctx.r)\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("  : r=e ;\n");
		grammarBuilder.append("e : e '(' eList ')' # Call\n");
		grammarBuilder.append("  | INT             # Int\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("eList : e (',' e)* ;\n");
		grammarBuilder.append("MULT: '*' ;\n");
		grammarBuilder.append("ADD : '+' ;\n");
		grammarBuilder.append("INT : [0-9]+ ;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="1(2,3)";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "s", input, false);
		assertEquals(
			"(e (e 1) ( (eList (e 2) , (e 3)) ))\n" +
			"1\n" +
			"2\n" +
			"3\n" +
			"1 [13 6]\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testRuleGetters_1() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(749);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@parser::header {\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("@parser::members {\n");
		grammarBuilder.append("type LeafListener struct {\n");
		grammarBuilder.append("	*BaseTListener\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func NewLeafListener() *LeafListener {\n");
		grammarBuilder.append("	return &LeafListener{BaseTListener: &BaseTListener{}}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) ExitA(ctx *AContext) {\n");
		grammarBuilder.append("	if ctx.GetChildCount() == 2 {\n");
		grammarBuilder.append("		fmt.Printf(\"%s %s %s\", ctx.B(0)[0].GetStart().GetText(), ctx.B(1)[0].GetStart().GetText(), ctx.B(-1)[0].GetStart().GetText())\n");
		grammarBuilder.append("	} else {\n");
		grammarBuilder.append("		fmt.Println(ctx.B(0)[0].GetStart().GetText())\n");
		grammarBuilder.append("	}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("fmt.Println($ctx.r.ToStringTree(nil, p))\n");
		grammarBuilder.append("var walker = antlr.NewParseTreeWalker()\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("walker.Walk(NewLeafListener(), $ctx.r)\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("  : r=a ;\n");
		grammarBuilder.append("a : b b		// forces list\n");
		grammarBuilder.append("  | b		// a list still\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("b : ID | INT;\n");
		grammarBuilder.append("MULT: '*' ;\n");
		grammarBuilder.append("ADD : '+' ;\n");
		grammarBuilder.append("INT : [0-9]+ ;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="1 2";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "s", input, false);
		assertEquals(
			"(a (b 1) (b 2))\n" +
			"1 2 1\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testRuleGetters_2() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(749);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@parser::header {\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("@parser::members {\n");
		grammarBuilder.append("type LeafListener struct {\n");
		grammarBuilder.append("	*BaseTListener\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func NewLeafListener() *LeafListener {\n");
		grammarBuilder.append("	return &LeafListener{BaseTListener: &BaseTListener{}}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) ExitA(ctx *AContext) {\n");
		grammarBuilder.append("	if ctx.GetChildCount() == 2 {\n");
		grammarBuilder.append("		fmt.Printf(\"%s %s %s\", ctx.B(0)[0].GetStart().GetText(), ctx.B(1)[0].GetStart().GetText(), ctx.B(-1)[0].GetStart().GetText())\n");
		grammarBuilder.append("	} else {\n");
		grammarBuilder.append("		fmt.Println(ctx.B(0)[0].GetStart().GetText())\n");
		grammarBuilder.append("	}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("fmt.Println($ctx.r.ToStringTree(nil, p))\n");
		grammarBuilder.append("var walker = antlr.NewParseTreeWalker()\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("walker.Walk(NewLeafListener(), $ctx.r)\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("  : r=a ;\n");
		grammarBuilder.append("a : b b		// forces list\n");
		grammarBuilder.append("  | b		// a list still\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("b : ID | INT;\n");
		grammarBuilder.append("MULT: '*' ;\n");
		grammarBuilder.append("ADD : '+' ;\n");
		grammarBuilder.append("INT : [0-9]+ ;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "s", input, false);
		assertEquals(
			"(a (b abc))\n" +
			"abc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testTokenGetters_1() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(681);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@parser::header {\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("@parser::members {\n");
		grammarBuilder.append("type LeafListener struct {\n");
		grammarBuilder.append("	*BaseTListener\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func NewLeafListener() *LeafListener {\n");
		grammarBuilder.append("	return &LeafListener{BaseTListener: &BaseTListener{}}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) ExitA(ctx *AContext) {\n");
		grammarBuilder.append("	if ctx.GetChildCount() == 2 {\n");
		grammarBuilder.append("		fmt.Printf(\"%s %s %s\", ctx.INT(0)[0].GetSymbol().GetText(), ctx.INT(1)[0].GetSymbol().GetText(), ctx.INT(0)[0])\n");
		grammarBuilder.append("	} else {\n");
		grammarBuilder.append("		fmt.Println(ctx.ID().GetSymbol())\n");
		grammarBuilder.append("	}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("fmt.Println($ctx.r.ToStringTree(nil, p))\n");
		grammarBuilder.append("var walker = antlr.NewParseTreeWalker()\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("walker.Walk(NewLeafListener(), $ctx.r)\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("  : r=a ;\n");
		grammarBuilder.append("a : INT INT\n");
		grammarBuilder.append("  | ID\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("MULT: '*' ;\n");
		grammarBuilder.append("ADD : '+' ;\n");
		grammarBuilder.append("INT : [0-9]+ ;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="1 2";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "s", input, false);
		assertEquals(
			"(a 1 2)\n" +
			"1 2 [1, 2]\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testTokenGetters_2() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(681);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@parser::header {\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("@parser::members {\n");
		grammarBuilder.append("type LeafListener struct {\n");
		grammarBuilder.append("	*BaseTListener\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func NewLeafListener() *LeafListener {\n");
		grammarBuilder.append("	return &LeafListener{BaseTListener: &BaseTListener{}}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("func (*LeafListener) ExitA(ctx *AContext) {\n");
		grammarBuilder.append("	if ctx.GetChildCount() == 2 {\n");
		grammarBuilder.append("		fmt.Printf(\"%s %s %s\", ctx.INT(0)[0].GetSymbol().GetText(), ctx.INT(1)[0].GetSymbol().GetText(), ctx.INT(0)[0])\n");
		grammarBuilder.append("	} else {\n");
		grammarBuilder.append("		fmt.Println(ctx.ID().GetSymbol())\n");
		grammarBuilder.append("	}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("fmt.Println($ctx.r.ToStringTree(nil, p))\n");
		grammarBuilder.append("var walker = antlr.NewParseTreeWalker()\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("walker.Walk(NewLeafListener(), $ctx.r)\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("  : r=a ;\n");
		grammarBuilder.append("a : INT INT\n");
		grammarBuilder.append("  | ID\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("MULT: '*' ;\n");
		grammarBuilder.append("ADD : '+' ;\n");
		grammarBuilder.append("INT : [0-9]+ ;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "s", input, false);
		assertEquals(
			"(a abc)\n" +
			"[@0,0:2='abc',<4>,1:0]\n", found);
		assertNull(this.stderrDuringParse);

	}

}
