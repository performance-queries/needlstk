// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class PerfQueryCompiler {
  public static void main(String[] args) throws Exception {
    // create a CharStream that reads from standard input
    ANTLRInputStream input = new ANTLRInputStream(System.in);

    // create a lexer that feeds off of input CharStream
    perf_queryLexer lexer = new perf_queryLexer(input);

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    perf_queryParser parser = new perf_queryParser(tokens);
    ParseTree tree = parser.prog(); // begin parsing at the prog production

    // Create a walker for the parse tree
    ParseTreeWalker walker = new ParseTreeWalker();

    // Create symbol table
    System.out.println("Creating symbol table ...");
    SymbolTableCreator symbol_table_creator = new SymbolTableCreator();
    walker.walk(symbol_table_creator, tree);

    // Expression tree creator
    System.out.println("Creating expression tree ...");
    ExprTreeCreator expr_tree_creator = new ExprTreeCreator(parser.ID, symbol_table_creator.symbol_table());
    walker.walk(expr_tree_creator, tree);
  }
}